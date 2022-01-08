package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/hello-basic", "/hello-go"}, method = RequestMethod.GET)
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @GetMapping(value = {"/mapping-get-v1"})
    public String mappingGetV1() {
        log.info("helloBasic");
        return "ok";
    }

    @GetMapping(value = "/mapping/{userId}")
   // public String mappingPath(@PathVariable("userId") String data) {
    // 변수명과 PathVariable 이름이 같으면 변수만 써줘도 된다.
    public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath userId ={}", userId);
        return "ok";
    }

    /**
     * PathVariable 변수 여러개 사용
     */
    @GetMapping("/mapping/users/{userId}/odres/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "OK";
    }


    /**
     * 파라미터로 추가 매핑
     * 해당 파라미터 쿼리가 있어야 동작한다.
     * mode=debug 이게 파라미터로 있어야한다.
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * 헤더에 해당 값이 있어야한다.
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * 헤더의 content-type이 json 이면 응답한다.
     * consumes: 소비하는 입장에서 보면 요청의 content 타입을 처리
     * produces: 컨트롤러가 생성하는 타입을 말한다.
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces: 클라이언트 쪽이 받아 들이는 타입을 정의해준다.
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}

