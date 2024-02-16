package com.drybro.sportdata.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import com.drybro.sportdata.controller.tournament.TournamentController;
import com.drybro.sportdata.model.Tournament;
import com.drybro.sportdata.model.constants.Format;
import com.drybro.sportdata.model.constants.Sport;
import com.drybro.sportdata.repository.TournamentRepository;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class TournamentControllerImplTest {

	@MockBean
	private TournamentRepository tournamentRepository;

	@Autowired
	private TournamentController tournamentController;

	private final static List<Tournament> tournamentList = new ArrayList<>();

	private static Tournament tournamentOne;
	private static Tournament tournamentTwo;
	private static Tournament tournamentThree;

	@BeforeAll
	public static void beforeAll() {
		tournamentOne = new Tournament( 1L, "Tournament One", 8, Format.LEAGUE, 1, 8, 1, 1,
				Sport.FOOTBALL );
		tournamentTwo = new Tournament( 2L, "Tournament Two", 8, Format.KNOCKOUT, 0, 0, 3, 0,
				Sport.FOOTBALL );
		tournamentThree = new Tournament( 3L, "Tournament Three", 8, Format.GROUPS_AND_KNOCKOUT, 8,
				4, 4, 14, Sport.AMERICAN_FOOTBALL );

		tournamentList.add( tournamentOne );
		tournamentList.add( tournamentTwo );
		tournamentList.add( tournamentThree );
	}

	@Test
	public void getTournaments_HappyPath() {
		when( tournamentRepository.findAll() ).thenReturn( tournamentList );
		final List<Tournament> tournaments = tournamentController.getTournaments();
		assertThat( tournaments ).contains( tournamentOne );
		assertThat( tournaments ).contains( tournamentTwo );
		assertThat( tournaments ).contains( tournamentThree );
	}

	@Test
	public void getTournaments_HappyPathEmptyList() {
		when( tournamentRepository.findAll() ).thenReturn( new ArrayList<>() );
		final List<Tournament> tournaments = tournamentController.getTournaments();
		assertThat( tournaments.size() ).isEqualTo( 0 );
	}

	@Test
	public void createTournament_HappyPath() {
		tournamentController.createTournament( tournamentOne );
		verify( tournamentRepository, times( 1 ) ).save( tournamentOne );
	}

	@Test
	public void createTournament_NullTournamentThrowsException() {
		when( tournamentRepository.save( null ) ).thenThrow( ConstraintViolationException.class );
		assertThrows( ConstraintViolationException.class,
				() -> tournamentController.createTournament( null ) );
	}

	@Test
	public void createTournament_NullTournamentNameThrowsException() {
		tournamentOne.setName( null );
		when( tournamentRepository.save( tournamentOne ) ).thenThrow( ConstraintViolationException.class );
		assertThrows( ConstraintViolationException.class, () -> tournamentController.createTournament( tournamentOne ) );
	}

	@Test
	public void getTournamentById_HappyPath() {
		when(tournamentRepository.findById( 1L )).thenReturn( Optional.of( tournamentOne ) );
		final Tournament returnedTournament = tournamentController.getTournamentById( 1L );
		assertThat( returnedTournament.getName() ).isEqualTo( tournamentOne.getName() );
	}

	@Test
	void getTournamentById_TournamentNotFoundThrowsResponseStatusException() {
		assertThrows( ResponseStatusException.class,
				() -> tournamentController.getTournamentById( 5L ) );
	}

	@Test
	void getTournamentById_NullValueThrowsResponseStatusException() {
		assertThrows( ResponseStatusException.class,
				() -> tournamentController.getTournamentById( null ) );
	}

	@Test
	void getTournamentBySport_HappyPath() {
		List<Tournament> footballTournaments = new ArrayList<>();
		footballTournaments.add( tournamentOne );
		footballTournaments.add( tournamentTwo );
		when( tournamentRepository.findTournamentsBySport( Sport.FOOTBALL ) ).thenReturn( footballTournaments );

		List<Tournament> tournaments = tournamentController.getTournamentsBySport( Sport.FOOTBALL );
		assertThat( tournaments.size() ).isEqualTo( 2 );
	}

	@Test
	void getTournamentBySport_NullValueThrowsResponseStatusException() {
		when( tournamentRepository.findTournamentsBySport(null)).thenReturn( new ArrayList<>() );
		final List<Tournament> tournaments = tournamentController.getTournamentsBySport( null );
		assertThat( tournaments.size() ).isEqualTo( 0 );
	}

	@Test
	void updateTournament_HappyPath() {
		when( tournamentRepository.findById( 1L ) ).thenReturn(
				Optional.of( tournamentOne ) );

		final Tournament updateTournament = tournamentOne;
		updateTournament.setName( "Updated" );

		tournamentController.updateTournament(1L, updateTournament);

		verify( tournamentRepository, times(1) ).findById( 1L );
		verify( tournamentRepository, times( 1 ) ).save( updateTournament );
	}

	@Test
	void updateTournament_tournamentNotFoundThrowsResponseStatusException() {
		assertThrows( ResponseStatusException.class,
				() -> tournamentController.updateTournament( 5L, new Tournament() ) );
	}

	@Test
	void deleteTournament_HappyPath() {
		tournamentController.deleteTournament( tournamentOne.getId() );
		verify( tournamentRepository, times( 1 ) ).deleteById( tournamentOne.getId() );
	}

}
