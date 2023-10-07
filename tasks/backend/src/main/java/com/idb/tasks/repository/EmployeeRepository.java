package com.idb.tasks.repository;

import com.idb.tasks.dto.EmployeeDto;
import com.idb.tasks.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT new com.idb.tasks.dto.EmployeeDto(e) " +
            "FROM Employee e " +
            "WHERE COALESCE(:ids, '__') = '__' OR e.id IN(:ids)")
    Page<EmployeeDto> getByIds(@Param("ids") List<Long> ids, Pageable pageable);

}
