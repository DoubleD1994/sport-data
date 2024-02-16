package com.drybro.sportdata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.drybro.sportdata.model.Tournament;
import com.drybro.sportdata.model.TournamentTeam;
import com.drybro.sportdata.model.constants.Round;

@Repository
public interface TournamentTeamRepository extends CrudRepository<TournamentTeam, Long> {

	List<TournamentTeam> findTournamentTeamByTournament( Tournament tournament);

	List<TournamentTeam> findTournamentTeamByTournamentOrderByGroupPointsDescGroupScoreDifferenceDescGroupScoreDifferenceDesc(Tournament tournament);

	List<TournamentTeam> findTournamentTeamByTournamentAndEliminated(Tournament tournament, Boolean eliminated);

	List<TournamentTeam> findTournamentTeamByTournamentAndRound(Tournament tournament, Round round);

	List<TournamentTeam> findTournamentTeamByTournamentAndAndTournamentGroupOrderByGroupPointsDescGroupScoreDifferenceDescGroupScoreDifferenceDesc(Tournament tournament, String group);

}
