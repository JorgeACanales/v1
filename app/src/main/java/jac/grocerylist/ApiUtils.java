package jac.grocerylist;


public class ApiUtils {


    public static final String BASE_URL = "http://192.168.1.79/GroceryList/app/v1/";


    public static Servicio getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(Servicio.class);
    }



}

