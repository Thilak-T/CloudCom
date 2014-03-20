/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.bb;

import com.ejca.ejb.DiscussionFacade;
import com.ejca.entity.Discussion;
import com.ejca.entity.Person;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Rucha Kulkarni
 */

public class LazyDiscussionDataModel extends LazyDataModel<Discussion> {

    private DiscussionFacade discussionFacade;
    private List<Discussion> datasource;
    private Person user;

    LazyDiscussionDataModel(DiscussionFacade ejbDiscussion, Person user) {
       this.discussionFacade = ejbDiscussion;
       this.user = user;
       this.setRowCount(discussionFacade.getCountOfDiscussions(user));
    }
        
    @Override
    public Discussion getRowData(String rowKey) {
        return discussionFacade.find(rowKey);
    }

    @Override
    public Object getRowKey(Discussion discussion) {
        return discussion.getId();
    }

    @Override
    public List<Discussion> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
     
        this.datasource = discussionFacade.getDiscussions(this.user, first, pageSize);
        //rowCount
        int dataSize = this.datasource.size();  
        //paginate  
        if(dataSize > pageSize) {  
            try {  
                return this.datasource.subList(first, first + pageSize);  
            }  
            catch(IndexOutOfBoundsException e) {  
                return this.datasource.subList(first, first + (dataSize % pageSize));  
            }  
        }  
        else {  
            return this.datasource;  
        }  
        }  
}
          
    

