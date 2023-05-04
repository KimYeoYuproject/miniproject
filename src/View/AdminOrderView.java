package View;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import DAO.MenuDAO;
import DAO.OrderDAO;
import Model.MenuDTO;
import Model.OrderDTO;

/**
 * @author yoosc89
 *         관리자 주문 관리 페이지
 */
public class AdminOrderView {

    private OrderDAO orderDAO;
    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();

    public AdminOrderView(OrderDAO OrderDAO) {
        this.orderDAO = OrderDAO;
    }

    public void adminOrderView() {

        label: while (true) {
            ClearConsole.clear();
            System.out.println("------ 주문 내역 ------");
            System.out.println("1. 주문 내역 조회 - 주문번호");
            System.out.println("2. 주문 내역 조회 - 휴대폰번호");
            System.out.println("3. 주문 전체 조회");
            System.out.println("4. 주문 추가");
            System.out.println("5. 주문 삭제");
            System.out.println("9. 종료");

            System.out.print("메뉴를 선택하세요 : ");
            int number = sc.nextInt();
            sc.nextLine();
            ClearConsole.clear();
            switch (number) {
                case 1:
                    findByOrderNumber();
                    break;
                case 2:
                    findByOrderUser();
                    break;
                case 3:
                    findAllByOrder();
                    break;
                case 4:
                    saveByOrder();
                    break;
                case 5:
                    deleteOrderByNo();
                    break;
                case 9:
                    break label;
                default:
                    System.out.println("잘못 선택 다시 선택 해주세요 ");
            }
        }
    }

    public void findByOrderNumber() {
        System.out.println("주문번호를 입력하세요 : ");
        int number = sc.nextInt();
        orderDAO.searchOrderByNo(number).forEach(System.out::println);
    }

    public void findByOrderUser() {
        System.out.println("핸드폰 번호를 입력하세요 : ");
        String phoneNumber = sc.nextLine();
        orderDAO.searchOrderByOrderer(phoneNumber).forEach(System.out::println);
    }

    public void findAllByOrder() {
        List<OrderDTO> orderVO = orderDAO.selectAll().stream().toList();
        orderVO.sort(Comparator.comparing(OrderDTO::getNo));
        orderVO.forEach(System.out::println);
    }

    public OrderDTO randemOrderGenerator() {
        int index = random.nextInt(new MenuDAO().findAllByMenu().size());
        boolean usedCoupon = random.nextBoolean();
        MenuDTO menuVO = new MenuDAO().findAllByMenu().get(index);
        StringBuilder stringBuilder = new StringBuilder("010");
        for (int j = 0; j < 8; j++) {
            int c = random.nextInt(10) + 48;
            stringBuilder.append((char) c);

        }
        return new OrderDTO(new Random().nextInt(30), stringBuilder.toString(),
                menuVO.getName(), menuVO.getPrice(),
                new Date(), usedCoupon);
    }

    public void saveByOrder() {

        for (int i = 0; i < 20; i++) {
            orderDAO.insertOrder(randemOrderGenerator());
        }

    }

    public void deleteOrderByNo() {
        findAllByOrder();
        System.out.println("삭제할 주문번호를 입력하세요 : ");
        int number = sc.nextInt();
        orderDAO.deleteOrderByNo(number);
    }
}
