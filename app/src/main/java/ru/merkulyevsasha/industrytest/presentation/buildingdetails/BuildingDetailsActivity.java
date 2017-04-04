package ru.merkulyevsasha.industrytest.presentation.buildingdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;


import ru.merkulyevsasha.industrytest.KeyHolder;
import ru.merkulyevsasha.industrytest.R;
import ru.merkulyevsasha.industrytest.pojo.Building;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class BuildingDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildingdetails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        Building building = (Building) intent.getSerializableExtra(KeyHolder.BUILDING);
        if (building == null) {
            finish();
        }

        TextView name = (TextView)findViewById(R.id.textview_name);
        TextView builder = (TextView)findViewById(R.id.textview_buildername);
        TextView floors = (TextView)findViewById(R.id.textview_floors);

        name.setText(building.getName());
        builder.setText(building.getBuilder());
        floors.setText(String.valueOf(building.getFloors()));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
