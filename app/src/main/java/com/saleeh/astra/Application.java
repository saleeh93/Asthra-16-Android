package com.saleeh.astra;


import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.saleeh.astra.api.ClientConfig;
import com.saleeh.astra.api.ServiceAPI;
import com.saleeh.astra.api.models.User;


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
                        .setUrl("http://asthra16.com/api/")
                        .build());
        ParseObject.registerSubclass(User.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("asthra")
                .clientKey("asthra")
                .server("http://api.asthra16.com/parse/").build());
        ParseUser.enableAutomaticUser();
        ParseInstallation.getCurrentInstallation().saveInBackground();


    }
}
