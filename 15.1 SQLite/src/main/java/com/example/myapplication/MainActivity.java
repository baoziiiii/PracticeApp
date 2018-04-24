package com.example.myapplication;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this, 3);

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText1 = findViewById(R.id.username);
        final EditText editText2 = findViewById(R.id.password);
        final EditText editText3 = findViewById(R.id.gender);
        final EditText editText4 = findViewById(R.id.phone);
        listView = findViewById(R.id.listview);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("删除")
                        .setMessage("是否删除该项？")
                        .setNegativeButton("否", null)
                        .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                                Toast.makeText(MainActivity.this, "ID=" + map.get("ID") + "已删除", Toast.LENGTH_SHORT).show();
                                delete((int) map.get("ID"));
                                query();
                            }
                        }).show();
                return true;
            }
        });
        query();


        Button bt_commit = findViewById(R.id.button);
        bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = editText1.getText().toString();
                String str2 = editText2.getText().toString();
                String str3 = editText3.getText().toString();
                String str4 = editText4.getText().toString();
                if (str1.isEmpty() && str2.isEmpty() && str3.isEmpty() && str4.isEmpty()) {
                    Toast.makeText(MainActivity.this, "不能提交空信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String, Object> entry = new HashMap<>();
                entry.put("username", editText1.getText().toString());
                entry.put("password", editText2.getText().toString());
                entry.put("gender", editText3.getText().toString());
                entry.put("phone", editText4.getText().toString());
                insert(entry);
                query();
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText4.setText("");
                editText2.performClick();
            }
        });

        Button bt_clearall=findViewById(R.id.button2);
        bt_clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("清空")
                        .setMessage("是否清空所有数据？")
                        .setNegativeButton("否", null)
                        .setPositiveButton("清空", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delete(-1);
                                query();
                                Toast.makeText(MainActivity.this, "已清空", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }

    private void insert(Map<String, Object> entry) {
        SQLiteDatabase database = myDatabaseHelper.getWritableDatabase();
        String sql = "INSERT INTO info (username,password,gender,phone) VALUES(?,?,?,?)";
        Object[] args = new Object[4];
        args[0] = entry.get("username");
        args[1] = entry.get("password");
        args[2] = entry.get("gender");
        args[3] = entry.get("phone");
        database.execSQL(sql, args);
        database.close();
    }

    private void delete(int ID) {
        SQLiteDatabase database = myDatabaseHelper.getWritableDatabase();
        String sql;
        if (ID == -1)
            sql = "DELETE FROM info";
        else
            sql = "DELETE FROM info WHERE _id = " + ID;
        database.execSQL(sql);
        database.close();
    }

    private void query() {
        SQLiteDatabase database = myDatabaseHelper.getWritableDatabase();
        String sql = "SELECT * FROM info";
        Cursor cursor = database.rawQuery(sql, null);

        List<Map<String, Object>> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("ID", cursor.getInt(0));
            entry.put("username", cursor.getString(1));
            entry.put("password", cursor.getString(2));
            entry.put("gender", cursor.getString(3));
            entry.put("phone", cursor.getString(4));
            list.add(entry);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.item, new String[]{"username", "password", "gender", "phone"}, new int[]{R.id.item_user, R.id.item_pass, R.id.item_gender, R.id.item_phone});
        listView.setAdapter(simpleAdapter);
        cursor.close();
        database.close();
    }
}
