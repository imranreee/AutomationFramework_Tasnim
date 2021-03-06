package services.serviceUtils;

import auth.CookieManager;
import coreUtils.BuildParameterKeys;
import coreUtils.CoreConstants;
import coreUtils.CoreFileUtils;
import coreUtils.DomainPropertyReader;
import dataBase.DataBaseCore;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.*;

import static io.restassured.RestAssured.given;

public class ShopUpPostMan {

    // This Layer is basically to hit the get requests and send back the response

    private String baseURL;
    private String cookie;
    private String module;
    private String cookieKey;
    private String sessionCookie;
    private String sessionCookieKey;
    private String user;

    public ShopUpPostMan(String module){
        this.module = module;
        String app = System.getProperty(BuildParameterKeys.KEY_APP);
        String env = System.getProperty(BuildParameterKeys.KEY_ENV);
        user = System.getProperty(BuildParameterKeys.KEY_USER);
//        String app = CoreConstants.APP_WMS;
//        String env = CoreConstants.ENV_STAGE;

        try{

            switch (module.toLowerCase()){
                case CoreConstants.MODULE_ANDROID_UI :
                    switch (env){
                        case CoreConstants.ENV_STAGE :
                            switch (app){
                                case CoreConstants.APP_RESELLER :
                                    this.baseURL =
                                            DomainPropertyReader.
                                                    getValueOfKey(DomainPropertyReader.Keys.RESELLER_APP_STAGE_BASE_URL);
                                    cookieKey = CookieManager.Keys.RESELLER_COOKIE;
                                    break;
                                case CoreConstants.APP_MOKAM :
                                    this.baseURL =
                                            DomainPropertyReader.
                                                    getValueOfKey(DomainPropertyReader.Keys.MOKAM_APP_STAGE_BASE_URL);
                                    cookieKey = CookieManager.Keys.MOKAM_COOKIE;
                                    break;
                            }
                            break;
                        case CoreConstants.ENV_PROD :
                            switch (app){
                                case CoreConstants.APP_RESELLER :
                                    this.baseURL =
                                            DomainPropertyReader.
                                                    getValueOfKey(DomainPropertyReader.Keys.RESELLER_APP_PROD_BASE_URL);
                                    cookieKey = CookieManager.Keys.RESELLER_COOKIE;
                                    break;
                                case CoreConstants.APP_MOKAM :
                                    this.baseURL =
                                            DomainPropertyReader.
                                                    getValueOfKey(DomainPropertyReader.Keys.MOKAM_APP_PROD_BASE_URL);
                                    cookieKey = CookieManager.Keys.MOKAM_COOKIE;
                                    break;
                            }
                            break;
                    }
                    break;

                case CoreConstants.MODULE_MOBILE_WEB_UI :
                    switch (env){
                        case CoreConstants.ENV_STAGE :
                            switch (app){
                                case CoreConstants.APP_RESELLER :
                                    this.baseURL =
                                            DomainPropertyReader.
                                                    getValueOfKey(DomainPropertyReader.Keys.RESELLER_WAP_STAGE_BASE_URL);
                                    cookieKey = CookieManager.Keys.RESELLER_COOKIE;
                                    break;
                                case CoreConstants.APP_MOKAM :
                                    this.baseURL =
                                            DomainPropertyReader.
                                                    getValueOfKey(DomainPropertyReader.Keys.MOKAM_WAP_STAGE_BASE_URL);
                                    cookieKey = CookieManager.Keys.MOKAM_COOKIE;
                                    break;
                            }
                            break;
                        case CoreConstants.ENV_PROD :
                            switch (app){
                                case CoreConstants.APP_RESELLER :
                                    this.baseURL =
                                            DomainPropertyReader.
                                                    getValueOfKey(DomainPropertyReader.Keys.RESELLER_WAP_PROD_BASE_URL);
                                    cookieKey = CookieManager.Keys.RESELLER_COOKIE;
                                    break;
                                case CoreConstants.APP_MOKAM :
                                    this.baseURL =
                                            DomainPropertyReader.
                                                    getValueOfKey(DomainPropertyReader.Keys.MOKAM_WAP_PROD_BASE_URL);
                                    cookieKey = CookieManager.Keys.MOKAM_COOKIE;
                                    break;
                            }
                            break;
                    }
                    break;

                case CoreConstants.MODULE_WEB_UI :
                    switch (env){
                        case CoreConstants.ENV_STAGE :
                            switch (app){
                                case CoreConstants.APP_RESELLER :
                                    this.baseURL =
                                            DomainPropertyReader.
                                                    getValueOfKey(DomainPropertyReader.Keys.RESELLER_WEB_STAGE_BASE_URL);
                                    cookieKey = CookieManager.Keys.RESELLER_COOKIE;
                                    break;
                                case CoreConstants.APP_MOKAM :
                                    this.baseURL =
                                            DomainPropertyReader.
                                                    getValueOfKey(DomainPropertyReader.Keys.MOKAM_WEB_STAGE_BASE_URL);
                                    cookieKey = CookieManager.Keys.MOKAM_COOKIE;
                                    break;
                            }
                            break;
                        case CoreConstants.ENV_PROD :
                            switch (app){
                                case CoreConstants.APP_RESELLER :
                                    this.baseURL =
                                            DomainPropertyReader.
                                                    getValueOfKey(DomainPropertyReader.Keys.RESELLER_WEB_PROD_BASE_URL);
                                    cookieKey = CookieManager.Keys.RESELLER_COOKIE;
                                    break;
                                case CoreConstants.APP_MOKAM :
                                    this.baseURL =
                                            DomainPropertyReader.
                                                    getValueOfKey(DomainPropertyReader.Keys.MOKAM_WEB_PROD_BASE_URL);
                                    cookieKey = CookieManager.Keys.MOKAM_COOKIE;
                                    break;
                            }
                            break;
                    }
                    break;

                case CoreConstants.MODULE_STORE_WAP :

                    switch (env){
                        case CoreConstants.ENV_STAGE : this.baseURL =
                                DomainPropertyReader.
                                        getValueOfKey(DomainPropertyReader.Keys.STORE_WAP_STAGE_BASE_URL);
                            cookieKey = CookieManager.Keys.STORE_WAP_COOKIE;
                            break;
                        case CoreConstants.ENV_PROD : this.baseURL =
                                DomainPropertyReader.
                                        getValueOfKey(DomainPropertyReader.Keys.STORE_WAP_PROD_BASE_URL);
                            cookieKey = CookieManager.Keys.STORE_WAP_COOKIE;
                            break;
                    }
                    break;

                case CoreConstants.MODULE_WMS_UI :

                    switch (env){
                        case CoreConstants.ENV_STAGE : this.baseURL =
                                DomainPropertyReader.
                                        getValueOfKey(DomainPropertyReader.Keys.WMS_WEB_STAGE_BASE_URL);
                            cookieKey = CookieManager.Keys.WMS_COOKIE;
                            sessionCookieKey = CookieManager.Keys.WMS_SESSION;
                            break;
                        case CoreConstants.ENV_PROD : this.baseURL =
                                DomainPropertyReader.
                                        getValueOfKey(DomainPropertyReader.Keys.WMS_WEB_PROD_BASE_URL);
                            cookieKey = CookieManager.Keys.WMS_COOKIE;
                            break;
                    }
                    break;

                case CoreConstants.MODULE_SAP_UI :

                    switch (env){
                        case CoreConstants.ENV_STAGE : this.baseURL =
                                DomainPropertyReader.
                                        getValueOfKey(DomainPropertyReader.Keys.SAP_API_STAGE_BASE_URL);
                            cookieKey = CookieManager.Keys.SAP_WEB_COOKIE;
                            break;
                        case CoreConstants.ENV_PROD : this.baseURL =
                                DomainPropertyReader.
                                        getValueOfKey(DomainPropertyReader.Keys.SAP_API_PROD_BASE_URL);
                            cookieKey = CookieManager.Keys.SAP_WEB_COOKIE;
                            break;
                    }
                    break;

                case CoreConstants.MODULE_REDX_WEB_UI :

                    switch (env){
                        case CoreConstants.ENV_STAGE : this.baseURL =
                                DomainPropertyReader.
                                        getValueOfKey(DomainPropertyReader.Keys.REDX_API_STAGE_BASE_URL);
                            cookieKey = CookieManager.Keys.RED_X_COOKIE;
                            break;
                        case CoreConstants.ENV_PROD : this.baseURL =
                                DomainPropertyReader.
                                        getValueOfKey(DomainPropertyReader.Keys.REDX_WEB_PROD_BASE_URL);
                            cookieKey = CookieManager.Keys.RED_X_COOKIE;
                            break;
                    }
                    break;

            }

        }catch (Exception e){
            System.out.println("Exception At ShopUpPostman : Reading BaseURL from DomainPropertyReader");
            System.out.println(e);
        }

    }


