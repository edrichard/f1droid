package com.edrichard.f1droid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

import com.edrichard.f1droid.model.Pilote;
import com.edrichard.f1droid.util.DateUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

@SuppressLint("SimpleDateFormat")
public class DetailsPiloteActivity extends Activity {

	private static final Context ctx = null;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_details_pilote);
		
		Pilote pilote = (Pilote) 
				this.getIntent().getExtras().getSerializable("MON_PILOTE");

		TextView ln_fn_pilote = (TextView) this.findViewById(R.id.lnFnPilote);
		ln_fn_pilote.setText(pilote.getFamilyName() + " " + pilote.getGivenName());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date date = null;
        try {
            date = format.parse (pilote.getDateOfBirth().toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
        
        SimpleDateFormat formatFr = new SimpleDateFormat("dd/MM/yyyy");
        String mydateString = formatFr.format(date);
        
		TextView dateOfBirth_pilote = (TextView) this.findViewById(R.id.dateOfBirthPilote);
		dateOfBirth_pilote.setText(mydateString);

		TextView nationality_pilote = (TextView) this.findViewById(R.id.nationalityPilote);
		nationality_pilote.setText(pilote.getNationality());
	}
	
}
