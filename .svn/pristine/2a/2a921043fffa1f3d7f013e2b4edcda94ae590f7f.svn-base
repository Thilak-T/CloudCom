/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejca.ejb;

import com.ejca.entity.SurveyBubble;
import com.ejca.entity.SurveyOptions;
import com.ejca.entity.SurveyResponse;
import com.ejca.entity.TextBubble;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mido
 */
@Stateless
public class SurveyBubbleFacade extends AbstractFacade<SurveyBubble> {
    @PersistenceContext(unitName = "CloudComPU")
    private EntityManager em;

    @EJB SurveyOptionsFacade surOptionsFac;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SurveyBubbleFacade() {
        super(SurveyBubble.class);
    }
    
    public void updateResponse( SurveyBubble b, SurveyResponse res) {
        SurveyOptions oldOption;
        SurveyOptions newOption;
        for ( SurveyResponse temp : b.getSurveyResponseCollection() ) {
            if ( temp.getPerson().getEmail().equals(res.getPerson().getEmail())) {
                oldOption = temp.getSelectedOption();
                newOption = res.getSelectedOption();
                oldOption.decrementSelectedCount();
                newOption.incrementSelectedCount();
                temp.setSelectedOption(newOption);
                edit(b);
                surOptionsFac.edit(newOption);
                surOptionsFac.edit(oldOption);
                return;
            }
        }
        // First time response
        newOption = res.getSelectedOption();
        newOption.incrementSelectedCount();
        b.addSurveyResponse(res);
        edit(b);
        surOptionsFac.edit(newOption);
    }
    
    public void updateMessage(int id, String message) {
        SurveyBubble s = find(id);
        s.setQuestion(message);
        edit(s);
    }
}
