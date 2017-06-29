package solmedia.ec.tipcal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import solmedia.ec.tipcal.R;
import solmedia.ec.tipcal.models.TipModel;

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {

    private List<TipModel> dataSet = new ArrayList<>();
    private Context context;
    private OnClickItemTipListener listener;

    public TipAdapter(Context context, OnClickItemTipListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void add(TipModel tipModel) {
        dataSet.add(tipModel);
        notifyDataSetChanged();
    }

    public void clear() {
        dataSet.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TipModel tipModel = dataSet.get(position);
        String template = context.getString(R.string.main_format_tip);
        holder.bind(tipModel, template);
        holder.setOnClickListener(listener, tipModel);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemTip;
        TextView tvItemDate;
        View itemView;


        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            tvItemTip = (TextView) itemView.findViewById(R.id.tvItemTip);
            tvItemDate = (TextView) itemView.findViewById(R.id.tvItemDate);
        }

        protected void bind(TipModel tipModel, String template) {
            String strTip = String.format(template, tipModel.getTip());
            tvItemTip.setText(strTip);
            tvItemDate.setText(tipModel.getTimeStamp().toString());
        }

        public void setOnClickListener(
                final OnClickItemTipListener listener
                , final TipModel tipModel) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(tipModel);
                }
            });
        }
    }
}
