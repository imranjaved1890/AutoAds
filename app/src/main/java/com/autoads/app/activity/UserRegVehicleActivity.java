package com.autoads.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.autoads.app.R;
import com.autoads.app.adapter.AdapterForRegVeh;
import com.autoads.app.constants.AppConstants;
import com.autoads.app.model.ResponseModelForVehReg;
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

public class UserRegVehicleActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title)
    AppCompatTextView titleTv;

    @BindView(R.id.error_tv)
    AppCompatTextView errorTv;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    AdapterForRegVeh adapterForRegVeh;
    private List<DataForRegVeh> regVehList;


    private APIInterface apiInterface;
    private MyDialog myDialog;
    private MyPreferences myPreferences;

    private int userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registered_veh);
        ButterKnife.bind(this);

        regVehList = new ArrayList<DataForRegVeh>();
        apiInterface = APIClient.getClient().create(APIInterface.class);
        myDialog = new MyDialog(UserRegVehicleActivity.this);
        myPreferences = new MyPreferences(UserRegVehicleActivity.this);

        userID = getIntent().getIntExtra(AppConstants.KEY_ID, -5);

        setUpToolbar();


        if (MyUtil.isNetworkAvailable(UserRegVehicleActivity.this)) {
            getRegisteredVeh(myPreferences.getUserId());
        } else {
            errorTv.setVisibility(View.VISIBLE);
            errorTv.setText(getResources().getString(R.string.internet_connection_not_available));
        }


    }

    private void setUpToolbar() {
        titleTv.setText(getResources().getString(R.string.reg_veh));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_back_b));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void getRegisteredVeh(Integer userId) {
        myDialog.show();
        myDialog.setMessage(getResources().getString(R.string.loading)); // always call after myDialog.show();
        errorTv.setVisibility(View.GONE);

        Call<ResponseModelForRegisteredVehicle> call = apiInterface.getRegisteredVehicle(1, userId);
        call.enqueue(new Callback<ResponseModelForRegisteredVehicle>() {
            @Override
            public void onResponse(Call<ResponseModelForRegisteredVehicle> call, Response<ResponseModelForRegisteredVehicle> response) {

                myDialog.dismiss();

                if (response != null) {
                    ResponseModelForRegisteredVehicle responseModelForRegisteredVehicle = response.body();
                    if (responseModelForRegisteredVehicle.getError()) {
                        Toast.makeText(UserRegVehicleActivity.this, "could not register vehicle. Please try again", Toast.LENGTH_SHORT).show();
                    } else {

                        if (responseModelForRegisteredVehicle.getData().size() > 0) {
                            errorTv.setVisibility(View.GONE);

                            for (int i = 0; i < responseModelForRegisteredVehicle.getData().size(); i++) {
                                regVehList.add(responseModelForRegisteredVehicle.getData().get(i));
                            }

                            adapterForRegVeh = new AdapterForRegVeh(UserRegVehicleActivity.this, regVehList);
                            recyclerView.setAdapter(adapterForRegVeh);
                        } else {
                            errorTv.setVisibility(View.VISIBLE);
                            errorTv.setText(getResources().getString(R.string.no_data_found));
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModelForRegisteredVehicle> call, Throwable t) {
                call.cancel();
                MyUtil.showLog("Error", t.getMessage() + "");
                myDialog.dismiss();
                Toast.makeText(UserRegVehicleActivity.this, getResources().getString(R.string.api_failed_msg), Toast.LENGTH_SHORT).show();
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
