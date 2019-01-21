package jac.grocerylist;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddItem  extends AppCompatActivity {
    EditText input_product, input_branch, input_presentation, input_presentationCount, input_price;
    Button btnAddItem;
    private Context mCtx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);


        input_product = (EditText) findViewById(R.id.input_product);
        input_branch = (EditText) findViewById(R.id.input_branch);
        input_presentation = (EditText) findViewById(R.id.input_presentation);
        input_presentationCount = (EditText) findViewById(R.id.input_presentationCount);
        input_price = (EditText) findViewById(R.id.input_price);

        btnAddItem = (Button) findViewById(R.id.btnAddItem);



        if (((Sessions)getApplicationContext()).getSesButton().equals("Edit Item")){
            getDataItem();

        }

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product = input_product.getText().toString();
                String branch = input_branch.getText().toString();
                String presentation = input_presentation.getText().toString();
//                double presentationCount = Double.parseDouble(input_presentationCount.getText().toString());
 //               double price = Double.parseDouble(input_price.getText().toString());

                //validate form
                if(validateConfig(product, branch)){
                    if (((Sessions)getApplicationContext()).getSesButton().equals("Edit Item")){
                        updateItem(((Sessions)getApplicationContext()).getSesidProduct(), product, branch, presentation);
                    }
                    else {
                        insertItem(product, branch, presentation);
                    }
                   // Intent intent = new Intent(Configuracion.this, Escaner.class);
                   // startActivity(intent);

                }
            }
        });






    }

    public void insertItem(String product, String branch, String presentation){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registrando ITEM...");
        progressDialog.show();


        OkHttpClient client = new OkHttpClient();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL)
                //  .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        //Defining retrofit api service
        Servicio service = retrofit.create(Servicio.class);

        Item conf = new Item(product, branch, presentation);


        Call<Result> call = service.addItem(
                conf.getproduct(),
                conf.getbranch(),
                conf.getpresentation()
              //  conf.getpresentationCount(),
              //  conf.getprice()

        );

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                     }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }


    public void getDataItem(){
        input_product.setText(((Sessions)getApplicationContext()).getSesProduct());
        input_branch.setText(((Sessions)getApplicationContext()).getsesBranch());
        input_presentation.setText(((Sessions)getApplicationContext()).getsesPresentation());
        //Toast.makeText(AddItem.this, "getDataItem" + ((Sessions)getApplicationContext()).getSesProduct(), Toast.LENGTH_LONG).show();


    }



    public void updateItem(int idProduct, String product, String branch, String presentation){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating ITEM...");
        progressDialog.show();


        OkHttpClient client = new OkHttpClient();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL)
                //  .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        //Defining retrofit api service
        Servicio service = retrofit.create(Servicio.class);

        Item conf = new Item(product, branch, presentation, idProduct);


        Call<Result> call = service.editItem(
                conf.getproduct(),
                conf.getbranch(),
                conf.getpresentation(),
                conf.getidProduct()
                //  conf.getpresentationCount(),
                //  conf.getprice()

        );

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }




    private boolean validateConfig(String ip, String telefono){
        if(ip == null || ip.trim().length() == 0){
            Toast.makeText(this, "Ingresar IP o Dominio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(telefono == null || telefono.trim().length() == 0){
            Toast.makeText(this, "Ingresar Telefono", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }






    }
