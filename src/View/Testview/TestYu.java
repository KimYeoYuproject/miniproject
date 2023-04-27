package View.Testview;

import java.io.IOException;
import java.util.Scanner;

import Controller.CouponController;
import Controller.MenuController;
import DTO.OrderDTO;
import View.AdminView;
import View.ClearConsole;

public class TestYu {
    private CouponController couponController = new CouponController();
    private MenuController menuController = new MenuController();
    private OrderDTO orderDTO = new OrderDTO();

    private Scanner sc = new Scanner(System.in);

    public void view() {
        ClearConsole.clear();
        couponController.couponGenerate(10, 20);
        int number = 0;
        label: while (true) {
            ClearConsole.clear();
            System.out.println("테스트 메뉴 선택");
            System.out.println("1. 관리자페이지");
            System.out.println("9. 종료");
            System.out.println();
            System.out.print("사용할 메뉴 선택 : ");
            number = sc.nextInt();
            sc.nextLine();
            switch (number) {
                case 1:
                    try {
                        new AdminView(couponController, menuController, orderDTO).adminView();
                    } catch (IOException e) {
                        System.out.println("오류가 발생했습니다.");
                        e.printStackTrace();
                    }
                case 9:
                    break label;
                default:
                    System.out.println("없는 메뉴 입니다.");
                    break;

            }
        }
    }
}
