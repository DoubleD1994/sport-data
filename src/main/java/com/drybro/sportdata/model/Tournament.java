package com.drybro.sportdata.model;

import java.util.List;

import com.drybro.sportdata.model.constants.Format;
import com.drybro.sportdata.model.constants.Sport;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tournament {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "tournament_id")
	private Long tournamentId;

	@NotNull
	@Column(name = "name", unique = true)
	private String name;

	@NotNull
	@Column(name = "no_of_teams")
	private Integer numberOfTeams;

	@NotNull
	@Column(name = "format")
	private Format format;

	@NotNull
	@Column(name = "no_of_groups")
	private Integer numberOfGroups;

	@NotNull
	@Column(name = "group_size")
	private Integer groupSize;

	@NotNull
	@Column(name = "no_of_knockout_rounds")
	private Integer numberOfKnockoutRounds;

	@NotNull
	@Column(name = "no_of_qualifying_teams_from_group")
	private Integer numberOfQualifyingTeamsFromGroup;

	@NotNull
	@Column(name = "sport")
	private Sport sport;

	@JsonIgnore
	@OneToMany(mappedBy = "tournament")
	private List<TournamentTeam> tournamentTeams;

}
