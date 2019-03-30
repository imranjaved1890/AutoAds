package com.autoads.app.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.autoads.app.R;
import com.google.gson.Gson;



public class MyPreferences {

    private static final String KEY_APP_RATE = "app_rate";
    private static final String KEY_USER_VERIFIED = "app_rate";
    private static final String KEY_CURRENT_LANDING_OBJECT = "current_landing_object";
    private static final String KEY_APPLICATION_ID = "application_id";
    private static final String KEY_SSID = "ssid";
    private static final String KEY_PASS = "pass";
    private static final String KEY_RATE_LATER_COUNT = "rate_later_count";
    private static final String KEY_APP_LAUNCH_COUNT = "app_launch_count";
    private static final String KEY_FIRST_TIME = "first_time";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_COMPANY_ID = "company_id";
    private static final String KEY_USER_TYPE_ID = "user_type_id";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_CONTACT_NUMBER = "user_contact_number";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_COMPANY_EMAIL = "company_email";
    private static final String KEY_OTP_CODE = "otp_code";
    private static final String KEY_OTP_STATUS = "otp_status";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_IS_FILTER_SET = "is_filter_set";
    private static final String KEY_IS_CUSTOMER_LOGIN = "customer_is_logged_in";
    private static final String KEY_PAYMENT_INFO = "payment_info";
    private static final String KEY_CURRENT_LAT = "current_latitude";
    private static final String KEY_CURRENT_LONG = "current_longitude";
    private static final String KEY_PROFILE_IMAGE = "profile_image";
    private static final String KEY_COMPANY_LOGO = "company_logo";

    private static final String KEY_USER_SELECTED_LAT = "user_latitude";
    private static final String KEY_USER_SELECTED_LONG = "user_longitude";

    private static final String KEY_FILTER_LOCATION_ADDRESS = "filter_location_address";
    private static final String KEY_FILTER_LOCATION_LAT = "filter_location_lat";
    private static final String KEY_FILTER_LOCATION_LNG = "filter_location_lng";
    private static final String KEY_FILTER_DISTANCE_RADIUS = "filter_distance_radius";
    private static final String KEY_TERMS_AND_CONDITIONS_URL = "ters_and_conditions_url";


    private final Context mContext;
    private SharedPreferences sharedPreferences;
    private String preferenceName;
    private static Gson GSON = new Gson();


    public MyPreferences(Context context) {
        mContext = context;
        if (mContext != null) {
            preferenceName = mContext.getResources().getString(R.string.app_shared_pref);
            sharedPreferences = mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        }
    }


    public int getOtpStatus() {
        return sharedPreferences.getInt(KEY_OTP_STATUS, 0);
    }

    public void setOtpStatus(int otpStatus) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_OTP_STATUS, otpStatus);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setLoggedIn(boolean loginStatus) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, loginStatus);
        editor.commit();
    }

    public boolean isFilterSet() {
        return sharedPreferences.getBoolean(KEY_IS_FILTER_SET, false);
    }

    public void setFilterSet(boolean filterSet) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_FILTER_SET, filterSet);
        editor.commit();
    }


    public boolean isCustomerLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_CUSTOMER_LOGIN, false);
    }

    public void setCustomerLogin(boolean customerLogin) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_CUSTOMER_LOGIN, customerLogin);
        editor.commit();
    }


    public boolean hasPaymentInfoSaved() {
        return sharedPreferences.getBoolean(KEY_PAYMENT_INFO, false);
    }

    public void setPaymentInfo(boolean paymentInfo) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_PAYMENT_INFO, paymentInfo);
        editor.commit();
    }


    public int getUserId() {
        return sharedPreferences.getInt(KEY_USER_ID, 0);
    }

    public void setUserId(int userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, userId);
        editor.commit();
    }

    public int getCompanyId() {
        return sharedPreferences.getInt(KEY_COMPANY_ID, 0);
    }

    public void setCompanyId(int companyId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_COMPANY_ID, companyId);
        editor.commit();
    }

    public String getUserTypeId() {
        return sharedPreferences.getString(KEY_USER_TYPE_ID, "");
    }

    public void setUserTypeId(String userTypeId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_TYPE_ID, userTypeId);
        editor.commit();
    }

    public String getUserName() {
        return sharedPreferences.getString(KEY_USER_NAME, "");
    }

    public void setUserName(String userName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_NAME, userName);
        editor.commit();
    }

    public String getUserContactNumber() {
        return sharedPreferences.getString(KEY_USER_CONTACT_NUMBER, "");
    }

    public void setUserContactNumber(String userContactNumber) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_CONTACT_NUMBER, userContactNumber);
        editor.commit();
    }

    public String getUserEmail() {
        return sharedPreferences.getString(KEY_USER_EMAIL, "");
    }

    public void setUserEmail(String userEmail) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_EMAIL, userEmail);
        editor.commit();
    }

    public String getCompanyEmail() {
        return sharedPreferences.getString(KEY_COMPANY_EMAIL, "");
    }

    public void setCompanyEmail(String companyEmail) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_COMPANY_EMAIL, companyEmail);
        editor.commit();
    }


    public String getOtpCode() {
        return sharedPreferences.getString(KEY_OTP_CODE, "");
    }

    public void setOtpCode(String otpCode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_OTP_CODE, otpCode);
        editor.commit();
    }

