package finder.phone.blackhunter.faizulhauqe.phonefinder;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private Switch switch1;
	private TextView valueTV;
	MyPreference myPreference;
	private boolean checked = false;
	private String codeValue;
	private Button codeBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setIcon(R.mipmap.app_icon);
		setContentView(R.layout.activity_main);

		switch1 = (Switch) findViewById(R.id.switch1);
		switch1.setBackgroundColor(Color.BLACK);
		valueTV = (TextView) findViewById(R.id.valueTV);
		codeBtn = (Button) findViewById(R.id.codeBtn);
		codeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						SecurityCode.class);
				startActivity(intent);
			}
		});

		myPreference = new MyPreference(this);

		checked = myPreference.isOn();
		codeValue = myPreference.getCode();

		if (checked) {
			switch1.setChecked(true);
			valueTV.setText(codeValue);
			enableBroadcastReceiver();
		} else {
			switch1.setChecked(false);
			disableBroadcastReceiver();
		}

		switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (codeValue != null) {
						MyPreference preference = new MyPreference(
								MainActivity.this, true);
						valueTV.setText(myPreference.getCode());
						enableBroadcastReceiver();
					} else {
						Toast.makeText(getApplicationContext(),
								"Set Security Code First", Toast.LENGTH_LONG)
								.show();
						switch1.setChecked(false);
						disableBroadcastReceiver();
					}

				} else {
					MyPreference preference = new MyPreference(
							MainActivity.this, false);
					valueTV.setText("");
					disableBroadcastReceiver();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.securityCode:
			Intent intent = new Intent(this, SecurityCode.class);
			startActivity(intent);
			break;
		case R.id.about:
			Intent intentAbout = new Intent(getApplicationContext(),
					AboutApp.class);
			startActivity(intentAbout);
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
			alt_bld.setIcon(R.mipmap.app_icon);
			alt_bld.setMessage("Are you sure you want to exit?")
					.setCancelable(false)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// Action for 'Yes' Button

									Toast.makeText(
											getApplicationContext(),
											"Thank you for using Silent Phone Finder",
											Toast.LENGTH_LONG).show();
									finish();

								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// Action for 'NO' Button

									dialog.cancel();
								}
							});
			AlertDialog alert = alt_bld.create();
			// Title for AlertDialog
			alert.setTitle("Exit Silent Phone Finder?");
			// Icon for AlertDialog
			alert.show();

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	private void enableBroadcastReceiver() {
		PackageManager packageManager = this.getPackageManager();
		ComponentName componentName = new ComponentName(
				getApplicationContext(), IncomingSms.class);
		packageManager.setComponentEnabledSetting(componentName,
				PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
				PackageManager.DONT_KILL_APP);
	}
	
	private void disableBroadcastReceiver() {
		PackageManager packageManager = this.getPackageManager();
		ComponentName componentName = new ComponentName(
				getApplicationContext(), IncomingSms.class);
		packageManager.setComponentEnabledSetting(componentName,
				PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
				PackageManager.DONT_KILL_APP);
	}

}
