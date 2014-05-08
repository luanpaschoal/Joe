package br.rj.cefet.joe.app.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.rj.cefet.joe.app.R;

/**
 * Created by Luan on 18/04/2014.
 */

public class Mensagem {

    public static void show(Context context, String texto, int tipoMsg) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.msg_layout, null);

        TextView textView = (TextView) layout.findViewById(R.id.tvTexto);
        textView.setText(texto);

        LinearLayout llRoot = (LinearLayout) layout.findViewById(R.id.llRoot);

        Drawable icone;
        int fundo;

        switch (tipoMsg) {
            case Constantes.AVISO:
                icone = context.getResources().getDrawable(R.drawable.aviso);
                fundo = R.drawable.msg_fundo_amarelo;
                break;
            case Constantes.ERRO:
                icone = context.getResources().getDrawable(R.drawable.erro);
                fundo = R.drawable.msg_fundo_vermelho;
                break;
            default:
                icone = context.getResources().getDrawable(R.drawable.informacao);
                fundo = R.drawable.msg_fundo_azul;
                break;
        }

        textView.setCompoundDrawablesWithIntrinsicBounds(icone, null, null, null);
        llRoot.setBackgroundResource(fundo);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}