package com.estuate.archiver.controller;

import com.estuate.archiver.entity.DepartmentEntity;
import com.estuate.archiver.entity.EmployeeEntity;
import com.estuate.archiver.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/table")
@CrossOrigin(origins = "http://localhost:3000")
public class TableController {

    @Autowired
    private TableService tableService;

    @PostMapping("/saveEmployee")
    public ResponseEntity<EmployeeEntity> addEmployee(@RequestBody EmployeeEntity employee){
        EmployeeEntity saveEmp = tableService.saveEmployee(employee);
        return ResponseEntity.ok(saveEmp);
    }

    @GetMapping("/allEmployees")
    public ResponseEntity<List<EmployeeEntity>> getAllEmps(){
        List<EmployeeEntity> emps = tableService.getAllEmployees();
        return  ResponseEntity.ok(emps);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<?> deleteEmp(@PathVariable Long empId){
        tableService.deleteEmployee(empId);
        return ResponseEntity.ok("Employee Deleted Successfully!!");
    }

    @PostMapping("/saveDept")
    public ResponseEntity<DepartmentEntity> addDept(@RequestBody DepartmentEntity department){
        DepartmentEntity deptName = tableService.saveDept(department);
        return ResponseEntity.ok(deptName);
    }

    @GetMapping("/allDepts")
    public ResponseEntity<List<DepartmentEntity>> getAllDepts(){
        List<DepartmentEntity> depts = tableService.getDepts();
        return  ResponseEntity.ok(depts);
    }

    @DeleteMapping("/deleteDept/{deptId}")
    public ResponseEntity<?> deleteDept(@PathVariable Long deptId){
        tableService.deleteDept(deptId);
        return ResponseEntity.ok("Department Deleted Successfully!!");
    }




}
