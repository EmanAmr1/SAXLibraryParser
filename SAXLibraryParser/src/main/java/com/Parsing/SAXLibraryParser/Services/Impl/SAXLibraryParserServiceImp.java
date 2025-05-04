package com.Parsing.SAXLibraryParser.Services.Impl;

import com.Parsing.SAXLibraryParser.Services.SAXLibraryParserService;
import org.springframework.stereotype.Service;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class SAXLibraryParserServiceImp implements SAXLibraryParserService {

    List<File> xmlFiles = new ArrayList<>();


    public void parse(String path) {

        File dir = new File(path);
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.getAbsolutePath().endsWith(".xml")) {
                xmlFiles.add(file);
            }
        }

        try {
            for (File xmlfile : xmlFiles) {
                SAXParserFactory f = SAXParserFactory.newInstance();
                SAXParser p = f.newSAXParser();
                SaxParserHandler h = new SaxParserHandler();
                p.parse(xmlfile, h);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