    public Response getCall(String path){
      Response response = null;
        HashMap<String,String> headers = new HashMap();
        String cookie = null;
        try{
            cookie = CookieManager.getValueOfKey(cookieKey);
//            sessionCookie = CookieManager.getValueOfKey(sessionCookieKey);
            //System.out.println("User Cookie is : "+cookie);
        }catch (Exception e){
            System.out.println("Exception at reading : CookieValue : getCall : ShopUpPostMan");
        }
        System.out.println("Control in GetCall");
        System.out.println("Base-URL is : "+baseURL);
        RestAssured.baseURI = baseURL;
        System.out.println("Final URL is : "+baseURL+path);
      
        if(module == CoreConstants.MODULE_WMS_UI) {
            response = given().header("Content-Type", "application/json")
                    .header("cookie", cookie)
                    .header("cookie", sessionCookie)
                    .when()
                    .get(path);
            //return response;
        }
        else{
            response = given().header("Content-Type", "application/json")
                    .header("cookie", cookie)
                    .when()
                    .get(path);
            //return response;
        }
        if(response.getStatusCode() == 503 || response.getStatusCode() == 502){
            response.then().log().all();
            // Exit Java Process
            System.out.println("Service is temporarily unavailable : "+ response.getStatusCode());
            System.exit(1);
        }
        return response;
    }



