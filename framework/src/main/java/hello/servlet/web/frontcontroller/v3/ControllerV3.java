package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    // v2 와 비교해보면 상당히 단순해졌다.
    ModelView process(Map<String, String> paramMap);
}
