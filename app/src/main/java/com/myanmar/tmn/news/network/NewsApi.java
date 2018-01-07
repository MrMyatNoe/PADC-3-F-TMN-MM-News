package com.myanmar.tmn.news.network;

import com.myanmar.tmn.news.network.responses.GetNewsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by msi on 1/6/2018.
 */

public interface NewsApi {

    //return from Api
    //must wrap with call
    @FormUrlEncoded
    @POST("getMMNews.php")
    Call<GetNewsResponse> getNews(@Field("page") int page,
                                  @Field("access_token") String accessToken);
}
