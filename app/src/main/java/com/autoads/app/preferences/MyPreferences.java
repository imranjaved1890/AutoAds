package com.autoads.app.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.autoads.app.R;
import com.google.gson.Gson;


public class MyPreferences {

    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_PHONE = "user_phone";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_OTP_STATUS = "otp_status";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_MOBILE_NUM_VERIFIED = "mobile_number_verified";


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

    public boolean isMobileVerified() {
        return sharedPreferences.getBoolean(KEY_MOBILE_NUM_VERIFIED, false);
    }

    public void setMobileVerified(boolean mobileVerified) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_MOBILE_NUM_VERIFIED, mobileVerified);
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


    public int getUserId() {
        return sharedPreferences.getInt(KEY_USER_ID, 0);
    }

    public void setUserId(int userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, userId);
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

    public String getUserPhone() {
        return sharedPreferences.getString(KEY_USER_PHONE, "");
    }

    public void setUserPhone(String userPhone) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_PHONE, userPhone);
        editor.commit();
    }


    public void clearPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }


}