    public Response postCall(String path,Map object){
        String cookie = null;
        try{
            cookie = CookieManager.getValueOfKey(cookieKey);
            //System.out.println("User Cookie is : "+cookie);
        }catch (Exception e){
            System.out.println("Exception at reading : CookieValue : postCall : ShopUpPostMan");
        }
        System.out.println("Control in PostCall");
        System.out.println("Base-URL is : "+baseURL);
        RestAssured.baseURI = baseURL;
        System.out.println("Final URL is : "+baseURL+path);
        JSONObject request=new JSONObject(object);
        System.out.println(request);
        Response  response = given().header("Content-Type","application/json")
                .header("cookie",cookie)
                .body(request.toJSONString())
                .when()
                .post(path);
        if(response.getStatusCode() == 503 || response.getStatusCode() == 502){
            response.then().log().all();
            // Exit Java Process
            System.out.println("Service is temporarily unavailable : "+ response.getStatusCode());
            System.exit(1);
        }
        return response;
    }

    public Response putCall(String path,Map object){
        String cookie = null;
        try{
            cookie = CookieManager.getValueOfKey(cookieKey);
            //System.out.println("User Cookie is : "+cookie);
        }catch (Exception e){
            System.out.println("Exception at reading : CookieValue : putCall : ShopUpPostMan");
        }
        System.out.println("Control in PutCall");
        System.out.println("Base-URL is : "+baseURL);
        RestAssured.baseURI = baseURL;
        System.out.println("Final URL is : "+baseURL+path);
        JSONObject request=new JSONObject(object);
        System.out.println(request);
        Response  response = given().header("Content-Type","application/json")
                .header("cookie",cookie)
                .body(request.toJSONString())
                .when()
                .put(path);
        if(response.getStatusCode() == 503 || response.getStatusCode() == 502){
            response.then().log().all();
            // Exit Java Process
            System.out.println("Service is temporarily unavailable : "+ response.getStatusCode());
            System.exit(1);
        }
        return response;
    }


