package cc.openhome.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.openhome.model.Message;
import cc.openhome.model.UserService;

@WebServlet("")
public class Index extends HttpServlet {
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
    	
    	//接到請求後，跳轉到首頁
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp")
               .forward(request, response);
    }
}
