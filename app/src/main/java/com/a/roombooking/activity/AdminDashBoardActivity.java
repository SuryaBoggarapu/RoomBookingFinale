package com.a.roombooking.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.a.roombooking.R;

public class AdminDashBoardActivity extends AppCompatActivity {
    EditText et_block_name,et_Room_name,et_add_capacity,et_add_softwere,et_add_werewerr;
    Button btn_addblock,btn_add_room,btn_booked_rooms,btn_not_booked_rooms,btn_logout,btn_all_rooms,btn_add_staff;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminaddblockrooms);

        getSupportActionBar().setTitle("Admin Panel");
        /*getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/


        btn_addblock=(Button)findViewById(R.id.btn_addblock);
        btn_add_room=(Button)findViewById(R.id.btn_add_room);
        btn_not_booked_rooms=(Button)findViewById(R.id.btn_not_booked_rooms);
        btn_booked_rooms=(Button)findViewById(R.id.btn_booked_rooms);
        btn_all_rooms=(Button)findViewById(R.id.btn_all_rooms);
        btn_add_staff=(Button)findViewById(R.id.btn_add_staff);
        btn_logout=(Button)findViewById(R.id.btn_logout);

        btn_add_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this, RegistrationActivity.class);
                startActivity(intent);

            }
        });

        btn_addblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this, AddBlockActivity.class);
                startActivity(intent);

            }
        });
        btn_add_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this, AddRoomActivity.class);
                startActivity(intent);

            }
        });
        btn_all_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this, AdminGetAddRoomsActivity.class);
                startActivity(intent);

            }
        });
        btn_booked_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this, AdminGetBookedRoomsActivity.class);
                startActivity(intent);

            }
        });
        btn_not_booked_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this, AdminNotBookedRoomsActivity.class);
                startActivity(intent);

            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminDashBoardActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }*/
}
