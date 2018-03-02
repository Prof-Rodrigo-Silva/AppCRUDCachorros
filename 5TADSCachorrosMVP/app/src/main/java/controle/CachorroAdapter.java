package controle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rodrigo.a5tads_cachorrosmvp.R;

import java.util.List;

/**
 * Created by rodrigo on 21/11/17.
 */

public class CachorroAdapter extends RecyclerView.Adapter<CachorroAdapter.CachorrosViewHolder> {

    protected static final String TAG = "debugcachorro";
    private final List<Cachorro> cachorros;
    private final Context context;
    private final CachorroOnClickListener onClickListener;

    public interface CachorroOnClickListener{
        public void onClickCachorro(CachorrosViewHolder holder, int idx);
    }
    public CachorroAdapter(Context context, List<Cachorro> cachorros, CachorroOnClickListener onClickListener){
        this.context = context;
        this.cachorros = cachorros;
        this.onClickListener = onClickListener;
    }
    @Override
    public CachorrosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_cachorro, viewGroup, false);

        CachorrosViewHolder holder = new CachorrosViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final CachorrosViewHolder holder, final int position){
        Cachorro c = cachorros.get(position);
        holder.tNome.setText(c.nome);
        holder.tSexo.setText(c.sexo);
        holder.tRaca.setText(c.raca);
        if(c.foto != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(c.foto,0,c.foto.length);

            holder.img.setImageBitmap(bitmap);
        }else {
            holder.img.setImageResource(R.drawable.tijolos);
        }
        if (onClickListener !=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v){
                    onClickListener.onClickCachorro(holder, position);
                }
            });
        }
    }
    @Override
    public int getItemCount(){
        return this.cachorros != null ? this.cachorros.size():0;
    }
    public static class CachorrosViewHolder extends RecyclerView.ViewHolder{
        public TextView tNome;
        public TextView tSexo;
        public TextView tRaca;
        ImageView img;
        private View view;
        public CachorrosViewHolder(View view){
            super(view);
            this.view = view;
            tNome = (TextView) view.findViewById(R.id.tNome);
            tSexo = (TextView) view.findViewById(R.id.tSexo);
            tRaca = (TextView) view.findViewById(R.id.tRaca);
            img = (ImageView) view.findViewById(R.id.img);
        }
    }

}
