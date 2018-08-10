package blctelkom.id.srimandali.Menu;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.Arrays;
import java.util.List;

import blctelkom.id.srimandali.MainActivity;
import blctelkom.id.srimandali.Menu.Adapter.TiltMeterAdapter;
import blctelkom.id.srimandali.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TiltMeter extends Fragment implements AdapterView.OnItemClickListener {
    private List<String> dataTempat;
    private List<String> dataLink;
    private TiltMeterAdapter mAdapter;
    Context context;

    public TiltMeter newInstance(){
        return new TiltMeter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tilt_meter, container, false);
        ((MainActivity) getActivity())
                .setTitle("Tilt Meter");
        //Inisialisasi Array
        // Data akan di ambil dari String pada XML
        dataTempat = Arrays.asList(getResources().getStringArray(R.array.Tempat));
        dataLink = Arrays.asList(getResources().getStringArray(R.array.LinkTilt));
        // Inisialisasi RecycleView
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.RVTilt);
        //Set Jenis Layout yang ingin di gunakan Oleh RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Set adapter recyclerview
        // adapter akan Mengambil data dan melempar ke TiltMeterAdapter
        mAdapter = new TiltMeterAdapter(dataTempat,dataLink,getActivity());
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
