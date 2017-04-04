package ru.merkulyevsasha.industrytest.presentation.flats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ru.merkulyevsasha.industrytest.KeyHolder;
import ru.merkulyevsasha.industrytest.R;
import ru.merkulyevsasha.industrytest.pojo.Flat;
import ru.merkulyevsasha.industrytest.presentation.buildingdetails.BuildingDetailsActivity;
import ru.merkulyevsasha.industrytest.presentation.flatdetails.FlatDetailsActivity;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class FlatsActivity extends AppCompatActivity implements FlatsAdapter.OnItemClickListener {

    RecyclerView buildings;
    FlatsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        List<Flat> flats = new ArrayList<>();

        flats.add(new Flat(1, 1, 1, 50));
        flats.add(new Flat(2, 1, 2, 55));
        flats.add(new Flat(3, 2, 3, 100));
        flats.add(new Flat(4, 3, 1, 80));

        buildings = (RecyclerView)findViewById(R.id.recyclerview_flats);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        buildings.setLayoutManager(layoutManager);
        adapter = new FlatsAdapter(this, flats);
        buildings.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFlatItemClick(Flat item) {
        Intent intent = new Intent(this, FlatDetailsActivity.class);
        intent.putExtra(KeyHolder.FLAT, item);
        startActivity(intent);
    }
}
