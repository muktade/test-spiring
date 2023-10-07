package com.idb.tasks.serviceImpl;

import com.idb.tasks.dto.TeamDto;
import com.idb.tasks.entity.Team;
import com.idb.tasks.exceptions.InvalidOperationException;
import com.idb.tasks.repository.TeamRepository;
import com.idb.tasks.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team update(Team team) throws Exception {
        if (team.hasId()) {
            return save(team);
        } else {
            throw new InvalidOperationException("Team id required for update operation");
        }
    }

    @Override
    public Page<TeamDto> getByIds(Pageable pageable, Long... ids) {
        if (ids == null || ids.length < 1) {
            return getAll(pageable);
        } else {
            return teamRepository.getByIds(Arrays.asList(ids), pageable);
        }
    }

    @Override
    public void deleteByIds(Long... ids) {
        teamRepository.deleteAllByIdInBatch(Arrays.asList(ids));
    }

    @Override
    public Page<TeamDto> getAll(Pageable pageable) {
        return teamRepository.getByIds(null, pageable);
    }
}
