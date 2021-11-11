package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class listadapter2 extends BaseAdapter {
    //알레르기 리스트뷰 구현 공간

    private ArrayList<listview_item2> items1 = new ArrayList<listview_item2>();


    TextView prdlstNm;//식품이름
    TextView allergy;//알레르기
    TextView rawmtrl;//원재료


    public listadapter2() {
    }

    @Override
    public int getCount() {
        return items1.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();


        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item2,parent,false);
        }
        prdlstNm=(TextView) convertView.findViewById(R.id.name2);
        allergy=(TextView) convertView.findViewById(R.id.info2);
        rawmtrl=(TextView) convertView.findViewById(R.id.ge);

        listview_item2 listview_item2 = items1.get(position);

        prdlstNm.setText(listview_item2.getprdlstNm());
        allergy.setText(listview_item2.getallergy());
        rawmtrl.setText(listview_item2.getrawmtrl());
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return items1.get(position);
    }



    public void addItem(String prdlstNm, String rawmtrl, String allergy) {
        listview_item2 item = new listview_item2();
        item.setprdlstNm(prdlstNm);
        item.setrawmtrl(rawmtrl);
        item.setallergy(allergy);
        items1.add(item);
    }
    public void delete() {
        items1.clear();
    }
}
