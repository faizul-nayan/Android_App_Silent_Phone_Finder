package finder.phone.blackhunter.faizulhauqe.phonefinder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecurityCode extends AppCompatActivity {

	EditText securityCodeEditText;
	Button saveButton;
	Button homeButton;

	MyPreference myPreference;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setIcon(R.mipmap.app_icon);
		setContentView(R.layout.activity_security_code);

		securityCodeEditText = (EditText) findViewById(R.id.securityCodeEditText);
		saveButton = (Button) findViewById(R.id.saveButton);
		homeButton = (Button) findViewById(R.id.homeButton);
		myPreference = new MyPreference(this);
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!securityCodeEditText.getText().toString().equals("")) {
					MyPreference preference = new MyPreference(
							getApplicationContext(), securityCodeEditText
									.getText().toString());
					securityCodeEditText.setText("");
					Toast.makeText(getApplicationContext(),
							"Security Code Saved", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getApplicationContext(),
							"Enter your Security Code", Toast.LENGTH_LONG)
							.show();
					securityCodeEditText.requestFocus();
				}
			}
		});

		homeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SecurityCode.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.security_code, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.securityHome:
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}
