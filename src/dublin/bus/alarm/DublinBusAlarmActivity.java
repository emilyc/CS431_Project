/*
 * Splashscreen - displays image for 5 seconds, then loads next activity
 * 
 */

package dublin.bus.alarm;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class DublinBusAlarmActivity extends Activity {

	private long SPLASH_DISPLAY_LENGTH = 5000;
	

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.splashscreen); // bind the layout to the activity

		TimerTask task = new TimerTask(){
			@Override
			public void run(){
				finish();
				Intent intent = new Intent(DublinBusAlarmActivity.this, SelectRouteActivity.class);
				startActivity(intent);
			
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(task, SPLASH_DISPLAY_LENGTH);
		
		
		
	}
		

}