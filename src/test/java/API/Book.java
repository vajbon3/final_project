package API;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;

public class Book {
    @JsonProperty("title")
    public String title;
    @JsonProperty("publisher")
    public String publisher;

    @ConstructorProperties({"title", "publisher"})
    public Book(String title, String publisher) {
        this.title = title;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
