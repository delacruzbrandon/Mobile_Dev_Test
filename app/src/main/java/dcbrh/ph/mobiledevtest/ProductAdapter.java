package dcbrh.ph.mobiledevtest;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final List<Product> productList;
    private final Context context;
    private static OnItemClickListener itemClickListener;

    public ProductAdapter(List<Product> productList, Context context, OnItemClickListener onItemClickListener) {
        this.productList = productList;
        this.context = context;
        this.itemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int itemPosition);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Picasso.get().load(productList.get(position).getId()).into(holder.productImage);
        holder.productCategory.setText(productList.get(position).getCategory());
        holder.productName.setText(productList.get(position).getName());
        holder.productPrice.setText("$"+productList.get(position).getPrice());
        holder.productImage.setBackgroundColor(Color.parseColor(productList.get(position).getBgColor()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView productImage;
        private TextView productCategory, productName, productPrice;
        private Button productButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.imageView_image_product);
            productCategory = itemView.findViewById(R.id.textView_category_product);
            productName = itemView.findViewById(R.id.textView_name_product);
            productPrice = itemView.findViewById(R.id.textView_price_product);
            productButton = itemView.findViewById(R.id.button_add_product);

            productButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position!= RecyclerView.NO_POSITION) {
                itemClickListener.onItemClick(position);
            }
        }
    }
}
