package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public void getJsonDataToHmap() throws IOException {
		
		File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		String jsonContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		
		
		ObjectMapper mapper = new ObjectMapper();
		
    
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});

        for(int i=0; i<data.size();i++) {
        	
        	HashMap<String, String> map = data.get(i);
        }
	}
	
		

}
