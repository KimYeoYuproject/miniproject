package Controller;

import java.util.List;

import DAO.UserDAO;
import Model.UserVO;

/**
 * @author minjihi
 */
public class UserController {

	// UserDAO 필드 변수
	UserDAO userDAO = new UserDAO();

	// 유저 추가, UserDAO 이용
	public void addUser(UserVO userVo) {
		userDAO.addUserVo(userVo);
	}

	// 유저 찾는 메소드, 핸드폰 번호 입력 받아 UserVO 객체 리턴, UserDAO 이용
	public UserVO getUser(String phone) {
		return userDAO.getUserVo(phone);
	}

	// 회원정보 수정
	public void updateUser(UserVO userVo, UserVO updateUserVo) {
		userDAO.updateUser(userVo, updateUserVo);
	}

	// 회원탈퇴
	public void deleteUser(UserVO userVo) {
		userDAO.deleteUser(userVo);
	}

	public List<UserVO> findAllByUser() {
		return userDAO.findAllByUser();
	}

}
