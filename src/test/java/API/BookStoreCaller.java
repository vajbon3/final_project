package API;

import org.json.JSONObject;

import java.util.HashMap;

public class BookStoreCaller extends Caller{

    public BookStoreCaller() {
        super("https://bookstore.toolsqa.com/BookStore/v1");
    }

    public BookStoreResponse fetchBooks() {
        // make a call and return a mapped custom response object
        return get("/Books")
                .as(BookStoreResponse.class,getMapper());
    }
}
