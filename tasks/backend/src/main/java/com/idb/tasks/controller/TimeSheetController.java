package com.idb.tasks.controller;

import com.idb.tasks.dto.TimeSheetDto;
import com.idb.tasks.entity.TimeSheet;
import com.idb.tasks.exceptions.ResourceNotFoundException;
import com.idb.tasks.service.TimeSheetService;
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
@RequestMapping("/time-sheet/")
@RequiredArgsConstructor
public class TimeSheetController implements BaseController<TimeSheet, Long> {

    private final TimeSheetService timeSheetService;

    @Override
    public ResponseEntity<TimeSheet> save(@RequestBody TimeSheet timeSheet) {
        timeSheetService.save(timeSheet);
        return ResponseEntity.ok(timeSheet);
    }

    @Override
    public ResponseEntity<String> update(@RequestBody TimeSheet timeSheet) {
        try {
            timeSheetService.update(timeSheet);
            return ResponseEntity.ok("Successfully updated timeSheet");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<TimeSheetDto> getById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Pageable pageable = Pageable.ofSize(1);
        Page<TimeSheetDto> timeSheets = timeSheetService.getByIds(pageable, id);
        TimeSheetDto timeSheetDto = timeSheets.stream().findFirst().orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(timeSheetDto);
    }

    @Override
    public ResponseEntity<Page<TimeSheetDto>> getByIds(@PathVariable("ids") Long... ids) {
        Page<TimeSheetDto> timeSheetList = timeSheetService.getByIds(Pageable.ofSize(10), ids);
        return ResponseEntity.ok(timeSheetList);
    }

    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) {
        timeSheetService.deleteByIds(ids);
        return ResponseEntity.ok("TimeSheets deleted successfully");
    }

    @Override
    public ResponseEntity<Page<TimeSheetDto>> getAll(
            @PathVariable(value = "pageNumber", required = false) Integer pageNumber,
            @PathVariable(value = "pageSize", required = false) Integer pageSize,
            @PathVariable(value = "sortDirection", required = false) String sortDirection
    ) {
        Pageable pageable = PageUtils.getPageable(pageNumber, pageSize, sortDirection, "id");
        Page<TimeSheetDto> page = timeSheetService.getAll(pageable);
        return ResponseEntity.ok(page);
    }
}
