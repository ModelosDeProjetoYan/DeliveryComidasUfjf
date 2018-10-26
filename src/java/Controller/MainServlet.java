package Controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        String action = request.getParameter("action");
        Action actionObject = null;
        
//        if (action == null || action.equals("")) {
//            if ("/Cadastrar.html".equals(request.getServletPath())) {
//                RequestDispatcher dispachante = request.getRequestDispatcher("WEB-INF/Cadastrojsp.jsp");
//                dispachante.forward(request, response);
//            } else {
//                action = "Index";
//            }
//        }
        
        String actionPath = request.getServletPath();
        String action = "";
        if (actionPath.length() > 5) {
            action = actionPath.substring(1, actionPath.length() - 5);
        }
        
        System.out.println("actionPath:" + actionPath);
        System.out.println("action: " + action);
        
        // Se não estiver logado
        if (request.getSession().isNew() 
                || request.getSession().getAttribute("id_usuario") == null
                || "".equals(request.getSession().getAttribute("id_usuario"))) {
            if ("".equals(actionPath) || "/".equals(actionPath) || "/Index.html".equals(actionPath) || "/UsuarioNovo.html".equals(actionPath) || "/Login.html".equals(actionPath)) {
                if ("".equals(actionPath) || "/".equals(actionPath)) {
                    action = "Index";
                }
            } else {
                action = "Login";
                request.setAttribute("erro", "É necessário fazer LOGIN para acessar " + actionPath);
            }
        // Se estiver logado
        } else {
            if ("/UsuarioNovo.html".equals(actionPath) || "/Login.html".equals(actionPath)) {
                action = "VisaoGeralDoUsuario";
                request.setAttribute("erro", "É necessário fazer LOGOUT para acessar " + actionPath);
            }
        }
        
        actionObject = ActionFactory.create(action);
        if (actionObject != null) {
            actionObject.execute(request, response);
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
