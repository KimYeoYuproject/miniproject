package Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import DTO.CouponDTO;
import Model.CouponVO;

public class CouponController {

    private Random random = new Random();
    private CouponDTO couponDTO = new CouponDTO();

    public String randomCouponGenerator(int length) {
        int index = 0;
        String listitem = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";
        StringBuilder coupon = new StringBuilder();

        for (int i = 0; i < length; i++) {
            index = random.nextInt(listitem.length());
            coupon.append(listitem.charAt(index));
        }

        return coupon.toString();
    }

    public void couponGenerate(int number, int length) {
        for (int i = 0; i < number; i++) {
            String newCoupon = randomCouponGenerator(length);
            if (!existCoupon(newCoupon) == false) {
                couponDTO.saveByCoupon(newCoupon);
            } else {
                number--;
            }
        }
    }

    public boolean existCoupon(String coupon) {
        return couponDTO.findByCoupon(coupon.toUpperCase()) != null ? true : false;
    }

    public Optional<CouponVO> findByCoupon(String coupon) {
        return couponDTO.findByCoupon(coupon);
    }

    public boolean deleteCoupon(String coupon) {
        return couponDTO.deleteByCoupon(coupon.toUpperCase());
    }

    public boolean availableCoupon(String coupon) {
        boolean result = false;
        // 오늘과 쿠폰 날짜 차이 6개월 계산
        Optional<CouponVO> getCoupon = couponDTO.findByCoupon(coupon);
        if (getCoupon.get().getEndDate().getTime() >= new Date().getTime()
                && getCoupon.get().getAvailable() == true) {
            result = true;
        }

        return result;
    }

    public void useCoupon(String coupon) {
        Optional<CouponVO> couponVO = couponDTO.findByCoupon(coupon.toUpperCase());
        System.out.println(couponVO.get().getCoupon());

        couponVO.get().setAvailable(false);
        couponDTO.modifyByCoupon(couponVO);
    }

    public Optional<List<CouponVO>> findAllByCounpon() {
        return couponDTO.findAllByCoupon();
    }

}
