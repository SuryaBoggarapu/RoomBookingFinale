package com.a.roombooking.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a.roombooking.EndPointUrl;
import com.a.roombooking.R;
import com.a.roombooking.RetrofitInstance;
import com.a.roombooking.adapter.DisplayBlocksAdapter;
import com.a.roombooking.model.BlockPOJO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayBlocksActivity extends AppCompatActivity {
    Button btn_edit_profile,btn_avalable_rooms,btn_mybooked_rooms,btn_logout;
    List<BlockPOJO> a1;
    RecyclerView recyclerView;
    DisplayBlocksAdapter displayBlocksAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdashboard);
        getSupportActionBar().setTitle("Floors");


        a1 = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(DisplayBlocksActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);


        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(25), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        displayBlocksAdapter = new DisplayBlocksAdapter(DisplayBlocksActivity.this, a1);
        recyclerView.setAdapter(displayBlocksAdapter);
        getAllServices();
    }

    public void getAllServices() {
        EndPointUrl apiService = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<List<BlockPOJO>> call = apiService.getblock();
        call.enqueue(new Callback<List<BlockPOJO>>() {
            @Override
            public void onResponse(Call<List<BlockPOJO>> call, Response<List<BlockPOJO>> response) {
                a1 = response.body();
                Log.d("TAG", "Response = " + a1);
                displayBlocksAdapter.setMovieList(a1);
            }

            @Override
            public void onFailure(Call<List<BlockPOJO>> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });

    }
    //Grid Space Class Start

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
     /*btn_edit_profile=(Button)findViewById(R.id.btn_edit_profile);
        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserDashBoardActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });
        btn_avalable_rooms=(Button)findViewById(R.id.btn_avalable_rooms);
        btn_avalable_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserDashBoardActivity.this, AvalableRoomsActivity.class);
                startActivity(intent);
            }
        });
        btn_mybooked_rooms=(Button)findViewById(R.id.btn_mybooked_rooms);
        btn_mybooked_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserDashBoardActivity.this, MyBookedRoomsActivity.class);
                startActivity(intent);
            }
        });

        btn_logout=(Button)findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserDashBoardActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.avalable_rooms:
                Intent contact = new Intent(getApplicationContext(), AvalableRoomsActivity.class);
                startActivity(contact);
                return true;
            case R.id.mybooked_rooms:
                Intent mybooked = new Intent(getApplicationContext(), MyBookedRoomsActivity.class);
                startActivity(mybooked);
                return true;
            case R.id.edit_profile:
                Intent editprofile = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(editprofile);
                return true;
            case R.id.logout:
                Intent logout = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(logout);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
