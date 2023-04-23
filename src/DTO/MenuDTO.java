package DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Model.MenuVO;

public class MenuDTO {
    private List<MenuVO> menuVO = new ArrayList<>();

    {
        menuVO.add(new MenuVO("아메리카노", "커피", 2000, new Date()));
        menuVO.add(new MenuVO("레몬에이드", "에이드", 3500, new Date()));
        menuVO.add(new MenuVO("딸기주스", "에이드", 4000, new Date()));
        menuVO.add(new MenuVO("카페라떼", "커피", 3000, new Date()));
        menuVO.add(new MenuVO("카라멜마끼야또", "커피", 3500, new Date()));
        menuVO.add(new MenuVO("카페모카", "커피", 3500, new Date()));
        menuVO.add(new MenuVO("자몽에이드", "에이드", 3500, new Date()));
        menuVO.add(new MenuVO("커피프라페", "스무디", 3900, new Date()));
        menuVO.add(new MenuVO("딸기요거트스무디", "스무디", 3900, new Date()));
    }

    public MenuVO findByMenu(String menuName) {
        try {
            for (MenuVO m : menuVO) {
                if (m.getName().equals(menuName)) {
                    return m;
                }
            }
        } catch (NullPointerException e) {
            return null;
        }
        return null;
    }

    public List<MenuVO> findAllByMenu() {
        return menuVO;
    }

    public boolean saveByMenu(MenuVO menu) {
        return menuVO.add(menu);
    }

    public boolean modifyByMenu(MenuVO menu) {
        boolean result = false;
        for (int i = 0; i < menuVO.size(); i++) {
            if (menuVO.get(i).getName().equals(menu.getName())) {
                menuVO.get(i).setName(menu.getName());
                menuVO.get(i).setCategory(menu.getCategory());
                menuVO.get(i).setPrice(menu.getPrice());
                result = true;
            }
        }
        return result;
    }

    public boolean deleteByMenu(String menuName) {
        boolean result = false;
        for (int i = 0; i < menuVO.size(); i++) {
            if (menuVO.get(i).getName().equals(menuName)) {
                menuVO.remove(i);
                result = true;
            }
        }
        return result;
    }

    public boolean deleteAllBymenu() {
        boolean result = false;
        for (int i = 0; i < menuVO.size(); i++) {
            menuVO.remove(i);
        }
        if (menuVO == null) {
            result = true;
        }
        return result;
    }

    public Set<String> findAllCategory() {
        Set<String> category = new HashSet<>();

        for (MenuVO m : menuVO) {
            category.add(m.getCategory());
        }
        return category;
    }
}
