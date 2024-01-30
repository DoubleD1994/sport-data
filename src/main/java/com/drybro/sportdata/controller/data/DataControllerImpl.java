package com.drybro.sportdata.controller.data;

import static com.drybro.sportdata.controller.data.DataController.DATA_PATH;

import java.util.Arrays;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drybro.sportdata.model.constants.Format;
import com.drybro.sportdata.model.constants.Round;
import com.drybro.sportdata.model.constants.Sport;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Validated
@RestController
@RequestMapping(path = DATA_PATH, produces = "application/json")
@AllArgsConstructor
@Slf4j
public class DataControllerImpl implements DataController{

	@Override
	@GetMapping(ROUNDS_PATH)
	public List<Round> getRounds() {
		return Arrays.asList(Round.values());
	}

	@Override
	@GetMapping(FORMATS_PATH)
	public List<Format> getFormats() {
		return Arrays.asList( Format.values() );
	}

	@Override
	@GetMapping(SPORTS_PATH)
	public List<Sport> getSports() {
		return Arrays.asList( Sport.values() );
	}
}
