package com.example.myapplication;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;


import java.util.ArrayList;

public class nutrient extends AppCompatActivity {


    TextView edtan,eddan,edgi,eddang,edna;
    ListView listView, listView2;
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> items1 = new ArrayList<String>();

    DBHelper2 dbHelper;
    SQLiteDatabase db2 = null;

    Cursor cursor;
    ArrayAdapter adapter;


    double sum_tan = 0;
    double sum_dan = 0;
    double sum_gi = 0;
    double sum_na = 0;
    double sum_dang = 0;

    private long backBtnTime = 0;

    @SuppressWarnings("unchecked")
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrient);

        edtan = (TextView)  findViewById(R.id.calctan);
        eddan = (TextView) findViewById(R.id.calcdan);
        edgi = (TextView) findViewById(R.id.calcgi);
        eddang = (TextView) findViewById(R.id.calcdang);
        edna = (TextView) findViewById(R.id.calcna);

        Button btn6 = (Button) findViewById(R.id.btn6);

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ai.class);
                startActivity(intent);
                finish();
            }

        });

        dbHelper = new DBHelper2(this, 5);
        db2 = dbHelper.getWritableDatabase();    // ??????/?????? ????????? ????????????????????? ??????
    }

    public void insert2(View v) {
        Intent intent = getIntent(); //foodserch?????? ????????? ????????????
        String tan = " ";
        String dan = " ";
        String gi = " ";
        String na = " ";
        String dang = " ";

        try {
            tan = intent.getStringExtra("tan");
            dan = intent.getStringExtra("dan");
            gi  = intent.getStringExtra("gi");
            na = intent.getStringExtra("na");
            dang = intent.getStringExtra("dang");
//"N/A"??? ????????? ????????? ???????????? ?????? ?????? ?????? ?????????
            try{
                sum_tan = Double.parseDouble(tan);
               // sum_tan += t;
            }catch(NumberFormatException e){
                sum_tan = 0;

            }catch(Exception e){
                e.printStackTrace();
                //sum_tan = 0;
            }////////////////
            try{
                sum_dan = Double.parseDouble(dan);
               // sum_dan += d;
            }catch(NumberFormatException e){
                sum_dan = 0;

            }catch(Exception e){
                e.printStackTrace();
                //sum_tan = 0;
            }/////////////////
            try{
                sum_gi = Double.parseDouble(gi);
                //sum_gi += g;
            }catch(NumberFormatException e){
                sum_gi = 0;

            }catch(Exception e){
                e.printStackTrace();
                //sum_tan = 0;
            }/////////////
            try{
                sum_na = Double.parseDouble(na);
               // sum_na += n;
            }catch(NumberFormatException e){
                sum_na = 0;

            }catch(Exception e){
                e.printStackTrace();
                //sum_tan = 0;
            }///////////////
            try{
                sum_dang = Double.parseDouble(dang);
               // sum_dang += da;
                //Toast.makeText(nutrient.this, Integer.toString((int) sum_dang), Toast.LENGTH_LONG).show();
            }catch(NumberFormatException e){
                sum_dang = 0;
                //Toast.makeText(nutrient.this, Integer.toString((int) sum_dang), Toast.LENGTH_LONG).show();

            }catch(Exception e){
                e.printStackTrace();
                //sum_tan = 0;
            }




            //????????? ????????? ????????????
          /*  if(dang == "N/A") {


            } else {sum_tan = Double.parseDouble(tan); }

            if(dan == "N/A") {
                sum_dan = 0;

            } else {sum_dan = Double.parseDouble(dan); }

            if(gi == "N/A") {
                sum_tan = 0;

            } else {sum_gi = Double.parseDouble(gi); }

            if(na == "N/A") {
                sum_na = 0;

            } else {sum_na = Double.parseDouble(na); }

            if(dang == "N/A") {
                sum_dang = 0;

            } else {sum_dang = Double.parseDouble(dang); }*/

            //Toast.makeText(nutrient.this, Integer.toString(sum_tan), Toast.LENGTH_LONG).show();


            db2.execSQL("INSERT INTO tableName2 VALUES ('" + sum_tan + "', '" + sum_dan + "','" + sum_gi +"','" + sum_na + "','" + sum_dang + "');"); //????????????????????? ??????
        } catch (NullPointerException e) {
            Toast.makeText(nutrient.this, "??????", Toast.LENGTH_LONG).show();
        }

        cursor = db2.rawQuery("SELECT * FROM tableName2", null);
        startManagingCursor(cursor);    //??????????????? ??????????????? ????????? ??????????????? ?????? ??????.

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        while (cursor.moveToNext()) {
           /* double tan1 = cursor.getInt(0); //tan1??? ?????????
            double dan1 = cursor.getInt(1);
            double gi1 = cursor.getInt(2);
            double dang1 = cursor.getInt(3);
            double na1 = cursor.getInt(4);*/


            String sql = "SELECT sum(tan), sum(dan), sum(gi), sum(na), sum(dang) FROM tableName2"; //?????? ????????? ??????????????????
            Cursor cursor1 = db2.rawQuery(sql,null); //?????? ??????
            while (cursor1.moveToNext()) {

                double tan1 = cursor1.getDouble(0);
                String tan2 = String.format("%.2f",tan1);
                edtan.setText(String.valueOf(tan2));

                double dan1 = cursor1.getDouble(1);
                String dan2 = String.format("%.2f",dan1);
                eddan.setText(String.valueOf(dan2));

                double gi1 = cursor1.getDouble(2);
                String gi2 = String.format("%.2f",gi1);
                edgi.setText(String.valueOf(gi2));
//String.format("%.6f")
                double na1 = cursor1.getDouble(3);
                String na2 = String.format("%.2f",na1);
                edna.setText(String.valueOf(na2));

                double dang1 = cursor1.getDouble(4);
                String dang2 = String.format("%.2f",dang1);
                eddang.setText(String.valueOf(dang2));
            }

        }

    }

    public void delete2(View v) {//?????????????????? ????????? ?????? ????????????
        String sqlDelete = "DELETE FROM tableName2";
        db2.execSQL(sqlDelete);

        sum_tan = 0;
        sum_dan = 0;
        sum_gi = 0;
        sum_na = 0;
        sum_dang = 0;

        edtan.setText(String.valueOf(sum_tan));
        eddan.setText(String.valueOf(sum_dan));
        edgi.setText(String.valueOf(sum_gi));
        eddang.setText(String.valueOf(sum_dang));
        edna.setText(String.valueOf(sum_na));

    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
        }
        else {
            backBtnTime = curTime;
            Toast.makeText(this, "?????? ??? ????????? ???????????????.",Toast.LENGTH_SHORT).show();
        }
    }

}
