package masxdeveloper.simplegalley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String Path = getIntent().getStringExtra("PATH");

        File path = new File(Path);

        ImageView detail = (ImageView) findViewById(R.id.iv_detail);

        Picasso.with(this)
                .load(path.getAbsoluteFile())
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(detail);
    }
}
