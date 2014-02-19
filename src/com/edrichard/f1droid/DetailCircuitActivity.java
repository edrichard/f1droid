package com.edrichard.f1droid;

import com.edrichard.f1droid.model.Circuit;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapFragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DetailCircuitActivity extends Activity {

	private Circuit getCircuitSerialize() {
		Circuit circuit = (Circuit) 
				this.getIntent().getExtras().getSerializable("MON_CIRCUIT");
		return circuit;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_detail_circuit);

		this.initGoogleMap();
		this.buttonWiki();
	}
	
	/**
	 * Google MAP V2.
	 */
	public void initGoogleMap() {
		GoogleMap map = 
				((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

		LatLng circuitPosition = new LatLng(
				getCircuitSerialize().getLatitude(), 
				getCircuitSerialize().getLongitude());

		map.setMyLocationEnabled(true); 
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(circuitPosition, 13));

		Marker marker = map.addMarker(new MarkerOptions() 
			.title(getCircuitSerialize().getName())
			.snippet(
					getCircuitSerialize().getLocacity() + 
					" (" + 
					getCircuitSerialize().getCountry() + 
					")") 
			.position(circuitPosition));
		
		marker.showInfoWindow();
	}
	
	/**
	 * Action bouton wikipedia circuit.
	 */
	public void buttonWiki() {
	    Button btWikiCircuit = (Button) findViewById(R.id.btWikiCircuit);
	    btWikiCircuit.setText(this.getString(R.string.btWikiCircuit) + " : " + getCircuitSerialize().getName());
	    
	    btWikiCircuit.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
                        Uri.parse(getCircuitSerialize().getUrl()));
                startActivity(browserIntent);
            }
        });
	}
	
}
