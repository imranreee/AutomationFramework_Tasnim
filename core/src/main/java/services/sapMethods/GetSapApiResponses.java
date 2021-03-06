package services.sapMethods;

import com.google.gson.Gson;
import dataParcer.CSVParser;
import io.restassured.RestAssured;
import io.restassured.response.*;
import org.json.simple.JSONObject;
import services.responseModels.sapModels.*;
import services.serviceUtils.*;
import java.util.*;

public class GetSapApiResponses {

    private ShopUpPostMan shopUpPostMan;
    private final String module;
    private Random random;
    private Gson gson;

    public GetSapApiResponses(String module)
    {
        this.module=module;
        shopUpPostMan = new ShopUpPostMan(module);
        random = new Random();
        gson = new Gson();
    }

    public ShopDetailsModel shopDetailsGetCall(int limit)
    {
        String shopDetailsGetCall = "https://sap.shopups1.xyz/api/shop/shops-details?limit=" + limit;
        Response shopDetailsResponse = shopUpPostMan.getCall(shopDetailsGetCall);
        ShopDetailsModel shopDetailsModel = gson.fromJson(shopDetailsResponse.getBody().asString(),ShopDetailsModel.class);
        return  shopDetailsModel;
    }

    public ShopNameListModel shopNameListGetCall()
    {
        String shopNameListGetCall = "https://sap.shopups1.xyz/api/shop/shops-name";
        Response  shopNameListResponse = shopUpPostMan.getCall(shopNameListGetCall);
        ShopNameListModel shopNameListModel = gson.fromJson(shopNameListResponse.getBody().asString(),ShopNameListModel.class);
        return shopNameListModel;
    }

    public ShopInfoModel shopInfoGetCall(int shopId)
    {
        String shopInfoGetCall = "https://sap.shopups1.xyz/api/shop/shops-details?shop=" + shopId;
        Response shopInfoResponse = shopUpPostMan.getCall(shopInfoGetCall);
        ShopInfoModel shopInfoModel = gson.fromJson(shopInfoResponse.getBody().asString(),ShopInfoModel.class);
        return shopInfoModel;
    }

    public ShopStoreInfoModel shopStoreInfoGetCall(int shopId)
    {
        String shopStoreInfoGetCall = "https://apiredx.shopups1.xyz/v1/logistics/shop-stores/" + shopId;
        Response shopStoreInfoResponse = shopUpPostMan.getCall(shopStoreInfoGetCall);
        ShopStoreInfoModel shopStoreInfoModel  = gson.fromJson(shopStoreInfoResponse.getBody().asString(),ShopStoreInfoModel.class);
        return  shopStoreInfoModel;
    }

    public AreaQueryModel areaQueryGetCall(String query)
    {
        String areaQueryGetCall = "https://sap.shopups1.xyz/api/area/search?q=" + query;
        Response areaQueryResponse = shopUpPostMan.getCall(areaQueryGetCall);
        AreaQueryModel areaQueryModel = gson.fromJson(areaQueryResponse.getBody().asString(),AreaQueryModel.class);
        return areaQueryModel;
    }

    /*----------Authentication----------*/

    public Response requestLoginCodePostCall()
    {
        String requestLoginCode = "https://shopups1.xyz/v1/user/request-login-code";
        Map requestLoginCodeBody = new HashMap();
        requestLoginCodeBody.put("countryCode","BD");
        requestLoginCodeBody.put("callingCode","+880");
        requestLoginCodeBody.put("phoneNumber","1401122188");

//        System.out.println("Request Login Code Body : " + requestLoginCodeBody.toString() + "\n);

        JSONObject request = new JSONObject(requestLoginCodeBody);
        Response response = RestAssured.given().header("Content-Type","application/json")
                .body(request.toJSONString()).when().post(requestLoginCode);
        return response;
    }

    public Response loginWithCodePostCall()
    {
        String loginWithCode ="https://shopups1.xyz/v1/user/login-with-code";
        Map loginWithCodeBody = new HashMap();
        loginWithCodeBody.put("loginCode","6666");
        loginWithCodeBody.put("phone","01401122188");

//        System.out.println("Login with Code Body : " + loginWithCodeBody.toString() + "\n);

        JSONObject request = new JSONObject(loginWithCodeBody);
        Response response = RestAssured.given().header("Content-Type","application/json")
                .body(request.toJSONString()).when().post(loginWithCode);
        return response;
    }

    /*----------Import Parcel----------*/

    public List<String> uploadFilePostCall(int shopId, String path)
    {
        System.out.println("\nUploading File");

        String uploadParcels ="https://shopups1.xyz/v2/admin/shop/" + shopId + "/logistics/parcels";

        Map uploadFileBody = new HashMap();
        List<HashMap<String, Object>> list = CSVParser.getHashListForDataPath(path);
        System.out.println("Number of Parcels Uploaded = " + list.size() + "\n");
        uploadFileBody.put("parcels",list);

//        System.out.println("Upload File Body : " + uploadFileBody.toString() + "\n);

        List<String> invoiceNumber = new ArrayList<String>();
        for(int i=0;i<list.size();i++)
        {
            invoiceNumber.add(list.get(i).get("INVOICE_NUMBER").toString());
        }

//        System.out.println("Invoice Number Size : " + invoiceNumber.size());
//        System.out.println(invoiceNumber.toString());

        Response response = shopUpPostMan.postCall(uploadParcels,uploadFileBody);
//        System.out.println("Upload File Response : "+ response.getBody().asString() + "\n");
        return invoiceNumber;
    }

