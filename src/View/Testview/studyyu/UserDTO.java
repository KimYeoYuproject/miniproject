package View.Testview.studyyu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDTO {

    private List<UserVO> userVO = new ArrayList<>();

    public Optional<UserVO> findByUser(String phoneNumber) {
        return this.userVO.stream()
                .filter(u -> u.getPhoneNumber()
                        .equals(phoneNumber))
                .findFirst();
    }

    public List<UserVO> findAllByUser() {
        return this.userVO;
    }

    public void saveByUser(View.Testview.studyyu.UserVO userVO2) {
        this.userVO.add(userVO2);
    }

    public void deleteByUser(String phoneNumber) {
        for (int i = 0; i < userVO.size(); i++) {
            if (userVO.get(i).getPhoneNumber().equals(phoneNumber)) {
                userVO.remove(i);
            }
        }
    }

    public void modifyByUser(UserVO uVo) {
        for (int i = 0; i < userVO.size(); i++) {
            if (userVO.get(i).getPhoneNumber().equals(uVo.getPhoneNumber())) {
                userVO.get(i).setPhoneNumber(uVo.getPhoneNumber());
                userVO.get(i).setName(uVo.getName());
                userVO.get(i).setFavorites(uVo.getFavorites());
                userVO.get(i).setCouponList(uVo.getCouponList());
            }
        }
    }
}
