package com.example.a106;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerForContextMenu(findViewById(R.id.introduce));
        registerForContextMenu(findViewById(R.id.left_book));
        registerForContextMenu(findViewById(R.id.right_book));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater=new MenuInflater(this);
        switch(v.getId()) {
            case R.id.introduce: menuInflater.inflate(R.menu.introduce_menu, menu);break;
            case R.id.left_book:
            case R.id.right_book:menuInflater.inflate(R.menu.book_menu,menu);break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_copy:
                Toast.makeText(MainActivity.this,"已复制",Toast.LENGTH_SHORT).show();break;
            case R.id.menu_collect:
                Toast.makeText(MainActivity.this,"已收藏",Toast.LENGTH_SHORT).show();break;
            case R.id.menu_report:
                Toast.makeText(MainActivity.this,"已举报",Toast.LENGTH_SHORT).show();break;
            case R.id.menu_translate:
                Toast.makeText(MainActivity.this,"已翻译",Toast.LENGTH_SHORT).show();break;
            case R.id.menu_book_collect:
                Toast.makeText(MainActivity.this,"已收藏",Toast.LENGTH_SHORT).show();break;
            case R.id.menu_book_report:
                Toast.makeText(MainActivity.this,"已举报",Toast.LENGTH_SHORT).show()
                ;break;
        }
        return true;
    }
}
