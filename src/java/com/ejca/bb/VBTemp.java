
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.bb;

import com.ejca.ejb.CommentFacade;
import com.ejca.ejb.DiscussionFacade;
import com.ejca.ejb.PersonFacade;
import com.ejca.entity.Comment;
import com.ejca.entity.Discussion;
import com.ejca.entity.InfoBubble;
import com.ejca.entity.MapBubble;
import com.ejca.entity.Person;
import com.ejca.entity.SolicitBubble;
import com.ejca.entity.SurveyBubble;
import com.ejca.util.AppConfig;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

/**
 *
 * @author mido
 */
@Named("vb")
@ViewScoped
public class VBTemp implements Serializable {

    @Inject
    SearchBB searchBB;
    @Inject
    CreateBubble createBubble;
    @Inject
    UserBean userBean;
    @EJB
    DiscussionFacade ejbDiscussion;
    @EJB
    PersonFacade ejbPerson;
    @EJB
    CommentFacade ejbComment;
    private List<Comment> baseComments;
    private Discussion discussion;
    private String dissID;
    private boolean access;
    private DualListModel<Person> participantsDLM;
    private String modeSelected;
    private Comment selectedComment;
    private boolean renderCreateComment;
    private boolean renderEditComment;
    private TreeNode root;
    private Map<Integer, TreeNode> indexToTree;


    @Inject
    private Sbean sBean;
    @Inject
    private Commentbb commbb;
    @Inject
    private SolicitBubbleBB solicitBB;
    @Inject
    private MapBubbleBB mapBB;

    public DualListModel<Person> getParticipantsDLM() {
        if (participantsDLM == null) {
            try {
                List<Person> source = new ArrayList<Person>();
                List<Person> target = new ArrayList<Person>();
                target.addAll(discussion.getParticipants());
                source = ejbPerson.findAll();
                source.remove(userBean.getUser());
                source.remove(discussion.getCreatorId());
                target.remove(userBean.getUser());
                target.remove(discussion.getCreatorId());
                source.removeAll(target);
                participantsDLM = new DualListModel<Person>(source, target);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return participantsDLM;
    }

    public void editParticipants() {

        List<Person> target = participantsDLM.getTarget();
        target.add(userBean.getUser());
        if (!target.contains(discussion.getCreatorId())) {
            target.add(discussion.getCreatorId());
        }

        List<Person> pl = new ArrayList<Person>();
        for (Object s : target) {
            String s1 = s.toString();
            pl.add(ejbPerson.find(s1));
        }
        System.out.println("pl" + pl);
        ejbDiscussion.updateDiscussion(discussion, pl);

    }

    @PostConstruct
    public void init2() {
        indexToTree = new HashMap<>();
        discussion = ejbDiscussion.find(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("discussionID"));
        baseComments = ejbComment.getBaseComments(discussion);
        buildViewModel();
        renderCreateComment = false;
        renderEditComment = false;
        participantsDLM = null;

    }

    public void buildTree(Comment c, TreeNode n) {
        TreeNode temp = new DefaultTreeNode(c, n);
        temp.setExpanded(true);
        temp.setSelectable(false);
        indexToTree.put(c.getId(), temp);
        for (Comment child : ejbComment.findSubComments(c)) {
            if (child.getCommentType() == 1) {
                sBean.addSurveyBubble((SurveyBubble) child.getBubble());
            }
            if (child.getCommentType() == 2) {
                solicitBB.setResponse((SolicitBubble) child.getBubble());
            }
            if (child.getCommentType() == 3) {
                mapBB.setAddress((MapBubble) child.getBubble());
            }

            buildTree(child, temp);
        }
    }

    public void showComment() {
        if (renderCreateComment) {
            renderCreateComment = false;
        } else {
            renderCreateComment = true;
        }
    }

    public List<Comment> getBaseComments() {
        return baseComments;
    }

    public void setBaseComments(List<Comment> baseComments) {
        this.baseComments = baseComments;
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }

    public String getDissID() {
        return dissID;
    }

    public void setDissID(String dissID) {
        this.dissID = dissID;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public String getModeSelected() {
        return modeSelected;
    }

    public void setModeSelected(String modeSelected) {
        this.modeSelected = modeSelected;
    }

    public Comment getSelectedComment() {
        return selectedComment;
    }

    public void setSelectedComment(Comment selectedComment) {
        this.selectedComment = selectedComment;
    }

    public boolean isRenderCreateComment() {
        return renderCreateComment;
    }

    public void setRenderCreateComment(boolean renderCreateComment) {
        this.renderCreateComment = renderCreateComment;
    }

    public boolean isRenderEditComment() {
        return renderEditComment;
    }

    public void setRenderEditComment(boolean renderEditComment) {
        this.renderEditComment = renderEditComment;
    }

    public void setParticipantsDLM(DualListModel<Person> participantsDLM) {
        this.participantsDLM = participantsDLM;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public VBTemp() {

    }

    public void createTC() {
        createBubble.setSelection("text");
        createSetup();
    }

    public void createSurvey() {
        createBubble.setSelection("survey");
        createSetup();
    }

    public void createSolicit() {
        createBubble.setSelection("solicit");
        createSetup();
    }

    public void createMap() {
        createBubble.setSelection("map");
        createSetup();
    }

    public void createMedia() {
        createBubble.setSelection("info");
        createSetup();
    }

    public void createSetup() {
        renderCreateComment = true;
        createBubble.getComment().setParentDiscussion(discussion);
        createBubble.setDiscussion(discussion);
        if (getCommentID() == -1) {
            createBubble.getComment().setParentComment(null);
        } else {
            createBubble.getComment().setParentComment(ejbComment.find(getCommentID()));
        }
    }

    public int getCommentID() {
        Iterator req = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterNames();
        String inputTextId = null;
        while (req.hasNext()) {
            String temp = (String) req.next();
            if (temp.contains("CommentID")) {
                inputTextId = temp;
                break;
            }
        }
        String value = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get(inputTextId);
        if ("root".equals(value)) {
            return -1;
        }
        Integer bid = Integer.parseInt(value);
        return bid;
    }

    public void actionL() {
        renderCreateComment = false;
        createBubble.action();
        buildViewModel();
    }
    
    
    public void handleFileUpload(FileUploadEvent event) {
        createBubble.handleFileUpload(event);
        actionL();
    }
    

    public void buildViewModel() {
        root = new DefaultTreeNode("Root", null);
        indexToTree.put(-1, root);
        baseComments = ejbComment.getBaseComments(discussion);
        for (Comment c : baseComments) {
            if (c.getCommentType() == (short) 1) {
                sBean.addSurveyBubble((SurveyBubble) c.getBubble());
            }

            if (c.getCommentType() == (short) 2) {
                solicitBB.setResponse((SolicitBubble) c.getBubble());
            }

            if (c.getCommentType() == (short) 3) {
                mapBB.setAddress((MapBubble) c.getBubble());
            }
            commbb.setFlag(c.getBubble().getId());
            buildTree(c, root);
        }
    }

    public String randomFunction() {
        ejbDiscussion.removeParticipant(discussion, userBean.getUser());
        return "/page/homepage.xhtml?faces-redirect=true";
    }
    
    public String search(){
        if((searchBB.getSearchDate()==null)&&(("".equals(searchBB.getSearchName().trim()))||(searchBB.getSearchName()==null)))
            return null;
        searchBB.setDiscussion(discussion);
        return "/page/searchResults.xhtml?faces-redirect=true";
    }

}