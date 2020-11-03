package com.nirodha.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.security.AccessControlContext;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    TextView min,sec,dec,id1;
    Button b;
    Handler handler;
    int x=0;
    int m=0;
    int s=0;
    int d=0;
    int count=0;
    boolean xx=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        min = findViewById(R.id.min);
        sec = findViewById(R.id.sec);
        dec = findViewById(R.id.dec);
        id1= findViewById(R.id.id1);
        b= findViewById(R.id.b);

//        handler= new Handler(){
//            @Override
//            public void handleMessage(Message msg){
//
//
//
//            }
//        };

    }


    protected void viewList() {


        databaseHelper td = new databaseHelper(this);
        SQLiteDatabase db = td.getReadableDatabase();
        String sql = "SELECT _id,minute,second,decimal FROM table1";


        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();



        ListView lv = findViewById(R.id.list4);

        //hour = cursor.getString(4);

        String cc= String.valueOf(count);
        Toast.makeText(this, cc+"", Toast.LENGTH_SHORT).show();
        //id1.setText(cc);


        int layout = R.layout.record;
        String[] columns = {"minute", "second", "decimal"};
        int[] labels = {R.id.t1, R.id.t2, R.id.t3}; // hadagatta custom layout eke lable thune id thuna
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, layout, cursor, columns, labels);
        lv.setAdapter(adapter);

    }

    public void start(View view)
    {
        xx=true;
        Runnable r = new Runnable() {
            @Override
            public void run() {


                while (xx==true){
                    try {
                        Thread.sleep(10); //thathparayal sleep karnna



                        final int mm=m;
                        final int ss=s;
                        final int dd=d;
                        Runnable r2= new Runnable() {
                            @Override
                            public void run() {

                                dec.setText(d+"");
                                d=d+1;
                                if(d==99){
                                    s=s+1;
                                    d=0;
                                    sec.setText(s+"");
                                }

                                if(s==59)
                                {
                                    m=m+1;
                                    s=0;
                                     min.setText(m+"");
                                }
                                //handler.sendEmptyMessage(0);
                            }
                        };
                        runOnUiThread(r2);

                    }

                    catch (Exception e){

                    }
                }
            }
        };

        Thread t = new Thread(r);
        t.start();






    }


    public void lap(View view)
    {
        String min =String.valueOf(m);
        String sec =String.valueOf(s);
        String dec =String.valueOf(d);
        databaseHelper td2 = new databaseHelper(this);
        SQLiteDatabase db =td2.getWritableDatabase();

        String sql ="INSERT INTO table1 (minute,second,decimal) VALUES ('"+min+"','"+sec+"','"+dec+"')";
        db.execSQL(sql);

        count +=1;
        viewList();

    }

    public void reset(View view)
    {
        min.setText("00");
        sec.setText("00");
        dec.setText("00");

        count=0;

        databaseHelper td3 = new databaseHelper(this);
        SQLiteDatabase db =td3.getWritableDatabase();

        String sql ="DELETE FROM table1";
        db.execSQL(sql);

        ListView listView = findViewById(R.id.list4);
        listView.setAdapter(null);


    }

    public void stop(View view)
    {

        xx=false;

    }


    }


