/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejca.util;

import com.ejca.entity.Discussion;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

/**
 *
 * @author mido
 */
public class DiscussionSorter  implements Comparator<Discussion> {

    private String sortField;
    
    private SortOrder sortOrder;
    
    public DiscussionSorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(Discussion car1, Discussion car2) {
        try {
            Object value1 = Discussion.class.getField(this.sortField).get(car1);
            Object value2 = Discussion.class.getField(this.sortField).get(car2);

            int value = ((Comparable)value1).compareTo(value2);
            
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
