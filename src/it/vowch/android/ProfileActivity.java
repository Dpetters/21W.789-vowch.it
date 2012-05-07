package it.vowch.android;

import it.vowch.android.adapters.GoalAdapter;
import it.vowch.android.data.Goal;

import com.google.gson.Gson;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileActivity extends ListActivity {
	protected Dialog evidenceDialog;
	protected LinearLayout layout;
    
    protected boolean click = true;
    protected TextView tv;
    private static final int CHOOSE_PICTURE_REQUEST = 0;
    private static final int CAPTURE_PICTURE_REQUEST = 1;
    private static final int CHOOSE_VIDEO_REQUEST = 2;
    private static final int CAPTURE_VIDEO_REQUEST = 3;
    private static final int RECORD_AUDIO_REQUEST = 4;
    //private static final int PROVIDE_LINK_REQUEST = 5;
    
    public static String getRealPathFromUri(Activity activity, Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = activity.managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    
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
    	evidenceDialog = new Dialog(ProfileActivity.this);
    	evidenceDialog.setContentView(R.layout.give_evidence_dialog);
    	evidenceDialog.setTitle("Upload Evidence");
    	
    	Button choosePictureButton = (Button)evidenceDialog.findViewById(R.id.choose_picture);
    	choosePictureButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent galleryIntent = new Intent();
				galleryIntent.setType("image/*");
				galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(galleryIntent, CHOOSE_PICTURE_REQUEST);
			}
		});

    	Button capturePictureButton = (Button)evidenceDialog.findViewById(R.id.capture_picture);
    	capturePictureButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);  
	            startActivityForResult(cameraIntent, CAPTURE_PICTURE_REQUEST); 
			}
		});
    	
    	Button chooseVideoButton = (Button)evidenceDialog.findViewById(R.id.choose_video);
    	chooseVideoButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent galleryIntent = new Intent();
				galleryIntent.setType("video/*");
				galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(galleryIntent, CHOOSE_VIDEO_REQUEST);
			}
		});
    	
    	Button captureVideoButton = (Button)evidenceDialog.findViewById(R.id.capture_video);
    	captureVideoButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);  
	            startActivityForResult(cameraIntent, CAPTURE_VIDEO_REQUEST);
			}
		});

    	
    	Button recordAudioButton = (Button)evidenceDialog.findViewById(R.id.record_audio);
    	recordAudioButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
	    	   Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
	    	   startActivityForResult(intent, RECORD_AUDIO_REQUEST);
			}
		});
    	
    	evidenceDialog.show();
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
    
    public void showAbout(){
    	
    }
    
    public void logout(){
    	
    }

    public void showPreferences(){
    	
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
        	evidenceDialog.dismiss();
        	if (requestCode == CHOOSE_PICTURE_REQUEST) {
            	Log.d("Dmitrij", "choose picture");
            } else if(requestCode == CAPTURE_PICTURE_REQUEST) {
            	Log.d("Dmitrij", "capture picture");
            } else if(requestCode == CHOOSE_VIDEO_REQUEST) {
            	Log.d("Dmitrij", "choose video");
            } else if(requestCode == CAPTURE_VIDEO_REQUEST) {
            	Log.d("Dmitrij", "capture video");
            } else if(requestCode == RECORD_AUDIO_REQUEST) {
            	Log.d("Dmitrij", "record audio");
            }
        	/*
        	Uri filePathFromActivity = (Uri) extras.get(Intent.EXTRA_STREAM);
        	filePathFromActivity = Uri.parse(getRealPathFromUri( (Activity) ProfileActivity.this, filePathFromActivity));
        	File imageFile = new File(filePathFromActivity.getPath());
        	Log.d("Dmitrij", imageFile.toString());
        	*/
        }
    }
}