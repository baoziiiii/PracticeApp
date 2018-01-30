package com.example.a39xml;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = (Button) findViewById(R.id.button);
        Button bt2 = (Button) findViewById(R.id.button2);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<SaveSMS> smslist = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    SaveSMS sms = new SaveSMS();
                    sms.from = "1000" + i;
                    sms.content = ""+i;
                    sms.date = "2018-01-30-13-12";
                    smslist.add(sms);
                }

                //创建序列器
                XmlSerializer serializer = Xml.newSerializer();
                try {
                    //别忘了设定输出位置
                    serializer.setOutput(openFileOutput("SMS.xml", MODE_PRIVATE), "utf-8");
                    //serializer.startDocument(String encoding,Boolean Standalone);
                    serializer.startDocument("utf-8", true);//文档独立
                    //<SMSList>
                    //serializer.startTag(String namespace,String tag); 没有约束文件，namespace=null
                    serializer.startTag(null, "SMSList");
                    for (SaveSMS i : smslist) {
                        serializer.startTag(null, "SMS");

                        serializer.startTag(null, "from");
                        serializer.text(i.from);
                        serializer.endTag(null, "from");

                        serializer.startTag(null, "content");
                        serializer.text(i.content);
                        serializer.endTag(null, "content");

                        serializer.startTag(null, "date");
                        serializer.text(i.date);
                        serializer.endTag(null, "date");

                        serializer.endTag(null, "SMS");
                    }
                    //</SMSList>
                    serializer.endTag(null, "SMSList");
                    serializer.endDocument();//别忘了关文档
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<SaveSMS> smslist = new ArrayList<>();
                XmlPullParser xpp=Xml.newPullParser();
                try {
                    xpp.setInput(openFileInput("SMS.xml"),"utf-8");

                    int type=xpp.getEventType();
                    SaveSMS sms=null;
                    while(type!=XmlPullParser.END_DOCUMENT){
                        switch(type){
                            case XmlPullParser.START_TAG:{
                                if("SMS".equals(xpp.getName()))
                                   sms=new SaveSMS();
                                else if("from".equals(xpp.getName()))
                                    sms.from=xpp.nextText();
                                else if("content".equals(xpp.getName()))
                                    sms.content=xpp.nextText();
                                else if("date".equals(xpp.getName()))
                                    sms.date=xpp.nextText();
                            }break;
                            case  XmlPullParser.END_TAG:{
                                if("SMS".equals(xpp.getName()))
                                    smslist.add(sms);
                            }break;
                            default:break;
                        }
                        type=xpp.next();
                    }

                    TextView tv2= (TextView) findViewById(R.id.textView2);
                    StringBuilder sb=new StringBuilder();
                    for (SaveSMS i: smslist) {
                        sb.append(i.toString());
                    }
                    tv2.setText(sb.toString());
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
