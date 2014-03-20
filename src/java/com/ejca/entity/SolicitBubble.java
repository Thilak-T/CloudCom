/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Nithya
 */
@Entity
@Table(name = "SOLICIT_BUBBLE")
@XmlAccessorType(XmlAccessType.NONE)
@PrimaryKeyJoinColumn(name = "ID")
public class SolicitBubble extends Bubble implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "MEETING_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @XmlElement(required=true) 
    private Date meetingTime;
    
    @Basic(optional = false)
    @Column(name = "MEETING_INFO")
    @XmlElement(required=true) 
    private String meetingInfo;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name= "SOLICIT_ID", referencedColumnName = "ID")
    @XmlElement(required=true) 
    private ArrayList<SolicitResponse> solicitResponses;

    public Date getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(Date meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getMeetingInfo() {
        return meetingInfo;
    }

    public void setMeetingInfo(String meetingInfo) {
        this.meetingInfo = meetingInfo;
    }

    public ArrayList<SolicitResponse> getSolicitResponses() {
        return solicitResponses;
    }

    public void setSolicitResponses(ArrayList<SolicitResponse> solicitResponses) {
        this.solicitResponses = solicitResponses;
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
        if (!(object instanceof SolicitBubble)) {
            return false;
        }
        SolicitBubble other = (SolicitBubble) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejca.entities.SolicitBubble[ id=" + id + " ]";
    }
    
}
