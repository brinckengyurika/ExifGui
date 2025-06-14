/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package own.exifgui;

import java.util.Base64;


/**
 *
 * @author satch
 */
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
