package blctelkom.id.srimandali.Menu.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import blctelkom.id.srimandali.R;

/**
 * Created by waski on 29/05/2018.
 */

public class KameraPengamatAdapter extends RecyclerView.Adapter<KameraPengamatAdapter.MyViewHolder> {
    private List<String> dataTempat;
    private List<String> dataGambar;
    private Context context;
    private CardView cardView;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTempat;
        private ImageView mImageView;
        private MyViewHolder(View itemView) {
            super(itemView);
            mTempat = (TextView)itemView.findViewById(R.id.TVNamaCam);
            mImageView = (ImageView)itemView.findViewById(R.id.IVGambar);
            cardView = (CardView)itemView.findViewById(R.id.cvPhoto);
        }
    }
    public KameraPengamatAdapter(ArrayList<String> dataTempat, ArrayList<String> dataGambar, Context context){
        this.dataTempat = dataTempat;
        this.dataGambar = dataGambar;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_kamera_pengamat_row,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final String listTempat = dataTempat.get(position);
        final String listGambar = dataGambar.get(position);
        holder.mTempat.setText(""+listTempat);
        //Picasso.get().load(""+listGambar).into(holder.mImageView);
        Glide.with(context).load(""+listGambar).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return dataTempat.size();
    }


}
