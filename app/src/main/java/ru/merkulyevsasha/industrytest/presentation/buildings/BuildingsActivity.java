package ru.merkulyevsasha.industrytest.presentation.buildings;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.merkulyevsasha.industrytest.IndustryTest;
import ru.merkulyevsasha.industrytest.JobServiceUpdater;
import ru.merkulyevsasha.industrytest.KeyHolder;
import ru.merkulyevsasha.industrytest.R;
import ru.merkulyevsasha.industrytest.pojo.Building;
import ru.merkulyevsasha.industrytest.presentation.buildingdetails.BuildingDetailsActivity;
import ru.merkulyevsasha.industrytest.presentation.flats.FlatsActivity;

public class BuildingsActivity extends AppCompatActivity
        implements BuildingsAdapter.OnItemClickListener, BuildingsAdapter.OnFlatItemClickListener {

    private RecyclerView buildings;
    private BuildingsAdapter adapter;

    @Inject
    BuildingsPresenterImpl pres;

    private View root;
    private ProgressBar progress;
    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        root = findViewById(R.id.root);
        progress = (ProgressBar)findViewById(R.id.progress);
        swipe = (SwipeRefreshLayout)findViewById(R.id.swipe);

        IndustryTest.getComponent().inject(this);

        buildings = (RecyclerView)findViewById(R.id.recyclerview_buildings);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        buildings.setLayoutManager(layoutManager);
        adapter = new BuildingsAdapter(this, new ArrayList<Building>());
        buildings.setAdapter(adapter);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        JobServiceUpdater.registerJobService(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buildings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.action_refresh) {
            refresh();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        pres.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        pres.onStop();
    }

    @Override
    public void onBuildingClick(Building item) {
        Intent intent = new Intent(this, BuildingDetailsActivity.class);
        intent.putExtra(KeyHolder.BUILDING, item);
        startActivity(intent);
    }

    @Override
    public void onBuildingFlatClick(Building item) {
        Intent intent = new Intent(this, FlatsActivity.class);
        intent.putExtra(KeyHolder.BUILDING, item);
        startActivity(intent);
    }

    private void refresh(){
        pres.onRefresh();
    }

    public void show(final List<Building> items) {
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
                swipe.setRefreshing(true);
            }
        });
    }

    public void hideProgress(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(false);
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
