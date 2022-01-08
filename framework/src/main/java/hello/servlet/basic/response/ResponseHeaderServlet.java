package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // [status-line]
        resp.setStatus(HttpServletResponse.SC_OK);

        // [response-header]
        resp.setHeader("Pragma","no-cache");
        resp.setHeader("my-header","hello");

        content(resp);
        cookie(resp);

        // redirect
        redirect(resp);

        PrintWriter writer = resp.getWriter();
        writer.write("OK");
    }

    private void content(HttpServletResponse resp) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse res) {
        //Set-Cookie: myCookie=good; Max-Age=600
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("muCookie","good");
        cookie.setMaxAge(600); // 600초 동안 유효하다.
        res.addCookie(cookie);
    }

    private void redirect(HttpServletResponse res) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html

        //res.setStatus(HttpServletResponse.SC_FOUND); //302
        //res.setHeader("Location:","/basic/hello-form.html");
        res.sendRedirect("/basic/hello-form.html");
    }
}
