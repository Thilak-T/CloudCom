package com.ejca.util;


import com.ejca.bb.CreateDiscussionBB;
import com.ejca.entity.Person;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;

@ViewScoped
@ManagedBean(name = "personConverter")
public class PersonConverter implements Converter {

    @ManagedProperty(value = "#{person}")
    private CreateDiscussionBB cdbb;

    public CreateDiscussionBB getCdbb() {
        return cdbb;
    }

    public void setCdbb(CreateDiscussionBB cdbb) {
        this.cdbb = cdbb;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        List<Person> persons = cdbb.getParticipants().getSource();
        for (Person person : persons) {
            if (value.equals(person.getEmail())) {
                return person;
            }
        }
         persons = cdbb.getParticipants().getTarget();
        for (Person person : persons) {
            if (value.equals(person.getEmail())) {
                return person;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        return ((Person) value).getEmail();
    }



}