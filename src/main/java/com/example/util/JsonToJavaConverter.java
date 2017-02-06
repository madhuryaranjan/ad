package com.example.util;



import com.example.dto.Campaign;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;



public class JsonToJavaConverter {

	static Logger log = Logger.getLogger(
			JsonToJavaConverter.class.getName());
  
 
	
    public Object fromJson(String json) throws JsonParseException
                                               , JsonMappingException, IOException{

           
    	Campaign user = new ObjectMapper().readValue(json, Campaign.class);
    	
        if(log.isDebugEnabled()){     	
    		log.debug("Java Object created from JSON String ");
    		log.debug("JSON String : " + json);
    		log.debug("Java Object : " + user);
        }
            return user;
    }  
    
}

