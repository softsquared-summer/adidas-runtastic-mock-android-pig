package com.softsquared.runtastic.src.login.models;

import java.io.Serializable;

public class Goal implements Serializable {
    int exerciseType,termType,measureType;
    String termValue, measureValue;

    public Goal(int exerciseType, int termType, int measureType, String termValue, String measureValue) {
        this.exerciseType = exerciseType;
        this.termType = termType;
        this.measureType = measureType;
        this.termValue = termValue;
        this.measureValue = measureValue;
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

    public int getMeasureType() {
        return measureType;
    }

    public void setMeasureType(int measureType) {
        this.measureType = measureType;
    }

    public String getTermValue() {
        return termValue;
    }

    public void setTermValue(String termValue) {
        this.termValue = termValue;
    }

    public String getMeasureValue() {
        return measureValue;
    }

    public void setMeasureValue(String measureValue) {
        this.measureValue = measureValue;
    }
}
