package it.vowch.android;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ProfileActivity extends ListActivity {
    /** Called when the activity is first created. */
	protected Button testButton;
	protected PopupWindow popup;
    protected LinearLayout layout;
    protected boolean click = true;
    protected TextView tv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        
        testButton = (Button)findViewById(R.id.testButton);
        popup = new PopupWindow(this);
        tv = (TextView)findViewById(R.id.reputation_level);
        
        testButton.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		if (click) {
        	         popup.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
        	         popup.update(50, 50, 300, 80);
        	         click = false;
        	        } else {
        	         popup.dismiss();
        	         click = true;
        	        }
        	}


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.profile, menu);
        return(super.onCreateOptionsMenu(menu));
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
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
    	
    }

    public void showPreferences(){
    	
    }
}