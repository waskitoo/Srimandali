package blctelkom.id.srimandali.Menu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import blctelkom.id.srimandali.Menu.TilitMeterDetail;
import blctelkom.id.srimandali.R;

/**
 * Created by waski on 05/06/2018.
 */

public class TiltMeterAdapter extends RecyclerView.Adapter<TiltMeterAdapter.MyViewHolder> {
    private List<String> dataTempat;
    private List<String> dataLink;
    private Context context;
    private CardView cardView;
    public class MyViewHolder extends RecyclerView.ViewHolder {
       private TextView mTempat;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTempat = (TextView)itemView.findViewById(R.id.TVNamaTilt);
            cardView = (CardView)itemView.findViewById(R.id.CVTilt);
        }
    }
    public TiltMeterAdapter(List<String> dataTempat, List<String> dataLink, Context context){
        this.dataLink =dataLink;
        this.dataTempat = dataTempat;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tilt_meter_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final String listTempat = dataTempat.get(position);
        final String listLink = dataLink.get(position);
        holder.mTempat.setText(""+listTempat);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, TilitMeterDetail.class);
                i.putExtra("LinkKEY",""+listLink);
                i.putExtra("NamaKEY",""+listTempat);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataTempat.size();
    }

}
