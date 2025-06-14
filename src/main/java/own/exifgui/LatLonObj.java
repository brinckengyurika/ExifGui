/**
 *
 * @author Gyorgy Brincken brinckengyurika@gmail.com
 * https://github.com/brinckengyurika/ExifGui
 * 
 */
package own.exifgui;

import java.util.Base64;


public class LatLonObj {
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
    
    @Override
    public String toString(){
        return OwnUtils.encodetoBase64(new String[] {this.LocationName, this.Location, this.lat, this.lon});
    }    
    
}
