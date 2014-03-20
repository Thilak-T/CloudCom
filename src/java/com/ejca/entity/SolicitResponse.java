/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Nithya
 */
@Entity
@Table(name = "SOLICIT_RESPONSE")
@XmlAccessorType(XmlAccessType.NONE)
public class SolicitResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional=false)
    @Column(name = "ID")
    @XmlElement(required=true) 
    private Long id;
    
    @OneToOne(optional = false)
    @JoinColumn(name = "PERSON_ID")
    @XmlElement(required=true) 
    private Person person;
    
    @Basic(optional = false)
    @Column(name = "ATTEND_RESPONSE")
    @XmlElement(required=true) 
    private boolean attend;    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isAttend() {
        return attend;
    }

    public void setAttend(boolean attend) {
        this.attend = attend;
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
        if (!(object instanceof SolicitResponse)) {
            return false;
        }
        SolicitResponse other = (SolicitResponse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejca.entities.SolicitResponse[ id=" + id + " ]";
    }
    
}
