package com.example.shoop.config;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.compress.utils.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/media")
public class MediaController {

    private FileTool fileTool = new FileTool();

    @GetMapping(value = "/fimages/{imgId}", produces = {"image/png"})
    public @ResponseBody
    byte[] getPng170( @PathVariable String imgId ) {
        String source=fileTool.getFileRoot()  + imgId + ".png";
        System.out.println(  source );
        try {
            FileInputStream fis = new FileInputStream( source );
            return IOUtils.toByteArray(fis);
        } catch ( IOException e ) {
            try {
                FileInputStream fis = new FileInputStream(fileTool.getFileRoot() + "blank.png" );
                return IOUtils.toByteArray(fis);
            } catch( IOException e1 ){}
        }
        return null;
    }
}