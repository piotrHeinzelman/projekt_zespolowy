package com.example.shoop.config;

import com.example.shoop.model.Picture;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileTool {
    private static final String fileroot="./files/";
    private FileWriter fw=null;

    public FileTool() {}
    public FileTool(String fileName ) {
        File f = new File( fileroot + fileName );
        try {
            if (!f.exists()) { f.createNewFile(); }
            fw = new FileWriter( f , true );
            } catch ( Throwable t ){ System.out.println( t ); }
    }


    public static void moveUploadedToFile( Picture picture, MultipartFile file ){
        File destFile = new File( fileroot + picture.getPict_id() + ".png" );
        try (InputStream in = file.getInputStream();
             OutputStream out = new FileOutputStream(destFile)) {
            IOUtils.copy(in, out);
        } catch (Exception ex) {
        }
    }

    public String getFileRoot() {
        return fileroot;
    }
}
