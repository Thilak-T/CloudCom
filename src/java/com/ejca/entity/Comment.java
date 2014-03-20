/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thilak
 */
@Entity
@Table(name = "COMMENT")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findById", query = "SELECT c FROM Comment c WHERE c.id = :id"),
    @NamedQuery(name = "Comment.findByEditable", query = "SELECT c FROM Comment c WHERE c.editable = :editable"),
    @NamedQuery(name = "Comment.findByCreatedTime", query = "SELECT c FROM Comment c WHERE c.createdTime = :createdTime"),
    @NamedQuery(name = "Comment.findByLastEditedTime", query = "SELECT c FROM Comment c WHERE c.lastEditedTime = :lastEditedTime"),
    @NamedQuery(name = "Comment.findByCommentType", query = "SELECT c FROM Comment c WHERE c.commentType = :commentType")})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID")
    @XmlElement(required=true) 
    private Integer id;
    
    @Basic(optional = false)
    //@NotNull
    @Column(name = "EDITABLE")
    @XmlElement(required=true) 
    private boolean editable;
    
    @Basic(optional = false)
    //@NotNull
    @Column(name = "CREATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @XmlElement(required=true) 
    private Date createdTime;
    
    @Basic(optional = false)
    //@NotNull
    @Column(name = "LAST_EDITED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @XmlElement(required=true)
    private Date lastEditedTime;
    
    @Basic(optional = false)
    //@NotNull
    @Column(name = "COMMENT_TYPE")
    @XmlElement(required=true)
    private short commentType;

    @JoinColumn(name = "CREATOR_ID")
    @OneToOne(optional = false)
    @XmlElement(required=true)
    private Person commentCreator;
    
    @JoinColumn(name = "LAST_EDITOR_ID")
    @OneToOne
    @XmlElement(required=true)
    private Person lastEditor;
    
    @OneToMany(mappedBy = "parentComment")
    //@JoinColumn(name="CHILD_COMMENT", referencedColumnName="ID")
    private Collection<Comment> commentCollection;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BUBBLE_ID")
    @XmlElement(required=true)
    private Bubble bubble;
    
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "REPLYTO_COMMENT")
    private Comment parentComment;

    @ManyToOne
    @JoinColumn(name = "DISCUSSION_ID")
    private Discussion parentDiscussion;

    @Transient
    private boolean createFormRender = false;

    public Comment() {
    }

    public Comment(Integer id) {
        this.id = id;
    }

    public Comment(Integer id, boolean editable, Date createdTime, Date lastEditedTime, short commentType) {
        this.id = id;
        this.editable = editable;
        this.createdTime = createdTime;
        this.lastEditedTime = lastEditedTime;
        this.commentType = commentType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isCreateFormRender() {
        return createFormRender;
    }

    public void setCreateFormRender(boolean createFormRender) {
        this.createFormRender = createFormRender;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Person getCommentCreator() {
        return commentCreator;
    }

    public void setCommentCreator(Person commentCreator) {
        this.commentCreator = commentCreator;
    }

    public Person getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(Person lastEditor) {
        this.lastEditor = lastEditor;
    }

    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastEditedTime() {
        return lastEditedTime;
    }

    public void setLastEditedTime(Date lastEditedTime) {
        this.lastEditedTime = lastEditedTime;
    }

    public short getCommentType() {
        return commentType;
    }

    public void setCommentType(short commentType) {
        this.commentType = commentType;
    }

    public Bubble getBubble() {
        return bubble;
    }

    public void setBubble(Bubble bubble) {
        this.bubble = bubble;
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
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ejca.entities.Comment[ id=" + id + " ]";
    }
    
    
    
    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Discussion getParentDiscussion() {
        return parentDiscussion;
    }

    public void setParentDiscussion(Discussion parentDiscussion) {
        this.parentDiscussion = parentDiscussion;
    }

}
