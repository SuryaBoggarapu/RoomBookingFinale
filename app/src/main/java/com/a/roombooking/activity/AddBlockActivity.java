package com.a.roombooking.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.a.roombooking.EndPointUrl;
import com.a.roombooking.R;
import com.a.roombooking.ResponseData;
import com.a.roombooking.RetrofitInstance;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddBlockActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    EditText et_block_name;
    Button btn_submit,select_image;
    ProgressDialog progressDialog;
    private static final String TAG = AddBlockActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "http://roombookingappproject.online/";
    private Uri uri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addblock);
        initilization();


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //submitData();
                uploadImageToServer();

            }
        });
        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
            }
        });

    }
    public void initilization() {
        getSupportActionBar().setTitle("Add Floors");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        select_image=(Button)findViewById(R.id.select_image);

        et_block_name=(EditText)findViewById(R.id.et_block_name);
        btn_submit=(Button)findViewById(R.id.btn_submit);

    }
        /*private void submitData() {
     String bname = et_block_name.getText().toString();

     progressDialog = new ProgressDialog(AddBlockActivity.this);
     progressDialog.setMessage("Loading....");
     progressDialog.show();

     EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
     Call<ResponseData> call = service.blockAdd(bname);
     call.enqueue(new Callback<ResponseData>() {
         @Override
         public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

             if (response.body().status.equals("true")) {
                 progressDialog.dismiss();
                 Toast.makeText(AddBlockActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                 //startActivity(new Intent(OurFamiliesActivity.this, MainActivity.class));

             } else {
                 Toast.makeText(AddBlockActivity.this, response.body().message, Toast.LENGTH_LONG).show();
             }
         }

         @Override
         public void onFailure(Call<ResponseData> call, Throwable t) {
             progressDialog.dismiss();
             Toast.makeText(AddBlockActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
         }
     });
 }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, AddBlockActivity.this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, AddBlockActivity.this);
                file = new File(filePath);

            } else {
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }
    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
    File file;
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null){
            String filePath = getRealPathFromURIPath(uri, AddBlockActivity.this);
            file = new File(filePath);
            // uploadImageToServer();
        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");

    }
    ProgressDialog pd;
    private void uploadImageToServer(){
        pd=new ProgressDialog(AddBlockActivity.this);
        pd.setTitle("Loading");
        pd.show();
        Map<String, String> map = new HashMap<>();
        map.put("bname",et_block_name.getText().toString());
        //map.put("price",et_price.getText().toString());


        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EndPointUrl uploadImage = retrofit.create(EndPointUrl.class);
        Call<ResponseData> fileUpload = uploadImage.blockAdd(fileToUpload, map);
        fileUpload.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                Toast.makeText(AddBlockActivity.this, "Data is submitted successfully. ", Toast.LENGTH_LONG).show();
                finish();
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(AddBlockActivity.this, "Error"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
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
