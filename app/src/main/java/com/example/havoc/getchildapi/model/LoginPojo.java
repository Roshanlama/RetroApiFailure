package com.example.havoc.getchildapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by havoc on 8/17/17.
 */

public class LoginPojo {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("login_type")
    @Expose
    private String loginType;
    @SerializedName("login_user_id")
    @Expose
    private String loginUserId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("authentication_key")
    @Expose
    private String authenticationKey;
    @SerializedName("class_id")
    @Expose
    private String classID;


    public String getClassID(){
        return classID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthenticationKey() {
        return authenticationKey;
    }

    public void setAuthenticationKey(String authenticationKey) {
        this.authenticationKey = authenticationKey;
    }
}
