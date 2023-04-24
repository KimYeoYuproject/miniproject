package Controller;

import java.time.LocalDateTime;
import java.util.Scanner;

import DTO.OrderDTO;
import Model.OrderVO;

/**
 * @author miji
 * 저장된 주문정보 데이터를 저장
 *
 */
public class OrderController {
	
	OrderDTO od =  new OrderDTO();
	Scanner sc = new Scanner(System.in);
	
	
	
	// 주문정보 입력받아 배열에 저장하기
	public void insertOrder() {
		
		OrderVO[] orderList = new OrderVO[10];

		
		System.out.println("주문 도와드리겠습니다~");
		
		// 주문번호, 주문자, 메뉴, 가격, 주문일자, 쿠폰사용여부를 입력받아 Order 객체 생성
	
		// 주문번호는 1번부터 1씩 늘어나도록 하기
		int no = 1;
		// 주문자는 전화번호를 입력받아 저장
		System.out.print("1. 전화번호를 입력해주세요 :  ");
		String orderer = sc.next();
		
		// 사용자로부터 매뉴를 입력받음 메뉴가 여러개일경우 주문번호와 주문자가 같은 배열을 하나더 생성해야됨
		System.out.print("2. 메뉴를 선택해주세요:  ");
		// 메뉴 DTO에서 메뉴를 받아와서 화면에 뿌려줌
		// 한글로 메뉴를 입력받음
		String menu = sc.next();
		
		
		
		
//		System.out.print("주문번호 입력 :  ");
		int price = 5000;
//		System.out.print("주문일자 입력 :  ");
		LocalDateTime date = null;
//		System.out.print("쿠폰사용여부 입력 :  ");
		boolean coupon = false;
		
		
		
		// 입력받은 정보를 토대로 배열에 정보 저장
		orderList[0] = new OrderVO(no, orderer, menu, price, date,coupon);
		orderList[1] = new OrderVO(2, orderer, menu, price, date,coupon);
		orderList[2] = new OrderVO(2, orderer, menu, price, date,coupon);
		orderList[3] = new OrderVO(2, orderer, menu, price, date,coupon);
		orderList[4] = new OrderVO(2, orderer, menu, price, date,coupon);
		orderList[5] = new OrderVO(2, "민지", menu, price, date,coupon);
		orderList[6] = new OrderVO(2, orderer, menu, price, date,coupon);
		orderList[7] = new OrderVO(2, orderer, menu, price, date,coupon);
		orderList[8] = new OrderVO(2, orderer, menu, price, date,coupon);
		orderList[9] = new OrderVO(2, orderer, menu, price, date,coupon);
		
		od.insertOrder(orderList);
		
	}
	
	
	//  주문 전체목록 불러오기
	public void selectAll() {
		
		OrderVO[] orderList = od.selectAll();
		for(int i = 0; i < orderList.length; i++) {
			System.out.println(i + "번주문 : " + orderList[i]);
		}
	}
	
	// 내 주문정보를 선택해서 정보 불러오기
	public void selectNo() {
		System.out.println(od.orderInfo(0));
	}
	
	
	// 주문번호를 선택해서 검색하기
	public void searcOrderByNo() {
		System.out.print("주문번호를 입력하세요");
		int no = sc.nextInt();
		
		OrderVO[] orderList = od.searchOrderByNo(no);
				
		for(OrderVO order : orderList) {
			if(order != null){
				System.out.println(order);
			}
		}	// 널포인트익셉션 잡기	
	}
	
	// 고객번호를 선택해서 검색하기
	public void searcOrderByOrderer() {
		System.out.println("고객번호를 입력하세요");
		String orderer = sc.next();
		
		OrderVO[] orderList = od.searchOrderByOrderer(orderer);
				
		for(OrderVO order : orderList) {
			if(order != null){
				System.out.println(order);
			}
		}	// 널포인트익셉션 잡기	
	}
	
	
	
	

}
