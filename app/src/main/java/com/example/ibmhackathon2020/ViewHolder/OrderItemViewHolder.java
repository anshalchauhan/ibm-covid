package com.example.ibmhackathon2020.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.ibmhackathon2020.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderItemViewHolder extends RecyclerView.ViewHolder {
    public TextView txtitemname6,txtcost6,txtqty6;
    public TextView in,ic,iq;

    public OrderItemViewHolder(@NonNull View itemView) {
        super(itemView);


            txtitemname6=(TextView)itemView.findViewById(R.id.iname6);
            txtcost6=(TextView)itemView.findViewById(R.id.cost6);
            txtqty6=(TextView)itemView.findViewById(R.id.quantity6);

        in=(TextView)itemView.findViewById(R.id.itemName3);
        ic=(TextView)itemView.findViewById(R.id.cost3);
        iq=(TextView)itemView.findViewById(R.id.qty3);


    }
}
