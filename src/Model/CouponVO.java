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

    public Date getEndDate() {
        return expDate;
    }

    public void setEndDate(Date endDate) {
        this.expDate = endDate;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
