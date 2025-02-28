package com.estuate.archiver.controller;

import com.estuate.archiver.service.ArchiveReadService;
import com.estuate.archiver.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/archive")
@CrossOrigin(origins = "http://localhost:3000")
public class ArchiveController {

    private final ArchiveService archiveService;
    private final ArchiveReadService archiveReadService;

    @Autowired
    public ArchiveController(ArchiveService archiveService, ArchiveReadService archiveReadService) {
        this.archiveService = archiveService;
        this.archiveReadService = archiveReadService;
    }

    @GetMapping("/create-employee-archive")
    public String createEmployeeArchive(@RequestParam String archiveFileName) {
        try {
            return archiveService.createEmployeeArchive(archiveFileName);
        } catch (IOException e) {
            return "Error creating employee archive: " + e.getMessage();
        }
    }

    @GetMapping("/create-department-archive")
    public String createDepartmentArchive(@RequestParam String archiveFileName) {
        try {
            return archiveService.createDepartmentArchive(archiveFileName);
        } catch (IOException e) {
            return "Error creating department archive: " + e.getMessage();
        }
    }

    @GetMapping("/read-employee-archive")
    public String readEmployeeArchive(@RequestParam String archiveFilePath) {
        try {
            String jsonResponse = archiveReadService.readEmployeeArchive(archiveFilePath);
            return jsonResponse;
        } catch (IOException e) {
            return "Error reading employee archive: " + e.getMessage();
        }
    }

    @GetMapping("/read-department-archive")
    public String readDepartmentArchive(@RequestParam String archiveFilePath) {
        try {
            String jsonResponse = archiveReadService.readDepartmentArchive(archiveFilePath);
            return jsonResponse;
        } catch (IOException e) {
            return "Error reading department archive: " + e.getMessage();
        }
    }
}
