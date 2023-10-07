package com.idb.tasks.serviceImpl;

import com.idb.tasks.dto.SprintDto;
import com.idb.tasks.entity.Sprint;
import com.idb.tasks.exceptions.InvalidOperationException;
import com.idb.tasks.repository.SprintRepository;
import com.idb.tasks.service.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService {

    private final SprintRepository sprintRepository;

    @Override
    public Sprint save(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint update(Sprint sprint) throws Exception {
        if (sprint.hasId()) {
            return save(sprint);
        } else {
            throw new InvalidOperationException("Sprint id required for update operation");
        }
    }

    @Override
    public Page<SprintDto> getByIds(Pageable pageable, Long... ids) {
        if (ids == null || ids.length < 1) {
            return getAll(pageable);
        } else {
            return sprintRepository.getByIds(Arrays.asList(ids), pageable);
        }
    }

    @Override
    public void deleteByIds(Long... ids) {
        sprintRepository.deleteAllByIdInBatch(Arrays.asList(ids));
    }

    @Override
    public Page<SprintDto> getAll(Pageable pageable) {
        return sprintRepository.getByIds(null, pageable);
    }
}
