package com.evaluaciones.ws.response;

import java.util.ArrayList;
import java.util.HashMap;

public class MetadataResponse {

	private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

	public ArrayList<HashMap<String, String>> getMetadata() {
		return metadata;
	}

	public void setMetadata(String type, String code, String message) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("type", type);
		map.put("code", code);
		map.put("info", message);
		
		metadata.add(map);
	}
}
