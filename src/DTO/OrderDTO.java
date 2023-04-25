package DTO;

import java.util.ArrayList;
import java.util.List;

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
	List<OrderVO> orderList =  new ArrayList<>();

	
	// 전달받은 order 주소값을 order레퍼런스에 대입
	public void insertOrder (OrderVO orderList2) {
		this.orderList.add(orderList2);
	}
	
	// 주문목록 전체값 리턴
	public List<OrderVO> selectAll() {
		return orderList;
	}
	
	// 주문번호로 서치
	public List<OrderVO> searchOrderByNo(int number) {
		// 검색결과를 담아줄 orderVO 객체배열 생성 -- 임의로 크기 10 지정
		List<OrderVO> orderListNo = new ArrayList<>();
		int count =0;
		for(int i =0; i<orderList.size(); i++) {
			if(orderList.get(i).getNo() == number) {
				orderListNo.add(count, orderList.get(i));
				count ++;
			}
		}		
		return orderListNo;
	}
	
	// 고객번호로 서치
	public List<OrderVO> searchOrderByOrderer(String orderer) {
		// 검색결과를 담아줄 orderVO 객체배열 생성 -- 임의로 크기 10 지정
		List<OrderVO> orderListOrderer = new ArrayList<>();
		int count =0;
		for(int i =0; i<orderList.size(); i++) {
			if(orderList.get(i).getOrderer().contains(orderer)) {
				orderListOrderer.add(count, orderList.get(i));
				count ++;
			}
		}		
		return orderListOrderer;
	}
	
	
	
	

}
