/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

import com.example.dto.Campaign;
import com.example.service.ProcessAd;
import com.example.util.JsonToJavaConverter;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.Context;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

import java.util.logging.Level;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;





/**
 *
 * @author MadhuryaR
 */

@RestController
@RequestMapping(ContractController.BASE_URI)
public class ContractController {
    
    
    @Context HttpServletRequest req=null;
    @Context HttpServletResponse res=null;
    
    
  public static final String BASE_URI="/ad";
  static final Logger      log     = Logger.getLogger(ContractController.class.getName());
      

    @GET
    @Path("/ad/{partner_id: [a-zA-Z][a-zA-Z_0-9]*}")
    @Consumes(
    {
            MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAd(@PathParam("partner_id") String partnersId)
    {
        
        JsonToJavaConverter converter = new JsonToJavaConverter();
        Campaign userdata = null;
        ProcessAd process = new ProcessAd();
       
        String myString = "";
        Response response = null;
       

        try
        {

         
            JSONArray json = new JSONArray();
          
            
            json = process.getCookies(req, partnersId);
            myString = json.toString();

          

            response = Response.ok(myString).build();

        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            // if(log.isInfoEnabled())
            log.info("Exception readDirectoyRecordsInJson - " + e.getMessage());
        }
        log.info("Method readDirectoyRecordsInJson() End");
        return response;
    }
    
    
    @POST
    @Path("/ad")
    @Consumes(
    {
            MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON
    })
   
    @Produces(MediaType.APPLICATION_JSON)

    public Response postAd(@QueryParam("postAd") String incomingData)
    {

        if ( log.isDebugEnabled() )
        {
            log.debug("incomingData -inside postAd " + incomingData);
        }

        JsonToJavaConverter converter = new JsonToJavaConverter();

        JSONArray json = new JSONArray();
        Campaign userdata = null;
        String partnersId="" ;
        
        try {
            userdata = (Campaign) converter.fromJson(incomingData);
        } catch (JsonMappingException ex) {
            java.util.logging.Logger.getLogger(ContractController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ContractController.class.getName()).log(Level.SEVERE, null, ex);
        }
            partnersId = userdata.getPartner_id();
           

       
       

        if ( log.isDebugEnabled() )
        {
            log.debug("Java Object created from JSON String update ");
            log.debug("JSON String : " + incomingData);
            log.debug("Java Object : " + userdata);
           }

        boolean isValiduid = this.checkPartnersId(partnersId);// validate Partners id

        if ( isValiduid )
        {

            log.debug("Hitting ProcessAd if ProcessID is valid");
            
            ProcessAd process = new ProcessAd();
            process.setCookie(req,res,userdata);
            JSONObject errCode = new JSONObject();
            try {
                errCode.put("Status", "Success");
                json.put(errCode);
            } catch (JSONException ex) {
                java.util.logging.Logger.getLogger(ContractController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }
        else
        {

            try
            {
                JSONObject errCode = new JSONObject();
                errCode.put("Status", "PartnersId dosent exist or already present");
                json.put(errCode);
            }
            catch (JSONException e)
            {
                // TODO Auto-generated catch block
                if ( log.isInfoEnabled() ) log.info("JSONException - " + e.getMessage());
            }
        }

        return Response.status(200).entity(json.toString()).build();

    }

    private boolean checkPartnersId(String primaryKey) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
        
        //implement logic of coparing request cookie with the PartnersId and validation to have only alphanumeric chars;
    }

    

}