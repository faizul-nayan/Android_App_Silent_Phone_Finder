package finder.phone.blackhunter.faizulhauqe.phonefinder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class IncomingSms extends BroadcastReceiver {

	MyPreference myPreference;
	private String message;
	private Uri uri;
	private static Ringtone ringtone = null;
	private static Vibrator vibrator = null;
	final SmsManager sms = SmsManager.getDefault();

	public void onReceive(Context context, Intent intent) {

		uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		ringtone = RingtoneManager.getRingtone(context, uri);

		vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

		myPreference = new MyPreference(context);
		String code = myPreference.getCode();
		// Retrieves a map of extended data from the intent.
		final Bundle bundle = intent.getExtras();

		try {

			if (bundle != null) {

				final Object[] pdusObj = (Object[]) bundle.get("pdus");

				for (int i = 0; i < pdusObj.length; i++) {

					SmsMessage currentMessage = SmsMessage
							.createFromPdu((byte[]) pdusObj[i]);
					String phoneNumber = currentMessage
							.getDisplayOriginatingAddress();

					String senderNum = phoneNumber;
					message = currentMessage.getDisplayMessageBody();

					Log.i("SmsReceiver", "senderNum: " + senderNum
							+ "; message: " + message);

				} // end for loop
				if (message.equals(code)) {
					Toast toast1 = Toast.makeText(context, "CODE MATCH",
							Toast.LENGTH_LONG);
					toast1.show();
					doAction(context);
				}
			} // bundle is null

		} catch (Exception e) {
			Log.e("SmsReceiver", "Exception smsReceiver" + e);

		}
	}

	private void doAction(Context context) {

		AudioManager audioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

		try {
			long[] pattern = {0, 5000, 2000};
			ringtone.play();
			vibrator.vibrate(pattern,0);
			Intent stopIntent = new Intent(context, StopTone.class);
			stopIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(stopIntent);

		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
		}

	}

	public static void stopRingingTone(){
		vibrator.cancel();
		ringtone.stop();
	}

}
