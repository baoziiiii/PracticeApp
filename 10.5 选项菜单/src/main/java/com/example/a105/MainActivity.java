package com.example.a105;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=new MenuInflater(this);
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

   /* @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setTitle("这是一个菜单");
        adb.setMessage("菜单的内容");
        adb.setNeutralButton("这是一个中立菜单", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"这是一个中立菜单",Toast.LENGTH_SHORT);
            }
        });
        adb.show();
        return false;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings:
                Intent intent=new Intent(MainActivity.this,Settings.class);
                startActivity(intent);
                break;
            case R.id.regard:
                Intent intent1=new Intent(MainActivity.this,Regard.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
