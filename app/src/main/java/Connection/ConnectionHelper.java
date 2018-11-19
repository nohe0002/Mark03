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
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.noah.whiskey_app_mark_03.Login_Activity;


public class ConnectionHelper extends AsyncTask<String, Void, String> {


    String login_url;
    String registration_url;
    String type;
   // Context context;

 //   public ConnectionHelper(Context ctx) {
  //     context = ctx;
  // }



    protected void onPreExecute() {

        login_url = "http://192.168.2.121/mark3/SELECT/user_select.php?";
        registration_url = "http://10.9.42.55:80/webapp/update_data.php";

    }


    @Override
    protected String doInBackground(String ... params){
        type = params[0];
        {
            if(type.equals("find_login")) {
                try {
                    String username = params[1];
                    String password = params[2];
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
                    String post_data = URLEncoder.encode("nickname","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                            +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                    Log.d("Message", "Abfrage wurde durchgeführt test2");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result="";
                    String line="";
                    Log.d("Message", "Abfrage wurde durchgeführt3");
                    while((line = bufferedReader.readLine())!= null){
                        result+= line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    Log.d("Message", "Abfrage wurde durchgeführt Pasta");
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return "Exception: "+e.getMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Exception:"+e.getMessage();
                }

            }

        }

        if(type.equals("getCommunity")) {

        }
        return null;
    }


    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


     protected void onPostExecute(String result) {
         if (result.contains("nickname"))

             Login_Activity.check_login(result);

         {
            // OnlineActivity.parseJson(result);
         }
         if (result.contains("Data update"))
         {
           // Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            // OnlineActivity.uploadData.setIcon(context.getResources().getDrawable(R.mipmap.update_data));
         }
         if(result.contains("timeline"))
         {
             //TimelineActivity.txtJson.setText(result);
            // TimelineActivity.parse(result);
         }
         if(result.contains("community"))
         {
             //CommunityActivity.txtJson.setText(result);
            // CommunityActivity.parse(result);
         }

     }




}
