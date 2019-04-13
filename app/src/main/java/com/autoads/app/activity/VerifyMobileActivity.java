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
import com.autoads.app.constants.AppConstants;
import com.autoads.app.preferences.MyPreferences;
import com.autoads.app.retrofit.APIClient;
import com.autoads.app.retrofit.APIInterface;
import com.autoads.app.util.MyDialog;
import com.autoads.app.util.MyUtil;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerifyMobileActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.mobile_et)
    AppCompatEditText mobileEt;

    @BindView(R.id.submit_btn)
    AppCompatButton submitBtn;

    private MyPreferences myPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verification);
        ButterKnife.bind(this);

        myPreferences = new MyPreferences(VerifyMobileActivity.this);

        setListener();

    }



    private void setListener() {
        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_btn:
                if (MyUtil.isNetworkAvailable(VerifyMobileActivity.this)) {
                    if (validateFields()) {


                        String number = mobileEt.getText().toString().trim();

                        Intent intent = new Intent(VerifyMobileActivity.this, PinVerificationActivity.class);
                        intent.putExtra(AppConstants.KEY_PHONE, number);
                        startActivity(intent);

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
        } else if (mobileEt.getText().toString().length() < 10) {
            mobileEt.setError(getResources().getString(R.string.please_enter_valid_mobile_number));
            return false;
        }
        mobileEt.setError(null);

        return true;
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
