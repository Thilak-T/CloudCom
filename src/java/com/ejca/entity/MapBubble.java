/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rucha Kulkarni
 */
@Entity
@Table(name = "MAP_BUBBLE")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)

public class MapBubble extends Bubble implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column
    @NotNull
    @XmlElement(required=true) 
    private String address;
    
    @Basic(optional = false)
    @Column
    @XmlElement(required=true) 
    private String description;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MapBubble)) {
            return false;
        }
        MapBubble other = (MapBubble) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejca.entity.MapBubble[ id=" + id + " ]";
    }
    
}
