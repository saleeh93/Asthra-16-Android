package com.saleeh.astra;


import com.saleeh.astra.api.ClientConfig;
import com.saleeh.astra.api.ServiceAPI;


/**
 * Created by saleeh on 10/8/15.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ServiceAPI.initialize(
                this,
                new ClientConfig.Builder()
                        .setClientId("123")
                        .setClientSecret("123")
                        .setUrl("http://192.168.1.103/asthra16/web/api/")
                        .build());


    }
}
