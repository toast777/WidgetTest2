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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bWidget = findViewById(R.id.widget_button);

        sharedPreferences = getSharedPreferences("WidgetList",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("Name1","Treehouse");
        editor.putString("Name2","Georgia");
        editor.apply();


        bWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

                editor.putString("Name1","Two");
                editor.putString("Name2","Three");
                editor.apply();

//
//                int widgetIDs[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), TestWidget.class));
//
//                for (int id : widgetIDs)
//                    AppWidgetManager.getInstance(getApplication()).notifyAppWidgetViewDataChanged(id, R.id.frameLayout);
//
                ComponentName thisWidget = new ComponentName(context, TestWidget.class);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.frameLayout);
            }

        });
    }
}
