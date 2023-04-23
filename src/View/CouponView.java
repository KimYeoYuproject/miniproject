package View;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import Controller.CouponController;
import Model.CouponVO;

public class CouponView {
    private CouponController controller;
    private Scanner sc = new Scanner(System.in);

    public CouponView(CouponController controller) {
        this.controller = controller;
    }

    public void findCoupon() {
        ClearConsole.clear();
        String findCoupon = null;
        System.out.print("찾을 쿠폰입력 : ");
        findCoupon = sc.nextLine();
        try {

            System.out.println(controller.findByCoupon(findCoupon));
        } catch (NullPointerException e) {
            System.out.println("찾는 쿠폰이 없습니다");
        }
    }

    public void printCoupon() {
        ClearConsole.clear();
        List<CouponVO> coupons = controller.findAllByCounpon();
        int count = 0;
        for (CouponVO c : coupons) {
            System.out.printf("%2d. 쿠폰번호 : %s | 유효기간 : %s | 사용가능 : %s\n",
                    count++,
                    c.getCoupon(),
                    new SimpleDateFormat("YYYY-MM-dd").format(c.getExpDate()),
                    c.getAvailable());
        }
    }

    public void delCoupon() {
        ClearConsole.clear();
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
        ClearConsole.clear();
        String useCoupon = null;
        System.out.print("사용할 쿠폰 선택 쿠폰 입력 : ");
        useCoupon = sc.nextLine();
        try {
            controller.useCoupon(useCoupon);
        } catch (NullPointerException e) {
            System.out.println("쿠폰을 찾을 수 없습니다");
        }
    }

    public void createCoupon(int number, int length) {
        controller.couponGenerate(number, length);
    }

    public void availableCoupon() {
        ClearConsole.clear();
        String useCoupon = null;
        System.out.print("사용할 쿠폰 선택 쿠폰 입력 : ");
        useCoupon = sc.nextLine();
        if (controller.availableCoupon(useCoupon)) {
            System.out.println("사용가능");
        } else {
            System.out.println("사용불가");
        }
    }

    public void wirteCsvToCoupon() {
        controller.wirteCsvToCoupon();
    }

    public void couponView() {
        int number = 0;

        label: while (true) {
            ClearConsole.clear();
            System.out.println("1. 쿠폰 생성 ");
            System.out.println("2. 쿠폰 조회");
            System.out.println("3. 쿠폰 전체 조회");
            System.out.println("4. 쿠폰 사용");
            System.out.println("5. 쿠폰 사용 가능 여부 조회");
            System.out.println("6. 쿠폰 삭제");
            System.out.println("7. 쿠폰 파일로 저장");
            System.out.println("9. 종료 ");
            System.out.println();
            System.out.print("사용할 메뉴 선택 : ");
            number = sc.nextInt();
            sc.nextLine();

            switch (number) {
                case 1:
                    createCoupon(10, 20);
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
                    availableCoupon();
                    break;
                case 6:
                    delCoupon();
                    break;
                case 7:
                    wirteCsvToCoupon();
                    break;
                case 9:
                    break label;
                default:
                    System.out.println("없는 메뉴 입니다.");
                    break;

            }
        }

    }

}
