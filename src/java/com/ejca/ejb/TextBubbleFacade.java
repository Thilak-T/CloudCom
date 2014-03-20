/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejca.ejb;

import com.ejca.entity.TextBubble;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mido
 */
@Stateless
public class TextBubbleFacade extends AbstractFacade<TextBubble> {
    @PersistenceContext(unitName = "CloudComPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TextBubbleFacade() {
        super(TextBubble.class);
    }
    
    public void updateMessage(int id, String message) {
        TextBubble txt = find(id);
        txt.setMessage(message);
        edit(txt);
    }
}
