package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import modelo.Lugar;

public class AdapterLugares extends RecyclerView.Adapter {
    private List<Lugar> lugares;

    public AdapterLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lugares.size();
    }
}
