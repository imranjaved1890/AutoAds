package com.autoads.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.autoads.app.R;
import com.autoads.app.constants.AppConstants;
import com.autoads.app.model.ResponseModelForVehReg;
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

public class RegVehicleActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title)
    AppCompatTextView titleTv;

    @BindView(R.id.veh_make_et)
    AppCompatEditText vehMakeEt;

    @BindView(R.id.veh_model)
    AppCompatEditText vehModelEt;

    @BindView(R.id.veh_make_year)
    AppCompatEditText vehMakeYearEt;

    @BindView(R.id.veh_reg_number)
    AppCompatEditText vehRegNumberEt;

    @BindView(R.id.veh_color)
    AppCompatEditText vehColorEt;

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    @BindView(R.id.sedan)
    AppCompatRadioButton sedan;

    @BindView(R.id.hatchback)
    AppCompatRadioButton hatchback;

    @BindView(R.id.van)
    AppCompatRadioButton van;

    @BindView(R.id.suv)
    AppCompatRadioButton suv;

    @BindView(R.id.jeep)
    AppCompatRadioButton jeep;


    @BindView(R.id.submit_btn)
    AppCompatButton submitBtn;

    private APIInterface apiInterface;
    private MyDialog myDialog;
    private MyPreferences myPreferences;

    private int userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_veh);
        ButterKnife.bind(this);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        myDialog = new MyDialog(RegVehicleActivity.this);
        myPreferences = new MyPreferences(RegVehicleActivity.this);

        userID = getIntent().getIntExtra(AppConstants.KEY_ID, -5);

        setListener();
        setUpToolbar();

    }

    private void setUpToolbar() {
        titleTv.setText(getResources().getString(R.string.reg_veh));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_back_b));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setListener() {
        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_btn:
                if (MyUtil.isNetworkAvailable(RegVehicleActivity.this)) {
                    if (validateFields()) {

                        // get selected radio button from radioGroup
                        int selectedId = radioGroup.getCheckedRadioButtonId();

                        // find the radiobutton by returned id
                        AppCompatRadioButton radioButton = (AppCompatRadioButton) findViewById(selectedId);

                        regVehicle(userID, radioButton.getText().toString(), vehRegNumberEt.getText().toString(),
                                vehColorEt.getText().toString(), vehModelEt.getText().toString(), vehMakeEt.getText().toString(),
                                vehMakeYearEt.getText().toString());

                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.internet_connection_not_available), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private boolean validateFields() {
        if (vehMakeEt.getText().toString().isEmpty()) {
            vehMakeEt.setError("Please enter vehicle Make");
            return false;
        } else if (vehModelEt.getText().toString().isEmpty()) {
            vehModelEt.setError("Please enter vehicle model");
            return false;
        } else if (vehMakeYearEt.getText().toString().isEmpty()) {
            vehMakeYearEt.setError("Please enter vehicle Make Year");
            return false;
        } else if (vehRegNumberEt.getText().toString().isEmpty()) {
            vehRegNumberEt.setError("Please enter vehicle Registration number");
            return false;
        } else if (vehColorEt.getText().toString().isEmpty()) {
            vehColorEt.setError("Please enter vehicle color");
            return false;
        }
        vehMakeEt.setError(null);
        vehModelEt.setError(null);
        vehMakeYearEt.setError(null);
        vehRegNumberEt.setError(null);
        vehColorEt.setError(null);

        return true;
    }


    private void regVehicle(Integer userId, String vehicleType, String vehicleRegNo, String vehicleColor, String vehicleModel,
                            String vehicleMake, String vehicleMakeYear) {
        myDialog.show();
        myDialog.setMessage(getResources().getString(R.string.loading)); // always call after myDialog.show();

        Call<ResponseModelForVehReg> call = apiInterface.regVehicle(1, userId, vehicleType, vehicleRegNo,
                vehicleColor, vehicleModel, vehicleMake, vehicleMakeYear);
        call.enqueue(new Callback<ResponseModelForVehReg>() {
            @Override
            public void onResponse(Call<ResponseModelForVehReg> call, Response<ResponseModelForVehReg> response) {

                myDialog.dismiss();

                if (response != null) {
                    ResponseModelForVehReg responseModelForVehReg = response.body();
                    if (responseModelForVehReg.getError()) {
                        Toast.makeText(RegVehicleActivity.this, "could not register vehicle. Please try again", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegVehicleActivity.this, "Vehicle registered successfully.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModelForVehReg> call, Throwable t) {
                call.cancel();
                MyUtil.showLog("Error", t.getMessage() + "");
                myDialog.dismiss();
                Toast.makeText(RegVehicleActivity.this, getResources().getString(R.string.api_failed_msg), Toast.LENGTH_SHORT).show();
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
