package hello.servlet.web.frontcontroller.v3;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// /front-controller/v1/* 주소의 * 때문에 어떤 url이 들어와도 일단 해당 컨트롤러가호출된다.
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    // 프론트 컨트롤러 map 셋팅
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        ControllerV3 controllerV3 = controllerMap.get(requestURI);
        if (controllerV3 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 모델 뷰를 가져오는 부분
        Map<String, String> paramMap = createParamMap(req);
        ModelView mv = controllerV3.process(paramMap);

        // 뷰리졸버 구현 논리 이름을 물리 이름으로 변경해야 된다.
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), req, resp);
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
