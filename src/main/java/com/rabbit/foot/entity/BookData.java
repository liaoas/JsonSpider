package com.rabbit.foot.entity;

/**
 * <p>
 * 结果实体类，用于测试
 * </p>
 *
 * @author LiAo
 * @since 2023-09-18
 */
public class BookData {

    // 名称
    private String bookName;

    // 最新章节
    private String chapter;

    // 作者
    private String author;

    // 更新时间
    private String updateDate;

    // 链接
    private String bookLink;

    public BookData() {
    }

    public BookData(String bookName, String chapter, String author, String updateDate,String bookLink) {
        this.bookName = bookName;
        this.chapter = chapter;
        this.author = author;
        this.updateDate = updateDate;
        this.bookLink = bookLink;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getBookLink() {
        return bookLink;
    }

    public void setBookLink(String bookLink) {
        this.bookLink = bookLink;
    }
}
