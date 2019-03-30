package com.autoads.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.autoads.app.R;
import com.autoads.app.preferences.MyPreferences;
import com.autoads.app.retrofit.APIClient;
import com.autoads.app.retrofit.APIInterface;
import com.autoads.app.util.MyDialog;
import com.autoads.app.util.MyUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PinVerificationActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title)
    AppCompatTextView titleTv;

    @BindView(R.id.resend_tv)
    AppCompatTextView resendTv;

    @BindView(R.id.pin_one)
    AppCompatEditText pinCodeEt;


    @BindView(R.id.verify_now_btn)
    AppCompatButton verifyNowBtn;


    private MyPreferences myPreferences;
    private APIInterface apiInterface;
    private MyDialog myDialog;


    private String verificationId;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_verification);
        ButterKnife.bind(this);

        setUpToolbar();
        init();
        setListener();

        apiInterface = APIClient.getClient().create(APIInterface.class);
        myDialog = new MyDialog(PinVerificationActivity.this);
        myPreferences = new MyPreferences(PinVerificationActivity.this);


        String phonenumber = getIntent().getStringExtra("phonenumber");

        Log.e("phonenumber", phonenumber + "");
        sendVerificationCode(phonenumber);


    }


    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(PinVerificationActivity.this, ProfileActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(PinVerificationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            Log.e("msg", task.getException().getMessage() + "");
                        }
                    }
                });
    }

    private void sendVerificationCode(String number) {
//        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                pinCodeEt.setText(code);
                verifyCode(code);
            }
        }


        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(PinVerificationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("msg", e.getMessage() + "");
        }
    };


    private void setUpToolbar() {
//        titleTv.setText(getResources().getString(R.string.verification));
        titleTv.setTextColor(MyUtil.getColor(PinVerificationActivity.this, R.color.black));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init() {

        mAuth = FirebaseAuth.getInstance();
        //------------------
    }


    //*******************************************************************
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void setListener() {
        resendTv.setOnClickListener(this);
        verifyNowBtn.setOnClickListener(this);
    }

    private String getPinCode() {
        return pinCodeEt.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resend_tv:
//                MyUtil.showAlertDialog(PinVerificationActivity.this, "", myPreferences.getOtpCode() + "", getResources().getString(R.string.ok), "");

                break;
            case R.id.verify_now_btn:
                if (MyUtil.isNetworkAvailable(PinVerificationActivity.this)) {
                    if (validateFields()) {
//                        verifyPin(myPreferences.getUserName(), getPinCode());
                        String code = getPinCode();

                        if (code.isEmpty() || code.length() < 6) {
                            Toast.makeText(PinVerificationActivity.this, "Please enter code", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        verifyCode(code);
                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.internet_connection_not_available), Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private boolean validateFields() {

        if (pinCodeEt.getText().toString().isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.please_enter_valid_pin), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

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
//                        if (signUpResponseModel.getData().getOtpStatus() == 0) {
//                            intent = new Intent(PinVerificationActivity.this, PinVerificationActivity.class);
//                        } else {
//                            intent = new Intent(PinVerificationActivity.this, SelectLocationModeActivity.class);
//
//                        }
//
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                        finish();
//
//
//                    } else {
//                        MyUtil.showAlertDialog(PinVerificationActivity.this, "", signUpResponseModel.getData().getMessage(), getResources().getString(R.string.ok), "");
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SignUpLoginResponseModel> call, Throwable t) {
//                call.cancel();
//                MyUtil.showLog("Error", t.getMessage() + "");
//                myDialog.dismiss();
//                Toast.makeText(PinVerificationActivity.this, getResources().getString(R.string.api_failed_msg), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

}