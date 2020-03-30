package esilv.a4.antoine.antoineproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {


    @BindView(R.id.tvTittle)
    TextView tvTittle;

    @BindView(R.id.tvImage)
    TextView tvImage;

    @BindView(R.id.tvOverview)
    TextView tvOverview;

    @Override
    public void onCreate(Bundle SaveInstanceState) {
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setTitle("Detail du film");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        int vote_count = i.getIntExtra("vote_count",0);
        int vote_average = i.getIntExtra("vote_average",0);
        String title = i.getStringExtra("title");
        int popularity = i.getIntExtra("popularity",0);
        String poster_path = i.getStringExtra("poster_path");
        String original_language = i.getStringExtra("original_language");
        String original_title = i.getStringExtra("original_title");
        String overview = i.getStringExtra("overview");
        String release_date = i.getStringExtra("release_date");


        tvTittle.setText(title);
        tvOverview.setText(overview);
        Picasso.with(this).load(Constant.IMAGE+"/"+poster_path).into((Target) tvImage);



    }


    public boolean onOptionItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
            finish();
            break;

        }
        return super.onOptionsItemSelected(item);

    }

}
