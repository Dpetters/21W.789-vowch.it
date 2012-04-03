package it.vowch.android.adapters;

import it.vowch.android.R;
import it.vowch.android.data.Goal;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GoalAdapter extends ArrayAdapter<Goal> {
	private final Context context;
	private final Goal[] values;

	public GoalAdapter(Context context, Goal[] values) {
		super(context, R.layout.goal, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.goal, parent, false);
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
		Goal goal = values[position];
		
		TextView titleView = (TextView) rowView.findViewById(R.id.title);
		titleView.setText(goal.getTitle());
		
		TextView scheduleView = (TextView) rowView.findViewById(R.id.schedule);
		scheduleView.setText(goal.getSchedule());
		
		ProgressBar progressBar = (ProgressBar) rowView.findViewById(R.id.progress);
		int maxOccurences = goal.getMaxOccurences();
		progressBar.setMax(maxOccurences);
		
		int occurences = goal.getOccurences();
		progressBar.setProgress(occurences);
		
		TextView completionPercentageView = (TextView) rowView.findViewById(R.id.completion_percentage);
		Integer completionPercentage = (int) ((double)occurences/maxOccurences*100);
		completionPercentageView.setText(completionPercentage.toString() + "% complete");
		
		TextView gradeView = (TextView) rowView.findViewById(R.id.grade);
		String grade = goal.getGrade();
		Integer successRate = goal.getSuccessRate();
		gradeView.setText(grade + " (" + successRate + "%)");

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
		return rowView;
	}
}
