package in.goals.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;

import in.goals.dbaccess.model.ApiResponseDAO;
import in.goals.dbaccess.model.ApiResponseDAO.HttpMethod;
import in.goals.dbaccess.repositories.ApiResponseRepository;

@Service
public class ApiResponseParser {

	@Autowired
	ApiResponseRepository apiResponseRepository;

	public void persistApiResponseTime(@NotNull String file)
			throws FileNotFoundException, UnsupportedEncodingException {

		FileInputStream is;
		is = new FileInputStream(new File(file));

		Reader r = new InputStreamReader(is, "UTF-8");
		Gson gson = new GsonBuilder().create();
		JsonStreamParser p = new JsonStreamParser(r);

		while (p.hasNext()) {
			JsonElement e = p.next();
			if (e.isJsonObject()) {
				Map m = gson.fromJson(e, Map.class);
				String message = (String) m.get("message");

				HttpMethod httpMethod = parseHttpMethod(message);
				if (httpMethod == null) {
					continue;
				}

				ApiResponseDAO apiResponse = null;

				String apiUrl = parseApiJsonMessage(message);
				if (apiUrl == null) {
					continue;
				}

				String noQueryParam = parseApiWithoutQueryParams(apiUrl);
				if (noQueryParam == null) {
					continue;
				}

				Double responseTime = parseResponseTime(message);

				apiResponse = apiResponseRepository.findByHttpMethodAndApiUrl(httpMethod, noQueryParam);
				if (apiResponse != null) {
					apiResponse.setCount(apiResponse.getCount() + 1);
					apiResponse.setTotalResponseTime(apiResponse.getTotalResponseTime() + responseTime);
				} else {
					apiResponse = new ApiResponseDAO();
					apiResponse.setHttpMethod(httpMethod);
					apiResponse.setApiUrl(noQueryParam);
					apiResponse.setCount(1);
					apiResponse.setTotalResponseTime(responseTime);
				}

				Double avgResponse = apiResponse.getTotalResponseTime() / apiResponse.getCount();
				apiResponse.setAvgResponseTime(avgResponse);
				apiResponseRepository.save(apiResponse);
			}
		}
	}

	private static HttpMethod parseHttpMethod(@NotNull String message) {
		if (message.startsWith("GET")) {
			return HttpMethod.GET;
		}
		if (message.startsWith("POST")) {
			return HttpMethod.POST;
		}
		if (message.startsWith("PUT")) {
			return HttpMethod.PUT;
		}
		if (message.startsWith("DELETE")) {
			return HttpMethod.DELETE;
		}
		return null;
	}

	private String parseApiJsonMessage(@NotNull String message) {
		if (message.startsWith("GET")) {
			message = message.substring(message.indexOf("GET") + 3).trim();
		} else if (message.startsWith("POST")) {
			message = message.substring(message.indexOf("POST") + 4).trim();
		}

		String pattern = "^.*(?=(-->>))";
		Pattern pt = Pattern.compile(pattern);
		Matcher match = pt.matcher(message);
		if (match.find()) {
			return match.group();
		}
		return null;
	}

	private String parseApiWithoutQueryParams(@NotNull String api) {
		if (api.indexOf('?') != -1) {
			return api.substring(0, api.indexOf('?'));
		}

		return api;
	}

	private Double parseResponseTime(@NotNull String line) {
		if (line.indexOf("-->>") != -1) {
			String secs = line.substring(line.indexOf("-->>") + 4, line.indexOf("seconds")).trim();
			return Double.valueOf(secs);
		}
		return null;
	}

}
