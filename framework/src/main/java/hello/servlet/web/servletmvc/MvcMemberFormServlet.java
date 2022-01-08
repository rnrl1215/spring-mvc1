package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        
        // 컨트롤러에서 뷰로 이동하기 위해 사용
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);

        // jsp 를 찾아서 req, rsp을 넘겨준다.
        // 다른 서블릿이나 JSP로 이동할 수 있는 기능이다.
        // 리다이렉트가 아니다.
        // 서버안에서 내부적으로 호출이 발생한다.
        dispatcher.forward(req,resp);
    }
}
