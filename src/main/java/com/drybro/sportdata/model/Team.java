package com.drybro.sportdata.model;

import com.drybro.sportdata.model.constants.Sport;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
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
public class Team {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "team_name")
	private String teamName;

	@NotNull
	@Column(name = "sport")
	private Sport sport;

	@Nullable
	@Column(name = "logo_path")
	private String logoPath;

}
