import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

public class ParseData {

	void apiData(String file, String pattern) throws ParseException {
		System.out.println("in" + file);
		String filePath = file;
		BufferedReader br;
		String line = "";
		Pattern p1 = Pattern.compile(pattern);
		try {
			FileInputStream is = new FileInputStream(filePath);
			br = new BufferedReader(new InputStreamReader(is));
			try {
				// read file line by line
				JSONArray apis = new JSONArray();
				int i = 0;
				MongoDBConnection mb = new MongoDBConnection();
				while ((line = br.readLine()) != null) {
					Matcher vMatch = p1.matcher(line);
					BasicDBObject api = new BasicDBObject();
					i++;
					// if (i >= 8000) {
					if (vMatch.find()) {
						System.out.println("insert: " + i);
						String[] split = p1.split(line);

						if (!split[0].equals("")) {
							api.put("id", split[0]);
						} else {
							api.put("id", null);
						}

						if (!split[1].equals("")) {
							api.put("title", split[1]);
						} else {
							api.put("title", null);
						}

						if (!split[2].equals("")) {
							api.put("summary", split[2]);
						} else {
							api.put("summary", null);
						}

						if (!split[3].equals("")) {
							float ratingValue = Float.parseFloat(split[3]);
							api.put("rating", ratingValue);
						} else {
							api.put("rating", null);
						}

						if (!split[4].equals("")) {
							api.put("name", split[4]);
						} else {
							api.put("name", null);
						}

						if (!split[5].equals("")) {
							api.put("label", split[5]);
						} else {
							api.put("label", null);
						}

						if (!split[6].equals("")) {
							api.put("author", split[6]);
						} else {
							api.put("author", null);
						}

						if (!split[7].equals("")) {
							api.put("description", split[7]);
						} else {
							api.put("description", null);
						}

						if (!split[8].equals("")) {
							int typeVal = Integer.parseInt(split[8]);
							api.put("type", typeVal);
						} else {
							api.put("type", null);
						}

						if (!split[9].equals("")) {
							api.put("downloads", split[9]);

						} else {
							api.put("downloads", null);

						}

						if (!split[10].equals("")) {

							int count = Integer.parseInt(split[10]);
							api.put("useCount", count);

						} else {

							api.put("useCount", null);
						}

						if (!split[11].equals("")) {
							api.put("sampleUrl", split[11]);

						} else {
							api.put("sampleUrl", null);

						}

						if (!split[12].equals("")) {
							api.put("downloadUrl", split[12]);

						} else {
							api.put("downloadUrl", null);

						}

						if (!split[13].equals("")) {
							SimpleDateFormat sFormat = new SimpleDateFormat(
									"yyyy-MM-dd'T'HH:mm:ss");
							java.util.Date parsed = (java.util.Date) sFormat
									.parse(split[13]);
							api.put("dateModified", parsed);
						} else {
							api.put("dateModified", null);
						}

						if (!split[14].equals("")) {
							api.put("remoteFeed", split[14]);

						} else {
							api.put("remoteFeed", null);

						}

						if (!split[15].equals("")) {

							int count = Integer.parseInt(split[15]);
							api.put("numComments", count);

						} else {

							api.put("numComments", null);
						}

						if (!split[16].equals("")) {
							api.put("commentsUrl", split[16]);

						} else {
							api.put("commentsUrl", null);

						}

						//to split by ###
						String linep = split[17];
						String[] tags = linep.split("\\#" + "\\#" + "\\#");
						BasicDBList tagsJ = new BasicDBList();
						JSONArray array = new JSONArray();
						for (String tag : tags) {
							array = new JSONArray();
							array.add(tag);
						}

						api.put("Tags", array);

						if (!split[18].equals("")) {
							api.put("category", split[18]);

						} else {
							api.put("category", null);

						}

						if (!split[19].equals("")) {
							api.put("protocols", split[19]);

						} else {
							api.put("protocols", null);

						}

						if (!split[20].equals("")) {
							api.put("serviceEndpoint", split[20]);

						} else {
							api.put("serviceEndpoint", null);

						}

						if (!split[21].equals("")) {
							api.put("version", split[21]);

						} else {
							api.put("version", null);

						}

						if (!split[22].equals("")) {
							api.put("wsdl", split[22]);

						} else {
							api.put("wsdl", null);
						}

						if (!split[23].equals("")) {
							api.put("data Format", split[23]);

						} else {
							api.put("data Format", null);
						}

						if (!split[24].equals("")) {
							api.put("apigroups", split[24]);

						} else {
							api.put("apigroups", null);
						}

						String example = split[25];
						JSONObject exampleObj = new JSONObject();
						BasicDBList array2 = new BasicDBList();
						String[] examples = linep.split("\\#" + "\\#" + "\\#");
						for (String ex : examples) {
							array2.add(ex);
						}
						api.put("example", array2);

						if (!split[26].equals("")) {
							api.put("clientInstall", split[26]);

						} else {
							api.put("clientInstall", null);
						}

						if (!split[27].equals("")) {
							api.put("authentication", split[27]);

						} else {
							api.put("authentication", null);
						}

						if (!split[28].equals("")) {
							api.put("ssl", split[28]);

						} else {
							api.put("ssl", null);
						}

						if (!split[29].equals("")) {
							api.put("readonly", split[29]);

						} else {
							api.put("readonly", null);

						}

						if (!split[30].equals("")) {
							api.put("VendorApiKits", split[30]);

						} else {
							api.put("VendorApiKits", null);
						}

						if (!split[31].equals("")) {
							api.put("CommunityApiKits", split[31]);

						} else {
							api.put("CommunityApiKits", null);
						}

						if (!split[32].equals("")) {
							api.put("blog", split[32]);

						} else {
							api.put("blog", null);
						}

						if (!split[33].equals("")) {
							api.put("forum", split[33]);

						} else {
							api.put("forum", null);
						}

						if (!split[34].equals("")) {
							api.put("support", split[34]);

						} else {
							api.put("support", null);
						}

						if (!split[35].equals("")) {
							api.put("accountReq", split[35]);

						} else {
							api.put("accountReq", null);
						}

						if (!split[36].equals("")) {
							api.put("commercial", split[36]);

						} else {
							api.put("commercial", null);
						}

						if (!split[37].equals("")) {
							api.put("provider", split[37]);

						} else {
							api.put("provider", null);
						}

						if (!split[38].equals("")) {
							api.put("managedBy", split[38]);

						} else {
							api.put("managedBy", null);
						}

						if (!split[39].equals("")) {
							api.put("nonCommercial", split[39]);

						} else {
							api.put("nonCommercial", null);
						}

						if (!split[40].equals("")) {
							api.put("dataLicensing", split[40]);

						} else {
							api.put("dataLicensing", null);
						}

						if (!split[41].equals("")) {
							api.put("fees", split[41]);

						} else {
							api.put("fees", null);
						}

						if (!split[42].equals("")) {
							api.put("limits", split[42]);

						} else {
							api.put("limits", null);
						}

						if (!split[43].equals("")) {
							api.put("terms", split[43]);

						} else {
							api.put("terms", null);
						}

						if (!split[44].equals("")) {
							api.put("company", split[44]);

						} else {
							api.put("company", null);

						}

						SimpleDateFormat simpleFormat = new SimpleDateFormat(
								"yyyy-MM-dd'T'HH:mm:ss");
						java.util.Date parsed = (java.util.Date) simpleFormat
								.parse(split[45]);
						api.put("updated", parsed);

					} else {
						System.out.println("no");
					}

					mb.insertInto(api);
					// }
					// break;
				}
				br.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No such File");
		}

	}

	public void mashupData(String file, String pattern) throws ParseException {
		System.out.println("in" + file);
		String filePath = file;
		BufferedReader br;
		String line = "";
		MongoDBConnection mb = new MongoDBConnection();
		Pattern p1 = Pattern.compile(pattern);
		try {
			FileInputStream is = new FileInputStream(filePath);
			br = new BufferedReader(new InputStreamReader(is));
			try {
				// read file line by line
				JSONArray apis = new JSONArray();
				int i = 0;
				while ((line = br.readLine()) != null) {
					Matcher vMatch = p1.matcher(line);
					BasicDBObject api = new BasicDBObject();
					i++;
					// if (i < 5000) {
					if (vMatch.find()) {
						System.out.println("insert: " + i);
						String[] split = p1.split(line);

						if (!split[0].equals("")) {
							api.put("id", split[0]);
						} else {
							api.put("id", null);
						}

						if (!split[1].equals("")) {
							api.put("title", split[1]);
						} else {
							api.put("title", null);
						}

						if (!split[2].equals("")) {
							api.put("summary", split[2]);
						} else {
							api.put("summary", null);
						}

						if (!split[3].equals("")) {
							float ratingValue = Float.parseFloat(split[3]);
							api.put("rating", ratingValue);
						} else {
							api.put("rating", null);
						}

						if (!split[4].equals("")) {
							api.put("name", split[4]);
						} else {
							api.put("name", null);
						}

						if (!split[5].equals("")) {
							api.put("label", split[5]);
						} else {
							api.put("label", null);
						}

						if (!split[6].equals("")) {
							api.put("author", split[6]);
						} else {
							api.put("author", null);
						}

						if (!split[7].equals("")) {
							api.put("description", split[7]);
						} else {
							api.put("description", null);
						}

						if (!split[8].equals("")) {
							int typeVal = Integer.parseInt(split[8]);
							api.put("type", typeVal);
						} else {
							api.put("type", null);
						}

						if (!split[9].equals("")) {

							api.put("downloads", split[9]);

						} else {
							api.put("downloads", null);

						}

						if (!split[10].equals("")) {

							int count = Integer.parseInt(split[10]);
							api.put("useCount", count);

						} else {

							api.put("useCount", null);
						}

						if (!split[11].equals("")) {
							api.put("sampleUrl", split[11]);

						} else {
							api.put("sampleUrl", null);

						}

						if (!split[12].equals("")) {
							SimpleDateFormat sFormat = new SimpleDateFormat(
									"yyyy-MM-dd'T'HH:mm:ss");
							java.util.Date parsed = (java.util.Date) sFormat
									.parse(split[12]);
							api.put("dateModified", parsed);
						} else {
							api.put("dateModified", null);
						}

						if (!split[13].equals("")) {
							int count = Integer.parseInt(split[13]);
							api.put("numComments", count);
						} else {
							api.put("numComments", null);
						}

						if (!split[14].equals("")) {
							api.put("commentsUrl", split[14]);

						} else {
							api.put("commentsUrl", null);

						}

						String linep = split[15];
						String[] tags = linep.split("\\#" + "\\#" + "\\#");
						BasicDBList tagsJ = new BasicDBList();
						JSONArray array = new JSONArray();
						for (String tag : tags) {

							array.add(tag);
						}

						api.put("tags", array);

						String apiM = split[16];
						//to split by ###
						String[] apisList = apiM.split("\\#" + "\\#" + "\\#");
						BasicDBList apisListObj = new BasicDBList();

						//to split by $$$
						for (String apiV : apisList) {
							String apiParts[] = apiV.split("\\$" + "\\$"
									+ "\\$");
							if (apiParts.length > 1) {
								// System.out.println(apiParts[0]);
								JSONObject apiData = new JSONObject();
								apiData.put("apiName", apiParts[0]);
								apiData.put("apiUrl", apiParts[1]);

								BasicDBObject Value = new BasicDBObject();
								Value.put("api", apiData);
								apisListObj.add(Value);
							}
						}

						api.put("Apis", apisListObj);
						SimpleDateFormat simpleFormat = new SimpleDateFormat(
								"yyyy-MM-dd'T'HH:mm:ss");
						java.util.Date parsed = (java.util.Date) simpleFormat
								.parse(split[17]);
						api.put("updated", parsed);

					} else {
						System.out.println("no");
					}

					mb.insertIntoMashup(api);
					// }
					System.out.println("bb" + api.toString());
					// break;
				}
				br.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No such File");
		}

	}

	public static void main(String[] args) {

		ParseData pd = new ParseData();
		try {
			//to split by $#$
			pd.apiData("api.txt", "\\$" + "\\#" + "\\$");
			pd.mashupData("mashup.txt", "\\$" + "\\#" + "\\$");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
