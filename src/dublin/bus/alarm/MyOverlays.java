package dublin.bus.alarm;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
 
public class MyOverlays extends ItemizedOverlay<OverlayItem>{
 
private ArrayList<OverlayItem> overlayItemList = new ArrayList<OverlayItem>();
private Context context;
 

public MyOverlays(Context context, Drawable marker) {
	super(boundCenterBottom(marker));
	this.context = context;
	populate();
}
	 
	public void addItem(GeoPoint p, String title, String snippet){
	OverlayItem newItem = new OverlayItem(p, title, snippet);
	overlayItemList.add(newItem);
	   populate();
	}
	 
	@Override
	protected OverlayItem createItem(int i) {
	// TODO Auto-generated method stub
	return overlayItemList.get(i);
	}
	 
	@Override
	public int size() {
	// TODO Auto-generated method stub
	return overlayItemList.size();
	}
	 
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
	// TODO Auto-generated method stub
	super.draw(canvas, mapView, shadow);
	//boundCenterBottom(marker);
	}
 
	protected boolean onTap(int index) {
		final OverlayItem overlayItem = overlayItemList.get(index);
		final GeoPoint selectedPoint = overlayItem.getPoint();
		Builder builder = new AlertDialog.Builder(context);
		builder.setNeutralButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(context, setProximityAlertActivity.class);
                intent.putExtra("latitude", selectedPoint.getLatitudeE6());
                intent.putExtra("longitude", selectedPoint.getLongitudeE6());
                context.startActivity(intent);
            }
        });
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
		builder.setMessage("Select this Stop?");
		builder.setCancelable(true);
		
		AlertDialog dialog = builder.create();
		dialog.show();
		return true;
	};
}