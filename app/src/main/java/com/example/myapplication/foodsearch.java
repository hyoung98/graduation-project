package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class foodsearch extends AppCompatActivity {


    ListView listView;
    //ArrayAdapter adapter;
    listAdapter adapter;

    //StringBuffer buffer = new StringBuffer();

   // ArrayList<String> items= new ArrayList<String>();


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodsearch);

        adapter = new listAdapter();
        listView=findViewById(R.id.listview);
        listView.setAdapter(adapter);

        //adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,items);

        //원래 layout을 .xml을 만들어야 하지만 예제이므로 안드로이에서 제공하는 것(android.R.layout.simple_list_item_1)을 사용
        //listView.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                final String[] data = new String[1];

                switch (v.getId()){
                    case R.id.button:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //items.clear();

                                data[0] =getXmlData();


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String[] array = data[0].split("//");


                                        for(int i=0;i<array.length/8;i++) {
                                           //items.add(Arrays.toString(new String[]{array[i]}));
                                            //items.add(array[i]);
                                           // adapter.addItem(array[i],array[i+1],array[i+2],array[i+3],array[i+4],array[i+5],array[i+6],array[i+7]);
                                            adapter.addItem(array[i*8],array[i*8+1],array[i*8+2],array[i*8+3],array[i*8+4],array[i*8+5],array[i*8+6],array[i*8+7]);
                                        }
                                        adapter.notifyDataSetChanged();
                                       // text.setText(data[0]);//text에 정보가 들어가 있음 -> 리스트로 옮겨보기


                                    }
                                });
                            }
                        }).start();
                        break;
                }

            }
        });
    }


   /*public void mOnClick(View v){
       //Intent intent = new Intent(getApplicationContext(), ai.class);
       Intent intent = getIntent();
       final String[] data = new String[1];

       TextView text= (TextView)findViewById(R.id.result);
       switch (v.getId()){
           case R.id.button:
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       data[0] =getXmlData();


                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               text.setText(data[0]);//text에 정보가 들어가 있음 -> 리스트로 옮겨보기

                               // adapter.addItem(DESC_KOR,SERVING_WT,NUTR_CONT1,NUTR_CONT2,NUTR_CONT3,NUTR_CONT4,NUTR_CONT5,NUTR_CONT6);
                               //adapter.notifyDataSetChanged();
                           }
                       });
                   }
               }).start();
               break;
       }
    }*/

    public void btnStart(View v) {//리스트뷰 초기화 부분
        adapter.delete();
        adapter.notifyDataSetChanged(); //갱신
    }

    String getXmlData() {

        StringBuffer buffer = new StringBuffer();

        EditText edit= (EditText)findViewById(R.id.edit);
        String str = edit.getText().toString();//EditText에 작성된 Text얻어오기
        String location = URLEncoder.encode(str);
        String key="uM1BflnC15UNtCU8fk2opAqsjTZW32mracsZ8BcWd01OTq%2B7CPNW%2F2DiTGloCk6oPu7WljlvxzviSJLFoDpp8w%3D%3D";



        /* 식품 칼로리, 탄단지 정보 파싱부분 */
        String queryUrl = "http://apis.data.go.kr/1470000/FoodNtrIrdntInfoService/getFoodNtrItdntList?serviceKey=" + key + "&desc_kor=" + location + "&pageNo=1&numOfRows=5";

        try {
            URL url = new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기

            String tag;


            xpp.next();
            int eventType = xpp.getEventType();


            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        //buffer.append("파싱을 시작합니다.\n\n");

                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();//테그 이름 얻어오기

                        if (tag.equals("item")){
                            //items.clear();

                        }// 첫번째 검색결과
                        else if (tag.equals("DESC_KOR")) {

                            buffer.append("식품이름 : "); //buffer에는 식품이름만 넣기
                            xpp.next();
                            buffer.append(xpp.getText());// DESC_KOR 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("//"); //줄바꿈 문자 추가
                            //buffer.append("*"); //줄바꿈 문자 추가

                            //Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
                            //buffer.toString();

                            //String movieNm = xpp.getText();
                           // listItem.add(movieNm);



                        } else if (tag.equals("SERVING_WT")) {
                            buffer.append("1회제공량 : "); //buffer1
                            xpp.next();
                            buffer.append(xpp.getText());//SERVING_WT 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("//"); //줄바꿈 문자 추가
                            //buffer.append("**");//줄바꿈 문자 추가


                        } else if (tag.equals("NUTR_CONT1")) {
                            buffer.append("칼로리 : "); //buffer2
                            xpp.next();
                            buffer.append(xpp.getText());//NUTR_CONT1 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("//"); //줄바꿈 문자 추가
                            //buffer.append("***");//줄바꿈 문자 추가



                        } else if (tag.equals("NUTR_CONT2")) {
                            buffer.append("탄수화물 : "); //buffer3
                            xpp.next();
                            buffer.append(xpp.getText());//NUTR_CONT2 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("//"); //줄바꿈 문자 추가
                           // buffer.append("****");//줄바꿈 문자 추가



                        } else if (tag.equals("NUTR_CONT3")) {
                            buffer.append("단백질 : "); //buffer4
                            xpp.next();
                            buffer.append(xpp.getText());//NUTR_CONT3 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("//"); //줄바꿈 문자 추가
                          // buffer.append("*****");//줄바꿈 문자 추가



                        } else if (tag.equals("NUTR_CONT4")) {
                            buffer.append("지방 : "); //buffer5
                            xpp.next();
                            buffer.append(xpp.getText());//NUTR_CONT4 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("//"); //줄바꿈 문자 추가
                            //buffer.append("******");//줄바꿈 문자 추가



                        }  else if (tag.equals("NUTR_CONT5")) {
                            buffer.append("당류 : "); //buffer6
                            xpp.next();
                            buffer.append(xpp.getText());//NUTR_CONT5 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("//"); //줄바꿈 문자 추가
                            //buffer.append("*******");//줄바꿈 문자 추가



                        } else if (tag.equals("NUTR_CONT6")) {
                            buffer.append("나트륨 : "); //buffer7
                            xpp.next();
                            buffer.append(xpp.getText());//NUTR_CONT6 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("//"); //줄바꿈 문자 추가
                            //buffer.append("********");//줄바꿈 문자 추가


                        }

                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName(); //테그 이름 얻어오기

                        if (tag.equals("item")) {
                            //buffer.append("#");// 첫번째 검색결과종료..줄바꿈
                        }
                        break;
                }
                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //buffer.append("검색 완료\n");
        return buffer.toString();//StringBuffer 문자열 객체 반환
        //return buffer1.toString();

    }



}
