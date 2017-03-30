package masxdeveloper.simplegalley;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements LoaderPrensenter.OnFileLoaded {

    LoaderPrensenter presenter;
    private static final int REQUEST_CODE = 0x00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new LoaderPrensenter(this);

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

    @Override
    protected void onResume() {
        super.onResume();
        if (PermissionAkses()) {
            presenter.getListImage(Environment.getExternalStorageDirectory());
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE:

                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_DENIED) {
                    presenter.getListImage(Environment.getExternalStorageDirectory());
                }
                break;
        }
    }

    private boolean PermissionAkses() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED;
    }

}

