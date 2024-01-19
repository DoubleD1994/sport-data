package com.drybro.sportdata.controller.team;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.drybro.sportdata.model.Team;

public interface TeamController {

	String TEAM_PATH = "/team";

	String TEAM_ID_PATH = "/{teamId}";

	@GetMapping()
	List<Team> getTeams();

	@PostMapping()
	void createTeam(final Team team);

	@GetMapping(TEAM_ID_PATH)
	Team getTeam(@PathVariable final Long teamId);

	@PutMapping(TEAM_ID_PATH)
	void updateTeam(@PathVariable final Long teamId, @RequestBody final Team team);

	@DeleteMapping(TEAM_ID_PATH)
	void deleteTeam(@PathVariable final Long teamId);

}
