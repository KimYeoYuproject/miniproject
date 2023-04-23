package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
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
            if (existCoupon(newCoupon) == false) {
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
    public CouponVO findByCoupon(String coupon) {
        return couponDTO.findByCoupon(coupon);
    }

    /**
     * 생성된 쿠폰 전체 조회
     * 
     * @return
     */
    public List<CouponVO> findAllByCounpon() {
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
        CouponVO getCoupon = couponDTO.findByCoupon(coupon);
        try {
            if (getCoupon.getExpDate().getTime() >= new Date().getTime()
                    && getCoupon.getAvailable() == true) {
                result = true;
            }
        } catch (NullPointerException e) {
            System.out.println("쿠폰이 존재 하지 않습니다. 쿠폰을 생성해 주세요");
        }

        return result;
    }

    /**
     * 쿠폰 사용하기, MeneVO.available 값 false로 수정 하는 메소드
     * 
     * @param coupon 쿠폰 번호
     */
    public void useCoupon(String coupon) {
        CouponVO couponVO = couponDTO.findByCoupon(coupon.toUpperCase());
        System.out.println(couponVO.getCoupon());

        couponVO.setAvailable(false);
        couponDTO.modifyByCoupon(couponVO);
    }

    /**
     * 생성된 쿠폰 리스트 전체 Csv파일로 조저
     */
    public void wirteCsvToCoupon() {

        File mkdirs = new File("./static/output/coupon");
        mkdirs.mkdirs();

        String fileName = "coupon";
        String fileExt = ".csv";

        BufferedWriter bw = null;
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        try {
            bw = new BufferedWriter(
                    new FileWriter(mkdirs + "/" + new Date().getTime() + fileName + fileExt, Charset.forName("UTF-8")));
            bw.write('\ufeff'); // 엑셀 한글 깨짐 대비 해결
            bw.write("쿠폰번호,유효기간,사용가능");
            bw.newLine();
            for (CouponVO c : couponDTO.findAllByCoupon()) {
                System.out.println(c);
                bw.write(c.getCoupon() + "," + sdf.format(c.getExpDate()) + "," + (c.getAvailable() ? "가능" : "불가능"));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
