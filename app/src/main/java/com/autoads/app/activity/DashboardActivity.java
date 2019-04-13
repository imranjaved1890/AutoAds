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

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title)
    AppCompatTextView titleTv;

    @BindView(R.id.reg_btn)
    AppCompatButton regVehBtn;

    @BindView(R.id.view_veh_btn)
    AppCompatButton viewVehBtn;

    @BindView(R.id.profile_btn)
    AppCompatButton profileBtn;

    @BindView(R.id.post_ad_btn)
    AppCompatButton postAdBtn;

    @BindView(R.id.sign_out_btn)
    AppCompatButton signOutBtn;

    private MyPreferences myPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        myPreferences = new MyPreferences(DashboardActivity.this);

        setListener();
        setUpToolbar();

    }


    private void setUpToolbar() {
        titleTv.setText(getResources().getString(R.string.home));
        setSupportActionBar(toolbar);
    }


    private void setListener() {
        regVehBtn.setOnClickListener(this);
        viewVehBtn.setOnClickListener(this);
        profileBtn.setOnClickListener(this);
        postAdBtn.setOnClickListener(this);
        signOutBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_btn:
                Intent regVehIntent = new Intent(DashboardActivity.this, RegVehicleActivity.class);
                regVehIntent.putExtra(AppConstants.KEY_ID, myPreferences.getUserId());
                startActivity(regVehIntent);
                break;


            case R.id.view_veh_btn:
                Intent viewVehIntent = new Intent(DashboardActivity.this, UserRegVehicleActivity.class);
                startActivity(viewVehIntent);
                break;


            case R.id.profile_btn:
                Intent profileIntent = new Intent(DashboardActivity.this, UserProfileActivity.class);
                startActivity(profileIntent);
                break;


            case R.id.post_ad_btn:
                Toast.makeText(this, "Comming Soon", Toast.LENGTH_SHORT).show();
                break;


            case R.id.sign_out_btn:
                if (FirebaseAuth.getInstance().getCurrentUser() != null)
                    FirebaseAuth.getInstance().signOut();

                myPreferences.clearPreferences();

                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();


                break;

        }
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                // app icon in action bar clicked; goto parent activity.
//                this.finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
