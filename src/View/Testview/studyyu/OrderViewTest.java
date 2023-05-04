package View.Testview.studyyu;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Controller.CouponController;
import Controller.MenuController;
import DAO.OrderDAO;
import Model.MenuDTO;
import Model.OrderDTO;
import View.CouponView;

/**
 * @author yoosc89
 *         공부용 코드
 */
public class OrderViewTest {

	private MenuController menulist = new MenuController();
	private CouponController couponcon = new CouponController();
	private CouponView couponlist = new CouponView(couponcon);

	private OrderDAO od = new OrderDAO();
	private Scanner sc = new Scanner(System.in);
	private int no = 1; // 주문번호는 1번부터 시작하여 새로운 주문건 마다 1씩 늘어나도록 하기

	// 주문정보 입력받아 배열에 저장하기
	public void orderView() {
		// 쿠폰번호 뿌려주기
		couponlist.createCoupon(10, 20);

		String order = userValidate();
		boolean usedCoupon = couponConfirm();
		MenuDTO selectMenu;
		double priceSum = 0.0;

		while (true) {
			selectMenu = selectMenu();
			createOrder(no, selectMenu, order, usedCoupon);
			if (!addOrder()) {
				break;
			}
		}

		priceSum = printOrder(no);
		discount(priceSum, usedCoupon);
		pay(no);

	}

	public boolean couponConfirm() {
		String couponnunmber;
		while (true) {
			System.out.print("쿠폰을 사용하시겠습니까?  Y/N :  ");
			char ys = sc.next().charAt(0);
			if (ys == 'n' || ys == 'N') {
				return false;
			}
			if (ys == 'y' || ys == 'Y') {
				System.out.print("쿠폰번호를 입력해주세요! :  ");
				couponnunmber = sc.next();

				if (couponcon.availableCoupon(couponnunmber)) {
					System.out.println("사용 가능한 쿠폰입니다!");
					couponcon.useCoupon(couponnunmber);
					return true;
				}
			} else {
				System.out.println("Y/N중에 선택하세요~");
			}
		}
	}

	public String userValidate() {
		System.out.println("======================================");
		System.out.println("주문 도와드리겠습니다~");

		// 주문자는 전화번호를 입력받아 저장
		System.out.println();
		System.out.print("전화번호를 입력해주세요 :  ");
		String orderer = sc.next();

		// 있으면 유저객체 반환 / 없으면 새로운 유저 객체생성
		return orderer;
	}

	public MenuDTO selectMenu() {
		List<MenuDTO> menulist2 = menulist.findAllByMenu();
		int selectmenu;
		while (true) {
			System.out.println("================= 메  뉴 =====================");
			// 메뉴 DTO에서 메뉴를 받아와서 화면에 뿌려줌

			for (int i = 0; i < menulist2.size(); i++) {
				System.out.println(
						i + 1 + "번 메뉴: " + menulist2.get(i).getName() + " " + menulist2.get(i).getPrice() + "원");
			}
			System.out.println("=============================================");
			// 사용자로부터 메뉴를 입력받음 메뉴가 여러개일경우 주문번호와 주문자가 같은 배열을 하나더 생성함
			System.out.println();
			System.out.println("메뉴 번호를 선택해주세요:  ");
			selectmenu = sc.nextInt() - 1; // 리스트 출력시 1번 부터 시작

			if (menulist2.get(selectmenu) != null) {
				return menulist2.get(selectmenu);
			}
			System.out.println("없는 메뉴입니다. 다시 선택하세요");
		}
	}

	public void createOrder(int orderNum, MenuDTO menu,
			String orderer, boolean usedCoupon) {
		OrderDTO orderVO = new OrderDTO(orderNum, orderer,
				menu.getName(), menu.getPrice(),
				new Date(), usedCoupon);
		od.insertOrder(orderVO);
	}

	public double printOrder(int no) {
		List<OrderDTO> orderList = od.searchOrderByNo(no);
		double priceSum = 0.0;
		System.out.println("주문이 완료되었습니다! "
				+ orderList.get(0).getOrderer()
				+ " 님이 주문하신 내역은 다음과 같습니다.");

		System.out.println("====================== 주 문 내 역 =======================");
		for (OrderDTO order : orderList) {
			System.out.println(order.Print());
			priceSum += order.getPrice();
		}
		System.out.println("========================================================");

		return priceSum;
	}

	public void discount(double priceSum, boolean usedCoupon) {
		if (usedCoupon) {
			priceSum = priceSum * 0.9;
			System.out.println("※ 결제하실 금액은 10% 할인되어 " + (int) priceSum + "원 입니다. ※");
		} else {
			System.out.println("※ 결제하실 금액은 " + (int) priceSum + "원 입니다. ※");
		}
	}

	public int pay(int orderNum) {
		System.out.println("결제하시겠습니까? Y/N :  ");
		char ys = sc.next().charAt(0);

		if (ys == 'Y' || ys == 'y') {
			System.out.println("결제가 완료되었습니다. 감사합니다 ^_^ 음료는 픽업대에서 받아가세요~");
			return orderNum++;
		} else {
			System.out.println("주문이 취소되었습니다.");
			System.out.println(orderNum);
			od.deleteOrderByNo(orderNum);
			od.selectAll();
		}

		return orderNum;
	}

	public boolean addOrder() {
		while (true) {
			System.out.println("추가로 주문하시겠습니까? Y/N :  ");
			char ys2 = sc.next().charAt(0);
			if (ys2 == 'N' || ys2 == 'n') {
				return false;
			}
			if (ys2 == 'Y' || ys2 == 'y') {
				return true;
			}
			System.out.println("Y / N 만 입력하시기 바랍니다.");
		}
	}

}
/** ㄴㅇㄴㅇㄹㅇㄴㄹㄴㅇㄹ */