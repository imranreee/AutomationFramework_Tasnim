package services.responseModels.wmsModels;

import java.io.Serializable;
import java.util.List;

public class AllSellersModel {

    /**
     * success : true
     * result : [{"id":13,"name":"10"},{"id":7,"name":"102"},{"id":8,"name":"103"},{"id":9,"name":"104"},{"id":10,"name":"106"},{"id":11,"name":"107"},{"id":12,"name":"108"},{"id":14,"name":"110"},{"id":15,"name":"114"},{"id":34,"name":"ARE"},{"id":38,"name":"ARL"},{"id":24,"name":"ART"},{"id":33,"name":"ASI"},{"id":20,"name":"CWD"},{"id":29,"name":"DFW"},{"id":2056,"name":"DMC"},{"id":30,"name":"FDC"},{"id":22,"name":"GBS"},{"id":4,"name":"Indian Fashionista"},{"id":28,"name":"IPC"},{"id":18,"name":"Migrated_Seller_"},{"id":32,"name":"Migrated_Seller_1234"},{"id":36,"name":"Migrated_Seller_12344"},{"id":35,"name":"Migrated_Seller_168"},{"id":27,"name":"Migrated_Seller_2049"},{"id":31,"name":"Migrated_Seller_287000132"},{"id":37,"name":"Migrated_Seller_287000412"},{"id":2057,"name":"Migrated_Seller_3"},{"id":25,"name":"MTB"},{"id":17,"name":"Seller Name"},{"id":6,"name":"Selva Muthu Kumar"},{"id":26,"name":"SHB"},{"id":3,"name":"Shishang"},{"id":19,"name":"SSJ"},{"id":16,"name":"STF"},{"id":2059,"name":"Unbranded"},{"id":21,"name":"VCN"},{"id":5,"name":"Voonik Technologies pvt. ltd"},{"id":23,"name":"WFB"}]
     */

    private boolean success;
    private List<ResultBean> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * id : 13
         * name : 10
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
