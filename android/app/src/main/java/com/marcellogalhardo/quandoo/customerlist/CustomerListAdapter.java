package com.marcellogalhardo.quandoo.customerlist;

import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcellogalhardo.quandoo.R;
import com.marcellogalhardo.quandoo.data.Customer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {

    private List<Customer> customersFiltered;

    private List<Customer> customers;

    private ValueFilter valueFilter;

    private OnCustomerClickedListener onCustomerClickedListener;

    public CustomerListAdapter() {
        customers = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_customer_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Customer customer = customersFiltered.get(position);

        holder.customerName.setText(customer.getName());

        if (onCustomerClickedListener != null) {
            holder.view.setOnClickListener(v ->
                    onCustomerClickedListener.onCustomerClicked(position, customer));
        } else {
            holder.view.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        if (customersFiltered != null && customersFiltered.size() >= 0) {
            return customersFiltered.size();
        }
        return 0;
    }

    public void setOnCustomerClickedListener(OnCustomerClickedListener onBusinessClickedListener) {
        this.onCustomerClickedListener = onBusinessClickedListener;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers.clear();
        this.customers.addAll(customers);
        getFilter().filter("");
        notifyDataSetChanged();
    }

    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<Customer> filterList = new ArrayList<>();
                for (int i = 0; i < customers.size(); i++) {
                    Customer customer = customers.get(i);
                    if ((customer.getName().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(customer);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = customers.size();
                results.values = customers;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            customersFiltered = (List<Customer>) results.values;
            notifyDataSetChanged();
        }

    }

    interface OnCustomerClickedListener {
        void onCustomerClicked(int position, Customer customer);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        View view;

        @BindView(R.id.customer_name)
        TextView customerName;

        @BindView(R.id.chevron)
        ImageView chevron;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
            DrawableCompat.setAutoMirrored(chevron.getDrawable(), true);
        }
    }

}
