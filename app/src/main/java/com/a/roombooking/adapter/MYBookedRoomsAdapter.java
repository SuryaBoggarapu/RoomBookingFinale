package com.a.roombooking.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a.roombooking.EndPointUrl;
import com.a.roombooking.R;
import com.a.roombooking.ResponseData;
import com.a.roombooking.RetrofitInstance;
import com.a.roombooking.activity.BookaRoomActivity;
import com.a.roombooking.activity.CancelSlashScreenActivity;
import com.a.roombooking.activity.MyBookedRoomsActivity;
import com.a.roombooking.model.AvalableRoomsPojo;
import com.a.roombooking.model.MyBookedRoomsPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MYBookedRoomsAdapter extends BaseAdapter {
    List<MyBookedRoomsPojo> ar;
    Context cnt;
    String id;

    public MYBookedRoomsAdapter(List<MyBookedRoomsPojo> ar, Context cnt) {
        this.ar = ar;
        this.cnt = cnt;
    }

    @Override
    public int getCount() {
        return ar.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int pos, View view, ViewGroup viewGroup) {
        LayoutInflater obj1 = (LayoutInflater) cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View obj2 = obj1.inflate(R.layout.list_my_booked_rooms, null);

        TextView tv_bname = (TextView) obj2.findViewById(R.id.tv_bname);
        tv_bname.setText("Floor Name  :" + ar.get(pos).getBname());

        TextView tv_rname = (TextView) obj2.findViewById(R.id.tv_rname);
        tv_rname.setText("Room Name  :" + ar.get(pos).getRname());

        TextView tv_capacity = (TextView) obj2.findViewById(R.id.tv_capacity);
        tv_capacity.setText("Capacity  :" + ar.get(pos).getCapacity());

        TextView tv_softwere = (TextView) obj2.findViewById(R.id.tv_softwere);
        tv_softwere.setText("Software Equipment  :" + ar.get(pos).getSoftware());

        TextView tv_hardwere = (TextView) obj2.findViewById(R.id.tv_hardwere);
        tv_hardwere.setText("Hardware Equipment  :" + ar.get(pos).getHardware());

        TextView tv_desc = (TextView) obj2.findViewById(R.id.tv_desc);
        tv_desc.setText("Description  :" + ar.get(pos).getDescrip());


        TextView tv_date = (TextView) obj2.findViewById(R.id.tv_date);
        tv_date.setText("Check In Date  :" + ar.get(pos).getBooked_date());

        TextView tv_end_date = (TextView) obj2.findViewById(R.id.tv_end_date);
        tv_end_date.setText("Check Out Date  :" + ar.get(pos).getEnd_date());

        TextView tv_time = (TextView) obj2.findViewById(R.id.tv_time);
        tv_time.setText("Booking Time  :" + ar.get(pos).getBooked_time());



        id=ar.get(pos).getId();

        Button btn_cancle = (Button) obj2.findViewById(R.id.btn_cancle);
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
                Intent intent=new Intent(cnt, CancelSlashScreenActivity.class);
                cnt.startActivity(intent);
            }
        });
        return obj2;
    }
    ProgressDialog progressDialog;
    private void submitData() {

        progressDialog = new ProgressDialog(cnt);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.cancelMyBooking(id);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body().status.equals("true")) {
                    progressDialog.dismiss();
                    Toast.makeText(cnt, response.body().message, Toast.LENGTH_LONG).show();
                    //cnt.startActivity(new Intent(cnt, BookedRoomsActivity.class));
                    //cnt.finish();

                } else {
                    Toast.makeText(cnt, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(cnt, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
