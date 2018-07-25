package com.example.chuck.widgettest;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    List<String> strings = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bWidget = findViewById(R.id.widget_button);

        strings.add("tree");
        strings.add("fall");

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        Gson gsonIngList = new Gson();
        String json = gsonIngList.toJson(strings);
        editor.putString("json1",json);
        editor.apply();

        bWidget.setOnClickListener(v -> {
            Context context = v.getContext();
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            if (strings != null) {
                strings.clear();
            } else {
                strings = new ArrayList<>();
            }
            strings.add("two");
            strings.add("three");

            Gson gsonIngList1 = new Gson();

            String json1 = gsonIngList1.toJson(strings);
            editor.putString("json1", json1);
            editor.apply();

            ComponentName thisWidget = new ComponentName(context, TestWidget.class);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.listView);
        });
    }
}
