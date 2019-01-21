package jac.grocerylist;


import com.google.gson.annotations.SerializedName;


public class Result {

    @SerializedName("error")
    private Boolean error;

    @SerializedName("message")
    private String message;

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }


}


