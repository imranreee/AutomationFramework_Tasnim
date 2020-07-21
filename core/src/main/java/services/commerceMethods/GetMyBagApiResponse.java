package services.commerceMethods;

import com.google.gson.Gson;
import coreUtils.CoreConstants;
import io.restassured.response.Response;
import services.responseModels.commerceModels.ShoppingCartResponseModel;
import services.serviceUtils.EndPoints;
import services.serviceUtils.ShopUpPostMan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetMyBagApiResponse {

    private ShopUpPostMan shopUpPostMan;
    private String module;
    private Gson gson;
    private Response response;

    public GetMyBagApiResponse(String module){
        this.module = module;
        shopUpPostMan = new ShopUpPostMan(module);
        gson = new Gson();
    }


    public List<ShoppingCartResponseModel.CartItemsBean> cartItemsBeanList(){
        response = shopUpPostMan.getCall(EndPoints.SHOPPING_CART_JSON);
        ShoppingCartResponseModel shoppingCartResponseModel = gson.fromJson(response.getBody().asString(),ShoppingCartResponseModel.class);
        return shoppingCartResponseModel.getCart_items();
    }


    public Map<String,List<String>> getProductDetails(String productName,String size){
        List<ShoppingCartResponseModel.CartItemsBean> cartItemsBeanList = new ArrayList<>();
        Map<String,List<String>> productDetailsMap = new HashMap<>();
        List<String> listOfProductDetails = new ArrayList<>();
        response = shopUpPostMan.getCall(EndPoints.SHOPPING_CART_JSON);
        ShoppingCartResponseModel shoppingCartResponseModel = gson.fromJson(response.getBody().asString(),ShoppingCartResponseModel.class);
        for(int a = 0;a< shoppingCartResponseModel.getCart_items().size();a++){
           if(productName.equalsIgnoreCase(shoppingCartResponseModel.getCart_items().get(a).getProduct_name())
                             && size.equalsIgnoreCase(shoppingCartResponseModel.getCart_items().get(a).getSize())) {
               cartItemsBeanList.add(shoppingCartResponseModel.getCart_items().get(a));
               listOfProductDetails.add(0,shoppingCartResponseModel.getCart_items().get(a).getImage());
               listOfProductDetails.add(1,shoppingCartResponseModel.getCart_items().get(a).getProduct_name());
               listOfProductDetails.add(2,shoppingCartResponseModel.getCart_items().get(a).getFormatted_variant_price());
               listOfProductDetails.add(3,shoppingCartResponseModel.getCart_items().get(a).getFormatted_income());
               listOfProductDetails.add(4,shoppingCartResponseModel.getCart_items().get(a).getFormatted_per_item_total());
               listOfProductDetails.add(5,shoppingCartResponseModel.getCart_items().get(a).getPrice());
               listOfProductDetails.add(6,Integer.toString(shoppingCartResponseModel.getCart_items().get(a).getMax_selling_price()));
               listOfProductDetails.add(7,Integer.toString(shoppingCartResponseModel.getCart_items().get(a).getMin_selling_price()));
               listOfProductDetails.add(8,Integer.toString(shoppingCartResponseModel.getCart_items().get(a).getQuantity()));
               listOfProductDetails.add(9,shoppingCartResponseModel.getCart_items().get(a).getSize());
               break;
           }
        }
//        for(int i=0;i < shoppingCartResponseModel.getCart_items().size();i++){
//            cartItemsBeanList.add(i,shoppingCartResponseModel.getCart_items().get(i));
//        }
        productDetailsMap.put(productName,listOfProductDetails);
        return productDetailsMap;
    }


    public List<Integer> getCharges(){
        List<Integer> arrayList = new ArrayList<>();
        response = shopUpPostMan.getCall(EndPoints.SHOPPING_CART_JSON);
        ShoppingCartResponseModel shoppingCartResponseModel = gson.fromJson(response.getBody().asString(),ShoppingCartResponseModel.class);
        arrayList.add(0,shoppingCartResponseModel.getOrder_data().getTotal());
        arrayList.add(1,shoppingCartResponseModel.getOrder_data().getTotal_income());
        arrayList.add(2,shoppingCartResponseModel.getMinimum_delivery_charge());
        arrayList.add(3,shoppingCartResponseModel.getMaximum_delivery_charge());
        arrayList.add(4,shoppingCartResponseModel.getNon_cod_adjustments().get(0).getValue_amount());
        if(shoppingCartResponseModel.getNon_cod_adjustments().size()>1){
            arrayList.add(5,shoppingCartResponseModel.getNon_cod_adjustments().get(1).getValue_amount());
        }
        return arrayList;
    }


    public Map<Integer,List<String>> getContainerDetailsMap(){
        Map<Integer,List<String>> getContainerData = new HashMap<>();
        response = shopUpPostMan.getCall(EndPoints.SHOPPING_CART_JSON);
        ShoppingCartResponseModel shoppingCartResponseModel = gson.fromJson(response.getBody().asString(),ShoppingCartResponseModel.class);
        for(int i= 0;i<shoppingCartResponseModel.getCart_items().size();i++){
            List<String> listOfProductDetails = new ArrayList<>();
            listOfProductDetails.add(0,shoppingCartResponseModel.getCart_items().get(i).getImage());
            listOfProductDetails.add(1,shoppingCartResponseModel.getCart_items().get(i).getProduct_name());
            listOfProductDetails.add(2,shoppingCartResponseModel.getCart_items().get(i).getFormatted_variant_price());
            listOfProductDetails.add(3,shoppingCartResponseModel.getCart_items().get(i).getFormatted_income());
            listOfProductDetails.add(4,shoppingCartResponseModel.getCart_items().get(i).getFormatted_per_item_total());
            listOfProductDetails.add(5,shoppingCartResponseModel.getCart_items().get(i).getPrice());
            listOfProductDetails.add(6,Integer.toString(shoppingCartResponseModel.getCart_items().get(i).getMin_selling_price()));
            listOfProductDetails.add(7,Integer.toString(shoppingCartResponseModel.getCart_items().get(i).getMax_selling_price()));
            listOfProductDetails.add(8,Integer.toString(shoppingCartResponseModel.getCart_items().get(i).getQuantity()));
            listOfProductDetails.add(9,shoppingCartResponseModel.getCart_items().get(i).getSize());
            getContainerData.put(i,listOfProductDetails);
        }
        return getContainerData;
    }


    public List<ShoppingCartResponseModel.ShippedAddressesBean> addressesBeanList(){
        response = shopUpPostMan.getCall(EndPoints.SHOPPING_CART_JSON);
        ShoppingCartResponseModel shoppingCartResponseModel = gson.fromJson(response.getBody().asString(),ShoppingCartResponseModel.class);
        return shoppingCartResponseModel.getShipped_addresses();
    }


    public Map<Integer,List<String>> getAddressContainerDetailsMap(){
        Map<Integer,List<String>> getAddressContainerData = new HashMap<>();
        response = shopUpPostMan.getCall(EndPoints.SHOPPING_CART_JSON);
        ShoppingCartResponseModel shoppingCartResponseModel = gson.fromJson(response.getBody().asString(),ShoppingCartResponseModel.class);
        for (int i=0;i<shoppingCartResponseModel.getShipped_addresses().size();i++){
            List<String> listOfAddressDetails = new ArrayList<>();
            listOfAddressDetails.add(0,shoppingCartResponseModel.getShipped_addresses().get(i).getFirstname());
            listOfAddressDetails.add(1,shoppingCartResponseModel.getShipped_addresses().get(i).getAddress1());
            listOfAddressDetails.add(2,shoppingCartResponseModel.getShipped_addresses().get(i).getLandmark());
            listOfAddressDetails.add(3,shoppingCartResponseModel.getShipped_addresses().get(i).getCity());
            listOfAddressDetails.add(4,shoppingCartResponseModel.getShipped_addresses().get(i).getPhone());
            getAddressContainerData.put(i,listOfAddressDetails);
        }
        return getAddressContainerData;
    }


    public List<String> getSelectedAddressDetails() {
        List<String> addressDetails = new ArrayList<>();
        response = shopUpPostMan.getCall(EndPoints.SHOPPING_CART_JSON);
        ShoppingCartResponseModel shoppingCartResponseModel = gson.fromJson(response.getBody().asString(),ShoppingCartResponseModel.class);
        addressDetails.add(0,shoppingCartResponseModel.getOrder_address().getAddress().getFirstname());
        addressDetails.add(1,shoppingCartResponseModel.getOrder_address().getAddress().getAddress1());
        addressDetails.add(2,shoppingCartResponseModel.getOrder_address().getAddress().getLandmark());
        addressDetails.add(3,shoppingCartResponseModel.getOrder_address().getAddress().getCity());
        addressDetails.add(4,shoppingCartResponseModel.getOrder_address().getAddress().getPhone());
        return addressDetails;
    }


}
