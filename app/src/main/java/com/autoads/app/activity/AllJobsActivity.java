package com.autoads.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.autoads.app.R;
import com.autoads.app.constants.AppConstants;
import com.autoads.app.preferences.MyPreferences;
import com.autoads.app.util.MyUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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

public class AllJobsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title)
    AppCompatTextView titleTv;

    private MyPreferences myPreferences;
    private RecyclerView recyclerView;



    private String verificationId;
    private FirebaseAuth mAuth;
    private String phoneNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_jobs);
        ButterKnife.bind(this);

        myPreferences = new MyPreferences(AllJobsActivity.this);

        setUpToolbar();
        init();
        setListener();

        phoneNumber = getIntent().getStringExtra(AppConstants.KEY_PHONE);

        MyUtil.showLog(AppConstants.KEY_PHONE, phoneNumber + "");
        sendVerificationCode(phoneNumber);

    }


    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AllJobsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            if (getIntent().hasExtra(AppConstants.KEY_PHONE)) {
                                if (!getIntent().getStringExtra(AppConstants.KEY_PHONE).equals("")) {
                                    myPreferences.setUserPhone(phoneNumber);
                                    myPreferences.setMobileVerified(true);

                                    Intent intent = new Intent(AllJobsActivity.this, SignUpActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();

                                }
                            }


                        } else {
                            Toast.makeText(AllJobsActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            MyUtil.showLog("msg", task.getException().getMessage() + "");
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
            Toast.makeText(AllJobsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            MyUtil.showLog("msg", e.getMessage() + "");
        }
    };


    private void setUpToolbar() {
//        titleTv.setText(getResources().getString(R.string.verification));
        titleTv.setTextColor(MyUtil.getColor(AllJobsActivity.this, R.color.black));
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
                if (MyUtil.isNetworkAvailable(AllJobsActivity.this)) {
                    if (validateFields()) {
//                        verifyPin(myPreferences.getUserName(), getPinCode());
                        String code = getPinCode();

                        if (code.isEmpty() || code.length() < 6) {
                            Toast.makeText(AllJobsActivity.this, "Please enter code", Toast.LENGTH_SHORT).show();
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

}
