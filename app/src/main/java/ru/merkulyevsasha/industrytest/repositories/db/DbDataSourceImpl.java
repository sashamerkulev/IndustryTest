package ru.merkulyevsasha.industrytest.repositories.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.merkulyevsasha.industrytest.pojo.Building;
import ru.merkulyevsasha.industrytest.pojo.Flat;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class DbDataSourceImpl extends SQLiteOpenHelper implements DbDataSource {

    private static final String TAG = DbDataSourceImpl.class.getSimpleName();

    private static final String DATABASE_NAME = "industry";
    private static final int DATABASE_VERSION = 1;

    private static final String BUILDING_TABLE_NAME = "building";
    private static final String FLAT_TABLE_NAME = "flat";


    private static final String ID = "id";
    private static final String BUILDING_NAME = "name";
    private static final String BUILDER_NAME = "builder";
    private static final String FLOORS = "floors";

    private static final String BUILDING_ID = "buildingId";
    private static final String FLOOR = "floor";
    private static final String SQUARE = "square";
    private static final String DELETED = "deleted";

    public DbDataSourceImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_BUILDING_TABLE = "CREATE TABLE " + BUILDING_TABLE_NAME +
                "( " +
                ID + " INTEGER PRIMARY KEY, " +
                BUILDING_NAME + "  TEXT, " +
                BUILDER_NAME + " TEXT, " +
                FLOORS + " INTEGER " +
                ");"
                +" create index "+BUILDING_TABLE_NAME+"_id_index on " + BUILDING_TABLE_NAME + "(" + ID + "); ";

        String CREATE_FLATS_TABLE = "CREATE TABLE " + FLAT_TABLE_NAME +
                "( " +
                ID + " INTEGER PRIMARY KEY, " +
                BUILDING_ID + "  INTEGER, " +
                FLOOR + " INTEGER, " +
                SQUARE + " INTEGER, " +
                DELETED + " INTEGER " +
                ");"
                + " create index "+FLAT_TABLE_NAME+"_id_index on " + FLAT_TABLE_NAME + "(" + ID + "); "
                + " create index "+FLAT_TABLE_NAME+"_buildingid_index on " + FLAT_TABLE_NAME + "(" + BUILDING_ID + "); ";

        db.execSQL(CREATE_BUILDING_TABLE);
        db.execSQL(CREATE_FLATS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public List<Building> getBuildings() {
        ArrayList<Building> items = new ArrayList<>();
        try (SQLiteDatabase db = getReadableDatabase()) {
            String select = "SELECT * FROM " + BUILDING_TABLE_NAME;
            try (Cursor cursor = db.rawQuery(select, null)) {
                if (cursor.moveToFirst()) {
                    do {
                        Building item = new Building();
                        item.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                        item.setName(cursor.getString(cursor.getColumnIndex(BUILDING_NAME)));
                        item.setBuilder(cursor.getString(cursor.getColumnIndex(BUILDER_NAME)));
                        item.setFloors(cursor.getInt(cursor.getColumnIndex(FLOORS)));
                        items.add(item);
                    } while (cursor.moveToNext());
                }
            }
        }
        return items;
    }

    @Override
    public List<Flat> getFlats(int buildingId) {
        ArrayList<Flat> items = new ArrayList<>();
        try (SQLiteDatabase db = getReadableDatabase()) {
            String select = "SELECT * FROM " + FLAT_TABLE_NAME + " WHERE "+BUILDING_ID+"=@buildingId and "+DELETED+"=0";
            try(Cursor cursor = db.rawQuery(select, new String[]{String.valueOf(buildingId)})) {
                if (cursor.moveToFirst()) {
                    do {
                        Flat item = new Flat();
                        item.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                        item.setBuildingId(cursor.getInt(cursor.getColumnIndex(BUILDING_ID)));
                        item.setFloor(cursor.getInt(cursor.getColumnIndex(FLOOR)));
                        item.setSquare(cursor.getInt(cursor.getColumnIndex(SQUARE)));
                        items.add(item);
                    } while (cursor.moveToNext());
                }
            }
        }
        return items;
    }

    @Override
    public void saveBuildings(List<Building> items) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            try {
                db.beginTransaction();
                for (Building building : items) {
                    ContentValues buildingValues = new ContentValues();
                    buildingValues.put(ID, building.getId());
                    buildingValues.put(BUILDING_NAME, building.getName());
                    buildingValues.put(BUILDER_NAME, building.getBuilder());
                    buildingValues.put(FLOORS, building.getFloors());
                    int buildingResult = (int) db.insertWithOnConflict(BUILDING_TABLE_NAME, null, buildingValues, SQLiteDatabase.CONFLICT_IGNORE);
                    if (buildingResult == -1) {
                        db.update(BUILDING_TABLE_NAME, buildingValues, ID+"=?", new String[] {String.valueOf(building.getId())});
                    }
                    if (building.getFlats() != null){
                        for (Flat flat : building.getFlats()) {
                            ContentValues flatValues = new ContentValues();
                            flatValues.put(ID, flat.getId());
                            flatValues.put(BUILDING_ID, flat.getBuildingId());
                            flatValues.put(FLOOR, flat.getFloor());
                            flatValues.put(SQUARE, flat.getSquare());
                            flatValues.put(DELETED, 0);
                            int flatResult = (int) db.insertWithOnConflict(FLAT_TABLE_NAME, null, flatValues, SQLiteDatabase.CONFLICT_IGNORE);
                            if (flatResult == -1) {
                                flatValues.remove(DELETED);
                                db.update(FLAT_TABLE_NAME, flatValues, ID+"=?", new String[] {String.valueOf(flat.getId())});
                            }
                        }
                    }
                }
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }

        }

    }

    @Override
    public void deleteFlat(int flatId) {
        try (SQLiteDatabase db = getWritableDatabase()) {
            //int rows = db.delete(FLAT_TABLE_NAME, "id=?", new String[]{ String.valueOf(flatId) });
            ContentValues values = new ContentValues();
            values.put(DELETED, 1);
            int rows = db.update(FLAT_TABLE_NAME, values, "id=?", new String[]{ String.valueOf(flatId) });
            Log.d(TAG, "deleted rows"+rows);
        }
    }
}
