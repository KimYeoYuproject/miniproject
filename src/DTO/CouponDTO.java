package DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Model.CouponVO;

public class CouponDTO {

    List<CouponVO> coupons = new ArrayList<>();

    public Optional<CouponVO> findByCoupon(String coupon) {
        return coupons.stream()
                .filter(c -> c.getCoupon().equals(coupon.toUpperCase())).findFirst();

    }

    public List<CouponVO> findAllByCoupon() {
        return coupons;
    }

    public void saveByCoupon(String couponString) {
        String coupon = couponString;
        coupons.add(new CouponVO(coupon, LocalDateTime.now().plusMonths(6), true));
    }

    public boolean deleteByCoupon(String coupon) {
        boolean isExist = false;
        for (int i = 0; i < coupons.size(); i++) {
            if (coupons.get(i).getCoupon().equals(coupon)) {
                coupons.remove(i);
                isExist = true;
            }
        }
        return isExist;
    }

    public void modifyByCoupon(CouponVO couponVO) {
        for (int i = 0; i < coupons.size(); i++) {
            if (coupons.get(i).getCoupon() == couponVO.getCoupon()) {
                coupons.get(i).setCoupon(couponVO.getCoupon());
                coupons.get(i).setExpDate(couponVO.getExpDate());
                coupons.get(i).setAvailable(couponVO.getAvailable());
            }
        }
    }
}
