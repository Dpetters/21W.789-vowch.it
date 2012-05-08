package it.vowch.android;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

import com.parse.FindCallback;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseQuery;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
        
        ParseUser currentUser = ParseUser.getCurrentUser();

        setContentView(R.layout.profile);
        
        Double exactTotalPoints = currentUser.getDouble("reputationPoints");
        Integer totalPoints = exactTotalPoints.intValue();
        
        Double exactLevel = Scoring.getLevel(exactTotalPoints);
        Integer level = exactLevel.intValue();
        
        TextView currentLevelView = (TextView) this.findViewById(R.id.current_level);
        currentLevelView.setText(level.toString());
		
        TextView nextLevelView = (TextView) this.findViewById(R.id.next_level);
        Integer nextLevel = level+1;
        nextLevelView.setText(nextLevel.toString());
		
        Integer neededPoints = (int) (Scoring.getPoints((double)nextLevel) - Scoring.getPoints((double)level));
        TextView neededPointsView = (TextView) this.findViewById(R.id.needed_points);
        neededPointsView.setText(neededPoints.toString());
        
        Integer currentPoints = (int) (totalPoints - Scoring.getPoints(level));
        TextView currentPointsView = (TextView) this.findViewById(R.id.current_points);
        currentPointsView.setText(currentPoints.toString());
        
		ProgressBar progressBar = (ProgressBar) this.findViewById(R.id.points_progress);
		progressBar.setMax(neededPoints);
		progressBar.setProgress(currentPoints);
        
		ParseQuery query = new ParseQuery("Vow");
		query.whereEqualTo("user", ParseUser.getCurrentUser());
		query.findInBackground(new FindCallback() {
		    public void done(List<ParseObject> activeVows, com.parse.ParseException e) {
		        if (e == null) {
		        	//setListAdapter(new VowAdapter(this, activeVows));
		        } else {
		            Log.d("Dmitrij", "Error: " + e.getMessage());
		        }
		    }
		});
		
    	/** Called when the activity is first created. */
        if (currentUser != null) {
        	new Thread() {
        	    public void run() {
        	      try {
			        	Bundle args = new Bundle();
			            args.putString("fields", "name");
			            JSONObject currentUserJson = null;
			            try {
			            	currentUserJson = new JSONObject(ParseFacebookUtils.getFacebook().request("me", args));
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			            final String name = currentUserJson.getString("name");
			            ProfileActivity.this.runOnUiThread(new Runnable()
	                    {
	                        @Override
	                        public void run()
                            {
	                    		TextView titleView = (TextView) ProfileActivity.this.findViewById(R.id.name);
	                        	titleView.setText(name);
                            }
	                    });

        	      } catch (Exception e) {
    	          throw new RuntimeException(e);
    	        }
    	      }
    	    }.start();
        } else {
            startActivity(new Intent(this, StartActivity.class));
        }

        
        ActionBar actionBar = getActionBar();
        if(actionBar != null){
        	actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void viewNewVowActivity(MenuItem menuItem){
		startActivity(new Intent(this, NewVowActivity.class));
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
    	ParseUser.logOut();
    	startActivity(new Intent(this, StartActivity.class));
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