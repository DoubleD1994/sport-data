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

	String TOURNAMENT_SPORT_PATH = "/sport";

	@GetMapping()
	List<Tournament> getTournaments();

	@PostMapping()
	void createTournament( @RequestBody final Tournament tournament );

	@GetMapping(TOURNAMENT_ID_PATH)
	Tournament getTournamentById( @PathVariable final Long tournamentId );

	@GetMapping(TOURNAMENT_SPORT_PATH)
	List<Tournament> getTournamentsBySport( @RequestParam final Sport sport );

	@PutMapping(TOURNAMENT_ID_PATH)
	void updateTournament( @PathVariable final Long tournamentId,
			@RequestBody final Tournament updatedTournament );

	@DeleteMapping(TOURNAMENT_ID_PATH)
	void deleteTournament( @PathVariable final Long tournamentId );

}
