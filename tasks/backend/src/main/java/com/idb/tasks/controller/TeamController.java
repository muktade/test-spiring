package com.idb.tasks.controller;

import com.idb.tasks.dto.TeamDto;
import com.idb.tasks.entity.Team;
import com.idb.tasks.exceptions.ResourceNotFoundException;
import com.idb.tasks.service.TeamService;
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
@RequestMapping("/team/")
@RequiredArgsConstructor
public class TeamController implements BaseController<Team, Long> {

    private final TeamService teamService;

    @Override
    public ResponseEntity<Team> save(@RequestBody Team team) {
        teamService.save(team);
        return ResponseEntity.ok(team);
    }

    @Override
    public ResponseEntity<String> update(@RequestBody Team team) {
        try {
            teamService.update(team);
            return ResponseEntity.ok("Successfully updated team");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<TeamDto> getById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Pageable pageable = Pageable.ofSize(1);
        Page<TeamDto> teams = teamService.getByIds(pageable, id);
        TeamDto teamDto = teams.stream().findFirst().orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(teamDto);
    }

    @Override
    public ResponseEntity<Page<TeamDto>> getByIds(@PathVariable("ids") Long... ids) {
        Page<TeamDto> teamList = teamService.getByIds(Pageable.ofSize(10), ids);
        return ResponseEntity.ok(teamList);
    }

    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) {
        teamService.deleteByIds(ids);
        return ResponseEntity.ok("Teams deleted successfully");
    }

    @Override
    public ResponseEntity<Page<TeamDto>> getAll(
            @PathVariable(value = "pageNumber", required = false) Integer pageNumber,
            @PathVariable(value = "pageSize", required = false) Integer pageSize,
            @PathVariable(value = "sortDirection", required = false) String sortDirection
    ) {
        Pageable pageable = PageUtils.getPageable(pageNumber, pageSize, sortDirection, "id");
        Page<TeamDto> page = teamService.getAll(pageable);
        return ResponseEntity.ok(page);
    }
}
