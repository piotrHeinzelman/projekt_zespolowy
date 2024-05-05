package com.example.shoop.config;

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


    public static void moveUploadedToFile( String targetFolder, String fileName, MultipartFile file ){
        File destFile = new File( targetFolder + fileName );
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
