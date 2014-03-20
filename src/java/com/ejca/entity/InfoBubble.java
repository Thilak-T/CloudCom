/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramesh Kumar
 */
@Entity
//@DiscriminatorValue("T")
@Table(name = "INFO_BUBBLE")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@NamedQueries({
    @NamedQuery(name = "InfoBubble.findAll", query = "SELECT i FROM InfoBubble i"),
//    @NamedQuery(name = "InfoBubble.findByCommentId", query = "SELECT i FROM InfoBubble i WHERE i.comment.id = :commentId"),
    @NamedQuery(name = "InfoBubble.findByMessage", query = "SELECT i FROM InfoBubble i WHERE i.message = :message")})
public class InfoBubble extends Bubble implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    //@NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MESSAGE")
    private String message;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "INFO")
    @XmlElement(required=true) 
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    public InfoBubble() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.message);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InfoBubble other = (InfoBubble) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InfoBubble{" + "message=" + message + '}';
    }

   
    
}

