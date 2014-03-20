/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.entity;

import com.sun.xml.ws.rx.rm.protocol.wsrm200502.AcceptType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "PERSON")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.email = :id"),
    @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name"),
    @NamedQuery(name = "Person.findByRole", query = "SELECT p FROM Person p WHERE p.role = :role")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NAME")
    @XmlElement(required=true) 
    private String name;
    
//    @Basic(optional = false)
//    @Size(min = 1, max = 10)
    @Column(name = "PICTURE")
    @XmlElement(required=true) 
    private String picture;
    
//    @Basic(optional = false)
//    @Size(min = 1, max = 10)
    @Column(name = "ROLE")
    @XmlElement(required=true) 
    private String role;
  
    @Column(name ="DESCRIPTION")
    @XmlElement(required=true) 
    private String description;

    @ManyToMany(mappedBy = "participants")
    private Collection<Discussion> discussions;

   
    @Id
    @Basic(optional = false)
    @Size(min = 1, max = 40)
    @Column(name = "EMAIL")
    @XmlElement(required=true) 
    private String email;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "PERSON_GROUP",
            joinColumns = {
            @JoinColumn(name = "PERSON_ID", referencedColumnName = "EMAIL")},
            inverseJoinColumns = {
            @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")})
    private Collection<Group> groups;

    public Collection<Group> getGroups() {
        return groups;
    }

    public void setGroups(Collection<Group> groups) {
        this.groups = groups;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
     public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person() {
        groups = new ArrayList<Group>();
    }

//    public Person(Integer id) {
//        this.id = id;
//    }

    public Person(Integer id, String name, String role) {
//        this.id = id;
        this.name = name;
        this.picture = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Collection<Discussion> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(Collection<Discussion> discussions) {
        this.discussions = discussions;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.email);
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
        final Person other = (Person) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return email ;
    }



    

}
