package com.example.a35;

import android.content.Context;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by MSI on 2018/1/29.
 */
public class SaveData {
    private Context context;
    public SaveData(Context context){
        this.context=context;
    }

    public Boolean saveLoginInfo(String username, String password, Boolean autosave){
        String str=autosave+"\r\n"+username+"\r\n"+password;
        try {
            FileOutputStream fos= context.openFileOutput("LoginInfo.txt",Context.MODE_PRIVATE);
            fos.write(str.getBytes());
            fos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public String[] getLoginInfo() {
        String result[] = new String[]{"", "", ""};
        try {
            Scanner in = new Scanner(context.openFileInput("LoginInfo.txt"));
            result[0] = in.nextLine();
            result[1] = in.nextLine();
            result[2] = in.nextLine();
        } catch (IOException e) {
        }
           return result;
    }
}
