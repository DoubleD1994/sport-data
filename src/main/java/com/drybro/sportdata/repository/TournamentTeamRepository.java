package com.drybro.sportdata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.drybro.sportdata.model.TournamentTeam;

@Repository
public interface TournamentTeamRepository extends CrudRepository<TournamentTeam, Long> {

	List<TournamentTeam> findTournamentTeamByTournamentId(Long tournamentId);

}
