package controle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rodrigo.a5tads_cachorrosmvp.R;

import java.util.List;

/**
 * Created by rodrigo on 07/11/17.
 */

public class ListAdapter extends ArrayAdapter<Cachorro> {

    public Cachorro cachorro;

    public ListAdapter (Context context, List<Cachorro> cachorros){
        super(context,0,cachorros);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        cachorro = getItem(position);

        if (convertView ==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_layout,parent,false);
        }
        TextView tvNome = (TextView) convertView.findViewById(R.id.tv_item_nome);
        TextView tvRaca = (TextView) convertView.findViewById(R.id.tv_item_raca);
        TextView tvSexo = (TextView) convertView.findViewById(R.id.tv_item_sexo);

        tvNome.setText(cachorro.nome);
        tvRaca.setText(cachorro.raca);
        tvSexo.setText(cachorro.sexo);
        return convertView;
    }
}
