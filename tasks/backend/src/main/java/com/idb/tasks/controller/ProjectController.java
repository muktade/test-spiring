package com.idb.tasks.controller;

import com.idb.tasks.dto.ProjectDto;
import com.idb.tasks.entity.Project;
import com.idb.tasks.exceptions.ResourceNotFoundException;
import com.idb.tasks.service.ProjectService;
import com.idb.tasks.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project/")
@RequiredArgsConstructor
@Validated
public class ProjectController implements BaseController<Project, Long> {

    private final ProjectService projectService;

    @Override
    public ResponseEntity<Project> save(@RequestBody Project project) {
        projectService.save(project);
        return ResponseEntity.ok(project);
    }

    @Override
    public ResponseEntity<String> update(@RequestBody Project project) {
        try {
            projectService.update(project);
            return ResponseEntity.ok("Successfully updated project");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ProjectDto> getById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Pageable pageable = Pageable.ofSize(1);
        Page<ProjectDto> projects = projectService.getByIds(pageable, id);
        ProjectDto projectDto = projects.stream().findFirst().orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(projectDto);
    }

    @Override
    public ResponseEntity<Page<ProjectDto>> getByIds(@PathVariable("ids") Long... ids) {
        Page<ProjectDto> projectList = projectService.getByIds(Pageable.ofSize(10), ids);
        return ResponseEntity.ok(projectList);
    }

    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) {
        projectService.deleteByIds(ids);
        return ResponseEntity.ok("Projects deleted successfully");
    }

    @Override
    public ResponseEntity<Page<ProjectDto>> getAll(
            @PathVariable(value = "pageNumber", required = false) Integer pageNumber,
            @PathVariable(value = "pageSize", required = false) Integer pageSize,
            @PathVariable(value = "sortDirection", required = false) String sortDirection
    ) {
        Pageable pageable = PageUtils.getPageable(pageNumber, pageSize, sortDirection, "id");
        Page<ProjectDto> page = projectService.getAll(pageable);
        return ResponseEntity.ok(page);
    }
}
