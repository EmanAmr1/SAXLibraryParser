package com.Parsing.SAXLibraryParser.Controller;

import com.Parsing.SAXLibraryParser.Services.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class run {

    private final LibraryService libraryService;

    public run(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/import")

    public ResponseEntity<String> load(@RequestParam String path)
    {
        try {
            //String path = "D:/eman/project/SAXLibraryParser_Project/datasource";
            libraryService.doParsing(path);
            return ResponseEntity.ok("imported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import books: " + e.getMessage());
        }
    }
}