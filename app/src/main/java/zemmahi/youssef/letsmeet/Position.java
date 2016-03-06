package zemmahi.youssef.letsmeet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;

/**
 * Created by youssef on 24/02/2016.
 */

// exemple d'information recupere pour une position formet json : https://developers.google.com/maps/documentation/geocoding/intro?hl=fr
public class Position {

    private String id_=new String();
    private double longitude_;
    private double latitude_;
    private double radius_;
    private Adresse adresse_;
    private Date date_;

    public Position()
    {

    }
    public Position(String id,double lng, double lat)
    {
        id_=id;
        longitude_=lng;
        latitude_=lat;
        date_= Calendar.getInstance().getTime();;
    }

    public String getId() {
        return id_;
    }

    public void setId(String id) {
        this.id_ = id;
    }

    public double getLongitude() {
        return longitude_;
    }

    public void setLongitude(double longitude) {
        this.longitude_ = longitude;
    }

    public double getLatitude() {
        return latitude_;
    }

    public void setLatitude(double latitude) {
        this.latitude_ = latitude;
    }

    public Adresse getAdresse() {
        return adresse_;
    }

    public void setAdresse_(Adresse adresse) {
        this.adresse_ = adresse;
    }

    public Date getDate()
    {
        return date_;
    }

    public void setActualDateAndTime()
    {
        date_= Calendar.getInstance().getTime();
    }
    public void setDate(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
        try {
            date_=sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public String getDateString()
    {
        DateFormat df = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
        return  df.format(date_);
    }

    public double getRadius() {
        return radius_;
    }

    public void setRadius(double radius) {
        this.radius_ = radius;
    }
}
