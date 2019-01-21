package jac.grocerylist;




import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;


public class ItemAdapter  extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {


    private List<Item> items;
    private Context mCtx;
    FragmentManager f_manager;

    private static final String TAG = "ItemAdapter";

    private final ArrayList<Integer> selected = new ArrayList<>();


    public ItemAdapter(List<Item> items, Context mCtx) {
        this.items = items;
        this.mCtx = mCtx;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ItemAdapter.ViewHolder holder, final int position) {
        Item item = items.get(position);
        holder.textViewProduct.setText(item.getproduct());
        holder.textViewbranch.setText(item.getbranch());
        holder.textViewpresentation.setText(item.getpresentation());
       // holder.textViewpresentationCount.setText((int) item.getpresentationCount());
       // holder.textViewprice.setText((int) item.getprice());

        if (!selected.contains(position)){
            // view not selected
            holder.relativeRow.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.textViewProduct.setTextColor(Color.parseColor("#000000"));
            holder.textViewbranch.setTextColor(Color.parseColor("#000000"));
            holder.textViewpresentation.setTextColor(Color.parseColor("#000000"));
       //     holder.textViewpresentationCount.setTextColor(Color.parseColor("#000000"));
         //   holder.textViewprice.setTextColor(Color.parseColor("#000000"));

        }
        else
            // view is selected
            holder.relativeRow.setBackgroundColor(Color.parseColor("#008577"));


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ((Sessions)mCtx.getApplicationContext()).setSesidProduct(items.get(position).getidProduct());
                String strId= String.valueOf(((Sessions)mCtx.getApplicationContext()).getSesidProduct());



                ((Sessions)mCtx.getApplicationContext()).setSesProduct(items.get(position).getproduct());
                String strProducto= String.valueOf(((Sessions)mCtx.getApplicationContext()).getSesProduct());

               // Toast.makeText(mCtx, "producto: " + strProducto, Toast.LENGTH_SHORT).show();

                ((Sessions)mCtx.getApplicationContext()).setsesBranch(items.get(position).getbranch());
                ((Sessions)mCtx.getApplicationContext()).setsesPresentation(items.get(position).getpresentation());
             //   ((Sessions)mCtx.getApplicationContext()).setsespresentationCount(Double.valueOf(items.get(position).getpresentationCount()));
              //  ((Sessions)mCtx.getApplicationContext()).setsesprice(Double.valueOf(items.get(position).getprice()));



                // view.setBackgroundColor(Color.CYAN);
                holder.relativeRow.setBackgroundColor(Color.parseColor("#008577"));
                holder.textViewProduct.setTextColor(Color.parseColor("#ffffff"));
                holder.textViewbranch.setTextColor(Color.parseColor("#ffffff"));
                holder.textViewpresentation.setTextColor(Color.parseColor("#ffffff"));
          //      holder.textViewpresentationCount.setTextColor(Color.parseColor("#ffffff"));
             //   holder.textViewprice.setTextColor(Color.parseColor("#ffffff"));

                if (selected.isEmpty()){
                    selected.add(position);
                }else {
                    int oldSelected = selected.get(0);
                    selected.clear();
                    selected.add(position);
                    notifyItemChanged(oldSelected);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewProduct;
        public TextView textViewbranch;
        public TextView textViewpresentation;
       // public TextView textViewpresentationCount;
       // public TextView textViewprice;



        public ViewHolder(View itemView) {
            super(itemView);

            textViewProduct = (TextView) itemView.findViewById(R.id.textViewProduct);
            textViewbranch = (TextView) itemView.findViewById(R.id.textViewbranch);
            textViewpresentation = (TextView) itemView.findViewById(R.id.textViewpresentation);
         //   textViewpresentationCount = (TextView) itemView.findViewById(R.id.textViewpresentationCount);
          //  textViewprice = (TextView) itemView.findViewById(R.id.textViewprice);


            parentLayout = itemView.findViewById(R.id.parent_layout);
            relativeRow = itemView.findViewById(R.id.relativeRow);




        }

        LinearLayout parentLayout;
        final RelativeLayout relativeRow;

    }

}


