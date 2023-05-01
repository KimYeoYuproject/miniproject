package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import DTO.CouponDTO;
import Model.CouponVO;

/**
 * @author yoosc89
 */
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
            System.out.println(newCoupon);
            if (existCoupon(newCoupon) == false) {
                this.couponDTO.saveByCoupon(newCoupon);
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
        return this.couponDTO.findByCoupon(
                coupon.toUpperCase()).orElse(null) != null ? true : false;
    }

    /**
     * 쿠폰 정보를 찾아주는 메소드
     * 
     * @param coupon 쿠폰 번호
     * @return
     */
    public CouponVO findByCoupon(String coupon) {
        return this.couponDTO.findByCoupon(coupon)
                .orElse(null);
    }

    /**
     * 생성된 쿠폰 전체 조회
     * 
     * @return
     */
    public List<CouponVO> findAllByCounpon() {
        return this.couponDTO.findAllByCoupon();
    }

    /**
     * 쿠폰 삭제 메소드
     * 
     * @param coupon 쿠폰번호
     * @return
     */
    public boolean deleteCoupon(String coupon) {
        return this.couponDTO.deleteByCoupon(coupon.toUpperCase());
    }

    /**
     * 쿠폰 사용 가능 여부 확인
     * 쿠폰 유효기간 기본 6개월 유효기간 = 오늘 까지 이용가능, 사용가능 여부도 체크
     * 
     * @param coupon 쿠폰 번호
     * @return
     */
    public boolean availableCoupon(String coupon) {
        boolean result = false;
        CouponVO getCoupon = this.couponDTO.findByCoupon(coupon).orElse(null).build();
        try {
            if (getCoupon.getExpDate().isAfter(LocalDateTime.now())
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
        CouponVO couponVO = couponDTO.findByCoupon(coupon.toUpperCase()).orElse(null).build();
        couponVO.setAvailable(false);
        this.couponDTO.modifyByCoupon(couponVO);
    }

    /**
     * 생성된 쿠폰 리스트 전체 Csv파일로 조저
     */
    public void wirteCsvToCoupon() {

        File mkdirs = new File("./static/output/coupon");
        if (!mkdirs.exists()) {
            System.out.println("폴더 생성");
            mkdirs.mkdirs();
        }

        String fileName = "coupon";
        String fileExt = ".csv";
        String prefixFileName = LocalDateTime.now()
                .format(DateTimeFormatter
                        .ofPattern("uuuuMMddHHmmssn"));
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(
                    new FileWriter(mkdirs + "/"
                            + prefixFileName + fileName + fileExt,
                            Charset.forName("UTF-8")));

            bw.write('\ufeff'); // 엑셀 한글 깨짐 대비 해결
            bw.write("쿠폰번호,유효기간,사용가능");
            bw.newLine();

            for (CouponVO c : this.couponDTO.findAllByCoupon()) {
                bw.write(c.getCoupon() +
                        "," + c.getExpDate().format(DateTimeFormatter.ofPattern("uuuu-MM-dd"))
                        + "," + (c.getAvailable() ? "가능" : "불가능"));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("파일 및 폴더를 찾을 찾을 수 없습니다.");

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
