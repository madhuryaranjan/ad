/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dto;

import java.util.LinkedHashMap;

/**
 *
 * @author MadhuryaR
 */
public class Campaign {
    
    private String partner_id;
    private int duration;
    private String ad_content;
    private LinkedHashMap updatelist = new LinkedHashMap();

    public void setUpdatelist(LinkedHashMap updatelist) {
        this.updatelist = updatelist;
    }
    public LinkedHashMap getUpdatelist() {
        return updatelist;
    }
 
    public void setDuration(int duration) {
        this.duration = duration;
    }

   

    

    public int getDuration() {
        return duration;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public String getAd_content() {
        return ad_content;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public void setAd_content(String ad_content) {
        this.ad_content = ad_content;
    }



   
    
    
}
