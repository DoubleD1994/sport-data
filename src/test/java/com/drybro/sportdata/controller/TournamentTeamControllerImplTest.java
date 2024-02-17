package com.drybro.sportdata.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.drybro.sportdata.controller.tournamentteam.TournamentTeamController;
import com.drybro.sportdata.model.Team;
import com.drybro.sportdata.model.Tournament;
import com.drybro.sportdata.model.TournamentTeam;
import com.drybro.sportdata.model.constants.Format;
import com.drybro.sportdata.model.constants.Round;
import com.drybro.sportdata.model.constants.Sport;
import com.drybro.sportdata.repository.TeamRepository;
import com.drybro.sportdata.repository.TournamentRepository;
import com.drybro.sportdata.repository.TournamentTeamRepository;

@SpringBootTest
public class TournamentTeamControllerImplTest {

	@MockBean
	private TournamentTeamRepository tournamentTeamRepository;

	@MockBean
	private TournamentRepository tournamentRepository;

	@MockBean
	private TeamRepository teamRepository;

	@Autowired
	private TournamentTeamController tournamentTeamController;

	private static Tournament tournamentOne;
	private static Tournament tournamentTwo;

	private final static List<Long> teamList = new ArrayList<>();
	private final static List<TournamentTeam> tournamentTeamListOne = new ArrayList<>();
	private final static List<TournamentTeam> tournamentTeamListTwo = new ArrayList<>();

	private static Team teamOne;
	private static Team teamTwo;
	private static Team teamThree;
	private static Team teamFour;

	private static TournamentTeam tournamentTeamOne;
	private static TournamentTeam tournamentTeamTwo;
	private static TournamentTeam tournamentTeamThree;
	private static TournamentTeam tournamentTeamFour;

	@BeforeAll
	public static void beforeAll() {
		teamOne = new Team( 1L, "teamOne", Sport.FOOTBALL, "path", new ArrayList<>() );
		teamTwo = new Team( 2L, "teamTwo", Sport.FOOTBALL, null, new ArrayList<>() );
		teamThree = new Team( 3L, "teamThree", Sport.FOOTBALL, null, new ArrayList<>() );
		teamFour = new Team( 4L, "teamFour", Sport.FOOTBALL, null, new ArrayList<>() );

		teamList.add( teamOne.getTeamId() );
		teamList.add( teamTwo.getTeamId() );
		teamList.add( teamThree.getTeamId() );
		teamList.add( teamFour.getTeamId() );

		tournamentOne = new Tournament( 1L, "Tournament One", 2, Format.LEAGUE, 1, 2, 0, 1,
				Sport.FOOTBALL, new ArrayList<>() );

		tournamentTwo = new Tournament( 2L, "Tournament Two", 2, Format.KNOCKOUT, 0, 0, 1, 0,
				Sport.FOOTBALL, new ArrayList<>() );

		tournamentTeamOne = new TournamentTeam( null, teamOne, tournamentOne, Round.GROUP, null, 0, 0, 0, 0, 0,
				0, 0, 0, false );
		tournamentTeamTwo = new TournamentTeam( null, teamTwo, tournamentOne, Round.GROUP, null, 0, 0, 0, 0, 0,
				0, 0, 0, false );
		tournamentTeamThree = new TournamentTeam( null, teamThree, tournamentTwo, Round.FINAL, null, 0, 0, 0, 0,
				0, 0, 0, 0, false );
		tournamentTeamFour = new TournamentTeam( null, teamFour, tournamentTwo, Round.FINAL, null, 0, 0, 0, 0,
				0, 0, 0, 0, false );

		tournamentTeamListOne.add( tournamentTeamOne );
		tournamentTeamListOne.add( tournamentTeamTwo );
		tournamentTeamListTwo.add( tournamentTeamThree );
		tournamentTeamListTwo.add( tournamentTeamFour );
	}

	@Test
	public void getTournamentTeams_HappyPath() {
		when( tournamentRepository.findById( 1L ) ).thenReturn(
				Optional.ofNullable( tournamentOne ) );
		when( tournamentTeamRepository.findTournamentTeamsByTournament(
				tournamentOne ) ).thenReturn( tournamentTeamListOne );

		final List<Team> returnedTeamList = tournamentTeamController.getTournamentTeams( 1L );
		assertThat( returnedTeamList ).contains( teamOne );
		assertThat( returnedTeamList ).contains( teamTwo );
	}

