package src;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.algolia.search.*;
import com.algolia.search.models.settings.IndexSettings;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AlgoliaApplication {

	public static void main(String[] args) {

		java.security.Security.setProperty("networkaddress.cache.ttl", "60");
		SearchClient client = DefaultSearchClient.create("A3J8W241AO", "06ddc5a4aa410a6a4904d81608612d46");
		SearchIndex<Bordeaux> index = client.initIndex("Bordeaux", Bordeaux.class);
		//replica indices for custom sorting
		SearchIndex<Bordeaux> index_price_asc = client.initIndex("Bordeaux_price_asc", Bordeaux.class);
		SearchIndex<Bordeaux> index_price_desc = client.initIndex("Bordeaux_price_desc", Bordeaux.class);
		SearchIndex<Bordeaux> index_quality_asc = client.initIndex("Bordeaux_quality_asc", Bordeaux.class);
		SearchIndex<Bordeaux> index_quality_desc = client.initIndex("Bordeaux_quality_desc", Bordeaux.class);
		
		IndexSettings settings = new IndexSettings();
		IndexSettings settings_price_asc = new IndexSettings();
		settings_price_asc.setRanking(Arrays.asList("asc(price)"));
		IndexSettings settings_price_desc = new IndexSettings();
		settings_price_desc.setRanking(Arrays.asList("desc(price)"));
		IndexSettings settings_quality_asc = new IndexSettings();
		settings_quality_asc.setRanking(Arrays.asList("asc(quality)"));
		IndexSettings settings_quality_desc = new IndexSettings();
		settings_quality_desc.setRanking(Arrays.asList("desc(quality)"));
		
		index_price_asc.setSettings(settings_price_asc);
		index_price_desc.setSettings(settings_price_desc);
		index_quality_asc.setSettings(settings_quality_asc);
		index_quality_desc.setSettings(settings_quality_desc);
		
		ArrayList<String> sortIndices = new ArrayList<>();
		sortIndices.add("Bordeaux_price_asc");
		sortIndices.add("Bordeaux_price_desc");
		sortIndices.add("Bordeaux_quality_asc");
		sortIndices.add("Bordeaux_quality_desc");
		
		settings.setReplicas(sortIndices);
		settings.setSearchableAttributes(Arrays.asList("name", "type", "year", "domain"));
	    settings.setAttributesForFaceting(Arrays.asList("year", "price", "type", "quantity"));
	    //make sure to forward the settings to replicas
		index.setSettings(settings, true);
		
		indexJSON(index);


	}
	
	private static void indexJSON(SearchIndex<Bordeaux> index){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("resources/bordeaux.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray bordeauxList = new JSONArray(obj.toString());
            //System.out.println("bordeauxList = " + bordeauxList);
            
            ObjectMapper objectMapper = new ObjectMapper();
            
            System.out.println("bordeauxList.length() = " + bordeauxList.length());
            for(int i = 0; i < bordeauxList.length(); i++){
            	Bordeaux bordeaux = objectMapper.readValue(bordeauxList.get(i).toString(), Bordeaux.class); 
            	index.saveObject(bordeaux, true);
            }
            
            System.out.println("Done indexing!");
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
			e.printStackTrace();
		}
	}

}

