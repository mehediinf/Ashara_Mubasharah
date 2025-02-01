package com.ashara_mubasharah.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter implements Filterable {

    private List<String> originalList;
    private List<String> filteredList;
    private int[] imgView;
    private Filter filter;

    public CustomAdapter(List<String> sahabiList, int[] images) {
        this.originalList = sahabiList;
        this.filteredList = new ArrayList<>(sahabiList);
        this.imgView = images;
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public String getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_view, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imgSampleViewId);
        TextView textView = convertView.findViewById(R.id.txtSampleId);

        imageView.setImageResource(imgView[position]);
        textView.setText(filteredList.get(position));

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();
                    List<String> filteredNames = new ArrayList<>();

                    if (constraint == null || constraint.length() == 0) {
                        filteredNames.addAll(originalList);
                    } else {
                        String filterPattern = constraint.toString().toLowerCase().trim();
                        for (String name : originalList) {
                            if (name.toLowerCase().contains(filterPattern)) {
                                filteredNames.add(name);
                            }
                        }
                    }

                    results.values = filteredNames;
                    results.count = filteredNames.size();
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    filteredList.clear();
                    filteredList.addAll((List<String>) results.values);
                    notifyDataSetChanged();
                }
            };
        }
        return filter;
    }

}
