package com.example.gaunhavajson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.gaunhavajson.Adapter.HavaAdapter;
import com.example.gaunhavajson.Model.Hava;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {

    HttpHandler httpHandler;
    ProgressDialog progressDialog;
    ArrayList<Hava> havaArrayList;
    ListView liste;
    HavaAdapter havaAdapter;
    //Context context;

    int entrryID, f3, f4;
    String createdAt;

    private static String URL = "https://thingspeak.com/channels/890987/feed.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //context =this;
        //havaAdapter=new HavaAdapter(this,havaArrayList);

        //havaArrayList=new ArrayList<>();
        liste = (ListView) findViewById(R.id.liste);
        havaArrayList = new ArrayList<>();
        new getRecipe().execute();


    }

    private class getRecipe extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Lütfen bekleyiniz");
            progressDialog.setCancelable(false);
            progressDialog.show();
            /*
                işlemin başladığında
             */
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            havaAdapter = new HavaAdapter(MainActivity.this, havaArrayList);
            liste.setAdapter(havaAdapter);
            Log.i("countList:", "" + havaArrayList.size());

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            /*
                işlemin tamamlandığında
             */


        }

        @Override
        protected Void doInBackground(Void... params) {
            httpHandler = new HttpHandler();
            String jsonString = httpHandler.makeServiceCall(URL);

            Log.d("JSON_RESPONSE", jsonString);

            if(havaArrayList.size()<2){

                havaArrayList.add(new Hava("Created At",81818188,91919191,71717171));

            }

            JSONArray channel = null;
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jsonString);
                channel = jsonObject.getJSONArray("feeds");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            if (jsonString != null) {


                for (int i = 0; i < channel.length(); i++) {

                    try {


                        Log.i("comingdata : ", "channel length:" + channel.length());
                        Log.i("comingdata : ", "" + channel.getJSONObject(i).getInt("entry_id"));
                        Log.i("countList:", "" + havaArrayList.size());

                        entrryID = channel.getJSONObject(i).getInt("entry_id");
                        createdAt = "" + channel.getJSONObject(i).getString("created_at");
                        f3 = channel.getJSONObject(i).getInt("field3");
                        f4 = channel.getJSONObject(i).getInt("field4");


                    } catch (Exception E) {

                        E.printStackTrace();


                    }


                    Hava hava = new Hava(createdAt, entrryID , f3, f4);
                    Log.i("entrryID", "" + entrryID);

                    hava.setEntry_Id(entrryID);
                    hava.setCreated_At(createdAt);
                    hava.setField3(f3);
                    hava.setField4(f4);


                    Log.i("objInfos:", "" + hava.getEntry_Id() + "/" + createdAt);
                    havaArrayList.add(hava);


                }


            } else {
                Log.d("JSON RESPONSE", "sayfa kaynağı boş");

            }

            return null;
            /*
                işlemin gerçekleştirilme zamanında
             */
        }
    }


}
