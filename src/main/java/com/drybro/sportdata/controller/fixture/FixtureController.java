package com.drybro.sportdata.controller.fixture;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.drybro.sportdata.model.Fixture;

public interface FixtureController {

	String FIXTURE_PATH = "/fixture";

	String FIXTURE_ID_PATH = "/{fixtureId}";

	String FIXTURE_RESULT_PATH = "/{fixtureId}/result";

	String FIXTURE_BY_DATE_PATH = "/date";

	String FIXTURE_BY_COMPLETED_PATH = "/completed";

	String FIXTURE_BY_ROUND_PATH = "/round";

	String FIXTURE_BY_GROUP_PATH = "/group";

	@GetMapping()
	List<Fixture> getFixtures( @RequestParam(required = false) final Long tournamentId,
			@RequestParam(required = false) final Long teamId );

	@GetMapping(FIXTURE_ID_PATH)
	Fixture getFixtureById( @PathVariable final Long fixtureId );

	@PostMapping()
	void createFixture( @RequestBody final Fixture fixture );

	@PutMapping(FIXTURE_ID_PATH)
	void updateFixture( @PathVariable final Long fixtureId, @RequestBody final Fixture fixture );

	@DeleteMapping(FIXTURE_ID_PATH)
	void deleteFixture( @PathVariable final Long fixtureId );

	@PostMapping(FIXTURE_RESULT_PATH)
	void updateFixtureResult( @PathVariable final Long fixtureId,
			@RequestParam final Integer homeTeamPoints,
			@RequestParam final Integer awayTeamPoints );

	@GetMapping(FIXTURE_BY_DATE_PATH)
	List<Fixture> getFixturesByDate( @RequestParam(required = false) final Long tournamentId,
			@RequestParam(required = false) final Long teamId,
			@RequestParam(required = false) LocalDate startDate,
			@RequestParam(required = false) LocalDate endDate );

	@GetMapping(FIXTURE_BY_COMPLETED_PATH)
	List<Fixture> getFixturesByCompleted( @RequestParam(required = false) final Long tournamentId,
			@RequestParam(required = false) final Long teamId, @RequestParam Boolean isCompleted );

	@GetMapping(FIXTURE_BY_ROUND_PATH)
	List<Fixture> getTournamentFixturesByRound( @RequestParam final Long tournamentId,
			@RequestParam Boolean isCompleted );

	@GetMapping(FIXTURE_BY_GROUP_PATH)
	List<Fixture> getTournamentFixturesByGroup( @RequestParam final Long tournamentId,
			@RequestParam String group );

}
