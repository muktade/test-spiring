package com.idb.tasks.serviceImpl;

import com.idb.tasks.dto.RoleDto;
import com.idb.tasks.entity.Role;
import com.idb.tasks.exceptions.InvalidOperationException;
import com.idb.tasks.repository.RoleRepository;
import com.idb.tasks.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) throws Exception {
        if (role.hasId()) {
            return save(role);
        } else {
            throw new InvalidOperationException("Role id required for update operation");
        }
    }

    @Override
    public Page<RoleDto> getByIds(Pageable pageable, Long... ids) {
        if (ids == null || ids.length < 1) {
            return getAll(pageable);
        } else {
            return roleRepository.getByIds(Arrays.asList(ids), pageable);
        }
    }

    @Override
    public void deleteByIds(Long... ids) {
        roleRepository.deleteAllByIdInBatch(Arrays.asList(ids));
    }

    @Override
    public Page<RoleDto> getAll(Pageable pageable) {
        return roleRepository.getByIds(null, pageable);
    }
}
