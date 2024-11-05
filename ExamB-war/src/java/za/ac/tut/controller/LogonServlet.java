/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.ac.tut.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.ac.tut.entities.Question;
import za.ac.tut.entities.User;
import za.ac.tut.sessions.MarkingBeanService;
import za.ac.tut.sessions.QuestionBeanLocal;

/**
 *
 * @author trant
 */
@WebServlet(name = "LogonServlet", urlPatterns = {"/LogonServlet"})
public class LogonServlet extends HttpServlet {
@EJB
QuestionBeanLocal questionBean;


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
            out.println("<title>Servlet LogonServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LogonServlet at " + request.getContextPath() + "</h1>");
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String logons = username + "#" + password;
        try {
            Context context = new InitialContext();
            ConnectionFactory factory = (ConnectionFactory) context.lookup("");
            Queue queue = (Queue) context.lookup("jms/logonQueue");
            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publish = session.createProducer(queue);
            TextMessage txtMsg = session.createTextMessage();
            txtMsg.setText(logons);
            txtMsg.setJMSReplyTo(queue);
            publish.send(txtMsg);
            ConnectionFactory factory1 = (ConnectionFactory) context.lookup("");
            Queue queue1 = (Queue) context.lookup("jms/logonQueue");
            Connection connection1 = factory1.createConnection();
            Session session1 = connection1.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session1.createConsumer(queue1);
            Message message = consumer.receive(0);
            if(message instanceof ObjectMessage){
                ObjectMessage objMsg = (ObjectMessage) message;
                User user = (User) objMsg.getObject();
                if(user.getUserType().equals("admin")){
                    response.sendRedirect("question.jsp");
                }else if(user.getUserType().equals("learner")){
                    List<Question> questions = questionBean.retrieveQuestion();
                    InitialContext ctx = new InitialContext();
                    MarkingBeanService markingBean = (MarkingBeanService) ctx.lookup("za.ac.tut.sessions.MarkingBeanService");
                    markingBean.populateQuestion(questions);
                    response.sendRedirect("answerK53.jsp");
                }
                response.sendRedirect("login.html");
            }
            
        } catch (NamingException ex) {
            Logger.getLogger(LogonServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(LogonServlet.class.getName()).log(Level.SEVERE, null, ex);
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

}
