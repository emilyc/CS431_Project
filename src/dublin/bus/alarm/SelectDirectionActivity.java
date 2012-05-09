/*
 * Selected direction (inbound or outbound) of the selected route 
 * and call selectStopActivity to populate the math with the correct stops
 */

package dublin.bus.alarm;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectDirectionActivity extends ListActivity {
	static final String[] DIRECTION = new String[] {"Please Select a Direction","Straffan Road to Merrion Sq South (Inbound)", "Merrion Sq South to Straffan Road (Outbound)"};
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.route_list, DIRECTION));

		  ListView lv = getListView();
		  lv.setTextFilterEnabled(true);

		  lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		    	Intent intent = new Intent(SelectDirectionActivity.this, SelectStopActivity.class);
		    	intent.putExtra("direction", view.toString());
				SelectDirectionActivity.this.startActivity(intent);
		    }
		  });
		}
}
