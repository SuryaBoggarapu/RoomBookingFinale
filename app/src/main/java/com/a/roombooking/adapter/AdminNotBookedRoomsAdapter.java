package com.a.roombooking.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
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
import com.a.roombooking.model.AdminNotBookedRoomsPojo;
import com.a.roombooking.model.MyBookedRoomsPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminNotBookedRoomsAdapter extends BaseAdapter {
    List<AdminNotBookedRoomsPojo> ar;
    Context cnt;
    String id;

    public AdminNotBookedRoomsAdapter(List<AdminNotBookedRoomsPojo> ar, Context cnt) {
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
        View obj2 = obj1.inflate(R.layout.list_admin_not_booked_rooms, null);

        TextView tv_bname = (TextView) obj2.findViewById(R.id.tv_bname);
        tv_bname.setText("Floor Name  :" + ar.get(pos).getBname());

        TextView tv_rname = (TextView) obj2.findViewById(R.id.tv_rname);
        tv_rname.setText("Room Name  :" + ar.get(pos).getRname());

        TextView tv_capacity = (TextView) obj2.findViewById(R.id.tv_capacity);
        tv_capacity.setText("Capacity  :" + ar.get(pos).getCapacity());

        TextView tv_softwere = (TextView) obj2.findViewById(R.id.tv_softwere);
        tv_softwere.setText("Hardware Equipment  :" + ar.get(pos).getSoftware());

        TextView tv_hardwere = (TextView) obj2.findViewById(R.id.tv_hardwere);
        tv_hardwere.setText("Software Equipment  :" + ar.get(pos).getHardware());

        TextView tv_other = (TextView) obj2.findViewById(R.id.tv_other);
        tv_other.setText("Other Equipment  :" + ar.get(pos).getOther());

        TextView tv_reason_for_booking = (TextView) obj2.findViewById(R.id.tv_reason_for_booking);
        tv_reason_for_booking.setText("Reason For Booking  :" + ar.get(pos).getReason_for_booking());

        TextView tv_status = (TextView) obj2.findViewById(R.id.tv_status);
        tv_status.setText("Status  :" + ar.get(pos).getStatus());

        TextView tv_date = (TextView) obj2.findViewById(R.id.tv_date);
        tv_date.setText("Date Of Booked  :" + ar.get(pos).getBooked_date());
        Typeface fontstyle=Typeface.createFromAsset(cnt.getAssets(),"fonts/Lato-Medium.ttf");
        tv_bname.setTypeface(fontstyle);
        tv_date.setTypeface(fontstyle);
        tv_status.setTypeface(fontstyle);
        tv_reason_for_booking.setTypeface(fontstyle);
        tv_other.setTypeface(fontstyle);
        tv_softwere.setTypeface(fontstyle);
        tv_capacity.setTypeface(fontstyle);
        tv_rname.setTypeface(fontstyle);
        tv_hardwere.setTypeface(fontstyle);

        return obj2;
    }
}
