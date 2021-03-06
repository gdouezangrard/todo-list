package fr.enseirb.webxml.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;

import fr.enseirb.webxml.util.ServletToolkit;
import fr.enseirb.webxml.util.XMLToolkit;

/**
 * Servlet implementation class AboutServlet
 */
public class AboutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private int studentsNumber = 2;
    private int studentGroup = 2;
    private String studentClass = new String("I2");
    private String studentFirstName = new String("FirstName1");
    private String studentLastName = new String("LastName1");
    private String studentbFirstName = new String("FirstName2");
    private String studentbLastName = new String("LastName2");
    private String teacherName = new String("Teacher");


    /**
     * @see HttpServlet#HttpServlet()
     */
    public AboutServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sResponse;


        if(request.getRequestURI().contains("about/teacher"))
        {

            if(request.getRequestURI().contains("about/teacher/post"))
            {
                String postData = ServletToolkit.getPostData(request);
                sResponse = new String("Ok");
                teacherName = postData;
            }
            else
                sResponse = ServletToolkit.readFile("/resources/html/common/about_teacher.html");
        }
        else {
            Properties props = ServletToolkit.parseURLParams(request);
            sResponse = new String();
            String action = props.getProperty("action");

            if("studentsNumber".equals(action))
                sResponse = Integer.toString(studentsNumber);
            else if("class".equals(action))
                sResponse = studentClass;
            else if("teacher".equals(action))
                sResponse = teacherName;
            else if("group".equals(action))
                sResponse = Integer.toString(studentGroup);
            else if("full".equals(action) || props.size() == 0)
                sResponse = studentClass + " / " + Integer.toString(studentGroup) + " / "+ studentFirstName + " "+ studentLastName + " / "+ studentbFirstName + " "+ studentbLastName;
            else if(props.containsKey("studentId"))
            {
                int sId = Integer.parseInt(props.getProperty("studentId"));
                if(sId == 1)
                {
                    if("firstName".equals(action))
                        sResponse = studentFirstName;
                    else if ("lastName".equals(action))
                        sResponse = studentLastName;
                }
                else {
                    if("firstName".equals(action))
                        sResponse = studentbFirstName;
                    else if ("lastName".equals(action))
                        sResponse = studentbLastName;
                }
            }


        }

        ServletToolkit.writeResponse(response, sResponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
