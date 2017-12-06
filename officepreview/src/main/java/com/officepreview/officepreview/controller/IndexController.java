package com.officepreview.officepreview.controller;

import com.officepreview.officepreview.util.Doc2HtmlUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;

@Controller
public class IndexController {

    @RequestMapping("index")
    public void index() {
    }

    @RequestMapping("upload")
    public String upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, ModelMap modelMap) throws Exception {
        String path = IndexController.class.getClassLoader().getResource("").getPath() + "static/swf/";
        File folder = new File(path);
        if (!folder.exists()) folder.mkdir();
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        String fileName = System.currentTimeMillis() + "";
        File destF = new File(path + fileName + suffix);
        file.transferTo(destF);

        Doc2HtmlUtil coc2HtmlUtil = Doc2HtmlUtil.getDoc2HtmlUtilInstance();
        FileInputStream fileInputStream = new FileInputStream(destF);
        String swfFileName = coc2HtmlUtil.file2pdf(fileInputStream, path, suffix);
        Runtime.getRuntime().exec("D:/swftools/pdf2swf.exe " + folder.getPath() + File.separator + swfFileName + " -o " + folder.getPath() + File.separator + fileName + ".swf -T 9");

        modelMap.put("swfFilePath", fileName + ".swf");
        return "preview";
    }
}
