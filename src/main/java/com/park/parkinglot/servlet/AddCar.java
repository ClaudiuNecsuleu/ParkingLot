/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.parkinglot.servlet;

import com.park.parkinglot.common.UserDetails;
import com.park.parkinglot.ejb.CarBean;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.park.parkinglot.ejb.UserBean;
import java.util.List;

/**
 *
 * @author Clau
 */
@WebServlet(name = "AddCar", urlPatterns = {"/AddCar"})
public class AddCar extends HttpServlet {
@Inject
UserBean userBean;

@Inject
CarBean carBean;
    
  

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       List<UserDetails> users= userBean.getAllUsers();
       request.setAttribute("users", users);
       request.getRequestDispatcher("addCar.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String licensePlate = request.getParameter("license_plate");
       String parkingSpot = request.getParameter("parking_spot");
       int ownerId=Integer.parseInt(request.getParameter("owner_id"));
       
       carBean.createCar(licensePlate, parkingSpot, ownerId);
       response.sendRedirect(request.getContextPath()+"/Cars");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
