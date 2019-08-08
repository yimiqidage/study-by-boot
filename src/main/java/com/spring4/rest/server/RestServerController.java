package com.spring4.rest.server;

import com.spring4.rest.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Creator weishi8
 * Date&Time 2019-07-08 16:45
 * description
 */
@RestController
public class RestServerController {

    public static Map<String, Student> studentMap = new HashMap<>();

    static {
        studentMap.put("zhangsan", new Student("zhangsan", 10));
        studentMap.put("lisi", new Student("lisi", 11));
        studentMap.put("wangwu", new Student("wangwu", 12));
    }


    /**
     * 1、一般的json请求，只设置请求路径 path
     * 2、只接受get请求
     * @param name
     * @return
     */


    /**
     * 1、@RestController 标注的类，会为类下面所有的方法，应用消息转换功能（可以理解为每个方法增加@ResponseBody）
     * 1、@ResponseBody 是告知Spring，需要将返回的对象做为资源返回给客户端，并将其转换为客户端可以接收的形式（如json、xml、txt）
     * 2、@ResponseBody 注解可以省略，因为在类级别，已经添加了 @RestController 注解；
     * 3、@RequestBody 是告知Spring，查找一个消息转换器，将参数转换为对象；
     * 4、@PathVariable 是用于获取URL路径中的变量值，如xx.com/{id} @PathVariable(value = "id") String id 获取路径中的值
     * 5、@RequestParam 获取请求的参数值，如 xx.com?a=1，获取其中的a的值
     *
     * @param name
     * @param age
     * @param student
     * @return
     */
//    @ResponseBody
    @GetMapping(path = "/student/{name}")
    public Student getStudentByNameCommon(@PathVariable(value = "name") String name,
                                          @RequestParam(defaultValue = "2") int age,
                                          @RequestBody Student student,
                                          @RequestHeader HttpHeaders headers) {
        System.out.println(String.format("name:%s,age:%s", name, age));
        System.out.println(student);
        return new Student(name, age);
    }

    /**
     * http://127.0.0.1:8080/student/str/{name}
     *
     * @param name
     * @return
     */
    @GetMapping(path = "/student/str/{name}")
    public Student getStudentByNameStr(@PathVariable(value = "name") String name) {
        return studentMap.get(name);
    }

    /**
     * @param name
     * @return
     */
    @GetMapping(path = "/student/str")
    public Student getStudentByNameStrParam(@RequestParam String name,
                                            @RequestParam(defaultValue = "100") int age) {
        return new Student(name, age);
    }

    /**
     *
     * @param student
     * @return
     */
    @RequestMapping(path = "/student/obj/str")
    public Student getStudentByNameObj(@RequestBody(required = false) Student student){
        if(student==null){
            return new Student("za",111222);
        }
        return new Student(student.getName(),3333);
    }

    /**
     *
     * @param
     * @return
     */
    @GetMapping(path = "/student/blankstr")
    public Student getStudentByNameStrBlank(){
        return new Student("ddd",12);
    }


    /**
     *
     * 1、consumes属性标记，只处理 Content-Type="application/json"的数据；
     * 2、只接受Post请求
     * 3、produces 表明客户端能接收 "application/json"
     * @RequestParam 表明此参数不能为空
     * @param name
     * @return
     */
    @ResponseBody
    @PostMapping(path = "/student/consumes/{name}",consumes = "application/json")
    public Student getStudentByNameConsumes(@PathVariable(value = "name") String name){
        return studentMap.get(name);
    }


    /**
     * 代码示例：在接口中，增加返回header中的Location信息
     * @param name
     * @param uriComponentsBuilder
     * @return
     */
    @GetMapping("/student/entity/{name}")
    public ResponseEntity<Student>getStuEntity(@PathVariable String name,UriComponentsBuilder uriComponentsBuilder){
        HttpHeaders headers = new HttpHeaders();
        URI uri = uriComponentsBuilder.path("/student/entity/").path(name).build().toUri();
        headers.setLocation(uri);
        return new ResponseEntity<Student>(studentMap.get(name),headers, HttpStatus.OK);
    }


}
