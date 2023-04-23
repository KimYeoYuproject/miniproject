package Model;

import java.util.Date;

public class CouponVO {

    private String coupon;

    private Date expDate;

    private boolean available = true;

    public CouponVO(String coupon, Date expDate, boolean available) {
        this.coupon = coupon;
        this.expDate = expDate;
        this.available = available;
    }

    public CouponVO() {
    }

    @Override
    public String toString() {
        return "Coupon [coupon=" + coupon + ", expDate=" + expDate + ", vailable=" + available + "]";
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
