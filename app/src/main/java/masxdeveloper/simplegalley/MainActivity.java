package masxdeveloper.simplegalley;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements LoaderPrensenter.OnFileLoaded {

    LoaderPrensenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new LoaderPrensenter(this);
        presenter.getListImage(Environment.getExternalStorageDirectory());

    }

    @Override
    public void File(ArrayList<File> File) {
        AdapterGallery adp = new AdapterGallery(this, File, new AdapterGallery.OnItemClickListener() {
            @Override
            public void onItemClick(File item) {
                String Path = item.getAbsoluteFile().toString();
                Intent detail = new Intent(MainActivity.this, DetailActivity.class);
                detail.putExtra("PATH", Path);
                startActivity(detail);

            }
        });

        RecyclerView list = (RecyclerView) findViewById(R.id.imageGallery);
        list.setHasFixedSize(true);
        list.setLayoutManager(new GridLayoutManager(this, 3));
        list.setAdapter(adp);
    }
}

