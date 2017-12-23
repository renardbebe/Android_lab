package com.renardbebe.ex8;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ex8 extends AppCompatActivity {
    myDB myDataBase = new myDB(Ex8.this);
    List<Map<String, String> > dataList = new ArrayList<Map<String,String> >();
    private Button btn_add;
    private ListView birth_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex8);

        btn_add = (Button) findViewById(R.id.add_item);
        birth_list = (ListView)findViewById(R.id.birth_list);

        updateListView();  // 更新列表

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ex8.this, AddActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        birth_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = dataList.get(position).get("name");
                String birth  = dataList.get(position).get("birth");
                String gift  = dataList.get(position).get("gift");
                updateDialog(name, birth, gift).show();
            }
        });
        birth_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String name = dataList.get(position).get("name");
                final String[] Argus2 = new String[] {name};
                AlertDialog.Builder builder = new AlertDialog.Builder(Ex8.this);
                builder.setTitle("是否删除？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                myDataBase.delete("name", Argus2);
                                updateListView();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
    }

    protected void setDataList(List<Map<String,String> > mDataList) {
        Map<String,String> myMap;
        Cursor cursor = myDataBase.getAll();
        while(cursor.moveToNext()) {
            myMap = new HashMap<String,String>();
            myMap.put("name", cursor.getString(cursor.getColumnIndex("name")));
            myMap.put("birth", cursor.getString(cursor.getColumnIndex("birth")));
            myMap.put("gift", cursor.getString(cursor.getColumnIndex("gift")));
            mDataList.add(myMap);
        }
    }

    protected void updateListView() {
        dataList.clear();
        setDataList(dataList);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, dataList, R.layout.item,
                new String[] {"name", "birth", "gift"},
                new int[] {R.id.i_name, R.id.i_birth, R.id.i_gift});
        birth_list.setAdapter(simpleAdapter);
    }

    protected String getPhone(String name) {
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        String Number = "";
        while(cursor.moveToNext()) {
            int isHas = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER )));
            String current_name = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
            if(current_name.equals(name) && isHas != 0) {
                String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor phone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
                while(phone.moveToNext()) {
                    Number += phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                                    + " ";
                }
            }
        }
        return Number;
    }

    protected AlertDialog updateDialog(final String name, final String birth, final String gift) {
        LayoutInflater factor = LayoutInflater.from(Ex8.this);
        View view_in = factor.inflate(R.layout.dialoglayout, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(Ex8.this);
        builder.setView(view_in);
        final AlertDialog alertDialog = builder.create();

        TextView textName = (TextView) view_in.findViewById(R.id.diag_name2);
        final EditText editBirth = (EditText) view_in.findViewById(R.id.diag_birth2);
        final EditText editGift = (EditText) view_in.findViewById(R.id.diag_gift2);
        TextView phone = (TextView) view_in.findViewById(R.id.diag_phone2);
        Button btn1 = (Button) view_in.findViewById(R.id.btn1);
        Button btn2 = (Button) view_in.findViewById(R.id.btn2);
        final String Agres1[] = new String[]{name};

        textName.setText(name);
        editBirth.setText(birth);
        editGift.setText(gift);
        phone.setText(getPhone(name));

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_birth = editBirth.getText().toString();
                String new_gift = editGift.getText().toString();
                myDataBase.update("name", Agres1, name, new_birth, new_gift);
                updateListView();
                alertDialog.cancel();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        return alertDialog;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0 && resultCode == 0 && data != null) {
            String name = data.getStringExtra("name");
            String birth = data.getStringExtra("birth");
            String gift = data.getStringExtra("gift");
            myDataBase.insert(name, birth, gift);  // 更新数据库
            updateListView();
        }
    }
}
