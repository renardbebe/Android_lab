package com.renardbebe.ex7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ex7 extends AppCompatActivity {
    EditText pwd1;
    EditText pwd2;
    Button btn_ok;
    Button btn_clr;

    public static final String RREFERENCE_NAME = "SavePasswords";
    public static int MODE = MODE_PRIVATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex7);

        findViews();
        final SharedPreferences sharedPreferences = getSharedPreferences(RREFERENCE_NAME, MODE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final String str = sharedPreferences.getString("password","nothing");

        if(str == "nothing") {  // 第一次进入
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String password1 = pwd1.getText().toString();
                    String password2 = pwd2.getText().toString();
                    if(TextUtils.isEmpty(password1) || TextUtils.isEmpty(password2)) {
                        Toast.makeText(Ex7.this, "Passsword cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                    else if(!password1.equals(password2)) {
                        Toast.makeText(Ex7.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        editor.putString("password", password1);
                        editor.commit();
                        Intent intent = new Intent(Ex7.this, FileEditActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
        else {
            pwd1.setVisibility(pwd1.INVISIBLE);
            pwd2.setHint("Password");
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String password2 = pwd2.getText().toString();
                    if(TextUtils.isEmpty(password2)){
                        Toast.makeText(Ex7.this, "Passsword cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                    else if(!password2.equals(str)){
                        Toast.makeText(Ex7.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                    }
                    else if(password2.equals(str)){
                        Intent intent = new Intent(Ex7.this, FileEditActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
        btn_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd1.setText("");
                pwd2.setText("");
            }
        });
    }

    public void findViews() {
        pwd1 = (EditText) findViewById(R.id.password1);
        pwd2 = (EditText) findViewById(R.id.password2);
        btn_ok = (Button) findViewById(R.id.ok);
        btn_clr = (Button) findViewById(R.id.clear);
    }
}
