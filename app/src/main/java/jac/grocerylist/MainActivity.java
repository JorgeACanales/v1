package jac.grocerylist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView recyclerViewProductos;
    private RecyclerView.Adapter adapter;
    Button btnAddItem, btnEditItem, btnDeleteItem;
    Items resItems;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewProductos = (RecyclerView) findViewById(R.id.recyclerViewProductos);
        recyclerViewProductos.setHasFixedSize(true);
        recyclerViewProductos.setLayoutManager(new LinearLayoutManager(this));


        obtenerDatosProductos();
        btnAddItem = (Button) findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(this);
        btnEditItem = (Button) findViewById(R.id.btnEditItem);
        btnEditItem.setOnClickListener(this);
        btnDeleteItem = (Button) findViewById(R.id.btnDeleteItem);
        btnDeleteItem.setOnClickListener(this);





    }
    @Override
    public void onClick(View view) {
        if (view.equals(btnAddItem)) {
            ((Sessions)getApplicationContext()).setSesButton(btnAddItem.getText().toString());

        }
        if (view.equals(btnEditItem)) {
            ((Sessions)getApplicationContext()).setSesButton(btnEditItem.getText().toString());

        }

        if (view.equals(btnDeleteItem)) {
            ((Sessions)getApplicationContext()).setSesButton(btnDeleteItem.getText().toString());
            deleteItem(((Sessions)getApplicationContext()).getSesidProduct());
           // Toast.makeText(getApplicationContext(), "delete" + ((Sessions)getApplicationContext()).getSesidProduct(), Toast.LENGTH_LONG).show();

        }else
        {
            Intent i = new Intent(MainActivity.this, AddItem.class);
            startActivity(i);
            ((Activity) MainActivity.this).overridePendingTransition(0, 0);
        }




    }


    public void obtenerDatosProductos(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicio service = retrofit.create(Servicio.class);
        Call<Items> call = service.getItems();

        call.enqueue(new Callback<Items>() {

            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {
                if(response.isSuccessful()) {
                    resItems = (Items) response.body();
                    Toast.makeText(MainActivity.this, "Getting Data! " , Toast.LENGTH_SHORT).show();
                    adapter = new ItemAdapter(Arrays.asList(response.body().getitems()),  MainActivity.this.getApplicationContext());
                    recyclerViewProductos.setAdapter(adapter);
                    ArrayList<Item> itemData = new ArrayList<Item>();
                    itemData.addAll(Arrays.asList(resItems.getitems()));
                    list = new ArrayList<String>();


                    for (int i = 0; i < itemData.size(); i++) {
                        String lat = String.valueOf(itemData.get(i).getidProduct());
                        list.add(lat);
                    }


                }

                else {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Items> call, Throwable t) {

            }
        });


    }


    public void deleteItem(int idProduct){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Deleting ITEM...");
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

        Item conf = new Item(idProduct);


        Call<Result> call = service.deleteItem(
                conf.getidProduct()

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.refresh:
            obtenerDatosProductos();
            return(true);
        case R.id.deleteAll:

            //String text = builder.toString();
            StringBuilder builder = new StringBuilder();
            for (String value : list) {
                builder.append(value);
                Log.i("Value of element ",value);

                deleteItem(Integer.parseInt(value));


            }


            return(true);


    }
        return(super.onOptionsItemSelected(item));
    }



}
