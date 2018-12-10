package com.example.noah.whiskey_app_mark_03;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Connection.LoginHelper;

public class Registration_Activity extends AppCompatActivity implements View.OnClickListener{

    public static String nickname;
    public static String firstname;
    public static String lastname;
    public static String birth;
    public static String email;
    public static String code;
    public static String password;
    public static String password_confirm;


    String type;


    Button registration_back_button;
    Button registration_confirm_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        registration_back_button = (Button) findViewById(R.id.registration_back_button);
        registration_back_button.setOnClickListener(this);

        registration_confirm_button = (Button) findViewById(R.id.registration_confirm_button);
        registration_confirm_button.setOnClickListener(this);


    }
//Todo zur regestierung muss noch eine abfrage ob alle daten eingetragen wurden und ob es die person schon gibt. die prüfung kann ich in der api machen
    public void onClick(View v) {
        try {
            switch (v.getId()){
                case R.id.registration_confirm_button:


                    type = "find_registration";
                    nickname = ((EditText) findViewById(R.id.registration_nickname_edittext)).getText().toString();
                    firstname = ((EditText) findViewById(R.id.registration_firstname_edittext)).getText().toString();
                    lastname = ((EditText) findViewById(R.id.registration_lastname_edittext)).getText().toString();
                    birth = ((EditText) findViewById(R.id.registration_birth_edittext)).getText().toString();
                    email = ((EditText) findViewById(R.id.registration_email_edittext)).getText().toString();
                    code = ((EditText) findViewById(R.id.registration_code_edittext)).getText().toString();
                    password = ((EditText) findViewById(R.id.registration_password_one_edittext)).getText().toString();
                    password_confirm = ((EditText) findViewById(R.id.registration_password_two_edittext)).getText().toString();

                    if(password.equals(password_confirm)){

                        LoginHelper LoginHepler = new LoginHelper(this);
                        LoginHepler.execute(type, nickname, firstname, lastname, birth, email, code, password);



                    }else{

                        //Message erstellen beginn
                        AlertDialog.Builder fail_password = new AlertDialog.Builder(this);
                        fail_password.setTitle("Fehlermeldung");
                        fail_password.setMessage("Die Passwörter stimmen nicht überein!");
                        fail_password.setCancelable(true);
                        //Message erstellen ende

                        //Message anzeigen begin
                        AlertDialog login_fail_dialog = fail_password.create();
                        login_fail_dialog.show();
                        //Message anzeigen ende
                        break;
                    }


                      //  Intent in1 = new Intent(Registration_Activity.this, Login_Activity.class);
                       // startActivity(in1);
                        break;

                case R.id.registration_back_button:
                    Intent in2 = new Intent(Registration_Activity.this, Login_Activity.class);
                    startActivity(in2);
                    break;
            }



        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }
}
