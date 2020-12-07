package coreUtils;

public class CoreFileUtils {

    public static final String resellerStageApk = CoreFileRouters.apkPath+"app-reseller-for-automation.apk";
    public static final String resellerProdApk = "";
    public static final String mokamStageApk = CoreFileRouters.apkPath+"app-unicorn-for-automation-new.apk";
    public static final String mokamProdApk = "";
    public static final String redXStageApk = CoreFileRouters.apkPath+"app-redx-debug.apk";
    public static final String redXProdApk = "";
    public static final String storesStageApk = CoreFileRouters.apkPath+"shopupstore.apk";
    public static final String storesProdApk = "";
    public static final String chromeDriver = CoreFileRouters.chromeDriversFolderPath+"chromedriver86";
    public static final String chromeDriversFolderPath = CoreFileRouters.chromeDriversFolderPath;
    public static final String commerceSendOtpJsonPath = CoreFileRouters.commerceAuthData+"send_otp.json";
    public static final String commerceSendOtpSRJsonPath = CoreFileRouters.commerceAuthData+"send_otp_SR.json";
    public static final String commerceVerifyOtpJsonPath = CoreFileRouters.commerceAuthData+"verify_otp.json";
    public static final String commerceVerifyOtpSRJsonPath = CoreFileRouters.commerceAuthData+"verify_otp_SR.json";
    public static final String wmsUserSignInJsonPath = CoreFileRouters.wmsAuthData+"user_sign_in.json";
    public static final String cookiePropertiesFilePath = CoreFileRouters.authFolderPath+"cookie.properties";
    public static final String domainPropertiesFilePath = CoreFileRouters.scriptPropertiesFolderPath+"domains.properties";
    public static final String storeSendOtpJsonPath = CoreFileRouters.storeAuthData+"send_otp.json";
    public static final String storeLoginJsonPath = CoreFileRouters.storeAuthData+"login.json";
    public static final String searchTermJsonPath = CoreFileRouters.testDataFolderPath+"searchTerm.json";
    public static final String locationTermJsonPath = CoreFileRouters.testDataFolderPath+"locationTerm.json";
    public static final String sapSendCodeJsonPath = CoreFileRouters.sapAuthData+"request_login_code.json";
    public static final String sapVerifyCodeJsonPath = CoreFileRouters.sapAuthData+"login_with_code.json";


    public static class Reports{
        public static final String resellerAppResultsFilePath =
                CoreFileRouters.Reports.reportsFolderPath+"reseller_app.json";
        public static final String resellerWapResultsFilePath =
                CoreFileRouters.Reports.reportsFolderPath+"reseller_wap.json";
        public static final String resellerWEBResultsFilePath =
                CoreFileRouters.Reports.reportsFolderPath+"reseller_web.json";
    }
}
