package View.Testview.studyyu;

import java.time.LocalDateTime;
import java.util.List;

import Model.CouponVO;
import Model.MenuVO;

public class UserVO {

    private String phoneNumber;

    private String name;

    private List<CouponVO> couponList;

    private List<MenuVO> favorites;

    private LocalDateTime createDate;

    public UserVO() {
    }

    public UserVO(String phoneNumber, String name,
            List<CouponVO> couponList, List<MenuVO> favorites,
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

    public List<CouponVO> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponVO> couponList) {
        this.couponList = couponList;
    }

    public List<MenuVO> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<MenuVO> favorites) {
        this.favorites = favorites;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

}
