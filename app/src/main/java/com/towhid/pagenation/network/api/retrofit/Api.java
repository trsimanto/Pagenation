package com.towhid.pagenation.network.api.retrofit;



import com.towhid.pagenation.network.model.PageInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET(".")
    Call<PageInfo> pageInfo_(@Query("s") int s,@Query("l") int l);



}
