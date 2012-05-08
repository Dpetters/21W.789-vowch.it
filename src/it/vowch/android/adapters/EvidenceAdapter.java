package it.vowch.android.adapters;

import it.vowch.android.R;
import it.vowch.android.data.Evidence;
import it.vowch.android.data.User;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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
		
		TextView firstNameView = (TextView) rowView.findViewById(R.id.text);
		User user = evidence.getUser();
		/*
		CharSequence sequence = Html.fromSource(context.getString(R.string.clickable_string));
		SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
		UnderlineSpan[] underlines = strBuilder.getSpans(UnderlineSpan.class);
		for(UnderlineSpan span : underlines) {
		   int start = strBuilder.getSpanStart(span);
		   int end = strBuilder.getSpanEnd(span);
		   int flags = strBuilder.getSpanFlags(span);
		   ClickableSpan myActivityLauncher = new ClickableSpan() {
		     public void onClick(View view) {
		       context.startActivity(getIntentForActivityToStart());
		     }
		   };

		   strBuilder.setSpan(myActivityLauncher, start, end, flags);
		}
		*/
		firstNameView.setText("<a>" + user.getFirstName() + " " + user.getLastName() + "</a> " + evidence.getAction());
		firstNameView.setMovementMethod(LinkMovementMethod.getInstance());

		/*
		TextView lastNameView = (TextView) rowView.findViewById(R.id.last_name);
		lastNameView.setText(evidence.getUser().getLastName());
		
		TextView actionView = (TextView) rowView.findViewById(R.id.action);
		actionView.setText(evidence.getAction());
		*/
		ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
		imageView.setImageURI(evidence.getUser().getImageUri());
		
		return rowView;
	}
}
