package services.responseModels.commerceModels;

import java.util.List;

public class ProductListingResultsModel {


    private int products_count;

    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public int getProducts_count() {
        return products_count;
    }

    public void setProducts_count(int products_count) {
        this.products_count = products_count;
    }

    public static class ResultsBean {
        /**
         * activity_object_id : 38364562
         * brand : Shopup
         * category : Men shirt
         * category_id : 1118
         * coupon_offer : {}
         * description : "Product details of Casual Shirt For Men
         Product Type: Shirt
         Color: Blue
         Main Material: Cotton
         Stylish and fashionable
         Gender: Men
         Men's Long Sleeves Casual Shirt
         Long sleeves casual shirts is a cloth garment for the upper body. It is normally associated with long sleeves, a round neckline with collar. Shirts are generally made of a light, great quality fabric, and are easy to clean. Shirts with convertible long sleeves means you can roll up your sleeves when the weather gets warm and roll them back down again as the night progresses.


         Sizes:
         M=Length-28, Chest- 38,
         L=Length-29, Chest-40,
         XL= length-30, Chest- 42


         Lowest & Cheapest wholesale prices
         ---
         Return Policy:
         ডেলিভারি ম্যান থাকাকালীন প্রোডাক্ট চেক করে, ডেলিভারী ম্যানকে টাকা দিবেন।
         ডেলিভারি ম্যান চলে আসার পর কোনো অভিযোগ গ্রহণ করা হবে না, কোনো প্রোডাক্ট চেঞ্জ অথবা রিটার্ন হবে না। কোনো সমস্যা থাকলে ডেলিভারি ম্যান থাকাকালীন আমাদের কল দিবেন।
         ---
         Exchange Policy:
         আমাদের এক্সচেঞ্জ সুবিধাও আছে তবে তা ২৪ ঘন্টার মধ্যে ক্লেইম করতে হবে। এক্সচেঞ্জের সময় ডেলিভারি ম্যানের কাছে নিচের জিনিস গুলো অবশ্যই সঠিক ভাবে বুঝিয়ে দিতে হবে-
         ১। প্রোডাক্ট
         ২। প্রোডাক্টের সাথে প্রদানকৃত বক্স/প্যাকেজিং সামগ্রী
         ৩। বিল/ইনভয়েস
         ---
         Disclaimer
         Product color may slightly vary due to photographic lighting sources or your monitor settings.
         * discount : 8
         * has_multi_factor_sizes : false
         * id : 65288
         * image : //shopimages.vstores.io/88256/1569929604016-34-product.jpg?1580192805
         * image_height : 431
         * image_width : 430
         * images : [{"original":"//shopimages.vstores.io/88256/1569929604016-34-original.jpg?1580192805","product":"//shopimages.vstores.io/88256/1569929604016-34-product.jpg?1580192805","small":"//shopimages.vstores.io/88256/1569929604016-34-small.jpg?1580192805"}]
         * images_small : ["//shopimages.vstores.io/88256/1569929604016-34-small.jpg?1580192805"]
         * is_accessory : false
         * like_count : 0
         * liked : false
         * look : false
         * master_product_id : 65288
         * name : Casual Shirt For Men Blue - UPF
         * original_image : //shopimages.vstores.io/88256/1569929604016-34-original.jpg?1580192805
         * original_price : Tk. 384
         * per_piece_price : Tk. 355
         * permalink : casual-shirt-for-men-blue-upf-06ce2087
         * price : Tk. 355
         * primo_price :
         * product_id : 65288
         * product_stamp : EXPRESS DELIVERY
         * product_stock : 45
         * product_tags : [{"color":"#eb354b","tag":"EXPRESS DELIVERY"}]
         * purchases_count : 0
         * quality_verified : false
         * returns_count : 0
         * seal_of_trust : false
         * seller : null
         * seller_rating : 0.0
         * seo_title : Casual Shirt For Men Blue - UPF - Shopup Men shirt
         * sizes : [{"corrected_count_on_hand":0,"count_on_hand":0,"id":82572,"max_selling_price":667,"min_order_quantity":1,"min_selling_price":423,"name":"M","option_value_name":"M","original_price":384,"per_piece_price":355,"price":355,"product_id":65288,"quantity_per_variant":1,"size":11,"size_description":" "},{"corrected_count_on_hand":22,"count_on_hand":22,"id":82574,"max_selling_price":667,"min_order_quantity":1,"min_selling_price":423,"name":"L","option_value_name":"L","original_price":384,"per_piece_price":355,"price":355,"product_id":65288,"quantity_per_variant":1,"size":11,"size_description":" "},{"corrected_count_on_hand":23,"count_on_hand":23,"id":82575,"max_selling_price":667,"min_order_quantity":1,"min_selling_price":423,"name":"XL","option_value_name":"XL","original_price":384,"per_piece_price":355,"price":355,"product_id":65288,"quantity_per_variant":1,"size":11,"size_description":" "}]
         * slug : casual-shirt-for-men-blue-upf-shopup-bea40e57
         * small_image_url : //shopimages.vstores.io/88256/1569929604016-34-small.jpg?1580192805
         * sponsored_highlight : false
         * stylist : Voonik Support
         * title : Casual Shirt For Men Blue - UPF
         * video : []
         * welcome_text : Just for you ,New picks in your style, Your personal feed
         */

