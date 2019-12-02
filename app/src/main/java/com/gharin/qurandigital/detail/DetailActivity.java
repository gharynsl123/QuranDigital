package com.gharin.qurandigital.detail;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gharin.qurandigital.R;
import com.gharin.qurandigital.service.ApiClient;
import com.gharin.qurandigital.service.BaseApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    RecyclerView rvDetail;
    BaseApiService apiService;
    List<ResponseDetail> data;
    AdapterDetail adapterDetail;
    String nomor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity);

        rvDetail = findViewById(R.id.rvDetail);
        apiService = ApiClient.getShurah();
        data = new ArrayList<>();
        rvDetail.setLayoutManager(new LinearLayoutManager(this));
        nomor = getIntent().getStringExtra("KEY");

        getDetailSurah(nomor);


    }

    private void getDetailSurah(String no) {
        final ProgressDialog loding = ProgressDialog.show(this, null, "loading..." ,false, false);
        final Call<List<ResponseDetail>> listCall = apiService.getDetailSurah(no);
        listCall.enqueue(new Callback<List<ResponseDetail>>() {
            @Override
            public void onResponse(Call<List<ResponseDetail>> call, Response<List<ResponseDetail>> response) {
                if (response.isSuccessful()){
                    loding.dismiss();
                    data = response.body();
                    adapterDetail = new AdapterDetail(data);
                    rvDetail.setAdapter(adapterDetail);
                }else {
                    loding.dismiss();
                    Toast.makeText(DetailActivity.this, "response failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseDetail>> call, Throwable t) {
                loding.dismiss();
                t.printStackTrace();
                Toast.makeText(DetailActivity.this, "Bad Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
