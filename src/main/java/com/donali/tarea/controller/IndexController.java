package com.donali.tarea.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class IndexController {
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value="formMethod", method=RequestMethod.POST)
    public ModelAndView formMethod(
        @RequestParam String name
    ) {
        String errores = "";
        ModelAndView modelAndView = new ModelAndView();
        
        if(name.length()<=0)
            errores = errores.concat("El nombre no puede estar vacio");
        if(name.length()>25)
            errores = errores.concat("El nombre no puede superar los 25 caracteres");

        if(errores.length() > 0)
            name = errores;
        modelAndView.addObject("name", name);
        modelAndView.setViewName("result");
        
        return modelAndView;
    }
    

}