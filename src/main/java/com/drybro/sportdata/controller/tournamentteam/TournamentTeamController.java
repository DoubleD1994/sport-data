package com.drybro.sportdata.controller.tournamentteam;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.drybro.sportdata.model.Team;
import com.drybro.sportdata.model.TournamentTeam;
import com.drybro.sportdata.model.constants.Round;

public interface TournamentTeamController {

	String TOURNAMENT_TEAMS_PATH = "/tournament-team";

	String TOURNAMENT_TEAM_STANDING = "/{tournamentId}/standing";

	String TOURNAMENT_TEAM_BY_ROUND_PATH = "/{tournamentId}/round";

	String TOURNAMENT_TEAM_BY_GROUP_PATH = "/group";

	String TOURNAMENT_TEAM_IS_ELIMINATED_PATH = "/{tournamentId}/eliminated";


	@GetMapping()
	List<Team> getTournamentTeams( @PathVariable final Long tournamentId );

	@PostMapping()
	void addTeamsToTournament( @PathVariable final Long tournamentId,
			@RequestBody final List<Long> teamIds );

	@GetMapping(TOURNAMENT_TEAM_STANDING)
	List<TournamentTeam> getTournamentStandings(@PathVariable final Long tournamentId);

	@GetMapping(TOURNAMENT_TEAM_IS_ELIMINATED_PATH)
	List<TournamentTeam> getTournamentTeamsByStatus( @PathVariable final Long tournamentId,
			@RequestParam final Boolean isEliminated );

	@GetMapping(TOURNAMENT_TEAM_BY_ROUND_PATH)
	List<TournamentTeam> getTournamentTeamsByRound( @PathVariable final Long tournamentId,
			@RequestParam final Round round );

	@PutMapping(TOURNAMENT_TEAM_BY_GROUP_PATH)
	void addTournamentTeamToGroup( @RequestParam final Long tournamentTeamId, @RequestParam final String groupName );

	@GetMapping(TOURNAMENT_TEAM_BY_GROUP_PATH)
	List<TournamentTeam> getTournamentTeamsByGroup( @RequestParam final Long tournamentTeamId,
			@RequestParam final String group );
}
