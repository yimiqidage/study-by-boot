package com.spring4.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring4.rest.Student;
import io.netty.handler.codec.Headers;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过RestTemplate调用Rest接口，整体逻辑：
 * 1、声明RestTemplate对象；
 * 2、通过RestTemplate对象的getForObject、getForEntity（类似的还有postForObject、postForEntity等）获取url的结果；
 * 3、getForObject与getForEntity的区别在于，getForEntity可以从get请求中，获取响应头等信息；
 * Creator weishi8
 * Date&Time 2019-07-08 16:48
 * description
 *
 */
public class RestClientTest {

    public static void main(String[] args) {
//        getForObject1();
//        exchangeObject();
//        postForJson();
//        errorDemoGetMethod();
        testExecute();
    }


    /**
     * 代码示例：请求路径中带有参数： student/str/{name}
     * <p>1、getForObject中的 uriVariables 只能用于替换url中的占位符；不能用于拼接url后的参数（如a=1&b=2格式）
     * <p>2、示例：http://127.0.0.1:8080/student/str/zhangsan
     */
    public static void getForObject1(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8081/student/str/{name}";
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("name","zhangsan");
        // 1、使用map，替换url中的占位符{name}；需要保证参数的key同占位符名称相同
        Student student = restTemplate.getForObject(url,Student.class,paramMap);
        System.out.println(url +":"+student);
        //2、使用数组来替换url中的占位符；保持顺序一致即可；
        Student student2 = restTemplate.getForObject(url,Student.class,"lisi");
        System.out.println(url +":"+student2);
    }

    /**
     * 整体说明：
     * 1、服务端如果使用 @RequestBody 注解，需要使用post方式进行提交；（使用get方式提交，不会提交body信息）
     * 2、可以使用json数据格式，或者 @RequestBody 对应的对象格式（即Student）
     * 3、可以调用exchange或者postForObject方法
     */

    /**
     * 正确示例一：
     * <p>exchange方法调用示例
     * <p>1、通过 RestTemplate.exchange 方法，调用http接口；
     * <p>2、通过 Student 对象格式调用；
     * <p>3、此示例，可以不设置 setContentType
     */
    public static void exchangeObject(){
        //1、初始化RestTemplate对象
        RestTemplate restTemplate = new RestTemplate();
        //2、初始化请求参数
        Student student = new Student("zhangsan",10000);
        //3、初始化header对象
        HttpHeaders headers = new HttpHeaders();
        //4、设置内容类型为Json
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        // 5、初始化HttpEntity对象，注意将Student对象传入，做为初始化值
        HttpEntity<Student>requestEntity = new HttpEntity<Student>(student);
        String url = "http://127.0.0.1:8081/student/post/str";
        //6、还有其他重载函数，可以选择
        ResponseEntity<Student>responseEntity = restTemplate.exchange(url,HttpMethod.POST,requestEntity,Student.class);
        System.out.println(responseEntity.getBody());
    }

    /**
     * 正确示例二：
     * <p>exchange方法调用示例
     * <p>1、通过 RestTemplate.exchange 方法，调用http接口；
     * <p>2、通过json方式，组装请求参数，并且获取结果；
     * <p>3、此示例中，必须设置 setContentType
     */
    public static void postForJson(){
        //1、初始化RestTemplate模板
        RestTemplate restTemplate = new RestTemplate();
        //2、组织请求参数，json格式（注意，如果json格式错误，将会提示XX错误）
        String usrStr = "{\"name\":\"zhaoliu\",\"age\":\"2000\"}";
        //3、初始化header对象
        HttpHeaders headers = new HttpHeaders();
        //4、设置内容类型为Json
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //5、初始化请求对象
        HttpEntity<String>requestEntity = new HttpEntity<>(usrStr,headers);
        String url = "http://127.0.0.1:8081/student/post/str" ;
        //6、调用接口
        ResponseEntity<Student>responseEntity = restTemplate.exchange(url,HttpMethod.POST,requestEntity,Student.class);
        System.out.println(responseEntity.getBody());
    }

    /**
     * 正确示例三：
     * <p>postForEntity示例
     * <p>1、通过 restTemplate.postForEntity 方法，调用http接口；
     * <p>2、通过json方式，组装请求参数，并且获取结果；
     * <p>3、此示例中，必须设置 setContentType
     */
    public static void postForObjectJson(){

        //1、初始化RestTemplate模板
        RestTemplate restTemplate = new RestTemplate();
        //2、组织请求参数，json格式（注意，如果json格式错误，将会提示XX错误）
        String usrStr = "{\"name\":\"zhaoliu\",\"age\":\"2000\"}";
        //3、初始化header对象
        HttpHeaders headers = new HttpHeaders();
        //4、设置内容类型为Json
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //5、初始化请求对象
        HttpEntity<String>requestEntity = new HttpEntity<>(usrStr,headers);
        String url = "http://127.0.0.1:8081/student/post/str" ;
        //6、调用接口
        ResponseEntity<Student> responseEntity = restTemplate.postForEntity(url,requestEntity,Student.class);
        System.out.println(responseEntity.getBody());

    }

    /**
     * 错误示例一：
     * <p>get方式，无法调用@RequestBody的接口
     * <p>1、通过get方式，请求@RequestBody注解的接口；
     * <p>2、服务端接口，无法获取Student对象的值；
     * <p>3、此方法跟postForJson的区别，就是修改了提交方式为GET
     */
    public static void errorDemoGetMethod(){
        //1、初始化RestTemplate模板
        RestTemplate restTemplate = new RestTemplate();
        //2、组织请求参数，json格式（注意，如果json格式错误，将会提示XX错误）
        String usrStr = "{\"name\":\"zhaoliu\",\"age\":\"2000\"}";
        //3、初始化header对象
        HttpHeaders headers = new HttpHeaders();
        //4、设置内容类型为Json
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //5、初始化请求对象
        HttpEntity<String>requestEntity = new HttpEntity<>(usrStr,headers);
        String url = "http://127.0.0.1:8081/student/get/str" ;
        //5、调用接口
        ResponseEntity<Student>responseEntity = restTemplate.exchange(url,HttpMethod.GET,requestEntity,Student.class);
        System.out.println(responseEntity.getBody());

    }

    public static void testExecute(){
        //1、初始化RestTemplate模板
        RestTemplate restTemplate = new RestTemplate();
        //2、组织请求参数，json格式（注意，如果json格式错误，将会提示XX错误）
        String usrStr = "{\"name\":\"zhaoliu\",\"age\":\"2000\"}";
        String url = "http://127.0.0.1:8081/student/get/str";
        Object obj = restTemplate.execute(url,HttpMethod.GET,requestCallback(),null);
        System.out.println(obj);

    }
   static RequestCallback requestCallback(){
        return  (request)->{
          HttpHeaders headers = request.getHeaders();
          headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
          headers.add("user_name","zhangsan");
//       ObjectMapper objectMapper = new ObjectMapper();
//       objectMapper.writeValue(request.getBody(),student);
    };
    }
}
