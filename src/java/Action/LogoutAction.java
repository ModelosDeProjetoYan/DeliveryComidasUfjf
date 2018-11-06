package Action;

import Controller.Action;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionScope = request.getSession();
        if (sessionScope != null) {
            sessionScope.invalidate();
        }
        response.sendRedirect("MainServlet?parametro=Index");
    }
}
