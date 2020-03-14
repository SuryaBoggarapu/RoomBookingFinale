package com.a.roombooking.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.a.roombooking.EndPointUrl;
import com.a.roombooking.R;
import com.a.roombooking.ResponseData;
import com.a.roombooking.RetrofitInstance;
import com.a.roombooking.Utils;
import com.a.roombooking.model.ReqPojo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookaRoomActivity extends AppCompatActivity {
    EditText et_block_name,et_Room_name,et_add_capacity,et_equipment,et_reason_for_book,et_other_equipment, et_duration;
    TextView tv_dob,tv_time,tv_ending_date,tv_end_date;
    String date,time,date_end;
    Button btn_submit;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    String session,id;
    TextView tv_equipment_sw,tv_equipment_hw;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_rooms);
        getSupportActionBar().setTitle("Room Booking");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getApplication().getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        session = sharedPreferences.getString("user_name", "def-val");

        tv_equipment_sw=(TextView) findViewById(R.id.tv_equipment_sw);
        tv_equipment_hw=(TextView) findViewById(R.id.tv_equipment_hw);
        tv_time=(TextView) findViewById(R.id.tv_time);
        tv_ending_date=(TextView) findViewById(R.id.tv_ending_date);
        tv_end_date=(TextView) findViewById(R.id.tv_end_date);

        et_other_equipment=(EditText)findViewById(R.id.et_other_equipment);
        et_reason_for_book=(EditText)findViewById(R.id.et_reason_for_book);
        et_block_name=(EditText)findViewById(R.id.et_block_name);
        et_Room_name=(EditText)findViewById(R.id.et_Room_name);
        et_add_capacity=(EditText)findViewById(R.id.et_add_capacity);
        et_equipment=(EditText)findViewById(R.id.et_equipment);
        tv_dob=(TextView)findViewById(R.id.tv_dob);
        et_duration=(EditText)findViewById(R.id.et_duration);
        btn_submit=(Button) findViewById(R.id.btn_submit);

        et_block_name.setText(getIntent().getStringExtra("bname"));
        et_Room_name.setText(getIntent().getStringExtra("rname"));
        et_add_capacity.setText(getIntent().getStringExtra("capacity"));
        tv_equipment_sw.setText(getIntent().getStringExtra("software"));
        tv_equipment_hw.setText(getIntent().getStringExtra("hardware"));
        //et_equipment.setText(getIntent().getStringExtra("Equipment"));

        tv_equipment_sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                res_software.clear();
                sofwareServerData();
            }
        });
        tv_equipment_hw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                res_hardware.clear();
                hardwareServerData();

            }
        });

        tv_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setdatepicker();
            }
        });
        tv_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setenddatepicker();
            }
        });

        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setmTimePicker();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
                Intent intent=new Intent(BookaRoomActivity.this,SlashScreenActivity.class);
                startActivity(intent);
            }
        });


    }
    private void submitData() {
        String sware=tv_equipment_sw.getText().toString();
        String hware=tv_equipment_hw.getText().toString();
        String reason_book=et_reason_for_book.getText().toString();
        String other=et_other_equipment.getText().toString();

        progressDialog = new ProgressDialog(BookaRoomActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.bookMyRoom(getIntent().getStringExtra("id"),session,date,time,date_end,sware,hware,reason_book,other,et_duration.getText().toString());
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().status.equals("true")) {
                    progressDialog.dismiss();
                    Toast.makeText(BookaRoomActivity.this, response.body().message, Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(BookaRoomActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(BookaRoomActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public void setdatepicker() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(String.valueOf(BookaRoomActivity.this), "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                //date = month + "/" + day + "/" + year;
                date=year + "/" + month + "/" + day;
                tv_dob.setText(date);
            }
        };
        DatePickerDialog dialog = new DatePickerDialog(
                BookaRoomActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dialog.show();

    }

    public void setenddatepicker() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(String.valueOf(BookaRoomActivity.this), "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                //date = month + "/" + day + "/" + year;
                date_end=year + "/" + month + "/" + day;
                tv_end_date.setText(date_end);
            }
        };
        DatePickerDialog dialog = new DatePickerDialog(
                BookaRoomActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dialog.show();

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


    /**Software Start**/
    List<ReqPojo> list_sofraware;
    ArrayList<String> res_software=new ArrayList<String>();
    public AlertDialog show_software(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        tv_equipment_sw.setText(res_software.toString());
                        //Toast.makeText(getApplicationContext(),res_software.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
                .setAdapter(new Software_CustomAdapter(context), null);
        AlertDialog dialog = builder.show();
        ListView list = dialog.getListView();
        list.setItemsCanFocus(false);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        return dialog;
    }
    private LayoutInflater inflater=null;
    private  class Software_CustomAdapter extends BaseAdapter {
        public Software_CustomAdapter(Context context) {
            inflater = ( LayoutInflater )BookaRoomActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return list_sofraware.size();
        }
        @Override
        public Object getItem(int position) {
            return position;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_checkbox, null);
            }
            CheckBox chk = (CheckBox) convertView.findViewById(R.id.chk);
            chk.setText(list_sofraware.get(position).getName());
            chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        res_software.add(list_sofraware.get(position).getName());
                    } else {
                        res_software.remove(list_sofraware.get(position).getName());
                    }
                }
            });
            if (list_sofraware.get(position).getStatus().equals("0")) {
                chk.setEnabled(false);
                chk.setTextColor(Color.RED);
            } else if (list_sofraware.get(position).getStatus().equals("1")) {
                chk.setEnabled(true);
                chk.setTextColor(Color.GREEN);
            } else {
                chk.setEnabled(false);
                chk.setTextColor(Color.YELLOW);
            }
            return convertView;
        }
    }

   public void sofwareServerData() {
       progressDialog = new ProgressDialog(BookaRoomActivity.this);
       progressDialog.setMessage("Loading....");
       progressDialog.show();

       EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
       Call<List<ReqPojo>> call = service.get_software();
       call.enqueue(new Callback<List<ReqPojo>>() {
           @Override
           public void onResponse(Call<List<ReqPojo>> call, Response<List<ReqPojo>> response) {
               progressDialog.dismiss();
               list_sofraware = response.body();
               show_software(BookaRoomActivity.this);
           }

           @Override
           public void onFailure(Call<List<ReqPojo>> call, Throwable t) {
               progressDialog.dismiss();
               Toast.makeText(BookaRoomActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
           }
       });
   }
    /**Software End**/

    /**hardware Start**/
    List<ReqPojo> list_hardware;
    ArrayList<String> res_hardware=new ArrayList<String>();
    public AlertDialog show_hardware(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        tv_equipment_hw.setText(res_hardware.toString());
                        //Toast.makeText(getApplicationContext(),res_hardware.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
                .setAdapter(new Hardwareware_CustomAdapter(), null);
        AlertDialog dialog = builder.show();
        ListView list = dialog.getListView();
        list.setItemsCanFocus(false);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        return dialog;
    }
    private LayoutInflater inflater_hardware=null;
    private  class Hardwareware_CustomAdapter extends BaseAdapter {
        public Hardwareware_CustomAdapter() {
            inflater_hardware = ( LayoutInflater )BookaRoomActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return list_hardware.size();
        }
        @Override
        public Object getItem(int position) {
            return position;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater_hardware.inflate(R.layout.list_checkbox, null);
            }
            CheckBox chk = (CheckBox) convertView.findViewById(R.id.chk);
            chk.setText(list_hardware.get(position).getName());
            chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        res_hardware.add(list_hardware.get(position).getName());
                    } else {
                        res_hardware.remove(list_hardware.get(position).getName());
                    }
                }
            });
            if (list_hardware.get(position).getStatus().equals("0")) {
                chk.setEnabled(false);
                chk.setTextColor(Color.RED);
            } else if (list_hardware.get(position).getStatus().equals("1")) {
                chk.setEnabled(true);
                chk.setTextColor(Color.GREEN);
            } else {
                chk.setEnabled(false);
                chk.setTextColor(Color.YELLOW);
            }
            return convertView;
        }
    }

    public void hardwareServerData() {
        progressDialog = new ProgressDialog(BookaRoomActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<List<ReqPojo>> call = service.get_hardware();
        call.enqueue(new Callback<List<ReqPojo>>() {
            @Override
            public void onResponse(Call<List<ReqPojo>> call, Response<List<ReqPojo>> response) {
                progressDialog.dismiss();
                list_hardware = response.body();
                show_hardware(BookaRoomActivity.this);
            }

            @Override
            public void onFailure(Call<List<ReqPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(BookaRoomActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**hardware End**/

    /**Others Start**/
    List<ReqPojo> list_Others;
    ArrayList<String> res_Others=new ArrayList<String>();
    public AlertDialog show_others(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getApplicationContext(),res_Others.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
                .setAdapter(new Hardwareware_CustomAdapter(), null);
        AlertDialog dialog = builder.show();
        ListView list = dialog.getListView();
        list.setItemsCanFocus(false);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        return dialog;
    }
    private LayoutInflater inflater_others=null;
    private  class Others_CustomAdapter extends BaseAdapter {
        public Others_CustomAdapter() {
            inflater_others = ( LayoutInflater )BookaRoomActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return list_Others.size();
        }
        @Override
        public Object getItem(int position) {
            return position;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater_hardware.inflate(R.layout.list_checkbox, null);
            }
            CheckBox chk = (CheckBox) convertView.findViewById(R.id.chk);
            chk.setText(list_Others.get(position).getName());
            chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        res_Others.add(list_Others.get(position).getName());
                    } else {
                        res_Others.remove(list_Others.get(position).getName());
                    }
                }
            });
            if (list_Others.get(position).getStatus().equals("0")) {
                chk.setEnabled(false);
                chk.setTextColor(Color.RED);
            } else if (list_Others.get(position).getStatus().equals("1")) {
                chk.setEnabled(true);
                chk.setTextColor(Color.GREEN);
            } else {
                chk.setEnabled(false);
                chk.setTextColor(Color.YELLOW);
            }
            return convertView;
        }
    }

    public void othersServerData() {
        progressDialog = new ProgressDialog(BookaRoomActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<List<ReqPojo>> call = service.get_others();
        call.enqueue(new Callback<List<ReqPojo>>() {
            @Override
            public void onResponse(Call<List<ReqPojo>> call, Response<List<ReqPojo>> response) {
                progressDialog.dismiss();
                list_Others = response.body();
                show_others(BookaRoomActivity.this);
            }

            @Override
            public void onFailure(Call<List<ReqPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(BookaRoomActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**hardware End**/
    public void setmTimePicker(){

        // TODO Auto-generated method stub
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        int ampm=mcurrentTime.get(Calendar.AM);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(BookaRoomActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                time=selectedHour + ":" + selectedMinute;
                tv_time.setText(time);
            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }
}
