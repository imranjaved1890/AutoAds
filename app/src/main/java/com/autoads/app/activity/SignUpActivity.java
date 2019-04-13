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
import com.autoads.app.model.ResponseModelForSignUp;
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

    @BindView(R.id.sign_in_tv)
    AppCompatTextView sign_in_tv;

    @BindView(R.id.fistname_et)
    AppCompatEditText firstNameEt;

    @BindView(R.id.last_name_et)
    AppCompatEditText lastNameEt;

    @BindView(R.id.email_et)
    AppCompatEditText emailEt;


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


        if (myPreferences.getUserPhone().equals("")) {

            Intent intent = new Intent(SignUpActivity.this, PinVerificationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();

        }

    }


    private void setUpToolbar() {
        titleTv.setText(getResources().getString(R.string.sign_up));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_back_b));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void setListener() {
        singUpBtn.setOnClickListener(this);
        sign_in_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sing_up:
                if (MyUtil.isNetworkAvailable(SignUpActivity.this)) {
                    if (validateFields()) {

                        signUp(firstNameEt.getText().toString(), lastNameEt.getText().toString(), emailEt.getText().toString()
                                , myPreferences.getUserPhone(), pwdEt.getText().toString());
                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.internet_connection_not_available), Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.sign_in_tv:

                Intent logoutIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(logoutIntent);
                finish();
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
        } else if (pwdEt.getText().toString().isEmpty()) {
            pwdEt.setError(getResources().getString(R.string.please_enter_password));
            return false;
        }
        firstNameEt.setError(null);
        lastNameEt.setError(null);
        emailEt.setError(null);
        pwdEt.setError(null);

        return true;
    }

    private void signUp(String firstNameStr, String lastNameStr, String emailStr, String mobileStr, String pwdStr) {
        myDialog.show();
        myDialog.setMessage(getResources().getString(R.string.loading)); // always call after myDialog.show();

        Call<ResponseModelForSignUp> call = apiInterface.doSignUp(1, firstNameStr, lastNameStr,
                emailStr, mobileStr, pwdStr, 1); //user type hardcoded temporarily

        call.enqueue(new Callback<ResponseModelForSignUp>() {
            @Override
            public void onResponse(Call<ResponseModelForSignUp> call, Response<ResponseModelForSignUp> response) {

                myDialog.dismiss();

                if (response != null) {
                    ResponseModelForSignUp signUpResponseModel = response.body();
                    if (signUpResponseModel.getError()) {
                        if (signUpResponseModel.getMsg().equalsIgnoreCase("already exists")) {
                            Toast.makeText(SignUpActivity.this, "The user already exists", Toast.LENGTH_LONG).show();
                            Intent logoutIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                            logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(logoutIntent);
                            finish();
                        } else {
                            Toast.makeText(SignUpActivity.this, "Could not register, Please try again", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(SignUpActivity.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
                        Intent logoutIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                        logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(logoutIntent);
                        finish();

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModelForSignUp> call, Throwable t) {
                call.cancel();
                MyUtil.showLog("Error", t.getMessage() + "");
                myDialog.dismiss();
                Toast.makeText(SignUpActivity.this, getResources().getString(R.string.api_failed_msg), Toast.LENGTH_SHORT).show();
            }
        });
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
