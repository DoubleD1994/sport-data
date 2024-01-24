package com.drybro.sportdata.model;

import com.drybro.sportdata.model.constants.Format;
import com.drybro.sportdata.model.constants.Sport;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@Nonnull
	@Column(name = "name", unique = true)
	private String name;

	@Nonnull
	@Column(name = "no_of_teams")
	private Integer numberOfTeams;

	@Nonnull
	@Column(name = "format")
	private Format format;

	@Nonnull
	@Column(name = "no_of_groups")
	private Integer numberOfGroups;

	@Nonnull
	@Column(name = "group_size")
	private Integer groupSize;

	@Nonnull
	@Column(name = "no_of_rounds")
	private Integer numberOfRounds;

	@Nonnull
	@Column(name = "no_of_qualifying_teams")
	private Integer numberOfQualifyingTeams;

	@Nonnull
	@Column(name = "sport")
	private Sport sport;

}
