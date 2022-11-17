package assignment02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric<T> extends Book{
    public LibraryBookGeneric(long isbn, String author, String title) {
        super(isbn, author, title);//invoke Book's constructor
    }
    private T holder;
    private GregorianCalendar dueDate;

    public void setHolder(T holder_){
        holder=holder_;
    }

    public void setDueDate(GregorianCalendar dueDate_){
        dueDate=dueDate_;
    }

    public T getHolder(){
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
    public void checkout(T holder_,GregorianCalendar dueDate_){
        holder=holder_;
        dueDate=dueDate_;
    }

}
