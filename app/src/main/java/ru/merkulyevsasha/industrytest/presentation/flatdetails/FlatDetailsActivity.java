package ru.merkulyevsasha.industrytest.presentation.flatdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import ru.merkulyevsasha.industrytest.KeyHolder;
import ru.merkulyevsasha.industrytest.R;
import ru.merkulyevsasha.industrytest.pojo.Building;
import ru.merkulyevsasha.industrytest.pojo.Flat;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class FlatDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flatdetails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        Flat flat = (Flat) intent.getSerializableExtra(KeyHolder.FLAT);
        Building building = (Building) intent.getSerializableExtra(KeyHolder.BUILDING);
        if (flat == null || building == null) {
            finish();
        }

        TextView name = (TextView)findViewById(R.id.textview_name);
        TextView floors = (TextView)findViewById(R.id.textview_floor);
        TextView square = (TextView)findViewById(R.id.textview_square);

        name.setText(building.getName());
        floors.setText(String.valueOf(flat.getFloor()));
        square.setText(String.valueOf(flat.getSquare()));

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
