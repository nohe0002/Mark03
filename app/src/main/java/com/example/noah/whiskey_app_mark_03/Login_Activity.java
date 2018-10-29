package com.example.noah.whiskey_app_mark_03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;

import Connection.ConnectionHelper;


public class Login_Activity extends AppCompatActivity implements View.OnClickListener{

//Todo: Muss noch alles überarbeitet werden sobald die API dazu fertig ist

    public static String username;
    //String vorname;
    //String nachname;
    String password;
    String passwort;
    //String passwortdb;
    //Integer guthaben;
    //Integer code;
    String passworttest = "hans";
    String type;




    Button login_confirm_button;
    Button login_registration_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login_confirm_button = (Button) findViewById(R.id.login_confirm_button);
        login_confirm_button.setOnClickListener(this);

        login_registration_button = (Button) findViewById(R.id.login_registration_button);
        login_registration_button.setOnClickListener(this);
    }


    public void onClick(View v) {
        try {
            switch (v.getId()){
                case R.id.login_confirm_button:

                    type = "find_login";
                    username = ((EditText) findViewById(R.id.login_username_edittext)).getText().toString();
                    password = ((EditText) findViewById(R.id.login_password_edittext)).getText().toString();

                    onLogin(v, username, password);

                    //Hier muss die Abfrage der Datenbank hin
           //        new Connection.ConnectionHelper().execute(type, username, password);
                    break;
                case R.id.login_registration_button:
                        Intent in2 = new Intent(Login_Activity.this, Registration_Activity.class);
                        startActivity(in2);
                        break;
            }

        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }

public void change_activity (String change){

        switch (change){
            case "approve":
                Intent in1 = new Intent(Login_Activity.this, Home_Activity.class);
                startActivity(in1);
                break;

            case"denid":
                //Message erstellen beginn
                AlertDialog.Builder login_fail = new AlertDialog.Builder(this);
                login_fail.setTitle("Fehlermeldung");
                login_fail.setMessage("Bitee Login-Daten überprüfen");
                login_fail.setCancelable(true);
                //Message erstellen ende

                //Message anzeigen begin
                AlertDialog login_fail_dialog = login_fail.create();
                login_fail_dialog.show();
                //Message anzeigen ende


                break;
        }
}


public static void check_login(String login_data){



        //kommt gleich noch was dazu

}


    public void onLogin(View view, String username, String password){
        String type = "find_login";
        Connection.ConnectionHelper connectionHelper = new Connection.ConnectionHelper(this);
        connectionHelper.execute(type, username, password);
   }




}

