package com.edrichard.f1droid;

import com.edrichard.f1droid.model.Pilote;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsPiloteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_details_pilote);
		
		Pilote pilote = (Pilote) 
				this.getIntent().getExtras().getSerializable("MON_PILOTE");
		
		TextView ln_fn_pilote = (TextView) this.findViewById(R.id.lnFnPilote);
		ln_fn_pilote.setText(pilote.getFamilyName() + " " + pilote.getGivenName());
		
		TextView nationality_pilote = (TextView) this.findViewById(R.id.nationalityPilote);
		nationality_pilote.setText(pilote.getNationality());
	}
	
}
