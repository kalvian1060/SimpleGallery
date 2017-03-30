package masxdeveloper.simplegalley;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Masx Developer on 3/30/17.
 * https://masx-dev.blogspot.com
 */

public class LoaderPrensenter {

    private OnFileLoaded FileLoaded;

    public LoaderPrensenter(OnFileLoaded fileLoaded) {
        FileLoaded = fileLoaded;
    }

    public void getListImage(File Directory) {

        ArrayList<File> inFiles = new ArrayList<>();
        File[] files = Directory.listFiles();
        for (File file : files) {
            if (file.isFile()) {

                if (file.getName().contains(".jpg") || file.getName().contains(".JPG")
                        || file.getName().contains(".jpeg") || file.getName().contains(".JPEG")
                        || file.getName().contains(".png") || file.getName().contains(".PNG")
                        || file.getName().contains(".gif") || file.getName().contains(".GIF")
                        || file.getName().contains(".bmp") || file.getName().contains(".BMP")) {
                    inFiles.add(file);
                }
            } else {
                inFiles.addAll(Arrays.asList(file.listFiles()));
            }
        }

        FileLoaded.File(inFiles);

    }

    public interface OnFileLoaded {
        void File(ArrayList<File> File);
    }

}
