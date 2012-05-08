package it.vowch.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.parse.Parse;
import com.parse.ParseObject;

public class RegisterActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Parse.initialize(this, "DXxAwRJUW4EqeBcikp1z3xSkA6115g0AAdqvwh6T", "WqhAAGDu4uDODZ7tbAWQtDkyVaPccbwm0syyzrSA");
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.home, menu);
        return(super.onCreateOptionsMenu(menu));
    }
    
	public void viewNewVowActivity(View view){
		startActivity(new Intent(this, NewVowActivity.class));
	}

	public void viewProfileActivity(View view){
		startActivity(new Intent(this, ProfileActivity.class));
	}

	public void viewStreamActivity(View view){
		startActivity(new Intent(this, StreamActivity.class));
	}
}