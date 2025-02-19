package employeerecords.employeerecords.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import employeerecords.employeerecords.model.Employee;
import employeerecords.employeerecords.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Employee updateEmployee(Long employeeId, Employee employeeDetails) {
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow();
        existingEmployee.setName(employeeDetails.getName());
        existingEmployee.setAge(employeeDetails.getAge());
        existingEmployee.setDepartment(employeeDetails.getDepartment());
        return employeeRepository.save(existingEmployee);
    }

    public boolean softDeleteEmployee(Long employeeId) {
        Optional<Employee> existingEmployeeOpt = employeeRepository.findById(employeeId);
        if (existingEmployeeOpt.isPresent()) {
            Employee employee = existingEmployeeOpt.get();
            employee.setDeleted(true);
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }

    public List<Employee> getAllEmployees(String search, int page, int size, String sort) {
        String[] sortParams = sort.split(",");
        Sort sorting = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);

        Pageable pageable = PageRequest.of(page, size, sorting);
        Page<Employee> employeePage;

        if (search != null && !search.isEmpty()) {
            employeePage = employeeRepository.findByNameContainingIgnoreCaseAndDeletedFalse(search, pageable);
        } else {
            employeePage = employeeRepository.findByDeletedFalse(pageable);
        }
        
        return employeePage.getContent();
    }
}
