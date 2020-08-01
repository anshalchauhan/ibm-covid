package com.example.ibmhackathon2020.ViewHolder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ibmhackathon2020.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemCategoryViewHolder extends RecyclerView.ViewHolder{

    public TextView txtitemname,txtcost,txtqty;
    public TextView txtitemname1,txtcost1,txtqty1;
    public TextView txtiname2,txtcost2,txtslno2;
    public CheckBox selectitem;
    public EditText txtqty2;
    public RelativeLayout pl1;

    public ItemCategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        txtitemname=(TextView)itemView.findViewById(R.id.itemName);
        txtcost=(TextView)itemView.findViewById(R.id.cost1);
        txtqty=(TextView)itemView.findViewById(R.id.qty1);

        txtitemname1=(TextView)itemView.findViewById(R.id.itemName2);
        txtcost1=(TextView)itemView.findViewById(R.id.cost2);
        txtqty1=(TextView)itemView.findViewById(R.id.qty2);
        //selectitem=(CheckBox) itemView.findViewById(R.id.select_item);

        pl1=(RelativeLayout)itemView.findViewById(R.id.palayout);
        //enterqty1=(EditText)itemView.findViewById(R.id.enterqty);
        //txtcost2=(TextView)itemView.findViewById(R.id.cost3);

       // txtiname2=(TextView)itemView.findViewById(R.id.txtiname);
        //txtqty2=(EditText)itemView.findViewById(R.id.txtqty1);
        //txtcost2=(TextView)itemView.findViewById(R.id.txtcost1);
         //fab1 = (FloatingActionButton)itemView.findViewById(R.id.checkcost);
        //txtslno2=(TextView)itemView.findViewById(R.id.txtslno);

    }
}
