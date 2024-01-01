package fifthPackage;

import java.util.Optional;

public class ScientificLibrary implements ILibrary{
    private DoubleLinkedList doubleList;

    public ScientificLibrary(int hallsQty, int[] books) {
        this.doubleList = new DoubleLinkedList();
        for (int i = 0; i < hallsQty; ++i) {
            this.doubleList.addLast(new DoubleItem(new ScientificLibraryHall("Не определено", books[i])));
        }
    }

    public ScientificLibrary(DoubleLinkedList data) {
        this.doubleList = data;
    }

    public int getHallsQty() {
        try {
            if(this.doubleList.length() < 0) {
                throw new InvalidBookCountException();
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка подсчёта залов в библиотеке:\n" + e + '\n');
        }
        return this.doubleList.length();
    }

    public int getBooksQty() {
        int booksQty = 0;
        for (int i = 0; i < getHallsQty(); ++i) {
            booksQty += getHall(i).get().getBooksQty();
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
        for (int i = 0; i < getHallsQty(); ++i) {
            generalCost += getHall(i).get().getGeneralCost();
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

    public IHall[] getHallsArray() {
        IHall[] hallsArray = new IHall[getHallsQty()];
        for(int i = 0; i < getHallsQty(); ++i) {
            hallsArray[i] = getHall(i).get();
        }
        return hallsArray;
    }

    public Optional<IHall> getHall(int index) {
        try {
            if(index < 0 || index >= getHallsQty()) {
                throw new HallIndexOutOfBoundsException(index);
            }
            return Optional.of(doubleList.getDoubleItem(index).get().data);
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
            for (int i = 0; i < getHallsQty(); ++i) {
                if (index < doubleList.getDoubleItem(i).get().data.getBooksQty())
                    return doubleList.getDoubleItem(i).get().data.getBook(index);
                index -= doubleList.getDoubleItem(i).get().data.getBooksQty();
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка получения книги по индексу " + index + " в библиотеку:\n" + e + '\n');
        }
        return Optional.empty();
    }

    public IBook[] getSortedBooks() {
        IBook[] booksArray = new IBook[getBooksQty()];
        for (int i = 0; i < booksArray.length; ++i) {
            booksArray[i] = getBook(i).get();
        }
        for (int i = 0; i < booksArray.length; ++i) {
            boolean isSorted = false;
            for (int j = 0; j < booksArray.length - 1; ++j) {
                if (booksArray[j].getCost() < booksArray[j + 1].getCost()) {
                    isSorted = true;
                    IBook tmp = booksArray[j];
                    booksArray[j] = booksArray[j + 1];
                    booksArray[j + 1] = tmp;
                }
            }
            if (!isSorted) break;
        }
        return booksArray;
    }

    public String getHallsNames() {
        String result = "";
        for (int i = 0; i < getHallsQty(); ++i) {
            result += "\n\nЗал №" + i + ":\nИмя зала - "
                    + getHall(i).get().getHallName() + ", количество книг - "
                    + getHall(i).get().getBooksQty();
        }
        return result;
    }

    public void changeHall(int index, IHall hall) {
        try {

            if(index < 0 || index >= getHallsQty()) {
                throw new HallIndexOutOfBoundsException(index);
            }
            this.doubleList.removeDoubleItem(index);
            this.doubleList.addDoubleItem(index, new DoubleItem(hall));
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
            for (int i = 0; i < getHallsQty(); ++i) {
                if (index <= getHall(i).get().getBooksQty()) {
                    getHall(i).get().addBook(index, book);
                    return;
                }
                index -= getHall(i).get().getBooksQty();
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
            for (int i = 0; i < getHallsQty(); ++i) {
                if (index < getHall(i).get().getBooksQty()) {
                    getHall(i).get().removeBook(index);
                    break;
                }
                index -= getHall(i).get().getBooksQty();
            }
        }
        catch(Exception e) {
            System.out.println("\nВозникла ошибка удаления книги по индексу " + index + " из библиотеки:\n" + e + '\n');
        }
    }

    public IBook getBestBook() {
        if (doubleList.isEmpty()) return null;
        IBook bestBook = doubleList.getDoubleItem(0).get().data.getBestBook();
        for (int i = 0; i < getHallsQty(); ++i) {
            if (bestBook.getCost() < doubleList.getDoubleItem(i).get().data.getBestBook().getCost())
                bestBook = doubleList.getDoubleItem(i).get().data.getBestBook();
        }
        return bestBook;
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
