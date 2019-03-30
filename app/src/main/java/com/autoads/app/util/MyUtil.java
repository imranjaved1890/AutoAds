package com.autoads.app.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.autoads.app.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyUtil {


    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void log(String key, String message) {
        Log.e(key, message);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    public static int splitStringAndGetItem(String datToSplit){
        String[] parts = datToSplit.split(".");
        return Integer.parseInt(parts[0]);
    }

//    public static int getRequest(String requestType) {
//        if (requestType.equalsIgnoreCase(BinsinaConstants.POST))
//            return Request.Method.POST;
//        else if (requestType.equalsIgnoreCase(BinsinaConstants.GET))
//            return Request.Method.GET;
//        else if (requestType.equalsIgnoreCase(BinsinaConstants.DELETE))
//            return Request.Method.DELETE;
//        else
//            return Request.Method.PUT;
//    }


    /**
     * Converting dp to pixel
     */
    public static int dpToPx(int dp, Context context) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

//    public static void showSnackBar(View view, String msg) {
//        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
//    }

    public static void showToast(Context context) {
        Toast.makeText(context, context.getResources().getString(R.string.internet_connection_not_available), Toast.LENGTH_SHORT).show();
    }

    public static void openDialActivity(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }


//    public static String getURL(String url, Context mContext) {
//        String locale = Locale.getDefault().getLanguage();
//        String api_url = mContext.getResources().getString(R.string.api_url);
//        api_url += url;
////        Log.e("API URL", api_url);
//        return api_url;
//    }

//    public static String getLoginURL(String url, Context mContext) {
//        String locale = Locale.getDefault().getLanguage();
//        String api_url = mContext.getResources().getString(R.string.login_api_url);
//        api_url += url;
////        Log.e("API URL", api_url);
//        return api_url;
//    }

    //***********************************
//    public static void showLoginDialog(Activity activity) {
//
//        android.app.FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
//        Fragment prev = activity.getFragmentManager().findFragmentByTag("dialog");
//        if (prev != null) {
//            fragmentTransaction.remove(prev);
//        }
//        fragmentTransaction.addToBackStack(null);
//        // Create and show the dialog.
//        LoginDialogFragment loginDialogFragment = LoginDialogFragment.newInstance("Some Title");
//        loginDialogFragment.show(fragmentTransaction, "dialog");
//
//    }

    //***********************************
    public static void dumpBundleArguments(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Bundle bundle = savedInstanceState;
            if (bundle != null) {
                Set<String> keys = bundle.keySet();
                Iterator<String> it = keys.iterator();
                MyUtil.showLog("BUNDLE", "Dumping Intent start");
                while (it.hasNext()) {
                    String key = it.next();
                    MyUtil.showLog("BUNDLE", "[" + key + "=" + bundle.get(key) + "]");
                }
                MyUtil.showLog("BUNDLE", "Dumping Intent end");
            }
        }

    }

    //***********************************
    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    //    public static int getTabsHeight(Context context) {
//        return (int) context.getResources().getDimension(R.dimen.tabsHeight);
//    }
    //***********************************
    public static void showSuccessSnackBar(View parentView, Context context, String message) {
        Snackbar snack = Snackbar.make(parentView,
                message, Snackbar.LENGTH_SHORT);
        //snack.setAction(actionButton, new View.OnClickListener());//add your own listener
        View view = snack.getView();
        view.setBackgroundColor(MyUtil.getColor(context, R.color.green));
        TextView tv = (TextView) view
                .findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);//change textColor

        TextView tvAction = (TextView) view
                .findViewById(android.support.design.R.id.snackbar_action);
        tvAction.setTextSize(16);
        tvAction.setTextColor(Color.WHITE);

        snack.show();
    }

    //***********************************
    public static void showErrorSnackBar(View parentView, Context context, String message) {
        Snackbar snack = Snackbar.make(parentView,
                message, Snackbar.LENGTH_SHORT);
        //snack.setAction(actionButton, new View.OnClickListener());//add your own listener
        View view = snack.getView();
        view.setBackgroundColor(MyUtil.getColor(context, R.color.red_new));
        TextView tv = (TextView) view
                .findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);//change textColor

        TextView tvAction = (TextView) view
                .findViewById(android.support.design.R.id.snackbar_action);
        tvAction.setTextSize(16);
        tvAction.setTextColor(Color.WHITE);

        snack.show();
    }

    //**********************************************************************************************
    public static void showAlertDialog(Context context, String title, String message, String positiveBtn, String negativeBtn) {
        AlertDialog.Builder builder;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            builder = new AlertDialog.Builder(context, R.style.MyAlertDialogTheme);
//        } else {
//            builder = new AlertDialog.Builder(context);
//        }
        builder = new AlertDialog.Builder(context);
        if (!title.isEmpty()) {
            builder.setTitle(title);
        }
        builder.setMessage(message);

        builder.setPositiveButton(positiveBtn, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // continue with delete
                dialog.dismiss();
            }
        });
        if (!negativeBtn.isEmpty()) {
            builder.setNegativeButton(negativeBtn, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing
                    dialog.dismiss();
                }
            });
        }
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.show();
    }

    //**********************************************************************************************
    public static String convertToArabic(int value) {
        String newValue = (((((((((((value + "")
                .replaceAll("1", "١")).replaceAll("2", "٢"))
                .replaceAll("3", "٣")).replaceAll("4", "٤"))
                .replaceAll("5", "٥")).replaceAll("6", "٦"))
                .replaceAll("7", "٧")).replaceAll("8", "٨"))
                .replaceAll("9", "٩")).replaceAll("0", "٠"));
        return newValue;
    }

    //**********************************************************************************************

