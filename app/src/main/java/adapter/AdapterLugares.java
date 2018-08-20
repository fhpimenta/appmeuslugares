package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.edu.ifma.felipe.appmeuslugares.R;
import modelo.Lugar;

public class AdapterLugares extends RecyclerView.Adapter<AdapterLugares.ViewHolder> {

    private List<Lugar> lugares;
    private Context context;

    public AdapterLugares(List<Lugar> lugares, Context context) {
        this.lugares = lugares;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            Lugar lugar = lugares.get(position);
            holder.nome.setText(lugar.getNome());
            holder.descricao.setText(lugar.getDescricao());

            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent("MAPS");
                    intent.addCategory("VIEW");

                    Lugar lugar = lugares.get(position);
                    intent.putExtra("lugar", lugar);
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return lugares.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nome;
        TextView descricao;
        LinearLayout item;

        public ViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.lugar_item_title);
            descricao = itemView.findViewById(R.id.lugar_item_description);
            item = itemView.findViewById(R.id.item_activity);
        }
    }

}


