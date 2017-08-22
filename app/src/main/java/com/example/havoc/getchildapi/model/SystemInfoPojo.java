package com.example.havoc.getchildapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by havoc on 8/17/17.
 */

public class SystemInfoPojo {

    @SerializedName("system_name")
    @Expose
    private String systemName;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
}
