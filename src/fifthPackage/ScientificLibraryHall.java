package fifthPackage;
import java.util.Optional;
public class ScientificLibraryHall implements IHall{
    protected LinkedList list;
    protected String hallName;
    public ScientificLibraryHall(String hallName, int booksQty) {
        this.hallName = hallName;
        this.list = new LinkedList();
        for(int i = 0; i < booksQty; ++i) {
            this.list.addLast(new Item());
        }
    }
    public ScientificLibraryHall(String hallName, LinkedList other) {
        this.hallName = hallName;
        this.list = other;
    }
    public String getHallName() {
        return this.hallName;
    }
    public int getBooksQty() {
        try {
            if(list.length() < 0)  {
                throw new InvalidBookCountException();
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка подсчёта книг в зале библиотеки:\n" + e+ '\n');
        }
        return list.length();
    }
    public String getBooksNames() {
        return "\nСписок названий всех книг в зале:\n" + list.toString();
    }
    public double getGeneralCost() {
        double generalCost = 0;
        for(int i = 0; i < list.length(); ++i) {
            generalCost += getBook(i).get().data.getCost();
        }
        try {
            if(generalCost < 0) {
                throw new InvalidBookPriceException();
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка подсчёта общей стоимости книг в зале библиотеки:\n" + e+ '\n');
        }
        return generalCost;
    }
    public Optional<Item> getBook(int index) {
        try {
            if(index < 0 || index >= getBooksQty()) {
                throw new BookIndexOutOfBoundsException(index);
            }
            return list.getItem(index);
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка получения книги по индексу " + index + " из зала библиотеки:\n" + e+ '\n');
        }
        return Optional.empty();
    }
    public IBook[] getBooksArray() {
        IBook[] booksArray = new IBook[getBooksQty()];
        for(int i = 0; i < getBooksQty(); ++i) {
            booksArray[i] = list.getItem(i).get().data;
        }
        return booksArray;
    }
    public void changeBook( int index, IBook book) {
        try {
            if(!list.removeItem(index)) {
                throw new BookIndexOutOfBoundsException(index);
            }
            list.removeItem(index);
            list.addItem(index, new Item(book));
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка замены книги по индексу " + index + " в зал библиотеки:\n" + e+ '\n');
        }
    }
    public void addBook( int index, IBook book) {
        try {
            if(!list.addItem(index, new Item(book))) {
                throw new BookIndexOutOfBoundsException(index);
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка добавления книги по индексу " + index + " в зал библиотеки:\n" + e + '\n');
        }
    }
    public void removeBook(int index) {
        try {
            if(!list.removeItem(index)) {
                throw new BookIndexOutOfBoundsException(index);
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка удаления книги по индексу " + index + " из зала библиотеки:\n" + e+ '\n');
        }
    }
    public IBook getBestBook() {
        if(list.isEmpty()) return null;
        IBook bestBook = getBook(0).get().data;
        for(int i = 0; i < list.length(); ++i) {
            if(bestBook.getCost() < getBook(i).get().data.getCost()) bestBook = getBook(i).get().data;
        }
        return bestBook;
    }
    @Override
    public String toString() {
        return "\nВсе книги в зале:" + this.list.toString();
    }
}
