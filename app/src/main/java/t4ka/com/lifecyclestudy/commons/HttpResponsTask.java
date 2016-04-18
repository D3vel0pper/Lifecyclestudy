package t4ka.com.lifecyclestudy.commons;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import t4ka.com.lifecyclestudy.R;

/**
 * Created by taka-dhu on 2016/04/18.
 */
public class HttpResponsTask extends AsyncTask <Void,Void,String> {

    private String LocBaseUrl;
    private Activity act = null;
    private boolean flag = true;

    public HttpResponsTask(Activity activity){
        this.LocBaseUrl = activity.getString(R.string.LocBaseUrl);
        this.act = activity;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params){
        HttpURLConnection con = null;
        URL url = null;

        try {
            //Make URL
            url = new URL("http://weather.livedoor.com/weather_hacks/webservice");
            //Make Connection Object
            con = (HttpURLConnection)url.openConnection();
            //Set Request Method
            con.setRequestMethod("POST");
            //false -> not allow auto redirect
            con.setInstanceFollowRedirects(false);
            //true if u wanna read from connection
            con.setDoInput(true);
            //true if u wanna write in connection
            con.setDoOutput(true);

            //Connect
            con.connect();

        } catch(MalformedURLException e){
            e.printStackTrace();
            flag = false;
        } catch(IOException e){
            e.printStackTrace();
            flag = false;
        }


        return null;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        if(flag){
            Toast.makeText(act,"Connection Success",Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(act,"Failed to connect....",Toast.LENGTH_SHORT).show();
        }
    }
}
