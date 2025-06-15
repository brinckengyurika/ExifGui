/**
 *
 * @author Gyorgy Brincken brinckengyurika@gmail.com
 * https://github.com/brinckengyurika/ExifGui
 * 
 */
package own.exifgui;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputDirectory;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.common.ImageMetadata.ImageMetadataItem;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.Base64;
import java.util.Collections;
import java.util.Vector;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputField;

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
   
    public static String encodetoBase64(String[] original) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < original.length; ++i) {
            sb.append(Base64.getEncoder().encodeToString(original[i].getBytes()));
            if (i != original.length-1) {
                sb.append(".");
            }
        }
        return sb.toString();
    }
    
    public static boolean IsDouble(String s) {
        try {
            Double.valueOf(s);
        } catch (NullPointerException | NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static List<String> readFileInList(String fileName) {
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
        List<ImageMetadataItem> items = new ArrayList();
        final ImageMetadata metadata = Imaging.getMetadata(file);
        if (metadata instanceof JpegImageMetadata) {
            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
            items = jpegMetadata.getItems();
        }
        return items;
    }

    public static void changeExifMetadata(String source_path, String comment, LatLonObj latlonobj, boolean overwrite) throws ImagingException, ImagingException, FileNotFoundException {
        try {
            File jpegImageFile = new File(source_path);
            String temporary_destination_filename = "temporary.jpg";
            File dst = new File(temporary_destination_filename);
            ImageMetadata metadata = Imaging.getMetadata(jpegImageFile);
            JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
            TiffOutputSet outputSet = null;
            FileOutputStream fos = new FileOutputStream(dst); 
            OutputStream os = new BufferedOutputStream(fos);
            if (jpegMetadata == null) {
                outputSet = new TiffOutputSet();
            } else {
                TiffImageMetadata exif = jpegMetadata.getExif();
                if (null != exif) {
                    outputSet = exif.getOutputSet();
                }
            }
            TiffOutputDirectory exifDirectory = outputSet.getOrCreateExifDirectory();
            exifDirectory.removeField(ExifTagConstants.EXIF_TAG_USER_COMMENT);
            List <TiffOutputField> exiflist = exifDirectory.getFields();
            exifDirectory.add(ExifTagConstants.EXIF_TAG_USER_COMMENT, comment);
            outputSet.setGpsInDegrees(latlonobj.getLon(), latlonobj.getLat());
            new ExifRewriter().updateExifMetadataLossless(jpegImageFile, os, outputSet);
            File f1 = new File(temporary_destination_filename);
            File f2 = new File(source_path);
            f1.renameTo(f2);
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public static void appendStrToFile(String fileName, String str) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true))) {
            out.write(str);
        } catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }
    
    public static void saveListVector(String fileName, Vector data) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName, false))) {
            Iterator <Object> iter = data.iterator();
            while(iter.hasNext()) {
                out.write(iter.next().toString()+System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }    

    public static Vector<JsonNode> AskAboutTheLatLOnTheOpenstreetmap(String location) {
        Vector <JsonNode> ret = new Vector();
        try {
            String surl = "https://nominatim.openstreetmap.org/search";
            String format = "format=jsonv2";
            String[] pslitted = location.split("[,\\.\\s]");
            String question = String.join("+", pslitted);
            String fulluri = "%s?q=%s&%s".formatted(surl, question, format);
            URL obj = new URL(fulluri);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");        
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jNode = mapper.readTree(response.toString());
                Iterator <JsonNode> node = jNode.elements();
                while (node.hasNext()) {
                    ret.add(node.next());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }            
        return ret;
    }
}
