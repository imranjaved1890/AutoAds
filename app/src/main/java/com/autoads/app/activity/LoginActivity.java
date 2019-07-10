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
import com.autoads.app.model.ResponseModelForUserLogin;
import com.autoads.app.preferences.MyPreferences;
import com.autoads.app.retrofit.APIClient;
import com.autoads.app.retrofit.APIInterface;
import com.autoads.app.util.MyDialog;
import com.autoads.app.util.MyUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.mobile_et)
    AppCompatEditText mobileEt;

    @BindView(R.id.pwd_et)
    AppCompatEditText pwdEt;

    @BindView(R.id.sign_up_tv)
    AppCompatTextView signUpTv;

    @BindView(R.id.verify_now_btn)
    AppCompatTextView verify_now_btn;

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
        verify_now_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_up_tv:
                Intent singUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(singUpIntent);
                break;
            case R.id.verify_now_btn:

                Intent intent = new Intent(LoginActivity.this, VerifyMobileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();


                break;
            case R.id.login:
                if (MyUtil.isNetworkAvailable(LoginActivity.this)) {
                    if (validateFields()) {

                        doLogin(mobileEt.getText().toString(), pwdEt.getText().toString());
                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.internet_connection_not_available), Toast.LENGTH_SHORT).show();
                }
                break;


        }

    }

    private boolean validateFields() {

        if (mobileEt.getText().toString().isEmpty()) {
            mobileEt.setError(getResources().getString(R.string.please_enter_valid_mobile_number));
            return false;
        } else if (pwdEt.getText().toString().isEmpty()) {
            pwdEt.setError(getResources().getString(R.string.please_enter_password));
            return false;
        }
        mobileEt.setError(null);
        pwdEt.setError(null);

        return true;
    }

    private void doLogin(String mobileStr, String pwdStr) {
        myDialog.show();
        myDialog.setMessage(getResources().getString(R.string.loading)); // always call after myDialog.show();

        Call<ResponseModelForUserLogin> call = apiInterface.doLogin(1, mobileStr, pwdStr);
        call.enqueue(new Callback<ResponseModelForUserLogin>() {
            @Override
            public void onResponse(Call<ResponseModelForUserLogin> call, Response<ResponseModelForUserLogin> response) {

                myDialog.dismiss();

                if (response != null) {
                    ResponseModelForUserLogin signUpResponseModel = response.body();
                    if (signUpResponseModel.getError()) {
                        Toast.makeText(LoginActivity.this, "Could not sign in, Please recheck your credentials.", Toast.LENGTH_SHORT).show();
                    } else {

                        myPreferences.setUserId(Integer.parseInt(signUpResponseModel.getData().getId()));
                        myPreferences.setUserPhone(signUpResponseModel.getData().getMobile() + "");
                        myPreferences.setUserEmail(signUpResponseModel.getData().getEmail() + "");

                        myPreferences.setLoggedIn(true);

                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModelForUserLogin> call, Throwable t) {
                call.cancel();
                MyUtil.showLog("Error", t.getMessage() + "");
                myDialog.dismiss();
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.api_failed_msg), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
