package com.a.roombooking.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a.roombooking.R;
import com.a.roombooking.activity.BookaRoomActivity;
import com.a.roombooking.model.RoomsPOJO;
import com.bumptech.glide.Glide;

import java.util.List;

public class DisplayRoomsAdapter extends RecyclerView.Adapter<DisplayRoomsAdapter.MyviewHolder> {

    Context context;
    List<RoomsPOJO> a1;
    String url="http://roombookingappproject.online/RoomBooking/";

    public DisplayRoomsAdapter(Context context, List<RoomsPOJO> movieList) {
        this.context = context;
        this.a1 = movieList;
    }

    public  void setMovieList(List<RoomsPOJO> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }
    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_display_blocks_rooms,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int position) {
        if (a1.get(position).getStatus().equals("NotBooked")) {


                holder.tv_cname.setBackgroundColor(Color.parseColor("#9CCC65"));
                holder.image_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, BookaRoomActivity.class);
                        intent.putExtra("bname",a1.get(position).getBname());
                        intent.putExtra("rname",a1.get(position).getRname());
                        intent.putExtra("capacity",a1.get(position).getCapacity());
                        intent.putExtra("software",a1.get(position).getSoftware());
                        intent.putExtra("hardware",a1.get(position).getHardware());
                        intent.putExtra("id",a1.get(position).getId());
                        context.startActivity(intent);
                    }
                });

        }

         else {
            holder.tv_cname.setBackgroundColor(Color.parseColor("#FF9891"));
            holder.tv_cname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDiolouge();
                }
            });
        }
        holder.tv_cname.setText(a1.get(position).getRname());
        Glide.with(context).load(url+a1.get(position).getImg_url()).into(holder.image_view);
    }


    @Override
    public int getItemCount() {
        if(a1 != null){
            return a1.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tv_cname;
        ImageView image_view;

        public MyviewHolder(View itemView) {
            super(itemView);

            image_view = (ImageView)itemView.findViewById(R.id.image_view);
            tv_cname=(TextView)itemView.findViewById(R.id.tv_cname);
            Typeface alreadymem = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Lato-Regular.ttf");
            tv_cname.setTypeface(alreadymem);
        }
    }
    public void alertDiolouge()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(false);
        dialog.setTitle("Room Booking");
        dialog.setMessage("This Room is Already Booked" );
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
            }
        });
              /*  .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                    }
                });*/

        final AlertDialog alert = dialog.create();
        alert.show();
    }
}
