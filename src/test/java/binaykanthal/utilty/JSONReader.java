package binaykanthal.utilty;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONReader{
	
	public static List<HashMap<String, String>> getJsonDataMap() throws IOException{
		
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+ "/SeleniumFramework/src/test/java/binaykanthal/resources/purchaseOrder.json"), 
				StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){	
		});
		return data;
	}

}
