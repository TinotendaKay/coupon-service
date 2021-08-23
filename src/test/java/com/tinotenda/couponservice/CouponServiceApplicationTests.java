package com.tinotenda.couponservice;

import com.tinotenda.couponservice.controller.CouponController;
import com.tinotenda.couponservice.model.Coupon;
import com.tinotenda.couponservice.repos.CouponRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CouponServiceApplicationTests {

    public static final String SUPERSALE = "SUPERSALE";
    @Mock
    private CouponRepo repo;
    @InjectMocks
    private CouponController couponController;

    @Test
    public void testCreate() {
        Coupon coupon = new Coupon();
        coupon.setCode(SUPERSALE);

        when(repo.save(coupon)).thenReturn(coupon);
        Coupon couponCreated = couponController.create(coupon);
        verify(repo).save(coupon);
        assertNotNull(couponCreated);
        assertEquals(SUPERSALE, couponCreated.getCode());
    }

    @Test
    public void testCreate_When_Coupon_Is_Null() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            couponController.create(null);
        });
    }

    @Test
    public void testGetCoupon() {
        Coupon coupon = new Coupon();
        coupon.setCode(SUPERSALE);
        coupon.setId(1234L);
        coupon.setDiscount(new BigDecimal(10));
        coupon.setExpDate("28-08-2021");

        when(repo.findByCode(coupon.getCode())).thenReturn(coupon);
        Coupon foundCoupon = couponController.getCoupon(SUPERSALE);

        verify(repo).findByCode(SUPERSALE);
        assertNotNull(foundCoupon);
        assertEquals(SUPERSALE, foundCoupon.getCode());
        assertEquals(new BigDecimal(10), foundCoupon.getDiscount());
    }

}
