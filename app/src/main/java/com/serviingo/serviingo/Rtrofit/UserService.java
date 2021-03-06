package com.serviingo.serviingo.Rtrofit;


import com.serviingo.serviingo.modelrepo.Responsee.Profile_Repo;
import com.serviingo.serviingo.modelrepo.Responsee.VandorRepo;
import com.serviingo.serviingo.modelrepo.request.Changepassword_request;
import com.serviingo.serviingo.modelrepo.request.UpdateProfile_request;
import com.serviingo.serviingo.modelrepo.request.UserReg_Request;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @GET("states")
    Call<ResponseBody> GetState(
    );

   /* @GET("fetchcitybystate/")
    Call<ResponseBody> GetCity(
            @Query("state_id") String state_id
    );
*/

    @GET("cities/{state_id}")
    Call<ResponseBody> GetCity(
            @Path("state_id") String state_id
    );

    @GET("categories")
    Call<ResponseBody> Getcategory(
    );

    @FormUrlEncoded
    @POST("vendor/signup")
    Call<VandorRepo> doVandorReg(
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("state") String state,
            @Field("city") String city,
            @Field("category") String category,
            @Field("email") String email,
            @Field("mobile_number") String mobile_number,
            @Field("password") String password,
            @Field("confirm_password") String confirm_password
    );


    @POST("user/registration")
    Call<ResponseBody> DoUserReg(@Body UserReg_Request credential);

    @GET("user/logout")
    Call<ResponseBody> DoLogout(

    );

    @GET("myprofile")
    Call<Profile_Repo> Domyprofile(

    );

    @POST("update/password")
    Call<ResponseBody> Doupdatepassword(@Body Changepassword_request credential);

    @PUT("update/profile")
    Call<Profile_Repo> DoMyprofileUpdate(@Body UpdateProfile_request credential);

    /*  //getcategories
      @GET("getcategories")
      Call<List<CategoryListModel>> getcategories();

  */

/*
    @FormUrlEncoded
    @POST("login/generate_otp.php")
    Call<ResponseBody> Generate_otp(
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("login/validate_user.php")
    Call<ResponseBody> validate_user_bynumber(
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("login/validate_user.php")
    Call<ResponseBody> validate_user_byEmail(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("registration/registration.php")
    Call<ResponseBody> registration(
            @Field("fullname") String fullname,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("language") String language
    );

    //read_vocabulary
    //http://misfitamigos.com/naticcl_englishwise/vocabulary/read_vocabulary.php?
    @FormUrlEncoded
    @POST("vocabulary/read_vocabulary.php")
    Call<ResponseBody> read_vocabulary(
            @Field("lan") String lan
    );*/


    /* @Multipart
     @POST("pending/pendingtransfer")
     Call<SendAddAmountRequestResponse> SendAddAmountRequest(
             @Header("Authorization") String authorization,
             @Part("accountName") RequestBody Bankname,
             @Part("accountNo") RequestBody Acc_no,
             @Part("customerName") RequestBody Holder_name,
             @Part("transactionId") RequestBody TransId,
             @Part("amount") RequestBody Amount,
             @Part("currency") RequestBody Currency,
             @Part("utility") RequestBody utility,
             @Part MultipartBody.Part image

     );*//*
    @Multipart
    @POST("user/registration")
    Call<ResponseBody> registration(
            @Part("fullname") RequestBody fullname,
            @Part("email") RequestBody email,
            @Part("phone") RequestBody phone,
            @Part("language") RequestBody language,
            @Part MultipartBody.Part image);
   *//* @PUT("user/profileimage")
    Call<ProfileImageResp> uploadPic(@Part MultipartBody.Part image);
*//*
    //read_vocabulary
    //http://misfitamigos.com/naticcl_englishwise/vocabulary/read_vocabulary.php

    @Multipart
    @POST("user/update_profile")
    Call<ResponseBody> update_profile(
            @Part("fullname") RequestBody fullname,
            @Part("email") RequestBody email,
            @Part("phone") RequestBody phone,
            @Part("language") RequestBody language,
            @Part("userid") RequestBody userid,
            @Part MultipartBody.Part image);


    @FormUrlEncoded
    @POST("read/vocabulary")
    Call<ResponseBody> read_vocabulary(
            @Field("lan") String lan
    );

    @FormUrlEncoded
    @POST("language/category")
    Call<List<MockRespBean>> Read_MocktestList(
            @Field("lan") String lan
    );


    @FormUrlEncoded
    @POST("all/practicetest")
    Call<ResponseBody> Read_practicetestt(
            @Field("dialogueid") String dialogueid
    );

    @GET("auth/moacktest/{cat_id}")
    Call<ResponseBody> Read_practicetest(
            @Path("cat_id") String cat_id
    );





    @GET("auth/profile_video")
    Call<VideoRespBean> Get_YoutubeVideo(
    );
*/

}
