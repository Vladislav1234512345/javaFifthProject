package fifthPackage;

import java.util.Optional;

public class ChildrenLibrary implements ILibrary {
    private IHall[] hallsArray;
    public ChildrenLibrary(int hallQty, int[] bookHallsQty) {
        if(bookHallsQty.length != hallQty) hallQty = bookHallsQty.length;
        this.hallsArray = new IHall[hallQty];
        for (int i = 0; i < hallQty; i++) {
            hallsArray[i] = new ChildrenLibraryHall("Не определено", bookHallsQty[i]);
        }
    }
    public ChildrenLibrary(IHall[] hallsArray) {
        this.hallsArray = hallsArray;
    }

    public int getHallsQty() {
        try {
            if(this.hallsArray.length < 0) {
                throw new InvalidBookCountException();
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка подсчёта залов в библиотеке:\n" + e + '\n');
        }
        return hallsArray.length;
    }

    public int getBooksQty() {
        int booksQty = 0;
        for(int i = 0; i < hallsArray.length; i++) {
            booksQty += hallsArray[i].getBooksQty();
        }
        try {
            if(booksQty < 0) {
                throw new InvalidBookCountException();
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка подсчёта книг в библиотеке:\n" + e + '\n');
        }
        return booksQty;
    }
    public double getGeneralCost() {
        double generalCost = 0;
        for(int i = 0; i < getHallsQty(); ++i) {
            generalCost += hallsArray[i].getGeneralCost();
        }
        try {
            if(generalCost < 0) {
                throw new InvalidBookPriceException();
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка подсчёта общей стоимости книг в библиотеке:\n" + e + '\n');
        }
        return generalCost;
    }
    public IHall[] getHallsArray() { return hallsArray;}

    public Optional<IHall> getHall(int index) {
        try {
            if(index < 0 || index >= getHallsQty()) {
                throw new HallIndexOutOfBoundsException(index);
            }
            return Optional.of(hallsArray[index]);
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка получения зала по индексу " + index + " из библиотеки:\n" + e + '\n');
            return Optional.empty();
        }
    }

    public Optional<IBook> getBook(int index) {
        try {
            if(index < 0 || index >= getBooksQty()) {
                throw new BookIndexOutOfBoundsException(index);
            }
            for(int i = 0; i < hallsArray.length; i++) {
                for (int j = 0; j < hallsArray[i].getBooksQty(); j++) {
                    if(index == 0) return hallsArray[i].getBook(j);
                    index--;
                }
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка получения книги по индексу " + index + " в библиотеку:\n" + e + '\n');
        }
        return Optional.empty();
    }

    public IBook[] getSortedBooks() {
        IBook[] booksArray = new IBook[getBooksQty()];
        int n = -1;
        for(int i = 0; i < getBooksQty(); i++) {
            for(int j = 0; j < hallsArray[i].getBooksQty(); j++) {
                addBook(n, getBook(n++).get());
            }
        }
        boolean isSort;
        do {
            isSort = true;
            for (int i = 0; i < getBooksQty() - 1; i++) {
                if (booksArray[i].getCost() > booksArray[i + 1].getCost()) {
                    isSort = false;
                    IBook tmpBook = booksArray[i];
                    booksArray[i] = booksArray[i + 1];
                    booksArray[i + 1] = tmpBook;
                }
            }
        }
        while(isSort == false);
        return booksArray;
    }

    public String getHallsNames() {

        String namesAndBooksQty = "";
        for(int i = 0; i < hallsArray.length; i++) {
            namesAndBooksQty += "\nЗал №" + i + ": " + '"' + hallsArray[i].getHallName() + '"' + ", количество книг -  " + hallsArray[i].getBooksQty();
        }
        return namesAndBooksQty;
    }

    public void changeHall(int index, IHall hall) {
        try {
            if(index < 0 || index >= getHallsQty()) {
                throw new HallIndexOutOfBoundsException(index);
            }
            hallsArray[index] = hall;
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка замены зала по индексу " + index + " в библиотеку:\n" + e + '\n');
        }
    }

    public void changeBook(int index, IBook book) {
        try {
            if(index < 0 || index >= getBooksQty()) {
                throw new BookIndexOutOfBoundsException(index);
            }
            removeBook(index);
            addBook(index, book);
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка замены книги по индексу " + index + " в библиотеку:\n" + e + '\n');
        }
    }

    public void addBook(int index, IBook book) {
        try {
            if (index < 0 || index > getBooksQty()) {
                throw new BookIndexOutOfBoundsException(index);
            }
            for(int i = 0; i < getHallsQty(); i++) {
                if(index > hallsArray[i].getBooksQty()) {
                    index -= hallsArray[i].getBooksQty();
                }
                else {
                    hallsArray[i].addBook(index, book);
                    return;
                }
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка добавления книги по индексу " + index + " в библиотеку:\n" + e + '\n');
        }
    }

    public void removeBook(int index) {
        try {
            if(index < 0 || index >= getBooksQty()) {
                throw new BookIndexOutOfBoundsException(index);
            }
            for(int i = 0; i < getHallsQty(); ++i) {
                if(index < hallsArray[i].getBooksQty()) {
                    hallsArray[i].removeBook(index);
                    return;
                }
                index -= hallsArray[i].getBooksQty();
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка удаления книги по индексу " + index + " из библиотеки:\n" + e + '\n');
        }
    }

    public IBook getBestBook() {
        IBook book = hallsArray[0].getBestBook();
        for(int i = 0; i < getHallsQty(); i++) {
            if(book.getCost() < hallsArray[i].getBestBook().getCost()) {
                book = hallsArray[i].getBestBook();
            }
        }
        return book;
    }
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < getHallsQty(); ++i) {
            result += "\nЗал №" + i + ":" + getHall(i).get().toString() + '\n';
        }
        return result;
    }
}
