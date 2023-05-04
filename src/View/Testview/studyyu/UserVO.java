package View.Testview.studyyu;

import java.time.LocalDateTime;
import java.util.List;

import Model.CouponDTO;
import Model.MenuDTO;

/**
 * @author yoosc89
 *         공부용 코드
 */
public class UserVO {

    private String phoneNumber;

    private String name;

    private List<CouponDTO> couponList;

    private List<MenuDTO> favorites;

    private LocalDateTime createDate;

    public UserVO() {
    }

    public UserVO(String phoneNumber, String name,
            List<CouponDTO> couponList, List<MenuDTO> favorites,
            LocalDateTime createDate) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.couponList = couponList;
        this.favorites = favorites;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "계쩡명 : " + phoneNumber + ", 이름 : " + name + "등록한 쿠폰 개수 : " + couponList + ", 등록한 즐겨찾기 메뉴 개수"
                + favorites + ", 계정 생성 일자 : " + createDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CouponDTO> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponDTO> couponList) {
        this.couponList = couponList;
    }

    public List<MenuDTO> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<MenuDTO> favorites) {
        this.favorites = favorites;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

}
