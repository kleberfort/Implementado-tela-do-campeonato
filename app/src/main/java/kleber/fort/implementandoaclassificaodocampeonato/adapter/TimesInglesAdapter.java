package kleber.fort.implementandoaclassificaodocampeonato.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import kleber.fort.implementandoaclassificaodocampeonato.databinding.TelaClassificacaoInglesABinding;
import kleber.fort.implementandoaclassificaodocampeonato.databinding.TelaListaTimesBinding;
import kleber.fort.implementandoaclassificaodocampeonato.model.ClassificaoTimes;

public class TimesInglesAdapter extends RecyclerView.Adapter<TimesInglesAdapter.ViewHolder> {

    private List<ClassificaoTimes> listaTimes;

    public TimesInglesAdapter(List<ClassificaoTimes> listaTimes) {
        this.listaTimes = listaTimes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TelaClassificacaoInglesABinding binding = TelaClassificacaoInglesABinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        ClassificaoTimes classificaoTimes = listaTimes.get(position);

        Glide.with(context).load(classificaoTimes.getImagem()).into(holder.binding.ivTime);
        holder.binding.tvNomeTime.setText(classificaoTimes.getNome());


    }

    @Override
    public int getItemCount() {
        return listaTimes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TelaClassificacaoInglesABinding binding;

        public ViewHolder(TelaClassificacaoInglesABinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
