package com.idb.tasks.serviceImpl;

import com.idb.tasks.dto.EmployeeDto;
import com.idb.tasks.entity.Employee;
import com.idb.tasks.entity.User;
import com.idb.tasks.exceptions.InvalidOperationException;
import com.idb.tasks.repository.EmployeeRepository;
import com.idb.tasks.repository.UserRepository;
import com.idb.tasks.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    @Override
    public Employee save(Employee employee) {
        User user = employee.getUser();
        if (user != null) {
            if (user.hasId()) {
                boolean userExists = userRepository.existsById(user.getId());
                if (userExists) {
                    return employeeRepository.save(employee);
                }
            } else {
                User newUser = userRepository.save(user);
                employee.setUser(newUser);
                return employeeRepository.save(employee);
            }
        }
        return null;
    }

    @Override
    public Employee update(Employee employee) throws Exception {
        if (employee.hasId()) {
            return save(employee);
        } else {
            throw new InvalidOperationException("Employee id required for update operation");
        }
    }

    @Override
    public Page<EmployeeDto> getByIds(Pageable pageable, Long... ids) {
        if (ids == null || ids.length < 1) {
            return getAll(pageable);
        } else {
            return employeeRepository.getByIds(Arrays.asList(ids), pageable);
        }
    }

    @Override
    public void deleteByIds(Long... ids) {
        ids[0] = 1L;
        employeeRepository.deleteAllByIdInBatch(Arrays.asList(ids));
    }

    @Override
    public Page<EmployeeDto> getAll(Pageable pageable) {
        return employeeRepository.getByIds(null, pageable);
    }
}
