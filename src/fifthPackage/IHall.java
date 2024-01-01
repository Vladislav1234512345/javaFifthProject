package fifthPackage;

import java.util.Optional;

public interface IHall{
    public int getBooksQty();
    public String getBooksNames();
    public double getGeneralCost();
    public Optional getBook(int index);
    public IBook[] getBooksArray();
    public void changeBook(int index, IBook book);
    public void addBook(int index, IBook book);
    public void removeBook(int index);
    public IBook getBestBook();
    public String getHallName();
}
