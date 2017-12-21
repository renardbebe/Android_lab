package com.renardbebe.ex9;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.renardbebe.ex9.factory.ServiceFactory;
import com.renardbebe.ex9.model.Github;
import com.renardbebe.ex9.model.Repos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by renardbebe on 2017/12/12.
 */

public class ReposActivity extends AppCompatActivity {
    private ListView listView;
    private ProgressBar load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        listView = (ListView) findViewById(R.id.repos_list);
        load = (ProgressBar) findViewById(R.id.repos_load);
        load.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("Name");
        getRepos(userName);
    }

    public void getRepos(String name) {
        Retrofit retrofit = ServiceFactory.build();
        // 实例化接口
        ServiceFactory.GithubService github = retrofit.create(ServiceFactory.GithubService.class);
        Observable<List<Repos>> observable = github.getUserRepos(name);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repos>>() {
                    @Override
                    public void onSubscribe(Disposable d) {  // 提交请求
                        Log.i("Github","Subscribe");
                        load.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onNext(List<Repos> repos) {  // 收到数据
                        List<HashMap<String, String>> data = new ArrayList<>(repos.size());
                        HashMap<String,String> myMap;
                        for(Repos i : repos) {
                            myMap = new HashMap<String,String>();
                            myMap.put("title", i.getTitle());
                            myMap.put("language", i.getLanguage());
                            myMap.put("description", i.getDescription());
                            data.add(myMap);
                        }
                        SimpleAdapter simpleAdapter = new SimpleAdapter(ReposActivity.this, data, R.layout.item_info,
                                new String[] {"title", "language", "description"},
                                new int[] {R.id.title, R.id.language, R.id.description});
                        listView.setAdapter(simpleAdapter);
                    }

                    @Override
                    public void onComplete() {  // 请求结束
                        System.out.println("完成传输");
                        load.setVisibility(View.INVISIBLE);
                        listView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {  // 出现错误
                        Toast.makeText(ReposActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        Log.e("Github",e.getMessage());
                        load.setVisibility(View.INVISIBLE);
                        finish();
                    }
                });
    }
}
