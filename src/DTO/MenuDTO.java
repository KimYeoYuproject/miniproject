package DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import Model.MenuVO;

/**
 * @author yoosc89
 */
public class MenuDTO {
    private List<MenuVO> menuVO = new ArrayList<>();

    {
        menuVO.add(new MenuVO("아메리카노", "커피", 2000, LocalDateTime.now()));
        menuVO.add(new MenuVO("레몬에이드", "에이드", 3500, LocalDateTime.now()));
        menuVO.add(new MenuVO("딸기주스", "에이드", 4000, LocalDateTime.now()));
        menuVO.add(new MenuVO("카페라떼", "커피", 3000, LocalDateTime.now()));
        menuVO.add(new MenuVO("카라멜마끼야또", "커피", 3500, LocalDateTime.now()));
        menuVO.add(new MenuVO("카페모카", "커피", 3500, LocalDateTime.now()));
        menuVO.add(new MenuVO("자몽에이드", "에이드", 3500, LocalDateTime.now()));
        menuVO.add(new MenuVO("커피프라페", "스무디", 3900, LocalDateTime.now()));
        menuVO.add(new MenuVO("딸기요거트스무디", "스무디", 3900, LocalDateTime.now()));
    }

    public Optional<MenuVO> findByMenu(String menuName) {
        return this.menuVO.stream().filter(menuVO -> menuVO.getName().equals(menuName)).findFirst();
    }

    public List<MenuVO> findAllByMenu() {
        this.menuVO.sort(Comparator.comparing(MenuVO::getName));
        return this.menuVO;
    }

    public boolean saveByMenu(MenuVO menu) {
        return this.menuVO.add(menu);
    }

    public boolean modifyByMenu(MenuVO menu) {
        for (int i = 0; i < menuVO.size(); i++) {
            if (this.menuVO.get(i).getName().equals(menu.getName())) {
                this.menuVO.get(i).setName(menu.getName());
                this.menuVO.get(i).setCategory(menu.getCategory());
                this.menuVO.get(i).setPrice(menu.getPrice());
                return true;
            }
        }
        return false;
    }

    public boolean deleteByMenu(String menuName) {
        for (int i = 0; i < menuVO.size(); i++) {
            if (this.menuVO.get(i).getName().equals(menuName)) {
                this.menuVO.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAllBymenu() {
        if (!this.menuVO.isEmpty()) {
            this.menuVO.clear();
            return true;
        } else {
            return false;
        }
    }

    public Set<String> findAllCategory() {
        Set<String> category = new HashSet<>();

        for (MenuVO m : this.menuVO) {
            category.add(m.getCategory());
        }
        return category;
    }
}
