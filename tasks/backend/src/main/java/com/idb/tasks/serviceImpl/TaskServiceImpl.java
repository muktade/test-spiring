package com.idb.tasks.serviceImpl;

import com.idb.tasks.dto.TaskDto;
import com.idb.tasks.entity.Task;
import com.idb.tasks.exceptions.InvalidOperationException;
import com.idb.tasks.repository.TaskRepository;
import com.idb.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) throws Exception {
        if (task.hasId()) {
            return save(task);
        } else {
            throw new InvalidOperationException("Task id required for update operation");
        }
    }

    @Override
    public Page<TaskDto> getByIds(Pageable pageable, Long... ids) {
        if (ids == null || ids.length < 1) {
            return getAll(pageable);
        } else {
            return taskRepository.getByIds(Arrays.asList(ids), pageable);
        }
    }

    @Override
    public void deleteByIds(Long... ids) {
        taskRepository.deleteAllByIdInBatch(Arrays.asList(ids));
    }

    @Override
    public Page<TaskDto> getAll(Pageable pageable) {
        return taskRepository.getByIds(null, pageable);
    }
}
