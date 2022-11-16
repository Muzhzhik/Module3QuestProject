package com.quest;

import com.quest.questions.Answer;
import com.quest.questions.Question;
import com.quest.questions.QuestionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GameServlet", value = "/game")
public class GameServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession();
        QuestionManager questionManager = new QuestionManager();
        Question currentQuestion = getCurrentQuestion(req);
        Question nextQuestion = null;
        if (currentQuestion == null) {
            nextQuestion = questionManager.getById(1);
        } else {
            Answer currentAnswer = getCurrentAnswer(currentQuestion, req);
            if (currentAnswer != null && !currentAnswer.isWrongAnswer()) {
                nextQuestion = questionManager.getById(currentQuestion.getId() + 1);
            } else {
                currentSession.setAttribute("wrongAnswer", currentAnswer);
            }
        }
        System.out.println(nextQuestion);
        currentSession.setAttribute("question", nextQuestion);
        resp.sendRedirect("/game.jsp");
    }

    private Question getCurrentQuestion(HttpServletRequest req) {
        try {
            return  (Question) req.getSession().getAttribute("question");
        } catch (Exception e) {
            return null;
        }
    }

    private Answer getCurrentAnswer(Question currentQuestion, HttpServletRequest req) {
        try {
            int answerId = Integer.parseInt(req.getParameter("answerId"));
            return currentQuestion.getAnswerList().get(answerId);
        } catch (Exception e) {
            return null;
        }
    }
}

