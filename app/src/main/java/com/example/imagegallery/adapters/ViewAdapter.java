package com.example.imagegallery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.imagegallery.R;
import com.example.imagegallery.models.FlickrImage;
import com.squareup.picasso.Picasso;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder>{

    private Context context;
    private List<FlickrImage> data;

    public ViewAdapter(Context context, List<FlickrImage> data){
        this.context = context;
        this.data = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.cardview, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(data.get(position).getImageTitle());
        Picasso.with(context).load(data.get(position).getImageURL()).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivImage;
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.image_title);
            ivImage = itemView.findViewById(R.id.imageview_id);

        }

    }

}
