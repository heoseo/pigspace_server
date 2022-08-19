package com.pigplace.common.vo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Status{
	//code가 key인 상태값
	public static final Map<Integer, String> StatusCode= 
			Collections.unmodifiableMap(new HashMap<Integer, String>(){
				private static final long serialVersionUID = 7835287845816788595L;
			{
				put(200, "OK");
				put(201, "CREATED");
				put(204, "NO_CONTENT");
				put(400, "BAD_REQUEST");
				put(401, "UNAUTHORIZED");
				put(403, "FORBIDDEN");
				put(404, "NOT_FOUND");
				put(500, "INTERNAL_SERVER_ERROR");
				put(503, "SERVICE_UNAVAILABLE");
				put(600, "DB_ERROR");

			}});
	
	//message가 key인 상태값
	public static final Map<String, Integer> StatusMessage  = 
		    Collections.unmodifiableMap(new HashMap<String, Integer>() {
				private static final long serialVersionUID = 3922431394814449884L;
			{ 
		    	put("OK", 200);
		    	put("CREATED", 201);
		    	put("NO_CONTENT", 204);
		    	put("BAD_REQUEST",  400);
		    	put("UNAUTHORIZED", 401);
		    	put("FORBIDDEN", 403);
		    	put("NOT_FOUND", 404);
		    	put("INTERNAL_SERVER_ERROR", 500);
		    	put("SERVICE_UNAVAILABLE", 503);
		    	put("DB_ERROR", 600);
		    }});
}