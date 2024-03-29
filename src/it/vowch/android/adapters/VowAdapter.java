package it.vowch.android.adapters;

import java.util.List;

import com.parse.ParseObject;

import it.vowch.android.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class VowAdapter extends ArrayAdapter<ParseObject> {
	private final Context context;
	private final List<ParseObject> values;

	public VowAdapter(Context context, List<ParseObject> values) {
		super(context, R.layout.vow, values);
		this.context = context;
		this.values = values;
	}
	
	public int maxOccurences(ParseObject vow){
		String s1 = vow.getString("period");
		String s2 = vow.getString("lengthUnit");
		int a=1;
		int b=1;
		
		if (s1 == "day"){
			a = 1;
		}
		else if (s1 == "week"){
			a = 7;
		}
		else if (s1 == "month"){
			a = 30;
		}
		else if (s1 == "year"){
			a = 365;
		}
		
		if (s2 == "days"){
			b = 1;
		}
		else if (s2 == "weeks"){
			b = 7;
		}
		else if (s2 == "months"){
			b = 30;
		}
		else if (s2 == "years"){
			b = 365;
		}
		
		int n = vow.getInt("lengthNum");
		
		return (n * b) / a;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.vow, parent, false);
		/*
		Button giveEvidenceButton = (Button) rowView.findViewById(R.id.give_evidence);
		giveEvidenceButton.setOnClickListener(new OnClickListener() {
			 @Override
			 public void onClick(View view) {
			  String url = (String) view.getTag();
			  Intent myIntent = new Intent(view.getContext(), WebView_Activity.class);
			  myIntent.putExtra("url", url);
			  view.getContext().startActivity(myIntent);
			 }
			});
		*/
		ParseObject vow = values.get(position);
		
		TextView titleView = (TextView) rowView.findViewById(R.id.text);
		titleView.setText(vow.getString("text"));
		
		TextView scheduleView = (TextView) rowView.findViewById(R.id.schedule);
		scheduleView.setText("Every " + vow.getString("period") + " for " + ((Integer)vow.getInt("lengthNum")).toString() + " " + vow.getString("lengthUnit"));
		
		ProgressBar progressBar = (ProgressBar) rowView.findViewById(R.id.progress);
		// TODO - actually compute the maxOccurrences
		int maxOccurences = maxOccurences(vow);
		progressBar.setMax(maxOccurences);


		
		int occurences = vow.getInt("totalOccurences");
		progressBar.setProgress(occurences);

		TextView occurencesLeftView = (TextView) rowView.findViewById(R.id.occurences_left);
		occurencesLeftView.setText(((Integer)(maxOccurences-occurences)).toString());
		
		TextView completionPercentageView = (TextView) rowView.findViewById(R.id.completion_percentage);
		Integer completionPercentage = (int) ((double)occurences/maxOccurences*100);
		completionPercentageView.setText(completionPercentage.toString() + "% complete");
		
		
		int successfulOccurences = vow.getInt("successfulOccurences");
		



		TextView gradeView = (TextView) rowView.findViewById(R.id.grade);
		
		double successRate = 1;
		if (occurences!=0){
			successRate = successfulOccurences/occurences;
		}
		
		String grade = "";
		
		if (successRate >= .97){
			grade = "A+";
		} else if (successRate >= .92){
			grade = "A";
		} else if (successRate >= .88){
			grade = "A-";
		} else if (successRate >= .82){
			grade = "B+";
		} else if (successRate >= .78){
			grade = "B";
		} else if (successRate >= .74){
			grade = "B-";
		} else if (successRate >= .70){
			grade = "C+";
		} else if (successRate >= .65){
			grade = "C";
		} else if (successRate >= .6){
			grade = "C-";
		} else if (successRate >= .5){
			grade = "D";
		} else {grade = "F"; 
		}
		
		
		gradeView.setText(grade + " (" + ((int) successRate) *100 + "%)");
		
		/*
		TextView timeLeftView = (TextView) rowView.findViewById(R.id.time_left);
		String timeLeft = "";
		Integer minutesLeft = goal.getMinutesLeft();
		
		Integer hoursLeft = minutesLeft / 60;
		minutesLeft = minutesLeft % 60;
		if(hoursLeft!=0){
			timeLeft += hoursLeft + " Hours ";
		}else{
			timeLeftView.setTextColor(Color.RED);
		}
		timeLeft += minutesLeft + " Minutes Left";
		timeLeftView.setText(timeLeft);
		*/
		return rowView;
	}
}
