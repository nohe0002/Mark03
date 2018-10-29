package com.example.noah.whiskey_app_mark_03;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration_Activity extends AppCompatActivity implements View.OnClickListener{


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
                        Intent in1 = new Intent(Registration_Activity.this, Login_Activity.class);
                        startActivity(in1);
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
