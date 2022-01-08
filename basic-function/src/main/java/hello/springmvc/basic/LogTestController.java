package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // 해당 어노테이션을 쓰면 Logger 를 선언안해줘도 된다.
@RestController // 리턴 값을 그냥화면에 반환한다.
public class LogTestController {
    //private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        // 이렇게 쓰면 안됨
        // + 를 쓰게 되면 info는 상관없지만 trace 를 쓰게되면
        //더하기 연산이 발생한다.
        // trace 로그를 쓰지 않는데도 불필요한 리소를 사용한다.
        log.info("info log" + name);

        log.trace("trace log={}" ,name);
        log.debug("debug log={}" , name);
        log.info("info log={}" , name);
        log.warn("warn log={}" , name);
        log.error("error log={}" , name);
        return "Ok";
    }
}
