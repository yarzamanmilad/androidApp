package com.example.khialerahat.experts_khialerahat.MainPackage;

public class Model_Message
{
    String tiltle;
    String message_body;
    String message_image_address;
    String message_id;

    public String getMessage_time() {
        return message_time;
    }

    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }

    String message_time;

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getTiltle() {
        return tiltle;
    }

    public void setTiltle(String tiltle) {
        this.tiltle = tiltle;
    }

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }

    public String getMessage_image_address() {
        return message_image_address;
    }

    public void setMessage_image_address(String message_image_address) {
        this.message_image_address = message_image_address;
    }
}
