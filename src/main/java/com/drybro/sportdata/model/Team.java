package com.drybro.sportdata.model;

import java.util.List;

import com.drybro.sportdata.model.constants.Sport;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nullable;
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
import lombok.RequiredArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "team_id")
	private Long teamId;

	@NotNull
	@Column(name = "team_name")
	private String teamName;

	@NotNull
	@Column(name = "sport")
	private Sport sport;

	@Nullable
	@Column(name = "logo_path")
	private String logoPath;

	@JsonIgnore
	@OneToMany(mappedBy = "team")
	private List<TournamentTeam> tournamentTeams;
}
