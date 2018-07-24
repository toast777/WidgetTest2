package com.example.chuck.widgettest;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class TestWidget extends AppWidgetProvider {
    public static final String KEY_ITEM = "com.teamtreehouse.listwidget.KEY_ITEM";
//    public static final String TOAST_ACTION = "com.teamtreehouse.listwidget.TOAST_ACTION";
    public ArrayList<String> wList;

//    public TestWidget (ArrayList<String> wList){
//        this.wList = wList;
//    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        int[] realAppWidgetIds = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, TestWidget.class));
        for (int id : realAppWidgetIds) {
            if (wList == null) {
                wList = new ArrayList<>();
                wList.add("Treehouse");
                wList.add("Android");
            }
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.test_widget);
            Intent serviceIntent = new Intent(context, WidgetService.class);
            remoteViews.setRemoteAdapter(R.id.listView, serviceIntent);
            serviceIntent.putStringArrayListExtra("Test String", wList);

            int r = (int)(Math.random() * 0xff);
            int g = (int)(Math.random() * 0xff);
            int b = (int)(Math.random() * 0xff);
            int color = (0xff << 24) + (r << 16) + (g << 8) + b;
            remoteViews.setInt(R.id.frameLayout, "setBackgroundColor", color);

            Intent intent = new Intent(context, TestWidget.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, realAppWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            remoteViews.setPendingIntentTemplate(R.id.listView, pendingIntent);

            appWidgetManager.updateAppWidget(id, remoteViews);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

