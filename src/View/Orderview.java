package View;

import java.util.InputMismatchException;
import java.util.Scanner;

import Controller.OrderController;

/**
 * @author yoosc89
 */
public class Orderview {

    private OrderController orderController;
    private Scanner sc = new Scanner(System.in);

    public Orderview(OrderController orderController) {
        this.orderController = orderController;
    }

    public void findByOrder() {
        orderController.searcOrderByNo();
    }

    public void findAllByOrder() {
        orderController.selectAll();
    }

    public void deleteFindByOrder() {
    }

    public void orderView() throws InputMismatchException {
        int number = 0;
        label: while (true) {
            ClearConsole.clear();
            System.out.println("======== 주문 내역 페이지 =========");
            System.out.println("1. 주문 내역 조회 ");
            System.out.println("2. 주문 내역 전체 조회 ");
            System.out.println("3. 주문 내역 삭제 ");
            System.out.println("9. 종료 ");
            number = sc.nextInt();
            sc.nextLine();
            switch (number) {
                case 1:
                    findByOrder();
                    break;
                case 2:
                    findAllByOrder();
                    break;
                case 3:
                    deleteFindByOrder();
                    break;
                case 9:
                    break label;
                default:
                    System.out.println("없는 항목을 선택했습니다.");
                    break;
            }
        }
    }
}
