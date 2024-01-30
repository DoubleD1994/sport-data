package com.drybro.sportdata.controller.tournament;

import static com.drybro.sportdata.controller.tournament.TournamentController.TOURNAMENT_PATH;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.drybro.sportdata.model.constants.Sport;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Validated
@RestController
@RequestMapping(path = TOURNAMENT_PATH, produces = "application/json")
@AllArgsConstructor
@Slf4j
public class TournamentControllerImpl implements TournamentController{

	@Override
	@GetMapping
	public List<Tournament> getTournaments() {
		return null;
	}

	@Override
	@PostMapping
	public void createTournament( @RequestBody final Tournament tournament ) {

	}

	@Override
	@GetMapping(TOURNAMENT_ID_PATH)
	public Tournament getTournamentById( @PathVariable final Long tournamentId ) {
		return null;
	}

	@Override
	@GetMapping()
	public Tournament getTournamentBySport( @RequestParam final Sport sport ) {
		return null;
	}

	@Override
	@PutMapping(TOURNAMENT_ID_PATH)
	public void updateTournament( @PathVariable final Long tournamentId,
			@RequestBody final Tournament updatedTournament) {

	}

	@Override
	@DeleteMapping(TOURNAMENT_ID_PATH)
	public void deleteTournament( @PathVariable final Long tournamentId ) {

	}

	@Override
	@GetMapping(TOURNAMENT_TEAMS_PATH)
	public List<Team> getTournamentTeams( @PathVariable final Long tournamentId ) {
		return null;
	}

	@Override
	@PostMapping(TOURNAMENT_TEAMS_PATH)
	public void addTeamsToTournament( @PathVariable final Long tournamentId,
			@RequestBody final List<Long> teamIds ) {

	}

	@Override
	@GetMapping(TOURNAMENT_STANDINGS_PATH)
	public List<TournamentTeam> getTournamentStandings( @PathVariable final Long tournamentId ) {
		return null;
	}

	@Override
	@GetMapping(TOURNAMENT_TEAM_IS_ELIMINATED_PATH)
	public Tournament getTournamentTeamsByStatus( @PathVariable final Long tournamentId,
			@RequestParam final Boolean isEliminated  ) {
		return null;
	}

	@Override
	@GetMapping(TOURNAMENT_TEAM_BY_ROUND_PATH)
	public List<TournamentTeam> getTournamentTeamByRound( @PathVariable final Long tournamentId,
			@RequestParam final Round round ) {
		return null;
	}

	@Override
	@GetMapping(TOURNAMENT_TEAM_BY_GROUP_PATH)
	public List<TournamentTeam> getTournamentTeamByGroup( @PathVariable final Long tournamentId,
			@RequestParam final String group ) {
		return null;
	}
}
