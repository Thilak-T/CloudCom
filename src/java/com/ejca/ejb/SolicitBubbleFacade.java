/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.ejb;

import com.ejca.entity.SolicitBubble;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mido
 */
@Stateless
public class SolicitBubbleFacade extends AbstractFacade<SolicitBubble> {

    @PersistenceContext(unitName = "CloudComPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitBubbleFacade() {
        super(SolicitBubble.class);
    }

    public void updateMessage(int id, String message) {
        SolicitBubble sb = find(id);
        sb.setMeetingInfo(message);
        edit(sb);
    }

}