    /*----------Pickup Module----------*/

    public PickupModel pickupGetCall(String hubId,String pickupAgentId,String status)
    {
        if(status.equalsIgnoreCase("all- statuses"))
            status="";
        String pickupGetCall = "https://shopups1.xyz/v1/admin/shop/999999999/pickup?hubId=" + hubId + "&pickupAgentId=" + pickupAgentId + "&status=" + status;
        Response pickupResponse = shopUpPostMan.getCall(pickupGetCall);
        PickupModel pickupModel = gson.fromJson(pickupResponse.getBody().asString(),PickupModel.class);
        return pickupModel;
    }

    public String getApiParameter(String value)
    {
        String[] split = value.split("(?=\\p{Upper})");
        String param ="";
        for(int i = 0;i<split.length;i++)
        {
            param += split[i].toLowerCase();
            if(i!=split.length-1)
                param+="-";
        }
        return param;
    }

    /*----------Create Parcel----------*/

    public CreateParcelModel createParcelPostCall(int shopId,Map createParcelBody)
    {
        String createParcelPostCall = "https://shopups1.xyz/v1/admin/shop/" + shopId + "/logistics/parcels";
        Response createParcelResponse = shopUpPostMan.postCall(createParcelPostCall, createParcelBody);
        System.out.println(createParcelResponse.asString());
        CreateParcelModel createParcelModel = gson.fromJson(createParcelResponse.getBody().asString(),CreateParcelModel.class);
        return createParcelModel;
    }

    /*----------Receive Hub Parcel----------*/

    public ReceiveParcelsListModel getParcelsReceiveHub(int shopId)
    {
        System.out.println("Getting Parcels List of shop " + shopId + " in Receive Module");
        String receiveParcelsPostCall = "https://sap.shopups1.xyz/api/logistics/parcels?filterOutProblematic=true&limit=5000&offset=0&page=1&parcelType=regular&shop=" + shopId + "&status=pickup-pending&status=pickup-in-progress&status=picked-up";
        Response receiveParcelsResponse = shopUpPostMan.getCall(receiveParcelsPostCall);
//        System.out.println(receiveParcelsResponse.getBody().asString());
        ReceiveParcelsListModel receiveParcelsModel = gson.fromJson(receiveParcelsResponse.getBody().asString(),ReceiveParcelsListModel.class);
        return receiveParcelsModel;
    }

    public ReceiveParcelsListModel getReceiveMotherHubParcels(int sourceHubId,int destinationHubId)
    {
        System.out.println("Getting Mother Hub Parcels List in Receive Module");
        String receiveParcelsPostCall = "https://sap.shopups1.xyz/api/logistics/parcels?destinationHubId=" + destinationHubId + "&filterOutProblematic=true&offset=0&page=1&sourceHubId=" + sourceHubId + "&status=hub-transfer";
        Response receiveParcelsResponse = shopUpPostMan.getCall(receiveParcelsPostCall);
//        System.out.println(receiveParcelsResponse.getBody().asString());
        ReceiveParcelsListModel receiveParcelsModel = gson.fromJson(receiveParcelsResponse.getBody().asString(),ReceiveParcelsListModel.class);
        return receiveParcelsModel;
    }

    public void receiveParcels(List<String> trackingIds,int sourceHubId,int currentPartnerId)
    {
        System.out.println("\nReceiving Parcels\n");
        String action = "received-from-seller";
        String dir = System.getProperty("user.dir");
        String filePath = dir + "/src/test/resources/testData/receiveParcelsData.csv";
//        List<HashMap<String, Object>> parcels = CSVParser.getHashListForDataPath(filePath);
        List<HashMap<String, Object>> parcels = new ArrayList<>();

        for(int i =0; i<trackingIds.size();i++)
        {
            HashMap<String, Object> parcel = new HashMap<>();
            parcel.put("id",trackingIds.get(i));
            parcel.put("currentStatus","ready-for-delivery");
            parcel.put("oldStatus","pickup-pending");
            parcel.put("sourceHubId",sourceHubId);
            parcel.put("currentPartnerId",currentPartnerId);
            parcels.add(parcel);
//            parcels.get(i).put("id",trackingIds.get(i));
        }
        bulkStatusPutCall(parcels,action);
    }

    /*----------Dispatch To Agent----------*/

    public void assignAgent(List<String> parcelIds,int agentId,String agentName,int hubId)
    {
        System.out.println("\nAssigning Agent to Parcels\n");
        String assignAgentPostCall = "https://shopups1.xyz/v1/logistics/auto-rider-assignment/common/assign-agents/" + hubId;
        Map data = new HashMap();
        data.put("id",agentId);
        data.put("name",agentName);
        data.put("parcels",parcelIds);
        Map assignAgentBody = new HashMap();
        assignAgentBody.put("type","assign");
        assignAgentBody.put("data",data);
        Response response = shopUpPostMan.postCall(assignAgentPostCall,assignAgentBody);
    }

