package com.softsquared.runtastic.src.sign.models;

import com.google.gson.annotations.SerializedName;

public class SetGoalRequest {
    @SerializedName("userNo")
    private int userNo;
    @SerializedName("exerciseType")
    private int exerciseType;
    @SerializedName("termType")
    private int termType;
    @SerializedName("termValue")
    private String termValue;
    @SerializedName("measureType")
    private int measureType;
    @SerializedName("measureValue")
    private String measureValue;

    public SetGoalRequest(int userNo, int exerciseType, int termType, String termValue, int measureType, String measureValue) {
        this.userNo = userNo;
        this.exerciseType = exerciseType;
        this.termType = termType;
        this.termValue = termValue;
        this.measureType = measureType;
        this.measureValue = measureValue;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(int exerciseType) {
        this.exerciseType = exerciseType;
    }

    public int getTermType() {
        return termType;
    }

    public void setTermType(int termType) {
        this.termType = termType;
    }

    public String getTermValue() {
        return termValue;
    }

    public void setTermValue(String termValue) {
        this.termValue = termValue;
    }

    public int getMeasureType() {
        return measureType;
    }

    public void setMeasureType(int measureType) {
        this.measureType = measureType;
    }

    public String getMeasureValue() {
        return measureValue;
    }

    public void setMeasureValue(String measureValue) {
        this.measureValue = measureValue;
    }
}
