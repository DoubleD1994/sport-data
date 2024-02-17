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

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Validated
@Service
@AllArgsConstructor
@Slf4j
public class TournamentTeamService {

	private final TournamentService tournamentService;

	private final TournamentTeamRepository tournamentTeamRepository;

	private final TeamService teamService;

	public void addTeamsToTournament( final Long tournamentId, final List<Long> teamIds ) {
		final Tournament tournament = tournamentService.findTournamentById( tournamentId );
		Round round = Round.GROUP;
		log.info( "Tournament {}, Teams {}", tournament, teamIds );
		if ( tournament.getFormat().equals( Format.KNOCKOUT ) ) {
			round = Round.getRoundFromValue( tournament.getNumberOfKnockoutRounds() );
		}

		for ( final Long teamId : teamIds ) {
			final Team team = teamService.findTeamById( teamId );
			final TournamentTeam tournamentTeam = getTournamentTeam( team, tournament, round );
			log.info( "Tournament team {}", tournamentTeam );
			tournamentTeamRepository.save( tournamentTeam );
		}
	}

	@Nonnull
	private static TournamentTeam getTournamentTeam( final Team team, final Tournament tournament,
			final Round round ) {
		final TournamentTeam tournamentTeam = new TournamentTeam();
		tournamentTeam.setTeam( team );
		tournamentTeam.setTournament( tournament );
		tournamentTeam.setTeamRound( round );
		tournamentTeam.setTournamentGroup( null );
		tournamentTeam.setGroupGamesPlayed( 0 );
		tournamentTeam.setGroupGamesWon( 0 );
		tournamentTeam.setGroupGamesDraw( 0 );
		tournamentTeam.setGroupGamesLost( 0 );
		tournamentTeam.setGroupScoreFor( 0 );
		tournamentTeam.setGroupScoreAgainst( 0 );
		tournamentTeam.setGroupScoreDifference( 0 );
		tournamentTeam.setGroupPoints( 0 );
		tournamentTeam.setEliminated( false );
		return tournamentTeam;
	}

}
