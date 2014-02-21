package com.edrichard.f1droid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.edrichard.f1droid.model.Pilote;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Class details of pilot activity.
 * @author edrichard.
 */
@SuppressLint("SimpleDateFormat")
public class DetailsPiloteActivity extends Activity {

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_details_pilote);

        Pilote pilote = (Pilote)
                this.getIntent().getExtras().getSerializable("MON_PILOTE");

        TextView lnFnPilote = (TextView) this.findViewById(R.id.lnFnPilote);
        lnFnPilote.setText(
                pilote.getFamilyName() + " " + pilote.getGivenName());

        SimpleDateFormat format =
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = null;
        try {
            date = format.parse(pilote.getDateOfBirth().toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        SimpleDateFormat formatFr = new SimpleDateFormat("dd/MM/yyyy");
        String mydateString = formatFr.format(date);

        TextView dateOfBirthPilote =
                (TextView) this.findViewById(R.id.dateOfBirthPilote);
        dateOfBirthPilote.setText(mydateString);

        TextView nationalityPilote =
                (TextView) this.findViewById(R.id.nationalityPilote);
        nationalityPilote.setText(pilote.getNationality());
    }

}
