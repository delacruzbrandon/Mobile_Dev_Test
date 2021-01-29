package dcbrh.ph.mobiledevtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import dcbrh.ph.mobiledevtest.ProductDataService.VolleyResponseListener;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnItemClickListener {
    private static final String TAG = "MainActivity";
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProductDataService dataService = new ProductDataService(this);
        dataService.getLocalProductList(new VolleyResponseListener() {
            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<Product> response) {
                 productList = response;
                Log.d(TAG, "onResponse: "+response);
            }
        });



        ProductAdapter productAdapter = new ProductAdapter(productList, this, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_main);
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    public void onItemClick(int itemPosition) {
        Product clickedItem = productList.get(itemPosition);
        Intent intent = new Intent(getLayoutInflater().getContext(), MainActivity.class);

        intent.putExtra("id", clickedItem.getId());
        intent.putExtra("name", clickedItem.getName());
        intent.putExtra("category", clickedItem.getCategory());
        intent.putExtra("price", clickedItem.getPrice());
        intent.putExtra("bgColor", clickedItem.getBgColor());

        Log.d(TAG, "onItemClick: "+intent.getStringExtra("name"));
    }
}