package View.Testview;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import Controller.CouponController;
import Controller.UserController;
import DTO.OrderDTO;
import Model.OrderVO;
import Model.UserVO;
import View.ClearConsole;

/**
 * @author minjihi
 */
public class TestKim {

	public void view() {
		UserController userController = new UserController();

		Scanner sc = new Scanner(System.in);

		ClearConsole.clear();

		while (true) {
			System.out.println();
			System.out.println("=====마이 페이지=====");
			System.out.println("1. 회원가입");
			System.out.println("2. 회원정보조회");
			System.out.println("3. 회원정보수정");
			System.out.println("4. 회원탈퇴");
			System.out.println("5. 관리자메뉴");
			int input = sc.nextInt();

			if (input == 1) {

				System.out.println("=====회원가입=====");
				System.out.print("이름 : ");
				String name = sc.next();
				System.out.print("연락처 : ");
				String phone = sc.next();

				UserVO userVo = new UserVO(phone, name, LocalDate.now());

				userController.addUser(userVo);

			} else if (input == 2) {

				System.out.println("=====회원정보 조회=====");
				System.out.print("연락처 : ");
				String phone = sc.next();

				UserVO userVo = userController.getUser(phone);

				if (userVo == null) {
					System.out.println("해당하는 고객이 없습니다");
					continue;
				}

				System.out.println("반갑습니다! " + userVo.getName() + "님!");

				System.out.println("최근 주문 목록");
				OrderDTO orderDto = new OrderDTO();
				List<OrderVO> orderList = orderDto.searchOrderByOrderer(phone);

				for (int i = 0; i < orderList.size(); i++) {

					if (i < 5) {
						System.out.println(orderList.get(i).getMenu());
					}

				}

			} else if (input == 3) {

				System.out.println("=====회원정보 수정=====");
				System.out.print("연락처 : ");
				String phone = sc.next();
				System.out.println();

				UserVO userVo = userController.getUser(phone);
				UserVO newUserVo = new UserVO(userVo.getPhone(), userVo.getName(), userVo.getCreateDate());

				System.out.print("이름을 수정하시겠습니까? Y/N");
				String nameYN = sc.next();
				System.out.println();

				if (nameYN.equals("Y") || nameYN.equals("y")) {
					System.out.print("변경할 이름을 입력해 주세요");
					newUserVo.setName(sc.next());
					System.out.println();
				}

				System.out.print("연락처를 수정하시겠습니까? Y/N");
				String phoneYN = sc.next();
				System.out.println();

				if (phoneYN.equals("Y") || phoneYN.equals("y")) {
					System.out.print("변경할 연락처를 입력해 주세요");
					newUserVo.setPhone(sc.next());
					System.out.println();
				}

				System.out.print("쿠폰 번호를 입력해 주세요");
				int coupon = sc.nextInt();

				userController.updateUser(userVo, newUserVo);

				CouponController couponController = new CouponController();
				// 수정 필요 TODO
				couponController.couponGenerate(coupon, coupon);

				System.out.println("회원정보수정이 완료 되었습니다");

			} else if (input == 4) {

				UserVO userVo = new UserVO();

				System.out.println("=====회원탈퇴=====");
				System.out.print("이름 : ");
				userVo.setName(sc.next());
				System.out.print("연락처 : ");
				userVo.setPhone(sc.next());

				userController.deleteUser(userVo);

				System.out.println("회원탈퇴가 완료 되었습니다");

			} else if (input == 5) {
				break;
			}
		}
	}
}
