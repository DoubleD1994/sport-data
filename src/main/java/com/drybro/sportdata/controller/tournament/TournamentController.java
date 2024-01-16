package com.drybro.sportdata.controller.tournament;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.drybro.sportdata.model.Team;
import com.drybro.sportdata.model.Tournament;
import com.drybro.sportdata.model.TournamentTeam;
import com.drybro.sportdata.model.constants.Sport;

public interface TournamentController {

	String TOURNAMENT_PATH = "/tournament";

	String TOURNAMENT_ID_PATH = "/{id}";

	String TOURNAMENT_BY_SPORT = "/{sport}";

	String TOURNAMENT_TEAMS_PATH = "/{id}/teams";

	String TOURNAMENT_STANDINGS_PATH = "/{id}/standings";

	@GetMapping()
	List<Tournament> getTournaments();

	@PostMapping()
	void createTournament( Tournament tournament );

	@GetMapping(TOURNAMENT_ID_PATH)
	Tournament getTournamentById( Long tournamentId );

	@GetMapping(TOURNAMENT_BY_SPORT)
	Tournament getTournamentBySport( Sport sport );

	@PutMapping(TOURNAMENT_ID_PATH)
	void updateTournament( Long tournamentId, Tournament updatedTournament );

	@DeleteMapping(TOURNAMENT_ID_PATH)
	void deleteTournament( Long tournamentId );

	@GetMapping(TOURNAMENT_TEAMS_PATH)
	List<Team> getTournamentTeams(Long tournamentId);

	@PostMapping(TOURNAMENT_TEAMS_PATH)
	void addTeamToTournament(Long tournamentId, Team team );

	@GetMapping(TOURNAMENT_STANDINGS_PATH)
	List<TournamentTeam> getTournamentStandings(Long tournamentId);

}
