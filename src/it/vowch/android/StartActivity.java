package it.vowch.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import com.facebook.android.*;
import com.facebook.android.Facebook.*;

public class StartActivity extends Activity {
	
	Facebook facebook = new Facebook(")7#F,(.4+bxG?b2bT42Z");
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        Button loginButton = (Button) findViewById(R.id.loginButton);
    	loginButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				//Do dat click thang
			}
		});
        
        facebook.authorize(this, new DialogListener() {
            @Override
            public void onComplete(Bundle values) {}

            @Override
            public void onFacebookError(FacebookError error) {}

            @Override
            public void onError(DialogError e) {}

            @Override
            public void onCancel() {}
        });
        
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebook.authorizeCallback(requestCode, resultCode, data);
    }
}