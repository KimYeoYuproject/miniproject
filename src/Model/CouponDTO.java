package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yoosc89
 */
public class CouponDTO {

    private String coupon;

    private LocalDateTime expDate;

    private boolean available = true;

    public CouponDTO(String coupon, LocalDateTime expDate, boolean available) {
        this.coupon = coupon;
        this.expDate = expDate;
        this.available = available;
    }

    public CouponDTO() {
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

    public CouponDTO setCoupon(String coupon) {
        this.coupon = coupon;
        return this;
    }

    public LocalDateTime getExpDate() {
        return expDate;
    }

    public CouponDTO setExpDate(LocalDateTime expDate) {
        this.expDate = expDate;
        return this;
    }

    public boolean getAvailable() {
        return available;
    }

    public CouponDTO setAvailable(boolean available) {
        this.available = available;
        return this;
    }

    public CouponDTO build() {
        return new CouponDTO(coupon, expDate, available);
    }
}
