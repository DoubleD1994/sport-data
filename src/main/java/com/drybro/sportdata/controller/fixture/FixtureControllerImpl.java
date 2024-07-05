package com.drybro.sportdata.controller.fixture;

import static com.drybro.sportdata.controller.fixture.FixtureController.FIXTURE_PATH;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.StreamSupport;

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

import com.drybro.sportdata.model.Fixture;
import com.drybro.sportdata.repository.FixtureRepository;
import com.drybro.sportdata.service.FixtureService;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Validated
@RestController
@RequestMapping(path = FIXTURE_PATH, produces = "application/json")
@AllArgsConstructor
@Slf4j
public class FixtureControllerImpl implements FixtureController {

	private final FixtureRepository fixtureRepository;

	private final FixtureService fixtureService;

	@Override
	@GetMapping
	public List<Fixture> getFixtures( @RequestParam(required = false) final Long tournamentId,
			@RequestParam(required = false) final Long teamId ) {
		if ( tournamentId != null && teamId != null ) {
			return fixtureService.getFixturesByTournamentAndTeam( tournamentId, teamId );
		}
		if ( tournamentId != null ) {
			return fixtureService.getFixturesByTournament( tournamentId );
		}
		if ( teamId != null ) {
			return fixtureService.getFixturesByTeam( teamId );
		}
		return StreamSupport.stream( fixtureRepository.findAll().spliterator(), false ).toList();
	}

	@Override
	@GetMapping(FIXTURE_ID_PATH)
	public Fixture getFixtureById( @PathVariable final Long fixtureId ) {
		return null;
	}

	@Override
	@PostMapping
	public void createFixture( @RequestBody final Fixture fixture ) {

	}

	@Override
	@PutMapping(FIXTURE_ID_PATH)
	public void updateFixture( @PathVariable final Long fixtureId,
			@RequestBody final Fixture fixture ) {

	}

	@Override
	@DeleteMapping(FIXTURE_ID_PATH)
	public void deleteFixture( @PathVariable final Long fixtureId ) {

	}

	@Override
	@PostMapping(FIXTURE_RESULT_PATH)
	public void updateFixtureResult( @PathVariable final Long fixtureId,
			@RequestParam final Integer homeTeamPoints,
			@RequestParam final Integer awayTeamPoints ) {

	}

	@Override
	@GetMapping(FIXTURE_BY_DATE_PATH)
	public List<Fixture> getFixturesByDate( @RequestParam(required = false) final Long tournamentId,
			@RequestParam(required = false) final Long teamId,
			@RequestParam(required = false) LocalDate startDate,
			@RequestParam(required = false) LocalDate endDate ) {
		return null;
	}

	@Override
	@GetMapping(FIXTURE_BY_COMPLETED_PATH)
	public List<Fixture> getFixturesByCompleted(
			@RequestParam(required = false) final Long tournamentId,
			@RequestParam(required = false) final Long teamId, @RequestParam Boolean isCompleted ) {
		return null;
	}

	@Override
	@GetMapping(FIXTURE_BY_ROUND_PATH)
	public List<Fixture> getTournamentFixturesByRound( @RequestParam final Long tournamentId,
			@RequestParam Boolean isCompleted ) {
		return null;
	}

	@Override
	@GetMapping(FIXTURE_BY_GROUP_PATH)
	public List<Fixture> getTournamentFixturesByGroup( @RequestParam final Long tournamentId,
			@RequestParam String group ) {
		return null;
	}
}
