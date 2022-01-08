package hello.springmvc.basic.request;


import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        log.info("username={}, age={}",username, age);
        res.getWriter().write("Ok");
    }


    @ResponseBody // 문자를 그대로 응답메시지에 넣는다.
    @RequestMapping("/request-param-v2")
    // controller 의 string 은 url을 찾는다.
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    )
    {
        log.info("username={}, age={}",memberName, memberAge);
        return "ok";
    }

    @ResponseBody // 문자를 그대로 응답메시지에 넣는다.
    @RequestMapping("/request-param-v3")
    // 변수명이 동일 하면 생략이 가능하다.
    // param  안에 변수명 생략이 가능하다.
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    )
    {
        log.info("username={}, age={}",username, age);
        return "ok";
    }

    @ResponseBody // 문자를 그대로 응답메시지에 넣는다.
    @RequestMapping("/request-param-v4")
    // 요청 파라미터와 변수명이 동일 하면 아예 @RequestParam 생략이 가능하다.
    // 하지만 @RequestParam 애노테이션 을 쓰는걸 권장한다.
    public String requestParamV4(String username, int age)
    {
        log.info("username={}, age={}",username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    // (required = false) 가 붙으면 해당 값이 없어도 에러가 발생하지 않는다.
    // username 은 필수 이다, age 는 필수 값이아니다.
    // 하지만 age 를 안넣어주면 null 값이 들어가는데 int 에는 null 을 넣을수 없기 때문에
    // 500 에러가 발생한다.
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) int age
    )
    {
        log.info("username={}, age={}",username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    // defaultValue 로 기본값 설정이 가능하다.
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    )
    {
        log.info("username={}, age={}",username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    // map 으로도 받을수 있다.
    public String requestParamMap(@RequestParam Map<String,Object> paramMap
    )
    {
        log.info("username={}, age={}",paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    //public String modelAttributeV1(@RequestParam String username, @RequestParam int age)
    // @ModelAttribute 를 쓰면 HelloData 객체가 생성되고 값도 들어가게 된다.
    public String modelAttributeV1(@ModelAttribute HelloData helloData)
    {
        /*
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username={}, age={}",helloData.getUsername(), helloData.getAge());
        */

        log.info("helloData={}", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    //public String modelAttributeV1(@RequestParam String username, @RequestParam int age)
    // @ModelAttribute 도 생략이 가능하다.
    public String modelAttributeV2(HelloData helloData)
    {
        /*
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username={}, age={}",helloData.getUsername(), helloData.getAge());
        */

        log.info("helloData={}", helloData);
        return "ok";
    }

}