    public void dispatchParcelsToAgent(List<String> parcelIds,int deliveryAgentId,int sourceHubId)
    {
        System.out.println("\nDispatching Parcels To Agent\n");
        String action = "dispatched-to-agent";

        List<HashMap<String, Object>> parcels = new ArrayList<>();

        for(int i =0; i<parcelIds.size();i++)
        {
            HashMap<String, Object> parcel = new HashMap<>();
            parcel.put("id",parcelIds.get(i));
            parcel.put("currentStatus","delivery-in-progress");
            parcel.put("oldStatus","ready-for-delivery");
            parcel.put("deliveryAgentId",deliveryAgentId);
            parcel.put("sourceHubId",sourceHubId);
            parcels.add(parcel);
//            parcels.get(i).put("id",parcelIds.get(i));
        }
        bulkStatusPutCall(parcels,action);
    }

    /*----------Bulk Status----------*/

    public void bulkStatusPutCall(List<HashMap<String, Object>> parcels, String action)
    {
        String bulkStatusUrl = "https://sap.shopups1.xyz/api/logistics/parcel/status/bulk";
        Map bulkStatusBody = new HashMap();
        bulkStatusBody.put("parcels",parcels);
        bulkStatusBody.put("action",action);
        bulkStatusBody.put("lineHaulId",null);
        bulkStatusBody.put("forceNewBulkTransferId",null);
        Response response = shopUpPostMan.putCall(bulkStatusUrl,bulkStatusBody);
//        System.out.println(response.getBody().asString());
    }

    /*----------Receive Problematic Parcels From Agents----------*/

    public ReceiveDeliveryAgentsParcelListModel deliveryAgentProblematicParcelsListGetCall(int deliveryAgentId)
    {
        String deliveryAgentsProblematicParcelsListGetCall = "https://sap.shopups1.xyz/api/logistics/parcels?deliveryAgentId=" + deliveryAgentId + "&filterOutProblematic=true&limit=5000&offset=0&page=1&phone=&status=delivery-in-progress&status=delivered&status=agent-returning&status=agent-hold-returning&status=agent-area-change";
        Response deliveryAgentsProblematicParcelsListResponse = shopUpPostMan.getCall(deliveryAgentsProblematicParcelsListGetCall);
        ReceiveDeliveryAgentsParcelListModel receiveDeliveryAgentsParcelListModel = gson.fromJson(deliveryAgentsProblematicParcelsListResponse.getBody().asString(),ReceiveDeliveryAgentsParcelListModel.class);
        return  receiveDeliveryAgentsParcelListModel;
    }

    public Response reasonsListGetCall(String category)
    {
        String reasonsListGetCall = "https://shopups1.xyz/v2/logistics/reasons?category=" + category + "&module=delivery";
        Response reasonsListResponse = shopUpPostMan.getCall(reasonsListGetCall);
        return reasonsListResponse;
    }

    public void parcelActionPutCall(String action,String trackingId, Map reasons)
    {
        Map parcelActionBody = new HashMap();
        String currentStatus = new String();
        String actionValue = new String();

        switch (action)
        {
            case "hold":
                currentStatus = "agent-hold-returning";
                actionValue = "user-hold-returning";
                Date currentDate = new Date();
                long dateTime = currentDate.getTime();
                long deliveryDate = dateTime + (24*60*60*1000);
                parcelActionBody.put("deliveryDate",deliveryDate);
                parcelActionBody.put("remarks",reasons.get("Remarks"));
                parcelActionBody.put("holdReason",reasons.get("Reason"));
                parcelActionBody.put("reasonId",reasons.get("Id"));
                break;
            case "return":
                currentStatus = "agent-returning";
                actionValue = "user-parcel-returning";
                parcelActionBody.put("remarks",reasons.get("Remarks"));
                parcelActionBody.put("returnReason",reasons.get("Reason"));
                parcelActionBody.put("reasonId",reasons.get("Id"));
                break;
            case "areaChange":
                currentStatus = "agent-area-change";
                actionValue = "user-area-change";
                parcelActionBody.put("deliveryAddress","Address, Area, Code");
                parcelActionBody.put("holdComment","Area changed to Kalabagan");
                break;
            case "delivered":
                currentStatus = "delivered";
                actionValue = "user-delivered";
                break;
        }

        parcelActionBody.put("currentStatus",currentStatus);
        parcelActionBody.put("oldStatus","delivery-in-progress");
        parcelActionBody.put("action",actionValue);
        statusWithRemarkPutCall(trackingId,parcelActionBody);
    }

    public void statusWithRemarkPutCall(String trackingId,Map body)
    {
        String statusWithRemarkPutCall = "https://sap.shopups1.xyz/api/logistics/parcel/status-with-remarks/" + trackingId;
        Response statusWithRemarksResponse = shopUpPostMan.putCall(statusWithRemarkPutCall,body);
    }

