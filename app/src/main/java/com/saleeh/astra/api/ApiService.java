package com.saleeh.astra.api;


import com.saleeh.astra.api.models.Event;
import com.saleeh.astra.api.models.Group;
import com.saleeh.astra.api.models.Participant;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by saleeh on 8/9/15.
 */
public interface ApiService {

    @GET("groups")
    Call<List<Group>> groups();

    @GET("events")
    Call<List<Event>> events();

    @GET("participant")
    Call<List<Participant>> participant(@Query("event") String eventId);


    @GET("result")
    Call<List<Participant>> results(@Query("event") String eventId);


}
