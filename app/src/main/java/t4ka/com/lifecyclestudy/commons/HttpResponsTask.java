package t4ka.com.lifecyclestudy.commons;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
    private TextView textView;

    public HttpResponsTask(Activity activity,TextView tv){
        this.LocBaseUrl = activity.getString(R.string.LocBaseUrl);
        this.act = activity;
        this.textView = tv;
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
            //Make URL (this example shows Kurume city in Fukuoka)
            url = new URL(LocBaseUrl + "?city=400040");
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

            InputStream in = con.getInputStream();
            String readSt = readInputStream(in);
            return readSt;
            /*
            try{
                JSONObject jsonData = new JSONObject(readSt).getJSONObject("");
            } catch(JSONException e){
                e.printStackTrace();
                flag = false;
            }
            */



        } catch(MalformedURLException e){
            e.printStackTrace();
            flag = false;
        } catch(IOException e){
            e.printStackTrace();
            Log.d("IOException",e.toString());
            flag = false;
        }


        return null;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        if(result == null){
            Toast.makeText(act,"!! result is null !!",Toast.LENGTH_SHORT).show();
        } else{
            textView.setText(result);
            textView.invalidate();
            //Toast.makeText(act,"Failed to connect....",Toast.LENGTH_SHORT).show();

        }
    }

    //for get JSON data
    public String readInputStream(InputStream in) throws IOException, UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        String st = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
        while((st = br.readLine()) != null){
            sb.append(st);
        }
        try {
            in.close();
        } catch(Exception e){
            e.printStackTrace();
            flag = false;
        }

        return sb.toString();
    }

}
