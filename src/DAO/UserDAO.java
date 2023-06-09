package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Model.UserDTO;

/**
 * @author minjihi
 */
public class UserDAO {

	// UserVO 객체 배열 필드 변수
	List<UserDTO> userList = new ArrayList<>();

	// UserVO 객체 배열에 추가하는 메소드
	public void addUserVo(UserDTO userVo) {
		userList.add(userVo);

		System.out.println("가입이 완료되었습니다");
	}

	// 휴대폰 번호를 매개변수로 받아 해당 UserVO 객체 리턴
	public UserDTO getUserVo(String phone) {

		List<UserDTO> userVoList = userList.stream().filter(item -> phone.equals(item.getPhone()))
				.collect(Collectors.toList());

		if (userVoList.size() <= 0) {
			return null;
		}

		return userVoList.get(0);
	}

	// 회원정보 수정
	public void updateUser(UserDTO userVo, UserDTO updateUser) {

		System.out.println("기존");
		System.out.println(userVo.getName());
		System.out.println(userVo.getPhone());

		System.out.println("업데이트");
		System.out.println(updateUser.getName());
		System.out.println(updateUser.getPhone());

		userList = userList.stream().map(item -> {

			if (item.getName().equals(userVo.getName()) && item.getPhone().equals(userVo.getPhone())) {

				System.out.println("update!!!");
				item.setName(updateUser.getName());
				item.setPhone(updateUser.getPhone());

				return item;
			}

			return item;

		}).collect(Collectors.toList());

	}

	// 회원탈퇴
	public void deleteUser(UserDTO userVo) {

		userList = userList.stream().filter(item -> {
			return !(item.getName().equals(userVo.getName()) && item.getPhone().equals(userVo.getPhone()));

		}).collect(Collectors.toList());

		System.out.println(userList);

	}

	public List<UserDTO> findAllByUser() {
		return this.userList;
	}

}
