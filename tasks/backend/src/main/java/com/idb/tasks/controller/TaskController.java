package com.idb.tasks.controller;

import com.idb.tasks.dto.TaskDto;
import com.idb.tasks.entity.Task;
import com.idb.tasks.exceptions.ResourceNotFoundException;
import com.idb.tasks.service.TaskService;
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
@RequestMapping("/task/")
@RequiredArgsConstructor
public class TaskController implements BaseController<Task, Long> {

    private final TaskService taskService;

    @Override
    public ResponseEntity<Task> save(@RequestBody Task task) {
        taskService.save(task);
        return ResponseEntity.ok(task);
    }

    @Override
    public ResponseEntity<String> update(@RequestBody Task task) {
        try {
            taskService.update(task);
            return ResponseEntity.ok("Successfully updated task");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<TaskDto> getById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Pageable pageable = Pageable.ofSize(1);
        Page<TaskDto> tasks = taskService.getByIds(pageable, id);
        TaskDto taskDto = tasks.stream().findFirst().orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(taskDto);
    }

    @Override
    public ResponseEntity<Page<TaskDto>> getByIds(@PathVariable("ids") Long... ids) {
        Page<TaskDto> taskList = taskService.getByIds(Pageable.ofSize(10), ids);
        return ResponseEntity.ok(taskList);
    }

    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) {
        taskService.deleteByIds(ids);
        return ResponseEntity.ok("Tasks deleted successfully");
    }

    @Override
    public ResponseEntity<Page<TaskDto>> getAll(
            @PathVariable(value = "pageNumber", required = false) Integer pageNumber,
            @PathVariable(value = "pageSize", required = false) Integer pageSize,
            @PathVariable(value = "sortDirection", required = false) String sortDirection
    ) {
        Pageable pageable = PageUtils.getPageable(pageNumber, pageSize, sortDirection, "id");
        Page<TaskDto> page = taskService.getAll(pageable);
        return ResponseEntity.ok(page);
    }
}
