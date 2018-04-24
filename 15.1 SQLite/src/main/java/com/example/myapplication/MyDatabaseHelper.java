package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by MSI on 2018/2/5.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private int version;

    public MyDatabaseHelper(Context context,int version) {
        super(context,"info",null,version);
        this.context=context;
        this.version=version;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE info(_id integer primary key autoincrement,username varchar(20),password varchar(20) )");
        Toast.makeText(context,"表创建成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:db.execSQL("ALTER TABLE info ADD gender varchar(20) ");
            case 2:db.execSQL("ALTER TABLE info ADD phone varchar(20)");
        }
    }
}
