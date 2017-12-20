package com.renardbebe.ex7;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by renardbebe on 2017/12/3.
 */

public class FileEditActivity extends AppCompatActivity {
    private Button btn_save;
    private Button btn_load;
    private Button btn_clr;
    private Button btn_delete;
    private EditText input;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_layout);

        findViews();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FILE_NAME = input.getText().toString().trim() + ".txt";
                try {
                    FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                    String words = content.getText().toString();
                    fileOutputStream.write(words.getBytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();

                    Toast.makeText(FileEditActivity.this, "Save successfully", Toast.LENGTH_SHORT).show();
                    Log.i("TAG", "Successfully saved file.");
                } catch (IOException ex) {
                    Log.e("TAG", "Fail to save file.");
                }
            }
        });
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FILE_NAME = input.getText().toString().trim() + ".txt";
                try{
                    content.setText("");
                    FileInputStream fileInputStream = openFileInput(FILE_NAME);
                    byte[] contents = new byte[fileInputStream.available()];
                    fileInputStream.read(contents);
                    String s1 = new String(contents,"utf-8");
                    content.setText(s1);
//                    fileInputStream.close();

                    Toast.makeText(FileEditActivity.this, "Load successfully", Toast.LENGTH_SHORT).show();
                    Log.i("TAG", "Successfully saved file.");
                } catch(IOException ex){
                    Toast.makeText(FileEditActivity.this, "Fail to load file", Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "Fail to read file");
                }
            }
        });
        btn_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                content.setText("");
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FILE_NAME = input.getText().toString().trim() + ".txt";
                try {
                    deleteFile(FILE_NAME);
                    Toast.makeText(FileEditActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "Successfully delete file.");
                } catch (Exception e) {
                    Toast.makeText(FileEditActivity.this, "Fail to delete file", Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "Fail to delete file");
                }
            }
        });
    }
    public void findViews() {
        btn_save = (Button) findViewById(R.id.save);
        btn_load = (Button) findViewById(R.id.load);
        btn_clr = (Button) findViewById(R.id.clear);
        btn_delete = (Button) findViewById(R.id.delete);
        input = (EditText) findViewById(R.id.input);
        content = (EditText) findViewById(R.id.content);
    }
}