    public ReconciliationParcelListModel reconciliationParcelListGetCall(List<String> trackingIds)
    {
        String trackingId = trackingIds.toString().replace(", ",",");
        String reconciliationParcelListGetCall = "https://shopups1.xyz/v3/logistics/reconciliation/cash/parcels?trackingIds=" + trackingId.substring(1,trackingId.length()-1);
        Response reconciliationParcelListResponse = shopUpPostMan.getCall(reconciliationParcelListGetCall);
        System.out.println(reconciliationParcelListResponse.getBody().asString());
        ReconciliationParcelListModel reconciliationParcelListModel = gson.fromJson(reconciliationParcelListResponse.getBody().asString(),ReconciliationParcelListModel.class);
        return reconciliationParcelListModel;
    }

    /*--------------------Pickup--------------------*/

    public Response pickupAgentPutCall(int agentId, int shopId, int storeId)
    {
        String pickupAgentPutCall = "https://shopups1.xyz/v1/admin/shop/" + shopId + "/pickup/agent";
        Map pickupAgentBody = new HashMap();
        pickupAgentBody.put("agentId",agentId);
        pickupAgentBody.put("shopId",shopId);
        pickupAgentBody.put("shopStoreId",storeId);
        Response pickupAgentResponse = shopUpPostMan.putCall(pickupAgentPutCall,pickupAgentBody);
        return pickupAgentResponse;
    }

    /*--------------------Area Management--------------------*/

    public DistrictListModel districtListGetCall()
    {
        String districtListGetCall = "https://sap.shopups1.xyz/api/districts";
        Response districtListResponse = shopUpPostMan.getCall(districtListGetCall);
        DistrictListModel districtListModel = gson.fromJson(districtListResponse.getBody().asString(),DistrictListModel.class);
        return districtListModel;
    }

    public HubListModel hubListGetCall()
    {
        String hubListGetCall = "https://sap.shopups1.xyz/api/hubs";
        Response hubListResponse = shopUpPostMan.getCall(hubListGetCall);
        HubListModel hubListModel = gson.fromJson(hubListResponse.getBody().asString(),HubListModel.class);
        return hubListModel;
    }

    public HubListModel hubListGetCall(int currentHubId,String type)
    {
        String hubListGetCall = "https://shopups1.xyz/v4/logistics/hubs?currentHubId=" + currentHubId + "&type=" + type;
        Response hubListResponse = shopUpPostMan.getCall(hubListGetCall);
        HubListModel hubListModel = gson.fromJson(hubListResponse.getBody().asString(),HubListModel.class);
        return hubListModel;
    }

    public AreaHubModel areaHubListGetCall(int districtId)
    {
        String areaHubListGetCall = "https://sap.shopups1.xyz/api/districts/" + districtId + "/area-hub";
        Response areaHubListResponse = shopUpPostMan.getCall(areaHubListGetCall);
        System.out.println(areaHubListResponse.asString());
        AreaHubModel areaHubModel = gson.fromJson(areaHubListResponse.getBody().asString(),AreaHubModel.class);
        return areaHubModel;
    }

    /*--------------------Agents--------------------*/

    public AgentControlListModel agentControlListGetCall(int hubId)
    {
        String agentControlListGetCall = "https://sap.shopups1.xyz/api/user/agent?hubId=" + hubId + "&limit=999999&status=active";
        Response agentControlListResponse = shopUpPostMan.getCall(agentControlListGetCall);
        AgentControlListModel agentControlListModel = gson.fromJson(agentControlListResponse.getBody().asString(),AgentControlListModel.class);
        return agentControlListModel;
    }

    public AgentsListModel agentListGetCall(int hubId)
    {
        String agentListGetCall = "https://sap.shopups1.xyz/api/logistics/agent?hubId=" + hubId;
        Response agentListResponse = shopUpPostMan.getCall(agentListGetCall);
        AgentsListModel agentsListModel = gson.fromJson(agentListResponse.getBody().asString(),AgentsListModel.class);
        return agentsListModel;
    }
    /*----------Parcel Received ----------*/

    public void cashReceive(int hubId,List<String>trackingIds){
       //String url = "https://shopups1.xyz/v2/logistics/bulk-status-updates";

        String action = "received-problematic-parcel";

        List<HashMap<String, Object>> parcels = new ArrayList<>();

        for(int i =0; i<trackingIds.size();i++)
        {
            HashMap<String, Object> parcel = new HashMap<>();
            parcel.put("id",trackingIds.get(i));
            parcel.put("currentStatus","shopup-returning");
            parcel.put("oldStatus","agent-returning");
            //parcel.put("sourceHubId",(reasonId.get(i)));
            parcel.put("sourceHubId",hubId);
            parcels.add(parcel);
           //System.out.println(trackingIds.get(i)+"from api page"+reasonId.get(i));
//            parcels.get(i).put("id",parcelIds.get(i));
        }
        cashReceivePutCall(parcels,action);


    }
   public void cashReceivePutCall(List<HashMap<String, Object>> parcels, String action){
       String bulkStatusUrl = "https://shopups1.xyz/v2/logistics/bulk-status-updates";
       Map bulkStatusBody = new HashMap();
       bulkStatusBody.put("parcels",parcels);
       bulkStatusBody.put("action",action);
       bulkStatusBody.put("lineHaulId",null);
       bulkStatusBody.put("forceNewBulkTransferId",null);
       Response response = shopUpPostMan.putCall(bulkStatusUrl,bulkStatusBody);
       System.out.println("Parcel Receive api "+bulkStatusBody);


   }
   public void dispatchProblematicPracelAssigntoAgent(List<String> trackingIds,int pickupAgentId){
     String assignUrl =  "https://sap.shopups1.xyz/api/logistics/parcel/assign-delivery/"+pickupAgentId;
       Map data = new HashMap();
       data.put("parcels",trackingIds);
       Response response = shopUpPostMan.putCall(assignUrl,data);
       System.out.println(response.getStatusCode());
       System.out.println(response.getStatusCode());

   }


