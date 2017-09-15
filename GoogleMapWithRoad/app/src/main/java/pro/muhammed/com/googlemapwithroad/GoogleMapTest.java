package pro.muhammed.com.googlemapwithroad;

import android.app.Application;

import okhttp3.OkHttpClient;

/**
 * Created by muhammed on 9/15/2017.
 */

public class GoogleMapTest extends Application {
    private static OkHttpClient mOkHttpClient;
    private static GoogleMapTest mInstance;

    public static GoogleMapTest getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mOkHttpClient = OkClientFactory.provideOkHttpClient(this);
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }
}
