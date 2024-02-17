package com.drybro.sportdata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.drybro.sportdata.model.Tournament;
import com.drybro.sportdata.model.TournamentTeam;
import com.drybro.sportdata.model.constants.Round;

@Repository
public interface TournamentTeamRepository extends CrudRepository<TournamentTeam, Long> {

	List<TournamentTeam> findTournamentTeamsByTournament( Tournament tournament);

	List<TournamentTeam> findTournamentTeamsByTournamentOrderByGroupPointsDescGroupScoreDifferenceDescGroupScoreDifferenceDesc(Tournament tournament);

	List<TournamentTeam> findTournamentTeamsByTournamentAndEliminated(Tournament tournament, Boolean eliminated);

	List<TournamentTeam> findTournamentTeamsByTournamentAndTeamRound(Tournament tournament, Round round);

	List<TournamentTeam> findTournamentTeamsByTournamentAndTournamentGroupOrderByGroupPointsDescGroupScoreDifferenceDescGroupScoreDifferenceDesc(Tournament tournament, String group);

}
