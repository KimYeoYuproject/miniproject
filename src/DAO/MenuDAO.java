package DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import Model.MenuDTO;

/**
 * @author yoosc89
 */
public class MenuDAO {
    private List<MenuDTO> menuDTOList = new ArrayList<>();

    {
        menuDTOList.add(new MenuDTO("아메리카노", "커피", 2000, LocalDateTime.now()));
        menuDTOList.add(new MenuDTO("레몬에이드", "에이드", 3500, LocalDateTime.now()));
        menuDTOList.add(new MenuDTO("딸기주스", "에이드", 4000, LocalDateTime.now()));
        menuDTOList.add(new MenuDTO("카페라떼", "커피", 3000, LocalDateTime.now()));
        menuDTOList.add(new MenuDTO("카라멜마끼야또", "커피", 3500, LocalDateTime.now()));
        menuDTOList.add(new MenuDTO("카페모카", "커피", 3500, LocalDateTime.now()));
        menuDTOList.add(new MenuDTO("자몽에이드", "에이드", 3500, LocalDateTime.now()));
        menuDTOList.add(new MenuDTO("커피프라페", "스무디", 3900, LocalDateTime.now()));
        menuDTOList.add(new MenuDTO("딸기요거트스무디", "스무디", 3900, LocalDateTime.now()));
    }

    public Optional<MenuDTO> findByMenu(String menuName) {
        return this.menuDTOList.stream()
                .filter(item -> item.getName().equals(menuName)).findFirst();
    }

    public List<MenuDTO> findAllByMenu() {
        this.menuDTOList.sort(Comparator.comparing(MenuDTO::getName));
        return this.menuDTOList;
    }

    public boolean saveByMenu(MenuDTO menu) {
        return this.menuDTOList.add(menu);
    }

    public boolean modifyByMenu(MenuDTO oldMenu, MenuDTO newMenu) {
        for (int i = 0; i < menuDTOList.size(); i++) {
            if (this.menuDTOList.get(i).getName().equals(oldMenu.getName())) {
                this.menuDTOList.get(i)
                        .setName(newMenu.getName())
                        .setCategory(newMenu.getCategory())
                        .setPrice(newMenu.getPrice());
                System.out.println(this.menuDTOList.get(i));
                return true;
            }
        }
        return false;
    }

    public boolean deleteByMenu(String menuName) {
        for (int i = 0; i < menuDTOList.size(); i++) {
            if (this.menuDTOList.get(i).getName().equals(menuName)) {
                this.menuDTOList.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAllBymenu() {
        if (!this.menuDTOList.isEmpty()) {
            this.menuDTOList.clear();
            return true;
        } else {
            return false;
        }
    }

    public Set<String> findAllCategory() {
        Set<String> category = new HashSet<>();

        for (MenuDTO m : this.menuDTOList) {
            category.add(m.getCategory());
        }
        return category;
    }
}
