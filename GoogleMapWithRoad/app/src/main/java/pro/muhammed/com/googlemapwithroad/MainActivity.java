package pro.muhammed.com.googlemapwithroad;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, RoutingListener {

    private GoogleMap googleMap;
    private Routing routing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                try {
                    ArrayList<Route> routes = routing.get();
                    boolean locationOnPath = PolyUtil.isLocationOnPath(latLng, routes.get(0).getPoints(), true, 50);
                    if (locationOnPath) {
                        Toast.makeText(MainActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

//        Call<GoogleDirectionResponse> routeInfo = APIRequest.providePlaceInterface().getRouteInfo("", "");
//
//        routeInfo.enqueue(new Callback<GoogleDirectionResponse>() {
//            @Override
//            public void onResponse(Call<GoogleDirectionResponse> call, Response<GoogleDirectionResponse> response) {
//                List<LatLng> list = decodePoly((response.body().getRoutes().get(0).getOverviewPolyline().getPoints()));
//
//            }
//
//            @Override
//            public void onFailure(Call<GoogleDirectionResponse> call, Throwable t) {
//
//            }
//        });
        LatLng start = new LatLng(10.016056, 76.341847);
//        LatLng waypoint = new LatLng(18.01455, -77.499333);
        LatLng end = new LatLng(9.957815, 76.358412);
        routing = new Routing.Builder().travelMode(Routing.TravelMode.DRIVING)
                .withListener(MainActivity.this).waypoints(start, end)
                .key("AIzaSyBkBF07tPOxZVC9c7PhKPGGrWPn3z0_QO8").build();
        routing.execute();


    }

    @Override
    public void onRoutingFailure(RouteException e) {
        Log.d("test", "test");
    }

    @Override
    public void onRoutingStart() {
        Log.d("test", "test");
    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> arrayList, int i) {
        googleMap.addPolyline(new PolylineOptions()
                .addAll(arrayList.get(0).getPoints())
                .width(12)
                .color(Color.parseColor("#A299F6"))//Google maps blue color
                .geodesic(true));


    }

    @Override
    public void onRoutingCancelled() {
        Log.d("test", "test");
    }

//
//    private List<LatLng> decodePoly(String encoded) {
//
//        List<LatLng> poly = new ArrayList<LatLng>();
//        int index = 0, len = encoded.length();
//        int lat = 0, lng = 0;
//
//        while (index < len) {
//            int b, shift = 0, result = 0;
//            do {
//                b = encoded.charAt(index++) - 63;
//                result |= (b & 0x1f) << shift;
//                shift += 5;
//            } while (b >= 0x20);
//            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
//            lat += dlat;
//
//            shift = 0;
//            result = 0;
//            do {
//                b = encoded.charAt(index++) - 63;
//                result |= (b & 0x1f) << shift;
//                shift += 5;
//            } while (b >= 0x20);
//            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
//            lng += dlng;
//
//            LatLng p = new LatLng((((double) lat / 1E5)),
//                    (((double) lng / 1E5)));
//            poly.add(p);
//        }
//
//        return poly;
//    }


}
