package com.estuate.archiver.service;

import com.estuate.archiver.entity.DepartmentEntity;
import com.estuate.archiver.entity.EmployeeEntity;
import com.estuate.archiver.repo.DepartmentRepo;
import com.estuate.archiver.repo.EmployeeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class ArchiveReadService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final EmployeeRepo employeeRepository;
    private final DepartmentRepo departmentRepository;

    public ArchiveReadService(EmployeeRepo employeeRepository, DepartmentRepo departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public String readEmployeeArchive(String archiveFilePath) throws IOException {
        return readArchive(archiveFilePath, "employees.json", EmployeeEntity.class);
    }

    public String readDepartmentArchive(String archiveFilePath) throws IOException {
        return readArchive(archiveFilePath, "departments.json", DepartmentEntity.class);
    }

    private <T> String readArchive(String archiveFilePath, String entryName, Class<T> clazz) throws IOException {
        File archiveFile = new File(archiveFilePath);
        if (!archiveFile.exists()) {
            throw new FileNotFoundException("Archive file not found: " + archiveFilePath);
        }

        FileInputStream fis = new FileInputStream(archiveFile);
        ZipInputStream zis = new ZipInputStream(fis);
        ZipEntry entry;
        boolean entryFound = false;
        String jsonData = null;

        while ((entry = zis.getNextEntry()) != null) {
            if (entry.getName().equals(entryName)) {
                entryFound = true;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    baos.write(buffer, 0, len);
                }

                jsonData = baos.toString();
                break;
            }
        }

        if (!entryFound) {
            throw new IOException("Entry " + entryName + " not found in the archive.");
        }

        zis.close();
        fis.close();

        return jsonData;
    }
}
