package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductManagerTest {
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductManager manager;

    private final Product product = new Product(3456, "Product", 4567);
    private final Book book1 = new Book(1232, "Book 1", 3456, "Author 1");
    private final Book book2 = new Book(3456, "Book 2", 8756, "Author 1");
    private final Book book3 = new Book(5678, "Book 3", 8756, "Author 2");
    private final Smartphone smartphone1 = new Smartphone(4567, "Phone 1", 45670, "Apple");
    private final Smartphone smartphone2 = new Smartphone(9865, "Phone 2", 98677, "Apple");
    private final Smartphone smartphone3 = new Smartphone(5687, "Phone 3", 45670, "Android");
    private final Product[] returned = new Product[]{book1, book2, book3, smartphone1, smartphone2, smartphone3, product};

    @Test
    public void shouldSearchBookByAuthor() {
        Product[] excepted = new Product[]{book1, book2};
        doReturn(returned).when(repository).findAll();

        Product[] actual = manager.searchBy("Author 1");
        assertArrayEquals(excepted, actual, "При поиске книг по автору, массивы не совпадают!");
    }

    @Test
    public void shouldSearchBookByName() {
        Product[] excepted = new Product[]{book3};
        doReturn(returned).when(repository).findAll();

        Product[] actual = manager.searchBy("Book 3");
        assertArrayEquals(excepted, actual, "При поиске книг по названию, массивы не совпадают!");
    }

    @Test
    public void shouldSearchSmartphoneByManufacture() {
        Product[] excepted = new Product[]{smartphone1, smartphone2};
        doReturn(returned).when(repository).findAll();

        Product[] actual = manager.searchBy("Apple");
        assertArrayEquals(excepted, actual, "При поиске смартфонов по производителю, массивы не совпадают!");
    }

    @Test
    public void shouldSearchSmartphoneByName() {
        Product[] excepted = new Product[]{smartphone3};
        doReturn(returned).when(repository).findAll();

        Product[] actual = manager.searchBy("Phone 3");
        assertArrayEquals(excepted, actual, "При поиске смартфонов по названию, массивы не совпадают!");
    }


}