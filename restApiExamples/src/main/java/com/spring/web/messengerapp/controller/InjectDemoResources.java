package com.spring.web.messengerapp.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@RestController
public class InjectDemoResources {

    @Context
    private UriInfo uriInfo;

    @RequestMapping(value = "/annotations/{param}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN)
    public String getParamsUsingAnnotations(@PathVariable("param") String matrixParam){
        return "matrixParam:"+matrixParam;
    }

    @RequestMapping(value = "/annotations", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN)
    public String getParamsUsingAnnotations2(@RequestParam(defaultValue = "5") String matrixParam){
        return "matrixParam:"+matrixParam;
    }

    @RequestMapping(value = "/annotations/context", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN)
    public String getParamsUsingAnnotations3(){
        return "matrixParam:"+uriInfo.getAbsolutePath();
    }
}
