package com.idb.tasks.serviceImpl;

import com.idb.tasks.dto.ProjectDto;
import com.idb.tasks.entity.Project;
import com.idb.tasks.exceptions.InvalidOperationException;
import com.idb.tasks.repository.ProjectRepository;
import com.idb.tasks.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project update(Project project) throws Exception {
        if (project.hasId()) {
            return save(project);
        } else {
            throw new InvalidOperationException("Project id required for update operation");
        }
    }

    @Override
    public Page<ProjectDto> getByIds(Pageable pageable, Long... ids) {
        if (ids == null || ids.length < 1) {
            return getAll(pageable);
        } else {
            return projectRepository.getByIds(Arrays.asList(ids), pageable);
        }
    }

    @Override
    public void deleteByIds(Long... ids) {
        projectRepository.deleteAllByIdInBatch(Arrays.asList(ids));
    }

    @Override
    public Page<ProjectDto> getAll(Pageable pageable) {
        return projectRepository.getByIds(null, pageable);
    }
}
