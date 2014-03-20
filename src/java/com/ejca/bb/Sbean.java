/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.bb;

import com.ejca.ejb.PersonFacade;
import com.ejca.ejb.SurveyBubbleFacade;
import com.ejca.ejb.SurveyOptionsFacade;
import com.ejca.entity.Person;
import com.ejca.entity.SurveyBubble;
import com.ejca.entity.SurveyOptions;
import com.ejca.entity.SurveyResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Thilak
 */
@Named
@ViewScoped
@RolesAllowed("USERS")
public class Sbean implements Serializable {
    
    private Map<Integer,Object> respView = new HashMap<>();
    private Map<Integer,PieChartModel> pieViewMap = new HashMap<>();
    @EJB SurveyBubbleFacade sFacade;
    @EJB PersonFacade pFacade;
    @Inject UserBean ub;
    @EJB SurveyOptionsFacade soFac;

    public void addSurveyBubble(SurveyBubble b) {
        PieChartModel temp = new PieChartModel(new HashMap<String,Number>());
            for (SurveyOptions tempS : b.getSurveyOptionsCollection() ) {
                      temp.set(tempS.getChoice(), tempS.getSelectedCount());
            }
            pieViewMap.put(b.getId(), temp);
            for ( SurveyResponse res : b.getSurveyResponseCollection()) {
                 if ( res.getPerson().getEmail() == ub.getUser().getEmail() ) {
                     respView.put(b.getId(), res.getSelectedOption().getId());
                 }
            }
    }
    
    public void refreshPieChart( SurveyBubble b) {
        PieChartModel temp = pieViewMap.get(b.getId());
        for (SurveyOptions tempS : b.getSurveyOptionsCollection() ) {
                      temp.set(tempS.getChoice(), tempS.getSelectedCount());
            }
    }
    
    public Map<Integer, PieChartModel> getPieViewMap() {
        return pieViewMap;
    }

    public void setPieViewMap(Map<Integer, PieChartModel> pieViewMap) {
        this.pieViewMap = pieViewMap;
    }

    public Map<Integer, Object> getRespView() {
        return respView;
    }

    public void setRespView(Map<Integer, Object> respView) {
        this.respView = respView;
    }
    
    public String updateChoice() {
        Iterator req = FacesContext.getCurrentInstance().
		getExternalContext().getRequestParameterNames();
        String inputTextId = null;
        while ( req.hasNext() ) {
            String temp = (String)req.next();
            if ( temp.contains("hidden")) {
                inputTextId = temp;
                break;
            }     
        }
        String value = FacesContext.getCurrentInstance().
		getExternalContext().getRequestParameterMap().get(inputTextId);
        Integer sid = Integer.parseInt(value);
        Integer oid =  Integer.parseInt((String)respView.get(sid));
        System.out.println(sid);
        SurveyBubble tempB = sFacade.find(sid);
        Person p = ub.getUser();
        SurveyOptions so = soFac.find(oid);
        SurveyResponse res = new SurveyResponse();
        res.setPerson(p);
        res.setSelectedOption(so);
        sFacade.updateResponse(tempB, res);
        refreshPieChart(tempB);
        return null;
    }

    
    
}
