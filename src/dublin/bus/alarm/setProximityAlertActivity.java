package dublin.bus.alarm;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;


public class setProximityAlertActivity extends Activity {
    
    private static final long MINIMUM_DISTANCECHANGE_FOR_UPDATE = 1; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATE = 1000; // in Milliseconds
    
    private static final long POINT_RADIUS = 1000; // in Meters
    private static final long PROX_ALERT_EXPIRATION = -1; 

    private static final String POINT_LATITUDE_KEY = "POINT_LATITUDE_KEY";
    private static final String POINT_LONGITUDE_KEY = "POINT_LONGITUDE_KEY";
    
    static final String PROX_ALERT_INTENT = 
         "You are near your stop!";
    
    
    private LocationManager locationManager;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent sender=getIntent();
        int latitude=sender.getExtras().getInt("latitude");
        int longitude=sender.getExtras().getInt("longitude");
        
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 
                        MINIMUM_TIME_BETWEEN_UPDATE, 
                        MINIMUM_DISTANCECHANGE_FOR_UPDATE,
                        new MyLocationListener()
        );

                addProximityAlert(latitude, longitude);

       
    }


    private void addProximityAlert(double latitude, double longitude) {
        
        Intent intent = new Intent(PROX_ALERT_INTENT);
        PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        
        locationManager.addProximityAlert(
            latitude, // the latitude of the central point of the alert region
            longitude, // the longitude of the central point of the alert region
            POINT_RADIUS, // the radius of the central point of the alert region, in meters
            PROX_ALERT_EXPIRATION, // time for this proximity alert, in milliseconds, or -1 to indicate no expiration 
            proximityIntent // will be used to generate an Intent to fire when entry to or exit from the alert region is detected
       );
        
       IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);  
       registerReceiver(new ProximityIntentReceiver(), filter);
       
    }

    private Location retrievelocationFromPreferences() {
    	       SharedPreferences prefs =
    		           this.getSharedPreferences(getClass().getSimpleName(),
    	                           Context.MODE_PRIVATE);
    	        Location location = new Location("POINT_LOCATION");
    		        location.setLatitude(prefs.getFloat(POINT_LATITUDE_KEY, 0));
    	        location.setLongitude(prefs.getFloat(POINT_LONGITUDE_KEY, 0));
    		        return location;
    }
  	
    
    public class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
            Location pointLocation = retrievelocationFromPreferences();
            float distance = location.distanceTo(pointLocation);
            Toast.makeText(setProximityAlertActivity.this, 
                    "Distance from Point:"+distance, Toast.LENGTH_LONG).show();
        }
        public void onStatusChanged(String s, int i, Bundle b) {            
        }
        public void onProviderDisabled(String s) {
        }
        public void onProviderEnabled(String s) {            
        }
    }
    
}