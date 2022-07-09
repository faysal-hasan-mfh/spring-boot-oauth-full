package com.mfh.combinationofthree.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

  @GetMapping("/success")
  public ResponseEntity<String> getHome() {
    return ResponseEntity.ok("Success");
  }
}
