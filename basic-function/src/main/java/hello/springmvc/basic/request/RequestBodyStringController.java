package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        res.getWriter().write("ok");
    }

    //inputstream 과 reader 를 바로 받을수 있다.
    @PostMapping("/request-body-string-v2")
    public void requestBodyString2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    // messageConverter 를 사용하면 간략하게 쓸수 있다.
    // T 타입이 문자네? 그럼 스프링이 알아서 body 내용을 문자로 넣어준다.

    // HTTPEntity 는 header 와 body 정보를 편히라게 조회한다.
    // 뷰를 조회하지않는다.

    // !!!!!! 요청 파라미터는 GET 의 쿼리스트링 또는 , POST 의 HTML form 데이터 전송하는 방식 인 경우에만
    // @RequestParam, ModelAttritube 해당 어노테이션을 사용한다.
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyString3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);

        // 이렇게 하면 body에 데이터를 넣어준다.
        return new HttpEntity<String>("ok");
    }

    // !!!!!! 요청 파라미터는 GET 의 쿼리스트링 또는 , POST 의 HTML form 데이터 전송하는 방식 인 경우에만
    // @RequestParam, ModelAttritube 해당 어노테이션을 사용한다.
    @PostMapping("/request-body-string-v3-1")
    public ResponseEntity<String> requestBodyString3_1(RequestEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);

        // 이렇게 하면 body에 데이터와 상태코드를 넣을수 있다.
        return new ResponseEntity<String>("OK",HttpStatus.CREATED);
    }

    // 응답  RequestBody 실무에서 엄청 많이 쓴다.
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyString4(@RequestBody String messageBody) throws IOException {
        // 끝이다.
        log.info("messageBody={}", messageBody);
        // 이렇게 하면 body에 데이터와 상태코드를 넣을수 있다.
        return "OK";
    }

}
