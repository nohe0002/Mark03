package Connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.noah.whiskey_app_mark_03.Home_Activity;
import com.example.noah.whiskey_app_mark_03.Login_Activity;
import com.example.noah.whiskey_app_mark_03.R;
import com.example.noah.whiskey_app_mark_03.Suggestion_Activity;
import com.example.noah.whiskey_app_mark_03.Whiskey_Selection_Activity;

import static android.support.v4.content.ContextCompat.startActivity;


public class OnlineHelper extends AsyncTask<String, Void, String> {

String ip;
Context context;
String get_data_url;
String suggestion_url;
String whiskey_select_url;


String type;
String nickname;
String whiskeyid;


    public OnlineHelper(Context ctx) {
        context = ctx;
    }


    protected void onPreExecute() {

        ip = "http://192.168.2.121";

        get_data_url = ip + "/mark3/SELECT/get_data.php";
        suggestion_url = ip + "/mark3/SELECT/suggestion.php";
        whiskey_select_url = ip + "/mark3/SELECT/whiskey_select.php";
        //login_url = ip + "/mark3/SELECT/user_select.php";
        //registration_url = ip + "/mark3/INSERT/user_insert_test.php";



    }


    @Override
    protected String doInBackground(String... params) {
        type = params[0];

            if (type.equals("get_data")) {
                try {


                    nickname = params[1];
                    Log.d("Message", "Abfrage wurde durchgeführt Pizza");


                    URL url = new URL(get_data_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    Log.d("Message", "Abfrage wurde durchgeführt test0");
                    httpURLConnection.setRequestMethod("POST");
                    Log.d("Message", "Abfrage wurde durchgeführt test");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    Log.d("Message", "Abfrage wurde durchgeführt test35");
                    OutputStream outputStream = httpURLConnection.getOutputStream(); //Todo Ab hier geht es nicht mehr weiter
                    Log.d("Message", "Abfrage wurde durchgeführt HIER STEHT JETZT WAS ANDERES");
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("nickname", "UTF-8") + "=" + URLEncoder.encode(nickname, "UTF-8");
                    Log.d("Message", "Abfrage wurde durchgeführt test2");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    Log.d("Message", "Abfrage wurde durchgeführt3");
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    Log.d("Message", "Abfrage wurde durchgeführt Pasta");
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return "Exception: " + e.getMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Exception:" + e.getMessage();
                }

            }





        if (type.equals("suggestion")) {
            try {

                //Todo Hier wird die testdatenbank genommen da die richtige noch keine tabellen hat. Dafuer muss ich aber einen anderen usernamen benutzten
                //nickname = params[1];

                nickname = "manni";

                Log.d("Vorschlag", "Vorschlag wird durchführt");


                URL url = new URL(suggestion_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                Log.d("Vorschlag", "Abfrage wurde durchgeführt test0");
                httpURLConnection.setRequestMethod("POST");
                Log.d("Vorschlag", "Abfrage wurde durchgeführt test");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                Log.d("Vorschlag", "Abfrage wurde durchgeführt test35");
                OutputStream outputStream = httpURLConnection.getOutputStream(); //Todo Ab hier geht es nicht mehr weiter
                Log.d("Vorschlag", "Abfrage wurde durchgeführt HIER STEHT JETZT WAS ANDERES");
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("nickname", "UTF-8") + "=" + URLEncoder.encode(nickname, "UTF-8");
                Log.d("Vorschlag", "Abfrage wurde durchgeführt test2");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                Log.d("Vorschlag", "Abfrage wurde durchgeführt3");
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.d("Vorschlag", "Abfrage wurde durchgeführt Pasta");
                Log.d("parseJsonVorschlag", result);

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception:" + e.getMessage();
            }

        }


        if (type.equals("whiskey_select")) {
            try {

                //Todo Hier wird die testdatenbank genommen da die richtige noch keine tabellen hat. Dafuer muss ich aber einen anderen usernamen benutzten
                //nickname = params[1];

                whiskeyid= params[1];

                Log.d("Vorschlag", whiskeyid);


                URL url = new URL(whiskey_select_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                Log.d("Vorschlag", "Abfrage wurde durchgeführt test0");
                httpURLConnection.setRequestMethod("POST");
                Log.d("Vorschlag", "Abfrage wurde durchgeführt test");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                Log.d("Vorschlag", "Abfrage wurde durchgeführt test35");
                OutputStream outputStream = httpURLConnection.getOutputStream(); //Todo Ab hier geht es nicht mehr weiter
                Log.d("Vorschlag", "Abfrage wurde durchgeführt HIER STEHT JETZT WAS ANDERES");
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("whiskeyid", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(whiskeyid), "UTF-8");
                Log.d("Vorschlag", "Abfrage wurde durchgeführt test2");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                Log.d("Vorschlag", "Abfrage wurde durchgeführt3");
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.d("Vorschlag", "Abfrage wurde durchgeführt Pasta");
                Log.d("parseJsonWhiskeyselect", result);

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception:" + e.getMessage();
            }

        }



        return null;
    }


    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    protected void onPostExecute(String result) {

        Home_Activity parse_Json_home = new Home_Activity();
        Suggestion_Activity parse_Json_suggestion = new Suggestion_Activity();
        Whiskey_Selection_Activity parse_Json_whiskey_select =   new Whiskey_Selection_Activity();
        //Home_Activity change_user_data = new Home_Activity();
//Todo Hier muss das mit den nicknamen noch raus. damit der nicht immer probleme bekommt. dort kommt auch ein successwort davor oder dahinter
        if(result.contains(nickname)) {
            parse_Json_home.parseJson(result);

           // change_user_data.change_user_data();

        }
        if(result.contains("whiskeyid")) {

                Log.d("VorschlagOnlineHepler", "Richtiger case");
                parse_Json_suggestion.parseJson(result);



        }
        if(result.contains("whiskeylangbeschreibung_select")) {

            Log.d("Whiskey_SelectOnlineHepler", "Richtiger case");
            parse_Json_whiskey_select.parseJson(result);



        }


       // Intent in1 = new Intent(context, Home_Activity.class);
       // Intent in2 = new Intent(context, Login_Activity.class);

        //     super.onPreExecute();

        switch (result) {
            case "success":
                parse_Json_home.parseJson(result);

               // Home_Activity.parseJson(result);

                break;

            case "get_data_fail":

                Log.d("Message", "Falsche userdaten");

                AlertDialog.Builder login_fail = new AlertDialog.Builder(context);
                login_fail.setTitle("Fail");
                login_fail.setMessage("Es ist etwas schief gelaufe mit der Datenbank");
                login_fail.setCancelable(true);

                AlertDialog login_fail_dialog = login_fail.create();
                login_fail_dialog.show();

                break;

            case "registrationsuccess":

                AlertDialog.Builder registration_success = new AlertDialog.Builder(context);
                registration_success.setTitle("Glückwunsch");
                registration_success.setMessage("Sie haben sich Erfolgreich registriert. Bitte geben sie ihre Login-Daten ein");
                registration_success.setCancelable(true);

                AlertDialog registration_success_dialog = registration_success.create();
                registration_success_dialog.show();

               // context.startActivity(in2);

                break;

            case "nicknamefail":

                AlertDialog.Builder registration_user_fail = new AlertDialog.Builder(context);
                registration_user_fail.setTitle("Fehlermeldung");
                registration_user_fail.setMessage("Es gibt bereits eine Person mit selben Nickname: Bitte einen anderen aussuchen");
                registration_user_fail.setCancelable(true);

                AlertDialog regitration_user_fail_dialog = registration_user_fail.create();
                regitration_user_fail_dialog.show();

                break;

            case "codefail":

                AlertDialog.Builder registration_code_fail = new AlertDialog.Builder(context);
                registration_code_fail.setTitle("Fehlermeldung");
                registration_code_fail.setMessage("Es gibt bereits eine Person mit selben Code: Bitte einen anderen aussuchen");
                registration_code_fail.setCancelable(true);

                AlertDialog registration_code_fail_dialog = registration_code_fail.create();
                registration_code_fail_dialog.show();

                break;

            case "emailfail":

                AlertDialog.Builder registration_email_fail = new AlertDialog.Builder(context);
                registration_email_fail.setTitle("Fehlermeldung");
                registration_email_fail.setMessage("Es wurde bereits ein Account mit dieser E-Mailadresse angelegt.");
                registration_email_fail.setCancelable(true);

                AlertDialog registration_emai_fail_dialog = registration_email_fail.create();
                registration_emai_fail_dialog.show();

                break;

            case "connectionfail":

                AlertDialog.Builder registration_connection_fail = new AlertDialog.Builder(context);
                registration_connection_fail.setTitle("Fehlermeldung");
                registration_connection_fail.setMessage("Es gibt unerwartete Probleme mit dem System: Bitte versuchen sie es später erneut");
                registration_connection_fail.setCancelable(true);

                AlertDialog registration_connection_fail_dialog = registration_connection_fail.create();
                registration_connection_fail_dialog.show();

                break;

            case "defaultfail":

                AlertDialog.Builder registration_default_fail = new AlertDialog.Builder(context);
                registration_default_fail.setTitle("Fehlermeldung");
                registration_default_fail.setMessage("Bitte fühlen sie alle Felder aus");
                registration_default_fail.setCancelable(true);

                AlertDialog registration_default_fail_dialog= registration_default_fail.create();
                registration_default_fail_dialog.show();

                break;



        }
    }

}