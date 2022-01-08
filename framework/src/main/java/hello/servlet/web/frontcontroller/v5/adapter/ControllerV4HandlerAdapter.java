package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4)handler;
        Map<String, String> paramMap = createParamMap(request);

        HashMap<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);


        // 어댑터 역할을 한다.
        // 뷰네임 을 모델로 변환시켰다.
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
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
