package com.example.a35;

import android.content.Context;
import android.content.SharedPreferences;

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
    private SharedPreferences sp;

    public SaveData(SharedPreferences sp) {
        this.sp = sp;
    }

    public Boolean saveLoginInfo(String username, String password, Boolean autosave) {
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("saveflag", autosave);
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
        return true;
    }

    public String[] getLoginInfo() {
        String result[] = new String[]{"", "", ""};
        result[0] = String.valueOf(sp.getBoolean("saveflag", false));
        result[1] = sp.getString("username", "");
        result[2] = sp.getString("password", "");
        return result;
    }
}
