package utils;

import coreUtils.CoreConstants;
import testData.ReadJSONFile;
import services.commerceInterfaces.CommerceServices;
import services.commerceMethods.*;


public class ServiceRequestLayer implements CommerceServices {

    private final String module;

    public ServiceRequestLayer(){
        this.module = CoreConstants.MODULE_ANDROID_UI;
    }


    @Override
    public GetCommerceApiResponse getControlOverServices() {
        return new GetCommerceApiResponse(module);
    }

    @Override
    public GetMyBagApiResponse getMyBagControl() {
        return new GetMyBagApiResponse(module);
    }

    @Override
    public void getControlOverSearchForUserApi() {

    }

    @Override
    public void getControlOverUserProfileApi() {

    }

    @Override
    public void getControlOverCollectionsApi() {

    }

    @Override
    public void getControlOverMyCartApi() {

    }

    @Override
    public GetAuthenticationApiResponse getControlOverAuthentication() {
        return new GetAuthenticationApiResponse(module);
    }

    @Override
    public GetPLPModuleApiResponse getControlOverPLPModuleApiResponse() {
        return new GetPLPModuleApiResponse(module);
    }

    @Override
    public GetMyOrderApiResponse getControlOverMyOrderApiResponse() {
        return new GetMyOrderApiResponse(module);
    }

    @Override
    public GetSalesRepApiResponse getControlOverSalesRepApiResponse() {
        return new GetSalesRepApiResponse(module);
    }


    public GetSearchSuggestionsApiResponse getControlOverSearchSuggestionsApi() {
        return new GetSearchSuggestionsApiResponse(module);
    }

    public ReadJSONFile getControlOverReadJSONFile(){
        return new ReadJSONFile(module);
    }

    @Override
    public GetPDPModuleApiResponse getControlOverPDPModuleApiResponse() {
        return new GetPDPModuleApiResponse(module);
    }

}
