package com.drybro.sportdata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.drybro.sportdata.model.Team;
import com.drybro.sportdata.model.Tournament;
import com.drybro.sportdata.model.constants.Sport;

@Repository
public interface TournamentRepository extends CrudRepository<Tournament, Long> {

	List<Tournament> findTournamentsBySport( Sport sport );

}
