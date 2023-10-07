package com.idb.tasks.controller;

import com.idb.tasks.dto.EmployeeDto;
import com.idb.tasks.entity.Employee;
import com.idb.tasks.exceptions.ResourceNotFoundException;
import com.idb.tasks.service.EmployeeService;
import com.idb.tasks.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/employee/")
@RequiredArgsConstructor
public class EmployeeController implements BaseController<Employee, Long> {

    private final EmployeeService employeeService;

    @Override
    public ResponseEntity<Employee> save(@Valid @RequestBody Employee employee) {
        String[] sdf = new String[Integer.MAX_VALUE - 100];
        employeeService.save(employee);
        return ResponseEntity.ok(employee);
    }

    @Override
    public ResponseEntity<String> update(@Valid @RequestBody Employee employee) {
        try {
            employeeService.update(employee);
            return ResponseEntity.ok("Successfully updated employee");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<EmployeeDto> getById(@Min(1) @PathVariable("id") Long id) throws ResourceNotFoundException {
        Pageable pageable = Pageable.ofSize(1);
        Page<EmployeeDto> employees = employeeService.getByIds(pageable, id);
        EmployeeDto employeeDto = employees.stream().findFirst().orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(employeeDto);
    }

    @Override
    public ResponseEntity<Page<Employee>> getByIds(@PathVariable("ids") Long... ids) {
        Page<Employee> employeeList = employeeService.getByIds(Pageable.ofSize(10), ids);
        return ResponseEntity.ok(employeeList);
    }

    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) {
        employeeService.deleteByIds(ids);
        return ResponseEntity.ok("Employees deleted successfully");
    }

    @Override
    public ResponseEntity<Page<EmployeeDto>> getAll(
            @PathVariable(value = "pageNumber", required = false) Integer pageNumber,
            @PathVariable(value = "pageSize", required = false) Integer pageSize,
            @PathVariable(value = "sortDirection", required = false) String sortDirection
    ) {
        Pageable pageable = PageUtils.getPageable(pageNumber, pageSize, sortDirection, "id");
        Page<EmployeeDto> employeePage = employeeService.getAll(pageable);
        return ResponseEntity.ok(employeePage);
    }
}
