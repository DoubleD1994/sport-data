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
import com.drybro.sportdata.repository.TeamRepository;

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
		teamOne = new Team( 1L, "teamOne", "path" );
		teamTwo = new Team( 2L, "teamTwo", null );
		teamThree = new Team( 3L, "teamThree", null );

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
		assertThrows( ResponseStatusException.class, () -> teamController.createTeam( null ) );
	}

	@Test
	public void createTeam_NullTeamNameThrowsException() {
		assertThrows( NullPointerException.class,
				() -> teamController.createTeam( new Team( 4L, null, null ) ) );
	}

	@Test
	public void getTeamById_HappyPath() {
		when(teamRepository.findById( 1L )).thenReturn( Optional.of( teamOne ) );
		final Team returnedTeam = teamController.getTeamById( 1L );
		assertThat( returnedTeam.getTeamName() ).isEqualTo( teamOne.getTeamName() );
	}

	@Test
	void getTeamById_TeamNotFoundThrowsResponseStatusException() {
		assertThrows( ResponseStatusException.class,
				() -> teamController.getTeamById( 5l ) );
	}

	@Test
	void getTeamById_NullValueThrowsResponseStatusException() {
		assertThrows( ResponseStatusException.class,
				() -> teamController.getTeamById( null ) );
	}

	@Test
	void updateTeam_HappyPath() {
		when( teamRepository.findById( 1L ) ).thenReturn(
				Optional.of( teamOne ) );

		final Team updateTeam = teamOne;
		updateTeam.setTeamName( "Updated" );

		teamController.updateTeam(1L, updateTeam);

		verify( teamRepository, times(1) ).findById( 1L );
		verify( teamRepository, times( 1 ) ).save( updateTeam );
	}

	@Test
	void updateUser_UserNotFoundThrowsResponseStatusException() {
		assertThrows( ResponseStatusException.class,
				() -> teamController.updateTeam( 5l, new Team() ) );
	}

	@Test
	void deleteUser_HappyPath() {
		teamController.deleteTeam( teamOne.getId() );
		verify( teamRepository, times( 1 ) ).deleteById( teamOne.getId() );
	}

}
