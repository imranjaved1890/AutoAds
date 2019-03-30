package com.autoads.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.autoads.app.R;
import com.autoads.app.preferences.MyPreferences;
import com.autoads.app.retrofit.APIClient;
import com.autoads.app.retrofit.APIInterface;
import com.autoads.app.util.MyDialog;
import com.autoads.app.util.MyUtil;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title)
    AppCompatTextView titleTv;

    @BindView(R.id.fistname_et)
    AppCompatEditText firstNameEt;

    @BindView(R.id.last_name_et)
    AppCompatEditText lastNameEt;

    @BindView(R.id.email_et)
    AppCompatEditText emailEt;

    @BindView(R.id.mobile_et)
    AppCompatEditText mobileEt;

    @BindView(R.id.pwd_et)
    AppCompatEditText pwdEt;

    @BindView(R.id.sing_up)
    AppCompatButton singUpBtn;

    private APIInterface apiInterface;
    private MyDialog myDialog;
    private MyPreferences myPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        myDialog = new MyDialog(SignUpActivity.this);
        myPreferences = new MyPreferences(SignUpActivity.this);


        setListener();
        setUpToolbar();

    }


    private void setUpToolbar() {
//        titleTv.setText(getResources().getString(R.string.sign_up));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_back_b));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void setListener() {
        singUpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sing_up:
                if (MyUtil.isNetworkAvailable(SignUpActivity.this)) {
                    if (validateFields()) {


//                        String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

                        String number = mobileEt.getText().toString().trim();

                        if (number.isEmpty() || number.length() < 10) {
                            mobileEt.setError("Valid number is required");
                            mobileEt.requestFocus();
                            return;
                        }

//                        String phoneNumber = "+" + code + number;

                        Intent intent = new Intent(SignUpActivity.this, PinVerificationActivity.class);
                        intent.putExtra("phonenumber", number);
                        startActivity(intent);

//                        signUp(emailEt.getText().toString(), mobileEt.getText().toString(), pwdEt.getText().toString()
//                                , firstNameEt.getText().toString(), lastNameEt.getText().toString());
                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.internet_connection_not_available), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private boolean validateFields() {
        if (firstNameEt.getText().toString().isEmpty()) {
            firstNameEt.setError(getResources().getString(R.string.please_enter_first_name));
            return false;
        } else if (lastNameEt.getText().toString().isEmpty()) {
            lastNameEt.setError(getResources().getString(R.string.please_enter_last_name));
            return false;
        } else if (emailEt.getText().toString().isEmpty()) {
            emailEt.setError(getResources().getString(R.string.please_enter_valid_email_username));
            return false;
        } else if (!MyUtil.isEmailValid(emailEt.getText().toString())) {
            emailEt.setError(getResources().getString(R.string.please_enter_valid_email_username));
            return false;
        } else if (mobileEt.getText().toString().isEmpty()) {
            mobileEt.setError(getResources().getString(R.string.please_enter_valid_mobile_number));
            return false;
        } else if (pwdEt.getText().toString().isEmpty()) {
            pwdEt.setError(getResources().getString(R.string.please_enter_password));
            return false;
        }
        emailEt.setError(null);
        mobileEt.setError(null);
        pwdEt.setError(null);

        return true;
    }

//    private void signUp(String emailStr, String mobileStr, String pwdStr, String firstNameStr, String lastNameStr) {
//        myDialog.show();
//        myDialog.setMessage(getResources().getString(R.string.loading)); // always call after myDialog.show();
//
//        Call<SignUpLoginResponseModel> call = apiInterface.doSignUp(emailStr, pwdStr, mobileStr, firstNameStr, lastNameStr);
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
//                        Toast.makeText(SignUpActivity.this, getResources().getString(R.string.user_successfully_registered), Toast.LENGTH_SHORT).show();
//                        finish();
//                    } else {
//                        MyUtil.showAlertDialog(SignUpActivity.this, "", signUpResponseModel.getData().getMessage(), getResources().getString(R.string.ok), "");
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SignUpLoginResponseModel> call, Throwable t) {
//                call.cancel();
//                MyUtil.showLog("Error", t.getMessage() + "");
//                myDialog.dismiss();
//                Toast.makeText(SignUpActivity.this, getResources().getString(R.string.api_failed_msg), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

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
}