        private String activity_object_id;
        private String brand;
        private String category;
        private int category_id;
        private CouponOfferBean coupon_offer;
        private String description;
        private float discount;
        private boolean has_multi_factor_sizes;
        private int id;
        private String image;
        private int image_height;
        private int image_width;
        private boolean is_accessory;
        private int like_count;
        private boolean liked;
        private boolean look;
        private int master_product_id;
        private String name;
        private String original_image;
        private String original_price;
        private String per_piece_price;
        private String permalink;
        private String price;
        private String primo_price;
        private int product_id;
        private String product_stamp;
        private int product_stock;
        private int purchases_count;
        private boolean quality_verified;
        private int returns_count;
        private boolean seal_of_trust;
        private Object seller;
        private double seller_rating;
        private String seo_title;
        private String slug;
        private String small_image_url;
        private boolean sponsored_highlight;
        private String stylist;
        private String title;
        private String welcome_text;
        private List<ImagesBean> images;
        private List<String> images_small;
        private List<ProductTagsBean> product_tags;
        private List<SizesBean> sizes;
        private List<?> video;

        public int getPlp_view_type() {
            return plp_view_type;
        }

        public void setPlp_view_type(int plp_view_type) {
            this.plp_view_type = plp_view_type;
        }

        private int plp_view_type;

        public String getActivity_object_id() {
            return activity_object_id;
        }

