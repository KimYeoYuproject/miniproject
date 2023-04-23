package View;

import java.io.IOException;
import java.util.Scanner;

import Controller.CouponController;
import Controller.MenuController;

public class AdminView {

    private CouponController couponController;
    private MenuController menuController;

    private Scanner sc = new Scanner(System.in);
    private int number;

    public AdminView(CouponController couponController, MenuController menuController) {
        this.couponController = couponController;
        this.menuController = menuController;
    }

    public void adminView() throws IOException {

        label: while (true) {
            ClearConsole.clear();
            System.out.println("======= 관리자 메뉴 ========");
            System.out.println("1. 주문 관리");
            System.out.println("2. 계정 관리");
            System.out.println("3. 쿠폰 관리");
            System.out.println("4. 주문 내역");
            System.out.println("9. 관리자 메뉴 종료");
            System.out.println();
            System.out.print("메뉴를 입력하세요 : ");
            number = sc.nextInt();
            sc.nextLine();

            switch (number) {
                case 1:
                    new CoffeMenuView(this.menuController).coffeMenu();
                    break;
                case 2:
                    break;
                case 3:
                    new CouponView(this.couponController).couponView();
                    break;
                case 4:
                    break;
                case 9:
                    System.out.println("관리자 메뉴 종료 합니다");
                    break label;
                default:
                    System.out.println("없는 메뉴 입니다.");
                    break;
            }

        }
    }
}
