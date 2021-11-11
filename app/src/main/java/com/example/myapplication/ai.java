package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.SimpleFormatter;


public class ai extends AppCompatActivity {
    /*데이터베이스 저장 구역
     * foodsearch화면에서 리스트 클릭시 그 내용들이 데이터베이스에 저장되어 이 화면에
     * 리스트로 나타나게 하는 영역*/
    EditText editText, editText2;
    ListView listView, listView2;
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> items1 = new ArrayList<String>();

    DBHelper dbHelper;
    SQLiteDatabase db = null;

    Cursor cursor;

    listAdapter adapter;

    //ArrayAdapter adapter, adapter2; - 쓰는 것


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.ai);
        adapter = new listAdapter();

        listView = findViewById(R.id.listView);
        listView2 = findViewById(R.id.listView2);

        dbHelper = new DBHelper(this, 8);
        //dbHelper = new DBHelper(this, 1);
        db = dbHelper.getWritableDatabase();    // 읽기/쓰기 모드로 데이터베이스를 오픈

    }
 /*   public void listUpdate(View v) {//데이터 베이스 업데이트 구역
        cursor = db.rawQuery("SELECT * FROM tableName", null);
        startManagingCursor(cursor);    //엑티비티의 생명주기와 커서의 생명주기를 같게 한다.

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        while (cursor.moveToNext()) {
            adapter.add(cursor.getString(0));
            adapter2.add(cursor.getString(1));

        }

        /*
        cursor.moveToFirst();
        cursor.moveToPrevious();
        cursor.moveToPosition(2);
        */

        /*listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);
    }*/



    public void insert(View v) {//데이터베이스 칼로리 정보삽입구역

        Intent intent = getIntent();
        //String name = editText.getText().toString();
        //String info = editText2.getText().toString();

        String name = " ";
        String info = " ";
        String na =" ";
        String kcal =" ";
        String tan =" ";
        String dan =" ";
        String gi =" ";
        String dang =" ";

        try {
            name = intent.getStringExtra("name");
            info = intent.getStringExtra("info");
            na = intent.getStringExtra("na");
            kcal = intent.getStringExtra("kcal");
            tan = intent.getStringExtra("tan");
            dan = intent.getStringExtra("dan");
            gi = intent.getStringExtra("gi");
            dang = intent.getStringExtra("dang");

            //Toast.makeText(ai.this, name1 + "클릭", Toast.LENGTH_LONG).show();
            //String value2 = (String)intent.getStringExtra("info");
            //  db.execSQL("INSERT INTO tableName VALUES ('" + name1 + "', '" + info1 + "');");
            db.execSQL("INSERT INTO tableName VALUES ('" + name + "', '" + info + "', '"+ na + "', '" + kcal + "', '" + tan + "', '"+ dan + "', '"+ gi + "', '" + dang + "');");



        }catch (NullPointerException e) {
            Toast.makeText(ai.this, "오류남", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(getApplicationContext(), "추가 성공", Toast.LENGTH_SHORT).show();

        //editText.setText("");
        //editText2.setText("");

        cursor = db.rawQuery("SELECT * FROM tableName", null);
        startManagingCursor(cursor);    //엑티비티의 생명주기와 커서의 생명주기를 같게 한다.

        //adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,items);
       // adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
       // adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        while (cursor.moveToNext()) {
            adapter.addItem(name, info,na,kcal,tan,dan,gi,dang);
         //  adapter.add(cursor.getString(0));
        //    adapter2.add(cursor.getString(1));

        }

        /*
        cursor.moveToFirst();
        cursor.moveToPrevious();
        cursor.moveToPosition(2);
        */

        listView.setAdapter(adapter);
      //  listView2.setAdapter(adapter2);
    }

    public void delete(View v) {//데이터베이스 칼로리 정보 삭제구역
        //String name = editText.getText().toString();
        // db.execSQL("DELETE FROM tableName WHERE name = '" + name + "';");
        //adapter.delete();
        //adapter.notifyDataSetChanged(); //갱신
        // items.clear();
        // items1.clear();

        Intent intent = getIntent();
        //String name = editText.getText().toString();
        //String info = editText2.getText().toString();
        String name1 = " ";
        String info1 = " ";
        try {
            name1 = intent.getStringExtra("name");
            info1 = intent.getStringExtra("info");
            db.execSQL("DELETE FROM tableName WHERE name = '" + name1 + "';");
            //db.execSQL("DELETE FROM tableName WHERE info = '" + info1+ "';");

        }catch (NullPointerException e) {
            Toast.makeText(ai.this, "오류남", Toast.LENGTH_LONG).show();
        }


    }
    //날짜 기준으로 초기화 하기
/*   public static void delete1(SQLiteDatabase database){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-1);//하루 전 날짜
        SimpleFormatter sdf = new SimpleFormatter("yyyy-mm-dd");
        String pDate = sdf.format(cal.getTime());
        database.execSQL("DELETE FROM TB_CHARGE_HISTORYWHERE_start_data <"+""+pDate + "00:00:00");
    }*/

}
