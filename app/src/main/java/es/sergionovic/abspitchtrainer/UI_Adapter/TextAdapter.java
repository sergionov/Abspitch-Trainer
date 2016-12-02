package es.sergionovic.abspitchtrainer.UI_Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import es.sergionovic.abspitchtrainer.Model.ExerciseCard;
import es.sergionovic.abspitchtrainer.Model.TextCard;
import es.sergionovic.abspitchtrainer.R;

/**
 * Created by Sergio on 23/06/2015.
 */
public class TextAdapter extends RecyclerView.Adapter<TextAdapter.MyViewHolder> {

    private Context context;
    private List<TextCard> data;
    private LayoutInflater inflater;

    public TextAdapter(Context context, List<TextCard> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TextAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.text_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TextAdapter.MyViewHolder holder, int position) {
        final TextCard current = data.get(position);
        holder.card_title.setText(current.cardTitle);
        holder.card_subtitle.setText(current.cardSubtitle);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView card_title;
        public TextView card_subtitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            card_title = (TextView) itemView.findViewById(R.id.text_title);
            card_subtitle = (TextView) itemView.findViewById(R.id.text_subtitle);
        }
    }
}
