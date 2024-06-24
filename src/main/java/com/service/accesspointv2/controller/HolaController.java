package com.service.accesspointv2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    @RequestMapping("/api/hola")
    public class HolaController {

        @GetMapping
        public String decirHola() {
            return "Hola Mundo";
        }
    }

