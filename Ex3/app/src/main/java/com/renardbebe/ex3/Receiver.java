package com.renardbebe.ex3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

/**
 * Created by renardbebe on 2017/10/25.
 */

public class Receiver extends BroadcastReceiver {
    private static final String STATICACTION = "com.renardbebe.ex3.MyStaticFilter";
    private static final String DYNAMICACTION = "com.renardbebe.ex3.MyDynamicFilter";

    private static final String STATICACTION_1 = "com.renardbebe.ex3.MyStaticFilter_1";

    private int I=1;

    RemoteViews mRemoteViews = new RemoteViews("com.renardbebe.ex3", R.layout.remoteview_layout);

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(STATICACTION)) {
            Bundle bundle = intent.getExtras();
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(context);

            String Name = bundle.getString("Name");
            String Price = bundle.getString("Price");
            String Type = bundle.getString("Type");
            String Info = bundle.getString("Info");
            int i = R.mipmap.p1;

            if (Name.equals("Enchated Forest")) i = R.mipmap.p1;
            else if (Name.equals("Arla Milk")) i = R.mipmap.p2;
            else if (Name.equals("Devondale Milk")) i = R.mipmap.p3;
            else if (Name.equals("Kindle Oasis")) i = R.mipmap.p4;
            else if (Name.equals("waitrose 早餐麦片")) i = R.mipmap.p5;
            else if (Name.equals("Mcvitie's 饼干")) i = R.mipmap.p6;
            else if (Name.equals("Ferrero Rocher")) i = R.mipmap.p7;
            else if (Name.equals("Maltesers")) i = R.mipmap.p8;
            else if (Name.equals("Lindt")) i = R.mipmap.p9;
            else if (Name.equals("Borggreve")) i = R.mipmap.p10;

            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),i);

            /*builder.setContentTitle("新商品热卖")
                    .setContentText(Name+"仅售"+Price)
                    .setTicker("您有一条新消息")
                    .setLargeIcon(bitmap)
                    .setSmallIcon(i)
                    .setAutoCancel(true);*/

            mRemoteViews.setTextViewText(R.id.title, "新商品热卖");
            mRemoteViews.setImageViewBitmap(R.id.img, bitmap);
            mRemoteViews.setTextViewText(R.id.context, Name+"仅售"+Price);

            Intent mIntent = new Intent(context, detail.class);
            mIntent.putExtra("Name", Name);
            mIntent.putExtra("Price", Price);
            mIntent.putExtra("Type", Type);
            mIntent.putExtra("Info", Info);
            PendingIntent mPendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(mPendingIntent);

            builder.setSmallIcon(i)
                    .setContent(mRemoteViews);

            Notification notify = builder.build();
            manager.notify(0, notify);
        }
        else if(intent.getAction().equals(DYNAMICACTION)) {
            Bundle bundle = intent.getExtras();
            Data data = (Data) bundle.get("data");
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(context);

            String Name = data.getName();

            int i = R.mipmap.p1;

            if (Name.equals("Enchated Forest")) i = R.mipmap.p1;
            else if (Name.equals("Arla Milk")) i = R.mipmap.p2;
            else if (Name.equals("Devondale Milk")) i = R.mipmap.p3;
            else if (Name.equals("Kindle Oasis")) i = R.mipmap.p4;
            else if (Name.equals("waitrose 早餐麦片")) i = R.mipmap.p5;
            else if (Name.equals("Mcvitie's 饼干")) i = R.mipmap.p6;
            else if (Name.equals("Ferrero Rocher")) i = R.mipmap.p7;
            else if (Name.equals("Maltesers")) i = R.mipmap.p8;
            else if (Name.equals("Lindt")) i = R.mipmap.p9;
            else if (Name.equals("Borggreve")) i = R.mipmap.p10;

            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),i);

            /*builder.setContentTitle("马上下单")
                    .setContentText(Name+"已添加到购物车")
                    .setTicker("您有一条新消息")
                    .setLargeIcon(bitmap)
                    .setSmallIcon(i)
                    .setAutoCancel(true);*/

            mRemoteViews.setTextViewText(R.id.title, "马上下单");
            mRemoteViews.setImageViewBitmap(R.id.img, bitmap);
            mRemoteViews.setTextViewText(R.id.context, Name+"已添加到购物车");

            Intent mIntent = new Intent(context, Ex3.class);
            Bundle mbundle = new Bundle();
            mbundle.putSerializable("data", data);
            mIntent.putExtras(mbundle);
            PendingIntent mPendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);
            builder.setContentIntent(mPendingIntent);

            builder.setSmallIcon(i)
                    .setContent(mRemoteViews);

            Notification notify = builder.build();
            manager.notify(I++, notify);
        }
        else {
            Bundle bundle = intent.getExtras();
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(context);

            String Name = bundle.getString("Name");
            String Price = bundle.getString("Price");
            String Type = bundle.getString("Type");
            String Info = bundle.getString("Info");
            int i = R.mipmap.p1;

            if (Name.equals("Enchated Forest")) i = R.mipmap.p1;
            else if (Name.equals("Arla Milk")) i = R.mipmap.p2;
            else if (Name.equals("Devondale Milk")) i = R.mipmap.p3;
            else if (Name.equals("Kindle Oasis")) i = R.mipmap.p4;
            else if (Name.equals("waitrose 早餐麦片")) i = R.mipmap.p5;
            else if (Name.equals("Mcvitie's 饼干")) i = R.mipmap.p6;
            else if (Name.equals("Ferrero Rocher")) i = R.mipmap.p7;
            else if (Name.equals("Maltesers")) i = R.mipmap.p8;
            else if (Name.equals("Lindt")) i = R.mipmap.p9;
            else if (Name.equals("Borggreve")) i = R.mipmap.p10;

            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),i);

            mRemoteViews.setTextViewText(R.id.title, "旋转商品图片");
            mRemoteViews.setImageViewBitmap(R.id.img, bitmap);
            mRemoteViews.setTextViewText(R.id.context, Name);

            Intent mIntent = new Intent(context, showPicture.class);
            mIntent.putExtra("Name", Name);
            PendingIntent mPendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);
            builder.setContentIntent(mPendingIntent);

            builder.setSmallIcon(i)
                    .setContent(mRemoteViews);

            Notification notify = builder.build();
            manager.notify(0, notify);
        }
    }
}
