package com.drybro.sportdata.controller.tournamentteam;

import static com.drybro.sportdata.controller.tournamentteam.TournamentTeamController.TOURNAMENT_TEAMS_PATH;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drybro.sportdata.model.Team;
import com.drybro.sportdata.model.Tournament;
import com.drybro.sportdata.model.TournamentTeam;
import com.drybro.sportdata.model.constants.Round;
import com.drybro.sportdata.repository.TournamentTeamRepository;
import com.drybro.sportdata.service.TournamentTeamService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Validated
@RestController
@RequestMapping(path = TOURNAMENT_TEAMS_PATH, produces = "application/json")
@AllArgsConstructor
@Slf4j
public class TournamentTeamControllerImpl implements TournamentTeamController {

	private final TournamentTeamRepository tournamentTeamRepository;

	private final TournamentTeamService tournamentTeamService;

	@Override
	@GetMapping()
	public List<Team> getTournamentTeams( @RequestParam final Long tournamentId ) {
		final List<Team> tournamentTeamList = new ArrayList<>();
		tournamentTeamRepository.findTournamentTeamByTournamentId( tournamentId )
				.forEach( tournamentTeam -> tournamentTeamList.add( tournamentTeam.getTeam() ) );
		return tournamentTeamList;
	}

	@Override
	@PostMapping()
	public void addTeamsToTournament( @RequestParam final Long tournamentId,
			@RequestBody final List<Team> teams ) {
		tournamentTeamService.addTeamsToTournament( tournamentId, teams );
	}

	@Override
	@GetMapping(TOURNAMENT_TEAM_STANDING)
	public List<TournamentTeam> getTournamentStandings( @PathVariable final Long tournamentId ) {
		return null;
	}

	@Override
	@GetMapping(TOURNAMENT_TEAM_IS_ELIMINATED_PATH)
	public List<TournamentTeam> getTournamentTeamsByStatus( @PathVariable final Long tournamentId,
			@RequestParam final Boolean isEliminated ) {
		return null;
	}

	@Override
	@GetMapping(TOURNAMENT_TEAM_BY_ROUND_PATH)
	public List<TournamentTeam> getTournamentTeamByRound( @PathVariable final Long tournamentId,
			@RequestParam final Round round ) {
		return null;
	}

	@Override
	@PutMapping(TOURNAMENT_TEAM_BY_GROUP_PATH)
	public List<TournamentTeam> addTournamentTeamToGroup( @PathVariable final Long tournamentId,
			@RequestParam final Long teamId, @RequestParam final String groupName ) {
		return null;
	}

	@Override
	@GetMapping(TOURNAMENT_TEAM_BY_GROUP_PATH)
	public List<TournamentTeam> getTournamentTeamByGroup( @PathVariable final Long tournamentId,
			@RequestParam final String group ) {
		return null;
	}

}
