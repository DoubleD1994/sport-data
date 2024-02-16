package com.drybro.sportdata.controller.team;

import static com.drybro.sportdata.controller.team.TeamController.TEAM_PATH;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.drybro.sportdata.model.Team;
import com.drybro.sportdata.model.constants.Sport;
import com.drybro.sportdata.repository.TeamRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Validated
@RestController
@RequestMapping(path = TEAM_PATH, produces = "application/json")
@AllArgsConstructor
@Slf4j
public class TeamControllerImpl implements TeamController {

	private final TeamRepository teamRepository;

	@Override
	@GetMapping()
	public List<Team> getTeams() {
		final List<Team> teams = new ArrayList<>();
		teamRepository.findAll().forEach( teams::add );
		return teams;
	}

	@Override
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void createTeam( @RequestBody final Team team ) {
		teamRepository.save( team );
		log.info( "TEAM CREATED: {}", team );
	}

	@Override
	@GetMapping(TEAM_ID_PATH)
	public Team getTeamById( @PathVariable("teamId") final Long teamId ) {
		return findTeamById( teamId );
	}

	@Override
	@PutMapping(TEAM_ID_PATH)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateTeam( @PathVariable("teamId") final Long teamId,
			@RequestBody final Team updatedTeam ) {
		final Team team = findTeamById( teamId );

		if ( !updatedTeam.getTeamName().isBlank() ) {
			team.setTeamName( updatedTeam.getTeamName() );
		}
		if ( updatedTeam.getSport().describeConstable().isPresent() ) {
			team.setSport( updatedTeam.getSport() );
		}
		if ( updatedTeam.getLogoPath() != null && !updatedTeam.getLogoPath().isBlank() ) {
			team.setLogoPath( updatedTeam.getLogoPath() );
		}

		teamRepository.save( team );
		log.info( "TEAM WITH ID {} UPDATED", teamId );
	}

	@Override
	@DeleteMapping(TEAM_ID_PATH)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTeam( @PathVariable("teamId") final Long teamId ) {
		teamRepository.deleteById( teamId );
		log.info( "TEAM WITH ID {} DELETED", teamId );
	}

	@Override
	public List<Team> getTeamsBySport( final Sport sport ) {
		return teamRepository.findTeamsBySport( sport );
	}

	private Team findTeamById( final Long teamId ) {
		try {
			return teamRepository.findById( teamId ).orElseThrow();
		} catch ( final NoSuchElementException nsee ) {
			throw new ResponseStatusException( HttpStatus.NOT_FOUND,
					"Team with ID " + teamId + "  not found", nsee );
		}
	}
}
