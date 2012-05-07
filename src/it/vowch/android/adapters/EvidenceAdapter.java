package it.vowch.android.adapters;

import it.vowch.android.R;
import it.vowch.android.data.Evidence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EvidenceAdapter extends ArrayAdapter<Evidence> {
	private final Context context;
	private final Evidence[] values;

	public EvidenceAdapter(Context context, Evidence[] values) {
		super(context, R.layout.evidence, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.evidence, parent, false);

		Evidence evidence = values[position];

		TextView firstNameView = (TextView) rowView.findViewById(R.id.first_name);
		firstNameView.setText(evidence.getUser().getFirstName());

		TextView lastNameView = (TextView) rowView.findViewById(R.id.last_name);
		lastNameView.setText(evidence.getUser().getFirstName());
		
		TextView actionView = (TextView) rowView.findViewById(R.id.action);
		actionView.setText(evidence.getUser().getFirstName());

		ImageView imageView = (ImageView) rowView.findViewById(R.id.action);
		imageView.setImageURI(evidence.getUser().getImageUrl());
		actionView.setText(evidence.getUser().getFirstName());
		return rowView;
	}
}
