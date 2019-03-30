package com.autoads.app.retrofit;


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

/**
 * Created by Asad
 */

public interface APIInterface {


//    @FormUrlEncoded
//    @POST("signup")
//    Call<SignUpLoginResponseModel> doSignUpAsCompany(@Field("user_type") int userType, @Field("name") String name, @Field("email") String email
//            , @Field("password") String password, @Field("password_confirmation") String ConfirmPassword
//            , @Field("contact_number") String contactNumber, @Field("company_name") String companyName
//            , @Field("company_email") String companyEmail, @Field("company_phone_number") String companyPhoneNumber
//            , @Field("company_establishment_year") String companyEstablishmentYear, @Field("company_address") String companyAddress
//            , @Field("is_real_estate_agency") int isRealEstateAgency, @Field("is_service_provider") int isServiceProvider
//            , @Field("company_address_latitude") Double latitude, @Field("company_address_longitude") Double longitude
//            , @Field("device_token") String deviceToken);
//
//    @Multipart
//    @POST("signup")
//    Call<SignUpLoginResponseModel> doSignUpAsCompanyWithImage(@Part MultipartBody.Part image, @Part("user_type") RequestBody userYype
//            , @Part("name") RequestBody name, @Part("email") RequestBody email
//            , @Part("password") RequestBody password, @Part("password_confirmation") RequestBody ConfirmPassword
//            , @Part("contact_number") RequestBody contactNumber, @Part("company_name") RequestBody companyName
//            , @Part("company_email") RequestBody companyEmail, @Part("company_phone_number") RequestBody companyPhoneNumber
//            , @Part("company_establishment_year") RequestBody companyEstablishmentYear, @Part("company_address") RequestBody companyAddress
//            , @Part("is_real_estate_agency") RequestBody isRealEstateAgency, @Part("is_service_provider") RequestBody isServiceProvider
//            , @Part("company_address_latitude") RequestBody latitude, @Part("company_address_longitude") RequestBody longitude
//            , @Part("device_token") RequestBody deviceToken);
//
//
//    @Multipart
//    @POST("edit-profile")
//    Call<ModelForUpdateUserProfile> updateUserProfileWithoutImage(@Part("user") RequestBody userEmail
//            , @Part("user_type") RequestBody userType, @Part("name") RequestBody userName, @Part("email") RequestBody email
//            , @Part("contact_number") RequestBody contactNumberStr);
//
//
//    @Multipart
//    @POST("edit-profile")
//    Call<ModelForUpdateUserProfile> updateUserProfile(@Part MultipartBody.Part image, @Part("user") RequestBody userEmail
//            , @Part("user_type") RequestBody userType, @Part("name") RequestBody userName, @Part("email") RequestBody email
//            , @Part("contact_number") RequestBody contactNumberStr);
//
//
//    @FormUrlEncoded
//    @POST("signup")
//    Call<SignUpLoginResponseModel> doSignUpAsUser(@Field("user_type") int userType, @Field("name") String name, @Field("email") String email
//            , @Field("password") String password, @Field("password_confirmation") String String, @Field("contact_number") String contactNumber
//            , @Field("device_token") String deviceToken);
//
//
//    @Multipart
//    @POST("signup")
//    Call<SignUpLoginResponseModel> doSignUpAsUserWithImage(@Part MultipartBody.Part image, @Part("user_type") RequestBody userType
//            , @Part("name") RequestBody name, @Part("email") RequestBody email, @Part("password") RequestBody password
//            , @Part("password_confirmation") RequestBody ConfirmPassword, @Part("contact_number") RequestBody contactNumber
//            , @Part("device_token") RequestBody deviceToken);
//
//
//    @FormUrlEncoded
//    @POST("login")
//    Call<LoginResponseModel> doLogin(@Field("email") String email, @Field("password") String password,
//                                     @Field("device_token") String fcmDeviceToken);
//
//    @FormUrlEncoded
//    @POST("edit-profile")
//    Call<ModelForUpdateUserProfile> updateUserProfile(@Field("user") String userEmail, @Field("user_type") Integer userType,
//                                                      @Field("name") String userName, @Field("email") String userEmailToUpdate,
//                                                      @Field("contact_number") String userContactNumber);
//
//    @FormUrlEncoded
//    @POST("make-booking")
//    Call<ModelForMakeBooking> makeBooking(@Field("user") Integer userId, @Field("company") Integer companyId,
//                                          @Field("name") String userName, @Field("email") String userEmail,
//                                          @Field("contact_number") String userContactNumber, @Field("address") String userAddress,
//                                          @Field("latitude") String latitude, @Field("longitude") String longitude,
//                                          @Field("information") String information, @Field("service_ad") Integer serviceAdId,
//                                          @Field("services") String services, @Field("date") String date, @Field("time") String time);
//
//
//    @FormUrlEncoded
//    @POST("reset")
//    Call<GenericResponseModel> resetPwd(@Field("email") String email);
//
//
//    @FormUrlEncoded
//    @POST("schedule-visit")
//    Call<ModelForScheduleVisit> scheduleVisit(@Field("user") int userId, @Field("company") int companyId, @Field("property") int propertyId
//            , @Field("name") String name, @Field("email") String email, @Field("contact_number") String contactNumber
//            , @Field("date") String visitDate, @Field("time") String visitTime);
//
//
//    @GET("search-properties")
//    Call<ResponseModelForSearchProperty> searchProperty(@Query("user") String user, @Query("latitude") Double latitude, @Query("longitude") Double longitude,
//                                                        @Query("query") String query, @Query("type") String type, @Query("status") String status,
//                                                        @Query("distance") Integer distance, @Query("rating") Integer rating,
//                                                        @Query("price_min") Integer priceMin, @Query("price_max") Integer priceMax,
//                                                        @Query("beds") Integer beds, @Query("baths") Integer baths, @Query("square_feet") Integer squareFeet,
//                                                        @Query("year_built") Integer yearBuilt, @Query("category") String category);
//
//    @GET("search-service-ads")
//    Call<ResponseModelForSearchServices> searchServices(@Query("user") String user, @Query("query") String query, @Query("category") String category);
//
//
//    @GET("get-services-categories")
//    Call<ModelForServicesAndCategories> getServiceCategories();
//
//    @GET("landing-page")
//    Call<LandingPageResponseModel> getLandingPageImage(@Query("country") int cityId);
//
//    @GET("property")
//    Call<ModelForSingleProperty> getPropertyDetails(@Query("property") int propertyId, @Query("user") String user);
//
//    @GET("user")
//    Call<ResponseModelForUser> getUser(@Query("email") String email);
//
//    @GET("get-user-property-bookmark")
//    Call<ModelForBookmarkedProperties> getUserBookmarkedProperties(@Query("user") String email);
//
//    @GET("get-user-service-ad-bookmark")
//    Call<ModelForBookmarkedServices> getUserBookmarkedService(@Query("user") String email);
//
//    @GET("get-invoice")
//    Call<ModelForInvoice> getInvoice(@Query("booking") Integer bookingId);
//
//    @GET("user-scheduled-visits")
//    Call<ModelForScheduleVisits> getUserScheduledVisits(@Query("user") Integer userId);
//
//    @GET("get-user-bookings")
//    Call<ModelForUsingBooking> getUserBooking(@Query("user") Integer userId);
//
//    @GET("company-scheduled-visits")
//    Call<ModelForScheduleVisits> getCompanyScheduledVisits(@Query("company") Integer companyId);
//
//
//    @GET("get-bookmark")
//    Call<ResponseModelForUser> getBookMarks(@Query("user") String user, @Query("property") int property);
//
//    @GET("get-service-provider-location")
//    Call<ModelAgentLocation> getAgentLocation(@Query("service_provider") Integer user);
//
//
//    @GET("get-service-ad-services")
//    Call<ModelForServiceAds> getServiceAdsServices(@Query("service_ad") Integer serviceAdId);
//
//    @GET("company-properties-serviceads")
//    Call<ModelForPropertyServices> getCompanyPropertiesAndServices(@Query("company") Integer companyId);
//
//
//    @FormUrlEncoded
//    @POST("add-property-bookmark")
//    Call<BookMarkResponseModel> addPropertyBookMark(@Field("user") String user, @Field("property") int property);
//
//
//    @FormUrlEncoded
//    @POST("add-service-ad-bookmark")
//    Call<BookMarkResponseModel> addServiceBookMark(@Field("user") String user, @Field("service") int serviceAdId);
//
//    @FormUrlEncoded
//    @POST("rate-booking")
//    Call<ModelForRating> rateBooking(@Field("booking") Integer bookingId, @Field("rating") Float rating);
//
//    @FormUrlEncoded
//    @POST("remove-service-ad-bookmark")
//    Call<BookMarkResponseModel> removeServiceBookMark(@Field("bookmark") int serviceBookmarkId);
//
//
//    @FormUrlEncoded
//    @POST("remove-property-bookmark")
//    Call<BookMarkResponseModel> removePropertyBookMark(@Field("bookmark") int property);



