package View.Testview.studyyu;

import java.time.LocalDateTime;
import java.util.List;

import Model.CouponDTO;
import Model.MenuDTO;

/**
 * @author yoosc89
 *         공부용 코드
 */
public class UserController {
    UserDTO userDTO = new UserDTO();

    public void simpleSaveByUser(String phoneNumber) {
        userDTO.saveByUser(new UserVO(phoneNumber, null, null, null, LocalDateTime.now()));
    }

    public void fullSaveByUser(UserVO uVo) {
        userDTO.saveByUser(uVo);
    }

    public UserVO findByUser(String phoneNumber) {
        return userDTO.findByUser(phoneNumber).orElse(null);
    }

    public List<UserVO> findAllByUser() {
        return userDTO.findAllByUser();
    }

    public void deleteByUser(String phoneNumber) {
        userDTO.deleteByUser(phoneNumber);
    }

    public void addCoupon(CouponDTO coupon, String phoneNumber) {
        UserVO uVo = findByUser(phoneNumber);
        uVo.getCouponList().add(coupon);
        userDTO.modifyByUser(uVo);
    }

    public void usedCoupon(CouponDTO coupon, String phoneNumber) {
        UserVO uVo = findByUser(phoneNumber);
        List<CouponDTO> tempCouponList = uVo.getCouponList();
        for (int i = 0; i < tempCouponList.size(); i++) {
            if (tempCouponList.get(i).getCoupon().equals(phoneNumber)) {
                tempCouponList.get(i).setAvailable(false);
            }
        }
        uVo.setCouponList(tempCouponList);
        userDTO.modifyByUser(uVo);
    }

    public void addFavorites(MenuDTO menuVO, String phoneNumber) {
        UserVO uVo = findByUser(phoneNumber);
        uVo.getFavorites().add(menuVO);
        userDTO.modifyByUser(uVo);
    }

    public void deleteFavorites(MenuDTO menuVO, String phoneNumber) {
        UserVO uVo = findByUser(phoneNumber);
        List<MenuDTO> tempFavorites = uVo.getFavorites();
        for (int i = 0; i < tempFavorites.size(); i++) {
            if (tempFavorites.get(i).getName().equals(menuVO.getName())) {
                tempFavorites.remove(i);
            }
        }
        uVo.setFavorites(tempFavorites);
        userDTO.modifyByUser(uVo);
    }

}
