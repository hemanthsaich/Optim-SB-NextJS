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
import java.util.zip.ZipOutputStream;

@Service
public class ArchiveService {

    private final EmployeeRepo employeeRepository;
    private final DepartmentRepo departmentRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ArchiveService(EmployeeRepo employeeRepository, DepartmentRepo departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public String createEmployeeArchive(String archiveFileName) throws IOException {
        if (archiveFileName == null || archiveFileName.isEmpty()) {
            throw new IllegalArgumentException("Archive file name must be provided");
        }

        List<EmployeeEntity> employees = employeeRepository.findAll();

        FileOutputStream fos = new FileOutputStream(archiveFileName + ".AF");
        ZipOutputStream zos = new ZipOutputStream(fos);

        addToZip("employees.json", employees, zos);

        zos.close();
        fos.close();

        return archiveFileName;
    }

    public String createDepartmentArchive(String archiveFileName) throws IOException {
        if (archiveFileName == null || archiveFileName.isEmpty()) {
            throw new IllegalArgumentException("Archive file name must be provided");
        }

        List<DepartmentEntity> departments = departmentRepository.findAll();

        FileOutputStream fos = new FileOutputStream(archiveFileName + ".AF");
        ZipOutputStream zos = new ZipOutputStream(fos);

        addToZip("departments.json", departments, zos);

        zos.close();
        fos.close();

        return archiveFileName;
    }

    private <T> void addToZip(String entryName, T data, ZipOutputStream zos) throws IOException {
        ZipEntry entry = new ZipEntry(entryName);
        zos.putNextEntry(entry);
        String jsonData = objectMapper.writeValueAsString(data);
        zos.write(jsonData.getBytes());
        zos.closeEntry();
    }
}
