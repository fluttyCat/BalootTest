package com.core.api

import com.core.dto.login.LoginResultDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginApi {

    @GET("User/UserLogin.ashx")
    fun activation(
        @Query("Username") username : String,
        @Query("Password") Password : String,
        @Query("NationalCode") NationalCode : String
    ) : Observable<LoginResultDto<String>>

    @GET("User/GetProjectList.ashx")
    fun appList(@Query("PromoterID") PromoterID:String): Observable<LoginResultDto<String>>


    @GET("FLA/FakeLocationAppList.ashx")
    fun getFakeAppList(): Observable<LoginResultDto<String>>

    @GET("FLA/HaveFLAReport.ashx")
    fun setUserHaveFla(
        @Query("UserID") userId : String,
        @Query("HaveFLA") count : String
    ): Observable<LoginResultDto<String>>

}