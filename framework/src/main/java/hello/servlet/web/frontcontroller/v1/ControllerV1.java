package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 인터페이스로 만든 이유
// 폼, 저장, 리스트 를 만들기 위해
public interface ControllerV1 {
    void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
