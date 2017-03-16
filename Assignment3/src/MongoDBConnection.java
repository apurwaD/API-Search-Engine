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

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.swing.text.Document;

import org.json.simple.JSONObject;

public class MongoDBConnection {
	MongoClient mongoClient;
	DB db;

	public MongoDBConnection() {
		try {
			mongoClient = new MongoClient("localhost", 27017);
			db = mongoClient.getDB("test");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * insert api records into mongodb
	 */
	public void insertInto(BasicDBObject api) {
		try {

			DBCollection coll = db.createCollection("apis", null);
			coll.insert(api);

		} catch (Exception e) {

		}
	}

	/*
	 * insert mashup records into mongodb
	 */
	public void insertIntoMashup(BasicDBObject api) {
		try {

			DBCollection coll = db.createCollection("mashup", null);
			coll.insert(api);
		} catch (Exception e) {

		}
	}

}
