
package com.autoads.app.model.responseModelForRegisteredVeh;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseModelForRegisteredVehicle {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private List<DataForRegVeh> data = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataForRegVeh> getData() {
        return data;
    }

    public void setData(List<DataForRegVeh> data) {
        this.data = data;
    }




}
