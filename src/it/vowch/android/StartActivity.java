package it.vowch.android;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class StartActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    ParseFacebookUtils.initialize("231596146949539", true);
        setContentView(R.layout.start);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this, HomeActivity.class));        	
        } 
    }
	
	/** Called when the activity is first created. */
	public void loginWithFacebook(View view){
		final StartActivity that = this;
		ParseFacebookUtils.logIn(this, new LogInCallback() {
			@Override
			public void done(final ParseUser user, ParseException err) {
				if (user == null) {
					Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
				} else{
					if(user.isNew()) {
				    	/** Called when the activity is first created. */
			        	new Thread() {
			        	    public void run() {
			        	      try {
						        	Bundle args = new Bundle();
						            args.putString("fields", "name");
						            JSONObject currentUserJson = null;
						            try {
						            	currentUserJson = new JSONObject(ParseFacebookUtils.getFacebook().request("me", args));
									} catch (MalformedURLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									user.put("reputationPoints", 0.0);
									user.put("name", currentUserJson.getString("name"));
									user.saveInBackground(new SaveCallback(){
										public void done(ParseException e){
											startActivity(new Intent(that, HomeActivity.class));
										}
									});
			        	      } catch (Exception e) {
			    	          throw new RuntimeException(e);
			    	        }
			    	      }
			    	    }.start();
					}else{
						startActivity(new Intent(that, HomeActivity.class));
					}
				}
			}
		});
	}
	


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
	}
}