package com.example.textbookinventory;

public class AdapterBook {
    public int BookCode ;
    public String BookName ;
    public String Category ;
    public int Price;
    public String PublishDate;
    public String AuthorName;
    public String Publisher;
    public int inStore;
    public int inStock;

    AdapterBook(String bookCode,String BookName,String Category,String Price,String PublishDate,String AuthorName,String Publisher,String inStore,String inStock){
        this.BookCode=Integer.parseInt(bookCode);
        this.BookName=BookName;
        this.Category=Category;
        this.Price=Integer.parseInt(Price);
        this.PublishDate=PublishDate;
        this.AuthorName=AuthorName;
        this.Publisher=Publisher;
        this.inStore=Integer.parseInt(inStore);
        this.inStock=Integer.parseInt(inStock);
    }

    AdapterBook(String bookCode,String BookName,String Category,String Price,String PublishDate,String AuthorName,String Publisher){
        this.BookCode=Integer.parseInt(bookCode);
        this.BookName=BookName;
        this.Category=Category;
        this.Price=Integer.parseInt(Price);
        this.PublishDate=PublishDate;
        this.AuthorName=AuthorName;
        this.Publisher=Publisher;
    }
}
