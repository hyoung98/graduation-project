package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class listadapter2 extends BaseAdapter {

    private ArrayList<listview_item2> items = new ArrayList<listview_item2>();


    TextView prdlstNm;
    TextView allergy;
    TextView rawmtrl;
    TextView nutrient;

    public listadapter2() {
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
            convertView = inflater.inflate(R.layout.list_item2,parent,false);
        }
        prdlstNm=(TextView) convertView.findViewById(R.id.name2);
        allergy=(TextView) convertView.findViewById(R.id.info2);
        rawmtrl=(TextView) convertView.findViewById(R.id.ge);
        nutrient=(TextView) convertView.findViewById(R.id.sung);

        listview_item2 listview_item2 = items.get(position);

        prdlstNm.setText(listview_item2.getprdlstNm());
        allergy.setText(listview_item2.getallergy());
        rawmtrl.setText(listview_item2.getrawmtrl());
        nutrient.setText(listview_item2.getnutrient());


        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }



    public void addItem(String prdlstNm, String allergy, String rawmtrl, String nutrient) {
        listview_item2 item = new listview_item2();
        item.setprdlstNm(prdlstNm);
        item.setallergy(allergy);
        item.setrawmtrl(rawmtrl);
        item.setnutrient(nutrient);
        items.add(item);
    }
    public void delete() {
        items.clear();
    }
}
