package fifthPackage;

import java.util.Optional;

public class ChildrenLibraryHall implements IHall{
    public String hallName;
    protected IBook[] booksArray;

    public ChildrenLibraryHall() {
        this.hallName = "Не определено";
        this.booksArray = null;
    }
    public ChildrenLibraryHall(String hallName, int quantity) {
        booksArray = new IBook[quantity];
        this.hallName = hallName;
        for(int i = 0; i < quantity; i++) {
            booksArray[i] = new ScientificBook();
        }
    }
    public ChildrenLibraryHall(String hallName, IBook[] bookArray) {
        this.hallName  = hallName;
        this.booksArray = bookArray;
    }
    public String getHallName() {
        return this.hallName;
    }
    public int getBooksQty() {
        try {
            if(booksArray.length < 0)  {
                throw new InvalidBookCountException();
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка подсчёта книг в зале библиотеки:\n" + e + '\n');
        }
        return booksArray.length;
    }
    public String getBooksNames() {
        String namesOfBooks = "\nСписок названий всех книг в зале:\n";
        for(int i = 0; i < getBooksQty(); i++) {
            namesOfBooks += booksArray[i].getName() + '\n';
        }
        return namesOfBooks;
    }

    public double getGeneralCost() {
        double generalCost = 0;
        for(int i = 0; i < getBooksQty(); i++) {
            generalCost += booksArray[i].getCost();
        }
        try {
            if(generalCost < 0) {
                throw new InvalidBookPriceException();
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка подсчёта общей стоимости книг в зале библиотеки:\n" + e + '\n');
        }
        return generalCost;
    }
    public Optional<IBook> getBook(int index) {
        try {
            if(index < 0 || index >= getBooksQty()) {
                throw new BookIndexOutOfBoundsException(index);
            }
            for(int i = 0; i < getBooksQty(); i++) {
                if(index == i) {
                    return Optional.of( booksArray[i]);
                }
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка получения книги по индексу " + index + " из зала библиотеки:\n" + e+ '\n');
        }
        return Optional.empty();
    }
    public IBook[] getBooksArray() {
        return this.booksArray;
    }
    public void changeBook(int index, IBook book) {
        try {
            if(index >= getBooksQty() || index < 0) {
                throw new BookIndexOutOfBoundsException(index);
            }
            removeBook(index);
            addBook(index, book);
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка замены книги по индексу " + index + " в зал библиотеки:\n" + e+ '\n');
        }
    }
    public void removeBook(int index) {
        try {
            if(index >= getBooksQty() || index < 0) {
                throw new BookIndexOutOfBoundsException(index);
            }
            IBook[] tempBooksArray = new IBook[booksArray.length - 1];
            int n = 0;
            for(int i = 0; i < getBooksQty(); i++) {
                if(index != i) {
                    tempBooksArray[n] = booksArray[i];
                    n++;
                }
            }
            booksArray = tempBooksArray;
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка удаления книги по индексу " + index + " из зала библиотеки:\n" + e+ '\n');
        }
    }

    public void addBook(int index, IBook book) {
        try {
            if(index > getBooksQty() || index < 0) {
                throw new BookIndexOutOfBoundsException(index);
            }
            IBook[] newBooksArray = new IBook[booksArray.length + 1];
            if(index == booksArray.length) {
                for(int i = 0; i < getBooksQty(); i++) {
                    newBooksArray[i] = booksArray[i];
                }
                newBooksArray[booksArray.length] = book;
                booksArray = newBooksArray;
            }
            else {
                for(int i = 0, j = 0; i < getBooksQty() + 1; i++) {
                    if(index == i) {
                        newBooksArray[i] = book;
                    }
                    else {
                        newBooksArray[i] = booksArray[j];
                        j++;
                    }
                }
                booksArray = newBooksArray;
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка добавления книги по индексу " + index + " в зал библиотеки:\n" + e + '\n');
        }
    }

    public IBook getBestBook() {
        double max = booksArray[0].getCost();
        IBook bestBook = booksArray[0];
        for(int i = 0; i < getBooksQty(); i++) {
            if(max < booksArray[i].getCost()) {
                max = booksArray[i].getCost();
                bestBook = booksArray[i];
            }
        }
        return bestBook;
    }
    @Override
    public String toString() {
        String result = "\nВсе книги в зале:";
        for(int i = 0; i < getBooksQty(); ++i) {
            result += '\n' + this.booksArray[i].toString();
        }
        return result;
    }
}
