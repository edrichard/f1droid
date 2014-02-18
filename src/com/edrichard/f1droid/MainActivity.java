package com.edrichard.f1droid;

import com.edrichard.f1doid.data.ApplicationSQLiteOpenHelper;
import com.edrichard.f1doid.data.DAOCircuit;
import com.edrichard.f1doid.data.DAOPilote;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new CreateDataBase(this).execute();
		
		this.actionButtonLesCircuits();
		this.actionButtonLesPilotes();
		
	}
	
	/**
	 * Action button "Les circuits". Open new list activity.
	 */
	public void actionButtonLesCircuits() {
		Button btnLesCircuits = (Button) findViewById(R.id.btnLesCircuits);
		btnLesCircuits.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, 
						CircuitActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});
	}
	
	/**
	 * Action button "Les Pilotes". Open new list activity.
	 */
	public void actionButtonLesPilotes() {
		Button btnLesPilotes = (Button) findViewById(R.id.btnLesPilotes);
		btnLesPilotes.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, 
						PiloteActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private class CreateDataBase extends AsyncTask<Void, Void, String> {
		private Context ctx;
		
		/**
		 * Constructor LoadWebServiceF1.
		 * @param ctx
		 */
		public CreateDataBase(Context ctx) {
			super();
			this.ctx = ctx;
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		/* (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
		 */
		@Override
		protected String doInBackground(Void... params) {
			ApplicationSQLiteOpenHelper helper = new ApplicationSQLiteOpenHelper(
					MainActivity.this,
					ApplicationSQLiteOpenHelper.DATABASE_F1DROID,
					null,
					1);

			new DAOCircuit(helper.getWritableDatabase());
			new DAOPilote(helper.getWritableDatabase());

			return null;
		}
		
		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if(result != null) {
				Toast.makeText(this.ctx, "Chargement...", Toast.LENGTH_LONG).show();
			}
		}
		
	}

}
