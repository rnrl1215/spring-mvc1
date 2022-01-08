package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// /front-controller/v1/* 주소의 * 때문에 어떤 url이 들어와도 일단 해당 컨트롤러가호출된다.
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    // 프론트 컨트롤러 map 셋팅
    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        ControllerV4 controllerV4 = controllerMap.get(requestURI);
        if (controllerV4 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 모델 뷰를 가져오는 부분
        Map<String, String> paramMap = createParamMap(req);
        Map<String, Object> model = new HashMap<>();  // 추가됨.
        String viewName = controllerV4.process(paramMap, model);

        MyView view = viewResolver(viewName);
        view.render(model, req, resp);
    }

    private MyView viewResolver(String viewName) {
        MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return view;
    }

    // 레벨을 맞춰주기 위해 메서드로 뽑아준다.
    private Map<String, String> createParamMap(HttpServletRequest req) {
        // paramMap을 넘겨줘야한다.
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
