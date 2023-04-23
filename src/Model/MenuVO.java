package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MenuVO {

    private String name;

    private String category;

    private int price;

    private LocalDateTime createDate;

    @Override
    public String toString() {
        return "메뉴명 : " + name + ", 카테고리 : " + category + ", 가격 :" + price + ", 생성시간 : "
                + createDate.format(DateTimeFormatter.ofPattern("uuuu-MM-dd"));
    }

    public MenuVO() {
    }

    public MenuVO(String name, String category, int price, LocalDateTime createDate) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

}
