package ru.merkulyevsasha.industrytest.presentation.flats;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.merkulyevsasha.industrytest.IndustryTest;
import ru.merkulyevsasha.industrytest.KeyHolder;
import ru.merkulyevsasha.industrytest.R;
import ru.merkulyevsasha.industrytest.pojo.Building;
import ru.merkulyevsasha.industrytest.pojo.Flat;
import ru.merkulyevsasha.industrytest.presentation.flatdetails.FlatDetailsActivity;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */

public class FlatsActivity extends AppCompatActivity implements FlatsAdapter.OnItemClickListener {

    private RecyclerView flats;
    private FlatsAdapter adapter;

    private View root;
    private ProgressBar progress;

    @Inject
    FlatsPresenterImpl pres;

    private Building building;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        root = findViewById(R.id.root);
        progress = (ProgressBar)findViewById(R.id.progress);

        IndustryTest.getComponent().inject(this);

        Intent intent = getIntent();
        building = (Building) intent.getSerializableExtra(KeyHolder.BUILDING);
        if (building == null) {
            finish();
        }

        setTitle(building.getName());

        flats = (RecyclerView)findViewById(R.id.recyclerview_flats);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        flats.setLayoutManager(layoutManager);
        adapter = new FlatsAdapter(this, new ArrayList<Flat>());
        flats.setAdapter(adapter);
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
    protected void onStart() {
        super.onStart();
        pres.onStart(this);
        pres.onStart(building);
    }

    @Override
    protected void onStop() {
        super.onStop();
        pres.onStop();
    }

    @Override
    public void onFlatItemClick(Flat item) {
        Intent intent = new Intent(this, FlatDetailsActivity.class);
        intent.putExtra(KeyHolder.FLAT, item);
        intent.putExtra(KeyHolder.BUILDING, building);
        startActivity(intent);
    }

    public void show(final List<Flat> items) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setItems(items);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void showProgress(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.VISIBLE);
            }
        });
    }

    public void hideProgress(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
            }
        });
    }

    public void showErrorMessage(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(root, "aaaa", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
