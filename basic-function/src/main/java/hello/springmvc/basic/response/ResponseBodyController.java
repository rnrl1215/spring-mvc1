package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Controller
//@ResponseBody
@RestController //@ResponseBody + @Controller
public class ResponseBodyController {

    // Writer 를 가져와 Body에 직접 넣어준다.
    @GetMapping("/response-body-string-v1")
    public void responseBodyStringV1(HttpServletResponse res) throws IOException {
        res.getWriter().write("OK");
    }

    // ResponseEntity 로 상태코드도 같이 넘길수 있다.
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyStringV2(HttpServletResponse res) {
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }


    //@ResponseBody
    @GetMapping("/response-body-json-v1")
    public String responseBodyJsonV1(HttpServletResponse res) {
        return "OK";
    }

    @GetMapping("/response-body-json-v2")
    public ResponseEntity<HelloData> responseBodyJsonV2(HttpServletResponse res) {
        HelloData data = new HelloData();
        data.setUsername("skahn");
        data.setAge(19);

        return new ResponseEntity<HelloData>(data,HttpStatus.OK);
    }


    // API 를 만들때 가장 많이 쓰는 스타일이다.
    // ResponseBody 를 사용할 경우 아래와같이 상태값을 넘길수 있다.
    @ResponseStatus(HttpStatus.OK)
    //@ResponseBody
    @GetMapping("/response-body-json-v3")
    public HelloData responseBodyJsonV3(HttpServletResponse res) {
        HelloData data = new HelloData();
        data.setUsername("skahn");
        data.setAge(19);

        return data;
    }


}
