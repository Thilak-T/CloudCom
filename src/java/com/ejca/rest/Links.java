/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejca.rest;

import java.net.URI;

/**
 *
 * @author A0107595X
 */
class Links {
    private URI uri;

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
    
    public Links(){
        
    }
    
    public Links(URI u){
        uri = u;
    }
    
    
}
