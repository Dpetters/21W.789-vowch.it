package it.vowch.android;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
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
        tv = (TextView)findViewById(R.id.reputation_level);
        
        
        testButton.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		if (click) {
        	         displayPopup(v);
        	        } else {
        	         popup.dismiss();
        	         click = true;
        	        }
        	}
        });

        ActionBar actionBar = getActionBar();
        if(actionBar != null){
        	actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
    
    @SuppressWarnings("deprecation")
	public void displayPopup(View v){
    	final Context context = v.getContext();
    	LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
    	
    	View popupView = inflater.inflate(R.layout.popup, null, false);
    	//popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
    	
    	//popup = new PopupWindow(popupView, popupView.getMeasuredHeight(), popupView.getMeasuredWidth());
    	popup = new PopupWindow(popupView, 350, 560, true);
    	
    	popup.setTouchable(true);
    	popup.setFocusable(true);
    	popup.setBackgroundDrawable(new BitmapDrawable());
    	popup.setOutsideTouchable(true);
    	
    	popup.setTouchInterceptor(new OnTouchListener() {
    		public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
                    popup.dismiss();
                    return true;
                }
                return false;
            }
    		
        });
    	popup.showAtLocation(popupView, Gravity.CENTER, 0, 0); 

    }
    
    public void showAbout(){
    	
    }
    
    public void logout(){
    	
    }

    public void showPreferences(){
    	
    }
    
}