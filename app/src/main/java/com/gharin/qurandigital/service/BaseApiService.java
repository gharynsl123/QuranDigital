package com.gharin.qurandigital.service;

import com.gharin.qurandigital.detail.ResponseDetail;
import com.gharin.qurandigital.model.ResponseList;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BaseApiService {

    @GET("data.json?print=pretty")
    Call<List<ResponseList>> getListSurah();

    @GET("surat/{nomor}.json?print=pretty")
    Call<List<ResponseDetail>> getDetailSurah(@Path("nomor") String nomor);

}
