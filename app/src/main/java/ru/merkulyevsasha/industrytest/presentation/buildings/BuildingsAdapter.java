package ru.merkulyevsasha.industrytest.presentation.buildings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import ru.merkulyevsasha.industrytest.R;
import ru.merkulyevsasha.industrytest.pojo.Building;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */
public class BuildingsAdapter extends RecyclerView.Adapter<BuildingsAdapter.ViewHolder> {

    private static final String TAG = BuildingsAdapter.class.getSimpleName();
    private Context mContext;
    private List<Building> mList;

    BuildingsAdapter(Context context, List<Building> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.building_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Building item = mList.get(position);

        holder.buildingName.setText(item.getName());
        holder.builderName.setText(item.getBuilder());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof BuildingsAdapter.OnFlatItemClickListener) {
                    ((BuildingsAdapter.OnFlatItemClickListener)mContext).onBuildingFlatClick(item);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (mContext instanceof BuildingsAdapter.OnItemClickListener) {
                    ((BuildingsAdapter.OnItemClickListener)mContext).onBuildingClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setItems(List<Building> items){
        mList = items;
    }

    interface OnItemClickListener {
        void onBuildingClick(Building item);
    }

    interface OnFlatItemClickListener {
        void onBuildingFlatClick(Building item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView buildingName;
        private final TextView builderName;
        private final ImageButton button;

        ViewHolder(View itemView) {
            super(itemView);

            buildingName = (TextView)itemView.findViewById(R.id.textview_building_name);
            builderName = (TextView)itemView.findViewById(R.id.textview_builder_name);
            button = (ImageButton)itemView.findViewById(R.id.button_flats);

        }
    }
}