package it.vowch.android;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

import android.app.Application;

public class DefaultApplication extends Application {
	@Override
	public void onCreate() {
	    super.onCreate();
	    Parse.initialize(this, "DXxAwRJUW4EqeBcikp1z3xSkA6115g0AAdqvwh6T", "WqhAAGDu4uDODZ7tbAWQtDkyVaPccbwm0syyzrSA");
	}
}