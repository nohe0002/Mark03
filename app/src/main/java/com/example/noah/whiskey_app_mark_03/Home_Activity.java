package com.example.noah.whiskey_app_mark_03;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Connection.OnlineHelper;

public class Home_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


  static   String nickname;
  static  String firstname = "hans";
  static  String lastname =  "peter";
  static  String email;
  static  String credit = "5";
  static  String code;
  static  String password;
  static  String birth;

    String type;


  static NavigationView navigationView1;
  static View headerView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Ab hier ist es von mir selbst


        if(getIntent().hasExtra("nickname") == true) {
            nickname = getIntent().getExtras().getString("nickname");
            Log.d("Daten", nickname);
            type = "get_data";


            OnlineHelper OnlineHelper = new OnlineHelper(this);
            OnlineHelper.execute(type, nickname);

        //So kann ich TextViews ändern die über Include verknüpft wurden (normalerweise kommt NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view); noch dazu. Ist aber in dem Fall schon vorhanden)
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.nav_header_nickname);
        navUsername.setText(nickname);
            navigationView1 = (NavigationView) findViewById(R.id.nav_view);
            headerView1 = navigationView1.getHeaderView(0);

        }

        //Hier denke ich kommt die abfrage des onlinehelper rein
     //   change_user_data();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_credit) {


            AlertDialog.Builder select_credit = new AlertDialog.Builder(this);
            select_credit.setTitle("Guthaben");
            select_credit.setMessage(credit + "€");
            select_credit.setCancelable(true);
            select_credit.setPositiveButton("OK", null);

            AlertDialog select_credit_dialog = select_credit.create();
            select_credit_dialog.show();

        }else if (id == R.id.nav_logout) {

            AlertDialog.Builder ask_logout = new AlertDialog.Builder(this);
            ask_logout.setTitle("Abmelden");
            ask_logout.setMessage("Wollen Sie sich wirklich abmelden?");
            ask_logout.setCancelable(false);
            ask_logout.setPositiveButton("Bestätigen", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    Intent in1 = new Intent(Home_Activity.this, Login_Activity.class);
                    startActivity(in1);
                    }
                });
            ask_logout.setNegativeButton("Cancel", null);

            AlertDialog ask_logout_dialog = ask_logout.create();
            ask_logout_dialog.show();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void parseJson (String result) {

        Log.d("Fail3", result);

//Todo versuch
        if (result != null) {
            try {
                JSONArray user = new JSONArray(result);
                Log.d("Fail4", "fail4");
                for (int i = 0; i < user.length(); i++) {
                    JSONObject c = user.getJSONObject(i);
                    nickname = c.getString("nickname");
                    firstname = c.getString("firstname");
                    lastname = c.getString("lastname");
                    birth = c.getString("birth");
                    email = c.getString("email");
                    credit = String.valueOf(c.getString("credit"));
                    code = String.valueOf(c.getString("code"));
                    password = c.getString("password");
                    Log.d("Daten", nickname);
                    Log.d("Daten", firstname);
                    Log.d("Daten", lastname);
                    Log.d("Daten", birth);
                    Log.d("Daten", email);
                    Log.d("Daten", credit);
                    Log.d("Daten", code);
                    Log.d("Daten", password);


                       change_user_data();



                    // Wird gezeigt wie ich die sachen aufrufen kann.

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


       }


       //Ist nur ein test
    public void change_user_data(){
        Log.d("Daten2", firstname);
        Log.d("Daten2", lastname);


        TextView user_firstname_lastname = (TextView) headerView1.findViewById(R.id.nav_header_subtitle);
        user_firstname_lastname.setText(firstname + " " + lastname);


    }

}
