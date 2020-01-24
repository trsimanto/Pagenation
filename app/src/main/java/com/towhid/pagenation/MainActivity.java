package com.towhid.pagenation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;

import com.towhid.pagenation.network.api.retrofit.RetrofitClient;
import com.towhid.pagenation.network.model.Info;
import com.towhid.pagenation.network.model.PageInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler;
    List<Name> names;
    LinearLayoutManager mLayoutManager;
    boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    RecyclerViewAdapter_name recyclerViewAdapter_name;
    int count = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.recycler);
        names = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(this);
        recyclerViewAdapter_name = new RecyclerViewAdapter_name(MainActivity.this, names);
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(recyclerViewAdapter_name);

        Call<PageInfo> call = RetrofitClient.getInstance().getApi().pageInfo_(1, 20);
        call.enqueue(new Callback<PageInfo>() {
            @Override
            public void onResponse(Call<PageInfo> call, Response<PageInfo> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    int len = response.body().getLength();
                    Log.d("oo", "len: " + len);
                    List<Info> info = response.body().getInfo();
                    for (Info info1 : info) {
                        names.add(new Name(info1.getName()));
                    }
                    recyclerViewAdapter_name = new RecyclerViewAdapter_name(MainActivity.this, names);
                    recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recycler.setAdapter(recyclerViewAdapter_name);
                } else {
                    Log.d("oo", "code: " + response.code());

                }
            }

            @Override
            public void onFailure(Call<PageInfo> call, Throwable t) {
                Log.d("oo", "error: " + t.getMessage());
            }
        });

        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = recycler.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        Log.v("...", "oo");
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            Log.v("...", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                            Call<PageInfo> call = RetrofitClient.getInstance().getApi().pageInfo_(count, count+20);
                            call.enqueue(new Callback<PageInfo>() {
                                @Override
                                public void onResponse(Call<PageInfo> call, Response<PageInfo> response) {
                                    if (response.isSuccessful()) {
                                        assert response.body() != null;
                                        count+=20;
                                        loading=true;
                                        int len = response.body().getLength();
                                        Log.d("oo", "len: " + len);
                                        List<Info> info = response.body().getInfo();
                                        for (Info info1 : info) {
                                            names.add(new Name(info1.getName()));
                                        }
                                        recyclerViewAdapter_name.notifyDataSetChanged();
                                    } else {
                                        Log.d("oo", "code: " + response.code());

                                    }
                                }

                                @Override
                                public void onFailure(Call<PageInfo> call, Throwable t) {
                                    Log.d("oo", "error: " + t.getMessage());
                                }
                            });


                        }
                    }

                }
            }
        });


    }
}
