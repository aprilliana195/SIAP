import java.util.Collections;
import java.util.List;

public class ProductFetcher {


        private static final String URL_STRING = "https://dummyjson.com/products";
        private static final String X_CONS_ID_HEADER = "X-Cons-ID";
        private static final String USER_KEY_HEADER = "User-Key";
        private static final String X_CONS_ID_VALUE = "124567";
        private static final String USER_KEY_VALUE = "faY738sH";

        public static void main(String[] args) {
            try {
                // Mengambil data JSON dari URL
                String jsonData = fetchDataFromUrl();

                // Mengurai JSON menjadi daftar produk
                List<Product> productList = parseJsonData(jsonData);

                // Mengurutkan produk berdasarkan rating
                Collections.sort(productList);

                // Menampilkan produk yang telah diurutkan
                displaySortedProducts(productList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static String fetchDataFromUrl() throws IOException {
            URL url = new URL(URL_STRING);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Menambahkan header akses
            connection.setRequestProperty(X_CONS_ID_HEADER, X_CONS_ID_VALUE);
            connection.setRequestProperty(USER_KEY_HEADER, USER_KEY_VALUE);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
            reader.close();

            return jsonData.toString();
        }

        private static List<Product> parseJsonData(String jsonData) {
            Gson gson = new Gson();
            ProductListResponse response = gson.fromJson(jsonData, ProductListResponse.class);
            return response.getProducts();
        }

        private static void displaySortedProducts(List<Product> productList) {
            for (Product product : productList) {
                System.out.println(product);
            }
        }
    }

    class ProductListResponse {
        @SerializedName("products")
        private List<Product> products;

        public List<Product> getProducts() {
            return products;
        }
    }

    class Product implements Comparable<Product> {
        @SerializedName("name")
        private String name;

        @SerializedName("rating")
        private double rating;

        @SerializedName("category")
        private String category;

        @SerializedName("description")
        private String description;

        public String getName() {
            return name;
        }

        public double getRating() {
            return rating;
        }

        public String getCategory() {
            return category;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "name='" + name + '\'' +
                    ", rating=" + rating +
                    ", category='" + category + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Product other) {
            return Double.compare(this.rating, other.rating);
        }
    }

}
