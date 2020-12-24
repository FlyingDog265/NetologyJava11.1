package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.helpers.NotFoundException;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductManagerTestWithoutMock {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    private final Book book1 = new Book(1232, "Book 1", 3456, "Author 1");
    private final Book book2 = new Book(5678, "Book 2", 3456, "Author 1");
    private final Smartphone smartphone1 = new Smartphone(5687, "Phone 3", 45670, "Android");
    private final Product[] excepted = new Product[]{book1};

    @Test
    public void shouldAddSomeStuffInRepo() {
        manager.add(book1);
        manager.add(smartphone1);

        Product[] actual = manager.searchBy("Author 1");
        assertArrayEquals(excepted, actual, "Добавление продуктов в репо поломалось");
    }

    @Test
    public void shouldRemoveSomeStuffFromRepo() {
        manager.add(book1);
        manager.add(book2);
        manager.removeById(5678);

        Product[] actual = manager.searchBy("Author 1");
        assertArrayEquals(excepted, actual, "Удаление продуктов из репо поломалось");
    }

    @Test
    public void shouldShowNotFoundException() {
        manager.add(book1);
        manager.add(book2);
        assertThrows(NotFoundException.class, () -> manager.removeById(45654),
                "Ошибка удвления несуществующего продукта из репо поломалась");
    }
}
