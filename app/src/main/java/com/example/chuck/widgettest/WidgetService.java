package com.example.chuck.widgettest;

import android.content.Intent;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetAdapter(this.getApplicationContext());
    }

}