//    public static void setLanguageAndFontPreferences(Context context) {
//        //*************************************Changing translation*********************************
//
//        Locale locale;
//
//        FontsOverride.setDefaultFont(context, "DEFAULT", "jaldi_regular.ttf");
//        FontsOverride.setDefaultFont(context, "MONOSPACE", "lekton_bold.ttf");
//        FontsOverride.setDefaultFont(context, "SERIF", "lekton_regular.ttf");
//
////        MyContextWrapper.wrap(this, "cs");
//
//        AnotherConstants.LANGUAGE_CODE = "en";
////        BinsinaConstants.LANGUAGE_CODE = "ar";
//        String languageToLoad = AnotherConstants.LANGUAGE_CODE; // your language
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            locale = context.getResources().getConfiguration().getLocales().get(0);
////            locale = new Locale(languageToLoad);
//        } else {
////            locale = context.getResources().getConfiguration().locale;
//            locale = new Locale(languageToLoad);
//        }
//
////        Locale locale = new Locale(languageToLoad);
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
//
//
//    }
    //**********************************************************************************************

    public static boolean hasFrontCamera(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            return true;
        } else {
            return false;
        }
    }

    //*****************************
    public static boolean hasBackCamera(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    //*****************************
    public static boolean hasCamera(Context context) {
        PackageManager pm = context.getPackageManager();
        if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            return true;
        } else {
            return false;
        }
    }

    //*****************************
    public static void setColorFiler(Context context, ImageView imageView, int color) {
        imageView.setColorFilter(MyUtil.getColor(context, color));
    }
    //*****************************

    public static Bitmap getTintedBitmap(Bitmap bitmap, int color) {
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        Bitmap bitmapResult = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapResult);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bitmapResult;
    }

    public static void openLocationInMap(Context context, double lat, double lng, String label) {
        String uri = "geo:" + lat + "," + lng + "?q=" + lat + "," + lng + "(" + label + ")";
        Uri gmmIntentUri = Uri.parse(uri);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        }
    }

    //*****************************
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    //*****************************


    public String getAppLocale() {
        return Locale.getDefault().getLanguage();
    }

    //**********************************************************************************************

    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    //**********************************************************************************************
    public static boolean isURLValid(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //**********************************************************************************************

    public static boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber.length() < 5 || phoneNumber.length() > 13)
            return false;
        else
            return true;
    }

    //**********************************************************************************************

    public static boolean isPhoneNumberValid1(String phoneNumber) {
        boolean isValid = false;
        String expression = "^((\\+92)|(0092))-{0,1}\\d{3}-{0,1}\\d{7}$|^\\d{11}$|^\\d{4}-\\d{7}$";
        CharSequence inputStr = phoneNumber;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    //**********************************************************************************************

    public static String getIntPrecedingZero(int num) {
        if (num > -1 && num < 10) {
            return "0" + num;
        } else {
            return num + "";
        }
    }


    public static boolean isAppActive() {
        return false;
    }


    public static final String getMD5Hash(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static boolean isLocationEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (gps_enabled || network_enabled) {
            return true;
        } else {
            return false;
        }
    }


    //***************************************************


    public static Double strToDouble(String str) {
        try {

            return Double.parseDouble(str);

        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }


//    public static String getDeviceIMEI(Context context) {
//
//        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        String IMEI = telephonyManager.getDeviceId();
//
//        return IMEI;
//    }


    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
    //******************************************************************************

    public static boolean contactExists(Context context, String number) {
/// number is the phone number
        Uri lookupUri = Uri.withAppendedPath(
                ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));
        String[] mPhoneNumberProjection = {ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.NUMBER, ContactsContract.PhoneLookup.DISPLAY_NAME};
        Cursor cur = context.getContentResolver().query(lookupUri, mPhoneNumberProjection, null, null, null);
        try {
            if (cur.moveToFirst()) {
                return true;
            }
        } finally {
            if (cur != null)
                cur.close();
        }
        return false;
    }

    //****************************************************************************
    public static String getContactName(Context context, String phoneNumber) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
        if (cursor == null) {
            return null;
        }
        String contactName = null;
        if (cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));

            String tempPhone = contactName.replace("-", "");
            if (tempPhone.trim().toString().equals(phoneNumber.trim().toString())) {
                contactName = "";
            }
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return contactName;
    }


    //********************************************

    public static String convertDateFormat(String date) {
        SimpleDateFormat gsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
        try {
            Date d = gsdf.parse(date);
            System.out.println("new date format " + sdf.format(d));
            return sdf.format(d);
        } catch (Exception e) {
            // handle exception if parse time or another by cause
            return date;
        }

    }


    public static int getScreenOrientation(Context context) {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Do some stuff
            return 1;
        } else if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            //Do some stuff
            return 2;
        }
        return 0;
    }


    public static int getScreenSize(AppCompatActivity appCompatActivity) {
        DisplayMetrics metrics = new DisplayMetrics();
        appCompatActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        float scaleFactor = metrics.density;

        float widthDp = widthPixels / scaleFactor;
        float heightDp = heightPixels / scaleFactor;
        float smallestWidth = Math.min(widthDp, heightDp);

        if (smallestWidth > 720) {
            //Device is a 10" tablet
            return 10;

        } else if (smallestWidth > 600) {
            //Device is a 7" tablet
            return 7;
        } else if (smallestWidth < 600) {
            return 5;
        } else
            return 3;

    }


