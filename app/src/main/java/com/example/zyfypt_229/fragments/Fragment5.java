package com.example.zyfypt_229.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zyfypt_229.R;
import com.example.zyfypt_229.activities.LoginActivity;
import com.example.zyfypt_229.activities.Main3Activity;


public class Fragment5 extends BaseFragment {
    public Button button8,btn_zx;



    public Fragment5() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment5,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView( view );
    }


    private void initRecyclerView(View view) {
        button8 = view.findViewById(R.id.button8);
        btn_zx=view.findViewById( R.id.btn_zx);
        btn_zx.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    deleteSP(getContext());
                Intent intent  = new  Intent(getActivity(), LoginActivity.class );
                intent.putExtra("ZX",1);
                startActivity( intent );

                }
            public void deleteSP(Context context){
                SharedPreferences sharedPreferences=context.getSharedPreferences("login",context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();

            }


        } );

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Main3Activity.class);
                startActivity(intent);
            }
        });
    }}



