package com.example.myapplication_gridview;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdpter extends BaseAdapter {
    private String[] names;
    private int[] icons;
    private Context context;
    private SharedPreferences sp;

    public MainAdpter(String[] names, int[] icons, Context context) {
        super();
        this.names = names;
        this.icons = icons;
        this.context = context;
        sp = context.getSharedPreferences("hxj", Context.MODE_PRIVATE);
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return names[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(context, R.layout.item_main, null);
        }
        ImageView imageView = view.findViewById(R.id.iv_item_icon);
        TextView textView = view.findViewById(R.id.tv_item_name);
        imageView.setImageResource(icons[i]);
        textView.setText(names[i]);
        if (i == 0) {
            String savedName = sp.getString("NAME", null);
            if (savedName != null) {
                textView.setText(savedName);
            }
        }
        return view;
    }
}
