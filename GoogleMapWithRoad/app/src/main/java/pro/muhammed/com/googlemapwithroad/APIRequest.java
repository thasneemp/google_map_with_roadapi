package pro.muhammed.com.googlemapwithroad;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class APIRequest {
    static Retrofit retrofit;
    static Retrofit retrofitGoogle;

    private APIRequest() {
    }


    private static Retrofit provideGoogleRestAdapter() {
        if (retrofitGoogle == null)
            retrofitGoogle = new Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/maps/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(GoogleMapTest.getInstance().getOkHttpClient())
                    .build();

        return retrofitGoogle;
    }


    public static ServicePresenter providePlaceInterface() {
        return provideGoogleRestAdapter().create(ServicePresenter.class);
    }
}
