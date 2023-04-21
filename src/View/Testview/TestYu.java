package View.Testview;

import java.util.Scanner;

import View.Testview.testYu.TestCoupon;
import View.Testview.testYu.TestMenu;

public class TestYu {

    private Scanner sc = new Scanner(System.in);

    public void view() {
        int number = 0;
        try {
            label: while (true) {
                System.out.println("테스트 메뉴 선택");
                System.out.println("1. 쿠폰");
                System.out.println("2. 주문메뉴");
                System.out.println("3. 종료");
                System.out.print("사용할 메뉴 선택 : ");
                number = sc.nextInt();
                sc.nextLine();
                switch (number) {
                    case 1:
                        new TestCoupon().testCoupon();
                        break;
                    case 2:
                        new TestMenu().testMenu();
                        break;
                    case 3:
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
