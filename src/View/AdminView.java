package View;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Controller.CouponController;
import Controller.MenuController;
import DTO.OrderDTO;

/**
 * @author yoosc89
 */
public class AdminView {

    private CouponController couponController;
    private MenuController menuController;
    private OrderDTO orderDTO;

    private Scanner sc = new Scanner(System.in);
    private int number;

    public AdminView(CouponController couponController, MenuController menuController,
            OrderDTO orderDTO) {
        this.couponController = couponController;
        this.menuController = menuController;
        this.orderDTO = orderDTO;
    }

    public void adminView() throws IOException {
        try {
            label: while (true) {
                ClearConsole.clear();
                System.out.println("======= 관리자 메뉴 ========");
                System.out.println("1. 메뉴 관리");
                System.out.println("2. 계정 관리");
                System.out.println("3. 쿠폰 관리");
                System.out.println("4. 주문 내역");
                System.out.println("9. 관리자 메뉴 종료");
                System.out.println();
                System.out.print("메뉴를 입력하세요 : ");
                number = sc.nextInt();
                sc.nextLine();
                ClearConsole.clear();
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
                        new AdminOrderView(this.orderDTO).adminOrderView();
                        break;
                    case 9:
                        System.out.println("관리자 메뉴 종료 합니다");
                        break label;
                    default:
                        System.out.println("없는 메뉴 입니다.");
                        break;
                }

            }
        } catch (InputMismatchException e) {
            System.out.println("지원 하지 않는 입력 코드입니다.");
        }

    }

}
