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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thilak
 */
@Entity
@Table(name = "SURVEY_OPTIONS")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@NamedQueries({
    @NamedQuery(name = "SurveyOptions.findAll", query = "SELECT s FROM SurveyOptions s"),
    @NamedQuery(name = "SurveyOptions.findById", query = "SELECT s FROM SurveyOptions s WHERE s.id = :id"),
    @NamedQuery(name = "SurveyOptions.findByChoice", query = "SELECT s FROM SurveyOptions s WHERE s.choice = :choice"),
    @NamedQuery(name = "SurveyOptions.findBySelectedCount", query = "SELECT s FROM SurveyOptions s WHERE s.selectedCount = :selectedCount")})
public class SurveyOptions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID")
    @XmlElement(required=true) 
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CHOICE")
    @XmlElement(required=true) 
    private String choice;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "SELECTED_COUNT")
    @XmlElement(required=true) 
    private int selectedCount;

    public SurveyOptions() {
    }

    public SurveyOptions(Integer id) {
        this.id = id;
    }

    public SurveyOptions(Integer id, String choice, int selectedCount) {
        this.id = id;
        this.choice = choice;
        this.selectedCount = selectedCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public int getSelectedCount() {
        return selectedCount;
    }

    public void setSelectedCount(int selectedCount) {
        this.selectedCount = selectedCount;
    }
    
    public void incrementSelectedCount() {
        this.selectedCount+=1;
    }

    public void decrementSelectedCount() {
        this.selectedCount-=1;
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
        if (!(object instanceof SurveyOptions)) {
            return false;
        }
        SurveyOptions other = (SurveyOptions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SurveyOptions{" + "id=" + id + ", choice=" + choice + ", selectedCount=" + selectedCount + '}';
    }
    
}
