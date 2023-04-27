package View;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controller.CouponController;
import Controller.MenuController;

import java.util.Date;

import DTO.OrderDTO;
import Model.CouponVO;
import Model.MenuVO;
import Model.OrderVO;
import View.CouponView;

public class OrderView {
	
	MenuController menulist = new MenuController();
	CouponController couponcon = new CouponController();
	CouponView couponlist = new CouponView(couponcon);
	
	private OrderDTO od =  new OrderDTO();
	private Scanner sc = new Scanner(System.in);
	private java.util.Date today = new java.util.Date();
	private int no = 1;		  // 주문번호는 1번부터 시작하여 새로운 주문건 마다 1씩 늘어나도록 하기
	private Date date= today; // 주문일자를 현재시간으로 저장해줌
	
	// 주문정보 입력받아 배열에 저장하기
	public void insertOrder() {
		SimpleDateFormat dt = new SimpleDateFormat("YYYY-MM-DD");
		dt.format(new Date());
		String orderer;
		String menu;
		int price;
		boolean coupon = false;
		
		// 쿠폰번호 뿌려주기
		couponlist. createCoupon(10, 20);
		
		
		// 주문번호, 주문자, 메뉴, 가격, 주문일자, 쿠폰사용여부를 입력받아 Order 객체 생성
		System.out.println("======================================");
		System.out.println("주문 도와드리겠습니다~");
		
		// 주문자는 전화번호를 입력받아 저장
		System.out.println();
		System.out.print("전화번호를 입력해주세요 :  ");
		orderer = sc.next();
		

		// 쿠폰이 있는 경우 쿠폰 사용할지 묻기
		label2:
		while(true) {
			System.out.print("쿠폰을 사용하시겠습니까?  Y/N :  ");
			char ys = sc.next().charAt(0);
			if(ys == 'Y') {
				System.out.print("쿠폰번호를 입력해주세요! :  ");
				String couponnunmber = sc.next();
				coupon= couponcon.availableCoupon(couponnunmber);
				if(coupon == true){
					System.out.println("사용 가능한 쿠폰입니다!");
					couponcon.useCoupon(couponnunmber);
					break;
				} else {
					continue label2;
				  }
				
			} else {
				coupon = false;
				break;
			}

		}
		
		
		label:
		while(true) {
			System.out.println("================= 메  뉴 =====================");
			// 메뉴 DTO에서 메뉴를 받아와서 화면에 뿌려줌
			List<MenuVO> menulist2 = menulist.findAllByMenu();
			for(int i =0; i< menulist2.size(); i++) {
				System.out.println( i+1 + "번 메뉴: " +menulist2.get(i).getName() +" " + menulist2.get(i).getPrice() + "원");
			}
			System.out.println("=============================================");
			
			// 사용자로부터 메뉴를 입력받음 메뉴가 여러개일경우 주문번호와 주문자가 같은 배열을 하나더 생성함
			System.out.println();
			System.out.println("메뉴 번호를 선택해주세요:  ");
			int selectmenu = sc.nextInt();
			
			// 메뉴에 없는 번호 입력시 다시 입력하도록 함
			if(selectmenu <= 0 || selectmenu > menulist2.size()) {
				System.out.println("없는 메뉴입니다! 다시 선택하세요");
				System.out.println();
				continue label;
			}
			
			// menu인수에 해당하는 메뉴이름값을 대입해줌
			menu = menulist2.get(selectmenu-1).getName();

			// 메뉴 가격을 불러와서 저장해줌
			price = menulist.findByMenu(menu).getPrice();
			
			// 입력받을 정보로 객체 셍성
			OrderVO order = new OrderVO(no, orderer, menu, price, date, coupon);
			
			// 생성된 객체를 배열에 저장해줌
			od.insertOrder(order);
			
			// 추가 주문 여부 확인
			System.out.println("추가로 주문하시겠습니까? Y/N :  ");
			char ys2 = sc.next().charAt(0);
			if(ys2 == 'Y') {
				continue label;
			} else {
				
					// Y입력 안하면 주문완료 문구 출력
					// 현재 주문자의 정보를 추력하기 위해 객체 리스트 생성
					List<OrderVO> orderList = od.searchOrderByNo(no);
					System.out.println("주문이 완료되었습니다! "+ orderList.get(0).getOrderer() +" 님이 주문하신 내역은 다음과 같습니다.");	
					System.out.println("====================== 주 문 내 역 =======================");
					for(OrderVO print : orderList) {
						if(order != null){
							System.out.println(print.Print());
						}
					}
					System.out.println("========================================================");
					
					
					// 결제금액 출력
					double priceSum =0;
					for(OrderVO print : orderList) {
						if(order != null){
							priceSum += print.getPrice();
						}
					}
					
					// 쿠폰 사용여부에 따라 할인 적용
					if(coupon == true) {
						priceSum = priceSum * 0.9;
						System.out.println("※ 결제하실 금액은 10% 할인되어 " +(int) priceSum+ "원 입니다. ※" );
					} else {
						System.out.println("※ 결제하실 금액은 " +(int) priceSum+ "원 입니다. ※" );
					}
					
					// 결제 여부 확인
					System.out.println("결제하시겠습니까? Y/N :  ");
					char ys3 = sc.next().charAt(0);
					if(ys3 == 'Y') {
						System.out.println("결제가 완료되었습니다. 감사합니다 ^_^ 음료는 픽업대에서 받아가세요~");
						no++;
						break label;
						
					} else {
						// Y입력 안하면 주문이 취소되고 현재 정보가 배열에서 삭제됨
						System.out.println("주문이 취소되었습니다.");
						System.out.println(no);
						od.deleteOrderByNo(no);
						selectAll();
						break;
							}
				}

			}
		
		} 
		


	//  주문 전체목록 불러오기
	public void selectAll() {
		
		List<OrderVO> orderList = od.selectAll();
		for(int i = 0; i < orderList.size(); i++) {
			System.out.println(i + "번주문 : " + orderList.get(i));
		}
	}
	
	
	// 주문번호를 선택해서 검색하기
	public void searchOrderByNo() {
		System.out.print("주문번호를 입력하세요");
		int no = sc.nextInt();
		
		List<OrderVO> orderList = od.searchOrderByNo(no);
				
		for(OrderVO order : orderList) {
			if(order != null){
				System.out.println(order);
			}
		}	
	}
	
	
	
	// 고객번호를 선택해서 검색하기
	public void searchOrderByOrderer() {
		System.out.println("고객번호를 입력하세요");
		String orderer = sc.next();
		
		List<OrderVO> orderList = od.searchOrderByOrderer(orderer);
				
		for(OrderVO order : orderList) {
			if(order != null){
				System.out.println(order);
			}
		}
	}

}
