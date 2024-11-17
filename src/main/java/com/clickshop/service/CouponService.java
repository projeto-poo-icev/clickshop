package com.clickshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickshop.dtos.CouponCreationDto;
import com.clickshop.dtos.CouponDto;
import com.clickshop.dtos.Utils;
import com.clickshop.entities.Coupon;
import com.clickshop.repositories.CouponRepository;
import com.clickshop.service.exception.CouponNotFound;

@Service
public class CouponService {
    
    @Autowired
    CouponRepository couponRepository;

    public CouponDto createCoupon(CouponCreationDto newCoupon) {
        Coupon coupon = couponRepository.save(new Coupon(newCoupon.name(), newCoupon.discount()));
        return Utils.couponModelToDto(coupon);
    }

    public CouponDto findCouponbyName(String name) {
        Coupon coupon = couponRepository.findByName(name).orElseThrow(CouponNotFound::new);
        return Utils.couponModelToDto(coupon);
    }

    public List<CouponDto> findAll() {
        List<Coupon> couponList = couponRepository.findAll();
        return Utils.couponModelListToDto(couponList);
    }
}
