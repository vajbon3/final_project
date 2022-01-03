package API;

import Data.Account;
import Data.Container;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.HashMap;

public class AccountCaller extends Caller{
    public Account account;
    public AccountCaller() {
        super("https://bookstore.toolsqa.com/Account/v1");

        // get account object from the container
        account = (Account) Container.get(Account.class.getName());
    }

    public AuthorizationResponse register() {
        // headers and body for the API call
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");

        var body = new JSONObject();
        body.put("userName",account.username);
        body.put("password",account.password);

        // make a call
        return post("/User",headers,body.toString())
                .as(AuthorizationResponse.class,getMapper());
    }

    public AuthorizationResponse login() {
        // headers and body for the API call
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");

        var body = new JSONObject();
        body.put("userName",account.username);
        body.put("password",account.password);

        // make a call
        return post("/Authorized",headers,body.toString())
                .as(AuthorizationResponse.class,getMapper());
    }
}
