package com.idb.tasks.controller;

import com.idb.tasks.dto.RoleDto;
import com.idb.tasks.entity.Role;
import com.idb.tasks.exceptions.ResourceNotFoundException;
import com.idb.tasks.service.RoleService;
import com.idb.tasks.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role/")
@RequiredArgsConstructor
public class RoleController implements BaseController<Role, Long> {

    private final RoleService roleService;

    @Override
    public ResponseEntity<Role> save(@RequestBody Role role) {
        roleService.save(role);
        return ResponseEntity.ok(role);
    }

    @Override
    public ResponseEntity<String> update(@RequestBody Role role) {
        try {
            roleService.update(role);
            return ResponseEntity.ok("Successfully updated role");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<RoleDto> getById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Pageable pageable = Pageable.ofSize(1);
        Page<RoleDto> roles = roleService.getByIds(pageable, id);
        RoleDto roleDto = roles.stream().findFirst().orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(roleDto);
    }

    @Override
    public ResponseEntity<Page<Role>> getByIds(@PathVariable("ids") Long... ids) {
        Page<Role> roleList = roleService.getByIds(Pageable.ofSize(10), ids);
        return ResponseEntity.ok(roleList);
    }

    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) {
        roleService.deleteByIds(ids);
        return ResponseEntity.ok("Roles deleted successfully");
    }

    @Override
    public ResponseEntity<Page<Role>> getAll(
            @PathVariable(value = "pageNumber", required = false) Integer pageNumber,
            @PathVariable(value = "pageSize", required = false) Integer pageSize,
            @PathVariable(value = "sortDirection", required = false) String sortDirection
    ) {
        Pageable pageable = PageUtils.getPageable(pageNumber, pageSize, sortDirection, "id");
        Page<Role> page = roleService.getAll(pageable);
        return ResponseEntity.ok(page);
    }
}
