package com.example.myapplication;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class allergy extends AppCompatActivity {

    ListView listView;
    listadapter2 adapter;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.allergy);
        Intent intent = getIntent();

        adapter = new listadapter2();
        listView=findViewById(R.id.listview);
        listView.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] data = new String[1];

                switch (v.getId()){
                    case R.id.button:
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                data[0] =getXmlData();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Toast toast = Toast.makeText(getApplicationContext(), data[0],Toast.LENGTH_SHORT);
                                        //toast.show();
                                        String[] array = data[0].split("#");

                                        for(int i=0;i<array.length/3;i++) {
                                            adapter.addItem(array[i*3],array[i*3+1],array[i*3+2]);
                                        }
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }).start();
                        break;
                }

            }
        });


    }

    public void btnStart2(View v) {//리스트뷰 초기화 부분
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
        String queryUrl = "http://apis.data.go.kr/B553748/CertImgListService/getCertImgListService?serviceKey=" + key + "&prdlstNm=" + location + "&pageNo=1&numOfRows=5";

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
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();//테그 이름 얻어오기

                        if (tag.equals("item")) ;// 제품명으로 검색하기

                        else if (tag.equals("prdlstNm")) {
                            buffer.append("식품이름 : "); //제품명
                            xpp.next();
                            buffer.append(xpp.getText());// DESC_KOR 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("#"); //줄바꿈 문자 추가

                        }  else if (tag.equals("rawmtrl")) {
                            buffer.append("원재료 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//NUTR_CONT1 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("#"); //줄바꿈 문자 추가

                        } else if (tag.equals("allergy")) {//알레르기 유발 물질
                            buffer.append("알레르기 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//SERVING_WT 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("#"); //줄바꿈 문자 추가

                        }

                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName(); //테그 이름 얻어오기

                        if (tag.equals("item")) // 첫번째 검색결과종료..줄바꿈
                        break;
                }

                eventType = xpp.next();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();//StringBuffer 문자열 객체 반환
    }
}
