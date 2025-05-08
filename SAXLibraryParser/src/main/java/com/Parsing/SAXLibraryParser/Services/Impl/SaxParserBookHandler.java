package com.Parsing.SAXLibraryParser.Services.Impl;

import com.Parsing.SAXLibraryParser.Models.Book;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SaxParserBookHandler extends DefaultHandler {

    Book currentBook = null;
    List<Book> bookList = new ArrayList<>();
    StringBuilder currentValue = new StringBuilder();

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("book")) {
            currentBook = new Book();
        }
        currentValue.setLength(0);
    }


    @Override
    public void characters(char[] ch, int start, int length) {
        currentValue.append(ch, start, length);
    }


    @Override
    public void endElement(String uri, String localName, String qName) {
        if (currentBook != null) {
            switch (qName.toLowerCase()) {
                case "title":
                    currentBook.setTitle(String.valueOf(currentValue));
                    break;
                case "author":
                    currentBook.setAuthor(String.valueOf(currentValue));
                    break;
                case "year":
                    currentBook.setYear(String.valueOf(currentValue));
                    break;
                case "price":
                    currentBook.setPrice(Double.valueOf(String.valueOf(currentValue)));
                    break;
                case "book":
                    bookList.add(currentBook);
                    break;
            }

        }
    }

}
