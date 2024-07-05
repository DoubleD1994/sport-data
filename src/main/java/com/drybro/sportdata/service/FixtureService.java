package com.drybro.sportdata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.drybro.sportdata.model.Fixture;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Validated
@Service
@AllArgsConstructor
@Slf4j
public class FixtureService {

	private final TournamentService tournamentService;

	private final TeamService teamService;

	public List<Fixture> getFixturesByTeam(final Long teamId) {

		return new ArrayList<>();
	}

	public List<Fixture> getFixturesByTournament(final Long tournamentId) {
		return new ArrayList<>();
	}

	public List<Fixture> getFixturesByTournamentAndTeam(final Long tournamentId, final Long teamId){
		return new ArrayList<>();
	}

}