        public void setActivity_object_id(String activity_object_id) {
            this.activity_object_id = activity_object_id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public CouponOfferBean getCoupon_offer() {
            return coupon_offer;
        }

        public void setCoupon_offer(CouponOfferBean coupon_offer) {
            this.coupon_offer = coupon_offer;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public float getDiscount() {
            return discount;
        }

        public void setDiscount(float discount) {
            this.discount = discount;
        }

        public boolean isHas_multi_factor_sizes() {
            return has_multi_factor_sizes;
        }

        public void setHas_multi_factor_sizes(boolean has_multi_factor_sizes) {
            this.has_multi_factor_sizes = has_multi_factor_sizes;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getImage_height() {
            return image_height;
        }

        public void setImage_height(int image_height) {
            this.image_height = image_height;
        }

        public int getImage_width() {
            return image_width;
        }

        public void setImage_width(int image_width) {
            this.image_width = image_width;
        }

        public boolean isIs_accessory() {
            return is_accessory;
        }

        public void setIs_accessory(boolean is_accessory) {
            this.is_accessory = is_accessory;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }

        public boolean isLook() {
            return look;
        }

        public void setLook(boolean look) {
            this.look = look;
        }

        public int getMaster_product_id() {
            return master_product_id;
        }

        public void setMaster_product_id(int master_product_id) {
            this.master_product_id = master_product_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginal_image() {
            return original_image;
        }

        public void setOriginal_image(String original_image) {
            this.original_image = original_image;
        }

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public String getPer_piece_price() {
            return per_piece_price;
        }

        public void setPer_piece_price(String per_piece_price) {
            this.per_piece_price = per_piece_price;
        }

        public String getPermalink() {
            return permalink;
        }

        public void setPermalink(String permalink) {
            this.permalink = permalink;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrimo_price() {
            return primo_price;
        }

        public void setPrimo_price(String primo_price) {
            this.primo_price = primo_price;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getProduct_stamp() {
            return product_stamp;
        }

        public void setProduct_stamp(String product_stamp) {
            this.product_stamp = product_stamp;
        }

        public int getProduct_stock() {
            return product_stock;
        }

        public void setProduct_stock(int product_stock) {
            this.product_stock = product_stock;
        }

        public int getPurchases_count() {
            return purchases_count;
        }

        public void setPurchases_count(int purchases_count) {
            this.purchases_count = purchases_count;
        }

        public boolean isQuality_verified() {
            return quality_verified;
        }

        public void setQuality_verified(boolean quality_verified) {
            this.quality_verified = quality_verified;
        }

        public int getReturns_count() {
            return returns_count;
        }

        public void setReturns_count(int returns_count) {
            this.returns_count = returns_count;
        }

        public boolean isSeal_of_trust() {
            return seal_of_trust;
        }

        public void setSeal_of_trust(boolean seal_of_trust) {
            this.seal_of_trust = seal_of_trust;
        }

        public Object getSeller() {
            return seller;
        }

        public void setSeller(Object seller) {
            this.seller = seller;
        }

        public double getSeller_rating() {
            return seller_rating;
        }

        public void setSeller_rating(double seller_rating) {
            this.seller_rating = seller_rating;
        }

        public String getSeo_title() {
            return seo_title;
        }

        public void setSeo_title(String seo_title) {
            this.seo_title = seo_title;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getSmall_image_url() {
            return small_image_url;
        }

        public void setSmall_image_url(String small_image_url) {
            this.small_image_url = small_image_url;
        }

        public boolean isSponsored_highlight() {
            return sponsored_highlight;
        }

        public void setSponsored_highlight(boolean sponsored_highlight) {
            this.sponsored_highlight = sponsored_highlight;
        }

        public String getStylist() {
            return stylist;
        }

        public void setStylist(String stylist) {
            this.stylist = stylist;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWelcome_text() {
            return welcome_text;
        }

        public void setWelcome_text(String welcome_text) {
            this.welcome_text = welcome_text;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public List<String> getImages_small() {
            return images_small;
        }

        public void setImages_small(List<String> images_small) {
            this.images_small = images_small;
        }

        public List<ProductTagsBean> getProduct_tags() {
            return product_tags;
        }

        public void setProduct_tags(List<ProductTagsBean> product_tags) {
            this.product_tags = product_tags;
        }

        public List<SizesBean> getSizes() {
            return sizes;
        }

        public void setSizes(List<SizesBean> sizes) {
            this.sizes = sizes;
        }

        public List<?> getVideo() {
            return video;
        }

        public void setVideo(List<?> video) {
            this.video = video;
        }

        public static class CouponOfferBean {
        }

        public static class ImagesBean {
            /**
             * original : //shopimages.vstores.io/88256/1569929604016-34-original.jpg?1580192805
             * product : //shopimages.vstores.io/88256/1569929604016-34-product.jpg?1580192805
             * small : //shopimages.vstores.io/88256/1569929604016-34-small.jpg?1580192805
             */

            private String original;
            private String product;
            private String small;

            public String getOriginal() {
                return original;
            }

            public void setOriginal(String original) {
                this.original = original;
            }

            public String getProduct() {
                return product;
            }

            public void setProduct(String product) {
                this.product = product;
            }

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }
        }

        public static class ProductTagsBean {
            /**
             * color : #eb354b
             * tag : EXPRESS DELIVERY
             */

            private String color;
            private String tag;

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }
        }

        public static class SizesBean {
            /**
             * corrected_count_on_hand : 0
             * count_on_hand : 0
             * id : 82572
             * max_selling_price : 667
             * min_order_quantity : 1
             * min_selling_price : 423
             * name : M
             * option_value_name : M
             * original_price : 384
             * per_piece_price : 355
             * price : 355
             * product_id : 65288
             * quantity_per_variant : 1
             * size : 11
             * size_description :
             */

            private int corrected_count_on_hand;
            private int count_on_hand;
            private int id;
            private float max_selling_price;
            private int min_order_quantity;
            private float min_selling_price;
            private String name;
            private String option_value_name;
            private float original_price;
            private float per_piece_price;
            private float price;
            private int product_id;
            private int quantity_per_variant;
            private int size;
            private String size_description;
            private int zone_id;
            private String zone_name;

            public int getCorrected_count_on_hand() {
                return corrected_count_on_hand;
            }

            public void setCorrected_count_on_hand(int corrected_count_on_hand) {
                this.corrected_count_on_hand = corrected_count_on_hand;
            }

            public int getCount_on_hand() {
                return count_on_hand;
            }

            public void setCount_on_hand(int count_on_hand) {
                this.count_on_hand = count_on_hand;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public float getMax_selling_price() {
                return max_selling_price;
            }

            public void setMax_selling_price(float max_selling_price) {
                this.max_selling_price = max_selling_price;
            }

            public int getMin_order_quantity() {
                return min_order_quantity;
            }

            public void setMin_order_quantity(int min_order_quantity) {
                this.min_order_quantity = min_order_quantity;
            }

            public float getMin_selling_price() {
                return min_selling_price;
            }

            public void setMin_selling_price(float min_selling_price) {
                this.min_selling_price = min_selling_price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOption_value_name() {
                return option_value_name;
            }

            public void setOption_value_name(String option_value_name) {
                this.option_value_name = option_value_name;
            }

            public float getOriginal_price() {
                return original_price;
            }

            public void setOriginal_price(float original_price) {
                this.original_price = original_price;
            }

            public float getPer_piece_price() {
                return per_piece_price;
            }

            public void setPer_piece_price(float per_piece_price) {
                this.per_piece_price = per_piece_price;
            }

            public float getPrice() {
                return price;
            }

            public void setPrice(float price) {
                this.price = price;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public int getQuantity_per_variant() {
                return quantity_per_variant;
            }

            public void setQuantity_per_variant(int quantity_per_variant) {
                this.quantity_per_variant = quantity_per_variant;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getSize_description() {
                return size_description;
            }

            public void setSize_description(String size_description) {
                this.size_description = size_description;
            }

            public int getZone_id() {
                return zone_id;
            }

            public void setZone_id(int zone_id) {
                this.zone_id = zone_id;
            }

            public String getZone_name() {
                return zone_name;
            }

            public void setZone_name(String zone_name) {
                this.zone_name = zone_name;
            }

        }
    }
}
