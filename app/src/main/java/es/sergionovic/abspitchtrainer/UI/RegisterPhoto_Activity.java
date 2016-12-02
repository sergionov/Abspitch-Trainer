package es.sergionovic.abspitchtrainer.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import es.sergionovic.abspitchtrainer.DB.DataBaseHandler;
import es.sergionovic.abspitchtrainer.R;
import es.sergionovic.abspitchtrainer.Util.Preferences;

public class RegisterPhoto_Activity extends AppCompatActivity {

    private static Button btNext, btPrevious, btUpload;

    private static ImageView imageView;

    static String path, picturePath;

    static boolean boolName, boolAge;

    private static String NAME_EXTRA = "NAME_EXTRA", AGE_EXTRA = "AGE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_photo);

        controls();

        events();
    }

    private void events() {
        btPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                if (!getIntent().getStringExtra(NAME_EXTRA).isEmpty()) {
                    Preferences.setUserName(v.getContext(), getIntent().getStringExtra(NAME_EXTRA));
                    boolName = true;
                }

                if (!getIntent().getStringExtra(AGE_EXTRA).isEmpty()) {
                    Preferences.setUserAge(v.getContext(), getIntent().getStringExtra(AGE_EXTRA));
                    boolAge = true;
                }

                if (path.isEmpty())
                    Preferences.setUserPhoto(v.getContext(), picturePath);
                if (picturePath.isEmpty())
                    Preferences.setUserPhoto(v.getContext(), path);

                if (boolAge && boolName) {
                    startActivity(intent);
                    Preferences.setUserCreated(v.getContext(), true);
                    int user_id = Preferences.updateUserNumberNumber(v.getContext());
                    DataBaseHandler.newUser(v.getContext(), String.valueOf(user_id), getIntent().getStringExtra(NAME_EXTRA),
                            getIntent().getStringExtra(AGE_EXTRA), Preferences.getUserPhoto(v.getContext()));
                }
            }
        });
    }

    private void selectImage() {
        final CharSequence[] options = {getString(R.string.takePhoto), getString(R.string.ChooseFromGallery)};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.AddPhoto));

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals(getString(R.string.takePhoto))) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(Environment.getExternalStorageDirectory() + File.separator
                            + "AbsPitch_Trainer" + File.separator + "Profile_Pictures" + File.separator, "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                } else if (options[item].equals(getString(R.string.ChooseFromGallery))) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
            }
        })
                .setNegativeButton(getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }

    private void controls() {
        btNext = (Button) findViewById(R.id.btRegisterPhoto_Next);
        btPrevious = (Button) findViewById(R.id.btRegisterPhoto_Back);
        btUpload = (Button) findViewById(R.id.btRegisterPhoto_Upload);

        imageView = (ImageView) findViewById(R.id.imRegister_Photo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                picturePath = "";
                File f = new File(Environment.getExternalStorageDirectory() + File.separator
                        + "AbsPitch_Trainer" + File.separator + "Profile_Pictures");

                File dir = f;

                if(!dir.exists())
                    dir.mkdirs();

                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    imageView.setImageBitmap(bitmap);

                    path = Environment.getExternalStorageDirectory() + File.separator
                            + "AbsPitch_Trainer" + File.separator + "Profile_Pictures";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    path = file.getAbsolutePath();
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                btNext.setText("CONTINUAR");
            } else if (requestCode == 2) {
                path = "";
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                picturePath = c.getString(columnIndex);

                File original = new File(picturePath);

                String dir = Environment.getExternalStorageDirectory() + File.separator
                        + "AbsPitch_Trainer" + File.separator + "Profile_Pictures" + File.separator;

                File dire = new File(dir);
                if(!dire.exists())
                    dire.mkdirs();

                copyFile(original.getPath(), original.getName(), dir);

                File copy = new File(dir, original.getName());

                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(copy.getAbsolutePath()));
                Log.w("path of gallery image ", picturePath);
                imageView.setImageBitmap(thumbnail);
                btNext.setText("CONTINUAR");
            }
        }
    }

    private void copyFile(String inputPath, String inputFile, String outputPath) {

        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist
            File dir = new File (outputPath);
            if (!dir.exists())
            {
                dir.mkdirs();
            }


            in = new FileInputStream(inputPath);
            out = new FileOutputStream(outputPath + inputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file (You have now copied the file)
            out.flush();
            out.close();
            out = null;

        }  catch (FileNotFoundException fnfe1) {
            Log.e("tag", fnfe1.getMessage());
        }
        catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

    }
}
