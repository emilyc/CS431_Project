package dublin.bus.alarm;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectRouteActivity extends ListActivity {
	static final String[] ROUTES = new String[] {"Please Select a Bus Route","66", "67", "66A"};
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.route_list, ROUTES));

		  ListView lv = getListView();
		  lv.setTextFilterEnabled(true);

		  lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		      // When clicked, show a toast with the TextView text
		    	Intent intent = new Intent(SelectRouteActivity.this, SelectDirectionActivity.class);
		    	intent.putExtra("selectedRoute", view.toString());
				SelectRouteActivity.this.startActivity(intent);
		    }
		  });
		}
}



