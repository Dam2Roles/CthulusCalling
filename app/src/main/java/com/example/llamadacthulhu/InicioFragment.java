package com.example.llamadacthulhu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;


public class InicioFragment extends Fragment {

    PDFView pdfView;


    private View viewRoot;

    public InicioFragment() {
        // Required empty public constructor
    }
    void getRetrofitImage() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_inicio,container, false);
        pdfView=(PDFView)viewRoot.findViewById(R.id.pdfView);
        pdfView.fromAsset("manualrapidocthulhuesp.pdf").load();
        return viewRoot;
    }
}
