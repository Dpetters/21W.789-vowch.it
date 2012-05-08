package it.vowch.android.adapters;

import java.util.List;

import com.parse.ParseObject;

import it.vowch.android.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EvidenceAdapter extends ArrayAdapter<ParseObject> {
	private final Context context;
	private final List<ParseObject> values;

	public EvidenceAdapter(Context context, List<ParseObject> evidences) {
		super(context, R.layout.evidence, evidences);
		this.context = context;
		this.values = evidences;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.evidence, parent, false);

		ParseObject evidence = values.get(position);
		
		final TextView action = (TextView) rowView.findViewById(R.id.action);


		//firstNameView.setText("<a>" + user.getFirstName() + " " + user.getLastName() + "</a> " + evidence.getAction());
		//firstNameView.setMovementMethod(LinkMovementMethod.getInstance());

		/*
		TextView lastNameView = (TextView) rowView.findViewById(R.id.last_name);
		lastNameView.setText(evidence.getUser().getLastName());
		
		TextView actionView = (TextView) rowView.findViewById(R.id.action);
		actionView.setText(evidence.getAction());
		*/
		//ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
		//imageView.setImageURI(evidence.getUser().getImageUri());
		
		return rowView;
	}
}
