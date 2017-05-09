package com.marcellogalhardo.quandoo.tablemap;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcellogalhardo.quandoo.R;
import com.marcellogalhardo.quandoo.data.Table;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TableMapAdapter extends RecyclerView.Adapter<TableMapAdapter.ViewHolder> {

    private List<Table> tables;

    private OnTableClickedListener onTableClickedListener;

    public TableMapAdapter() {
        tables = new ArrayList<>();
    }

    @Override
    public TableMapAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_table_map, parent, false);
        return new TableMapAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TableMapAdapter.ViewHolder holder, final int position) {
        Table table = tables.get(position);

        Resources resources = holder.view.getResources();

        holder.table.setText(resources.getString(R.string.table_name, table.getNumber()));

        Context context = holder.view.getContext();
        if (table.isReserved()) {
            holder.image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_table_red));
            holder.status.setText("Reserved");
            holder.status.setTextColor(ContextCompat.getColor(context, R.color.grapefruit));
        } else {
            holder.image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_table_green));
            holder.status.setText("Available");
            holder.status.setTextColor(ContextCompat.getColor(context, R.color.greenish_teal));
        }

        if (onTableClickedListener != null) {
            holder.view.setOnClickListener(v ->
                    onTableClickedListener.onTableClickedListener(position, table));
        } else {
            holder.view.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public void setOnTableClickedListener(OnTableClickedListener onTableClickedListener) {
        this.onTableClickedListener = onTableClickedListener;
    }

    public void setTables(List<Table> tables) {
        this.tables.clear();
        this.tables.addAll(tables);
        notifyDataSetChanged();
    }

    public List<Table> getTables() {
        return tables;
    }

    interface OnTableClickedListener {
        void onTableClickedListener(int position, Table table);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        View view;

        @BindView(R.id.table)
        TextView table;

        @BindView(R.id.image)
        ImageView image;

        @BindView(R.id.status)
        TextView status;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
            DrawableCompat.setAutoMirrored(image.getDrawable(), true);
        }
    }
}
