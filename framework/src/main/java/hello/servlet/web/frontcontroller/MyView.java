package hello.servlet.web.frontcontroller;

import org.springframework.cache.annotation.CachePut;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp);
    }

    public void render(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 모델에 있는 데이터를 다 꺼내면서 setAttribute 에 저장한다.
        modelToRequestAttribute(model, req);
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp);
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest req) {
        model.forEach((key, value) -> req.setAttribute(key, value));
    }
}
