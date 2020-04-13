package com.homework.homework.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FuelConsumptionController {

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public void registerConsumptionRecord() {
    }
}
