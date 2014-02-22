package com.edrichard.f1droid;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.edrichard.f1doid.data.ApplicationSQLiteOpenHelper;
import com.edrichard.f1doid.data.DAOPilote;
import com.edrichard.f1droid.model.Pilote;
import com.edrichard.f1droid.util.DateUtils;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Class of pilot activity.
 * @author edrichard.
 */
public class PiloteActivity extends Activity {

    /** List of pilote. */
    private ArrayList<Pilote> listPilotes = new ArrayList<Pilote>();
    /** Pilote adapter. */
    private PiloteAdapter adapter = null;

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_pilotes);

        // Global list component
        ListView list = (ListView) PiloteActivity.this.findViewById(
                R.id.listViewPilote);
        final ArrayList<Pilote> pilotes = new ArrayList<Pilote>();
        this.adapter = new PiloteAdapter(
                PiloteActivity.this, R.layout.row_circuit, pilotes);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(
                    final AdapterView<?> arg0,
                    final View arg1,
                    final int position,
                    final long arg3) {
                Pilote p = pilotes.get(position);

                Intent intent = new Intent(PiloteActivity.this,
                        DetailsPiloteActivity.class);

                Bundle b = new Bundle();
                b.putSerializable("MON_PILOTE", p);
                intent.putExtras(b);

                //start new activity
                PiloteActivity.this.startActivity(intent);
            }

        });

        new LoadWebServicePilotF1(this).execute();
    }

    /**
     * AsyncTask of LoadWebServicePilotF1.
     * @author edrichard.
     */
    private class LoadWebServicePilotF1 extends AsyncTask<Void, Void, String> {
        /** Context of application. */
        private Context ctx;

        /**
         * Constructor LoadWebServiceF1.
         * @param ct : Context of application.
         */
        public LoadWebServicePilotF1(final Context ct) {
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
            String jsonResult = Parser.downloadJSON(ctx.getString(
                    R.string.ws_pilote_realease));

            ApplicationSQLiteOpenHelper helper =
                    ApplicationSQLiteOpenHelper.connexionDataBase(ctx);
            DAOPilote daoPilote = new DAOPilote(helper.getWritableDatabase());

            try {
                JSONObject root = new JSONObject(jsonResult);
                JSONObject mrData = root.getJSONObject("MRData");
                JSONObject driverTable = mrData.getJSONObject("DriverTable");
                JSONArray drivers = driverTable.getJSONArray("Drivers");

                for (int i = 0; i < drivers.length(); i++) {
                    JSONObject driver = (JSONObject) drivers.get(i);
                    String driverId = driver.getString("driverId");
                    String url = driver.getString("url");
                    String givenName = driver.getString("givenName");
                    String familyName = driver.getString("familyName");
                    String dateOfBirth = driver.getString("dateOfBirth");
                    String nationality = driver.getString("nationality");

                    Pilote p = new Pilote();
                    p.setId(driverId);
                    p.setUrl(url);
                    p.setGivenName(givenName);
                    p.setFamilyName(familyName);
                    p.setDateOfBirth(
                            DateUtils.formatLocalISOStringToDateTime(
                                    dateOfBirth + "T00:00:00"));
                    p.setNationality(nationality);

                    listPilotes.add(p);

                    Boolean idPiloteExist =
                            daoPilote.getPiloteExist(driverId);
                    if (idPiloteExist.equals(false)) {
                        daoPilote.addPilote(p, ctx);
                    }
                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                helper.close();
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

            DAOPilote daoPilote =
                    new DAOPilote(helper.getWritableDatabase());
            ArrayList<Pilote> allPilote = daoPilote.getAllPilote();

            adapter.clear();
            adapter.addAll(allPilote);
            adapter.notifyDataSetChanged();

            if (result != null) {
                Toast.makeText(this.ctx,
                        "Chargement...",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Class of pilote adapter.
     * @author edrichard.
     */
    public class PiloteAdapter extends ArrayAdapter<Pilote> {
        /** Resource of application. */
        private int res;

        /**
         * Constructor.
         * @param context of application.
         * @param resource of application.
         * @param items of list view.
         */
        public PiloteAdapter(
                final Context context,
                final int resource,
                final List<Pilote> items) {
            super(context, resource, items);
            this.res = resource;
        }

        @Override
        public final View getView(
                final int position,
                final View convertView,
                final ViewGroup parent) {
            Pilote item = this.getItem(position);

            LayoutInflater inf = LayoutInflater.from(getContext());
            View v = inf.inflate(this.res, null);

            TextView tvFn = (TextView) v.findViewById(R.id.row_tv_fn);
            tvFn.setText(item.getGivenName() + " " + item.getFamilyName());
            TextView tvLn = (TextView) v.findViewById(R.id.row_tv_ln);
            tvLn.setText(item.getNationality());

            return v;
        }
    }

}
