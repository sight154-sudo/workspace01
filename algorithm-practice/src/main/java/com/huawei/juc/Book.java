package com.huawei.juc;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author king
 * @date 2022/10/14-0:18
 * @Desc
 */
@Setter
@Getter
public class Book {
    private Integer id;
    private String bookName;
    private String author;
    private double price;

    public Book() {

    }


    public static void main(String[] args) {
        Book book = new Book();
//        Book build = Book.builder().id().build();
    }
}
