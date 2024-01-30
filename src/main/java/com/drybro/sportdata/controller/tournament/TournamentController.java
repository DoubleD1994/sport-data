package com.drybro.sportdata.controller.tournament;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.drybro.sportdata.model.Team;
import com.drybro.sportdata.model.Tournament;
import com.drybro.sportdata.model.TournamentTeam;
import com.drybro.sportdata.model.constants.Round;
import com.drybro.sportdata.model.constants.Sport;

public interface TournamentController {

	String TOURNAMENT_PATH = "/tournament";

	String TOURNAMENT_ID_PATH = "/{tournamentId}";

	String TOURNAMENT_TEAMS_PATH = "/{tournamentId}/teams";

	String TOURNAMENT_STANDINGS_PATH = "/{tournamentId}/standings";

	String TOURNAMENT_TEAM_BY_ROUND_PATH = "/{tournamentId}/round";

	String TOURNAMENT_TEAM_BY_GROUP_PATH = "/{tournamentId}/standing";

	String TOURNAMENT_TEAM_IS_ELIMINATED_PATH = "/{tournamentId}/eliminated";

	String TOURNAMENT_SPORT_PATH = "/sport";

	@GetMapping()
	List<Tournament> getTournaments();

	@PostMapping()
	void createTournament( @RequestBody final Tournament tournament );

	@GetMapping(TOURNAMENT_ID_PATH)
	Tournament getTournamentById( @PathVariable final Long tournamentId );

	@GetMapping(TOURNAMENT_SPORT_PATH)
	Tournament getTournamentBySport( @RequestParam final Sport sport );

	@PutMapping(TOURNAMENT_ID_PATH)
	void updateTournament( @PathVariable final Long tournamentId,
			@RequestBody final Tournament updatedTournament );

	@DeleteMapping(TOURNAMENT_ID_PATH)
	void deleteTournament( @PathVariable final Long tournamentId );

	@GetMapping(TOURNAMENT_TEAMS_PATH)
	List<Team> getTournamentTeams( @PathVariable final Long tournamentId );

	@PostMapping(TOURNAMENT_TEAMS_PATH)
	void addTeamsToTournament( @PathVariable final Long tournamentId,
			@RequestBody final List<Long> teamIds );

	@GetMapping(TOURNAMENT_STANDINGS_PATH)
	List<TournamentTeam> getTournamentStandings( @PathVariable final Long tournamentId );

	@GetMapping(TOURNAMENT_TEAM_IS_ELIMINATED_PATH)
	Tournament getTournamentTeamsByStatus( @PathVariable final Long tournamentId,
			@RequestParam final Boolean isEliminated );

	@GetMapping(TOURNAMENT_TEAM_BY_ROUND_PATH)
	List<TournamentTeam> getTournamentTeamByRound( @PathVariable final Long tournamentId,
			@RequestParam final Round round );

	@GetMapping(TOURNAMENT_TEAM_BY_GROUP_PATH)
	List<TournamentTeam> getTournamentTeamByGroup( @PathVariable final Long tournamentId,
			@RequestParam final String group );

}
