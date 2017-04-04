package ru.merkulyevsasha.industrytest.presentation.flats;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.merkulyevsasha.industrytest.R;
import ru.merkulyevsasha.industrytest.pojo.Flat;

/**
 * Created by sasha_merkulev on 04.04.2017.
 */
class FlatsAdapter extends RecyclerView.Adapter<FlatsAdapter.ViewHolder> {

    private static final String TAG = FlatsAdapter.class.getSimpleName();
    private Context mContext;
    private List<Flat> mList;

    FlatsAdapter(Context context, List<Flat> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.flat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Flat item = mList.get(position);

        holder.floor.setText(String.valueOf(item.getFloor()));
        holder.square.setText(String.valueOf(item.getSquare()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof FlatsAdapter.OnItemClickListener) {
                    ((FlatsAdapter.OnItemClickListener)mContext).onFlatItemClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setItems(List<Flat> items){
        mList = items;
    }

    interface OnItemClickListener {
        void onFlatItemClick(Flat item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView floor;
        private final TextView square;

        ViewHolder(View itemView) {
            super(itemView);

            floor = (TextView)itemView.findViewById(R.id.textview_floor);
            square = (TextView)itemView.findViewById(R.id.textview_square);

        }
    }
}