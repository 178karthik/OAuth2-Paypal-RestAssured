import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OAuth2_Authentication_RestAssured {

    private static final String  CLIENT_ID = "AeLPLRy7l-eETcbYbPZH41Bf1RCWfPSA7Jx6hS9Fa47jIv4RAX9cnDrftpdYXkD4agKAEdS6KYgYQu9Q";
    private static final String  CLIENT_SECRET = "EM5R1iSK5k5nbRljytRNORawMyrggaLPnQ3HaZm-LEK8NDhBMtxTvo1F7EwSZl2wZNrkikNotnwp8_tk";
    private static final String PAYPAL_OAUTH_URL = "https://api.sandbox.paypal.com/v1/oauth2/token";
    private static final String PAYPAL_API_URL = "https://api.sandbox.paypal.com/v2/invoicing/templates";
    private  static  String accessToken;

@Test
public void getPaypalInvoiceTemplateList()
{
    /*
    1. Get Client ID & Secret
    2.Get Access Token using client id and secret
    3.Hit the end point and get the data
     */

    //way 2
   accessToken= RestAssured.given()
            .auth().preemptive().basic(CLIENT_ID,CLIENT_SECRET)
            .param("grant_type","client_credentials")
            .when()
            .post(PAYPAL_OAUTH_URL).jsonPath().getString("access_token");

   System.out.println("*****************The access token is:************"+accessToken);
   int statuscode =  RestAssured.given()
            .header("Authorization","Bearer "+accessToken)
            .header("Content_Type","application/json")
            .when()
            .get(PAYPAL_API_URL)
            .then().extract().statusCode();
    Assert.assertEquals(statuscode,200);















      /*way 1
    RestAssured.baseURI = PAYPAL_OAUTH_URL;
    RequestSpecification requestSpec = RestAssured.given();
    requestSpec.auth().preemptive().basic(CLIENT_ID,CLIENT_SECRET);
    requestSpec.param("grant_type","client_credentials");


    Response response = requestSpec.post();
    System.out.println(response.getBody().prettyPrint());

    accessToken = response.getBody().jsonPath().getString("access_token");

    System.out.println("************************************************************");
    System.out.println("Access Token is:"+accessToken);
    System.out.println("************************************************************");
    RestAssured.baseURI = PAYPAL_API_URL;
    requestSpec = RestAssured.given();
    requestSpec.header("Authorization","Bearer "+accessToken);
    requestSpec.header("Content-Type","application/json");
    response = requestSpec.get();

    System.out.println(response.getBody().prettyPrint()); */






}

}
