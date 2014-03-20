/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejca.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mido
 */
@WebServlet("/media/*")
public class MediaServlet extends HttpServlet {

     @Inject AppConfig   appConfig;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException         {
        try {
            System.out.println("path"+request.getPathInfo());
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(appConfig.getMEDIALoc()+request.getPathInfo()));

            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
            response.getOutputStream().write(bytes);
        } catch (IOException e) {                e.printStackTrace();            }
    }

    
    
    
        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
        /**
         * Handles the HTTP <code>GET</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Handles the HTTP <code>POST</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
