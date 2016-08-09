package finder.phone.blackhunter.faizulhauqe.phonefinder;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;


public class StopTone extends AppCompatActivity {


	private Button stopTone;
	IncomingSms incomingSms;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_stop_tone);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.mipmap.app_icon);
		this.setFinishOnTouchOutside(false);
		stopTone = (Button) findViewById(R.id.stopTone);
		incomingSms = new IncomingSms();
		stopTone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				incomingSms.stopRingingTone();
				finish();
			}
		});
	}

	@Override
	public void onBackPressed() {

	}
}
