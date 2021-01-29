package dcbrh.ph.mobiledevtest;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.IInterface;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ProductDataService {
    private static final String TAG = "ProductDataService";
    private Context context;
    private String PRODUCT_LIST_QUERY = "file:///C:/Users/Admin/AndroidStudioProjects/MobileDevTest/app/src/main/assets/products.json";
    private String PRODUCT_IMAGE_QUERY = "file:///C:/Users/Admin/AndroidStudioProjects/MobileDevTest/app/src/main/assets/";

    public ProductDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String error);
        void onResponse(List<Product> response);
    }

    public void getLocalProductList(final VolleyResponseListener responseListener) {
        String jsonString;
        List<Product> productList = new ArrayList<>();

        try {

            // Processing of local JSON
            InputStream inputStream = context.getAssets().open("products.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            // Conversion of JSON file to JSON Object
            jsonString = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("products"));


            for (int i=0; i<jsonArray.length(); i++) {

                Product product = new Product();
                JSONObject productJson = jsonArray.getJSONObject(i);

                product.setId(productJson.getString("id"));
                product.setName(productJson.getString("name"));
                product.setCategory(productJson.getString("category"));
                product.setPrice(productJson.getString("price"));
                product.setBgColor(productJson.getString("bgColor"));

                productList.add(product);

                Log.d(TAG, "getLocalProductList: Product Item"+productList.toString());
//                Log.d(TAG, "getLocalProductList: Product Item"+productList.toString());
            }

            responseListener.onResponse(productList);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            responseListener.onError(e.getMessage());
        }
    }

}
