package com.idb.tasks.controller;

import com.idb.tasks.dto.SprintDto;
import com.idb.tasks.entity.Sprint;
import com.idb.tasks.exceptions.ResourceNotFoundException;
import com.idb.tasks.service.SprintService;
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
@RequestMapping("/sprint/")
@RequiredArgsConstructor
public class SprintController implements BaseController<Sprint, Long> {

    private final SprintService sprintService;

    @Override
    public ResponseEntity<Sprint> save(@RequestBody Sprint sprint) {
        sprintService.save(sprint);
        return ResponseEntity.ok(sprint);
    }

    @Override
    public ResponseEntity<String> update(@RequestBody Sprint sprint) {
        try {
            sprintService.update(sprint);
            return ResponseEntity.ok("Successfully updated sprint");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<SprintDto> getById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Pageable pageable = Pageable.ofSize(1);
        Page<SprintDto> sprints = sprintService.getByIds(pageable, id);
        SprintDto sprintDto = sprints.stream().findFirst().orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(sprintDto);
    }

    @Override
    public ResponseEntity<Page<Sprint>> getByIds(@PathVariable("ids") Long... ids) {
        Page<Sprint> sprintList = sprintService.getByIds(Pageable.ofSize(10), ids);
        return ResponseEntity.ok(sprintList);
    }

    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) {
        sprintService.deleteByIds(ids);
        return ResponseEntity.ok("Sprints deleted successfully");
    }

    @Override
    public ResponseEntity<Page<Sprint>> getAll(
            @PathVariable(value = "pageNumber", required = false) Integer pageNumber,
            @PathVariable(value = "pageSize", required = false) Integer pageSize,
            @PathVariable(value = "sortDirection", required = false) String sortDirection
    ) {
        Pageable pageable = PageUtils.getPageable(pageNumber, pageSize, sortDirection, "id");
        Page<Sprint> page = sprintService.getAll(pageable);
        return ResponseEntity.ok(page);
    }
}
