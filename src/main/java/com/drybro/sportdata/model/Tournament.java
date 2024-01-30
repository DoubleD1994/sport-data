package com.drybro.sportdata.model;

import com.drybro.sportdata.model.constants.Format;
import com.drybro.sportdata.model.constants.Sport;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@Column(name = "id")
	private Long id;

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
	@Column(name = "no_of_rounds")
	private Integer numberOfRounds;

	@NotNull
	@Column(name = "no_of_qualifying_teams")
	private Integer numberOfQualifyingTeams;

	@NotNull
	@Column(name = "sport")
	private Sport sport;

}
