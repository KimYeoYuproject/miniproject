package Controller;

import java.util.List;

import DAO.UserDAO;
import Model.UserDTO;

/**
 * @author minjihi
 */
public class UserController {

	// UserDAO 필드 변수
	UserDAO userDAO = new UserDAO();

	// 유저 추가, UserDAO 이용
	public void addUser(UserDTO userVo) {
		userDAO.addUserVo(userVo);
	}

	// 유저 찾는 메소드, 핸드폰 번호 입력 받아 UserVO 객체 리턴, UserDAO 이용
	public UserDTO getUser(String phone) {
		return userDAO.getUserVo(phone);
	}

	// 회원정보 수정
	public void updateUser(UserDTO userVo, UserDTO updateUserVo) {
		userDAO.updateUser(userVo, updateUserVo);
	}

	// 회원탈퇴
	public void deleteUser(UserDTO userVo) {
		userDAO.deleteUser(userVo);
	}

	public List<UserDTO> findAllByUser() {
		return userDAO.findAllByUser();
	}

}
