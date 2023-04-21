package DTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import Model.CouponVO;

public class CouponDTO {

    private List<CouponVO> coupons = new ArrayList<>();

    public Optional<CouponVO> findByCoupon(String coupon) {
        for (CouponVO c : coupons) {
            if (c.getCoupon().equals(coupon.toUpperCase())) {
                return Optional.of(c);
            }
        }
        return null;

    }

    public Optional<List<CouponVO>> findAllByCoupon() {
        return Optional.of(coupons);
    }

    public void saveByCoupon(String couponString) {
        String coupon = couponString;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, 6);
        coupons.add(new CouponVO(coupon, cal.getTime(), true));
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

    public void modifyByCoupon(Optional<CouponVO> couponVO) {
        for (int i = 0; i < coupons.size(); i++) {
            if (coupons.get(i).getCoupon() == couponVO.get().getCoupon()) {
                coupons.get(i).setCoupon(couponVO.get().getCoupon());
                coupons.get(i).setEndDate(couponVO.get().getEndDate());
                coupons.get(i).setAvailable(couponVO.get().getAvailable());
            }
        }
    }
}
