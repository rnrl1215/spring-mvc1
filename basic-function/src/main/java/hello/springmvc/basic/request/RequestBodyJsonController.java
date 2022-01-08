package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */

@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        resp.getWriter().write("OK");
    }

    @ResponseBody
    @PostMapping("request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "OK";
    }

    @ResponseBody
    @PostMapping("request-body-json-v3")
    // RequesBody 객체를 지정할수 있다.
    // HTTP 메세지 컨버터가 알아서 데이터를 넣어준다.
    // JSON 이구나 객체에 맞게 반환해준다.
    // @RequestBody 생략하면 안된다.
    // 값이 안들어가는 이유는 생략하면 모델 어트리뷰트가 된다. 즉 요청 파라미터를 처리하게 된다.
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }

    @ResponseBody
    @PostMapping("request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) throws IOException {
        HelloData data = httpEntity.getBody();
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return "OK";
    }

    @ResponseBody
    @PostMapping("request-body-json-v5")
    // 나갈때 ResponseBody 가 있으면 컨버터가 알아서 바디에 넣어준다.
    // 객체가 JSON 으로 바껴 body에 들어간다.
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) throws IOException {
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return data;
    }

    @ResponseBody
    @GetMapping("request-body-json-v6")
    // 상태코드 같이 넘기ㅣ
    public ResponseEntity<HelloData> requestBodyJsonV6(@RequestBody HelloData data) throws IOException {


        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}
