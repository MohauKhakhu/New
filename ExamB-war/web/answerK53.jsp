<%-- 
    Document   : answerK53
    Created on : 04 Nov 2024, 4:02:36 PM
    Author     : trant
--%>

<%@page import="za.ac.tut.entities.PossibleAnswers"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="za.ac.tut.entities.Question" %>
<%@page import="za.ac.tut.sessions.MarkingBeanService" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Answer the question</h1>
        <table>
        <%
            MarkingBeanService markingBean = (MarkingBeanService) request.getSession().getAttribute("markingContext");
            for (Question question : markingBean.getAllQuestion()) {
        %>
        <form action="AnswerK53Servlet" method="POST">
            <input type="hidden" name="questionId" value="<%= question.getQuestionId()%>" >
           <%= question.getQuestion()%><br/>
            <tr>
                
                <td>Select the possible answer<br/>
            <%
                for (PossibleAnswers answer : question.getPossibleAnswers()) {
                        if(!question.getIsAnswered()){
            %>              
            <input type="radio" name="givenAns" value="<%= answer.getPossibleAnswer().charAt(0)%>" >
            <%= answer.getPossibleAnswer()%><br/>
            <%
                        }
                    }
            %>
            Your mark : <%=question.getMark()%>
            <input type="submit" value="Answer Question" name ="select" />
            </td>
            </tr>
        </form>
            <%
                }
                %>
                <tr>
                    <td>
                        
                    </td>
        
            
        </table>   
    </body>
</html>
