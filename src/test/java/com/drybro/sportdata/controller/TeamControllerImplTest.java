package com.drybro.sportdata.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import com.drybro.sportdata.controller.team.TeamController;
import com.drybro.sportdata.model.Team;
import com.drybro.sportdata.model.Tournament;
import com.drybro.sportdata.model.constants.Sport;
import com.drybro.sportdata.repository.TeamRepository;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class TeamControllerImplTest {

	@MockBean
	private TeamRepository teamRepository;

	@Autowired
	private TeamController teamController;

	private final static List<Team> teamList = new ArrayList<>();

	private static Team teamOne;
	private static Team teamTwo;
	private static Team teamThree;

	@BeforeAll
	public static void beforeAll() {
		teamOne = new Team( 1L, "teamOne", Sport.FOOTBALL, "path", new ArrayList<>() );
		teamTwo = new Team( 2L, "teamTwo", Sport.FOOTBALL, null, new ArrayList<>() );
		teamThree = new Team( 3L, "teamThree", Sport.AMERICAN_FOOTBALL, null, new ArrayList<>() );

		teamList.add( teamOne );
		teamList.add( teamTwo );
		teamList.add( teamThree );
	}

	@Test
	public void getTeams_happyPath() {
		when( teamRepository.findAll() ).thenReturn( teamList );
		final List<Team> teams = teamController.getTeams();
		assertThat( teams.size() ).isEqualTo( 3 );
		assertThat( teams ).contains( teamOne );
		assertThat( teams ).contains( teamTwo );
		assertThat( teams ).contains( teamThree );
	}

	@Test
	public void getTeams_HappyPathEmptyList() {
		when( teamRepository.findAll() ).thenReturn( new ArrayList<>() );
		final List<Team> teams = teamController.getTeams();
		assertThat( teams.size() ).isEqualTo( 0 );
	}

	@Test
	public void createTeam_HappyPath() {
		teamController.createTeam( teamOne );
		verify( teamRepository, times( 1 ) ).save( teamOne );
	}

	@Test
	public void createTeam_HappyPathNullLogoPath() {
		assertDoesNotThrow( () -> teamController.createTeam( teamTwo ) );
		verify( teamRepository, times( 1 ) ).save( teamTwo );
	}

	@Test
	public void createTeam_NullTeamThrowsException() {
		when( teamRepository.save( null ) ).thenThrow( ConstraintViolationException.class );
		assertThrows( ConstraintViolationException.class, () -> teamController.createTeam( null ) );
	}

	@Test
	public void createTeam_NullTeamNameThrowsException() {
		when( teamRepository.save( new Team( 4L, null, null, null, new ArrayList<>() ) ) ).thenThrow(
				ConstraintViolationException.class );
		assertThrows( ConstraintViolationException.class,
				() -> teamController.createTeam( new Team( 4L, null, null, null, new ArrayList<>() ) ) );
	}

	@Test
	public void getTeamById_HappyPath() {
		when( teamRepository.findById( 1L ) ).thenReturn( Optional.of( teamOne ) );
		final Team returnedTeam = teamController.getTeamById( 1L );
		assertThat( returnedTeam.getTeamName() ).isEqualTo( teamOne.getTeamName() );
	}

	@Test
	void getTeamById_TeamNotFoundThrowsResponseStatusException() {
		assertThrows( ResponseStatusException.class, () -> teamController.getTeamById( 5l ) );
	}

	@Test
	void getTeamById_NullValueThrowsResponseStatusException() {
		assertThrows( ResponseStatusException.class, () -> teamController.getTeamById( null ) );
	}

	@Test
	void updateTeam_HappyPath() {
		when( teamRepository.findById( 1L ) ).thenReturn( Optional.of( teamOne ) );

		final Team updateTeam = teamOne;
		updateTeam.setTeamName( "Updated" );

		teamController.updateTeam( 1L, updateTeam );

		verify( teamRepository, times( 1 ) ).findById( 1L );
		verify( teamRepository, times( 1 ) ).save( updateTeam );
	}

	@Test
	void updateTeam_TeamNotFoundThrowsResponseStatusException() {
		assertThrows( ResponseStatusException.class,
				() -> teamController.updateTeam( 5l, new Team() ) );
	}

	@Test
	void deleteTeam_HappyPath() {
		teamController.deleteTeam( teamOne.getTeamId() );
		verify( teamRepository, times( 1 ) ).deleteById( teamOne.getTeamId() );
	}

	@Test
	void getTeamsBySport_HappyPath() {
		List<Team> footballTeams = new ArrayList<>();
		footballTeams.add( teamOne );
		footballTeams.add( teamTwo );
		when( teamRepository.findTeamsBySport( Sport.FOOTBALL ) ).thenReturn( footballTeams );

		List<Team> teams = teamController.getTeamsBySport( Sport.FOOTBALL );
		assertThat( teams.size() ).isEqualTo( 2 );
	}

	@Test
	void getTeamBySport_NullValueThrowsResponseStatusException() {
		when( teamRepository.findTeamsBySport( null ) ).thenReturn( new ArrayList<>() );
		final List<Team> teams = teamController.getTeamsBySport( null );
		assertThat( teams.size() ).isEqualTo( 0 );
	}
}
