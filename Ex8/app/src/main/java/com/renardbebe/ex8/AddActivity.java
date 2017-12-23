package com.renardbebe.ex8;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by renardbebe on 2017/12/6.
 */

public class AddActivity extends AppCompatActivity {
    myDB myDataBase = new myDB(AddActivity.this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_info);

        final EditText edit_name = (EditText)findViewById(R.id.edit1);
        final EditText edit_birth = (EditText)findViewById(R.id.edit2);
        final EditText edit_gift = (EditText)findViewById(R.id.edit3);
        final Button btn = (Button)findViewById(R.id.add);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edit_name.getText())){
                    Toast.makeText(AddActivity.this, "名字为空，请完善", Toast.LENGTH_SHORT).show();
                }
                else if(myDataBase.queryByName(edit_name.getText().toString())){
                    Toast.makeText(AddActivity.this, "名字重复啦，请检查", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent();
                    intent.putExtra("name",edit_name.getText().toString());
                    intent.putExtra("birth",edit_birth.getText().toString());
                    intent.putExtra("gift",edit_gift.getText().toString());
                    AddActivity.this.setResult(0,intent);  // 回传0
                    AddActivity.this.finish();
                }
            }
        });
    }
}
