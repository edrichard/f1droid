package com.edrichard.f1droid;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.edrichard.f1doid.data.ApplicationSQLiteOpenHelper;
import com.edrichard.f1doid.data.DAOCircuit;
import com.edrichard.f1droid.model.Circuit;
import com.edrichard.f1droid.util.Parser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Circuit Activity.
 * @author edrichard
 */
public class CircuitActivity extends Activity {

    /** List of circuit. */
    private ArrayList<Circuit> listCircuits = new ArrayList<Circuit>();
    /** Adapter of circuit. */
    private CircuitAdapter adapter = null;

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_circuits);

        // Global list component
        ListView list =
                (ListView)
                     CircuitActivity.this.findViewById(R.id.listViewCircuit);
        final ArrayList<Circuit> circuits = new ArrayList<Circuit>();
        this.adapter = new CircuitAdapter(
                CircuitActivity.this, R.layout.row_circuit, circuits);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(
                    final AdapterView<?> arg0,
                    final View arg1,
                    final int position,
                    final long arg3) {
                Circuit c = circuits.get(position);

                Intent intent = new Intent(CircuitActivity.this,
                        DetailCircuitActivity.class);

                Bundle b = new Bundle();
                b.putSerializable("MON_CIRCUIT", c);
                intent.putExtras(b);

                //start new activity
                CircuitActivity.this.startActivity(intent);
            }

        });

        new LoadWebServiceCircuitF1(this).execute();
    }

    /**
     * AsyncTask LoadWebService.
     * @author edrichard.
     */
    private class LoadWebServiceCircuitF1
            extends AsyncTask<Void, Void, String> {

        /** Context of application. */
        private Context ctx;

        /**
         * Constructor LoadWebServiceF1.
         * @param ct : context of application.
         */
        public LoadWebServiceCircuitF1(final Context ct) {
            super();
            this.ctx = ct;
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
        protected final String doInBackground(final Void... params) {
            String jsonResult =
                    Parser.downloadJSON(ctx.getString(
                            R.string.ws_circuit_realease));

            if (jsonResult != null) {
                ApplicationSQLiteOpenHelper helper =
                        ApplicationSQLiteOpenHelper.connexionDataBase(ctx);
                DAOCircuit daoCircuit =
                        new DAOCircuit(helper.getWritableDatabase());

                try {
                    JSONObject root =
                            new JSONObject(jsonResult);
                    JSONObject mrData =
                            root.getJSONObject("MRData");
                    JSONObject circuitTable =
                            mrData.getJSONObject("CircuitTable");
                    JSONArray circuits =
                            circuitTable.getJSONArray("Circuits");

                    for (int i = 0; i < circuits.length(); i++) {
                        JSONObject circuit = (JSONObject) circuits.get(i);
                        String circuitId = circuit.getString("circuitId");
                        String circuitName = circuit.getString("circuitName");
                        String url = circuit.getString("url");
                        JSONObject location =
                                circuit.getJSONObject("Location");
                        double longi = location.getDouble("long");
                        double lat = location.getDouble("lat");
                        String locality = location.getString("locality");
                        String country = location.getString("country");

                        Circuit c = new Circuit();
                        c.setId(circuitId);
                        c.setName(circuitName);
                        c.setUrl(url);
                        c.setLatitude(lat);
                        c.setLongitude(longi);
                        c.setLocacity(locality);
                        c.setCountry(country);

                        listCircuits.add(c);

                        Boolean idCircuitExist =
                                daoCircuit.getCircuitExist(circuitId);
                        if (idCircuitExist.equals(false)) {
                            daoCircuit.addCircuit(c);
                        }
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    helper.close();
                }
            }

            return null;
        }

        /* (non-Javadoc)
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected final void onPostExecute(final String result) {
            super.onPostExecute(result);

            ApplicationSQLiteOpenHelper helper =
                    ApplicationSQLiteOpenHelper.connexionDataBase(ctx);

            DAOCircuit daoCircuit =
                    new DAOCircuit(helper.getWritableDatabase());
            ArrayList<Circuit> allCircuit = daoCircuit.getAllCircuit();

            adapter.clear();
            adapter.addAll(allCircuit);
            adapter.notifyDataSetChanged();

            if (result != null) {
                Toast.makeText(
                        this.ctx, "Chargement...",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * CircuitAdapter of adapter list view of circuit.
     * @author edrichard
     */
    public class CircuitAdapter extends ArrayAdapter<Circuit> {
        /** int ressource. */
        private int res;

        /**
         * Constructor.
         * @param context of application.
         * @param resource of application.
         * @param items of list view.
         */
        public CircuitAdapter(
                final Context context,
                final int resource,
                final List<Circuit> items) {
            super(context, resource, items);
            this.res = resource;
        }

        @Override
        public final View getView(
                final int position,
                final View convertView,
                final ViewGroup parent) {
            Circuit item = this.getItem(position);

            LayoutInflater inf = LayoutInflater.from(getContext());
            View v = inf.inflate(this.res, null);

            TextView tvFn = (TextView) v.findViewById(R.id.row_tv_fn);
            tvFn.setText(item.getName());
            TextView tvLn = (TextView) v.findViewById(R.id.row_tv_ln);
            tvLn.setText(item.getCountry());

            return v;
        }
    }
}
