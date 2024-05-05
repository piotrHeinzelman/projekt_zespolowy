package com.example.shoop.crewControllers;

import com.example.shoop.config.FileTool;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.Random;


@Controller
public class CrewController {

    //@PreAuthorize("hasRole('CREW')")
    @RequestMapping(value = {"/crew"}, method = RequestMethod.GET)
    @ResponseBody
    public String crew() {
        return "Hello Crew!";
    }


    @RequestMapping(value = {"/crew/img/send_file/"}, method = RequestMethod.GET)
    public String crewImgSendGET(){
        return "product/img_add";
    }


    @RequestMapping(value = {"/crew/img/send_file/"}, method = RequestMethod.POST)
    public String crewImgSendPOST( Model model, FileForm form ) throws IOException, NullPointerException {

        System.out.println( form.getSKU() );
        if (form.getUploadedFile() == null || form.getUploadedFile().isEmpty()) {
            model.addAttribute("errorMsg", " niewłaściwy plik :- ( ");
        }

        FileTool fileTool = new FileTool();

        String fileName = form.getUploadedFile().getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
        String PathForXMLFile = fileTool.getFileRoot();
        String random = "" + new Random().nextInt(99);
        String random2 = "" + new Random().nextInt(99);

        File destFile = new File(PathForXMLFile + "img_" + random + "_" + random2 + "." + ext);
        System.out.println( destFile.getCanonicalFile() );
        System.out.println( destFile.getAbsoluteFile() );
        System.out.println( destFile.getAbsolutePath() );

        try (InputStream in = form.getUploadedFile().getInputStream();
             OutputStream out = new FileOutputStream(destFile)) {
            IOUtils.copy(in, out);
            System.out.println(destFile.getCanonicalFile().getCanonicalPath());
        } catch (Exception ex) {
            model.addAttribute("errorMsg", ex);
        }


        return "redirect:";
    }
}