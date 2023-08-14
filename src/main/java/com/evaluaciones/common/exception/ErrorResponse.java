package com.evaluaciones.common.exception;

import java.util.ArrayList;
import java.util.HashMap;

public class ErrorResponse {

	private ArrayList<HashMap<String, String>> error = new ArrayList<>();

	public ArrayList<HashMap<String, String>> getError() {
		return error;
	}

	public void setError(String type, String code, String message) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("type", type);
		map.put("code", code);
		map.put("info", message);
		
		error.add(map);
	}
}
