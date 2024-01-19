package com.drybro.sportdata.controller.team;

import static com.drybro.sportdata.controller.team.TeamController.TEAM_PATH;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drybro.sportdata.model.Team;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Validated
@RestController
@RequestMapping(path = TEAM_PATH, produces = "application/json")
@AllArgsConstructor
@Slf4j
public class TeamControllerImpl implements TeamController {

	@Override
	@GetMapping()
	public List<Team> getTeams() {
		return null;
	}

	@Override
	@PostMapping()
	public void createTeam( final Team team ) {

	}

	@Override
	@GetMapping(TEAM_ID_PATH)
	public Team getTeam( @RequestParam("teamId") final Long teamId ) {
		return null;
	}

	@Override
	@PutMapping(TEAM_ID_PATH)
	public void updateTeam( @RequestParam("teamId") final Long teamId, @RequestBody final Team team ) {

	}

	@Override
	@DeleteMapping(TEAM_ID_PATH)
	public void deleteTeam( @RequestParam("teamId") final Long teamId ) {

	}
}
