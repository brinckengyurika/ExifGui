/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package own.exifgui;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputDirectory;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;
import org.apache.commons.io.FileUtils;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.common.BufferedImageFactory;
import org.apache.commons.imaging.common.ImageMetadata.ImageMetadataItem;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.TiffImageParser;
import org.apache.commons.imaging.formats.tiff.TiffImagingParameters;
import org.apache.commons.imaging.formats.tiff.constants.GpsTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.InvalidPathException;
import java.util.Collections;
import java.util.Vector;
import org.apache.commons.imaging.formats.tiff.constants.TiffConstants;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputField;


/**
 *
 * @author satch
 */
public class OwnUtils {

    public static boolean DirectoryPathCheck(String directorypath) {
        try {
            Path path = Paths.get(directorypath);
            if (Files.exists(path)) {
                if (Files.isRegularFile(path)) {
                    return false;
                } else if (Files.isDirectory(path)) {
                    return true;
                }
            }
        } catch (InvalidPathException e) {
            return false; 
        }
        return false;
    }

    public static boolean IsDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NullPointerException e) {
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static List<String> readFileInList(String fileName)
    {
        List<String> lines = Collections.emptyList();
      	try {
            lines = Files.readAllLines(
                Paths.get(fileName),
                StandardCharsets.UTF_8);
        } catch(IOException e) {
            // do something
            e.printStackTrace();
        }
        return lines;
    }    
    
    
    public static List<ImageMetadataItem> readExifdataHashMap(final File file) throws ImagingException, IOException {
        List<ImageMetadataItem> items = new ArrayList<ImageMetadataItem>();
        final ImageMetadata metadata = Imaging.getMetadata(file);
        if (metadata instanceof JpegImageMetadata) {
            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
            items = jpegMetadata.getItems();
        }
        return items;
    }

    public static void readExifdata(final File file) throws ImagingException, IOException {
        final ImageMetadata metadata = Imaging.getMetadata(file);
        if (metadata instanceof JpegImageMetadata) {
            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
            System.out.println("file: " + file.getPath());
            printTagValue(jpegMetadata, TiffTagConstants.TIFF_TAG_XRESOLUTION);
            printTagValue(jpegMetadata, TiffTagConstants.TIFF_TAG_DATE_TIME);
            printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL);
            printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_DATE_TIME_DIGITIZED);
            printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_ISO);
            printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_SHUTTER_SPEED_VALUE);
            printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_APERTURE_VALUE);
            printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_BRIGHTNESS_VALUE);
            printTagValue(jpegMetadata, GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF);
            printTagValue(jpegMetadata, GpsTagConstants.GPS_TAG_GPS_LATITUDE);
            printTagValue(jpegMetadata, GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF);
            printTagValue(jpegMetadata, GpsTagConstants.GPS_TAG_GPS_LONGITUDE);
            System.out.println();

            final TiffImageMetadata exifMetadata = jpegMetadata.getExif();
            if (null != exifMetadata) {
                final TiffImageMetadata.GpsInfo gpsInfo = exifMetadata.getGpsInfo();
                if (null != gpsInfo) {
                    final String gpsDescription = gpsInfo.toString();
                    final double longitude = gpsInfo.getLongitudeAsDegreesEast();
                    final double latitude = gpsInfo.getLatitudeAsDegreesNorth();

                    System.out.println("    " + "GPS Description: " + gpsDescription);
                    System.out.println("    " + "GPS Longitude (Degrees East): " + longitude);
                    System.out.println("    " + "GPS Latitude (Degrees North): " + latitude);
                }
            }

            // more specific example of how to manually access GPS values
            final TiffField gpsLatitudeRefField = jpegMetadata.findExifValueWithExactMatch(GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF);
            final TiffField gpsLatitudeField = jpegMetadata.findExifValueWithExactMatch(GpsTagConstants.GPS_TAG_GPS_LATITUDE);
            final TiffField gpsLongitudeRefField = jpegMetadata.findExifValueWithExactMatch(GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF);
            final TiffField gpsLongitudeField = jpegMetadata.findExifValueWithExactMatch(GpsTagConstants.GPS_TAG_GPS_LONGITUDE);
            if (gpsLatitudeRefField != null && gpsLatitudeField != null && gpsLongitudeRefField != null && gpsLongitudeField != null) {
                // all of these values are strings.
                final String gpsLatitudeRef = (String) gpsLatitudeRefField.getValue();
                final RationalNumber[] gpsLatitude = (RationalNumber[]) gpsLatitudeField.getValue();
                final String gpsLongitudeRef = (String) gpsLongitudeRefField.getValue();
                final RationalNumber[] gpsLongitude = (RationalNumber[]) gpsLongitudeField.getValue();

                final RationalNumber gpsLatitudeDegrees = gpsLatitude[0];
                final RationalNumber gpsLatitudeMinutes = gpsLatitude[1];
                final RationalNumber gpsLatitudeSeconds = gpsLatitude[2];

                final RationalNumber gpsLongitudeDegrees = gpsLongitude[0];
                final RationalNumber gpsLongitudeMinutes = gpsLongitude[1];
                final RationalNumber gpsLongitudeSeconds = gpsLongitude[2];
                System.out.println("    " + "GPS Latitude: " + gpsLatitudeDegrees.toDisplayString() + " degrees, " + gpsLatitudeMinutes.toDisplayString()
                        + " minutes, " + gpsLatitudeSeconds.toDisplayString() + " seconds " + gpsLatitudeRef);
                System.out.println("    " + "GPS Longitude: " + gpsLongitudeDegrees.toDisplayString() + " degrees, " + gpsLongitudeMinutes.toDisplayString()
                        + " minutes, " + gpsLongitudeSeconds.toDisplayString() + " seconds " + gpsLongitudeRef);
            }

            System.out.println();

            final List<ImageMetadataItem> items = jpegMetadata.getItems();
            for (final ImageMetadataItem item : items) {
                System.out.println("    " + "item: " + item);
            }

            System.out.println();
        }
    }

    private static String getTagValue(final JpegImageMetadata jpegMetadata, final TagInfo tagInfo) {
        final TiffField field = jpegMetadata.findExifValueWithExactMatch(tagInfo);
        if (field == null) {
            return "Not found.";
        }
        return field.getValueDescription();
    }

    private static void printTagValue(final JpegImageMetadata jpegMetadata, final TagInfo tagInfo) {
        final TiffField field = jpegMetadata.findExifValueWithExactMatch(tagInfo);
        if (field == null) {
            System.out.println(tagInfo.name + ": " + "Not Found.");
        } else {
            System.out.println(tagInfo.name + ": " + field.getValueDescription());
        }
    }