    /*
    @FormUrlEncoded
    @POST("user/login?suppress_response_code=1")
    Call<SignUpLoginResponseModel> doLogin(@Field("username") RequestBody name, @Field("password") RequestBody pwd);

    @FormUrlEncoded
    @POST("user/delivery-boy-login?suppress_response_code=1")
    Call<DriverLoginResponseModel> driverLogin(@Field("username") RequestBody name, @Field("password") RequestBody pwd);


    @FormUrlEncoded
    @POST("user/otp-verification?suppress_response_code=1")
    Call<SignUpLoginResponseModel> verifyPin(@Field("username") RequestBody name, @Field("otp_code") RequestBody job);

    @GET("screen/homepage?")
    Call<HomePageResponseModel> getHomeScreenData(@Query("suppress_response_code") RequestBody suppressResponseCode, @Query("username") RequestBody username, @Query("latitude") RequestBody latitude, @Query("longitude") RequestBody longitude);

    @GET("screen/drive-thru?")
    Call<DineInResponseModel> getDriveThru(@Query("suppress_response_code") int suppressResponseCode, @Query("username") RequestBody username, @Query("latitude") double latitude, @Query("longitude") double longitude);

    @GET("screen/restaurant-info?")
    Call<RestaurantInformationResponseModel> getRestaurantInformation(@Query("suppress_response_code") RequestBody suppressResponseCode, @Query("username") RequestBody username, @Query("restaurant_id") RequestBody restaurantId);

    @GET("screen/menu?")
    Call<MenuResponseModel> getRestaurantMenu(@Query("suppress_response_code") RequestBody suppressResponseCode, @Query("restaurant_id") RequestBody restaurantId);

    @GET("user-addresses?")
    Call<AddressBookResponseModel> getUserAddressBook(@Query("suppress_response_code") RequestBody suppressResponseCode, @Query("username") RequestBody userNameStr);

    @GET("screen/my-orders")
    Call<OrderResponseModel> getMyOrders(@Query("suppress_response_code") RequestBody suppressResponseCode, @Query("username") RequestBody userNameStr);

    @GET("screen/additional-items")
    Call<AddOptionResponseModel> getCompulsoryItems(@Query("suppress_response_code") RequestBody suppressResponseCode, @Query("meal_id") RequestBody mealId);


    @GET("screen/dine-in")
    Call<DineInResponseModel> getDineIn(@Query("suppress_response_code") int suppressResponseCode, @Query("username") RequestBody username, @Query("latitude") double latitude, @Query("longitude") double longitude);

    @FormUrlEncoded
    @POST("screen/checkout?suppress_response_code=1")
    Call<CheckOutResponseModel> doCheckOut(@Field("data") RequestBody data);

    @FormUrlEncoded
    @POST("screen/search")
    Call<SearchResponseModel> searchWithFilter(@Query("suppress_response_code") int suppressResponseCode, @Query("username") RequestBody username, @Query("latitude") double latitude, @Query("longitude") double longitude,
                                               @Field("query") RequestBody query, @Field("is_delivery") int is_delivery, @Field("is_dinein") int is_dinein, @Field("is_drivethru") int is_drivethru,
                                               @Field("location_latitude") double location_latitude, @Field("location_longitude") double location_longitude, @Field("distance") int distance);


    @FormUrlEncoded
    @POST("screen/search")
    Call<SearchResponseModel> searchWithOutFilter(@Query("suppress_response_code") int suppressResponseCode, @Query("username") RequestBody username, @Query("latitude") double latitude, @Query("longitude") double longitude,
                                                  @Field("query") RequestBody query, @Field("is_delivery") int is_delivery, @Field("is_dinein") int is_dinein, @Field("is_drivethru") int is_drivethru);


    @FormUrlEncoded
    @PUT("orders/{id}")
    Call<CancelOrderResponseModel> cancelOrder(@Path("id") int id, @Field("status") int status, @Query("suppress_response_code") int suppressResponseCode);

    @PUT("users/{id}")
    Call<UserProfileResponseModel> getUserProfile(@Path("id") int id, @Query("suppress_response_code") RequestBody suppressResponseCode);


    @PUT("screen/orders-by-status")
    Call<ResponseModelForDriverModels> getOrderByStatus(@Query("delivery_boy_id") int delivery_boy_id, @Query("status") int status, @Query("suppress_response_code") int suppressResponseCode);

*/
}
