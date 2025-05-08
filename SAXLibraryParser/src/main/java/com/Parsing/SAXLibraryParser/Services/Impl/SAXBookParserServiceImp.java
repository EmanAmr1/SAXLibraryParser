package com.Parsing.SAXLibraryParser.Services.Impl;

import com.Parsing.SAXLibraryParser.Models.Book;
import com.Parsing.SAXLibraryParser.Services.SAXLibraryParserService;
import org.springframework.stereotype.Service;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class SAXBookParserServiceImp implements SAXLibraryParserService {

    List<File> xmlFiles = new ArrayList<>();


    public void parse(String path) {

        //Listing files in a directory
        File dir = new File(path);
        File[] files = dir.listFiles();


        if(files==null){
            System.out.println("No XML files found");
            return;
        }

        // collect all .xml files in a given directory.
        for (File file : files) {
            if (file.getAbsolutePath().toLowerCase().endsWith(".xml")) {
                xmlFiles.add(file);
            }
        }


        try {
            //parses xmlFiles using the SAX parser
            for (File xmlfile : xmlFiles) {
                SAXParserFactory f = SAXParserFactory.newInstance();
                SAXParser p = f.newSAXParser();
                SaxParserBookHandler h = new SaxParserBookHandler();
                p.parse(xmlfile, h);


                //Prints each Book object
                List<Book> books = h.getBookList();
                for (Book book : books) {
                    System.out.println(book);
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing XML file" + e);
        }

    }
}
