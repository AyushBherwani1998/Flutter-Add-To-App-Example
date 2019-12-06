package com.example.ayush.qapp;

import android.annotation.SuppressLint;


import android.content.Intent;


import android.content.SharedPreferences;

import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.widget.FrameLayout;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;


import androidx.appcompat.app.AppCompatActivity;
import io.flutter.facade.Flutter;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;

public class Favorite_Quotes extends AppCompatActivity {

    @Override
    protected void onStart() {
        loadData();
        super.onStart();
    }

    String Channel = "quotesHub.com/Quotes";
    public static LinkedList<String> FavoriteQuotes;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite__quotes);

        FlutterView flutterView = Flutter.createView(Favorite_Quotes.this, getLifecycle(), "/");
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        addContentView(flutterView, layoutParams);
        ArrayList<String> arrayList = new ArrayList<>(FavoriteQuotes);
        Log.i("ArrayList", arrayList.toString());
        new Handler().postDelayed(() -> new MethodChannel(flutterView, Channel)
                .invokeMethod("getQuotes", arrayList), 5000);

        new MethodChannel(flutterView, Channel).setMethodCallHandler((call, result) -> {
            if (call.method.equals("removeQuote")) {
                String quote = call.argument("quote");
                FavoriteQuotes.remove(quote);
                result.success(true);
            }

        });
    }


    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        ArrayList<String> arrayList = new ArrayList<String>(FavoriteQuotes);
        String json = gson.toJson(arrayList);
        editor.putString("favoriteQuotes", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        Settings.backgroundId = preferences.getInt("backgroundId", R.color.default_color);
        Settings.textSize = preferences.getInt("textSize", 14);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("favoriteQuotes", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> arrayList;
        arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            FavoriteQuotes = new LinkedList<>();
        } else {
            FavoriteQuotes = new LinkedList<>(arrayList);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }
}



