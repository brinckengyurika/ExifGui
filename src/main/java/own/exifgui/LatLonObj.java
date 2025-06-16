/**
 *
 * @author Gyorgy Brincken brinckengyurika@gmail.com
 * https://github.com/brinckengyurika/ExifGui
 * 
 */
package own.exifgui;

import java.util.Base64;


public class LatLonObj {
    private final double min_distance = 0.0004; //~44.5m
    private String LocationName;
    private String Location;
    private String lat;
    private String lon;
    
    public LatLonObj() {
        this.Location = "";
        this.LocationName = "";
        this.lat = "";
        this.lon = "";
    }
    
    public LatLonObj(LatLonObj obj) {
        this.Location = obj.Location;
        this.LocationName = obj.LocationName;
        this.lat = obj.lat;
        this.lon = obj.lon;
    }

    public LatLonObj(String encoded_line) {
        String splitted[] = encoded_line.split("\\.");
        this.Location = new String(Base64.getDecoder().decode(splitted[0]));
        this.LocationName = new String(Base64.getDecoder().decode(splitted[1]));
        this.lat = new String(Base64.getDecoder().decode(splitted[2]));
        this.lon = new String(Base64.getDecoder().decode(splitted[3]));
    }        

    public double getLat() {
        return Double.parseDouble(this.lat);
    }

    public double getLon() {
        return Double.parseDouble(this.lon);
    }
    
    public String getLatS() {
        return this.lat;
    }

    public String getLonS() {
        return this.lon;
    }    
    
    public String getLocation() {
        return this.Location;
    }

    public String getLocationName() {
        return this.LocationName;
    }
    
    public String toUndexodedString(){
        return "%s [%s] Lat: %s \t Lon: %s".formatted(this.LocationName, this.Location, this.lat, this.lon);
    }    
    
    @Override
    public String toString(){
        return OwnUtils.encodetoBase64(new String[] {this.LocationName, this.Location, this.lat, this.lon});
    }    
 
    public LatLonObj movelatlon(Object selected, int progress, int volumen) {
        LatLonObj ret = new LatLonObj(this);
        if ("Exactly".equals(selected.toString())) {
            //Placeholder
        } else if ("Matrix".equals(selected.toString())) {
            int x_size = (int) Math.sqrt((double) volumen);
//            int y_size = ((int) volumen/x_size);
            int mul = (int) progress / x_size;
            int x_offset = progress - mul * x_size;
            double lat_offset = mul * this.min_distance;
            double lon_offset = x_offset * this.min_distance;
            double newlat = ret.getLat() + lat_offset;
            double newlon = ret.getLon() + lon_offset;
            ret.lat = Double.toString(newlat);
            ret.lon = Double.toHexString(newlon);
        } else if ("Circle".equals(selected.toString())) {
            ++progress;
            int pow = OwnUtils.log2nlz(progress);
            int remain = (int) progress - (int) Math.pow(2, pow);
            double last_circle = (int) Math.pow(2, pow+1);
            double last_circle_div = 360/last_circle;
            double angle = last_circle_div * remain;
            double radiant = pow * this.min_distance;
            double rel_lat = radiant * Math.cos(angle * Math.PI/180);
            double rel_lon = radiant * Math.sin(angle * Math.PI/180);
            double newlat = ret.getLat() + rel_lat;
            double newlon = ret.getLon() + rel_lon;
            ret.lat = Double.toString(newlat);
            ret.lon = Double.toHexString(newlon);
        }
        System.out.println(ret.toUndexodedString());
        return ret;
    }
    
    
}
