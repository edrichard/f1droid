package com.edrichard.f1droid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.edrichard.f1droid.model.Pilote;
import com.koushikdutta.ion.Ion;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Class details of pilot activity.
 * @author edrichard.
 */
@SuppressLint("SimpleDateFormat")
public class DetailsPiloteActivity extends Activity {

    /**
     * De-serialize object pilote.
     * @return pilote object.
     */
    private Pilote getPiloteSerialize() {
        Pilote pilote = (Pilote)
                this.getIntent().getExtras().getSerializable("MON_PILOTE");
        return pilote;
    }

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_details_pilote);

        this.initFichePilote();
        this.initFlagPilot();
        this.initButtonWiki();
    }

    /**
     * Initilisation of detail of pilote.
     */
    public final void initFichePilote() {
        TextView lnFnPilote = (TextView) this.findViewById(R.id.lnFnPilote);
        lnFnPilote.setText(
                getPiloteSerialize().getFamilyName()
                + " " + getPiloteSerialize().getGivenName());

        SimpleDateFormat format =
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date date = null;
        try {
            date = format.parse(
                    getPiloteSerialize().getDateOfBirth().toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        SimpleDateFormat formatFr = new SimpleDateFormat("dd/MM/yyyy");
        String mydateString = formatFr.format(date);

        TextView dateOfBirthPilote =
                (TextView) this.findViewById(R.id.dateOfBirthPilote);
        dateOfBirthPilote.setText(mydateString);
    }

    /**
     * Initialisation of bouton wikipedia bouton of pilote.
     */
    public final void initButtonWiki() {
        Button btWikiPilote = (Button) findViewById(R.id.btWikiPilote);
        btWikiPilote.setText(this.getString(R.string.btWikiPilote)
                + " : " + getPiloteSerialize().getFamilyName()
                + " " + getPiloteSerialize().getGivenName());

                btWikiPilote.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getPiloteSerialize().getUrl()));
                startActivity(browserIntent);
            }
        });
    }
    
    /**
     * 
     */
    public final void initFlagPilot() {
        String natinality = getPiloteSerialize().getNationality();
        String recup = natinality.substring(0,2).toLowerCase();
        
        String urlWS =
                getApplicationContext().getString(R.string.ws_country_flag_realease)
                + recup
                + getApplicationContext().getString(R.string.formatImage);

        ImageView gif = (ImageView) findViewById(R.id.drapeauPilote);
        Ion.with(gif).load(urlWS);
    }
    
    
}
