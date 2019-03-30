package com.autoads.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.autoads.app.R;
import com.autoads.app.preferences.MyPreferences;
import com.autoads.app.retrofit.APIClient;
import com.autoads.app.retrofit.APIInterface;
import com.autoads.app.util.MyDialog;
import com.autoads.app.util.MyUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.email_et)
    AppCompatEditText emailEt;

    @BindView(R.id.pwd_et)
    AppCompatEditText pwdEt;


    @BindView(R.id.sign_up_tv)
    AppCompatTextView signUpTv;


    @BindView(R.id.login)
    AppCompatButton loginBtn;

    private MyPreferences myPreferences;
    private APIInterface apiInterface;
    private MyDialog myDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        myDialog = new MyDialog(LoginActivity.this);
        myPreferences = new MyPreferences(LoginActivity.this);

        setListener();

    }


    private void setListener() {
        loginBtn.setOnClickListener(this);
        signUpTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_up_tv:
                Intent singUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(singUpIntent);
                break;
            case R.id.login:
                if (MyUtil.isNetworkAvailable(LoginActivity.this)) {
                    if (validateFields()) {

                        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();


//                        doLogin(emailEt.getText().toString(), pwdEt.getText().toString());
                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.internet_connection_not_available), Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }

    private boolean validateFields() {

        if (emailEt.getText().toString().isEmpty()) {
            emailEt.setError(getResources().getString(R.string.please_enter_valid_email_username));
            return false;
        } else if (pwdEt.getText().toString().isEmpty()) {
            pwdEt.setError(getResources().getString(R.string.please_enter_password));
            return false;
        }
        emailEt.setError(null);
        pwdEt.setError(null);

        return true;
    }

//    private void doLogin(String emailStr, String pwdStr) {
//        myDialog.show();
//        myDialog.setMessage(getResources().getString(R.string.loading)); // always call after myDialog.show();
//
//        Call<SignUpLoginResponseModel> call = apiInterface.doLogin(emailStr, pwdStr);
//        call.enqueue(new Callback<SignUpLoginResponseModel>() {
//            @Override
//            public void onResponse(Call<SignUpLoginResponseModel> call, Response<SignUpLoginResponseModel> response) {
//
//                myDialog.dismiss();
//
//                if (response != null) {
//                    SignUpLoginResponseModel signUpResponseModel = response.body();
//                    if (signUpResponseModel.getSuccess()) {
//                        myPreferences.setUserId(signUpResponseModel.getData().getId());
//                        myPreferences.setOtpCode(signUpResponseModel.getData().getOtpCode() + "");
//                        myPreferences.setUserName(signUpResponseModel.getData().getUsername() + "");
//                        myPreferences.setOtpStatus(signUpResponseModel.getData().getOtpStatus());
//                        myPreferences.setLoggedIn(true);
//                        myPreferences.setCustomerLogin(true);
//
//                        Intent intent;
//                        if (signUpResponseModel.getData().getOtpStatus() == 0) {
////                            intent = new Intent(LoginActivity.this, PinVerificationActivity.class);
////                            startActivity(intent);
//                            verifyPin(myPreferences.getUserName(), signUpResponseModel.getData().getOtpCode()+"" );
//                        } else {
////                            if(myPreferences.getUserSelectedLat().equals("0.0") || myPreferences.getUserSelectedLng().equals("0.0")){
//                            intent = new Intent(LoginActivity.this, SelectLocationModeActivity.class);
////                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);
//                            finish();
////                            }
//
//                        }
//
//
//                    } else {
//                        MyUtil.showAlertDialog(LoginActivity.this, "", signUpResponseModel.getData().getMessage(), getResources().getString(R.string.ok), "");
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SignUpLoginResponseModel> call, Throwable t) {
//                call.cancel();
//                MyUtil.showLog("Error", t.getMessage() + "");
//                myDialog.dismiss();
//                Toast.makeText(LoginActivity.this, getResources().getString(R.string.api_failed_msg), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


//    private void verifyPin(String userNameStr, String pinStr) {
//        myDialog.show();
//        myDialog.setMessage(getResources().getString(R.string.loading)); // always call after myDialog.show();
//
//        Call<SignUpLoginResponseModel> call = apiInterface.verifyPin(userNameStr, pinStr);
//        call.enqueue(new Callback<SignUpLoginResponseModel>() {
//            @Override
//            public void onResponse(Call<SignUpLoginResponseModel> call, Response<SignUpLoginResponseModel> response) {
//
//                myDialog.dismiss();
//
//                if (response != null) {
//                    SignUpLoginResponseModel signUpResponseModel = response.body();
//                    if (signUpResponseModel.getSuccess()) {
//
//                        myPreferences.setOtpStatus(signUpResponseModel.getData().getOtpStatus());
//
//                        Intent intent;
////                        if (signUpResponseModel.getData().getOtpStatus() == 0) {
////                            intent = new Intent(LoginActivity.this, PinVerificationActivity.class);
////                        } else {
////                            intent = new Intent(LoginActivity.this, SelectLocationModeActivity.class);
////
////                        }
//                        intent = new Intent(LoginActivity.this, SelectLocationModeActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                        finish();
//
//
//                    } else {
//                        MyUtil.showAlertDialog(LoginActivity.this, "", signUpResponseModel.getData().getMessage(), getResources().getString(R.string.ok), "");
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SignUpLoginResponseModel> call, Throwable t) {
//                call.cancel();
//                MyUtil.showLog("Error", t.getMessage() + "");
//                myDialog.dismiss();
//                Toast.makeText(LoginActivity.this, getResources().getString(R.string.api_failed_msg), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


}
