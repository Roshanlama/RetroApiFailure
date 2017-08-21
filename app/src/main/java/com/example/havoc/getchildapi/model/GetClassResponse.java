package com.example.havoc.getchildapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by havoc on 8/21/17.
 */

public class GetClassResponse {
    @SerializedName("class_id")
    @Expose
    private String classId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_numeric")
    @Expose
    private String nameNumeric;
    @SerializedName("teacher_id")
    @Expose
    private String teacherId;
    @SerializedName("data")
    @Expose
    private List<GetClassPojo> data = null;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameNumeric() {
        return nameNumeric;
    }

    public void setNameNumeric(String nameNumeric) {
        this.nameNumeric = nameNumeric;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public List<GetClassPojo> getData() {
        return data;
    }

    public void setData(List<GetClassPojo> data) {
        this.data = data;
    }
}
