package com.saleeh.astra.api.rest;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * @author Saleeh
 */
public class RestAdapterFactory {
    public static final String TAG = RestAdapterFactory.class.getSimpleName();
    private final Application application;
    public String endPoint;
    private final Interceptor interceptor;
    public File cacheDir;
    private Gson gson;
    //   private final OkClient mOkClient;

    public RestAdapterFactory(Application application, String endPoint, Interceptor interceptor) {
        this.application = application;
        this.endPoint = endPoint;
        this.interceptor = interceptor;
        cacheDir = application.getCacheDir();


    }

    public Retrofit provideWebApiAdapter(Interceptor requestInterceptor) {
        OkHttpClient ok = new OkHttpClient();
        ok.setReadTimeout(30, TimeUnit.SECONDS);
        ok.setConnectTimeout(30, TimeUnit.SECONDS);
        ok.networkInterceptors().add(interceptor);
        ok.networkInterceptors().add(requestInterceptor);


        return new Retrofit.Builder()
                //  .setLogLevel(RestAdapter.LogLevel.FULL)
                .client(ok)

                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .serializeNulls()
                        .create()))
//                .addConverterFactory(GsonConverterFactory.create()).
/*
                .addConverterFactory(new GsonConverter(new GsonBuilder()
                        .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                        .serializeNulls()
                        .create()))
                        */
                .baseUrl(endPoint)
                        //       .setRequestInterceptor(requestInterceptor)
                .build();
    }


}
