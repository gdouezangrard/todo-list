package fr.enseirb.webxml.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;
import java.util.Map;
import java.util.HashMap;

import fr.enseirb.webxml.util.ServletToolkit;
import fr.enseirb.webxml.data.xml.XMLMediator;
import fr.enseirb.webxml.util.XMLToolkit;

/**
 * Servlet implementation class AboutServlet
 */
public class ListUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUsersServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sResponse;

        if(request.getRequestURI().contains("user/list/xml"))
        {
            sResponse = XMLMediator.getUsers();
            response.setHeader("Content-Type", "application/xml");
        }
        else
        {
            Map<String, String> xslParams = new HashMap<String, String>();
            xslParams.put("pageTitle", "Ajout utilisateur");

            String fluxXML = XMLMediator.getUsers();
            sResponse=XMLToolkit.transformXML(fluxXML, "/resources/xsl/common/user_list.xsl", xslParams);
        }
        ServletToolkit.writeResponse(response, sResponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
