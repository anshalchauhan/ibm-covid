package com.example.ibmhackathon2020.ViewHolder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ibmhackathon2020.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopCategoryViewHolder extends RecyclerView.ViewHolder {

    public TextView txtshopName,txtownerName,txtlocation,txtphonenum,txtpincode;
    public RelativeLayout parentLayout;

    public ShopCategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        txtshopName=(TextView)itemView.findViewById(R.id.shopName);
        txtownerName=(TextView)itemView.findViewById(R.id.ownerName);
        txtlocation=(TextView)itemView.findViewById(R.id.location);
        txtphonenum=(TextView)itemView.findViewById(R.id.phonenum);
        txtpincode=(TextView)itemView.findViewById(R.id.pincode);
        parentLayout=(RelativeLayout)itemView.findViewById(R.id.parlayout);
    }
}
