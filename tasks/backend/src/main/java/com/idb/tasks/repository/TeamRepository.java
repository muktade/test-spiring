package com.idb.tasks.repository;

import com.idb.tasks.dto.TeamDto;
import com.idb.tasks.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT new com.idb.tasks.dto.TeamDto(t) " +
            "FROM Team t " +
            "WHERE COALESCE(:ids, '__') = '__' OR t.id IN(:ids)")
    Page<TeamDto> getByIds(@Param("ids") List<Long> ids, Pageable pageable);
}
