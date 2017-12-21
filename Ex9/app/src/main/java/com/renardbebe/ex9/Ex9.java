package com.renardbebe.ex9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.renardbebe.ex9.factory.ServiceFactory;
import com.renardbebe.ex9.model.Github;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Ex9 extends AppCompatActivity {
    private EditText name;
    private Button btn1;
    private Button btn2;
    private ProgressBar load;

    protected RecyclerView mRecyclerView;
    protected List<Github> list = new ArrayList<>();
    protected HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex9);

        name = (EditText) findViewById(R.id.name);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        load = (ProgressBar) findViewById(R.id.load);

        load.setVisibility(View.INVISIBLE);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setVisibility(View.VISIBLE);
        homeAdapter = new HomeAdapter(list, Ex9.this);
        mRecyclerView.setAdapter(homeAdapter);

        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            /* 显示github详情 */
            public void onClick(final int position) {
                Intent intent = new Intent(Ex9.this, ReposActivity.class);
                intent.putExtra("Name", list.get(position).getName());
                startActivity(intent);
            }
            @Override
            /* 删除 */
            public void onLongClick(int position) {
                list.remove(position);
                homeAdapter.notifyDataSetChanged();
                Toast.makeText(Ex9.this, "成功删除!", Toast.LENGTH_SHORT).show();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {  // clear
            @Override
            public void onClick(View v) {
                name.setText("");
                list.clear();
                homeAdapter.notifyDataSetChanged();
                load.setVisibility(View.INVISIBLE);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {  // fetch
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                if(userName.isEmpty()) {
                    Toast.makeText(Ex9.this, "User name cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else {
                    getInfo(userName);
                    homeAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void getInfo(String name) {
        Retrofit retrofit = ServiceFactory.build();
        // 实例化接口
        ServiceFactory.GithubService github = retrofit.create(ServiceFactory.GithubService.class);
        Observable<Github> observable = github.getUser(name);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Github>() {
                    @Override
                    public void onSubscribe(Disposable d) {  // 提交请求
                        Log.i("Github","Subscribe");
                        load.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onNext(Github github) {  // 收到数据
                        list.add(github);
                    }

                    @Override
                    public void onComplete() {  // 请求结束
                        System.out.println("完成传输");
                        load.setVisibility(View.INVISIBLE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {  // 出现错误
                        Toast.makeText(Ex9.this, "请确认你搜索的用户存在", Toast.LENGTH_SHORT).show();
                        Log.e("Github",e.getMessage());
                        load.setVisibility(View.INVISIBLE);
                    }
                });
    }
}
