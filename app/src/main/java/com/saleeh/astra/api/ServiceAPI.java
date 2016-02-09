package com.saleeh.astra.api;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.saleeh.astra.api.rest.RestAdapterFactory;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.Retrofit;


/**
 * Created by saleeh on 8/9/15.
 */
public class ServiceAPI {
    public static final String TAG = ServiceAPI.class.getSimpleName();
    private static volatile ServiceAPI sSingleton;
    private final Application mApplication;
    private final ClientConfig mConfig;
    private final RestAdapterFactory mAdapterFactory;
    private ApiService mAPIService;
    private ApiService mCachedAPIService;

    /* package */ ServiceAPI(Application application, ClientConfig config) {
        mApplication = application;
        mConfig = config;
        mAdapterFactory = new RestAdapterFactory(application, mConfig.getUrl(), mCacheControlInterceptor);
    }

    /**
     * Initialize the singleton instance of this class.
     *
     * @param application the application.
     * @param config      your APIService API configuration.
     */
    public static void initialize(@NonNull Application application, @NonNull ClientConfig config) {
        if (sSingleton == null) {
            synchronized (ServiceAPI.class) {
                if (sSingleton == null) {
                    sSingleton = new ServiceAPI(application, config);
                }
            }
        }
    }

    public static synchronized ServiceAPI getInstance() {
        if (sSingleton == null) {
            throw new IllegalStateException("APIService is not yet initialized.");
        }
        return sSingleton;
    }

    /**
     * @return The ApiService instance to access Web API
     */
    public synchronized ApiService getApiService() {
        if (mAPIService == null) {
            Retrofit adapter = mAdapterFactory.provideWebApiAdapter(new WebApiAuthenticator());
            mAPIService = adapter.create(ApiService.class);
        }
        return mAPIService;
    }


    /**
     * The request interceptor that will add the header with OAuth
     * token to every request made swith the wrapper.
     */
    private class WebApiAuthenticator implements Interceptor {


        @Override
        public Response intercept(Chain chain) throws IOException {

            //      String mApiKey = (User.Response.isLogged()) ? User.Response.loadResponse().token : "";
            HttpUrl url = chain.request().httpUrl()
                    .newBuilder()
                    //   .addQueryParameter("access_token", mApiKey)
                    .build();
            Request request = chain.request().newBuilder().url(url).build();
            return chain.proceed(request);

        }
    }

    private final Interceptor mCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();


            // Add Cache Control only for GET methods
            /*
            if (request.method().equals("GET")) {
                if (isNetworkAvailable(mApplication)) {
                    // 1 day
                    request.newBuilder()
                            .header("Cache-Control", "only-if-cached")
                            .header("Pragma", "only-if-cached")
                            .build();
                } else {
                    // 4 weeks stale
                    request.newBuilder()
                            .header("Cache-Control", "public, max-stale=2419200")
                            .header("Pragma", "public, max-stale=2419200")
                            .build();
                }
            }
            */


            Response response = chain.proceed(request);

            // Re-write response CC header to force use of cache
            return response.newBuilder()
                    //     .header("Cache-Control", "public, max-age=86400") // 1 day
                    //   .header("Pragma", "public, max-age=86400")
                    .build();
        }
    };

    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
