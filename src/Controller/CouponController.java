package Controller;

import java.util.Optional;
import java.util.Random;

import DTO.CouponDTO;
import Model.CouponVO;

public class CouponController {

    Random random = new Random();
    CouponVO couponVO = new CouponVO();
    CouponDTO couponDTO = new CouponDTO();

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

    public void couponGenerate(int number) {
        couponDTO.saveByCoupon(randomCouponGenerator(number));
    }

    public boolean existCoupon(String coupon) {
        return couponDTO.findByCoupon(coupon.toUpperCase()) != null ? true : false;
    }

    public void deleteCoupon(String coupon) {
        couponDTO.deleteByCoupon(coupon.toUpperCase());
    }

    public boolean availableCoupon(String coupon) {
        boolean result = false;
        // 오늘과 쿠폰 날짜 차이 6개월 계산
        Optional<CouponVO> date = couponDTO.findByCoupon(coupon);

        return result;
    }

    public void useCoupon(String coupon) {
        Optional<CouponVO> couponVO = couponDTO.findByCoupon(coupon.toUpperCase());
        couponVO.get().setAvailable(false);
        couponDTO.modifyByCoupon(couponVO);
    }

}
