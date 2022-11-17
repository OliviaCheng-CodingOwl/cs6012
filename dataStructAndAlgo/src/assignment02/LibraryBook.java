package assignment02;

import java.util.GregorianCalendar;

public class LibraryBook extends Book{
    public LibraryBook(long isbn, String author, String title) {
        super(isbn, author, title);//invoke Book's constructor
    }
    private String holder;
    private GregorianCalendar dueDate;

    public void setHolder(String holder_){
        holder=holder_;
    }

    public void setDueDate(GregorianCalendar dueDate_){
        dueDate=dueDate_;
    }

    public String getHolder(){
        return holder;
    }

    public GregorianCalendar getDueDate(){
        return dueDate;
    }

    //checkin a book
    public void checkin(){
        holder=null;
        dueDate=null;
    }

    //check out a book
    public void checkout(String holder_,GregorianCalendar dueDate_){
        holder=holder_;
        dueDate=dueDate_;
        dueDate=dueDate_;
    }

}
