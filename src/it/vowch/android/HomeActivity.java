package it.vowch.android;

import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
        	startActivity(new Intent(this, StartActivity.class));
        } 
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

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
	        case android.R.id.home:
	            return true;
	        case R.id.about:
                showAbout();
                return true;
            case R.id.logout:
                logout();
                return true;
            case R.id.preferences:
                showPreferences();
            	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void showAbout(){
    	
    }
    
    public void logout(){
    	ParseUser.logOut();
    	startActivity(new Intent(this, StartActivity.class));
    }

    public void showPreferences(){
    	
    }
}
