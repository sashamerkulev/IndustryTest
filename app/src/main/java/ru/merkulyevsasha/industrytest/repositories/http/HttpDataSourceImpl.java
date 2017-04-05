package ru.merkulyevsasha.industrytest.repositories.http;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ru.merkulyevsasha.industrytest.pojo.Building;
import ru.merkulyevsasha.industrytest.pojo.Flat;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class HttpDataSourceImpl implements HttpDataSource {

    private static String data = "[{\"builder\":\"Алекс\",\"flats\":[{\"buildingId\":1,\"floor\":1,\"id\":1,\"square\":50},{\"buildingId\":1,\"floor\":1,\"id\":2,\"square\":100},{\"buildingId\":1,\"floor\":2,\"id\":3,\"square\":55},{\"buildingId\":1,\"floor\":3,\"id\":4,\"square\":80}],\"name\":\"Старый город\",\"floors\":10,\"id\":1},{\"builder\":\"Александр\",\"flats\":[{\"buildingId\":2,\"floor\":1,\"id\":5,\"square\":50},{\"buildingId\":2,\"floor\":1,\"id\":6,\"square\":100},{\"buildingId\":2,\"floor\":2,\"id\":7,\"square\":55},{\"buildingId\":2,\"floor\":3,\"id\":8,\"square\":80}],\"name\":\"Кремлевские звезды\",\"floors\":5,\"id\":2},{\"builder\":\"Авангард\",\"flats\":[{\"buildingId\":3,\"floor\":1,\"id\":9,\"square\":50},{\"buildingId\":3,\"floor\":1,\"id\":10,\"square\":100},{\"buildingId\":3,\"floor\":2,\"id\":11,\"square\":55},{\"buildingId\":3,\"floor\":3,\"id\":12,\"square\":80}],\"name\":\"Новый Оккервиль\",\"floors\":11,\"id\":3},{\"builder\":\"Центр Плюс\",\"flats\":[{\"buildingId\":4,\"floor\":1,\"id\":13,\"square\":50},{\"buildingId\":4,\"floor\":1,\"id\":14,\"square\":100},{\"buildingId\":4,\"floor\":2,\"id\":16,\"square\":55},{\"buildingId\":4,\"floor\":3,\"id\":17,\"square\":80}],\"name\":\"Приневский\",\"floors\":17,\"id\":4},{\"builder\":\"Hamlet\",\"flats\":[{\"buildingId\":5,\"floor\":1,\"id\":18,\"square\":50},{\"buildingId\":5,\"floor\":1,\"id\":19,\"square\":100},{\"buildingId\":5,\"floor\":2,\"id\":20,\"square\":55},{\"buildingId\":5,\"floor\":3,\"id\":21,\"square\":80}],\"name\":\"Петр Великий и Екатерина Великая\",\"floors\":7,\"id\":5},{\"builder\":\"Скандинавия\",\"flats\":[{\"buildingId\":6,\"floor\":1,\"id\":22,\"square\":50},{\"buildingId\":6,\"floor\":1,\"id\":23,\"square\":100},{\"buildingId\":6,\"floor\":2,\"id\":24,\"square\":55},{\"buildingId\":6,\"floor\":3,\"id\":25,\"square\":80}],\"name\":\"Кантемировский\",\"floors\":9,\"id\":6},{\"builder\":\"Северное сияние\",\"flats\":[{\"buildingId\":7,\"floor\":1,\"id\":26,\"square\":50},{\"buildingId\":7,\"floor\":1,\"id\":27,\"square\":100},{\"buildingId\":7,\"floor\":2,\"id\":28,\"square\":55},{\"buildingId\":7,\"floor\":3,\"id\":29,\"square\":80}],\"name\":\"Времена года\",\"floors\":11,\"id\":7},{\"builder\":\"Итака\",\"flats\":[{\"buildingId\":8,\"floor\":1,\"id\":30,\"square\":50},{\"buildingId\":8,\"floor\":1,\"id\":31,\"square\":100},{\"buildingId\":8,\"floor\":2,\"id\":32,\"square\":55},{\"buildingId\":8,\"floor\":3,\"id\":33,\"square\":80}],\"name\":\"Тридевяткино царство\",\"floors\":15,\"id\":8}]";

    private Context context;

    public HttpDataSourceImpl(Context context){
        this.context = context;
    }

    @Override
    public List<Building> getBuildings() {
//        List<Building> builds = new ArrayList<>();
//
//        builds.add(new Building(1, "Старый город", "Алекс", 10, getFlats1()));
//        builds.add(new Building(2, "Кремлевские звезды", "Александр", 5, getFlats2()));
//        builds.add(new Building(3, "Новый Оккервиль", "Авангард", 11, getFlats3()));
//        builds.add(new Building(4, "Приневский", "Центр Плюс", 17, getFlats4()));
//        builds.add(new Building(5, "Петр Великий и Екатерина Великая", "Hamlet", 7, getFlats5()));
//        builds.add(new Building(6, "Кантемировский", "Скандинавия", 9, getFlats6()));
//        builds.add(new Building(7, "Времена года", "Северное сияние", 11, getFlats7()));
//        builds.add(new Building(8, "Тридевяткино царство", "Итака", 15, getFlats8()));
//
//        Gson gson = new Gson();
//        String json = gson.toJson(builds);


        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Building>>(){}.getType();
//        List<Building> builds = gson.fromJson(data, collectionType);

        List<Building> builds = new ArrayList<>();
        try {
            InputStream ins = context.getResources().openRawResource(
                    context.getResources().getIdentifier("data", "raw", context.getPackageName()));
            String result = IOUtils.toString(ins, StandardCharsets.UTF_8);
            builds = gson.fromJson(result, collectionType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builds;
    }

    private List<Flat> getFlats1() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat(1, 1, 1, 50));
        flats.add(new Flat(2, 1, 1, 100));
        flats.add(new Flat(3, 1, 2, 55));
        flats.add(new Flat(4, 1, 3, 80));

        return flats;
    }

    private List<Flat> getFlats2() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat( 5, 2, 1, 50));
        flats.add(new Flat( 6, 2, 1, 100));
        flats.add(new Flat( 7, 2, 2, 55));
        flats.add(new Flat( 8, 2, 3, 80));
        return flats;
    }

    private List<Flat> getFlats3() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat( 9, 3, 1, 50));
        flats.add(new Flat(10, 3, 1, 100));
        flats.add(new Flat(11, 3, 2, 55));
        flats.add(new Flat(12, 3, 3, 80));
        return flats;
    }

    private List<Flat> getFlats4() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat(13, 4, 1, 50));
        flats.add(new Flat(14, 4, 1, 100));
        flats.add(new Flat(16, 4, 2, 55));
        flats.add(new Flat(17, 4, 3, 80));
        return flats;
    }

    private List<Flat> getFlats5() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat(18, 5, 1, 50));
        flats.add(new Flat(19, 5, 1, 100));
        flats.add(new Flat(20, 5, 2, 55));
        flats.add(new Flat(21, 5, 3, 80));
        return flats;
    }

    private List<Flat> getFlats6() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat(22, 6, 1, 50));
        flats.add(new Flat(23, 6, 1, 100));
        flats.add(new Flat(24, 6, 2, 55));
        flats.add(new Flat(25, 6, 3, 80));
        return flats;
    }

    private List<Flat> getFlats7() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat(26, 7, 1, 50));
        flats.add(new Flat(27, 7, 1, 100));
        flats.add(new Flat(28, 7, 2, 55));
        flats.add(new Flat(29, 7, 3, 80));
        return flats;
    }

    private List<Flat> getFlats8() {

        List<Flat> flats = new ArrayList<>();
        flats.add(new Flat(30, 8, 1, 50));
        flats.add(new Flat(31, 8, 1, 100));
        flats.add(new Flat(32, 8, 2, 55));
        flats.add(new Flat(33, 8, 3, 80));

        return flats;
    }
}