//    public void hideSoftKeyboard(Context context) {
//        if(getCurrentFocus()!=null) {
//            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//        }
//    }
//
//    /**
//     * Shows the soft keyboard
//     */
//    public void showSoftKeyboard(View view) {
//        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
//        view.requestFocus();
//        inputMethodManager.showSoftInput(view, 0);
//    }


    public static Bitmap createContrast(Bitmap src, double value) {
        // image size
        int width = src.getWidth();
        int height = src.getHeight();
        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
        // color information
        int A, R, G, B;
        int pixel;
        // get contrast value
        double contrast = Math.pow((100 + value) / 100, 2);

        // scan through all pixels
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // get pixel color
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                // apply filter contrast for every channel R, G, B
                R = Color.red(pixel);
                R = (int) (((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if (R < 0) {
                    R = 0;
                } else if (R > 255) {
                    R = 255;
                }

                G = Color.red(pixel);
                G = (int) (((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if (G < 0) {
                    G = 0;
                } else if (G > 255) {
                    G = 255;
                }

                B = Color.red(pixel);
                B = (int) (((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if (B < 0) {
                    B = 0;
                } else if (B > 255) {
                    B = 255;
                }

                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        return bmOut;
    }

    //**************************
    public static Drawable getTintedDrawable(Context context, @DrawableRes int drawableResId, @ColorRes int colorResId) {
        Drawable drawable = MyUtil.getDrawable(context, drawableResId);
        int color = MyUtil.getColor(context, colorResId);
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        return drawable;
    }
    //**************************

    public static final Drawable getDrawable(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getDrawable(context, id);
        } else {
            return context.getResources().getDrawable(id);
        }
    }
    //**************************

    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public static final int getColorWithTheme(Context context, int id, Resources.Theme theme) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return context.getResources().getColor(id, context.getTheme());
        } else {
            return context.getResources().getColor(id);
        }
    }

    //**************************
    public static int getRandomNumber(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }
    //**************************
/*
    public static void addContact(Context context, String firstName, String lastName, String phoneNumber) {
        ArrayList<ContentProviderOperation> operationList = new ArrayList<ContentProviderOperation>();
        operationList.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

        // first and last names
        operationList.add(ContentProviderOperation.newInsert(ContactsContract.DataAddOptions.CONTENT_URI)
                .withValueBackReference(ContactsContract.DataAddOptions.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.DataAddOptions.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, firstName)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, lastName)
                .build());

        operationList.add(ContentProviderOperation.newInsert(ContactsContract.DataAddOptions.CONTENT_URI)
                .withValueBackReference(ContactsContract.DataAddOptions.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.DataAddOptions.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
                .build());
//        operationList.add(ContentProviderOperation.newInsert(ContactsContract.DataAddOptions.CONTENT_URI)
//                .withValueBackReference(ContactsContract.DataAddOptions.RAW_CONTACT_ID, 0)
//
//                .withValue(ContactsContract.DataAddOptions.MIMETYPE,ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
//                .withValue(ContactsContract.CommonDataKinds.Email.DATA, emailAddress)
//                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
//                .build());

        try {
            ContentProviderResult[] results = context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, operationList);
            Toast.makeText(context, context.getResources().getString(R.string.contact_saved), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, context.getResources().getString(R.string.contact_not_saved), Toast.LENGTH_SHORT).show();
        }
    }

    */


    //***********************************************


    public static String getRelativeTime(String oldTimeInMilli) {

        String relativeTime = DateUtils.getRelativeTimeSpanString(Long.parseLong(oldTimeInMilli), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS) + "";

        return relativeTime;
//        if(relativeTime.equals("0 minutes ago") || relativeTime.equals("0 minute ago"))
//        {
//            relativeTime = "few sec";
//        }else if(relativeTime.contains("minutes ago") || relativeTime.contains("minute ago"))
//        {
//            relativeTime =  relativeTime.replace("minutes ago", "min");
//        }else if(relativeTime.contains("hour ago") || relativeTime.contains("hours ago"))
//        {
//
//            String[] out = relativeTime.split(" ");
//            relativeTime = out[0]+" hr";
//
//        }else if(relativeTime.contains("day ago") || relativeTime.contains("days ago"))
//        {
//
//            String[] out = relativeTime.split(" ");
//            relativeTime = out[0]+" hr";
//
//        }
//        else {
//
//
//            String  tempTime = relativeTime = relativeTime.replace(",","");
//            String[] out = tempTime.split(" ");
//
//            Calendar calendar = Calendar.getInstance();
//            int year = calendar.get(Calendar.YEAR);
//
//            if(year==Integer.parseInt(out[3]))
//            {
//                relativeTime =  tempTime =  out[0]+" "+out[1];
//            }
//
//        }
//        return relativeTime;
    }


    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideKeyboardOnActivityLaunch(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }


    public void doDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
            }
        }, 500);
    }


    public static boolean isAppInstalled(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }

    public static void showLog(String key, String message) {
        Log.e(key, message);
    }

    public static void showLogMessage(String message) {
        Log.e("WaseetAppLog", message);
    }

    public static void showLogD(String key, String message) {
        Log.d(key, message);
    }

//    public static void initFirebaseInstancedService(Context context) {
//        context.startService(new Intent(context, FirebaseInstanceIdService.class));
//    }


    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

//    Uri file_uri = Uri.parse(videoURI); // parse to Uri if your videoURI is string
//    String real_path = file_uri.getPath();


    public static String getPath(Context context, Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        int column_index = 0;
        if (cursor != null) {
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        } else
            return uri.getPath();
    }


    public static String getFileFromBitmap(Context context, Bitmap bitmap) {
        String folderPath = Environment.getExternalStorageDirectory() + File.separator + context.getString(R.string.app_name) + File.separator;
        File file = new File(folderPath);

        if (!file.exists()) {
            file.mkdir();
        }

        File imageFile =
                new File(folderPath,
                        System.currentTimeMillis() + ".jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.e("TAG", "Error writing bitmap", e);
        }
        return imageFile.getPath();
    }


    public static double getOptimalDoubleValue(String value) {
        if (value.length() > 10) {
            String[] dataToSplit = value.split("\\.");

            String item_0, item_1;

            item_0 = dataToSplit[0];
            item_1 = dataToSplit[1].substring(0, 7);
            item_0 = item_0 + "." + item_1;

            return Double.parseDouble(item_0);
        } else {
            return Double.parseDouble(value);
        }

    }

    public static Bitmap rotateImageIfNeed(Context context, Uri uri) {
        String filePath = getPath(context, uri);
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // check the rotation of the image and display it properly

        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,
                    true);

            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getPathFromUri(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


    public static boolean isLebanon(Context context) {
        if (((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE))
                .getNetworkCountryIso().equalsIgnoreCase("lb"))
            return true;
        else
            return false;
    }


}
