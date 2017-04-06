package ru.merkulyevsasha.industrytest.presentation.flats;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
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
        setUpItemTouchHelper();
    }

    private void setUpItemTouchHelper() {

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // we want to cache these and not allocate anything repeatedly in the onChildDraw method
            Drawable background;
            Drawable xMark;
            int xMarkMargin;
            boolean initiated;

            private void init() {

                background = new ColorDrawable(ContextCompat.getColor(FlatsActivity.this, R.color.colorPrimaryDark));
                xMark = ContextCompat.getDrawable(FlatsActivity.this, R.drawable.ic_clear_black_24dp);
                xMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                xMarkMargin = (int) FlatsActivity.this.getResources().getDimension(R.dimen.ic_clear_margin);
                initiated = true;
            }

            // not important, we don't want drag & drop
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//                int position = viewHolder.getAdapterPosition();
//                FlatsAdapter testAdapter = (FlatsAdapter)recyclerView.getAdapter();
//                if (testAdapter.isUndoOn() && testAdapter.isPendingRemoval(position)) {
//                    return 0;
//                }
                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int swipedPosition = viewHolder.getAdapterPosition();
                FlatsAdapter adapter = (FlatsAdapter) flats.getAdapter();
                Flat item = adapter.getItem(swipedPosition);
                adapter.remove(swipedPosition);
                pres.onRemove(item.getId());
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                View itemView = viewHolder.itemView;

                // not sure why, but this method get's called for viewholder that are already swiped away
                if (viewHolder.getAdapterPosition() == -1) {
                    // not interested in those
                    return;
                }

                if (!initiated) {
                    init();
                }

                // draw background
                background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                background.draw(c);

                // draw x mark
                int itemHeight = itemView.getBottom() - itemView.getTop();
                int intrinsicWidth = xMark.getIntrinsicWidth();
                int intrinsicHeight = xMark.getIntrinsicWidth();

                int xMarkLeft = itemView.getRight() - xMarkMargin - intrinsicWidth;
                int xMarkRight = itemView.getRight() - xMarkMargin;
                int xMarkTop = itemView.getTop() + (itemHeight - intrinsicHeight)/2;
                int xMarkBottom = xMarkTop + intrinsicHeight;
                xMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);

                xMark.draw(c);

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        mItemTouchHelper.attachToRecyclerView(flats);
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
                Snackbar.make(root, R.string.flats_error_message, Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
