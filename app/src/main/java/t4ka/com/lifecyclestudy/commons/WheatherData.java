package t4ka.com.lifecyclestudy.commons;

/**
 * Created by taka-dhu on 2016/04/18.
 * use this api for connecting↓
 * http://weather.livedoor.com/weather_hacks/webservice
 * And also this class is container for data u'v gotten
 * U MUST Determine which data u use or show
 */
public class WheatherData {
    
    public String publicTime;
    //description whether of where
    public String wTitle;
    public String descriptionText;
    public String descriptionTime;
    public String wLink;
    //public String[] wForcast;
    //area + "/" + prefecture + "/" + city
    public String wLocation;
    public String wCopyright;
}
