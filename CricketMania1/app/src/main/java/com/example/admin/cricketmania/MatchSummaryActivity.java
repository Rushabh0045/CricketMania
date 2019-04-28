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

import org.json.JSONArray;
import org.json.JSONObject;

public class MatchSummaryActivity extends AppCompatActivity {

    String url = "https://cricapi.com/api/fantasySummary?apikey=fp6cLCPNb4d7z2mfEb8izzJD6zG2&unique_id=";

    TextView feildT1TitleTv,feildT1DetailTv,feildT2TitleTv,feildT2DetailTv,
            bowlT1TitleTv,bowlT1DetailTv,bowlT2TitleTv,bowlT2DetailTv,
            batT1TitleTv,batT1DetailTv,batT2TitleTv,batT2DetailTv,
            otherResultsTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_summary);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Match Summary");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String uniqueId = intent.getStringExtra("match_id");
        url = url + uniqueId;

        feildT1TitleTv = findViewById(R.id.feildT1TitleTv);
        feildT1DetailTv = findViewById(R.id.feildT1DetailTv);
        feildT2TitleTv = findViewById(R.id.feildT2TitleTv);
        feildT2DetailTv = findViewById(R.id.feildT2DetailTv);
        bowlT1TitleTv = findViewById(R.id.bowlT1TitleTv);
        bowlT1DetailTv = findViewById(R.id.bowlT1DetailTv);
        bowlT2TitleTv = findViewById(R.id.bowlT2TitleTv);
        bowlT2DetailTv = findViewById(R.id.bowlT2DetailTv);
        batT1TitleTv = findViewById(R.id.batT1TitleTv);
        batT1DetailTv = findViewById(R.id.batT1DetailTv);
        batT2TitleTv = findViewById(R.id.batT2TitleTv);
        batT2DetailTv = findViewById(R.id.batT2DetailTv);
        otherResultsTv = findViewById(R.id.otherResultsTv);
        
        loadData();


    }

    private void loadData() {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject dataJobject = jsonObject.getJSONObject("data");

                    JSONArray fieldJARRAY = dataJobject.getJSONArray("fielding");
                    JSONArray bowlJARRAY = dataJobject.getJSONArray("bowling");
                    JSONArray batJARRAY = dataJobject.getJSONArray("batting");


                    JSONObject field0 = fieldJARRAY.getJSONObject(0);
                    JSONObject field1 = fieldJARRAY.getJSONObject(1);

                    String field1Title = field0.getString("title");
                    String field2Title = field1.getString("title");

                    JSONArray field1ScoresJArray = field0.getJSONArray("scores");
                    JSONArray field2ScoresJArray = field1.getJSONArray("scores");

                    feildT1TitleTv.setText(field1Title);
                    for (int i = 0; i<field1ScoresJArray.length(); i++){
                        String name = field1ScoresJArray.getJSONObject(i).getString("name");
                        String bowled = field1ScoresJArray.getJSONObject(i).getString("bowled");
                        String catchh = field1ScoresJArray.getJSONObject(i).getString("catch");
                        String lbw = field1ScoresJArray.getJSONObject(i).getString("lbw");
                        String runout = field1ScoresJArray.getJSONObject(i).getString("runout");
                        String stumped = field1ScoresJArray.getJSONObject(i).getString("stumped");

                        feildT1DetailTv.append("Name: " + name
                                +"\nBowled: " + bowled
                                +"\nCatch: " + catchh
                                +"\nLBW: " + lbw
                                +"\nRunOut: " + runout
                                +"\nStumped: " + stumped + "\n\n"
                        );

                    }

                    feildT2TitleTv.setText(field2Title);
                    for (int i = 0; i<field2ScoresJArray.length(); i++){
                        String name = field2ScoresJArray.getJSONObject(i).getString("name");
                        String bowled = field2ScoresJArray.getJSONObject(i).getString("bowled");
                        String catchh = field2ScoresJArray.getJSONObject(i).getString("catch");
                        String lbw = field2ScoresJArray.getJSONObject(i).getString("lbw");
                        String runout = field2ScoresJArray.getJSONObject(i).getString("runout");
                        String stumped = field2ScoresJArray.getJSONObject(i).getString("stumped");

                        feildT2DetailTv.append("Name: " + name
                                +"\nBowled: " + bowled
                                +"\nCatch: " + catchh
                                +"\nLBW: " + lbw
                                +"\nRunOut: " + runout
                                +"\nStumped: " + stumped + "\n\n"
                        );

                    }
                    JSONObject bowl0 = bowlJARRAY.getJSONObject(0);
                    JSONObject bowl1 = bowlJARRAY.getJSONObject(1);

                    String bowl1Title = bowl0.getString("title");
                    String bowl2Title = bowl1.getString("title");

                    JSONArray bowl1ScoresJArray = bowl0.getJSONArray("scores");
                    JSONArray bowl2ScoresJArray = bowl1.getJSONArray("scores");

                    bowlT1TitleTv.setText(bowl1Title);
                    for (int i=0; i<bowl1ScoresJArray.length(); i++){
                        String bowlerName = bowl1ScoresJArray.getJSONObject(i).getString("bowler");
                        String overs = bowl1ScoresJArray.getJSONObject(i).getString("O");
                        String wickets = bowl1ScoresJArray.getJSONObject(i).getString("W");
                        String runs = bowl1ScoresJArray.getJSONObject(i).getString("R");
                        String zeroes = bowl1ScoresJArray.getJSONObject(i).getString("0s");
                        String fours = bowl1ScoresJArray.getJSONObject(i).getString("4s");
                        String sixes = bowl1ScoresJArray.getJSONObject(i).getString("6s");
                        String m = bowl1ScoresJArray.getJSONObject(i).getString("M");
                        String econ = bowl1ScoresJArray.getJSONObject(i).getString("Econ");

                        bowlT1DetailTv.append("Name: " + bowlerName
                                + "\nOvers: " + overs
                                + "\nWickets: " + wickets
                                + "\nRuns: " + runs
                                + "\nDotBall: " + zeroes
                                + "\n4s: " + fours
                                + "\n6s: " + sixes
                                + "\nM: " + m
                                + "\nEcon: " + econ
                                +"\n\n");
                    }

                    bowlT2TitleTv.setText(bowl2Title);
                    for (int i=0; i<bowl2ScoresJArray.length(); i++){
                        String bowlerName = bowl2ScoresJArray.getJSONObject(i).getString("bowler");
                        String overs = bowl2ScoresJArray.getJSONObject(i).getString("O");
                        String wickets = bowl2ScoresJArray.getJSONObject(i).getString("W");
                        String runs = bowl2ScoresJArray.getJSONObject(i).getString("R");
                        String zeroes = bowl2ScoresJArray.getJSONObject(i).getString("0s");
                        String fours = bowl2ScoresJArray.getJSONObject(i).getString("4s");
                        String sixes = bowl2ScoresJArray.getJSONObject(i).getString("6s");
                        String m = bowl2ScoresJArray.getJSONObject(i).getString("M");
                        String econ = bowl2ScoresJArray.getJSONObject(i).getString("Econ");

                        bowlT2DetailTv.append("Name: " + bowlerName
                                + "\nOvers: " + overs
                                + "\nWickets: " + wickets
                                + "\nRuns: " + runs
                                + "\nDotBall: " + zeroes
                                + "\n4s: " + fours
                                + "\n6s: " + sixes
                                + "\nM: " + m
                                + "\nEcon: " + econ
                                +"\n\n"
                        );
                    }

                    JSONObject bat0 = batJARRAY.getJSONObject(0);
                    JSONObject bat1 = batJARRAY.getJSONObject(1);

                    String bat1Title = bat0.getString("title");
                    String bat2Title = bat1.getString("title");

                    JSONArray bat1ScoresJArray = bat0.getJSONArray("scores");
                    JSONArray bat2ScoresJArray = bat1.getJSONArray("scores");

                    for (int i=0; i<bat1ScoresJArray.length(); i++){
                        String batsman = bat1ScoresJArray.getJSONObject(i).getString("batsman");
                        String ball = bat1ScoresJArray.getJSONObject(i).getString("B");
                        String runs = bat1ScoresJArray.getJSONObject(i).getString("R");
                        String fours = bat1ScoresJArray.getJSONObject(i).getString("4s");
                        String sixes = bat1ScoresJArray.getJSONObject(i).getString("6s");
                        String strikeRate = bat1ScoresJArray.getJSONObject(i).getString("SR");
                        String dismisalInfo = bat1ScoresJArray.getJSONObject(i).getString("dismissal-info");
                        String dismissal = "",dismissedBy = "";
                        try {
                            dismissal = bat1ScoresJArray.getJSONObject(i).getString("dismissal");
                            dismissedBy = bat1ScoresJArray.getJSONObject(i).getJSONObject("dismissal-by").getString("name");

                        }
                        catch (Exception e){

                        }
                        batT1TitleTv.setText(bat1Title);
                        batT1DetailTv.append("Batsman: " +batsman
                                +"\nBalls: " +ball
                                +"\nRuns: " +runs
                                +"\n4s: " +fours
                                +"\n6s: " +sixes
                                +"\nSR: " +strikeRate
                                +"\nDismissal: " +dismissal
                                +"\nDismissal-Info: " +dismisalInfo
                                +"\nDismissal By: " +dismissedBy
                                +"\n\n");
                    }

                    for (int i=0; i<bat2ScoresJArray.length(); i++){
                        String batsman = bat2ScoresJArray.getJSONObject(i).getString("batsman");
                        String balls = bat2ScoresJArray.getJSONObject(i).getString("B");
                        String runs = bat2ScoresJArray.getJSONObject(i).getString("R");
                        String fours = bat2ScoresJArray.getJSONObject(i).getString("4s");
                        String sixes = bat2ScoresJArray.getJSONObject(i).getString("6s");
                        String strikeRate = bat2ScoresJArray.getJSONObject(i).getString("SR");
                        String dismisalInfo = bat2ScoresJArray.getJSONObject(i).getString("dismissal-info");
                        String dismissal = "",dismissedBy = "";
                        try {
                            dismissal = bat2ScoresJArray.getJSONObject(i).getString("dismissal");
                            dismissedBy = bat2ScoresJArray.getJSONObject(i).getJSONObject("dismissal-by").getString("name");

                        }
                        catch (Exception e){

                        }
                        batT2TitleTv.setText(bat2Title);
                        batT2DetailTv.append("Batsman: " +batsman
                                +"\nBalls: " +balls
                                +"\nRuns: " +runs
                                +"\n4s: " +fours
                                +"\n6s: " +sixes
                                +"\nSR: " +strikeRate
                                +"\nDismissal: " +dismissal
                                +"\nDismissal-Info: " +dismisalInfo
                                +"\nDismissal By: " +dismissedBy
                                +"\n\n");
                    }

                    String manofTheMatch = "", tossWinner = "", winnerteam = "";
                    try {
                        manofTheMatch = dataJobject.getJSONObject("man-of-the-match").getString("name");

                    }
                    catch (Exception e){

                    }
                    try {
                        tossWinner = dataJobject.getString("toss-winner_team");

                    }
                    catch (Exception e){

                    }
                    try {
                        winnerteam = dataJobject.getString("winner_team");

                    }
                    catch (Exception e){

                    }
                    otherResultsTv.setText("\nWinner: " +winnerteam
                            +"\nMan of the Match: " +manofTheMatch);


                }
                catch (Exception e){
                    Toast.makeText(MatchSummaryActivity.this,"Error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MatchSummaryActivity.this,"Error"+error.toString(),Toast.LENGTH_SHORT).show();

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
