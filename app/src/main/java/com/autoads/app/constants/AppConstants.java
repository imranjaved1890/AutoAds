package com.autoads.app.constants;


import android.location.Address;

import com.autoads.app.BuildConfig;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;


public class AppConstants {

    public static String BASE_URL = "http://eqaar.e-community24.com/";
//    public static String BASE_URL = "https://anglotesting.website/eqaar/";
    public static String API_URL = BASE_URL + "api/";
    public static String IMAGES_URL = BASE_URL + "public/";
    public static DecimalFormat df2 = new DecimalFormat(".##");


    public static String KEY_LAT = "key_lat";
    public static String KEY_LNG = "key_lng";
    public static String KEY_TITLE = "key_title";
    public static String KEY_LOCATION_ADDRESS = "location_address";

    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String RESULT_DATA_KEY_SAVED = "key_saved";
    public static final String RESULT_DATA_VALUE = "YES";

    public static final int COMPANY_CODE = 2;
    public static final int USER_CODE = 3;

    public static final int NOTIFICATION_TYPE_JOB_REQUESTED= 11;
    public static final int NOTIFICATION_TYPE_JOB_ASSIGNED= 12;
    public static final int NOTIFICATION_TYPE_JOB_STATUS_CHANGED= 13;
    public static final int NOTIFICATION_TYPE_RATE_BOOKING= 14;

    public static final int STATUS_REQUESTED = 1;
    public static final int STATUS_ACCEPTED = 2;
    public static final int STATUS_ON_THE_WAY = 3;
    public static final int STATUS_ARRIVED = 4;
    public static final int STATUS_SERVING = 5;
    public static final int STATUS_SERVED = 6;
    public static final int STATUS_CANCELED = 7;
    public static final int STATUS_PAYMENT_TAKEN= 8;


    public static final String INDIVIDUAL_ID = "3";
    public static final String COMPANY_ID = "2";
    public static final String AGENT_ID = "4";
    public static final String ADMIN_ID = "1";

    public static final int PROXIMITY_RADIUS = 1000;

    public static String appCurrency = "AED";
    public static Address myAddress;
    public static MediaType MEDIA_TYPE_IMAGE = MediaType.parse("image/*");

    public static String KEY_GET_LOCATION = "get_location";

    public static final String PACKAGE_NAME = BuildConfig.APPLICATION_ID;
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";
    public static final String RESULT_DATA_KEY_CITY = "key_city";
    public static final String RESULT_DATA_KEY_STATE = "key_state";
    public static final String RESULT_DATA_KEY_COUNTRY = "key_country";
    public static final String KEY_BOOKING_ID = "key_booking_id";

    public static final int MY_PERMISSIONS_REQUEST_READ_WRITE_STORAGE = 45;
    public static final int OPEN_MEDIA_PICKER = 14;
    public static String IMAGE = "image";

    public static String REG_AS_COMPANY = "reg_as_company";
    public static String KEY_WORD_SEARCH = "key_word_search";
    public static String KEY_SEARCH_TYPE = "key_search_type";
    public static String USER_TYPE = "user_type";
    public static String USER_INDIVIDUAL = "user_individual";
    public static String USER_COMPANY = "user_company";
    public static String KEY_POSITION = "key_position";
    public static String KEY_ID = "key_id";
    public static String KEY_NOTIFICATION_CODE = "key_notification_code";
    public static String KEY_SERVICE_ID = "key_service_id";
    public static String KEY_TRAVELLING_CHARGES = "key_travelling_charges";
    public static String KEY_PDF = "key_pdf";
    public static String KEY_RESIDENTIAL = "Residential";
    public static String KEY_COMMERCIAL = "Commercial";
    public static String KEY_FOR_SALE = "For Sale";
    public static String KEY_FOR_RENT = "For Rent";
    public static String nearbyPlacesAPIResponse = "";

    public static String privacyUrl = "https://sites.google.com/view/dynamixup/eqaar_privacy_policy";

    public static int FLAG = 0;

    public static int HOME_SCREEN_TAB_NUMBER = 0;

    public static int SPLAH_TIMEOUT = 2000;
    public static int MY_SCAN_REQUEST_CODE = 244;
    public static int MY_PERMISSIONS_REQUEST_CAMERA = 434;
    public static int MY_PERMISSIONS_REQUEST_LOCATION = 435;
    public static final int ADDRESS_BOOK_REQUEST_CODE = 12;


    public static double lat = 0.0d;
    public static double lng = 0.0d;

    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    //--------------------values for filter--------------
    public static Double mySearchLat = 0.0;
    public static Double mySearchLng = 0.0;
    public static String mySearchQuery = null;
    public static String searchType = null;
    public static String searchStatus = null;
    public static String searchCategory = null;
    public static Integer searchDistance = null;
    public static Integer searchRating = null;
    public static Integer searchPriceMin = null;
    public static Integer searchPriceMax = null;
    public static Integer searchBeds = null;
    public static Integer searchBaths = null;
    public static Integer searchSquareFeet = null;
    public static Integer searchYearBuilt = null;
    //---------------------------------------------------


//    public static int gridAnimationArray[] = {
//            R.anim.grid_layout_animation_from_bottom,
//            R.anim.grid_layout_animation_scale,
//            R.anim.grid_layout_animation_scale_random
//    };
//
//    public static int listAnimationArray[] = {
//            R.anim.layout_animation_fall_down,
//            R.anim.layout_animation_from_right,
//            R.anim.layout_animation_from_bottom
//    };

}
