package com.drybro.sportdata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.drybro.sportdata.model.Team;
import com.drybro.sportdata.model.constants.Sport;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

	List<Team> findTeamsBySport( Sport sport);

}