/*
    public static void changeExifMetadata(final File jpegImageFile, String usercomment, LatLonObj latlon, boolean overwrite) throws IOException, ImagingException, ImagingException {
//        String tempdir = System.getProperty("java.io.tmpdir");
        File temporary_file = File.createTempFile("jpeg","Exif");
        System.out.println(temporary_file.getPath());
        try (FileOutputStream fos = new FileOutputStream(temporary_file); OutputStream os = new BufferedOutputStream(fos)) {
            TiffOutputSet outputSet = null;
            final ImageMetadata metadata = Imaging.getMetadata(jpegImageFile);
            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
            if (jpegMetadata != null) {
                final TiffImageMetadata exif = jpegMetadata.getExif();
                if (exif != null) {
                    outputSet = exif.getOutputSet();
                }
            }
            if (outputSet != null) {
                outputSet = new TiffOutputSet();
            }
            final TiffOutputDirectory exifDirectory = outputSet.getOrCreateExifDirectory();

            byte[] comment = usercomment.getBytes("UnicodeLittle");
            TiffOutputField exif_comment = new TiffOutputField(TiffConstants.EXIF_TAG_USER_COMMENT, TiffFieldTypeConstants.FIELD_TYPE_UNDEFINED, comment.length, comment);
            exifDirectory.add(exif_comment);
            outputSet.setGpsInDegrees(Double.parseDouble(latlon.lon), Double.parseDouble(latlon.lat));
            
            new ExifRewriter().updateExifMetadataLossless(jpegImageFile, os, outputSet);
            temporary_file.renameTo(jpegImageFile);            
        }
        //temporary_file.delete();
    }
*/
    public static void removeExifMetadata(final File jpegImageFile, final File dst) throws IOException, ImagingException, ImagingException {
        try (FileOutputStream fos = new FileOutputStream(dst); OutputStream os = new BufferedOutputStream(fos)) {
            new ExifRewriter().removeExifMetadata(jpegImageFile, os);
        }
    }

    public static void removeExifTag(final File jpegImageFile, final File dst) throws IOException, ImagingException, ImagingException {
        try (FileOutputStream fos = new FileOutputStream(dst); OutputStream os = new BufferedOutputStream(fos)) {
            TiffOutputSet outputSet = null;

            // note that metadata might be null if no metadata is found.
            final ImageMetadata metadata = Imaging.getMetadata(jpegImageFile);
            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
            if (null != jpegMetadata) {
                // note that exif might be null if no Exif metadata is found.
                final TiffImageMetadata exif = jpegMetadata.getExif();

                if (null != exif) {
                    outputSet = exif.getOutputSet();
                }
            }

            if (null == outputSet) {
                FileUtils.copyFile(jpegImageFile, dst);
                return;
            }
            outputSet.removeField(ExifTagConstants.EXIF_TAG_APERTURE_VALUE);
            final TiffOutputDirectory exifDirectory = outputSet.getExifDirectory();
            if (null != exifDirectory) {
                exifDirectory.removeField(ExifTagConstants.EXIF_TAG_APERTURE_VALUE);
            }
            new ExifRewriter().updateExifMetadataLossless(jpegImageFile, os, outputSet);
        }
    }

    public static void setExifGPSTag(String source_path, double longitude, double latitude) throws IOException, ImagingException, ImagingException {
        try {
            File jpegImageFile = new File(source_path);
            String temporary_destination_filename = "temporary.jpg";
            File dst = new File(temporary_destination_filename);
            final ImageMetadata metadata = Imaging.getMetadata(jpegImageFile);
            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
            if (null != jpegMetadata) {
                try (FileOutputStream fos = new FileOutputStream(dst); OutputStream os = new BufferedOutputStream(fos)) {
                    TiffOutputSet outputSet = null;
                    final TiffImageMetadata exif = jpegMetadata.getExif();
                    if (null != exif) {
                        outputSet = exif.getOutputSet();
                    }
                    if (null == outputSet) {
                        outputSet = new TiffOutputSet();
                    }
                    final double lotude = longitude;
                    final double latude = latitude;
                    outputSet.setGpsInDegrees(lotude, latude);
                    new ExifRewriter().updateExifMetadataLossless(jpegImageFile, os, outputSet);
                    File f1 = new File(temporary_destination_filename);
                    File f2 = new File(source_path);
                    f1.renameTo(f2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void appendStrToFile(String fileName, String str) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(str);
            out.close();
        } catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }
    
    public static void saveListVector(String fileName, Vector data) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, false));
            Iterator <String> iter = data.iterator();
            while(iter.hasNext()) {
                out.write(iter.next()+System.lineSeparator());
            }
            out.close();
        } catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }    

    public static Vector<JsonNode> AskAboutTheLatLOnTheOpenstreetmap(String location)  throws IOException {
        //"https://nominatim.openstreetmap.org/search?q=Budapest+Kossuth+utca+12&format=jsonv2"  
        Vector <JsonNode> ret = new Vector();
        try {
            String surl = "https://nominatim.openstreetmap.org/search";
            String format = "format=jsonv2";
            String[] pslitted = location.split("[,\\.\\s]");
            String question = String.join("+", pslitted);
            String fulluri = "%s?q=%s&%s".formatted(surl, question, format);
            System.out.println(fulluri);
            URL obj = new URL(fulluri);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");        
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    System.out.println(inputLine);
                }
                in.close();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode addrjNode;
                JsonNode jNode = mapper.readTree(response.toString());
                System.out.println(response.toString());
                System.out.println(jNode.getNodeType());
                Iterator <JsonNode> node = jNode.elements();
                while (node.hasNext()) {
                    ret.add(node.next());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }            
        return ret;
    }

    public static void sendPOST(String POST_URL, String POST_PARAMS) throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
//		con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request did not work.");
        }
    }
}
