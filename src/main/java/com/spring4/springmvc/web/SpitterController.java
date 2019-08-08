package com.spring4.springmvc.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SpitterController {

//    @RequestMapping(path = "/api/books",method = RequestMethod.GET)
    @RequestMapping("/api/books")
    @GetMapping
    public String apiBooks(HttpServletRequest request){
        return request.getServletPath()+"/api/books";
    }

    @RequestMapping("/spitter/security")
    @GetMapping
    public String security(HttpServletRequest request){
        System.out.println("spitter/security/");
        return "security/security";
    }

    @RequestMapping("/spitter/me")
    @GetMapping
    public String me(HttpServletRequest request){
        System.out.println("spitter/me/");
        return request.getServletPath()+"/spitter/me";
    }

    @RequestMapping("/spitter/me1")
    @GetMapping
    public String me1(HttpServletRequest request){
        return request.getServletPath()+"/spitter/me1";
    }

    @RequestMapping("/spitter/me2")
    @GetMapping
    public String me2(HttpServletRequest request){
        return request.getServletPath()+"/spitter/me2";
    }

    @RequestMapping("/spitter/me3")
    @GetMapping
    public String me3(HttpServletRequest request){
        return request.getServletPath()+"/spitter/me3";
    }

    @RequestMapping("/spitter/me4")
    @GetMapping
    public String me4(HttpServletRequest request){
        return request.getServletPath()+"/spitter/me4";
    }

    @RequestMapping("/spitter/me5")
    @GetMapping
    public String me5(HttpServletRequest request){
        return request.getServletPath()+"/spitter/me5";
    }

    @RequestMapping("/spitter/me6")
    @GetMapping
    public String me6(HttpServletRequest request){
        return request.getServletPath()+"/spitter/me6";
    }




}
