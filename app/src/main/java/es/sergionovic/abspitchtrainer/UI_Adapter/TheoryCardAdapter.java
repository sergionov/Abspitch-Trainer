package es.sergionovic.abspitchtrainer.UI_Adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.sergionovic.abspitchtrainer.Model.TheoryCard;
import es.sergionovic.abspitchtrainer.R;
import es.sergionovic.abspitchtrainer.UI.AbspitchTrainer_Activity;
import es.sergionovic.abspitchtrainer.UI.Theory_Activity;

/**
 * Created by Sergio on 15/03/2015.
 */
public class TheoryCardAdapter extends RecyclerView.Adapter<TheoryCardAdapter.MyViewHolder> {

    private Context context;
    private List<TheoryCard> data;

    private LayoutInflater inflater;

    private String SUBTITLE_CARD = "cardSubtitle";
    private String BODY_CARD = "cardBody";
    private String TITLE_CARD = "cardTitle";

    String title, subtitle, body;

    MyViewHolder viewHolder;
    Palette palette;
    TheoryCard current;

    public TheoryCardAdapter(Context context, List<TheoryCard> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TheoryCardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.theory_card, parent, false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final TheoryCardAdapter.MyViewHolder holder, int position) {
        viewHolder = holder;
        current = data.get(position);
        holder.card_title.setText(current.cardTitle);
        holder.card_subtitle.setText(current.cardSubtitle);
        holder.card_body.setText(current.cardBody);
        holder.card_button.setText(current.cardButton);

        holder.card_button.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        final int cardElement = position;

        holder.card_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;

                title = data.get(cardElement).cardTitle;
                subtitle = data.get(cardElement).cardSubtitle;
                body = data.get(cardElement).cardBody;

                switch (cardElement) {
                    case 0:
                        intent = new Intent(v.getContext(), Theory_Activity.class);
                        intent.putExtra(TITLE_CARD, title);
                        intent.putExtra(SUBTITLE_CARD, subtitle);
                        intent.putExtra(BODY_CARD,  body);
                        v.getContext().startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(v.getContext(), Theory_Activity.class);
                        intent.putExtra(TITLE_CARD, title);
                        intent.putExtra(SUBTITLE_CARD, subtitle);
                        intent.putExtra(BODY_CARD,  body);
                        v.getContext().startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(v.getContext(), Theory_Activity.class);
                        intent.putExtra(TITLE_CARD, title);
                        intent.putExtra(SUBTITLE_CARD, subtitle);
                        intent.putExtra(BODY_CARD,  body);
                        v.getContext().startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(v.getContext(), Theory_Activity.class);
                        intent.putExtra(TITLE_CARD, title);
                        intent.putExtra(SUBTITLE_CARD, subtitle);
                        intent.putExtra(BODY_CARD,  body);
                        v.getContext().startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(v.getContext(), Theory_Activity.class);
                        intent.putExtra(TITLE_CARD, title);
                        intent.putExtra(SUBTITLE_CARD, subtitle);
                        intent.putExtra(BODY_CARD,  body);
                        v.getContext().startActivity(intent);
                        break;
                    default:
                        Toast toast = Toast.makeText(v.getContext(),
                                "Aún sin implementar ;)", Toast.LENGTH_SHORT);
                        toast.show();
                        break;
                }

            }
        });

    }


    //our new getItemCount() that includes header View
    @Override
    public int getItemCount() {
        return data.size(); // header
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView card_title;
        public TextView card_subtitle;
        public TextView card_body;
        public Button card_button;

        public MyViewHolder(View itemView) {
            super(itemView);
            card_title = (TextView) itemView.findViewById(R.id.theory_card_title);
            card_subtitle = (TextView) itemView.findViewById(R.id.theory_card_subtitle);
            card_body = (TextView) itemView.findViewById(R.id.theory_card_body);
            card_button = (Button) itemView.findViewById(R.id.theory_card_button);
        }
    }
}
