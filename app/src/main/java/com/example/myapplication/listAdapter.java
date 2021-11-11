package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class listAdapter extends BaseAdapter {
//리스트뷰를 만들기 위해서 사용되는 공간 / 이렇게 구현 해야하는 이유는 잘모름...
    private ArrayList<listview_item> items = new ArrayList<listview_item>();


     TextView DESC_KOR;
     TextView SERVING_WT;
     TextView NUTR_CONT1;
     TextView NUTR_CONT2;
     TextView NUTR_CONT3;
     TextView NUTR_CONT4;
     TextView NUTR_CONT5;
     TextView NUTR_CONT6;

    public listAdapter() {
    }

    @Override
    public int getCount() {
        return items.size();
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
            convertView = inflater.inflate(R.layout.list_item,parent,false);
        }
        DESC_KOR=(TextView) convertView.findViewById(R.id.name);
        SERVING_WT=(TextView) convertView.findViewById(R.id.info);
        NUTR_CONT1=(TextView) convertView.findViewById(R.id.dan);
        NUTR_CONT2=(TextView) convertView.findViewById(R.id.dang);
        NUTR_CONT3=(TextView) convertView.findViewById(R.id.tan);
        NUTR_CONT4=(TextView) convertView.findViewById(R.id.gi);
        NUTR_CONT5=(TextView) convertView.findViewById(R.id.na);
        NUTR_CONT6=(TextView) convertView.findViewById(R.id.kcal);

        listview_item listview_item = items.get(position);

        DESC_KOR.setText(listview_item.getDESC_KOR());
        SERVING_WT.setText(listview_item.getSERVING_WT());
        NUTR_CONT1.setText(listview_item.getNUTR_CONT1());
        NUTR_CONT2.setText(listview_item.getNUTR_CONT2());
        NUTR_CONT3.setText(listview_item.getNUTR_CONT3());
        NUTR_CONT4.setText(listview_item.getNUTR_CONT4());
        NUTR_CONT5.setText(listview_item.getNUTR_CONT5());
        NUTR_CONT6.setText(listview_item.getNUTR_CONT6());

        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }



    public void addItem(String DESC_KOR, String SERVING_WT, String NUTR_CONT1, String NUTR_CONT2, String NUTR_CONT3, String NUTR_CONT4, String NUTR_CONT5, String NUTR_CONT6) {
        listview_item item = new listview_item();
        item.setDESC_KOR(DESC_KOR);
        item.setSERVING_WT(SERVING_WT);
        item.setNUTR_CONT1(NUTR_CONT1);
        item.setNUTR_CONT2(NUTR_CONT2);
        item.setNUTR_CONT3(NUTR_CONT3);
        item.setNUTR_CONT4(NUTR_CONT4);
        item.setNUTR_CONT5(NUTR_CONT5);
        item.setNUTR_CONT6(NUTR_CONT6);

        items.add(item);
    }
    public void delete() {
        items.clear();
    }

}
