package com.example.khialerahat.experts_khialerahat.MainPackage;

public class Model_Transaction
{
    String type_transaction;
    String requst_date;
    String amount;
    String tasvyeh_date;
    String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTracking_code() {
        return tracking_code;
    }

    public void setTracking_code(String tracking_code) {
        this.tracking_code = tracking_code;
    }

    String tracking_code;

    public String getType_transaction() {
        return type_transaction;
    }

    public void setType_transaction(String type_transaction) {
        this.type_transaction = type_transaction;
    }

    public String getRequst_date() {
        return requst_date;
    }

    public void setRequst_date(String requst_date) {
        this.requst_date = requst_date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTasvyeh_date() {
        return tasvyeh_date;
    }

    public void setTasvyeh_date(String tasvyeh_date) {
        this.tasvyeh_date = tasvyeh_date;
    }
}
