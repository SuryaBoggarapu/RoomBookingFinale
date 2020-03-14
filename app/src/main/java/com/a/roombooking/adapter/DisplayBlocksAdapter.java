package com.a.roombooking.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.a.roombooking.R;
import com.a.roombooking.activity.DisplayRoomsActivity;
import com.a.roombooking.model.BlockPOJO;
import com.bumptech.glide.Glide;

import java.util.List;

public class DisplayBlocksAdapter extends RecyclerView.Adapter<DisplayBlocksAdapter.MyviewHolder> {

    Context context;
    List<BlockPOJO> a1;
    String url="http://roombookingappproject.online/RoomBooking/";

    public DisplayBlocksAdapter(Context context, List<BlockPOJO> movieList) {
        this.context = context;
        this.a1 = movieList;
    }

    public void setMovieList(List<BlockPOJO> a1) {
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

        holder.tv_cname.setText(a1.get(position).getBname());

        Glide.with(context).load(url+a1.get(position).getImg_url()).into(holder.image_view);
        holder.image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DisplayRoomsActivity.class);
                intent.putExtra("blockname",a1.get(position).getBname());
                context.startActivity(intent);
            }
        });

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
}
