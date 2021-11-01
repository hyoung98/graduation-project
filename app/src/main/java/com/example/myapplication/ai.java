package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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


public class ai extends AppCompatActivity {
/*데이터베이스 저장 구역
* foodsearch화면에서 리스트 클릭시 그 내용들이 데이터베이스에 저장되어 이 화면에
* 리스트로 나타나게 하는 영역*/
    EditText editText, editText2;
    ListView listView, listView2;

    DBHelper dbHelper;
    SQLiteDatabase db = null;
    Cursor cursor;
    ArrayAdapter adapter, adapter2;
    Intent intent = getIntent();

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.ai);
        


        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        listView = findViewById(R.id.listView);
        listView2 = findViewById(R.id.listView2);

        dbHelper = new DBHelper(this, 3);
        db = dbHelper.getWritableDatabase();    // 읽기/쓰기 모드로 데이터베이스를 오픈
    }
    public void listUpdate(View v) {//데이터 베이스 업데이트 구역
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

        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);
    }



    public void insert(View v) {//데이터베이스 칼로리 정보삽입구역
        //String name = editText.getText().toString();
        //String info = editText2.getText().toString();
        String value = (String)intent.getStringExtra("list");
        String value2 = (String)intent.getStringExtra("list2");

        db.execSQL("INSERT INTO tableName VALUES ('" + value + "', '" + value2 + "');");

        Toast.makeText(getApplicationContext(), "추가 성공", Toast.LENGTH_SHORT).show();

        //editText.setText("");
        //editText2.setText("");


        //String add_array="";

       /* String array[] = intent.getExtras().getStringArray("list");
        for(int i=0;i<array.length;i++){
            add_array+=array[i]+",";
        }
        adapter.setText(add_array);*/
    }

    public void delete(View v) {//데이터베이스 칼로리 정보 삭제구역
        String name = editText.getText().toString();
        db.execSQL("DELETE FROM tableName WHERE name = '" + name + "';");
    }


}
