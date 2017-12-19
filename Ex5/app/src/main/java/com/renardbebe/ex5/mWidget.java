package com.renardbebe.ex5;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class mWidget extends AppWidgetProvider {
    private static final String STATICACTION = "com.renardbebe.ex5.MyStaticFilter";
    private static final String DYNAMICACTION = "com.renardbebe.ex5.MyDynamicFilter";

    private static RemoteViews remoteView;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        if(remoteView == null) {
            remoteView = new RemoteViews(context.getPackageName(), R.layout.m_widget);  //实例化RemoteView,其对应相应的Widget布局
            Intent intent = new Intent(context, Ex5.class);
            PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            remoteView.setOnClickPendingIntent(R.id.appwidget_text, pi);  //设置点击事件
            remoteView.setTextViewText(R.id.appwidget_text, "当前没有任何信息");
            remoteView.setImageViewResource(R.id.appwidget_image, R.mipmap.car);

            ComponentName me = new ComponentName(context, Ex5.class);
            appWidgetManager.updateAppWidget(me,remoteView);
        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteView);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
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

    @Override
    public void onReceive(Context context,Intent intent) {
        super.onReceive(context, intent);
        if(intent.getAction().equals(STATICACTION)) {
            // 跳转到商品详情界面
            Bundle bundle = intent.getExtras();
            String Name = bundle.getString("Name");
            String Price = bundle.getString("Price");

            int i = com.renardbebe.ex5.R.mipmap.p1;

            if (Name.equals("Enchated Forest")) i = com.renardbebe.ex5.R.mipmap.p1;
            else if (Name.equals("Arla Milk")) i = com.renardbebe.ex5.R.mipmap.p2;
            else if (Name.equals("Devondale Milk")) i = com.renardbebe.ex5.R.mipmap.p3;
            else if (Name.equals("Kindle Oasis")) i = com.renardbebe.ex5.R.mipmap.p4;
            else if (Name.equals("waitrose 早餐麦片")) i = com.renardbebe.ex5.R.mipmap.p5;
            else if (Name.equals("Mcvitie's 饼干")) i = com.renardbebe.ex5.R.mipmap.p6;
            else if (Name.equals("Ferrero Rocher")) i = com.renardbebe.ex5.R.mipmap.p7;
            else if (Name.equals("Maltesers")) i = com.renardbebe.ex5.R.mipmap.p8;
            else if (Name.equals("Lindt")) i = com.renardbebe.ex5.R.mipmap.p9;
            else if (Name.equals("Borggreve")) i = com.renardbebe.ex5.R.mipmap.p10;

            Intent mIntent = new Intent(context, detail.class);
            mIntent.putExtras(bundle);
            PendingIntent mPendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            remoteView.setOnClickPendingIntent(R.id.appwidget_text, mPendingIntent);
            remoteView.setImageViewResource(R.id.appwidget_image, i);
            remoteView.setTextViewText(R.id.appwidget_text, Name+"仅售"+Price+"!");

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName me = new ComponentName(context, mWidget.class);
            appWidgetManager.updateAppWidget(me, remoteView);
        }
        else if(intent.getAction().equals(DYNAMICACTION)){
            // 跳转到购物车界面
            Bundle bundle = intent.getExtras();
            Data data = (Data) bundle.getSerializable("data");
            String Name = data.getName();

            int i = com.renardbebe.ex5.R.mipmap.p1;

            if (Name.equals("Enchated Forest")) i = com.renardbebe.ex5.R.mipmap.p1;
            else if (Name.equals("Arla Milk")) i = com.renardbebe.ex5.R.mipmap.p2;
            else if (Name.equals("Devondale Milk")) i = com.renardbebe.ex5.R.mipmap.p3;
            else if (Name.equals("Kindle Oasis")) i = com.renardbebe.ex5.R.mipmap.p4;
            else if (Name.equals("waitrose 早餐麦片")) i = com.renardbebe.ex5.R.mipmap.p5;
            else if (Name.equals("Mcvitie's 饼干")) i = com.renardbebe.ex5.R.mipmap.p6;
            else if (Name.equals("Ferrero Rocher")) i = com.renardbebe.ex5.R.mipmap.p7;
            else if (Name.equals("Maltesers")) i = com.renardbebe.ex5.R.mipmap.p8;
            else if (Name.equals("Lindt")) i = com.renardbebe.ex5.R.mipmap.p9;
            else if (Name.equals("Borggreve")) i = com.renardbebe.ex5.R.mipmap.p10;

            Intent mIntent = new Intent(context, Ex5.class);
            mIntent.putExtras(bundle);
            PendingIntent mPendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            remoteView.setOnClickPendingIntent(R.id.appwidget_text, mPendingIntent);
            remoteView.setImageViewResource(R.id.appwidget_image, i);
            remoteView.setTextViewText(R.id.appwidget_text, Name+"已添加到购物车");

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName me = new ComponentName(context, mWidget.class);
            appWidgetManager.updateAppWidget(me, remoteView);
        }
    }
}

