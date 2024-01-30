package com.drybro.sportdata.controller.data;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import com.drybro.sportdata.model.constants.Format;
import com.drybro.sportdata.model.constants.Round;
import com.drybro.sportdata.model.constants.Sport;

public interface DataController {

	String DATA_PATH = "/data";

	String ROUNDS_PATH = "/rounds";

	String FORMATS_PATH = "/formats";

	String SPORTS_PATH = "/sports";

	@GetMapping(ROUNDS_PATH)
	List<Round> getRounds();

	@GetMapping(FORMATS_PATH)
	List<Format> getFormats();

	@GetMapping(SPORTS_PATH)
	List<Sport> getSports();

}
