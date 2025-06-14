/**
 *
 * @author Gyorgy Brincken brinckengyurika@gmail.com
 * https://github.com/brinckengyurika/ExifGui
 * 
 */
package own.exifgui;

import java.util.Base64;

public class Comment {
    private String comment;
    
    public Comment() {
        this.comment = "";
    }
    
    public Comment(String encoded) {
        this.comment = new String(Base64.getDecoder().decode(encoded));
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    @Override
    public String toString() {
        return OwnUtils.encodetoBase64(new String[] {this.comment});
    }
}
