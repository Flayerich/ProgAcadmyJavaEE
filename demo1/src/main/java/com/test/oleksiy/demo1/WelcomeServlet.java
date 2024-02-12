package com.test.oleksiy.demo1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ask")
public class WelcomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Map<String, Integer> answerCounts = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String answer1 = request.getParameter("answer1");
        String answer2 = request.getParameter("answer2");

        incrementAnswerCount(answer1);
        incrementAnswerCount(answer2);

        response.sendRedirect("statistics.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("answerCounts", answerCounts);
        request.getRequestDispatcher("statistics.jsp").forward(request, response);
    }

    private synchronized void incrementAnswerCount(String answer) {
        if (answer != null) {
            answerCounts.put(answer, answerCounts.getOrDefault(answer, 0) + 1);
        }
    }

    public static synchronized Map<String, Integer> getAnswerCounts() {
        return answerCounts;
    }
}
