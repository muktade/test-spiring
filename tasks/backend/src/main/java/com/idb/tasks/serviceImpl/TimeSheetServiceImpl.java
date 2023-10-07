package com.idb.tasks.serviceImpl;

import com.idb.tasks.dto.TimeSheetDto;
import com.idb.tasks.entity.TimeSheet;
import com.idb.tasks.exceptions.InvalidOperationException;
import com.idb.tasks.repository.TimeSheetRepository;
import com.idb.tasks.service.TimeSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class TimeSheetServiceImpl implements TimeSheetService {

    private final TimeSheetRepository timeSheetRepository;

    @Override
    public TimeSheet save(TimeSheet timeSheet) {
        return timeSheetRepository.save(timeSheet);
    }

    @Override
    public TimeSheet update(TimeSheet timeSheet) throws Exception {
        if (timeSheet.hasId()) {
            return save(timeSheet);
        } else {
            throw new InvalidOperationException("TimeSheet id required for update operation");
        }
    }

    @Override
    public Page<TimeSheetDto> getByIds(Pageable pageable, Long... ids) {
        if (ids == null || ids.length < 1) {
            return getAll(pageable);
        } else {
            return timeSheetRepository.getByIds(Arrays.asList(ids), pageable);
        }
    }

    @Override
    public void deleteByIds(Long... ids) {
        timeSheetRepository.deleteAllByIdInBatch(Arrays.asList(ids));
    }

    @Override
    public Page<TimeSheetDto> getAll(Pageable pageable) {
        return timeSheetRepository.getByIds(null, pageable);
    }
}
