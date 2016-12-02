package es.sergionovic.abspitchtrainer.UI_Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.sergionovic.abspitchtrainer.Model.ProfileCard;
import es.sergionovic.abspitchtrainer.R;
import es.sergionovic.abspitchtrainer.UI.EditProfile_Activity;

/**
 * Created by Sergio on 15/03/2015.
 */
public class ProfileCardAdapter extends RecyclerView.Adapter<ProfileCardAdapter.MyViewHolder>{

    private Context context;
    private List<ProfileCard> data;

    private LayoutInflater inflater;

    private String COLOR_BACKGROUND = "colorBackground";
    private String COLOR_STATUSBAR = "colorStatus";
    private String TITLE_CARD = "cardTitle";

    MyViewHolder viewHolder;
    Palette palette;
    ProfileCard current;

    public ProfileCardAdapter(Context context, List<ProfileCard> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ProfileCardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.card_profile, parent, false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ProfileCardAdapter.MyViewHolder holder, int position) {
        viewHolder = holder;
        current = data.get(position);
        holder.card_title.setText(current.cardTitle);

        Bitmap thumbnail = (BitmapFactory.decodeFile(current.cardImage));

        holder.cardImage.setImageBitmap(thumbnail);
        holder.card_subtitle.setText(current.cardSubtitle);
        holder.cardButton.setText(current.cardButton);

        palette = Palette.from(thumbnail).generate();

        holder.cardViewTitle.setBackgroundColor(palette.getVibrantColor(context.getResources().getColor(R.color.colorPrimary)));
        holder.cardButton.setTextColor(palette.getVibrantColor(context.getResources().getColor(R.color.colorPrimary)));

        final int cardElement = position;

        holder.cardButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                //Integer taggedPosition = (Integer) v.getTag();
                Intent intent;

                switch (cardElement) {
                    case 0:
                            intent = new Intent(v.getContext(), EditProfile_Activity.class);
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
        public ImageView cardImage;
        public Button cardButton;
        public RelativeLayout cardViewTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            card_title = (TextView) itemView.findViewById(R.id.card_title);
            card_subtitle = (TextView) itemView.findViewById(R.id.card_subtitle);
            cardImage = (ImageView) itemView.findViewById(R.id.card_image);
            cardButton = (Button) itemView.findViewById(R.id.card_btStart);
            cardViewTitle = (RelativeLayout) itemView.findViewById(R.id.card_view_title);
        }
    }
}
