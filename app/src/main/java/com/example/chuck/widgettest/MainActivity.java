package com.example.chuck.widgettest;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bWidget = findViewById(R.id.widget_button);

        bWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.test_widget);
                ComponentName thisWidget = new ComponentName(context, TestWidget.class);
               // remoteViews.setTextViewText(R.id.my_text_view, "myText" + System.currentTimeMillis());
                ArrayList<String> wList = new ArrayList<>();
                wList.add("Treehouse");
                wList.add("Georgia");
                Intent serviceIntent = new Intent(context, WidgetService.class);
                remoteViews.setRemoteAdapter(R.id.listView, serviceIntent);
                Intent intent = new Intent(context, TestWidget.class);

                serviceIntent.putStringArrayListExtra("Test String", wList);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
                remoteViews.setPendingIntentTemplate(R.id.listView, pendingIntent);
                appWidgetManager.updateAppWidget(thisWidget, remoteViews);
            }
        });
    }
}
