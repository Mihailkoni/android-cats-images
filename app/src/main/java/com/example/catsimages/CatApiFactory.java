package com.example.catsimages;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatApiFactory {

    private static final String BASE_URL = "https://api.thecatapi.com/v1/";

    private static CatApiService apiService;

    public static CatApiService getApiService(){
        if(apiService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
            apiService = retrofit.create(CatApiService.class);
        }
        return apiService;
    }
}
