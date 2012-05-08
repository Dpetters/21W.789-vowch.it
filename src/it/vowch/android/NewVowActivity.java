package it.vowch.android;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class NewVowActivity extends Activity {
    private Spinner periodSpinner;
    private Spinner lengthUnitSpinner;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_vow);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
        	startActivity(new Intent(this, StartActivity.class));
        } 
        
        periodSpinner = (Spinner) findViewById(R.id.period);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.periods_array, R.layout.new_vow_spinner_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodSpinner.setAdapter(adapter);

        lengthUnitSpinner = (Spinner) findViewById(R.id.length_unit);
        adapter = ArrayAdapter.createFromResource(this, R.array.length_unit_array, R.layout.new_vow_spinner_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lengthUnitSpinner.setAdapter(adapter);
        
        ActionBar actionBar = getActionBar();
        if(actionBar != null){
        	actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void Activity(MenuItem menuItem){
		startActivity(new Intent(this, NewVowActivity.class));
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.new_vow, menu);
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
    
    public void makeVow(View view){
    	EditText vowTextView = (EditText) findViewById(R.id.vow_text);
    	Editable vowTextEditable = vowTextView.getText();
    	if(vowTextEditable.length()==0){
        	vowTextView.setError("Please decide what you want to do!");
        	return;
    	}
    	EditText lengthNumView = (EditText) findViewById(R.id.length_num);
    	Editable lengthNumEditable = lengthNumView.getText();
    	if(lengthNumEditable.length()==0){
    		lengthNumView.setError("Please decide how long you wish to vow for!");
        	return;
    	}
    	
    	ParseObject vow = new ParseObject("Vow");
    	vow.put("totalOccurences", 0);
    	vow.put("successfulOccurences", 0);
    	
    	vow.put("text", vowTextEditable.toString());
    	vow.put("lengthNum", Integer.parseInt(lengthNumEditable.toString()));
    	
    	String selectedPeriod = (String) periodSpinner.getSelectedItem();
    	vow.put("period", selectedPeriod);
    	
    	String selectedLengthUnit = (String) lengthUnitSpinner.getSelectedItem();
    	vow.put("lengthUnit", selectedLengthUnit);
    	
    	vow.saveInBackground();
    	
		startActivity(new Intent(this, ProfileActivity.class));
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