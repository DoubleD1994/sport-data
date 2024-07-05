package com.drybro.sportdata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.drybro.sportdata.model.Fixture;
import com.drybro.sportdata.model.Team;
import com.drybro.sportdata.model.Tournament;

@Repository
public interface FixtureRepository extends CrudRepository<Fixture, Long> {

	List<Fixture> findFixturesByTournament( Tournament tournament );

	List<Fixture> findFixturesByHomeTeamOrAwayTeam( Team homeTeam );

	List<Fixture> findFixturesByTournamentAndHomeTeam( Tournament tournament, Team homeTeam );

}
