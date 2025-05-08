package com.Parsing.SAXLibraryParser.Services.Impl;

import com.Parsing.SAXLibraryParser.Models.Student;
import com.Parsing.SAXLibraryParser.Services.SAXLibraryParserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class SAXStudentParserServiceImp implements SAXLibraryParserService {

    List<File> xmlFiles = new ArrayList<>();

    @Override
    public void parse(String path) {

        File dir = new File(path);
        File[] files = dir.listFiles();

        if (files == null)
        { System.out.println("No XML files found");
            return;
        }

        for (File file : files) {
            if (file.getAbsolutePath().toLowerCase().contains("student") &&
                    file.getAbsolutePath().toLowerCase().endsWith("xml")) {
                xmlFiles.add(file);
            }}

            try {
                for (File xmlfile : xmlFiles) {
                    SAXParserFactory f = SAXParserFactory.newInstance();
                    SAXParser saxParser = f.newSAXParser();
                    SaxParserStudentHandler h = new SaxParserStudentHandler();
                    saxParser.parse(xmlfile, h);

                    List<Student> allStudents = h.getStudentList();
                    for (Student student : allStudents) {
                        System.out.println(student);
                    }
                }
            }catch (Exception e) {
                System.out.println("Error while parse xml file : "+e);
            }

        }
    }

