/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.bb;

import com.ejca.ejb.CommentFacade;
import com.ejca.entity.Comment;
import com.ejca.entity.Discussion;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author mido
 */
@Named(value = "search")
@SessionScoped
@RolesAllowed("USERS")
public class SearchBB implements Serializable {

    private Integer searchLogical;
    private String searchName;
    private Date searchDate;
    private Discussion discussion;
    private List<Comment> resultList;


    public void setType(Integer type) {
        this.type = type;
    }
    private Integer type;
    @EJB
    CommentFacade ejbComment;

    @PostConstruct
    public void init() {
    }

    public void search2() {
        //Actual Search Starts here
        System.out.println("skfb Search Start");
        if (searchDate == null ) {
            type = 2;
        } else {
            if (searchName == null || "".equals(searchDate.toString().trim())) {
                type = 1;
            } else if (searchLogical == 1) {
                type = 3;
            } else {
                type = 4;
            }
        }

resultList=ejbComment.filterComments(discussion, searchName, searchDate, type);

    }
    
    

    public Integer getSearchLogical() {
        return searchLogical;
    }

    public void setSearchLogical(Integer searchLogical) {
        this.searchLogical = searchLogical;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }

    public SearchBB() {
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }
    
    public List<Comment> getResultList() {
        return resultList;
    }

    public void setResultList(List<Comment> resultList) {
        this.resultList = resultList;
    }

    public Integer getType() {
        return type;
    }
}
