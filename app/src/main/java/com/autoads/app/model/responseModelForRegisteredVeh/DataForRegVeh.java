package com.autoads.app.model.responseModelForRegisteredVeh;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataForRegVeh {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("veh_type")
    @Expose
    private String vehType;
    @SerializedName("veh_reg_no")
    @Expose
    private String vehRegNo;
    @SerializedName("veh_color")
    @Expose
    private String vehColor;
    @SerializedName("veh_model")
    @Expose
    private String vehModel;
    @SerializedName("veh_make")
    @Expose
    private String vehMake;
    @SerializedName("veh_make_year")
    @Expose
    private String vehMakeYear;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVehType() {
        return vehType;
    }

    public void setVehType(String vehType) {
        this.vehType = vehType;
    }

    public String getVehRegNo() {
        return vehRegNo;
    }

    public void setVehRegNo(String vehRegNo) {
        this.vehRegNo = vehRegNo;
    }

    public String getVehColor() {
        return vehColor;
    }

    public void setVehColor(String vehColor) {
        this.vehColor = vehColor;
    }

    public String getVehModel() {
        return vehModel;
    }

    public void setVehModel(String vehModel) {
        this.vehModel = vehModel;
    }

    public String getVehMake() {
        return vehMake;
    }

    public void setVehMake(String vehMake) {
        this.vehMake = vehMake;
    }

    public String getVehMakeYear() {
        return vehMakeYear;
    }

    public void setVehMakeYear(String vehMakeYear) {
        this.vehMakeYear = vehMakeYear;
    }

}
