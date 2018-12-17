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

import static android.support.v4.content.ContextCompat.startActivity;


public class LoginHelper extends AsyncTask<String, Void, String> {

    String ip;
    String login_url;
    String registration_url;
    Context context;
    AlertDialog alertDialog;

    String nickname;
    String firstname;
    String lastname;
    String birth;
    String email;
    String code;
    String password;
    String type;


    public LoginHelper(Context ctx) {
        context = ctx;
    }


    protected void onPreExecute() {

        ip = "http://192.168.2.121";

        login_url = ip + "/mark3/SELECT/user_select.php";

        registration_url = ip + "/mark3/INSERT/user_insert_test.php";


    }


    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        {
            if (type.equals("find_login")) {
                try {


                    nickname = params[1];
                    password = params[2];
                    Log.d("Message", "Abfrage wurde durchgeführt Pizza");


                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    Log.d("Message", "Abfrage wurde durchgeführt test0");
                    httpURLConnection.setRequestMethod("POST");
                    Log.d("Message", "Abfrage wurde durchgeführt test");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    Log.d("Message", "Abfrage wurde durchgeführt test35");
                    OutputStream outputStream = httpURLConnection.getOutputStream(); //Todo Ab hier geht es nicht mehr weiter
                    Log.d("Message", "Abfrage wurde durchgeführt test1");
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("nickname", "UTF-8") + "=" + URLEncoder.encode(nickname, "UTF-8") + "&"
                            + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
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

        }

        if (type.equals("find_registration")) {


            try {

                nickname = params[1];
                firstname = params[2];
                lastname = params[3];
                birth = params[4];
                email = params[5];
                code = params[6];
                password = params[7];

                Log.d("Message", "Abfrage wurde durchgeführt Pizza");


                URL url = new URL(registration_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                Log.d("Message", "Abfrage wurde durchgeführt test0");
                httpURLConnection.setRequestMethod("POST");
                Log.d("Message", "Abfrage wurde durchgeführt test");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                Log.d("Message", "Abfrage wurde durchgeführt test35");
                OutputStream outputStream = httpURLConnection.getOutputStream(); //Todo Ab hier geht es nicht mehr weiter
                Log.d("Message", "Abfrage wurde durchgeführt test1");
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =
                        URLEncoder.encode("nickname", "UTF-8") + "=" + URLEncoder.encode(nickname, "UTF-8") + "&"
                                + URLEncoder.encode("firstname", "UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8") + "&"
                                + URLEncoder.encode("lastname", "UTF-8") + "=" + URLEncoder.encode(lastname, "UTF-8") + "&"
                                + URLEncoder.encode("birth", "UTF-8") + "=" + URLEncoder.encode(birth, "UTF-8") + "&"
                                + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                                + URLEncoder.encode("code", "UTF-8") + "=" + URLEncoder.encode(code, "UTF-8") + "&"
                                + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                // Log.d("Message", "Abfrage wurde durchgeführt test2");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                //  Log.d("Message", "Abfrage wurde durchgeführt3");
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                //  Log.d("Message", "Abfrage wurde durchgeführt Pasta");
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

        Intent in1 = new Intent(context, Home_Activity.class);
        Intent in2 = new Intent(context, Login_Activity.class);

        //     super.onPreExecute();


        switch (result) {
            case "success":
                in1.putExtra("nickname", nickname);
               context.startActivity(in1);

                break;

            case "fail":

                Log.d("Message", "Falsche userdaten");

                AlertDialog.Builder login_fail = new AlertDialog.Builder(context);
                login_fail.setTitle("Fail");
                login_fail.setMessage("Bitte Login-Daten überprüfen");
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

                context.startActivity(in2);

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