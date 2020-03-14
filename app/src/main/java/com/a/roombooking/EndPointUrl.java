package com.a.roombooking;


import com.a.roombooking.model.AdminGetAllRoomsPojo;
import com.a.roombooking.model.AdminGetBookedRoomsPojo;
import com.a.roombooking.model.AdminNotBookedRoomsPojo;
import com.a.roombooking.model.AvalableRoomsPojo;
import com.a.roombooking.model.BlockPOJO;
import com.a.roombooking.model.EditProfilePojo;
import com.a.roombooking.model.GetBlocksPojo;
import com.a.roombooking.model.MyBookedRoomsPojo;
import com.a.roombooking.model.ReqPojo;
import com.a.roombooking.model.RoomsPOJO;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface EndPointUrl {


    @GET("RoomBooking/user_registration.php")
    Call<ResponseData> user_registration(
            @Query("name") String name,
            @Query("phone") String phone,
            @Query("emailid") String emailid,
            @Query("uname1") String uname1,
            @Query("pwd1") String pwd

    );

    @GET("RoomBooking/user_login.php")
    Call<ResponseData> user_login(
            @Query("uname") String uname,
            @Query("pwd") String pwd
    );

    @GET("/RoomBooking/admin_login.php?")
    Call<ResponseData> admin_login(
            @Query("uname") String uname,
            @Query("pwd") String pwd
    );

    @GET("RoomBooking/getUserProfile.php")
    Call<List<EditProfilePojo>> getUserProfile
            (
                    @Query("uname") String uname
            );


    @GET("RoomBooking/update_user_profile.php")
    Call<ResponseData> update_user_profile(
            @Query("name") String name,
            @Query("phone") String phone,
            @Query("emailid") String emailid,
            @Query("pwd1") String pwd1,
            @Query("uname1") String uname1
    );

    /*@GET("RoomBooking/add_block.php?")
    Call<ResponseData> blockAdd(
            @Query("bname") String bname,
            @Query("img_url") String img_url
    );*/

    @Multipart
    @POST("RoomBooking/add_block.php?")
    Call<ResponseData> blockAdd(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap

    );

    @GET("RoomBooking/get_blocks.php")
    Call<List<GetBlocksPojo>> get_blocks();

    @GET("RoomBooking/get_blocks.php")
    Call<List<BlockPOJO>> getblock();

    @GET("RoomBooking/get_rooms.php")
    Call<List<RoomsPOJO>> getRooms(@Query("bname") String bname);


    /*@GET("RoomBooking/add_room.php?")
    Call<ResponseData> addRoom(
            @Query("bname") String bname,
            @Query("rname") String rname,
            @Query("capacity") String capacity,
            @Query("software") String software,
            @Query("hardware") String hardware,
            @Query("descrip") String descrip
    );*/

    @Multipart
    @POST("RoomBooking/add_room.php?")
    Call<ResponseData> addRoom(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap

    );

    @GET("/RoomBooking/edit_room_details.php?")
    Call<ResponseData> edit_room_details(
            @Query("id") String id,
            @Query("capacity") String capacity,
            @Query("software") String software,
            @Query("hardware") String hardware,
            @Query("descrip") String descrip
    );

    @GET("RoomBooking/getAvailableRooms.php")
    Call<List<AvalableRoomsPojo>> getAvailableRooms();

    @GET("/RoomBooking/getMyBookedRooms.php?")
    Call<List<MyBookedRoomsPojo>> getMyBookedRooms(@Query("uname") String uname);

    @GET("RoomBooking/cancelMyBooking.php")
    Call<ResponseData> cancelMyBooking(@Query("id") String id);

    @GET("RoomBooking/getNotBookedRooms.php")
    Call<List<AdminNotBookedRoomsPojo>> getNotBookedRooms();

    @GET("RoomBooking/getBookedRooms.php")
    Call<List<AdminGetBookedRoomsPojo>> getBookedRooms();

    @GET("/RoomBooking/getAllRooms.php")
    Call<List<AdminGetAllRoomsPojo>> getAllRooms();

    @GET("RoomBooking/get_software.php")
    Call<List<ReqPojo>> get_software();

    @GET("/RoomBooking/get_hardware.php")
    Call<List<ReqPojo>> get_hardware();

    @GET("/RoomBooking/get_others.php")
    Call<List<ReqPojo>> get_others();


    @GET("RoomBooking/bookMyRoom.php")
    Call<ResponseData> bookMyRoom(
            @Query("id") String id,
            @Query("booked_by") String booked_by,
            @Query("booked_date") String booked_date,
            @Query("booked_time") String booked_time,
            @Query("end_date") String end_date,
            @Query("software") String software,
            @Query("hardware") String hardware,
            @Query("reason_for_booking") String reason_for_booking,
            @Query("other") String other,
            @Query("duration") String duration
    );


}