   public void returnProblematicParcelDispatch(int hubId,List<String>trackingIds,int currentPartnerId,int picKupagentId){

       String action = "return-parcel-dispatched-to-agent";

       List<HashMap<String, Object>> parcels = new ArrayList<>();

       for(int i =0; i<trackingIds.size();i++)

       {

           HashMap<String, Object> parcel = new HashMap<>();
           parcel.put("id",trackingIds.get(i));
           parcel.put("currentStatus","return-in-progress");
           parcel.put("oldStatus","shopup-returning");
           parcel.put("deliveryAgentId",picKupagentId);
           parcel.put("sourceHubId",hubId);
           parcel.put("currentPartnerId",currentPartnerId);
           parcels.add(parcel);

       }
       problematicParcelDispatchPutApi(action,parcels);
   }

   public void problematicParcelDispatchPutApi(String action,List<HashMap<String, Object>> parcels){
       String bulkStatusUrl = "https://shopups1.xyz/v2/logistics/bulk-status-updates";
       Map bulkStatusBody = new HashMap();
       bulkStatusBody.put("parcels",parcels);
       bulkStatusBody.put("action",action);
       bulkStatusBody.put("lineHaulId",null);
       bulkStatusBody.put("forceNewBulkTransferId",null);

       Response response = shopUpPostMan.putCall(bulkStatusUrl,bulkStatusBody);
       System.out.println(response.getStatusCode());
       System.out.println(response.getBody());

   }


    public ReceiveDeliveryAgentsParcelListModel pickupAgentProblematicParcelsListGetCall(int pickupAgentId,int hubId)
    {
        String deliveryAgentsProblematicParcelsListGetCall = "https://sap.shopups1.xyz/api/logistics/parcels?deliveryAgentId="+pickupAgentId+"&filterOutProblematic=true&limit=8000&offset=0&page=1&phone=&pickUpHubId="+hubId+"&status=return-in-progress&status=return-hold-returning&status=return-problematic-returning&status=agent-returned";
        Response deliveryAgentsProblematicParcelsListResponse = shopUpPostMan.getCall(deliveryAgentsProblematicParcelsListGetCall);
        ReceiveDeliveryAgentsParcelListModel receiveDeliveryAgentsParcelListModel = gson.fromJson(deliveryAgentsProblematicParcelsListResponse.getBody().asString(),ReceiveDeliveryAgentsParcelListModel.class);
        return  receiveDeliveryAgentsParcelListModel;
    }


