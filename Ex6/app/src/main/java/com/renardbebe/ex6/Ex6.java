package com.renardbebe.ex6;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Ex6 extends AppCompatActivity implements View.OnClickListener {
    private ImageView rotate_image;
    private TextView text;
    private SeekBar seek_bar;
    private TextView left;
    private TextView right;
    private Button playBtn;
    private Button stopBtn;
    private Button quitBtn;

    private IBinder mBinder;
    private ObjectAnimator animator;
    private ServiceConnection sc;
    private java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("mm:ss");

    int first, status, changePos, flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex6);

        // 动态获取权限
        Permission.verifyStoragePermissions(this);

        rotate_image = (ImageView) findViewById(R.id.image);
        text = (TextView) findViewById(R.id.text);
        seek_bar = (SeekBar) findViewById(R.id.seek_bar);
        left = (TextView) findViewById(R.id.left);
        right = (TextView) findViewById(R.id.right);
        playBtn = (Button) findViewById(R.id.play);
        stopBtn = (Button) findViewById(R.id.stop);
        quitBtn = (Button) findViewById(R.id.quit);

        bindButton();
        sc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder service) {
                Log.d("service", "connected");
                mBinder = service;

                try {
                    Parcel reply = Parcel.obtain();
                    mBinder.transact(106, null, reply, 0);
                    int maxn = reply.readInt();
                    seek_bar.setMax(maxn);
                    right.setText(time.format(maxn));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mBinder = null;
                sc = null;
            }
        };
        bind_service();
        changePos = 0;
        // 更改进度条
        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                left.setText(time.format(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                changePos = 1;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                try {
                    Parcel data = Parcel.obtain();
                    data.writeInt(seek_bar.getProgress());
                    mBinder.transact(105, data, null, 0);
                    changePos = 0;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 123:
                        // 更新UI内容
                        try {
                            if(changePos == 0) {
                                Parcel reply = Parcel.obtain();
                                mBinder.transact(104, null, reply, 0);
                                int pos = reply.readInt();
                                seek_bar.setProgress(pos);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        };
        // 界面更新
        Thread mThread = new Thread() {
            @Override
            public void run () {
                while(true) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(sc != null)
                        mHandler.obtainMessage(123).sendToTarget();
                }
            }
        };
        mThread.start();

        // 设置图片旋转
        setAnimator();
        first = 1;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play : {  // 播放
                try {
                    int code = 101;
                    Parcel reply = Parcel.obtain();  // 获得一个新的parcel
                    mBinder.transact(code, null, reply, 0);
                    status = reply.readInt();
                    refreshView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.stop : {  // 停止
                try {
                    int code = 102;
                    Parcel reply = Parcel.obtain();
                    mBinder.transact(code, null, reply, 0);
                    status = reply.readInt();
                    refreshView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.quit : {  // 退出
                try {
                    mBinder.transact(103, null, null, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                unbindService(sc);
                sc = null;
                try {
                    Ex6.this.finish();
                    System.exit(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    // 请求权限回调处理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED))
            onDestroy();
    }

    private void bindButton() {
        playBtn.setOnClickListener((View.OnClickListener) this);
        stopBtn.setOnClickListener((View.OnClickListener) this);
        quitBtn.setOnClickListener((View.OnClickListener) this);
    }

    private void bind_service() {
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        bindService(intent, sc, Context.BIND_AUTO_CREATE);
    }

    public void setAnimator(){
        animator = ObjectAnimator.ofFloat(rotate_image, "rotation", 0, 360);
        animator.setDuration(10000);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());  // 匀速转动
    }

    public void refreshView() {
        switch (status) {
            case 0 : {  // start
                text.setText("Playing");
                playBtn.setText("PLAY");
                if(first == 1) {
                    animator.start();
                    seek_bar.setProgress(0);
                    first = 0;
                }
                else {
                    if(flag != 1) animator.resume();
                    else {
                        animator.start();
                        flag = 0;
                    }
                }
                break;
            }
            case 1 : {  // pause
                text.setText("Paused");
                playBtn.setText("PAUSED");
                animator.pause();
                break;
            }
            case 2 : {  // stop
                text.setText("Stopped");
                playBtn.setText("PLAY");
                seek_bar.setProgress(0);
                animator.end();
                flag = 1;
                break;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
