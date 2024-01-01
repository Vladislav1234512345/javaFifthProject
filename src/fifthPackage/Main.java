package fifthPackage;

public class Main {
    public static void main(String[] args) {

        ChildrenBook book1 = new ChildrenBook("Andrei Lyapin", "JavaScript is my life", -228.666, 2022, 12);
        ChildrenBook book2 = new ChildrenBook("Sitnikov Vladislav", "How i graduated the school", 312, 2022, 16);
        ScientificBook book3 = new ScientificBook("Nikita Lyagoshin", "5 rules to become a successMAN", 243, 2021, 6.4);
        ScientificBook book4 = new ScientificBook("Matvei Zroichenko", "Psychology and maths in the poker", 534, 2019, 9.3);
        ChildrenLibraryHall hall1 = new ChildrenLibraryHall("12+", new ChildrenBook[]{book1});
        ChildrenLibraryHall hall2 = new ChildrenLibraryHall("16+", new ChildrenBook[]{book2});
        ChildrenLibrary library1 = new ChildrenLibrary(new ChildrenLibraryHall[]{hall1,hall2});
        System.out.println(library1.toString());
        library1.addBook(0, book3);
        System.out.println(library1.toString());
        library1.removeBook(1);
        System.out.println(library1.toString());
        library1.changeBook(0, new Book());
        System.out.println(library1.toString());
        library1.changeHall(0, new ChildrenLibraryHall("Не определено", 2));
        System.out.println(library1.toString());
        library1.addBook(library1.getBooksQty(), book4);
        System.out.println(library1.toString());
        System.out.println("\nОбщая стоимость всех книг в библиотеке:\n" + library1.getGeneralCost());
        System.out.println("\nСамая дорогая книга в библиотеке:\n" + library1.getBestBook());
        System.out.println(library1.getHallsNames());
        library1.changeHall(1, new ChildrenLibraryHall("Не определено", 3));
        System.out.println(library1.toString());

        Item item1 = new Item(book3);
        Item item2 = new Item(book4);
        ScientificLibraryHall hall3 = new ScientificLibraryHall("Marketing", new LinkedList(new Item[]{item1}));
        ScientificLibraryHall hall4 = new ScientificLibraryHall("Psychology", new LinkedList(new Item[]{item2}));
        DoubleItem item3 = new DoubleItem(hall3);
        DoubleItem item4 = new DoubleItem(hall4);
        ScientificLibrary library2 = new ScientificLibrary(new DoubleLinkedList(new DoubleItem[]{item3, item4}));
        System.out.println(library2.toString());
        library2.addBook(0, book1);
        System.out.println(library2.toString());
        library2.removeBook(1);
        System.out.println(library2.toString());
        library2.changeBook(0, book2);
        System.out.println(library2.toString());
        library2.addBook(library2.getBooksQty(), book3);
        System.out.println(library2.toString());
        System.out.println("\nОбщая стоимость всех книг в библиотеке:\n" + library2.getGeneralCost());
        System.out.println("\nСамая дорогая книга в библиотеке:\n" + library2.getBestBook());
        System.out.println(library2.getHallsNames());
        LinkedList changingList = new LinkedList(new Item[]{new Item(book1), new Item(book2), new Item(book3)});
        ScientificLibraryHall changingHall = new ScientificLibraryHall("18+", changingList);
        library2.changeHall(1, changingHall);
        System.out.println(library2.toString());
    }
}