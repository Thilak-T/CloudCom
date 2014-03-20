/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.rest;

import com.ejca.ejb.AbstractFacade;
import com.ejca.ejb.CommentFacade;
import com.ejca.ejb.DiscussionFacade;
import com.ejca.entity.Comment;
import com.ejca.entity.Discussion;
import java.net.URI;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Nithya
 */
@Stateless
@Path("/discussion")
public class DiscussionFacadeREST extends AbstractFacade<Discussion> {

    @PersistenceContext(unitName = "CloudComPU")
    private EntityManager em;
    @EJB private CommentFacade ejbComment;
    @EJB private DiscussionFacade ejbDiscussion;
    @Context private UriInfo ui;
    
    public DiscussionFacadeREST() {
        super(Discussion.class);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Discussion find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Discussion> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Discussion> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @Override
    protected EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     @GET
    @Path("/{d_Id}/filter")
    @Produces({"application/json","application/xml"})
    public Response filterComments(@PathParam("d_Id") Integer discussionId, 
                            @QueryParam("name") String name, @QueryParam("type") String type, @QueryParam("date") Date date) {
        int iType=0;
        if(type.equals("AND"))
            iType = 1;
        if(type.equals("OR"))
            iType = 2;
        if(date == null)
            iType = 3;
        if(name.isEmpty())
            iType = 4;
        Discussion discussion = ejbDiscussion.find(discussionId.toString());
        List<Comment> comments = ejbComment.filterComments(discussion, name, date, iType);
        
        List<Links> links = new LinkedList<Links>();
        UriBuilder builder = ui.getBaseUriBuilder();
        //builder.scheme("http").host("{localhost}").port(8080);
        
        builder = builder.path(CommentFacadeREST.class);
        for(Comment c : comments){
            URI uri = builder.clone().build(c.getId());
            links.add(new Links(uri));
        }
        
        GenericEntity<List<Links>> entity = new GenericEntity<List<Links>>(links){};
                
        return (Response.ok(entity).build());        
    }

}
