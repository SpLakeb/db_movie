package com.cinema.controller;

import com.cinema.bean.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/seats")
    public int seats(){
        return 144;
    }

    @GetMapping("/seatsHasSale")
    public List<Integer> seatsHasSale(){

        return Arrays.asList(1,3,4,18,29,67);
    }

    @PostMapping("/chooseSeats")
    public Map<String, String> chooseSeats(@RequestBody Map<String, String> map){

        return map;

    }

}
