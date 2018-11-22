package com.example.noah.whiskey_app_mark_03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Connection.ConnectionHelper;


public class Login_Activity extends AppCompatActivity implements View.OnClickListener{





//Todo: Muss noch alles überarbeitet werden sobald die API dazu fertig ist

    public static String username;
    public static String firstname;
    public static String lastname;
    public static String password;
    public static  Integer credit;
    public static  Integer code;
   // String passworttest = "hans";
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

                  //  onLogin(v, username, password);

                    //Hier muss die Abfrage der Datenbank hin
                    new Connection.ConnectionHelper().execute(type, username, password);

                    Log.d("Message", "Es wurde alles ausgefuehrt");


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




public void change_activity(String approval){

        switch (approval){
            case "allow":


                Intent in1 = new Intent(Login_Activity.this, Home_Activity.class);
               //in1.putExtra("JsonString", result);
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




    public static void parseJson (String result) {
//Todo versuch
    if (result != null) {
        try {
            JSONArray user = new JSONArray(result);
            for (int i = 0; i < user.length(); i++) {
                JSONObject c = user.getJSONObject(i);
                firstname = c.getString("firstname");
                lastname = c.getString("lastname");
                credit = c.getInt("credit");
                code = c.getInt("code");
                Log.d("Daten", username);
                Log.d("Daten", firstname);
                Log.d("Daten", lastname);
                Log.d("Daten", String.valueOf(credit));
                Log.d("Daten", String.valueOf(code));
                Log.d("Daten", password);
                String approval = "allow";
                // Wird gezeigt wie ich die sachen aufrufen kann.
               Login_Activity logintest = new Login_Activity();
               logintest.change_activity(approval);





            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}

}

