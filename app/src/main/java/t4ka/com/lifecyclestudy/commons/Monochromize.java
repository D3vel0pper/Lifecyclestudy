package t4ka.com.lifecyclestudy.commons;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by taka-dhu on 2016/04/17.
 */
public class Monochromize extends AsyncTask<Bitmap, Integer, Bitmap> {
    private ImageView imageView;
    public Monochromize(ImageView iv){
        super();
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

    @Override
    protected Bitmap doInBackground(Bitmap... bitMap){
        Bitmap outBitMap = bitMap[0].copy(Bitmap.Config.ARGB_8888,true);

        int width = outBitMap.getWidth();
        int height = outBitMap.getHeight();

        int i,j;
        for(i = 0;i < height;i++){
            for(j = 0;j < width;j++){
                int pixcelColor = outBitMap.getPixel(j,i);
                int y = (int) (0.299 * Color.red(pixcelColor) +
                0.587 * Color.green(pixcelColor) +
                0.114 * Color.blue(pixcelColor));
                outBitMap.setPixel(j,i, Color.rgb(y,y,y));
            }
        }
        return outBitMap;
    }

    @Override
    protected void onPostExecute(Bitmap result){
        imageView.setImageBitmap(result);
    }


}
