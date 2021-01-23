package cc.openhome.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cc.openhome.model.Accounting;
import cc.openhome.model.UserService;

@WebServlet("/browse_record")
public class BrowseRecord extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {

                String name = String.valueOf(request.getSession().getAttribute("login"));
                UserService userService = (UserService) getServletContext().getAttribute("userService");
                List<Accounting> allRecord = userService.findAcctingByName(name);

                for (Accounting a : allRecord) {
                        System.out.println(a.getName() + "\t" + a.getAmount() + "\t" + a.getCategory());
                }

                request.setAttribute("allRecord", allRecord);
                request.getRequestDispatcher("/WEB-INF/jsp/record.jsp").forward(request, response);
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                doGet(request, response);
        }
}
