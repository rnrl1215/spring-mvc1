package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송기능
 * http://localhost:8080/request-param?username=skahn&hobby=drum
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Search all parameter - start");

        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paraName-> System.out.println(paraName +"="+ request.getParameter(paraName)));

        System.out.println("Search all parameter - end");
        System.out.println();

        System.out.println("Search single parameter - start");
        String username = request.getParameter("username");
        String hobby = request.getParameter("hobby");

        System.out.println("username: "+username);
        System.out.println("hobby: "+hobby);

        System.out.println("조회 single parameter - end");

        System.out.println("복수 조회 - start");
        String []usernames =  request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("user name " + name);
        }

        response.getWriter().write("OK");
    }
}