	@Test
	public void addTeamsToTournament_HappyPathGroups() {
		when( tournamentRepository.findById( 1L ) ).thenReturn(
				Optional.ofNullable( tournamentOne ) );
		when( teamRepository.findById( 1L ) ).thenReturn( Optional.ofNullable( teamOne ) );
		when( teamRepository.findById( 2L ) ).thenReturn( Optional.ofNullable( teamTwo ) );
		when( teamRepository.findById( 3L ) ).thenReturn( Optional.ofNullable( teamThree ) );
		when( teamRepository.findById( 4L ) ).thenReturn( Optional.ofNullable( teamFour ) );

		tournamentTeamController.addTeamsToTournament( 1L, teamList );

		verify( tournamentTeamRepository, times( 1 ) ).save( tournamentTeamOne );
		verify( tournamentTeamRepository, times( 0 ) ).save( tournamentTeamThree );
		verify( tournamentTeamRepository, times( 4 ) ).save( any() );
	}

	@Test
	public void addTeamsToTournament_HappyPathKnockout() {
		when( tournamentRepository.findById( 2L ) ).thenReturn(
				Optional.ofNullable( tournamentTwo ) );
		when( teamRepository.findById( 1L ) ).thenReturn( Optional.ofNullable( teamOne ) );
		when( teamRepository.findById( 2L ) ).thenReturn( Optional.ofNullable( teamTwo ) );
		when( teamRepository.findById( 3L ) ).thenReturn( Optional.ofNullable( teamThree ) );
		when( teamRepository.findById( 4L ) ).thenReturn( Optional.ofNullable( teamFour ) );

		tournamentTeamController.addTeamsToTournament( 2L, teamList );

		verify( tournamentTeamRepository, times( 1 ) ).save( tournamentTeamThree );
		verify( tournamentTeamRepository, times( 1 ) ).save( tournamentTeamFour );
		verify( tournamentTeamRepository, times( 0 ) ).save( tournamentTeamOne );
		verify( tournamentTeamRepository, times( 4 ) ).save( any() );
	}

	@Test
	public void getTournamentStandings_HappyPath() {
		when( tournamentRepository.findById( 2L ) ).thenReturn(
				Optional.ofNullable( tournamentTwo ) );
		when( tournamentTeamRepository.findTournamentTeamsByTournamentOrderByGroupPointsDescGroupScoreDifferenceDescGroupScoreDifferenceDesc(
				tournamentTwo ) ).thenReturn( tournamentTeamListTwo );

		final List<TournamentTeam> tournamentTeamList = tournamentTeamController.getTournamentStandings(
				2L );

		assertThat( tournamentTeamList ).contains( tournamentTeamThree );
		assertThat( tournamentTeamList ).contains( tournamentTeamFour );
	}

	@Test
	public void getTournamentTeamsByStatus_HappyPath() {
		when( tournamentRepository.findById( 2L ) ).thenReturn(
				Optional.ofNullable( tournamentTwo ) );
		when( tournamentTeamRepository.findTournamentTeamsByTournamentAndEliminated( tournamentTwo,
				false ) ).thenReturn( tournamentTeamListTwo );

		final List<TournamentTeam> tournamentTeamList = tournamentTeamController.getTournamentTeamsByStatus(
				2L, false );

		assertThat( tournamentTeamList ).contains( tournamentTeamThree );
		assertThat( tournamentTeamList ).contains( tournamentTeamFour );
	}

	@Test
	public void getTournamentTeamsByRound_HappyPath() {
		when( tournamentRepository.findById( 2L ) ).thenReturn(
				Optional.ofNullable( tournamentTwo ) );
		when( tournamentTeamRepository.findTournamentTeamsByTournamentAndTeamRound( tournamentTwo,
				Round.FINAL ) ).thenReturn( tournamentTeamListTwo );

		final List<TournamentTeam> tournamentTeamList = tournamentTeamController.getTournamentTeamsByRound(
				2L, Round.FINAL );

		assertThat( tournamentTeamList ).contains( tournamentTeamThree );
		assertThat( tournamentTeamList ).contains( tournamentTeamFour );
	}

	@Test
	public void addTournamentTeamToGroup_HappyPath() {
		when( tournamentTeamRepository.findById( 1L ) ).thenReturn(
				Optional.ofNullable( tournamentTeamTwo ) );
		tournamentTeamTwo.setTournamentGroup( "A" );

		tournamentTeamController.addTournamentTeamToGroup( 1L, "A" );

		verify( tournamentTeamRepository, times( 1 ) ).save( tournamentTeamTwo );
	}

	@Test
	public void getTournamentTeamsByGroup_HappyPath() {
		when( tournamentRepository.findById( 2L ) ).thenReturn(
				Optional.ofNullable( tournamentTwo ) );
		when( tournamentTeamRepository.findTournamentTeamsByTournamentAndTournamentGroupOrderByGroupPointsDescGroupScoreDifferenceDescGroupScoreDifferenceDesc(
				tournamentTwo, "A" ) ).thenReturn( tournamentTeamListTwo );

		final List<TournamentTeam> tournamentTeamList = tournamentTeamController.getTournamentTeamsByGroup(
				2L, "A" );

		assertThat( tournamentTeamList ).contains( tournamentTeamThree );
		assertThat( tournamentTeamList ).contains( tournamentTeamFour );
	}

}
