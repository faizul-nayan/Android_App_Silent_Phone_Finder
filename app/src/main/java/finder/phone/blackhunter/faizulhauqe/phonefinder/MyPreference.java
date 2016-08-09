package finder.phone.blackhunter.faizulhauqe.phonefinder;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreference {

	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	Context context;
	private boolean checked;
	private String code;

	public MyPreference(Context context) {
		this.context = context;
		sharedPreferences = context.getSharedPreferences("myPrefs",
				context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
	}
	
	public MyPreference(Context context, boolean checked, String code) {
		this(context);
		this.context = context;
		this.checked = checked;
		this.code = code;

		//sharedPreferences = context.getSharedPreferences("myPrefs",
		//		context.MODE_PRIVATE);
		//editor = sharedPreferences.edit();
		editor.putBoolean("onOff", checked);
		editor.putString("code", code);
		editor.commit();
	}
	
	public MyPreference(Context context, String code) {
		this(context);
		this.context = context;
		this.code = code;
		
		editor.putString("code", code);
		editor.commit();
	}
	
	public MyPreference(Context context, boolean checked){
		this(context);
		this.context = context;
		this.checked = checked;
		
		editor.putBoolean("onOff", checked);
		editor.commit();
	}
	

	public boolean isOn(){
		boolean checked = sharedPreferences.getBoolean("onOff", false);
		if(checked)
			return checked;
		else
			return false;
	}
	
	public String getCode() {
		String code = sharedPreferences.getString("code", null);
		return code;
	}

}
