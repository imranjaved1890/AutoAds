package com.autoads.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.autoads.app.R;
import com.autoads.app.adapter.AdapterForRegVeh;
import com.autoads.app.constants.AppConstants;
import com.autoads.app.model.ResponseModelForUserProfile;
import com.autoads.app.model.responseModelForRegisteredVeh.DataForRegVeh;
import com.autoads.app.model.responseModelForRegisteredVeh.ResponseModelForRegisteredVehicle;
import com.autoads.app.preferences.MyPreferences;
import com.autoads.app.retrofit.APIClient;
import com.autoads.app.retrofit.APIInterface;
import com.autoads.app.util.MyDialog;
import com.autoads.app.util.MyUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title)
    AppCompatTextView titleTv;

    @BindView(R.id.f_name_tv)
    AppCompatTextView f_name_tv;

    @BindView(R.id.l_name_tv)
    AppCompatTextView l_name_tv;

    @BindView(R.id.email_tv)
    AppCompatTextView email_tv;

    @BindView(R.id.mobile_tv)
    AppCompatTextView mobile_tv;


    private APIInterface apiInterface;
    private MyDialog myDialog;
    private MyPreferences myPreferences;

    private int userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        myDialog = new MyDialog(UserProfileActivity.this);
        myPreferences = new MyPreferences(UserProfileActivity.this);

        userID = getIntent().getIntExtra(AppConstants.KEY_ID, -5);

        setUpToolbar();


        if (MyUtil.isNetworkAvailable(UserProfileActivity.this)) {
            getUserData(myPreferences.getUserPhone());
        }

    }

    private void setUpToolbar() {
        titleTv.setText(getResources().getString(R.string.profile));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_back_b));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void getUserData(String userMobileNumber) {
        myDialog.show();
        myDialog.setMessage(getResources().getString(R.string.loading)); // always call after myDialog.show();

        Call<ResponseModelForUserProfile> call = apiInterface.getUser(1, userMobileNumber);
        call.enqueue(new Callback<ResponseModelForUserProfile>() {
            @Override
            public void onResponse(Call<ResponseModelForUserProfile> call, Response<ResponseModelForUserProfile> response) {

                myDialog.dismiss();

                if (response != null) {
                    ResponseModelForUserProfile responseModelForUserProfile = response.body();
                    if (responseModelForUserProfile.getError()) {
                        Toast.makeText(UserProfileActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                    } else {

                        f_name_tv.setText(getResources().getString(R.string.first_name) + " : " + responseModelForUserProfile.getData().getFName());
                        l_name_tv.setText(getResources().getString(R.string.last_name) + " : " + responseModelForUserProfile.getData().getLName());
                        email_tv.setText(getResources().getString(R.string.email) + " : " + responseModelForUserProfile.getData().getEmail());
                        mobile_tv.setText(getResources().getString(R.string.mobile) + " : " + responseModelForUserProfile.getData().getMobile());

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModelForUserProfile> call, Throwable t) {
                call.cancel();
                MyUtil.showLog("Error", t.getMessage() + "");
                myDialog.dismiss();
                Toast.makeText(UserProfileActivity.this, getResources().getString(R.string.api_failed_msg), Toast.LENGTH_SHORT).show();
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
