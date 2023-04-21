package View.Testview;

import java.util.List;
import java.util.Optional;

import Controller.CouponController;
import Model.CouponVO;

public class TestYu {
    public void view() {
        new CouponController().couponGenerate(10);

        Optional<List<CouponVO>> coupons = new CouponController().findAllByCounpon();

        for (CouponVO c : coupons.get()) {
            System.out.println(c.getCoupon());
        }
    }
}
