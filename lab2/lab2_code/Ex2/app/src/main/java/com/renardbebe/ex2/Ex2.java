package com.renardbebe.ex2;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Ex2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2);

        // 点击图像部分
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final String [] items = {"拍摄", "从相册选择"};
        alertDialog.setTitle("上传头像").setItems(items,
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"您选择了"+items[i], Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"您选择了[取消]", Toast.LENGTH_SHORT).show();
                    }
                }).create();

        ImageView logo = (ImageView) findViewById(R.id.sysu);
        if(logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.show();
                }
            });
        }

        // 选择radiobutton部分
        final RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.buttonGroup);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton r1 = (RadioButton) findViewById(R.id.student);
                RadioButton r2 = (RadioButton) findViewById(R.id.teacher);
                if (checkedId == r1.getId()) {
                    Snackbar.make(r1, "您选择了学生", Snackbar.LENGTH_SHORT)
                            .setAction("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(getApplicationContext(), "Snackbar 的确定按钮被点击了", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                } else if (checkedId == r2.getId()) {
                    Snackbar.make(r1, "您选择了教职工", Snackbar.LENGTH_SHORT)
                            .setAction("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(getApplicationContext(), "Snackbar 的确定按钮被点击了", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                }
            }
        });

        // 登录部分
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputLayout user = (TextInputLayout) findViewById(R.id.user);
                TextInputLayout key = (TextInputLayout) findViewById(R.id.key);
                EditText username = user.getEditText();
                EditText password = key.getEditText();
                String s1 = "123456";
                String s2 = "6666";
                String s3 = username.getText().toString();
                String s4 = password.getText().toString();
                user.setErrorEnabled(false);
                key.setErrorEnabled(false);
                if (TextUtils.isEmpty(s3)) {
                    user.setErrorEnabled(true);
                    user.setError("学号不能为空");
                } else if (TextUtils.isEmpty(s4)) {
                    key.setErrorEnabled(true);
                    key.setError("密码不能为空");
                } else if (s1.equals(s3) && s2.equals(s4)) {
                    user.setErrorEnabled(false);
                    key.setErrorEnabled(false);
                    Snackbar.make(user, "登录成功", Snackbar.LENGTH_SHORT)
                            .setAction("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(getApplicationContext(), "Snackbar 的确定按钮被点击了", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                } else {
                    user.setErrorEnabled(false);
                    key.setErrorEnabled(false);
                    Snackbar.make(key, "学号或密码错误", Snackbar.LENGTH_SHORT)
                            .setAction("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(getApplicationContext(), "Snackbar 的确定按钮被点击了", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                }
            }
        });

        // 注册部分
        ImageView img = (ImageView) findViewById(R.id.sysu);
        final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton r1 = (RadioButton) findViewById(R.id.student);
                RadioButton r2 = (RadioButton) findViewById(R.id.teacher);
                if (r1.isChecked())
                    Snackbar.make(r1, "学生注册功能尚未启用", Snackbar.LENGTH_SHORT)
                            .setAction("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(getApplicationContext(), "Snackbar 的确定按钮被点击了", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                if (r2.isChecked()) {
                    Toast.makeText(getApplicationContext(), "教职工注册功能尚未启用", Toast.LENGTH_SHORT).show();

                    alertDialog2.setTitle("捉迷藏....")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setSingleChoiceItems(new String[]{"猜", "猜", "我", "在", "哪"}, 0,
                                    new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            if (which == 3)
                                                Toast.makeText(getApplicationContext(), "Bingo! 好棒棒", Toast.LENGTH_SHORT).show();
                                            else
                                                Toast.makeText(getApplicationContext(), "猜错啦 >w<", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                            )
                            .setNegativeButton("取消", null)
                            .show();
                }
            }
        });
    }
}
