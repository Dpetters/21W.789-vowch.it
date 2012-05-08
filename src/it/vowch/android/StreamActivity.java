package it.vowch.android;

import it.vowch.android.adapters.EvidenceAdapter;
import it.vowch.android.data.Evidence;

import com.google.gson.Gson;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class StreamActivity extends ListActivity {
    /** Called when the activity is first created. */
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String json = "["
                        + "{'user':{'firstName': 'Dmitrij', 'lastName':'Petters', 'username':'dpetters', imageUri':'https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash2/368918_607193525_243490091_q.jpg'},"
                        + "'action':'did 10 push-ups before bed.'"
                        + "}"
                        + "]";
        
        Evidence[] evidence = new Gson().fromJson(json, Evidence[].class);
        
    	/** Called when the activity is first created. */
        setContentView(R.layout.stream);
        
        ActionBar actionBar = getActionBar();
        if(actionBar != null){
        	actionBar.setDisplayHomeAsUpEnabled(true);
        }
        
        setListAdapter(new EvidenceAdapter(this, evidence));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.stream, menu);
        return(super.onCreateOptionsMenu(menu));
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; go home
	            Intent intent = new Intent(this, HomeActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            return true;
            case R.id.about:
                showInfo();
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
    
    public void showInfo(){
    	
    }
    
    public void logout(){
    	
    }

    public void showPreferences(){
    	
    }
}