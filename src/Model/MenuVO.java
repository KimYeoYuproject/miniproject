package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuVO {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-d HH:MM:ss");

    private String name;

    private String category;

    private int price;

    private Date createDate;

    @Override
    public String toString() {
        return "메뉴명 : " + name + ", 카테고리 : " + category + ", 가격 :" + price + ", 생성시간 : "
                + simpleDateFormat.format(createDate);
    }

    public MenuVO() {
    }

    public MenuVO(String name, String category, int price, Date createDate) {
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
