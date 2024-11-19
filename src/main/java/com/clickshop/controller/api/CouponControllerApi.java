package com.clickshop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clickshop.dtos.CouponCreationDto;
import com.clickshop.dtos.CouponDto;
import com.clickshop.service.CouponService;

@RestController
@RequestMapping
public class CouponControllerApi {
    
    @Autowired
    private CouponService couponService;

    @PostMapping(value = "/coupon")
    public ResponseEntity<CouponDto> createCoupon(@RequestBody CouponCreationDto coupon) {
        return ResponseEntity.status(HttpStatus.CREATED).body(couponService.createCoupon(coupon));
    }

    @GetMapping(value = "/coupon")
    public ResponseEntity<CouponDto> findByName(@RequestParam(name = "name") String coupon) {
        return ResponseEntity.status(HttpStatus.OK).body(couponService.findCouponbyName(coupon));
    }

    
}
