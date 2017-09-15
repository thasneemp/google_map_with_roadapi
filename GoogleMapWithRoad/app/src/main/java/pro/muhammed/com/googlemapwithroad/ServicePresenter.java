package pro.muhammed.com.googlemapwithroad;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by muhammed on 9/15/2017.
 */

public interface ServicePresenter {
    @GET("directions/json?mode=driving&units=metric&sensor=false")
    Call<GoogleDirectionResponse> getRouteInfo(@Query("origin") String origin, @Query("destination") String destination);
}
