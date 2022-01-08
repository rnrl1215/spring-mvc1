package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 기존과 동일하지만 반환을 MyView로 한다.
public interface ControllerV2 {
    MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
