package DTO;

import Model.OrderVO;

/**
 * @author miji
 * 저장된 주문정보 데이터를 저장, 조회, 수정, 삭제가능한 객체
 * 주문 정보는 주문번호와 주문자별로 조회가능하다.
 * 주문 정보 전체조회가 가능하며 월,년 별로 오름차순/ 내림차순으로 조회 가능하다.
 */
public class OrderDTO {
	
	private OrderVO order = null;	// order레퍼런스 선언 및 null값으로 명시 초기화
	
	// 주문내역을 입력받아 배열에 저장 -- 임의로 크기 10 지정
	OrderVO[] orderList = new OrderVO[10];

	
	// 전달받은 order 주소값을 order레퍼런스에 대입
	public void insertOrder (OrderVO[] order) {
		this.orderList = order;
	}
	
	// 특정주문정보 order레퍼런스 주소값 리턴
	public OrderVO orderInfo(int i) {
		return orderList[i];
	}
	
	// 주문목록 전체값 리턴
	public OrderVO[] selectAll() {
		return orderList;
	}
	
	// 주문번호로 서치
	public OrderVO[] searchOrderByNo(int number) {
		// 검색결과를 담아줄 orderVO 객체배열 생성 -- 임의로 크기 10 지정
		OrderVO[] orderListNo = new OrderVO[10];
		int count =0;
		for(int i =0; i<orderList.length; i++) {
			if(orderList[i].getNo() == number) {
				orderListNo[count] = orderList[i];
				count ++;
			}
		}		
		return orderListNo;
	}
	
	// 고객번호로 서치
	public OrderVO[] searchOrderByOrderer(String orderer) {
		// 검색결과를 담아줄 orderVO 객체배열 생성 -- 임의로 크기 10 지정
		OrderVO[] orderListOrderer = new OrderVO[10];
		int count =0;
		for(int i =0; i<orderList.length; i++) {
			if(orderList[i].getOrderer().contains(orderer)) {
				orderListOrderer[count] = orderList[i];
				count ++;
			}
		}		
		return orderListOrderer;
	}
	
	
	
	

}
