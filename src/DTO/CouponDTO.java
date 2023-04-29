package DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import Model.CouponVO;

/**
 * @author yoosc89
 */
public class CouponDTO {

    List<CouponVO> coupons = new ArrayList<>();

    public Optional<CouponVO> findByCoupon(String coupon) {
        return this.coupons.stream()
                .filter(c -> c.getCoupon().equals(coupon.toUpperCase()))
                .findFirst();

    }

    public List<CouponVO> findAllByCoupon() {
        this.coupons.sort(Comparator.comparing(CouponVO::getExpDate));
        return this.coupons;
    }

    public void saveByCoupon(String couponString) {
        String coupon = couponString;
        this.coupons.add(
                new CouponVO(coupon, LocalDateTime.now()
                        .plusMonths(6), true));
    }

    public boolean deleteByCoupon(String coupon) {
        for (int i = 0; i < this.coupons.size(); i++) {
            if (this.coupons.get(i).getCoupon().equals(coupon)) {
                this.coupons.remove(i);
                return true;
            }
        }
        return false;
    }

    public void modifyByCoupon(CouponVO couponVO) {
        for (int i = 0; i < this.coupons.size(); i++) {
            if (this.coupons.get(i).getCoupon().equals(couponVO.getCoupon())) {
                this.coupons.get(i)
                        .setCoupon(couponVO.getCoupon())
                        .setExpDate(couponVO.getExpDate())
                        .setAvailable(couponVO.getAvailable());
            }
        }
    }
}
