package com.drybro.sportdata.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.drybro.sportdata.model.Team;
import com.drybro.sportdata.model.Tournament;
import com.drybro.sportdata.model.TournamentTeam;
import com.drybro.sportdata.model.constants.Format;
import com.drybro.sportdata.model.constants.Round;
import com.drybro.sportdata.repository.TournamentTeamRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Validated
@Service
@AllArgsConstructor
@Slf4j
public class TournamentTeamService {

	private final TournamentService tournamentService;

	private final TournamentTeamRepository tournamentTeamRepository;

	public void addTeamsToTournament( final Long tournamentId, final List<Team> teams ) {
		final Tournament tournament = tournamentService.findTournamentById( tournamentId );
		Round round = Round.GROUP;

		if ( tournament.getFormat().equals( Format.KNOCKOUT ) ) {
			round = Round.getRoundFromValue( tournament.getNumberOfKnockoutRounds() );
		}

		for ( final Team team : teams ) {
			final TournamentTeam tournamentTeam = new TournamentTeam( team, tournament, round, 0, 0,
					0, 0, 0, 0, 0, 0, false );
			tournamentTeamRepository.save( tournamentTeam );
		}
	}

}
