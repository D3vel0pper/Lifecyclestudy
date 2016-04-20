package t4ka.com.lifecyclestudy.commons;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
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
import java.util.WeakHashMap;

import t4ka.com.lifecyclestudy.R;

/**
 * Created by taka-dhu on 2016/04/18.
 */
public class HttpResponsTask extends AsyncTask <Void,Void,WheatherData> {

    private String LocBaseUrl;
    private Activity act = null;
    private TextView textView;
    //layout which have each textview to show
    private LinearLayout linearLayout;

    public HttpResponsTask(Activity activity,TextView tv,LinearLayout ll){
        this.LocBaseUrl = activity.getString(R.string.LocBaseUrl);
        this.act = activity;
        this.textView = tv;
        this.linearLayout = ll;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected WheatherData doInBackground(Void... params){

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
        //make instance of Data
        WheatherData data = new WheatherData();
        //get JSON Data and then put to the Data structure
        try{
            JSONObject jsonObject = new JSONObject(result);
            data.setTitle(jsonObject.getString("title"));
            //data.setDescriptionText(jsonObject.getJSONObject("description").getString("text"));
            data.setDescriptionTime(jsonObject.getJSONObject("description").getString("publicTime"));
            data.setLocation(jsonObject.getJSONObject("location").getString("area") + " / " +
                    jsonObject.getJSONObject("location").getString("prefecture") + " / " +
                    jsonObject.getJSONObject("location").getString("city"));
            JSONArray jsonArray = jsonObject.getJSONObject("copyright").getJSONArray("provider");
            JSONObject temp = jsonArray.getJSONObject(0);
            data.setwCopyright(temp.getString("name") + " : " + temp.getString("link") + "\n" +
            jsonObject.getJSONObject("copyright").getString("link") + jsonObject.getJSONObject("copyright").getString("title"));

        } catch(JSONException e){
            Log.d("JSONException",e.toString());
        } catch(NullPointerException e){
            Log.d("NullPointerException",e.toString());
        }

        //Return
        return data;
    }

    @Override
    protected void onPostExecute(WheatherData result){
        super.onPostExecute(result);
        if(result == null){
            Toast.makeText(act,"!! result is null !!",Toast.LENGTH_SHORT).show();
        } else{
            TextView wTitle = (TextView)linearLayout.findViewById(R.id.wTitle);
            wTitle.setText(result.getTitle());
            TextView descTime = (TextView)linearLayout.findViewById(R.id.descTime);
            descTime.setText(result.getDescriptionTime());
            TextView descText = (TextView)linearLayout.findViewById(R.id.description);
            descText.setText(result.getDescriptionText());
            TextView wLocation = (TextView)linearLayout.findViewById(R.id.location);
            wLocation.setText(result.getLocation());
            TextView wCopyright = (TextView)linearLayout.findViewById(R.id.copyright);
            wCopyright.setText(result.getCopyright());
            linearLayout.invalidate();
        }
    }


}
