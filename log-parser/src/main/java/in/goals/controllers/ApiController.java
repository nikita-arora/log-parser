package in.goals.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.goals.constants.GoalsUrl;
import in.goals.service.ApiResponseParser;
import in.goals.service.model.PersistApiResponse;

@RestController
@RequestMapping(GoalsUrl.API_URL)
public class ApiController {

	@Autowired
	ApiResponseParser apiResponseParser;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PersistApiResponse> persistApiResponse(@RequestParam String fileLocation) {
		PersistApiResponse resp = new PersistApiResponse();
		try {
			apiResponseParser.persistApiResponseTime(fileLocation);
			resp.setSuccess(true);
			return new ResponseEntity<PersistApiResponse>(resp, HttpStatus.OK);
		} catch (Exception e) {
			String error = e.getMessage();
			List<String> errors = new ArrayList<String>();
			errors.add(error);
			resp.setErrors(errors);
			resp.setSuccess(false);
			ResponseEntity.BodyBuilder resEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
			ResponseEntity<PersistApiResponse> response = resEntity.body(resp);
			return response;
		}
	}
}
