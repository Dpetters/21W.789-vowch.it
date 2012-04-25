package it.vowch.android;

import it.vowch.android.adapters.GoalAdapter;
import it.vowch.android.data.Goal;

import com.google.gson.Gson;

import android.app.ActionBar;
import android.app.Dialog;
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
    private static final int CAMERA_PIC_REQUEST = 1;
    private static final int CAMERA_VIDEO_REQUEST = 2;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String json = "["
                        + "{'title' : 'Run 2 miles',"
                        + "'schedule' : 'Every Monday, Thursday for 2 weeks',"
                        + "'maxOccurences' : '4',"
                        + "'occurences' : '3',"
                        + "'grade' : 'A+',"
                        + "'successRate' : '100',"
                        + "'minutesLeft' : '45'"
                        + "}"
                        + ","
                        + "{'title' : 'Have a no-bro day',"
                        + "'schedule' : 'Every week for 8 months',"
                        + "'maxOccurences' : '35',"
                        + "'occurences' : '5',"
                        + "'grade' : 'B+',"
                        + "'successRate' : '89',"
                        + "'minutesLeft' : '1000'"
                        + "}"
                        + ","
                        + "{'title' : 'Do 10 push-ups before going to bed',"
                        + "'schedule' : 'Every day for a year',"
                        + "'maxOccurences' : '365',"
                        + "'occurences' : '167',"
                        + "'grade' : 'A',"
                        + "'successRate' : '93',"
                        + "'minutesLeft' : '456'"
                        + "}"
                        + ","
                        + "{'title' : 'Have a no-bro day',"
                        + "'schedule' : 'Every week for 8 months',"
                        + "'maxOccurences' : '35',"
                        + "'occurences' : '5',"
                        + "'grade' : 'B+',"
                        + "'successRate' : '89',"
                        + "'minutesLeft' : '1000'"
                        + "}"
                        + ","
                        + "{'title' : 'Do 10 push-ups before going to bed',"
                        + "'schedule' : 'Every day for a year',"
                        + "'maxOccurences' : '365',"
                        + "'occurences' : '167',"
                        + "'grade' : 'A',"
                        + "'successRate' : '93',"
                        + "'minutesLeft' : '456'"
                        + "}"
                        + ","
                        + "{'title' : 'Run 2 miles',"
                        + "'schedule' : 'Every Monday, Thursday for 2 weeks',"
                        + "'maxOccurences' : '4',"
                        + "'occurences' : '3',"
                        + "'grade' : 'A+',"
                        + "'successRate' : '100',"
                        + "'minutesLeft' : '45'"
                        + "}"
                        + ","
                        + "{'title' : 'Have a no-bro day',"
                        + "'schedule' : 'Every week for 8 months',"
                        + "'maxOccurences' : '35',"
                        + "'occurences' : '5',"
                        + "'grade' : 'B+',"
                        + "'successRate' : '89',"
                        + "'minutesLeft' : '1000'"
                        + "}"
                        + ","
                        + "{'title' : 'Do 10 push-ups before going to bed',"
                        + "'schedule' : 'Every day for a year',"
                        + "'maxOccurences' : '365',"
                        + "'occurences' : '167',"
                        + "'grade' : 'A',"
                        + "'successRate' : '93',"
                        + "'minutesLeft' : '456'"
                        + "}"
                        + "]";
        
        Goal[] goals = new Gson().fromJson(json, Goal[].class);
        
    	/** Called when the activity is first created. */
        setContentView(R.layout.profile);
        
        ActionBar actionBar = getActionBar();
        if(actionBar != null){
        	actionBar.setDisplayHomeAsUpEnabled(true);
        }
        
        setListAdapter(new GoalAdapter(this, goals));
    }

    public void showEvidenceDialog(View view){
        popup = new PopupWindow(this);
		if (click) {
	    	Dialog dialog = new Dialog(ProfileActivity.this);
	    	dialog.setContentView(R.layout.give_evidence_dialog);
	    	dialog.setTitle("Upload Evidence");
	    	dialog.show();
        } else {
        	popup.dismiss();
        	click = true;
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
    
	/*
	Button capturePictureButton = (Button) giveEvidencePopupView.findViewById(R.id.capture_picture);
	capturePictureButton.setOnClickListener(new OnClickListener(){
		public void onClick(View v){
			Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);  
            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST); 
		}
	});

	Button captureVideoButton = (Button) giveEvidencePopupView.findViewById(R.id.capture_video);
	captureVideoButton.setOnClickListener(new OnClickListener(){
		public void onClick(View v){
			Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);  
            startActivityForResult(cameraIntent, CAMERA_VIDEO_REQUEST); 
		}
	});
	*/
    
    public void showAbout(){
    	
    }
    
    public void logout(){
    	
    }

    public void showPreferences(){
    	
    }
    
}