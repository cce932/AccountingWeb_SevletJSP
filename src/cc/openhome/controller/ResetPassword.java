package cc.openhome.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.openhome.model.Account;
import cc.openhome.model.UserService;

@WebServlet("/reset_password") // 信寄完之後，user到他的信像去點擊link，
                               // 跑出來的頁面為reset_password.jsp，打完新密碼後submit就會跳到這一頁
public class ResetPassword extends HttpServlet {
    private final Pattern passwdRegex = Pattern.compile("^\\w{8,16}$");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String token = request.getParameter("token");

        UserService userService = (UserService) getServletContext().getAttribute("userService");
        Optional<Account> optionalAcct = userService.accountByNameEmail(name, email);

        if (optionalAcct.isPresent()) { // 如果輸入的name==用email去account尋找出來的name。(也就是說name=email的userName)
            Account acct = optionalAcct.get();
            if (acct.getPassword().equals(token)) {
                request.setAttribute("acct", acct);
                request.getSession().setAttribute("token", token);
                request.getRequestDispatcher("/WEB-INF/jsp/reset_password.jsp").forward(request, response);
                return;
            }
        }

        response.sendRedirect("/Accounting");

    }

    // 重設密碼
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        String storedToken = (String) request.getSession().getAttribute("token");
        if (storedToken == null || !storedToken.equals(token)) {
            response.sendRedirect("/Accounting");
            return;
        }

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        UserService userService = (UserService) getServletContext().getAttribute("userService");

        if (!validatePassword(password, password2)) {
            Optional<Account> optionalAcct = userService.accountByNameEmail(name, email);
            request.setAttribute("errors", Arrays.asList("隢Ⅱ隤�Ⅳ蝚血�撘蒂��漲蝣箄��Ⅳ"));
            request.setAttribute("acct", optionalAcct.get());

            request.getRequestDispatcher("/WEB-INF/jsp/reset_password.jsp").forward(request, response);
        } else {
            userService.resetPassword(name, password);
            request.getRequestDispatcher("/WEB-INF/jsp/reset_success.jsp").forward(request, response);
        }
    }

    private boolean validatePassword(String password, String password2) {
        return password != null && passwdRegex.matcher(password).find() && password.equals(password2);
    }
}