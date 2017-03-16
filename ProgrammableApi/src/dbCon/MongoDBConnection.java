package dbCon;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import com.sun.org.apache.xml.internal.security.encryption.AgreementMethod;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;

public class MongoDBConnection {
	static MongoClient mongoClient;
	static DB db;

	public MongoDBConnection() {
		try {
			if (mongoClient == null) {
				mongoClient = new MongoClient("localhost", 27017);
				db = mongoClient.getDB("test");
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertInto(BasicDBObject api) {
		try {
			DBCollection coll = db.createCollection("apis", null);
			coll.insert(api);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public void insertIntoMashup(BasicDBObject api) {
		try {

			DBCollection coll = db.createCollection("mashup", null);
			coll.insert(api);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/*
	 * search records by category
	 * searchValue: value ti be searched 
	 * type: apis or mashup
	 */
	public HashMap<String, String> searchbyCategory(String searchValue,
			String type) {
		System.out.println("in " + type);
		HashMap<String, String> list = new HashMap<String, String>();
		DBCollection coll = db.getCollection(type);
		BasicDBObject regex = new BasicDBObject();
		regex.put("$regex",
				Pattern.compile(searchValue, Pattern.CASE_INSENSITIVE));
		BasicDBObject obj = new BasicDBObject();
		obj.put("category", regex);
		System.out.println(obj.toString());
		for (DBObject document : coll.find(obj)) {
			String id = (String) document.get("id");
			String name = (String) document.get("name");
			/*System.out.println(id);
			System.out.println(document.get("category"));*/
			list.put(id, name);
		}
		return list;
	}

	/*
	 * search records by protocol
	 * searchValue: value ti be searched 
	 * type: apis or mashup
	 */
	public HashMap<String, String> searchbyProtocol(String searchValue,
			String type) {
		HashMap<String, String> list = new HashMap<String, String>();
		DBCollection coll = db.getCollection(type);
		BasicDBObject regex = new BasicDBObject();
		regex.put("$regex",
				Pattern.compile(searchValue, Pattern.CASE_INSENSITIVE));
		BasicDBObject obj = new BasicDBObject();
		obj.put("protocols", regex);
		System.out.println(obj.toString());
		for (DBObject document : coll.find(obj)) {
			String id = (String) document.get("id");
			String name = (String) document.get("name");
			/*System.out.println(id);
			System.out.println(document.get("protocols"));*/
			list.put(id, name);

		}
		return list;
	}

	/*
	 * search records by rating
	 * searchValue: value ti be searched 
	 * type: apis or mashup
	 * compare: less than or equql to or greater than
	 */
	public HashMap<String, String> searchbyRating(String searchValue,
			String type, String compare) {
		float ratingValue = Float.parseFloat(searchValue);
		System.out.println("innnnnnnnn" + compare);
		HashMap<String, String> list = new HashMap<String, String>();
		DBCollection coll = db.getCollection(type);
		BasicDBObject obj = new BasicDBObject();
		BasicDBObject comapre = new BasicDBObject();
		//less than greater than equal to 
		if (compare.equals("less")) {
			comapre.put("$lt", ratingValue);
			obj.put("rating", comapre);
		} else {
			if (compare.equals("great")) {
				comapre.put("$gt", ratingValue);
				obj.put("rating", comapre);
			} else {
				obj.put("rating", ratingValue);
			}
		}
		System.out.println(obj.toString());
		for (DBObject document : coll.find(obj)) {
			String id = (String) document.get("id");
			String name = (String) document.get("name");
			// System.out.println(id);
			// System.out.println(document.get("rating"));
			//System.out.println(document.get("rating"));
			list.put(id, name);

		}
		return list;
	}

	/*
	 * search records by tags
	 * searchValue: value ti be searched 
	 * type: apis or mashup
	 */
	public HashMap<String, String> searchbyTags(String searchValue, String type) {
		HashMap<String, String> list = new HashMap<String, String>();
		DBCollection coll = db.getCollection(type);
		BasicDBObject obj = new BasicDBObject();
		BasicDBList objL = new BasicDBList();
		objL.add(Pattern.compile(searchValue, Pattern.CASE_INSENSITIVE));
		BasicDBObject objtags = new BasicDBObject();
		objtags.put("$in", objL);
		if (type.equals("apis")) {
			obj.put("Tags", objtags);
		} else {
			obj.put("tags", objtags);
		}
		System.out.println(obj.toString());
		for (DBObject document : coll.find(obj)) {
			String id = (String) document.get("id");
			String name = (String) document.get("name");
			// System.out.println(id);
			if (type.equals("apis")) {
				//System.out.println(document.get("Tags"));
			} else {
				//System.out.println(document.get("tags"));
			}
			list.put(id, name);

		}
		return list;
	}

	/*
	 * search records by upadted year
	 * searchValue: value ti be searched 
	 * type: apis or mashup
	 */
	public HashMap<String, String> searchbyUpdatedYear(String searchValue,
			String type) {
		int yearValue = Integer.parseInt(searchValue);
		DBCollection coll = db.getCollection(type);
		// db.apis.aggregate({$project: {name: 1, id:2, year: {$year:
		// '$updated'}}},{$match:{ year: 2012}});
		BasicDBObject project = new BasicDBObject();
		BasicDBObject nameid = new BasicDBObject();
		BasicDBObject id = new BasicDBObject();
		BasicDBObject year = new BasicDBObject();
		BasicDBObject nYear = new BasicDBObject();
		BasicDBObject match = new BasicDBObject();
		BasicDBObject newyear = new BasicDBObject();
		match.put("year", yearValue);
		year.put("$year", "$updated");
		newyear.put("year", year);
		nameid.put("name", 1);
		nameid.put("id", 2);
		BasicDBObject projectN = new BasicDBObject();
		projectN.put("name", 1);
		projectN.put("id", 2);
		projectN.put("year", year);
		BasicDBObject aggregate1 = new BasicDBObject();
		aggregate1.put("$project", projectN);
		BasicDBObject aggregate2 = new BasicDBObject();
		aggregate2.put("$match", match);
		System.out.println(aggregate1);
		System.out.println(aggregate2);
		System.out.println(aggregate1.toString());
		HashMap<String, String> list = new HashMap<String, String>();
		BasicDBList tryB = new BasicDBList();
		tryB.add(aggregate1);
		tryB.add(aggregate2);
		System.out.println(tryB);
		// coll.f

		AggregationOutput output = coll.aggregate(aggregate1, aggregate2);
		System.out.println(output.getCommandResult());
		System.out.println(aggregate1.toString()+"" +aggregate2.toString());
		for (final DBObject result : output.results()) {
			String id2 = (String) result.get("id");
			String name = (String) result.get("name");
			//System.out.println(id2);
			/*System.out.println((Integer) result.get("year"));*/
			list.put(id2, name);

		}

		System.out.println("size" + list.size());
		return list;
	}

	/*
	 * search records by used api
	 * searchValue: value ti be searched 
	 * type: apis or mashup
	 */
	public HashMap<String, String> searchbyUsedApis(String searchValue,
			String type) {
		HashMap<String, String> list = new HashMap<String, String>();
		DBCollection coll = db.getCollection(type);
		BasicDBObject obj = new BasicDBObject();
		obj.put("Apis.api.apiName",
				Pattern.compile(searchValue, Pattern.CASE_INSENSITIVE));
		System.out.println(obj.toString());
		for (DBObject document : coll.find(obj)) {
			String id = (String) document.get("id");
			String name = (String) document.get("name");
			/*System.out.println(document.get("Apis"));*/
			list.put(id, name);

		}
		return list;
	}

	/*
	 * search records by key word 
	 * searchValue: value ti be searched 
	 * type: apis or mashup
	 */
	public HashMap<String, String> searchKeyword(String keyword, String type) {

		//split keyword by space and put into double quotes
		String[] splitKeyword = keyword.split(" ");
		String newString = "";
		for (String key : splitKeyword) {
			newString += "\"" + key + "\"";
		}
		System.out.println(newString);

		DBCollection coll = db.getCollection(type);
		BasicDBObject regex = new BasicDBObject();
		regex.put("$search", newString);

		BasicDBObject summary = new BasicDBObject();
		summary.put("$text", regex);
		System.out.println(summary.toString());
		HashMap<String, String> list = new HashMap<String, String>();
		BasicDBObject text = new BasicDBObject();
		text.put("title", "text");
		text.put("summary", "text");
		text.put("description", "text");
		
		//create index on above fields i.e title, summary, description
		coll.createIndex(text);
		System.out.println(summary.toString());
		for (DBObject document : coll.find(summary)) {
			String id = (String) document.get("id");
			String name = (String) document.get("name");
			System.out.println(id);
			/*System.out.println("titel: " + document.get("title"));
			System.out.println("summary: " + document.get("summary"));
			System.out.println("description" + document.get("description"));*/
			list.put(id, name);
		}
		return list;
	}

}
