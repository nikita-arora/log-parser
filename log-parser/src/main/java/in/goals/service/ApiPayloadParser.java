package in.goals.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;

import in.goals.dbaccess.model.PayloadDAO;
import in.goals.dbaccess.repositories.PayloadRepository;

@Service
public class ApiPayloadParser {

	@Autowired
	PayloadRepository payloadRepository;

	public void persistPayload(@NotNull String fileName) throws IOException, JSONException {
		FileInputStream is;
		is = new FileInputStream(new File(fileName));

		Reader r = new InputStreamReader(is, "UTF-8");
		Gson gson = new GsonBuilder().create();
		JsonStreamParser p = new JsonStreamParser(r);

		while (p.hasNext()) {
			JsonElement e = p.next();
			if (e.isJsonObject()) {
				Map m = gson.fromJson(e, Map.class);
				String message = (String) m.get("message");
				List<PayloadDAO> payloadList = parsePayloadList(message);
				if (payloadList == null || payloadList.isEmpty()) {
					continue;
				}
				for (PayloadDAO shipment : payloadList) {
					PayloadDAO existing = payloadRepository.findByWaybill(shipment.getWaybill());
					if (existing == null) {
						payloadRepository.save(shipment);
					}
				}
			}
		}
	}

	private List<PayloadDAO> parsePayloadList(String message) throws JSONException, IOException {
		List<PayloadDAO> shipments = new ArrayList<>();
		if (message.contains("request to api of create Package for awb number")) {
			String json = message.substring(message.lastIndexOf('=') + 1);
			JSONObject jsonObj = new JSONObject(json);
			String array = String.valueOf(jsonObj.get("shipments"));

			ObjectMapper mapper = new ObjectMapper();
			shipments = mapper.readValue(array, new TypeReference<ArrayList<PayloadDAO>>() {
			});
		}
		return shipments;
	}

}
