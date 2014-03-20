/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejca.bb;


import com.ejca.ejb.DiscussionFacade;
import com.ejca.entity.Discussion;
import com.ejca.util.DiscussionSorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
public class DiscussionDM extends LazyDataModel<Discussion> {
    
    private DiscussionFacade ejbDiscussion;
    public DiscussionDM() {
        ejbDiscussion=new DiscussionFacade();
    }
    
    @Override
    public Discussion getRowData(String rowKey) {
        return ejbDiscussion.find(rowKey);
    }

    @Override
    public Object getRowKey(Discussion discussion) {
        return discussion.getId();
    }

    @Override
    public List<Discussion> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        List<Discussion> data = new ArrayList<Discussion>();
        if(true)
            data= ejbDiscussion.findAll();
        this.setRowCount(data.size()); 
        if(getRowCount() > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (getRowCount() % pageSize));
            }
        }
        else {
            return data;
        }
    }
}
