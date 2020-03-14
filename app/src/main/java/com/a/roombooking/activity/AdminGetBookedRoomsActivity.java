package com.a.roombooking.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.a.roombooking.EndPointUrl;
import com.a.roombooking.R;
import com.a.roombooking.RetrofitInstance;
import com.a.roombooking.Utils;
import com.a.roombooking.adapter.AdminGetBookedRoomsAdapter;
import com.a.roombooking.adapter.AdminNotBookedRoomsAdapter;
import com.a.roombooking.model.AdminGetBookedRoomsPojo;
import com.a.roombooking.model.AdminNotBookedRoomsPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminGetBookedRoomsActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<AdminGetBookedRoomsPojo> a1;
    SharedPreferences sharedPreferences;
    String uname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booked_rooms);

       sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_name", "");

        getSupportActionBar().setTitle("Booked Rooms");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);
        a1= new ArrayList<>();
        serverData();
    }
    public void serverData(){
        progressDialog = new ProgressDialog(AdminGetBookedRoomsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<List<AdminGetBookedRoomsPojo>> call = service.getBookedRooms();
        call.enqueue(new Callback<List<AdminGetBookedRoomsPojo>>() {
            @Override
            public void onResponse(Call<List<AdminGetBookedRoomsPojo>> call, Response<List<AdminGetBookedRoomsPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(AdminGetBookedRoomsActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    a1 = response.body();
                    list_view.setAdapter(new AdminGetBookedRoomsAdapter(a1, AdminGetBookedRoomsActivity.this));


                }
            }

            @Override
            public void onFailure(Call<List<AdminGetBookedRoomsPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AdminGetBookedRoomsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override                                                                                                                    //add this method in your program
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


