package com.Parsing.SAXLibraryParser.Services.Impl;

import com.Parsing.SAXLibraryParser.Services.LibraryService;
import com.Parsing.SAXLibraryParser.Services.SAXLibraryParserService;
import org.springframework.stereotype.Service;


@Service
public class LibraryServiceImpl implements LibraryService {

private final SAXLibraryParserService saxLibraryParserService;
    public LibraryServiceImpl(SAXLibraryParserService saxLibraryParserService) {
        this.saxLibraryParserService = saxLibraryParserService;
    }

    @Override
    public void doParsing(String path) {

         saxLibraryParserService.parse(path);

    }
}
