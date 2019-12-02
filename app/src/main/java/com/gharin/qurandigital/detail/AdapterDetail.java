package com.gharin.qurandigital.detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gharin.qurandigital.R;

import java.util.List;

public class AdapterDetail extends RecyclerView.Adapter<AdapterDetail.DetailHolder> {

    private List<ResponseDetail> list;

    public AdapterDetail(List<ResponseDetail> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DetailHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_detail, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHolder detailHolder, int i) {
            detailHolder.binView(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DetailHolder extends RecyclerView.ViewHolder {
        TextView tvNomorde, tvNamade, tvAyatde;

        public DetailHolder(@NonNull View itemView) {
            super(itemView);

            tvNomorde = itemView.findViewById(R.id.tvNomorSurahde);
            tvNamade = itemView.findViewById(R.id.tvNamaSurahde);
            tvAyatde = itemView.findViewById(R.id.tvJumlahAyatde);

        }

        public void binView(ResponseDetail data) {
            String nama = Html.fromHtml(String.valueOf(data.getTr())).toString();
            tvNomorde.setText(data.getNomor());
            tvNamade.setText(data.getAr());
            tvAyatde.setText(nama);

        }
    }
}