  public void receivedParcelToSeller(List<String>trackingIds,int currentPartnerId,int sourceHubId){
        String action = "parcel-returned-to-seller";
         List<HashMap<String, Object>> parcels = new ArrayList<>();
         for(int i =0; i<trackingIds.size();i++) {
          HashMap<String, Object> parcel = new HashMap<>();
          parcel.put("id", trackingIds.get(i));
          parcel.put("currentStatus", "agent-returned");
          parcel.put("currentPartnerId", currentPartnerId);
          parcel.put("oldStatus", "return-in-progress");
          parcel.put("sourceHubId", sourceHubId);
          parcels.add(parcel);
      }
        receivedParcelToSellerPutAPi(action,parcels);


  }
  public void receivedParcelToSellerPutAPi(String action,List<HashMap<String, Object>> parcels){
   String bulkStatusUrl = "https://shopups1.xyz/v2/logistics/bulk-status-updates";
      Map bulkStatusBody = new HashMap();
      bulkStatusBody.put("parcels",parcels);
      bulkStatusBody.put("action",action);
      Response response = shopUpPostMan.putCall(bulkStatusUrl,bulkStatusBody);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody().asString());

  }




    /*----------Functions----------*/

    public String performAuthentication()
    {
        String cookie;
        Response loginCodePostCall = requestLoginCodePostCall();
        //        System.out.println("Request Code Response : " + loginCodePostCall.getBody().asString()  + "\n);
        Response loginWithCodePostCall = loginWithCodePostCall();
        //        System.out.println("Login Response : " + loginWithCodePostCall.getBody().asString() + "\n);
        cookie = loginWithCodePostCall.header("Set-Cookie");
        //        System.out.println(cookie + "\n);
        int index = cookie.indexOf(";");
        return cookie.substring(0,index);
    }

    public List<String> getTrackingIds(ReceiveParcelsListModel receiveParcelsModel, List<String> invoiceNumbers)
    {
        List<String> trackingIds = new ArrayList<>();
        int parcelSize;
        int i,j;

        parcelSize = receiveParcelsModel.getParcels().size();
        System.out.println("Parcels : " + parcelSize);

        for(i=0;i<invoiceNumbers.size();i++)
        {
            for(j=0;j<parcelSize;j++)
            {
                if(receiveParcelsModel.getParcels().get(j).getInvoiceNumber().equalsIgnoreCase(invoiceNumbers.get(i)))
                {
                    trackingIds.add(receiveParcelsModel.getParcels().get(j).getId());
                }
            }
//            System.out.println("Invoice Number : " + invoiceNumbers.get(i) + " has Tracking Id " + trackingIds.get(i));
        }

        return trackingIds;
    }

    public List<Integer> getParcelIds(ReceiveParcelsListModel receiveParcelsModel, List<String> invoiceNumbers)
    {
        List<Integer> parcelIds = new ArrayList<>();
        int parcelSize;
        int i,j;

        parcelSize = receiveParcelsModel.getParcels().size();
//        System.out.println("Parcels : " + parcelSize);

        for(i=0;i<invoiceNumbers.size();i++)
        {
            for(j=0;j<parcelSize;j++)
            {
                if(receiveParcelsModel.getParcels().get(j).getInvoiceNumber().equalsIgnoreCase(invoiceNumbers.get(i)))
                {
                    parcelIds.add(receiveParcelsModel.getParcels().get(j).getParcel_id());
                }
            }
//            System.out.println("Invoice Number : " + invoiceNumbers.get(i) + " has Parcel Id " + parcelIds.get(i));
        }

        return parcelIds;
    }

    public Map getRandomReason(String action)
    {
        Map reasonMap = new HashMap();
        int reasonCategoriesSize,reasonCategoryIndex,reasonsSize,reasonIndex;
        ParcelReasonsModel parcelReasonsModel;

        Response reasonsListResponse = reasonsListGetCall(action);
        parcelReasonsModel = gson.fromJson(reasonsListResponse.getBody().asString(), ParcelReasonsModel.class);

        reasonCategoriesSize = parcelReasonsModel.getReasons().size();
        reasonCategoryIndex = random.nextInt(reasonCategoriesSize);
        reasonsSize = parcelReasonsModel.getReasons().get(reasonCategoryIndex).getREASONS().size();
        reasonIndex = random.nextInt(reasonsSize);

        reasonMap.put("Group", parcelReasonsModel.getReasons().get(reasonCategoryIndex).getGROUP());
        reasonMap.put("Remarks", parcelReasonsModel.getReasons().get(reasonCategoryIndex).getREASONS().get(reasonIndex).getREASON_BN());
        reasonMap.put("Reason", parcelReasonsModel.getReasons().get(reasonCategoryIndex).getREASONS().get(reasonIndex).getREASON_EN());
        reasonMap.put("Id",String.valueOf(parcelReasonsModel.getReasons().get(reasonCategoryIndex).getREASONS().get(reasonIndex).getREASON_ID()));

        return reasonMap;
    }

    public List<Map> getReconciliationStatuses(List<String> trackingIds)
    {
        List<Map> parcelActions = new ArrayList<>();
        ReconciliationParcelListModel reconciliationParcelListModel = reconciliationParcelListGetCall(trackingIds);
        int parcelSize;
        int i,j;

        parcelSize = reconciliationParcelListModel.getData().size();
        System.out.println("Parcels : " + parcelSize);

        for(i=0;i<trackingIds.size();i++)
        {
            for(j=0;j<parcelSize;j++)
            {
                if(reconciliationParcelListModel.getData().get(j).getTrackingId().equalsIgnoreCase(trackingIds.get(i)))
                {
                    Map parcels = new HashMap();
                    parcels.put("TRACKING_ID",reconciliationParcelListModel.getData().get(j).getTrackingId());
                    parcels.put("PARCEL_ID",reconciliationParcelListModel.getData().get(j).getParcelId());
                    parcels.put("CLOSING_STATUS",reconciliationParcelListModel.getData().get(j).getClosingStatus());
                    parcels.put("CLOSING_TIME",reconciliationParcelListModel.getData().get(j).getClosingTime());
                    parcelActions.add(parcels);
                }
            }
        }

        return parcelActions;
    }

    public Map getRandomHub()
    {
        HubListModel hubListModel = hubListGetCall();
        int size = hubListModel.getHubs().size();
        int index = random.nextInt(size);
        int hubId = hubListModel.getHubs().get(index).getId();
        String hubName = hubListModel.getHubs().get(index).getHubName();
        System.out.println("Hub Name : " + hubName);
        Map map = new HashMap();
        map.put("id",hubId);
        map.put("name",hubName);
        return  map;
    }

    public Map getRandomHub(int currentHubId)
    {
        String type = new String();
        switch (currentHubId) {
            case 7 :
                type = "sub_hubs";
                break;
            case 8 :
                type = "hubs";
        }
        HubListModel hubListModel = hubListGetCall(currentHubId,type);
        int size = hubListModel.getBody().size();
        int index = random.nextInt(size);
        int hubId = hubListModel.getBody().get(index).getID();
        String hubName = hubListModel.getBody().get(index).getHUB_NAME();
        System.out.println("Hub Name : " + hubName);
        Map map = new HashMap();
        map.put("id",hubId);
        map.put("name",hubName);
        return  map;
    }

    public String getHubName(int hubId)
    {
        System.out.println("Hub Id : " + hubId);
        HubListModel hubListModel = hubListGetCall();
        String hubName = null;
        int i,size = hubListModel.getHubs().size();
        for(i=0;i<size;i++)
        {
            if(hubListModel.getHubs().get(i).getId()==hubId)
            {
                hubName = hubListModel.getHubs().get(i).getHubName();
            }

        }
        System.out.println("Hub Name : " + hubName);
        return hubName;
    }

    public int getHubId(String hubName)
    {
        System.out.println("Hub Name : " + hubName);
        HubListModel hubListModel = hubListGetCall();
        int i,size,hubId=0;
        size = hubListModel.getHubs().size();
        for(i=0;i<size;i++)
        {
            if(hubListModel.getHubs().get(i).getHubName().equalsIgnoreCase(hubName))
            {
                hubId = hubListModel.getHubs().get(i).getId();
            }

        }
        System.out.println("Hub Id : " + hubId);
        return hubId;
    }

    public Map getRandomAgent(int hubId)
    {
        AgentsListModel agentsListModel = agentListGetCall(hubId);
        int size = agentsListModel.getAgents().size();
        if(size!=0)
        {
            int index = random.nextInt(size);
            int agentId = agentsListModel.getAgents().get(index).getId();
            String agentName = agentsListModel.getAgents().get(index).getName();
            System.out.println("Agent Name : " + agentName);
            Map map = new HashMap();
            map.put("id",agentId);
            map.put("name",agentName);
            return  map;
        } else return null;
    }

    public Map getRandomAgent(int hubId,String type)
    {
        AgentsListModel agentsListModel = agentListGetCall(hubId);
        int count = 0;
        int index,size = agentsListModel.getAgents().size();
        if(size!=0)
        {
            while (true){
                index = random.nextInt(size);
                count++;
                if(agentsListModel.getAgents().get(index).getAgentType().equalsIgnoreCase(type))
                    break;
                if(count>size){System.out.println("Loop Count Greater than Size"); return null;}
            }
            int agentId = agentsListModel.getAgents().get(index).getId();
            String agentName = agentsListModel.getAgents().get(index).getName();
            System.out.println("Agent Name : " + agentName);
            System.out.println("Agent Type : " + agentsListModel.getAgents().get(index).getAgentType());
            Map map = new HashMap();
            map.put("id",agentId);
            map.put("name",agentName);
            return  map;
        } else return null;
    }

    public Map getRandomAgentAndShop(int hubId,String agentType)
    {
        AgentControlListModel agentControlListModel = agentControlListGetCall(hubId);
        Map details = new HashMap();
        int agentId,shopId;
        String agentName,shopName;
        int agents = agentControlListModel.getAgents().size();
        for(int i =0;i<agents;i++)
        {
            if(agentControlListModel.getAgents().get(i).getAgentType().equalsIgnoreCase(agentType))
            {
                agentId = agentControlListModel.getAgents().get(i).getId();
                agentName = agentControlListModel.getAgents().get(i).getName();
                shopId = agentControlListModel.getAgents().get(i).getShopId();
                shopName = agentControlListModel.getAgents().get(i).getShopName();
                details.put("agentId",agentId);
                details.put("agentName",agentName);
                details.put("shopId",shopId);
                details.put("shopName",shopName);
                System.out.println(details.toString());
                if(shopId==0)
                    continue;
                else break;
            }
        }
        return details;
    }



    public int getAgentId(String hubName, String agentName)
    {
        int hubId = getHubId(hubName);
        AgentsListModel agentsListModel = agentListGetCall(hubId);
        int i,size,agentId=0;
        size = agentsListModel.getAgents().size();
        for(i=0;i<size;i++)
        {
            if(agentsListModel.getAgents().get(i).getName().equalsIgnoreCase(agentName))
            {
                agentId = agentsListModel.getAgents().get(i).getId();
            }

        }
        System.out.println("Agent Id : " + agentId);
        return agentId;
    }

    public int getHubId(int districtId,int areaId)
    {
        AreaHubModel areaHubModel = areaHubListGetCall(districtId);
        int areas = areaHubModel.getAreas().size();
        int hubId = 0;
        for(int i=0;i<areas;i++)
        {
            if(areaHubModel.getAreas().get(i).getAreaId()==areaId)
                hubId = areaHubModel.getAreas().get(i).getHubId();
        }
        return hubId;
    }

    public int getPickupIndex(int shopId,PickupModel pickupModel)
    {
        int i,size = pickupModel.getShops().size();
        for(i=0;i<size;i++)
        {
            if(pickupModel.getShops().get(i).getSHOP_ID()==shopId)
                return i;
        }
        return -1;
    }

    public Map getRandomShop()
    {
        ShopNameListModel shopNameListModel = shopNameListGetCall();
        int size = shopNameListModel.getCount();
        int index = random.nextInt(size);
        int shopId = shopNameListModel.getShops().get(index).getId();
        String shopName = shopNameListModel.getShops().get(index).getName();
        System.out.println("Shop Name : " + shopName);
        System.out.println("Shop Id : " + shopId);
        Map map = new HashMap();
        map.put("id",shopId);
        map.put("name",shopName);
        return  map;
    }

    public int getShopId(String shopName)
    {
        System.out.println("Getting Shop Id of : " + shopName);
        ShopNameListModel shopNameListModel = shopNameListGetCall();
        int shopSize = shopNameListModel.getShops().size();
        System.out.println("Shop Size : " + shopSize);
        int shopId = 0;
        for(int i=0;i<shopSize;i++)
        {
            if(shopNameListModel.getShops().get(i).getName().equalsIgnoreCase(shopName)) {
                shopId = shopNameListModel.getShops().get(i).getId();
                System.out.println("Shop Id : " + shopId);
                break;
            }
        }
        return shopId;
    }

    public Map getStore(int shopId,String area)
    {
        Map storeDetails = new HashMap();
        ShopStoreInfoModel shopStoreInfoModel = shopStoreInfoGetCall(shopId);
        int size = shopStoreInfoModel.getBody().size();
        for(int i=0;i<size;i++)
        {
            if (shopStoreInfoModel.getBody().get(i).getAREA_NAME().equalsIgnoreCase(area)) {
                storeDetails.put("id",shopStoreInfoModel.getBody().get(i).getID());
                storeDetails.put("name",shopStoreInfoModel.getBody().get(i).getNAME());
            }
        }
        return storeDetails;
    }

    public Map getRandomStore(int shopId)
    {
        ShopStoreInfoModel shopStoreInfoModel = shopStoreInfoGetCall(shopId);
        int size = shopStoreInfoModel.getBody().size();
        int index= random.nextInt(size);
        int storeId = shopStoreInfoModel.getBody().get(index).getID();
        String storeName = shopStoreInfoModel.getBody().get(index).getNAME();
        Map map = new HashMap();
        map.put("id",storeId);
        map.put("name",storeName);
        return  map;
    }

    public Map getRandomStoreWithArea(int shopId)
    {
        ShopStoreInfoModel shopStoreInfoModel = shopStoreInfoGetCall(shopId);
        int size = shopStoreInfoModel.getBody().size();
        int index= random.nextInt(size);
        Map map = new HashMap();
        map.put("storeId",shopStoreInfoModel.getBody().get(index).getID());
        map.put("storeName",shopStoreInfoModel.getBody().get(index).getNAME());
        map.put("areaId",shopStoreInfoModel.getBody().get(index).getAREA_ID());
        map.put("areaName",shopStoreInfoModel.getBody().get(index).getAREA_NAME());
        return  map;
    }

    public int getRandomDistrict()
    {
        DistrictListModel districtListModel= districtListGetCall();
        int size = districtListModel.getDistricts().size();
        int index = random.nextInt(size);
        int districtId = districtListModel.getDistricts().get(index).getId();
        System.out.println("District Id : " + districtId);
        return districtId;
    }

    public Map getHubDetails(int areaId)
    {
        int hubId = 0;
        String hubName = null;
        Map hubDetails = new HashMap();
        for(int i=1;i<65;i++) {
            AreaHubModel areaHubModel = areaHubListGetCall(i);
            int areas = areaHubModel.getAreas().size();
            for(int j=0;j<areas;j++) {
                if(areaHubModel.getAreas().get(j).getAreaId()==areaId) {
                    hubId = areaHubModel.getAreas().get(j).getHubId();
                    break;
                }
            }
            if(hubId!=0) {
                hubName = getHubName(hubId);
                break;
            }
        }
        hubDetails.put("id",hubId);
        hubDetails.put("name",hubName);
        return hubDetails;
    }

    public Map getRandomArea()
    {
        Map areaDetails = new HashMap();
        AreaQueryModel areaQueryModel = areaQueryGetCall("");
        int areas = areaQueryModel.getAreas().size();
        int area = random.nextInt(areas);
        areaDetails.put("areaId",areaQueryModel.getAreas().get(area).getId());
        areaDetails.put("areaName",areaQueryModel.getAreas().get(area).getName());
        System.out.println(Arrays.asList(areaDetails));
        return areaDetails;
    }

    public String getBulkTransferId(String trackingId,int sourceHubId, int destinationId)
    {
        ReceiveParcelsListModel receiveParcelsListModel = getReceiveMotherHubParcels(sourceHubId,destinationId);
        int parcels =receiveParcelsListModel.getParcels().size();
        for(int i=0; i<parcels;i++)
            if(receiveParcelsListModel.getParcels().get(i).getId().equalsIgnoreCase(trackingId))
                return (String) receiveParcelsListModel.getParcels().get(i).getBulkTransferId();
            return null;
    }
}
