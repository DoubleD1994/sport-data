package com.drybro.sportdata.controller.team;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.drybro.sportdata.model.Team;
import com.drybro.sportdata.model.constants.Sport;

public interface TeamController {

	String TEAM_PATH = "/team";

	String TEAM_ID_PATH = "/{teamId}";

	String TEAMS_BY_SPORT = "/sport";

	@GetMapping()
	List<Team> getTeams();

	@PostMapping()
	void createTeam(@RequestBody final Team team);

	@GetMapping(TEAM_ID_PATH)
	Team getTeamById(@PathVariable final Long teamId);

	@PutMapping(TEAM_ID_PATH)
	void updateTeam(@PathVariable final Long teamId, @RequestBody final Team team);

	@DeleteMapping(TEAM_ID_PATH)
	void deleteTeam(@PathVariable final Long teamId);

	@GetMapping(TEAMS_BY_SPORT)
	List<Team> getTeamsBySport(@RequestParam final Sport sport );

}
