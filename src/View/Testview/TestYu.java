package View.Testview;

import java.util.Scanner;

import View.CoffeMenuView;
import View.CouponView;

public class TestYu {

    private Scanner sc = new Scanner(System.in);

    public void view() {
        int number = 0;
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

                    break;
                case 2:

                    break;
                case 3:
                    break label;
                default:
                    System.out.println("없는 메뉴 입니다.");
                    break;

            }
        }
    }
}
