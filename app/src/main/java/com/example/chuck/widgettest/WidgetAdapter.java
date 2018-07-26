package com.example.chuck.widgettest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class WidgetAdapter implements RemoteViewsService.RemoteViewsFactory {
    private Context context;
    private ArrayList<String> list;

    public WidgetAdapter(Context context) {
        this.context = context;
        this.list = getIngredients();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        list = getIngredients();
    }

    private ArrayList<String> getIngredients(){

        ArrayList<String> arrayList = null;
//        if (context != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            String listJson = sharedPreferences.getString("json1", "No Data");
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            arrayList = gson.fromJson(listJson, type);
//        }
        return arrayList;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.list_item);
        remoteViews.setTextViewText(R.id.textView, list.get(position));

        Log.i(TAG, list.get(position));

        return remoteViews;    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
