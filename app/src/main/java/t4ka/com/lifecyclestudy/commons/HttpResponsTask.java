package t4ka.com.lifecyclestudy.commons;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

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

        String result = null;

        //Make Request Object
        Request request = new Request.Builder()
                .url(LocBaseUrl + "?city=400040").get().build();
        //Make Client Object
        OkHttpClient client = new OkHttpClient();
        //Request then get result
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch(IOException e){
            Log.d("IOException",e.toString());
        }

        //Return
        return result;
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


}