    public void performAuthenticationInShopUpPostman(){
        Response response = null;
        String patch = null;
        String filePath1 = null;
        String filePath2 = null;
        System.out.println("Control in PerformAuthentication");
        System.out.println("Base-URL is : "+baseURL);
        RestAssured.baseURI = baseURL;
        if(module.equalsIgnoreCase(CoreConstants.MODULE_ANDROID_UI)){
            try
            {
                if (user.equalsIgnoreCase(CoreConstants.MOKAM_USER)){
                patch = EndPoints.COMMERCE_SEND_USER_OTP;
                System.out.println("Final URL : "+baseURL+patch);
                filePath1 = CoreFileUtils.commerceSendOtpSRJsonPath;
                System.out.println(filePath1);
                filePath2 = CoreFileUtils.commerceVerifyOtpSRJsonPath;
                System.out.println(filePath2);}
            }
            catch(Exception e){
            patch = EndPoints.COMMERCE_SEND_USER_OTP;
            System.out.println("Final URL : "+baseURL+patch);
            filePath1 = CoreFileUtils.commerceSendOtpJsonPath;
            System.out.println(filePath1);
            filePath2 = CoreFileUtils.commerceVerifyOtpJsonPath;
            System.out.println(filePath2);
            }
        }
//       else if(module.equalsIgnoreCase(CoreConstants.MODULE_WMS_UI)){
//             patch = EndPoints.WMS.USER_SIGN_IN;
//             System.out.println("Final URL : "+baseURL+patch);
//             filePath1 = CoreFileUtils.wmsUserSignInJsonPath;
//             System.out.println(filePath1);
        //} 
  else if (module.equalsIgnoreCase(CoreConstants.MODULE_STORE_WAP)) {
            patch = EndPoints.Store.SEND_OTP;
            System.out.println("Final URL : "+baseURL+patch);
            filePath1 = CoreFileUtils.storeSendOtpJsonPath;
            System.out.println(filePath1);
            filePath2 = CoreFileUtils.storeLoginJsonPath;
            System.out.println(filePath2);
        }

  else if(module.equalsIgnoreCase(CoreConstants.MODULE_SAP_UI)){
            patch = EndPoints.Sap.REQUEST_LOGIN_CODE;
            System.out.println("Final URL : "+baseURL+patch);
            filePath1 = CoreFileUtils.sapSendCodeJsonPath;
            System.out.println(filePath1);
            filePath2 = CoreFileUtils.sapVerifyCodeJsonPath;
            System.out.println(filePath2);
        }
  else if(module.equalsIgnoreCase(CoreConstants.MODULE_REDX_WEB_UI)){
            patch = EndPoints.RedX.SEND_OTP;
            System.out.println("Final URL : "+baseURL+patch);
            filePath1 = CoreFileUtils.redxSendOtpJsonPath;
            System.out.println(filePath1);
            filePath2 = CoreFileUtils.redxVerifyOtpJsonPath;
            System.out.println(filePath2);
        }

        try{
            Object obj1 = new JSONParser().parse(new FileReader(filePath1));
            JSONObject jo1 = (JSONObject) obj1;
            //System.out.println("Body is : "+jo1);
            response = given().header("Content-Type","application/json")
                    .body(jo1).post(patch);
            response.then().log().all();
            if(module.equalsIgnoreCase(CoreConstants.MODULE_ANDROID_UI)){
                //System.out.println("COMMERCE_VERIFY_OTP URL : "+baseURL+EndPoints.COMMERCE_VERIFY_OTP);
                Object obj2 = new JSONParser().parse(new FileReader(filePath2));
                JSONObject jo2 = (JSONObject) obj2;
                //System.out.println("Body is : "+jo2);
                response = given().header("Content-Type","application/json")
                        .body(jo2).post(EndPoints.COMMERCE_VERIFY_OTP);
                response.then().log().all();
            }if(module.equalsIgnoreCase(CoreConstants.MODULE_STORE_WAP)){
                // Get OTP From DataBase
//                String otp = new DataBaseCore().getOTPForStore();
                String otp = "666666";
                Object obj2 = new JSONParser().parse(new FileReader(filePath2));
                JSONObject jo2 = (JSONObject) obj2;
                // Update OTP
                ((JSONObject) obj2).put("otp",otp);
                response = given().header("Content-Type","application/json")
                        .body(jo2).post(EndPoints.Store.LOGIN);
                response.then().log().all();
            }if(module.equalsIgnoreCase(CoreConstants.MODULE_SAP_UI)){
                Object obj2 = new JSONParser().parse(new FileReader(filePath2));
                JSONObject jo2 = (JSONObject) obj2;
                //System.out.println("Body is : "+jo2);
                response = given().header("Content-Type","application/json")
                        .body(jo2).post(EndPoints.Sap.LOGIN_WITH_CODE);
                response.then().log().all();
            }if(module.equalsIgnoreCase(CoreConstants.MODULE_REDX_WEB_UI)){
                Object obj2 = new JSONParser().parse(new FileReader(filePath2));
                JSONObject jo2 = (JSONObject) obj2;
                response = given().header("Content-Type","application/json")
                        .body(jo2).post(EndPoints.RedX.LOGIN);
                response.then().log().all();
            }
            // Update cookie.properties file
            cookie = response.getHeader("Set-Cookie");
            CookieManager.setValue(cookieKey,response.getHeader("Set-Cookie"));

        }catch (Exception e){
            System.out.println(e);
            System.out.println("Exception At PerformAuthentication");
        }

    }

}
