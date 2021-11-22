package com.ta.steps;

import com.ta.pojos.*;
import com.ta.utils.GetConfigProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TestSteps {

    private final RequestSpecification REQUEST_SPECIFICATION =
              new RequestSpecBuilder()
             .setBaseUri(GetConfigProperties.getTestingService())
             .setContentType(ContentType.JSON)
             .build();

    private AbstractPojo getPojoFromRequest(String basePath, AbstractPojo pojoClass ) {
        return  given()
                .spec(REQUEST_SPECIFICATION)
                .basePath(basePath)
                .when().get()
                .then().statusCode(200)
                .extract().jsonPath().getObject("data", pojoClass.getClass());
    }

    private List<? extends AbstractPojo> getListPojoFromRequest(String basePath, AbstractPojo pojoClass ) {
        return  given()
                .spec(REQUEST_SPECIFICATION)
                .basePath(basePath)
                .when().get()
                .then().statusCode(200)
                .extract().jsonPath().getList("data", pojoClass.getClass());
    }

    private AbstractPojo getPojoFromPostRequest(AbstractPojo request, String basePath, int statusCode, AbstractPojo pojoClass){
        return given()
                .spec(REQUEST_SPECIFICATION)
                .basePath(basePath)
                .body(request)
                .post()
                .then().statusCode(statusCode)
                .extract().as(pojoClass.getClass());
   }

    public UserPojo getUser() {
       return (UserPojo) getPojoFromRequest("/users/2", new UserPojo());
   }

    public ResourcePojo getResource() {
        return (ResourcePojo) getPojoFromRequest("/unknown/2", new ResourcePojo());
    }

    public List<UserPojo> getListUsers(){
       return (List<UserPojo>) getListPojoFromRequest("/users", new UserPojo());
    }

    public List<ResourcePojo> getListResources(){
        return (List<ResourcePojo>) getListPojoFromRequest("/unknown", new ResourcePojo());
    }

    public ResponsePostUserPojo createUser(RequestUserPojo requestUser){
       return (ResponsePostUserPojo) getPojoFromPostRequest(requestUser, "/users", 201,
               new ResponsePostUserPojo());
    }

   public ResponseAuthorizationPojo authorizationRequest(RequestLoginPojo login){
       return (ResponseAuthorizationPojo) getPojoFromPostRequest(login, "/login",
               200, new ResponseAuthorizationPojo());
    }

   public ResponseRegistrationPojo registrationRequest(RequestLoginPojo login){
       return (ResponseRegistrationPojo) getPojoFromPostRequest(login, "/register", 200,
               new ResponseRegistrationPojo());
   }
   public UpdateUserPojo putUpdateUser(RequestUserPojo requestUser){
       return given()
               .spec(REQUEST_SPECIFICATION)
               .basePath("/users/2")
               .body(requestUser)
               .put()
               .then().statusCode(200)
               .extract().as(UpdateUserPojo.class);
   }

   public UpdateUserPojo patchUpdateUser(RequestUserPojo requestUser){
       return given()
               .spec(REQUEST_SPECIFICATION)
               .basePath("/users/2")
               .body(requestUser)
               .patch()
               .then().statusCode(200)
               .extract().as(UpdateUserPojo.class);
   }

   public RequestUserPojo generateUser(String name, String job){
       RequestUserPojo user = new RequestUserPojo();
       user.setName(name);
       user.setJob(job);
       return user;
   }

   public RequestLoginPojo generateLogin(String email, String password){
       RequestLoginPojo login = new RequestLoginPojo();
       login.setEmail(email);
       login.setPassword(password);
       return login;
    }
   public RequestSpecification getRequestSpecification() {
       return REQUEST_SPECIFICATION;
   }
}
