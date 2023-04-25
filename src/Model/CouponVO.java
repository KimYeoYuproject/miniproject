package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yoosc89
 */
public class CouponVO {

    private String coupon;

    private LocalDateTime expDate;

    private boolean available = true;

    public CouponVO(String coupon, LocalDateTime expDate, boolean available) {
        this.coupon = coupon;
        this.expDate = expDate;
        this.available = available;
    }

    public CouponVO() {
    }

    @Override
    public String toString() {
        return "쿠폰명 : " + coupon
                + " | 유효기간 : " + expDate.format(DateTimeFormatter.ofPattern("uuuu-MM-dd"))
                + " | 사용가능 : " + available;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public LocalDateTime getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDateTime expDate) {
        this.expDate = expDate;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
