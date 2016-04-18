package t4ka.com.lifecyclestudy.commons;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by taka-dhu on 2016/04/17.
 */
public class Monochromize extends AsyncTask<Bitmap, Integer, Bitmap> {
    private ImageView imageView;
    private ProgressDialog progressDialog;//for progress dialog
    private Activity uiActivity;//for progress dialog

    //Activity activity -> for progress dialog
    public Monochromize(Activity activity,ImageView iv){
        super();
        uiActivity = activity;
        imageView = iv;
    }

    /**
     * AsyncTask must get <Params, Progress, Result>
     *     these are referenced from ↓
     *
     * onPreExecute() -> write preparing things before doInBackground
     * doInbackground(Params...) -> write the process which u wanna do in background
     * onProgressUpdate(Progress...) -> write to show the progress of process in UI thread
     *      like a progress-bar
     * onPostExecute(Result) -> write the process
     *  which show or reflect to the UI thread after completed process in background
     */

    //for progress dialog
    @Override
    protected void onPreExecute(){
        progressDialog = new ProgressDialog(uiActivity);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
    }

    @Override
    protected Bitmap doInBackground(Bitmap... bitMap){
        Bitmap outBitMap = bitMap[0].copy(Bitmap.Config.ARGB_8888,true);

        int width = outBitMap.getWidth();
        int height = outBitMap.getHeight();
        int totalPixcel = width * height;//for progress dialog
        progressDialog.setMax(totalPixcel);//for progress dialog

        int i,j;
        for(i = 0;i < height;i++){
            for(j = 0;j < width;j++){
                int pixcelColor = outBitMap.getPixel(j,i);
                int y = (int) (0.299 * Color.red(pixcelColor) +
                0.587 * Color.green(pixcelColor) +
                0.114 * Color.blue(pixcelColor));
                outBitMap.setPixel(j,i, Color.rgb(y,y,y));
            }
            onProgressUpdate(j + i);//for progress dialog
        }
        return outBitMap;
    }

    //for progress dialog
    @Override
    protected  void onProgressUpdate(Integer... progress){
        progressDialog.incrementProgressBy(progress[0]);
    }


    @Override
    protected void onPostExecute(Bitmap result){
        progressDialog.dismiss();//for progress dialog
        imageView.setImageBitmap(result);
    }


}
