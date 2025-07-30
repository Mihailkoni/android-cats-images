package com.example.catsimages;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface CatApiService {

    @GET("images/search")
    Single<List<CatImage>> loadCatImage();
}
