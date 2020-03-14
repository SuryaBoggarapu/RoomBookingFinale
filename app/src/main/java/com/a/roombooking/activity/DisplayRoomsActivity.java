package com.a.roombooking.activity;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a.roombooking.EndPointUrl;
import com.a.roombooking.R;
import com.a.roombooking.RetrofitInstance;
import com.a.roombooking.adapter.DisplayRoomsAdapter;
import com.a.roombooking.model.RoomsPOJO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayRoomsActivity extends AppCompatActivity {
    List<RoomsPOJO> a1;
    RecyclerView recyclerView;
    DisplayRoomsAdapter displayRoomsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdashboard);
        getSupportActionBar().setTitle("Rooms");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        a1 = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(DisplayRoomsActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);


        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(25), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        displayRoomsAdapter = new DisplayRoomsAdapter(DisplayRoomsActivity.this, a1);
        recyclerView.setAdapter(displayRoomsAdapter);
        getAllServices();
    }

    public void getAllServices() {
        EndPointUrl apiService = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<List<RoomsPOJO>> call = apiService.getRooms(getIntent().getStringExtra("blockname"));
        call.enqueue(new Callback<List<RoomsPOJO>>() {
            @Override
            public void onResponse(Call<List<RoomsPOJO>> call, Response<List<RoomsPOJO>> response) {
                a1 = response.body();
                Log.d("TAG", "Response = " + a1);
                displayRoomsAdapter.setMovieList(a1);
            }

            @Override
            public void onFailure(Call<List<RoomsPOJO>> call, Throwable t) {
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
}