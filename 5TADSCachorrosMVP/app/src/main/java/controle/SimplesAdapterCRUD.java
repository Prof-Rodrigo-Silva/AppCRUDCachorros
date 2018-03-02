package controle;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by rodrigo on 06/11/17.
 */

public class SimplesAdapterCRUD extends BaseAdapter {
    private String[] crud = new String[]{"CADASTRAR","LISTAR","RECYCLERVIEW"};
    private Context context;

    public SimplesAdapterCRUD(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getCount() {
        return crud.length;//Retorna quantidade de itens do adapter
    }

    @Override
    public Object getItem(int position) {
        return crud[position];//Retorna o objeto para esta posição
    }

    @Override
    public long getItemId(int position) {
        return position;//Retorna o id do objeto para esta posição
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String cruds = crud[position];
        TextView t = new TextView(context);
        float dip = 50;
        float densidade = context.getResources().getDisplayMetrics().density;
        int px = (int) (dip * densidade + 0.5f);
        t.setHeight(px);
        t.setText(cruds);
        return t;
    }
}