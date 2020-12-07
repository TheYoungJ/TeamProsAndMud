package com.example.oneforall.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.oneforall.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //done
        ImageButton imgBtn1 = (ImageButton) root.findViewById(R.id.imageButton1);
        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/70305903"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn2 = (ImageButton) root.findViewById(R.id.imageButton2);
        imgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/80991034"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn3 = (ImageButton) root.findViewById(R.id.imageButton3);
        imgBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://app.primevideo.com/detail?gti=amzn1.dv.gti.58b7218c-6fa8-01bf-7c5a-84850dad1c1c&ref"));
                startActivity(url);
            }
        });


        //done
        ImageButton imgBtn4 = (ImageButton) root.findViewById(R.id.imageButton4);
        imgBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://app.primevideo.com/detail?gti=amzn1.dv.gti.20b88cde-41f2-3935-e671-d4f680ff1843&ref"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn5 = (ImageButton) root.findViewById(R.id.imageButton5);
        imgBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://app.primevideo.com/detail?gti=amzn1.dv.gti.d2b3c26b-1ded-4c1e-f69a-0f18e411f6e1&ref"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn6 = (ImageButton) root.findViewById(R.id.imageButton6);
        imgBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/70079583"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn7 = (ImageButton) root.findViewById(R.id.imageButton7);
        imgBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/80023638"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn8 = (ImageButton) root.findViewById(R.id.imageButton8);
        imgBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/80221640"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn9 = (ImageButton) root.findViewById(R.id.imageButton9);
        imgBtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/81002747"));
                startActivity(url);
            }
        });

        //change
        ImageButton imgBtn10 = (ImageButton) root.findViewById(R.id.imageButton10);
        imgBtn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/80221908"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn11 = (ImageButton) root.findViewById(R.id.imageButton11);
        imgBtn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/80189221"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn12 = (ImageButton) root.findViewById(R.id.imageButton12);
        imgBtn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://app.primevideo.com/detail?gti=amzn1.dv.gti.5eb510bc-7578-d2dd-49d3-484070a96b52&ref"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn13 = (ImageButton) root.findViewById(R.id.imageButton13);
        imgBtn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/70178217"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn14 = (ImageButton) root.findViewById(R.id.imageButton14);
        imgBtn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/70136120"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn15 = (ImageButton) root.findViewById(R.id.imageButton15);
        imgBtn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/80192098"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn16 = (ImageButton) root.findViewById(R.id.imageButton16);
        imgBtn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://app.primevideo.com/detail?gti=amzn1.dv.gti.8aabf457-9ba5-4d72-85e2-8e1de6398b66&ref"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn17 = (ImageButton) root.findViewById(R.id.imageButton17);
        imgBtn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://app.primevideo.com/detail?gti=amzn1.dv.gti.3aae96d6-2f06-c508-8773-90cdfdce7e44&ref"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn18 = (ImageButton) root.findViewById(R.id.imageButton18);
        imgBtn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/70301870"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn19 = (ImageButton) root.findViewById(R.id.imageButton19);
        imgBtn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/80057281"));
                startActivity(url);
            }
        });

        //done
        ImageButton imgBtn20 = (ImageButton) root.findViewById(R.id.imageButton20);
        imgBtn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/70155547"));
                startActivity(url);
            }
        });

        ImageButton imgBtn21 = (ImageButton) root.findViewById(R.id.imageButton21);
        imgBtn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/80996790"));
                startActivity(url);
            }
        });

        ImageButton imgBtn22 = (ImageButton) root.findViewById(R.id.imageButton22);
        imgBtn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/80117038"));
                startActivity(url);
            }
        });

        ImageButton imgBtn23 = (ImageButton) root.findViewById(R.id.imageButton23);
        imgBtn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/80240027"));
                startActivity(url);
            }
        });

        ImageButton imgBtn24 = (ImageButton) root.findViewById(R.id.imageButton24);
        imgBtn24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/80234304"));
                startActivity(url);
            }
        });

        ImageButton imgBtn25 = (ImageButton) root.findViewById(R.id.imageButton25);
        imgBtn25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/80988988"));
                startActivity(url);
            }
        });

        ImageButton imgBtn26 = (ImageButton) root.findViewById(R.id.imageButton26);
        imgBtn26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/80221410"));
                startActivity(url);
            }
        });

        ImageButton imgBtn27 = (ImageButton) root.findViewById(R.id.imageButton27);
        imgBtn27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/80025678"));
                startActivity(url);
            }
        });

        ImageButton imgBtn28 = (ImageButton) root.findViewById(R.id.imageButton28);
        imgBtn28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/81022733"));
                startActivity(url);
            }
        });

        ImageButton imgBtn29 = (ImageButton) root.findViewById(R.id.imageButton29);
        imgBtn29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/81002747"));
                startActivity(url);
            }
        });

        ImageButton imgBtn30 = (ImageButton) root.findViewById(R.id.imageButton30);
        imgBtn30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/80113847"));
                startActivity(url);
            }
        });

        return root;
    }
}