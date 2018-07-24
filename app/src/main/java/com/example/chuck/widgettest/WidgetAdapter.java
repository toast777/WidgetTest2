package com.example.chuck.widgettest;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WidgetAdapter implements RemoteViewsService.RemoteViewsFactory {
    Context context;
    private ArrayList<String> list;

    public WidgetAdapter(Context context, ArrayList<String> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

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

        Intent intent = new Intent();
        intent.putExtra(TestWidget.KEY_ITEM, list.get(position));
        remoteViews.setOnClickFillInIntent(R.id.list_item, intent);

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
