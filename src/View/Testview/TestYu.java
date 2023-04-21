package View.Testview;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import Controller.CouponController;
import Model.CouponVO;

public class TestYu {
    private CouponController controller = new CouponController();
    private Scanner sc = new Scanner(System.in);
    private Optional<List<CouponVO>> coupons = controller.findAllByCounpon();

    public void findCoupon() {
        String findCoupon = null;
        System.out.print("찾을 쿠폰입력 : ");
        findCoupon = sc.nextLine();
        try {

            System.out.println(controller.findByCoupon(findCoupon).get());
        } catch (NullPointerException e) {
            System.out.println("찾는 쿠폰이 없습니다");
        }
    }

    public void printCoupon() {
        int count = 0;
        for (CouponVO c : coupons.get()) {
            System.out.printf("%2d. 쿠폰번호 : %s | 유효기간 : %s | 사용가능 : %s\n", count++, c.getCoupon(),
                    new SimpleDateFormat("YYYY-MM-dd").format(c.getEndDate()), c.getAvailable());
        }
    }

    public void delCoupon() {
        String delCoupon = null;
        System.out.print("삭제할 쿠폰 입력 : ");
        delCoupon = sc.nextLine();
        if (controller.deleteCoupon(delCoupon)) {
            System.out.println("삭제 완료");
        } else {
            System.out.println("쿠폰을 찾을 수 없습니다.");
        }
        ;
    }

    public void useCoupon() {
        String useCoupon = null;
        System.out.print("사용할 쿠폰 선택 쿠폰 입력 : ");
        useCoupon = sc.nextLine();
        try {

            controller.useCoupon(useCoupon);
        } catch (NullPointerException e) {
            System.out.println("쿠폰을 찾을 수 없습니다");
        }
    }

    public void createCoupon(int number) {
        for (int i = 0; i < number; i++) {
            controller.couponGenerate(20);
        }
    }

    public void view() {

        int number = 0;
        try {
            label: while (true) {
                System.out.println("테스트 메뉴 선택");
                System.out.println("1. 쿠폰 생성 ");
                System.out.println("2. 쿠폰 조회");
                System.out.println("3. 쿠폰 전체 조회");
                System.out.println("4. 쿠폰 사용");
                System.out.println("5. 쿠폰 삭제");
                System.out.println("6.종료 ");
                System.out.print("사용할 메뉴 선택 : ");
                number = sc.nextInt();
                sc.nextLine();
                switch (number) {
                    case 1:
                        createCoupon(20);
                        break;
                    case 2:
                        findCoupon();
                        break;
                    case 3:
                        printCoupon();
                        break;
                    case 4:
                        useCoupon();
                        break;
                    case 5:
                        delCoupon();
                        break;
                    case 6:
                        break label;
                    default:
                        System.out.println("없는 메뉴 입니다.");
                        break;

                }
            }

        } catch (Exception e) {
            System.out.println("찰못 입력햇습니다.");
        }

    }
}
