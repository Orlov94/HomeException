package manager;

import data.Book;
import data.Product;
import data.Smartphone;
import exception.NotFoundException;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    Manager manager = new Manager();
    Book book1 = new Book(101, "brave new world", 500, "aldous leonard huxley");
    Book book2 = new Book(102, "three comrades,", 450, "remarque erich maria");
    Book book3 = new Book(103, "harry potter and the deathly hallows,", 900, "joanne rowling");
    Book book4 = new Book(104, "atlas shrugged,", 1200, "jayn rand");

    Smartphone smartphone1 = new Smartphone(205, "iphone 12", 70_000, "usa");
    Smartphone smartphone2 = new Smartphone(345, "honor", 23_500, "china");
    Smartphone smartphone3 = new Smartphone(428, "huawei", 35_000, "china");
    Smartphone smartphone4 = new Smartphone(587, "samsung", 59_000, "south korea");


    @Test
    @Description("Добавление товара в корзину")
    void case1() {
        manager.addNew(book1);
        manager.addNew(smartphone1);

        Product[] actual = manager.searchProduct();
        Product[] expected = {book1, smartphone1};

        assertArrayEquals(actual, expected);

    }


    @Test
    @Description("Поиск совпадения букв по имени")
    void case2() {
        manager.addNew(book2);
        manager.addNew(smartphone1);
        manager.addNew(book3);

        Product[] actual = manager.searchByMatches("th");
        Product[] expected = {book2, book3};

        assertArrayEquals(actual, expected);

    }

    @Test
    @Description("Удалить ранее добавленную книгу")
    void case3() {
        manager.addNew(book2);
        manager.addNew(smartphone1);
        manager.addNew(book3);
        manager.addNew(book1);

        manager.delete(103);

        Product[] actual = manager.searchProduct();
        Product[] expected = {book2, smartphone1, book1};

        assertArrayEquals(actual, expected);

    }

    @Test
    @Description("Удаление по id которого нет в списке")
    void case4() {
        manager.addNew(book2);
        manager.addNew(smartphone1);
        manager.addNew(book3);
        manager.addNew(book1);


        assertThrows(NotFoundException.class, () -> {
            manager.delete(1949);
        });

    }



}

