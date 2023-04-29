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

    public CouponVO setCoupon(String coupon) {
        this.coupon = coupon;
        return this;
    }

    public LocalDateTime getExpDate() {
        return expDate;
    }

    public CouponVO setExpDate(LocalDateTime expDate) {
        this.expDate = expDate;
        return this;
    }

    public boolean getAvailable() {
        return available;
    }

    public CouponVO setAvailable(boolean available) {
        this.available = available;
        return this;
    }

    public CouponVO build() {
        return new CouponVO(coupon, expDate, available);
    }
}
