package Controller;

import java.util.List;

import DTO.UserDTO;
import Model.UserVO;

public class UserController {

	// UserDTO 필드 변수
	UserDTO userDto = new UserDTO();

	// 유저 추가, UserDTO 이용
	public void addUser(UserVO userVo) {
		userDto.addUserVo(userVo);
	}

	// 유저 찾는 메소드, 핸드폰 번호 입력 받아 UserVO 객체 리턴, UserDTO 이용
	public UserVO getUser(String phone) {
		return userDto.getUserVo(phone);
	}

	// 회원정보 수정
	public void updateUser(UserVO userVo, UserVO updateUserVo) {
		userDto.updateUser(userVo, updateUserVo);
	}

	// 회원탈퇴
	public void deleteUser(UserVO userVo) {
		userDto.deleteUser(userVo);
	}

	public List<UserVO> findAllByUser() {
		return userDto.findAllByUser();
	}

}