//    private static final String KEY_FILTER_LOCATION_ADDRESS = "filter_location_address";
//    private static final String KEY_FILTER_LOCATION_LAT = "filter_location_lat";
//    private static final String KEY_FILTER_LOCATION_LNG = "filter_location_lng";
//    private static final String KEY_FILTER_DISTANCE_RADIUS = "filter_distance_radius";
//


    public String getFilterAddress() {
        return sharedPreferences.getString(KEY_FILTER_LOCATION_ADDRESS, "");
    }

    public void setFilterAdress(String filterAdress) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_FILTER_LOCATION_ADDRESS, filterAdress);
        editor.commit();
    }


    public String getTermsAndConditionsUrl() {
        return sharedPreferences.getString(KEY_TERMS_AND_CONDITIONS_URL, "");
    }

    public void setTermsAndConditionsUrl(String termsAndConditionsUrl) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TERMS_AND_CONDITIONS_URL, termsAndConditionsUrl);
        editor.commit();
    }


    public String getFilterLat() {
        return sharedPreferences.getString(KEY_FILTER_LOCATION_LAT, "");
    }

    public void setFilterLat(String filterLat) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_FILTER_LOCATION_LAT, filterLat);
        editor.commit();
    }


    public String getFilterLng() {
        return sharedPreferences.getString(KEY_FILTER_LOCATION_LNG, "");
    }

    public void setFilterLNg(String filterLng) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_FILTER_LOCATION_LNG, filterLng);
        editor.commit();
    }

    public int getFilterDistance() {
        return sharedPreferences.getInt(KEY_FILTER_DISTANCE_RADIUS, 1);
    }

    public void setFilterDistance(int filterDistance) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_FILTER_DISTANCE_RADIUS, filterDistance);
        editor.commit();
    }


    public String getCurrentLat() {
        return sharedPreferences.getString(KEY_CURRENT_LAT, "");
    }

    public String getCurrentLong() {
        return sharedPreferences.getString(KEY_CURRENT_LONG, "");
    }

    public void setCurrentLat(String currentLat) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_CURRENT_LAT, currentLat);
        editor.commit();
    }

    public void setCurrentLong(String currentLong) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_CURRENT_LONG, currentLong);
        editor.commit();
    }


    public String getUserSelectedLng() {
        return sharedPreferences.getString(KEY_USER_SELECTED_LONG, "0.0");
    }

    public void setUserSelectedLng(String selectedLng) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_SELECTED_LONG, selectedLng);
        editor.commit();
    }


    public String getUserSelectedLat() {
        return sharedPreferences.getString(KEY_USER_SELECTED_LAT, "0.0");
    }

    public void setUserSelectedLat(String selectedLat) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_SELECTED_LAT, selectedLat);
        editor.commit();
    }

    public boolean hasUserRatedApp() {
        return sharedPreferences.getBoolean(KEY_APP_RATE, false);
    }

    public void setUserRatedApp(boolean hasRated) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_APP_RATE, hasRated);
        editor.commit();
    }

    public boolean isUserVerified() {
        return sharedPreferences.getBoolean(KEY_USER_VERIFIED, false);
    }

    public void setUserVerified(boolean userVerified) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_USER_VERIFIED, userVerified);
        editor.commit();
    }

    public String getHotSpotPassword() {
        return sharedPreferences.getString(KEY_PASS, "");
    }

    public void setHotSpotPassword(String hotSpotPassword) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PASS, hotSpotPassword);
        editor.commit();
    }

    public int getRateLaterCount() {
        return sharedPreferences.getInt(KEY_RATE_LATER_COUNT, 0);
    }

    public void setRateLaterCount(int rateLaterCount) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_RATE_LATER_COUNT, rateLaterCount);
        editor.commit();
    }

    public int getAppLunchCount() {
        return sharedPreferences.getInt(KEY_APP_LAUNCH_COUNT, 0);
    }

    public void setAppLunchCount(int appLunchCount) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_APP_LAUNCH_COUNT, appLunchCount);
        editor.commit();
    }


    public boolean isFirstTime() {
        return sharedPreferences.getBoolean(KEY_FIRST_TIME, true);
    }

    public void setFirstTime(boolean firsTime) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_FIRST_TIME, firsTime);
        editor.commit();
    }

    public boolean getProfileImage() {
        return sharedPreferences.getBoolean(KEY_PROFILE_IMAGE, true);
    }

    public void setProfileImage(String profileImge) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PROFILE_IMAGE, profileImge);
        editor.commit();
    }

    public boolean getCompanyLogo() {
        return sharedPreferences.getBoolean(KEY_COMPANY_LOGO, true);
    }

    public void setCompanyLogo(String companyLogo) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_COMPANY_LOGO, companyLogo);
        editor.commit();
    }


    public String getHotSpotSSID() {
        return sharedPreferences.getString(KEY_SSID, "");
    }

    public void setHotSpotSSID(String hotSpotSSID) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_SSID, hotSpotSSID);
        editor.commit();
    }

    public void clearPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public String getFcmToken() {
        return sharedPreferences.getString(KEY_APPLICATION_ID, "");
    }

    public void setFcmToken(String applicationId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_APPLICATION_ID, applicationId);
        editor.commit();
    }















    //***************************Saving Current Landing Object **********************************


    public  void clearCurrentLandingObject( ){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_CURRENT_LANDING_OBJECT);
        editor.commit();
    }


    public void putObject(String key, Object object) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(object == null){
            throw new IllegalArgumentException("object is null");
        }

        if(key.equals("") || key == null){
            throw new IllegalArgumentException("key is empty or null");
        }

        editor.putString(key, GSON.toJson(object));
        editor.commit();
    }

    public <T> T getObject(String key, Class<T> a) {

        String gson =  sharedPreferences.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try{
                return GSON.fromJson(gson, a);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object storaged with key " + key + " is instanceof other class");
            }
        }
    }
    //******************************************************
}
