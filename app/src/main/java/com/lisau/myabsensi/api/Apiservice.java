package com.lisau.myabsensi.api;

import com.lisau.myabsensi.model.Users;
import com.lisau.myabsensi.model.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Apiservice {

    //TAMPIL SEMUA DATA
    @GET("absensi/")
    Call<Users> tampilDataAll(
            @Query("kunci") String kunci
    );

    //TAMPIL DATA DENGAN ID
//    @GET("uri")
//    Call<NamaKelas> getFunction(@Query("param") String param);

    //SIMPAN DATA
    @FormUrlEncoded
    @POST("absensi/")
    Call<Result> simpanDataCloud(
            @Query("kunci") String kunci,
            @Field("id") String id,
            @Field("nim") String nim,
            @Field("nama") String nama,
            @Field("tanggal") String tanggal,
            @Field("jam") String jam,
            @Field("status") String status,
            @Field("telat") String telat
    );

}
