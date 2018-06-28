package blctelkom.id.srimandali.Menu;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import blctelkom.id.srimandali.MainActivity;
import blctelkom.id.srimandali.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Kontak extends Fragment {
    public Kontak newInstance(){
        return new Kontak();
    }
    private ImageButton bpbd,polres,pmi,sar,kodim,orari;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kontak, container, false);
        bpbd = (ImageButton)view.findViewById(R.id.phone_bpbd);
        polres = (ImageButton)view.findViewById(R.id.phone_polres);
        pmi  = (ImageButton)view.findViewById(R.id.phone_pmi);
        sar = (ImageButton)view.findViewById(R.id.phone_sar);
        kodim = (ImageButton)view.findViewById(R.id.phone_kodim);
        orari = (ImageButton)view.findViewById(R.id.phone_orari);;
        // Inflate the layout for this fragment
        ((MainActivity) getActivity())
                .setTitle("Kontak");
        makePhone();
        return view;
    }
    public void makePhone(){
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.CALL_PHONE},
                1);
            bpbd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0272328564"));
                    startActivity(intent);
                }
            });
            polres.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0272321234"));
                    startActivity(intent);
                }
            });
            pmi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0272324473"));
                    startActivity(intent);
                }
            });
            sar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0272324115"));
                    startActivity(intent);
                }
            });
            kodim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0272322316"));
                    startActivity(intent);
                }
            });
            orari.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0272322595"));
                    startActivity(intent);
                }
            });
        }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getContext(), "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


}
