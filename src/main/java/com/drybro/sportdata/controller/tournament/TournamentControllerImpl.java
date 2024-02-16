package com.drybro.sportdata.controller.tournament;

import static com.drybro.sportdata.controller.tournament.TournamentController.TOURNAMENT_PATH;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.drybro.sportdata.model.Tournament;
import com.drybro.sportdata.model.constants.Sport;
import com.drybro.sportdata.repository.TournamentRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Validated
@RestController
@RequestMapping(path = TOURNAMENT_PATH, produces = "application/json")
@AllArgsConstructor
@Slf4j
public class TournamentControllerImpl implements TournamentController {

	private final TournamentRepository tournamentRepository;

	@Override
	@GetMapping
	public List<Tournament> getTournaments() {
		final List<Tournament> tournaments = new ArrayList<>();
		tournamentRepository.findAll().forEach( tournaments::add );
		return tournaments;
	}

	@Override
	@PostMapping
	public void createTournament( @RequestBody final Tournament tournament ) {
		tournamentRepository.save( tournament );
		log.info( "TOURNAMENT CREATED {}", tournament );
	}

	@Override
	@GetMapping(TOURNAMENT_ID_PATH)
	public Tournament getTournamentById( @PathVariable final Long tournamentId ) {
		return findTournamentById( tournamentId );
	}

	@Override
	@GetMapping(TOURNAMENT_SPORT_PATH)
	public List<Tournament> getTournamentsBySport( @RequestParam final Sport sport ) {
		return tournamentRepository.findTournamentsBySport( sport );
	}

	@Override
	@PutMapping(TOURNAMENT_ID_PATH)
	public void updateTournament( @PathVariable final Long tournamentId,
			@RequestBody final Tournament updatedTournament ) {
		final Tournament tournament = findTournamentById( tournamentId );
		updateTournament(tournament, updatedTournament);
		log.info( "Tournament with ID {} updated", tournamentId );
	}

	@Override
	@DeleteMapping(TOURNAMENT_ID_PATH)
	public void deleteTournament( @PathVariable final Long tournamentId ) {
		tournamentRepository.deleteById( tournamentId );
		log.info( "Tournament with ID {} deleted", tournamentId );
	}

	private Tournament findTournamentById( final Long tournamentId ) {
		try {
			return tournamentRepository.findById( tournamentId ).orElseThrow();
		} catch ( final NoSuchElementException nsee ) {
			throw new ResponseStatusException( HttpStatus.NOT_FOUND,
					"Tournament with ID " + tournamentId + "  not found", nsee );
		}
	}

	private void updateTournament( final Tournament tournament,
			final Tournament updatedTournament ) {
		if ( !updatedTournament.getName().isBlank() ) {
			tournament.setName( updatedTournament.getName() );
		}
		if( updatedTournament.getNumberOfTeams().describeConstable().isPresent() ) {
			tournament.setNumberOfTeams( updatedTournament.getNumberOfTeams() );
		}
		if ( !updatedTournament.getFormat().name().isBlank() ) {
			tournament.setFormat( updatedTournament.getFormat() );
		}
		if ( updatedTournament.getNumberOfGroups().describeConstable().isPresent() ) {
			tournament.setNumberOfGroups( updatedTournament.getNumberOfGroups() );
		}
		if ( updatedTournament.getGroupSize().describeConstable().isPresent() ) {
			tournament.setGroupSize( updatedTournament.getGroupSize() );
		}
		if ( updatedTournament.getNumberOfRounds().describeConstable().isPresent() ) {
			tournament.setNumberOfGroups( updatedTournament.getNumberOfRounds() );
		}
		if ( updatedTournament.getNumberOfQualifyingTeams().describeConstable().isPresent() ) {
			tournament.setNumberOfQualifyingTeams( updatedTournament.getNumberOfQualifyingTeams() );
		}
		if ( updatedTournament.getSport().describeConstable().isPresent() ) {
			tournament.setSport( updatedTournament.getSport() );
		}
		tournamentRepository.save( tournament );
	}

}
