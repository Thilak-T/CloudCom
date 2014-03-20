/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.ejb;

import com.ejca.entity.InfoBubble;
import com.ejca.util.AppConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ramesh Kumar
 */
@Stateless
public class InfoBubbleFacade extends AbstractFacade<InfoBubble> {
    @PersistenceContext(unitName = "CloudComPU")
    private EntityManager em;
        
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InfoBubbleFacade() {
        super(InfoBubble.class);
    }
    
    public void copyFile(String fileName, InputStream in) {
        try {      
            OutputStream out = new FileOutputStream(new File(fileName));

            int read =   0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
