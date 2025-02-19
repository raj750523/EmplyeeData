package employeerecords.employeerecords.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import employeerecords.employeerecords.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Search by name (case-insensitive)
    Page<Employee> findByNameContainingIgnoreCaseAndDeletedFalse(String name, Pageable pageable);

    // Get all non-deleted employees with pagination
    Page<Employee> findByDeletedFalse(Pageable pageable);
}
