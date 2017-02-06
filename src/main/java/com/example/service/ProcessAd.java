/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.controller.ContractController;
import com.example.dto.Campaign;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author MadhuryaR
 */
public class ProcessAd {
    
    static final Logger      log     = Logger.getLogger(ContractController.class.getName());
      public JSONArray getCookies(HttpServletRequest req, String partnersId){
      
          JSONArray jsonResp = new JSONArray();
          Cookie cookies [] = req.getCookies();
          String userdata="";
          if (cookies != null)
		{
			
			for (int i = 0; i < cookies.length; i++) 
			{
				if(log.isDebugEnabled()) {
                                    try {
                                        log.debug("[ cookie-["+i+"]- "+cookies[i].getName()+" - "+URLDecoder.decode(cookies[i].getValue(), "UTF-8")+"]");
                                    } catch (UnsupportedEncodingException ex) {
                                        java.util.logging.Logger.getLogger(ProcessAd.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    if(cookies[i].getName().equals(partnersId)){
                                        
                                        try {
                                            
                                            try {
                                                userdata= URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                                            } catch (UnsupportedEncodingException ex) {
                                                java.util.logging.Logger.getLogger(ProcessAd.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            
                                            JSONObject obj = new JSONObject();
                                            obj.put(partnersId, userdata);
                                            jsonResp.put(obj);
                                        } catch (JSONException ex) {
                                            java.util.logging.Logger.getLogger(ProcessAd.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
					
				
				
				}
			
			
                        }
                }
				
    
       
        
    
    return jsonResp;
    }

     public void setCookie(HttpServletRequest req, HttpServletResponse res, Campaign userdata) {
         //To change body of generated methods, choose Tools | Templates.
         
         String cookieName=userdata.getPartner_id();
         String cookieDomain =req.getServerName();
       //  res.addHeader("Set-Cookie", "PartnersId=; path=/; domain=.ge.com;Expires=Thu, 01-Jan-1970 00:00:10 GMT");
       
       //how to get the data;
       
         res.addHeader("Set-Cookie",cookieName+"="+userdata.toString()+"; path=/; domain="+cookieDomain);
         
         
    }

    

    
    
}
