package com.gharin.qurandigital;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gharin.qurandigital.detail.DetailActivity;
import com.gharin.qurandigital.model.ResponseList;

import java.util.List;

public class AdapterSurah extends RecyclerView.Adapter<AdapterSurah.SurahHolder> {

    private List<ResponseList> listSruah;

    public AdapterSurah(List<ResponseList> listSruah) {
        this.listSruah = listSruah;
    }

    @NonNull
    @Override
    public SurahHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new SurahHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SurahHolder surahHolder, int i) {

        surahHolder.binView(listSruah.get(i));

    }

    @Override
    public int getItemCount() {
        return listSruah.size();
    }

    public class SurahHolder extends RecyclerView.ViewHolder {

        TextView tvNomer, tvNama, tvJumlah;
        ImageView btSound;
        public SurahHolder(@NonNull View itemView) {
            super(itemView);

            tvNomer = itemView.findViewById(R.id.tvNomorSurah);
            tvNama = itemView.findViewById(R.id.tvNamaSurah);
            tvJumlah = itemView.findViewById(R.id.tvJumlahAyat);

            btSound = itemView.findViewById(R.id.btSound);
        }

        private void binView(ResponseList data) {
            tvNomer.setText(data.getNomor());
            tvNama.setText(data.getNama());
            tvJumlah.setText(String.valueOf(data.getAyat()));
            final String nomor = String.valueOf(data.getNomor());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), DetailActivity.class)
                    .putExtra("KEY", nomor));
                }
            });
        }

    }
}
