package com.autoads.app.retrofit;


import com.autoads.app.model.ResponseModelForSignUp;
import com.autoads.app.model.ResponseModelForUserLogin;
import com.autoads.app.model.ResponseModelForUserProfile;
import com.autoads.app.model.ResponseModelForVehReg;
import com.autoads.app.model.responseModelForRegisteredVeh.ResponseModelForRegisteredVehicle;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIInterface {


    @FormUrlEncoded
    @POST("services.php")
    Call<ResponseModelForVehReg> regVehicle(@Field("reg_veh") Integer regVehSetValue, @Field("user_id") Integer userId
            , @Field("veh_type") String vehType, @Field("veh_reg_no") String vehRegNo, @Field("veh_color") String vehColor
            , @Field("veh_model") String vehModel, @Field("veh_make") String vehMake, @Field("veh_make_year") String vehMakeYear);


    @FormUrlEncoded
    @POST("services.php")
    Call<ResponseModelForSignUp> doSignUp(@Field("sign_up_user") Integer regUserSetValue, @Field("fname") String fname
            , @Field("lname") String lname, @Field("email") String email, @Field("mobile") String mobile
            , @Field("password") String password, @Field("user_type") Integer userType);


    @FormUrlEncoded
    @POST("services.php")
    Call<ResponseModelForUserLogin> doLogin(@Field("login") Integer loginUserSetValue, @Field("mobile") String mobile
            , @Field("password") String password);

    @FormUrlEncoded
    @POST("services.php")
    Call<ResponseModelForUserProfile> getUser(@Field("get_user_profile") Integer getUserSetValue, @Field("mobile") String mobile);


    @FormUrlEncoded
    @POST("services.php")
    Call<ResponseModelForRegisteredVehicle> getRegisteredVehicle(@Field("get_reg_veh") Integer regVehSetValue, @Field("user_id") Integer userId);


}
