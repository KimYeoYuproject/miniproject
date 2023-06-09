package Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import DAO.OrderDAO;
import Model.MenuDTO;
import Model.OrderDTO;
import Model.UserDTO;
import View.CouponView;
import View.UserView;

/**
 * @author miji
 *         저장된 주문정보 데이터를 입력받아 저장하고 금액을 출력해준다.
 *         저장된 주문내역을 출력해준다.
 */
public class OrderController {

	MenuController menulist = new MenuController();
	CouponController couponcon = new CouponController();
	CouponView couponlist = new CouponView(couponcon);

	private OrderDAO od;
	private UserController usercon;
	private Scanner sc = new Scanner(System.in);
	private java.util.Date today = new java.util.Date();
	private int no = 1; // 주문번호는 1번부터 시작하여 새로운 주문건 마다 1씩 늘어나도록 하기
	private Date date = today; // 주문일자를 현재시간으로 저장해줌

	public OrderController(OrderDAO od, UserController usercon) {

		this.od = od;
		this.usercon = usercon;
	}

	// 주문정보 입력받아 배열에 저장하기
	public void insertOrder() {
		SimpleDateFormat dt = new SimpleDateFormat("YYYY-MM-DD");
		dt.format(new Date());
		String orderer;
		String name;
		String menu;
		int price;
		boolean coupon = false;

		// 쿠폰번호 뿌려주기
		couponlist.createCoupon(10, 20);

		// 주문번호, 주문자, 메뉴, 가격, 주문일자, 쿠폰사용여부를 입력받아 Order 객체 생성
		System.out.println("======================================");
		System.out.println("주문 도와드리겠습니다~");

		// 주문자는 전화번호를 입력받아 저장
		System.out.println();
		// System.out.print("전화번호를 입력해주세요 : ");
		// orderer = sc.next();
		orderer = new UserView(usercon).phoneNumberValidate();
		if (orderer == null) {
			return;
		}

		// 회원인경우 환영문구 추가
		if (usercon.getUser(orderer) != null) {
			name = usercon.getUser(orderer).getName();
			System.out.println(name + " 고객님 환영합니다.");
		}

		// 쿠폰이 있는 경우 쿠폰 사용할지 묻기
		label2: while (true) {
			coupon = false;
			System.out.print("쿠폰을 사용하시겠습니까?  Y/N :  ");
			char ys = sc.next().charAt(0);
			if (ys == 'Y') {
				System.out.print("쿠폰번호를 입력해주세요! :  ");
				String couponnunmber = sc.next();
				coupon = couponcon.availableCoupon(couponnunmber);
				if (coupon) {
					System.out.println("사용 가능한 쿠폰입니다!");
					couponcon.useCoupon(couponnunmber);
					break;
				}
			} else {
				break;
			}
		}

		label: while (true) {
			System.out.println("================= 메  뉴 =====================");
			// 메뉴 DTO에서 메뉴를 받아와서 화면에 뿌려줌
			List<MenuDTO> menulist2 = menulist.findAllByMenu();
			for (int i = 0; i < menulist2.size(); i++) {
				System.out.println(
						i + 1 + "번 메뉴: " + menulist2.get(i).getName() + " " + menulist2.get(i).getPrice() + "원");
			}
			System.out.println("=============================================");

			// 사용자로부터 메뉴를 입력받음 메뉴가 여러개일경우 주문번호와 주문자가 같은 배열을 하나더 생성함
			System.out.println();
			System.out.println("메뉴 번호를 선택해주세요:  ");
			int selectmenu = sc.nextInt();

			// 메뉴에 없는 번호 입력시 다시 입력하도록 함
			if (menulist2.get(selectmenu - 1) == null) {
				System.out.println("없는 메뉴입니다! 다시 선택하세요");
				System.out.println();
				continue label;
			}

			// menu인수에 해당하는 메뉴이름값을 대입해줌
			menu = menulist2.get(selectmenu - 1).getName();

			// 메뉴 가격을 불러와서 저장해줌
			price = menulist.findByMenu(menu).getPrice();

			// 입력받을 정보로 객체 셍성
			OrderDTO order = new OrderDTO(no, orderer, menu, price, date, coupon);

			// 생성된 객체를 배열에 저장해줌
			od.insertOrder(order);

			// 추가 주문 여부 확인
			System.out.println("추가로 주문하시겠습니까? Y/N :  ");
			char ys2 = sc.next().charAt(0);
			if (ys2 == 'Y') {
				continue label;
			} else {

				// Y입력 안하면 주문완료 문구 출력
				// 현재 주문자의 정보를 추력하기 위해 객체 리스트 생성
				List<OrderDTO> orderList = od.searchOrderByNo(no);
				// 회원인경우 환영문구 추가
				if (usercon.getUser(orderer) != null) {
					name = usercon.getUser(orderer).getName();
					System.out.println("주문이 완료되었습니다! " + name + " 님이 주문하신 내역은 다음과 같습니다.");
				} else {
					System.out.println("주문이 완료되었습니다! " + orderList.get(0).getOrderer() + " 님이 주문하신 내역은 다음과 같습니다.");
				}
				System.out.println("============ 주 문 내 역 ============= 주문번호 : " + no + " ========");
				for (OrderDTO print : orderList) {
					if (order != null) {
						System.out.println(print.Print());
					}
				}
				System.out.println("========================================================");

				// 결제금액 출력
				double priceSum = 0;
				for (OrderDTO print : orderList) {
					if (order != null) {
						priceSum += print.getPrice();
					}
				}

				// 쿠폰 사용여부에 따라 할인 적용
				if (coupon == true) {
					priceSum = priceSum * 0.9;
					System.out.println("※ 결제하실 금액은 10% 할인되어 " + (int) priceSum + "원 입니다. ※");
				} else {
					System.out.println("※ 결제하실 금액은 " + (int) priceSum + "원 입니다. ※");
				}

				// 결제 여부 확인
				System.out.println("결제하시겠습니까? Y/N :  ");
				char ys3 = sc.next().charAt(0);
				if (ys3 == 'Y') {

					System.out.println("결제가 완료되었습니다. 감사합니다 ^_^ 음료는 픽업대에서 받아가세요~");
					no++;
					if (usercon.getUser(orderer) == null) {
						// 주문 완료 후 회원이 아니면 회원추가
						usercon.addUser(new UserDTO(orderer, "익명", LocalDate.now()));
						usercon.findAllByUser();
					}
					break label;

				} else {
					// Y입력 안하면 주문이 취소되고 현재 정보가 배열에서 삭제됨
					System.out.println("주문이 취소되었습니다.");
					od.deleteOrderByNo(no);
					break;
				}
			}

		}

	}

	// 주문 전체목록 불러오기
	public void selectAll() {

		List<OrderDTO> orderList = od.selectAll();
		for (int i = 0; i < orderList.size(); i++) {
			System.out.println(i + "번주문 : " + orderList.get(i));
		}
	}

	// 주문번호를 선택해서 검색하기
	public void searchOrderByNo() {
		System.out.print("주문번호를 입력하세요");
		int no = sc.nextInt();

		List<OrderDTO> orderList = od.searchOrderByNo(no);

		for (OrderDTO order : orderList) {
			if (order != null) {
				System.out.println(order);
			}
		}
	}

	// 고객번호를 선택해서 검색하기
	public void searchOrderByOrderer() {
		System.out.println("고객번호를 입력하세요");
		String orderer = sc.next();

		List<OrderDTO> orderList = od.searchOrderByOrderer(orderer);

		for (OrderDTO order : orderList) {
			if (order != null) {
				System.out.println(order);
			}
		}
	}

}
