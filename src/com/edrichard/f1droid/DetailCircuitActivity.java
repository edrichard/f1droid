package com.edrichard.f1droid;

import com.edrichard.f1droid.model.Circuit;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapFragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

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
		
		TextView name_circuit = (TextView) this.findViewById(R.id.nameCircuit);
		name_circuit.setText(getCircuitSerialize().getName());
		
		TextView locacity_circuit = (TextView) this.findViewById(R.id.locacityCircuit);
		locacity_circuit.setText(
				getCircuitSerialize().getLocacity() + 
				" (" + 
				getCircuitSerialize().getCountry() + 
				")");
		
		//Google Map
		this.initGoogleMap();
	}
	
	/**
	 * Google MAP V2
	 */
	public void initGoogleMap() {
		GoogleMap map = 
				((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

		LatLng circuitPosition = new LatLng(
				getCircuitSerialize().getLatitude(), 
				getCircuitSerialize().getLongitude());

		map.setMyLocationEnabled(true); 
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(circuitPosition, 13));
		
		map.addMarker(new MarkerOptions() 
			.title(getCircuitSerialize().getName()) 
			.snippet(
					getCircuitSerialize().getLocacity() + 
					" (" + 
					getCircuitSerialize().getCountry() + 
					")") 
			.position(circuitPosition));
	}
	
}
