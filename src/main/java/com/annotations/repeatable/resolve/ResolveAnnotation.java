package com.annotations.repeatable.resolve;

import com.annotations.repeatable.Author;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Set;

/**
 * 演示如何解析一个注解
 */
@RestController
public class ResolveAnnotation {

    @RequestMapping("/resolve2")
    public String index(){
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false); // 不使用默认的TypeFilter
        provider.addIncludeFilter(new AnnotationTypeFilter(Controller.class));
        Set<BeanDefinition> beanDefinitionSet = provider.findCandidateComponents("com.annotations.repeatable");
        beanDefinitionSet.stream().forEach(bean->{
            Author[] authors = bean.getClass().getAnnotationsByType(Author.class);
            Arrays.asList(authors).forEach(author -> System.out.println(author.name()));
        });
        System.out.println("resolve执行完毕");
        return "resolve执行完毕";
    }

}
