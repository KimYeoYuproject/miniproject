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

    /**
     * 쿠폰 생성기
     * 
     * @param length 쿠폰 번호 길이
     * @return
     */
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

    /**
     * 쿠폰 생성 메소드
     * 
     * @param number 쿠폰 생성 개수
     * @param length 쿠폰 번호 길이
     */
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

    /**
     * 쿠폰 존재 여부 확인
     * 
     * @param coupon 쿠폰 번호
     * @return
     */
    public boolean existCoupon(String coupon) {
        return couponDTO.findByCoupon(coupon.toUpperCase()) != null ? true : false;
    }

    /**
     * 쿠폰 정보를 찾아주는 메소드
     * 
     * @param coupon 쿠폰 번호
     * @return
     */
    public Optional<CouponVO> findByCoupon(String coupon) {
        return couponDTO.findByCoupon(coupon);
    }

    /**
     * 생성된 쿠폰 전체 조회
     * 
     * @return
     */
    public Optional<List<CouponVO>> findAllByCounpon() {
        return couponDTO.findAllByCoupon();
    }

    /**
     * 쿠폰 삭제 메소드
     * 
     * @param coupon 쿠폰번호
     * @return
     */
    public boolean deleteCoupon(String coupon) {
        return couponDTO.deleteByCoupon(coupon.toUpperCase());
    }

    /**
     * 쿠폰 사용 가능 여부 확인
     * 
     * @param coupon 쿠폰 번호
     * @return
     */
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

    /**
     * 쿠폰 사용하기, MeneVO.available 값 false로 수정 하는 메소드
     * 
     * @param coupon 쿠폰 번호
     */
    public void useCoupon(String coupon) {
        Optional<CouponVO> couponVO = couponDTO.findByCoupon(coupon.toUpperCase());
        System.out.println(couponVO.get().getCoupon());

        couponVO.get().setAvailable(false);
        couponDTO.modifyByCoupon(couponVO);
    }

}
