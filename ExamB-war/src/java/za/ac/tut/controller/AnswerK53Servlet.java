/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.ac.tut.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.ac.tut.entities.User;
import za.ac.tut.sessions.MarkingBeanService;

/**
 *
 * @author trant
 */
@WebServlet(name = "AnswerK53Servlet", urlPatterns = {"/AnswerK53Servlet"})
public class AnswerK53Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AnswerK53Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AnswerK53Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            InitialContext ctx = new InitialContext();
            MarkingBeanService markingBean = (MarkingBeanService) ctx.lookup("");
            markingBean.markQuestion(Integer.parseInt(request.getParameter("questionId")), request.getParameter("possibleAnswer"));
            response.sendRedirect("answerK53.jsp");
            
            
        } catch (NamingException ex) {
            Logger.getLogger(AnswerK53Servlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        processRequest(request, response);
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
    public void publishPassedLearner(User user){
        try {
            InitialContext ctx = new InitialContext();
            MarkingBeanService markingBean = (MarkingBeanService) ctx.lookup("");

            ConnectionFactory factory = (ConnectionFactory) ctx.lookup("");
            Topic topic = (Topic) ctx.lookup("");
            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publish = session.createProducer(topic);
            ObjectMessage objMsg = session.createObjectMessage();

            if(markingBean.determineResult().equals("passed")){
        
                objMsg.setObject(user);
                publish.send(objMsg);
            }
        } catch (NamingException ex) {
            Logger.getLogger(AnswerK53Servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(AnswerK53Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
