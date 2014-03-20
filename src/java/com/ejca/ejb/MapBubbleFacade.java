/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.ejb;

import com.ejca.bb.MapBubbleBB;
import com.ejca.entity.MapBubble;
import com.ejca.util.GeocodingUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author Rucha Kulkarni
 */
@Stateless
public class MapBubbleFacade extends AbstractFacade<MapBubble> {

    @PersistenceContext(unitName = "CloudComPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MapBubbleFacade() {
        super(MapBubble.class);
    }

    public void updateAddress(Integer id, String address) {
        MapBubble mb = find(id);
        mb.setAddress(address);
        edit(mb);
    }

}
