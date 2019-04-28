package com.example.admin.cricketmania;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MatchDetailActivity extends AppCompatActivity {

    TextView mTeam1Tv, mTeam2Tv, mMatchStatusTv, mScoreTv, mDescripitionTv, mDateTv;
    private String url="http://cricapi.com/api/cricketScore/?apikey=fp6cLCPNb4d7z2mfEb8izzJD6zG2&unique_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Match Detail");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

       Intent intent = getIntent();
       String id1 = intent.getStringExtra("match_id");
       String date = intent.getStringExtra("date");
       url = url + id1;

        mTeam1Tv = findViewById(R.id.team1Tv);
        mTeam2Tv = findViewById(R.id.team2Tv);
        mMatchStatusTv = findViewById(R.id.matchStatusTv);
        mScoreTv = findViewById(R.id.scoreTv);
        mDescripitionTv = findViewById(R.id.descripitionTv);
        mDateTv = findViewById(R.id.dateTv);

        mDateTv.setText(date);
        
        loadData();

    }

    private void loadData() {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String team1 = jsonObject.getString("team-1");
                            String team2 = jsonObject.getString("team-2");
                            String matchStatus = jsonObject.getString("matchStarted");
                            if (matchStatus.equals("true")) {
                                matchStatus = "match started";
                            }
                            else {
                                matchStatus = "match not started";
                            }
                            mTeam1Tv.setText(team1);
                            mTeam2Tv.setText(team2);
                            mMatchStatusTv.setText(matchStatus);

                            try {
                                String score = jsonObject.getString("score");
                                String descripition = jsonObject.getString("description");
                                mScoreTv.setText(score);
                                mDescripitionTv.setText(descripition);
                            }
                            catch (Exception e){
                                Toast.makeText(MatchDetailActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }


                        }
                        catch (Exception e){
                            Toast.makeText(MatchDetailActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MatchDetailActivity.this,"Error"+error.toString(),Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
