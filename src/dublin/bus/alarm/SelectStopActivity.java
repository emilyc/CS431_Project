package dublin.bus.alarm;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class SelectStopActivity extends MapActivity {

	private MapController mapController;
	private MapView mapView;
	private MyLocationOverlay myLocationOverlay;
	
	double[][]latlongInbound = new double[][]{{53.373099,-6.589307},{53.376805,-6.587585}, {53.381939,-6.590064}, {53.382113,-6.585515}, {53.378015,-6.552681},{53.374112,-6.526527}, {53.373768,-6.524759},{53.370961,-6.512361}, {53.369497,-6.505855}, {53.367797,-6.499821},{53.365747,-6.492592}, {53.364488,-6.487337},{53.361831,-6.483477},{53.360535,-6.478311},{53.359182,-6.473211},{53.355511,-6.460186},{53.355596,-6.454997},{53.356548,-6.447918},{53.35808,-6.441832},{53.359634,-6.439502},{53.360088,-6.435157}, {53.359733,-6.430949},{53.359335,-6.424382}, {53.359035,-6.420701},{53.359139,-6.414671},{53.3585,-6.408861},{53.357754,-6.4052},{53.357514,-6.402779}, {53.35611,-6.391906}, {53.354924,-6.371259}, {53.353924,-6.366989},{53.351967,-6.355685},{53.35088,-6.351592},{53.349464,-6.348272},{53.348306,-6.343637},{53.346751,-6.336281},{53.346318,-6.327864},{53.346528,-6.321124},{53.347435,-6.315308},{53.348126,-6.310968},{53.348427,-6.305953}, {53.348327,-6.301091},{53.348299,-6.29853},{53.347944,-6.292341},{53.347003,-6.284664},{53.345941,-6.276009},{53.345648,-6.272935},{53.345913,-6.269233},{53.346928,-6.262051},{53.345481,-6.257689},{53.342617,-6.256194},{53.340797,-6.250857}};

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.main); // bind the layout to the activity
		Intent sender=getIntent();
        String direction=sender.getExtras().getString("direction");
        
		
		// Configure the Map
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapController = mapView.getController();
		mapController.setZoom(14); // Zoon 1 is world view
		

		myLocationOverlay = new MyLocationOverlay(this, mapView);
		mapView.getOverlays().add(myLocationOverlay);
		
		Drawable marker=getResources().getDrawable(android.R.drawable.star_big_on);
	    int markerWidth = marker.getIntrinsicWidth();
	    int markerHeight = marker.getIntrinsicHeight();
	    marker.setBounds(0, markerHeight, markerWidth, 0);
	    
	    MyOverlays myItemizedOverlay = new MyOverlays(this, marker);
	     mapView.getOverlays().add(myItemizedOverlay);
	   

	     for(int i=0; i<latlongInbound.length; i++)
	     {
	    		 GeoPoint tempPoint = new GeoPoint((int) (latlongInbound[i][0]*1000000), (int) (latlongInbound[i][1]*1000000));
	    		 myItemizedOverlay.addItem(tempPoint, "myPoint"+i, "myPoint"+i);

	     }
	}

	
	@Override
	protected boolean isLocationDisplayed() {
	// TODO Auto-generated method stub
	return false;
	}
	 
	@Override
	protected boolean isRouteDisplayed() {
	// TODO Auto-generated method stub
	return false;
	}
}
