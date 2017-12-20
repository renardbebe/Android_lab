package com.renardbebe.ex6;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by renardbebe on 2017/11/10.
 */

public class MusicService extends Service {
    public static MediaPlayer mp = new MediaPlayer();
    public final IBinder binder = new MyBinder();

    String s1 = Environment.getExternalStorageDirectory() + "/melt.mp3";
    String s2 = Environment.getExternalStorageDirectory() + "/Can't Stop Love.mp3";

    public class MyBinder extends Binder {
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 101 :
                    // 播放/暂停按钮
                    if(mp.isPlaying()) {
                        mp.pause();
                        reply.writeInt(1);
                    }
                    else {
                        mp.start();
                        reply.writeInt(0);
                    }
                    break;
                case 102 :
                    // 停止按钮
                    if(mp != null) {
                        mp.stop();
                        reply.writeInt(2);
                        try {
                            mp.prepare();
                            mp.seekTo(0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 103 :
                    // 退出按钮
                    if(mp != null) {
                        mp.stop();
                        mp.release();
                        mp = null;
                    }
                    break;
                case 104 :
                    // 界面刷新
                    reply.writeInt(mp.getCurrentPosition());
                    break;
                case 105 :
                    // 拖动进度条
                    mp.seekTo(data.readInt());
                    break;
                case 106 :
                    // 获取最大时长
                    reply.writeInt(mp.getDuration());
                    break;
            }
            return super.onTransact(code, data, reply, flags);
        }
    }

    public MusicService() {
        try {
            mp.setDataSource(s1);
            mp.prepare();
            mp.setLooping(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public void onDestroy() { super.onDestroy(); }
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
