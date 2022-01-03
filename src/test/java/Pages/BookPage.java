package Pages;

import API.Book;
import API.BookStoreCaller;
import API.BookStoreResponse;
import Data.Container;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.util.function.Predicate.not;

public class BookPage extends BasePage {
    public void validateBooks() {
        // get books from O'Reilly Media
        $("#searchBox").sendKeys("O'Reilly Media");

        var bookStoreCaller = (BookStoreCaller)Container.get(BookStoreCaller.class.getName());

        // get books from both API and the website
        Book[] apiBooks = Arrays.stream(bookStoreCaller.fetchBooks().books)
                .filter(book -> book.publisher.equals("O'Reilly Media"))
                .toArray(Book[]::new);
        Book[] websiteBooks = getBooks();

        System.out.println(Arrays.toString(apiBooks));
        System.out.println(Arrays.toString(websiteBooks));
            // validations
        // validate that the list is same size everywhere
        Assert.assertEquals(apiBooks.length,websiteBooks.length);

        // validate that last book is 'Understanding ECMAScript 6' everywhere
        String validTitle = "Understanding ECMAScript 6";

        Assert.assertEquals(apiBooks[apiBooks.length-1].title,validTitle);
        Assert.assertEquals(websiteBooks[websiteBooks.length-1].title,validTitle);
    }

    private Book[] getBooks() {
        var books = $$(".rt-tr-group");

        return books
                .stream()
                .filter(book -> !book.find(By.cssSelector(".rt-td:nth-child(2)")).getText().equals(" "))
                .map(book -> new Book(
                        book.find(By.cssSelector(".rt-td:nth-child(2)")).getText(),
                        book.find(By.cssSelector(".rt-td:last-child")).getText()))
                .toArray(Book[]::new);
    }
}
