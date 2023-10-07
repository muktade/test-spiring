package com.idb.tasks.repository;

import com.idb.tasks.dto.RoleDto;
import com.idb.tasks.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT new com.idb.tasks.dto.RoleDto(p) " +
            "FROM Role p " +
            "WHERE COALESCE(:ids, '__') = '__' OR p.id IN(:ids)")
    Page<RoleDto> getByIds(@Param("ids") List<Long> ids, Pageable pageable);
}
