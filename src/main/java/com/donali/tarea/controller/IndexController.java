package com.donali.tarea.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class IndexController {
    
    @RequestMapping(value="/ingresar", method=RequestMethod.GET)
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value="ingresar", method=RequestMethod.POST)
    public ModelAndView formMethod(
        @RequestParam String name,
        @RequestParam String lastname,
        @RequestParam String birthdate,
        @RequestParam String birthplace,
        @RequestParam String school,
        @RequestParam String homephone,
        @RequestParam String mobile
    ) {
        ArrayList<String> errorList = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        
        // Validacion Nombre
        if(name.length()<=0)
            errorList.add("El nombre no puede estar vacio\n");
        if(name.length()>25)
            errorList.add("El nombre no puede superar los 25 caracteres\n");

        // Validacion Apellidos
        if(lastname.length()<=0)
            errorList.add("El apellido no puede estar vacio\n");
        if(lastname.length()>25)
            errorList.add("El apellido no puede superar los 25 caracteres\n");

        // Validacion fecha nacimiento
        try{
            Date paramDate = new SimpleDateFormat("yyyy-MM-dd").parse("2003-01-01");
            Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);

            if(birthDate.compareTo(paramDate) < 0)
                errorList.add("La fecha ingresada es menor al primero de enero de 2003");

        }catch(ParseException e){
            System.err.println("No se ha ingresado una fecha valida");
            errorList.add("No se ha ingresado una fecha valida");
            // System.out.println(e.toString());
        }

        //Validacion dia de nacimiento
        if(birthplace.length()<=0)
            errorList.add("El lugar de nacimiento no puede estar vacio\n");
        if(birthplace.length()>25)
            errorList.add("El lugar de nacimiento no puede superar los 25 caracteres\n");

        //Validacion colegio
        if(birthplace.length()<=0)
            errorList.add("El colegio no puede estar vacio\n");
        if(birthplace.length()>25)
            errorList.add("El colegio no puede superar los 25 caracteres\n");

        //Validacion telefono fijo y movil
        try {
           Integer.parseInt(homephone);
           Integer.parseInt(mobile);

           if(homephone.length()< 8 || homephone.length() >8)
                errorList.add("El telefono fijo debe ser exactamente de 8 numeros");
            
            if(mobile.length()< 8 || mobile.length() >8)
                errorList.add("El telefono movil debe ser exactamente de 8 numeros");

        } catch (NumberFormatException e) {
            System.err.println("No se ha ingresado un telefono fijo o movil valido");
            errorList.add("No se ha ingresado un telefono fijo o movil valido");
        }
               

        modelAndView.addObject("errors",errorList);
        modelAndView.addObject("name", name);
        modelAndView.setViewName("result");
        
        return modelAndView;
    }
    

}