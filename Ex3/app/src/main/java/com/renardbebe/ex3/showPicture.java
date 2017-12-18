package com.renardbebe.ex3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by renardbebe on 2017/10/23.
 */

public class showPicture extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private ImageView imageView;
    private SeekBar seekBar;
    private TextView textView;
    private Matrix matrix = new Matrix();  // 矩阵类,用于图像旋转

    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);

        imageView = (ImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.textView);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);

        // 接收信息
        Intent intent = getIntent();
        Name = intent.getStringExtra("Name");
        Toast.makeText(this,Name,Toast.LENGTH_SHORT).show();

        if (Name.equals("Enchated Forest")) imageView.setImageResource(R.drawable.pic1);
        else if (Name.equals("Arla Milk")) imageView.setImageResource(R.drawable.pic2);
        else if (Name.equals("Devondale Milk")) imageView.setImageResource(R.drawable.pic3);
        else if (Name.equals("Kindle Oasis")) imageView.setImageResource(R.drawable.pic4);
        else if (Name.equals("waitrose 早餐麦片")) imageView.setImageResource(R.drawable.pic5);
        else if (Name.equals("Mcvitie's 饼干")) imageView.setImageResource(R.drawable.pic6);
        else if (Name.equals("Ferrero Rocher")) imageView.setImageResource(R.drawable.pic7);
        else if (Name.equals("Maltesers")) imageView.setImageResource(R.drawable.pic8);
        else if (Name.equals("Lindt")) imageView.setImageResource(R.drawable.pic9);
        else if (Name.equals("Borggreve")) imageView.setImageResource(R.drawable.pic10);

        // 创建一个空的展示矩阵
        DisplayMetrics displayMetrics = new DisplayMetrics();
        // 把当前管理的屏幕尺寸传递给displayMetrics
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // 初始化
        Bitmap bitmap = ((BitmapDrawable)(getResources().getDrawable(R.drawable.pic1))).getBitmap();

        if (Name.equals("Enchated Forest")) bitmap = ((BitmapDrawable)(getResources().getDrawable(R.drawable.pic1))).getBitmap();
        else if (Name.equals("Arla Milk")) bitmap = ((BitmapDrawable)(getResources().getDrawable(R.drawable.pic2))).getBitmap();
        else if (Name.equals("Devondale Milk")) bitmap = ((BitmapDrawable)(getResources().getDrawable(R.drawable.pic3))).getBitmap();
        else if (Name.equals("Kindle Oasis")) bitmap = ((BitmapDrawable)(getResources().getDrawable(R.drawable.pic4))).getBitmap();
        else if (Name.equals("waitrose 早餐麦片")) bitmap = ((BitmapDrawable)(getResources().getDrawable(R.drawable.pic5))).getBitmap();
        else if (Name.equals("Mcvitie's 饼干")) bitmap = ((BitmapDrawable)(getResources().getDrawable(R.drawable.pic6))).getBitmap();
        else if (Name.equals("Ferrero Rocher")) bitmap = ((BitmapDrawable)(getResources().getDrawable(R.drawable.pic7))).getBitmap();
        else if (Name.equals("Maltesers")) bitmap = ((BitmapDrawable)(getResources().getDrawable(R.drawable.pic8))).getBitmap();
        else if (Name.equals("Lindt")) bitmap = ((BitmapDrawable)(getResources().getDrawable(R.drawable.pic9))).getBitmap();
        else if (Name.equals("Borggreve")) bitmap = ((BitmapDrawable)(getResources().getDrawable(R.drawable.pic10))).getBitmap();

        // 旋转
        matrix.setRotate(progress);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        imageView.setImageBitmap(bitmap);
        textView.setText("图片顺时针旋转"+progress+"度");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
