package com.gharin.qurandigital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.gharin.qurandigital.model.ResponseList;
import com.gharin.qurandigital.service.ApiClient;
import com.gharin.qurandigital.service.BaseApiService;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvMain;
    AdapterSurah adapterSurah;
    List<ResponseList> list;
    BaseApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMain = findViewById(R.id.rvMain);
        list = new ArrayList<>();
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        apiService = ApiClient.getShurah();
        getDataList();
    }

    private void getDataList() {
        final Call<List<ResponseList>> listCall = apiService.getListSurah();
        listCall.enqueue(new Callback<List<ResponseList>>() {
            @Override
            public void onResponse(Call<List<ResponseList>> call, Response<List<ResponseList>> response) {
                    if (response.isSuccessful()){
                        list = response.body();
                        adapterSurah = new AdapterSurah(list);
                        rvMain.setAdapter(adapterSurah);
                    }else {
                        Toast.makeText(MainActivity.this, "redpone Sever gagal!!", Toast.LENGTH_SHORT).show();
                    }
                }

            @Override
            public void onFailure(Call<List<ResponseList>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Bad internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
