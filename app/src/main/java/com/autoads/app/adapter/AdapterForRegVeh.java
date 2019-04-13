package com.autoads.app.adapter;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.autoads.app.R;
import com.autoads.app.model.responseModelForRegisteredVeh.DataForRegVeh;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AdapterForRegVeh extends RecyclerView.Adapter<AdapterForRegVeh.MyViewHolder> {

    private Activity activity;
    private List<DataForRegVeh> addressList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTv, addressTv;

        @BindView(R.id.veh_make_name)
        AppCompatTextView vehMakeNameTv;

        @BindView(R.id.veh_model_year_tv)
        AppCompatTextView vehModelYearTV;

        @BindView(R.id.veh_type_tv)
        AppCompatTextView vehTypeTv;

        @BindView(R.id.veh_reg_number_tv)
        AppCompatTextView vehRegNumberTv;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);


        }
    }

    public AdapterForRegVeh(Activity activity, List<DataForRegVeh> addressList) {
        this.activity = activity;
        this.addressList = addressList;
    }

    @Override
    public AdapterForRegVeh.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_for_reg_veh, parent, false);

        return new AdapterForRegVeh.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterForRegVeh.MyViewHolder holder, final int position) {
        final DataForRegVeh regVeh = addressList.get(position);

        holder.vehMakeNameTv.setText(regVeh.getVehMake() + "");
        holder.vehModelYearTV.setText(regVeh.getVehModel() + " - " + regVeh.getVehMakeYear());
        holder.vehTypeTv.setText(regVeh.getVehColor() + " - " + regVeh.getVehType());
        holder.vehRegNumberTv.setText("Registration Number : " + regVeh.getVehRegNo() + "");

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

}

