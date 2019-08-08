package com.spring4.rest.server;

import com.spring4.rest.Student;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Creator weishi8
 * Date&Time 2019-07-09 17:44
 * description
 */
@RestController
public class RestExceptionController {

    public static Map<String, Student> studentMap = new HashMap<>();

    static {
        studentMap.put("zhangsan", new Student("zhangsan", 10));
        studentMap.put("lisi", new Student("lisi", 11));
        studentMap.put("wangwu", new Student("wangwu", 12));
    }

    /**
     * 原始的异常处理方式：
     * 1、定义 ResponseEntity<?> 返回值（因为不确定是返回Student，还是RestError）；
     * 2、ResponseEntity 返回值，会自动增加ResponseBody注解，因此在方法级别可以不用添加@ResponseBody；
     * 3、自定义RestError，通过异常判断；
     *
     * @param name
     * @return
     */
    @GetMapping(path = "/exception/get/{name}")
    public ResponseEntity<?> getByNameComplex(@PathVariable String name){
        Student student = studentMap.get(name);
        if(student==null){
            RestError restError = new RestError(HttpStatus.NOT_FOUND.value(),"未找到该用户");
            return new ResponseEntity<RestError>(restError,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(studentMap.get(name),HttpStatus.OK);
    }

    /**
     * 简单方式：
     * 1、定义一个异常 RestNotFoundException；
     * 2、通过@ExceptionHandler 注解，捕获该controller的 RestNotFoundException 异常；
     * 3、只要抛出RestNotFoundException异常，则统一由restNotFoud() 方法处理。
     * 4、可以去掉ResponseEntity<?>返回值，而修改为Student
     * @param name
     * @return
     */
    @GetMapping(path = "/exception/get/simple/{name}")
    public Student getByNameSimple(@PathVariable String name,@RequestHeader HttpHeaders headers ){
        System.out.println("请求header："+headers);
        Student student = studentMap.get(name);
        if(student==null){
            throw new RestNotFoundException(name);
        }
        return student;
    }

    /**
     * 异常捕获方式一：定义ResponseEntity返回对象
     * @param e
     * @return
     */
    @ExceptionHandler(RestNotFoundException.class)
    public ResponseEntity<RestError> restNotFoud(RestNotFoundException e){

        RestError restError = new RestError(HttpStatus.NOT_FOUND.value(),"未找到该用户");
        System.out.println(restError.toString());
        return new ResponseEntity<RestError>(restError,HttpStatus.NOT_FOUND);
    }

//    /**
//     * 异常捕获方式二：通过ResponseStatus将httpstatus返回，因此可以将方法的返回对象，从ResponseEntity修改为RestError
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(RestNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public RestError restNotFoud2(RestNotFoundException e){
//        return  new RestError(HttpStatus.NOT_FOUND.value(),"未找到该用户");
//    }

    class RestError{
        private int code;
        private String msg;

        public RestError(int code,String msg){
            this.code = code;
            this.msg = msg;
        }
        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "RestError{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }
}
