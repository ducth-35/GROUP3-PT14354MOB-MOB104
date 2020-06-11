package com.example.group3_pt14354_mob_mob104.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group3_pt14354_mob_mob104.R;
import com.example.group3_pt14354_mob_mob104.model.MonAn;

import java.time.Instant;
import java.util.List;

public class adapterMonAn extends ArrayAdapter<MonAn> {
    Activity context;
    int resource;
    List<MonAn> objects;

    public adapterMonAn(Activity context, int resource, List<MonAn> objects) {
        super(context, resource, objects);
        this.context=context;
        this.objects=objects;
        this.resource=resource;
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View row = layoutInflater.inflate(this.resource, null);
        TextView txtTenMon = (TextView) row.findViewById(R.id.txtTenMon);
        TextView txtTitle = (TextView) row.findViewById(R.id.txtTitle);
        ImageView ivIcon = (ImageView) row.findViewById(R.id.ivIcon);
        final MonAn monAn = objects.get(position);
        txtTenMon.setText(monAn.getTxtTenMon());
        txtTitle.setText(monAn.getTitle());
        String URL = monAn.getLinkImg();
//        Picasso.with(context).load(URL).into(ivIcon);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                xuLyClickItem(monAn);
            }
        });
        return row;
    }
//    private void xuLyClickItem(MonAn monAn) {
//        Intent intent=new Intent(context, chitiet.class);
//        intent.putExtra("TenMonAn",monAn.getTxtTenMon());
//        intent.putExtra("Title",monAn.getTitle());
//        intent.putExtra("LinkURL",monAn.getLinkURL());
//        intent.putExtra("LinkImage",monAn.getLinkImg());
//        context.startActivityForResult(intent,13);
//    }
}
