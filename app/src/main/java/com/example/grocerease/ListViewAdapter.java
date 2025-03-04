package com.example.grocerease;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class ListViewAdapter extends ArrayAdapter<listModel> {
    public ListViewAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
