package it.vowch.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

public class HomeActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
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
