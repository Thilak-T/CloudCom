/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejca.util;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author mido
 */
@ApplicationScoped
@Named("config")
public class AppConfig {
    
    public AppConfig() {
        MEDIALoc = "F:\\E-Java\\CA Project\\FileStore\\";
    }
      
    private  String MEDIALoc;
    
    public String getMEDIALoc() {
        return MEDIALoc;
    }
}
