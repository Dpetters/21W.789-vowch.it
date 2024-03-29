package it.vowch.android;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ProgressCallback;
import com.parse.SaveCallback;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
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

import it.vowch.android.adapters.*;

public class ProfileActivity extends ListActivity {
	protected Dialog evidenceDialog;
	protected LinearLayout layout;
	protected ProgressDialog progressDialog;
	
    protected boolean click = true;
    protected TextView tv;
    private static final int CHOOSE_PICTURE_REQUEST = 0;
    private static final int CAPTURE_PICTURE_REQUEST = 1;
    private static final int CHOOSE_VIDEO_REQUEST = 2;
    private static final int CAPTURE_VIDEO_REQUEST = 3;
    private static final int RECORD_AUDIO_REQUEST = 4;
    //private static final int PROVIDE_LINK_REQUEST = 5;
    
    public static String getRealPathFromUri(Activity activity, Uri contentUri) {
    	Log.d("Dmitrij", contentUri.toString());
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = activity.managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Check if authenticated */
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {
        	startActivity(new Intent(this, StartActivity.class));
        } 
        
        setContentView(R.layout.profile);

        TextView nameView = (TextView) this.findViewById(R.id.name);
        nameView.setText(currentUser.getString("name").toString());
        
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
		        	setListAdapter(new VowAdapter(ProfileActivity.this, activeVows));
		        } else {
		            Log.d("Dmitrij", "Error: " + e.getMessage());
		        }
		    }
		});
		
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
    public byte[] getByteArray(Bitmap bitmap) {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    bitmap.compress(CompressFormat.PNG, 0, bos);
	    return bos.toByteArray();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
        	
        	if(evidenceDialog!=null){
        		evidenceDialog.dismiss();
        	}
        	
        	if (requestCode == CHOOSE_PICTURE_REQUEST || requestCode == CAPTURE_PICTURE_REQUEST) {
        		Uri currImageURI = data.getData();
        		String pictureFilePath = getRealPathFromUri(this, currImageURI);
        		Filename pictureFileName = new Filename(pictureFilePath, '/', '.');
        		Bitmap pictureFileBitmap = BitmapFactory.decodeFile(pictureFilePath);
        		byte[] buffer = getByteArray(pictureFileBitmap);

        		final ParseFile file = new ParseFile(pictureFileName.filename() + "." + pictureFileName.extension(), buffer);
        		file.saveInBackground(new SaveCallback() {
    			  public void done(ParseException e) {
    	              ParseObject evidence = new ParseObject("Evidence");
    	              ParseUser currentUser = ParseUser.getCurrentUser();
    	              
    	              evidence.put("name", currentUser.getString("name"));
    	              evidence.put("proof", file);
    	              evidence.saveInBackground();
    			  }
    			}, new ProgressCallback() {
    			  public void done(Integer percentDone) {
    				  
    			  }
    			});
            } else if(requestCode == CHOOSE_VIDEO_REQUEST) {
            	Log.d("Dmitrij", "choose video");
            } else if(requestCode == CAPTURE_VIDEO_REQUEST) {
            	Log.d("Dmitrij", "capture video");
            } else if(requestCode == RECORD_AUDIO_REQUEST) {
            	Log.d("Dmitrij", "record audio");
            }
        }else{
        	if(evidenceDialog!=null){
        		evidenceDialog.dismiss();
        	}
        }
    }
}