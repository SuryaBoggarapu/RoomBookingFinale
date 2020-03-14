package com.a.roombooking.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.a.roombooking.EndPointUrl;
import com.a.roombooking.R;
import com.a.roombooking.ResponseData;
import com.a.roombooking.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAddRoomActivity extends AppCompatActivity {
    EditText et_Room_name,et_add_capacity,et_block_name,et_desc_room;
    TextView tv_equipment_sw,tv_equipment_hw;
    Button btn_submit;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_addroom);
        initilization();

        tv_equipment_sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multicheckboxsw();
            }
        });
        tv_equipment_hw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multicheckboxhw();
            }
        });

        et_Room_name.setText(getIntent().getStringExtra("room"));
        et_block_name.setText(getIntent().getStringExtra("block"));
        et_add_capacity.setText(getIntent().getStringExtra("Capacity"));
        tv_equipment_sw.setText(getIntent().getStringExtra("software"));
        tv_equipment_hw.setText(getIntent().getStringExtra("hardware"));
        et_desc_room.setText(getIntent().getStringExtra("description"));

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             submitData();

            }
        });

    }
    private void submitData() {
        String rname = et_Room_name.getText().toString();
        String capacity = et_add_capacity.getText().toString();
        String swequipment=tv_equipment_sw.getText().toString();
        String hwequipment=tv_equipment_hw.getText().toString();
        String room_desc=et_desc_room.getText().toString();


        progressDialog = new ProgressDialog(EditAddRoomActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.edit_room_details(getIntent().getStringExtra("id"),capacity,swequipment,hwequipment,room_desc);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                if (response.body().status.equals("true")) {
                    progressDialog.dismiss();
                    Toast.makeText(EditAddRoomActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    //startActivity(new Intent(OurFamiliesActivity.this, MainActivity.class));

                } else {
                    Toast.makeText(EditAddRoomActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditAddRoomActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void initilization() {
        getSupportActionBar().setTitle("Edit Room Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_Room_name=(EditText)findViewById(R.id.et_Room_name);
        et_block_name=(EditText)findViewById(R.id.et_block_name);
        et_add_capacity=(EditText)findViewById(R.id.et_add_capacity);
        tv_equipment_sw=(TextView) findViewById(R.id.tv_equipment_sw);
        tv_equipment_hw=(TextView) findViewById(R.id.tv_equipment_hw);
        et_desc_room=(EditText)findViewById(R.id.et_desc_room);
        btn_submit=(Button)findViewById(R.id.btn_submit);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void multicheckboxsw(){
        Dialog dialog;
        final String[] items = {" Eclipse", "Photoshop", "Android Studio", "NetBeans", "Adobe Premiere"};
        final ArrayList itemsSelected = new ArrayList();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Software Equipment : ");
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedItemId,
                                        boolean isSelected) {
                        if (isSelected) {
                            itemsSelected.add(items[selectedItemId]);
                            //Toast.makeText(getApplicationContext(), items[selectedItemId], Toast.LENGTH_SHORT).show();
                        } else if (itemsSelected.contains(items[selectedItemId])) {
                            itemsSelected.remove(items[selectedItemId]);
                        }
                    }
                })
                .setPositiveButton("Done!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Your logic when OK button is clicked

                        tv_equipment_sw.setText(itemsSelected.toString());


                    }

                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        dialog = builder.create();
        dialog.show();
    }
    public void multicheckboxhw(){
        Dialog dialog;
        final String[] items = {"Smart board", "Projector", "Sound Systems", "laptop"};
        final ArrayList itemsSelected = new ArrayList();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Hardware Equipment : ");
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedItemId,
                                        boolean isSelected) {
                        if (isSelected) {
                            itemsSelected.add(items[selectedItemId]);
                            //Toast.makeText(getApplicationContext(), items[selectedItemId], Toast.LENGTH_SHORT).show();
                        } else if (itemsSelected.contains(items[selectedItemId])) {
                            itemsSelected.remove(items[selectedItemId]);
                        }
                    }
                })
                .setPositiveButton("Done!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Your logic when OK button is clicked

                        tv_equipment_hw.setText(itemsSelected.toString());


                    }

                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        dialog = builder.create();
        dialog.show();
    }
}
