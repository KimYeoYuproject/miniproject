package Model;

import java.time.LocalDate;

/**
 * @author minjihi
 */
public class UserDTO {

	// 핸드폰 번호 필드(필드변수)
	private String phone;
	// 이름(필드변수)
	private String name;
	// 계정 생성날짜(필드변수)
	private LocalDate createDate;

	// 기본생성자
	public UserDTO() {
	}

	// 매개변수(핸드폰번호,이름,생성날짜) 있는 생성자
	public UserDTO(String phone, String name, LocalDate createDate) {
		this.phone = phone;
		this.name = name;
		this.createDate = createDate;
	}

	// Getter, Setter
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "[연락처 : " + phone + ", 이름 : " + name + ", 생성일자 : " + createDate + "]";
	}

}
