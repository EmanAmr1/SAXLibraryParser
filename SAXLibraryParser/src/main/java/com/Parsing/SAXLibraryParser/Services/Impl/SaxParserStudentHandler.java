package com.Parsing.SAXLibraryParser.Services.Impl;

import com.Parsing.SAXLibraryParser.Models.Student;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SaxParserStudentHandler extends DefaultHandler {

    boolean inStudent = false;
    boolean inName = false;
    boolean inAge = false;
    boolean inMajor = false;


    Student currentStudent = null;
    List<Student> studentList = new ArrayList<>();
    private StringBuilder currentValue = new StringBuilder();

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("Student")) {
            inStudent = true;
            currentStudent = new Student();
            currentStudent.setId(attributes.getValue("id"));
            System.out.println(currentStudent.getId());
        } else if (qName.equalsIgnoreCase("Name")) {
            inName = true;
        } else if (qName.equalsIgnoreCase("Age")) {
            inAge = true;
        } else if (qName.equalsIgnoreCase("Major")) {
            inMajor = true;
        }

        currentValue.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        currentValue.append(ch, start, length);
    }


    @Override
    public void endElement(String uri, String localName, String qName) {
        if (currentStudent != null) {
            if (qName.equalsIgnoreCase("Name")) {
                currentStudent.setName(currentValue.toString());
                inName = false;
            } else if (qName.equalsIgnoreCase("Age")) {
                currentStudent.setAge(Integer.parseInt(currentValue.toString()));
                inAge = false;
            } else if (qName.equalsIgnoreCase("Major")) {
                currentStudent.setMajor(currentValue.toString());
                inMajor = false;
            }
            if (qName.equalsIgnoreCase("Student")) {
                studentList.add(currentStudent);
                currentStudent=null;
            }
        }
    }
}
