package com.drybro.sportdata.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.drybro.sportdata.model.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {}
