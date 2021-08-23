package com.tinotenda.couponservice.controller;

import com.tinotenda.couponservice.model.Coupon;
import com.tinotenda.couponservice.repos.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/couponapi")
public class CouponController {
    @Autowired
    CouponRepo repo;

    @PostMapping(value = "/coupons")
    public Coupon create(@RequestBody Coupon coupon) {
        if (coupon==null) {
            throw new IllegalArgumentException("Coupon cannot be null");
        }
        return repo.save(coupon);
    }

    @GetMapping(value = "/coupons/{code}")
    public Coupon getCoupon(@PathVariable("code") String code) {
        return repo.findByCode(code);

    }
}
