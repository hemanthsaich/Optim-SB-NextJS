package com.estuate.archiver.service;

import com.estuate.archiver.entity.DepartmentEntity;
import com.estuate.archiver.entity.EmployeeEntity;
import com.estuate.archiver.repo.DepartmentRepo;
import com.estuate.archiver.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    public EmployeeEntity saveEmployee(EmployeeEntity employee){
        return employeeRepo.save(employee);
    }

    public List<EmployeeEntity> getAllEmployees(){
        return employeeRepo.findAll();
    }

    public void deleteEmployee(Long employeeId){
        employeeRepo.deleteById(employeeId);
    }

    public EmployeeEntity getEmpById(Long empId){
        return employeeRepo.findById(empId).get();
    }

    public DepartmentEntity saveDept(DepartmentEntity department){
        return departmentRepo.save(department);
    }

    public List<DepartmentEntity> getDepts(){
        return departmentRepo.findAll();
    }

    public void deleteDept(Long deptId){
        departmentRepo.deleteById(deptId);
    }

    public DepartmentEntity getDeptById(Long deptId){
        return departmentRepo.findById(deptId).get();
    }


}
