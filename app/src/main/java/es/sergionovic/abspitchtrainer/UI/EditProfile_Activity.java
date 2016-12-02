package es.sergionovic.abspitchtrainer.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class EditProfile_Activity extends AppCompatActivity {

    Toolbar toolbar;

    private static EditText etName, etAge;
    private static TextInputLayout tiName, tiAge;

    private static ImageView imageView;

    static String path = "", picturePath = "";

    private static Button btUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        controls();

        events();
    }

    private void events() {
        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void controls() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btUpload = (Button) findViewById(R.id.btRegisterPhoto_Upload);

        etName = (EditText) findViewById(R.id.etEdit_Name);
        etAge = (EditText) findViewById(R.id.etEdit_Age);

        if (!Preferences.getUserName(this).isEmpty())
            etName.setText(Preferences.getUserName(this));
        if (!Preferences.getUserAge(this).isEmpty())
            etAge.setText(Preferences.getUserAge(this));

        tiName = (TextInputLayout) findViewById(R.id.textinput_name);
        tiAge = (TextInputLayout) findViewById(R.id.textinput_age);

        imageView = (ImageView) findViewById(R.id.editProfile_Image);

        if (!Preferences.getUserPhoto(this).isEmpty()) {
            File copy = new File(Preferences.getUserPhoto(this));
            Bitmap thumbnail = (BitmapFactory.decodeFile(copy.getAbsolutePath()));
            imageView.setImageBitmap(thumbnail);
        }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                picturePath = "";
                File f = new File(Environment.getExternalStorageDirectory() + File.separator
                        + "AbsPitch_Trainer" + File.separator + "Profile_Pictures");

                File dir = f;

                if (!dir.exists())
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
                if (!dire.exists())
                    dire.mkdirs();

                copyFile(original.getPath(), original.getName(), dir);

                File copy = new File(dir, original.getName());

                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(copy.getAbsolutePath()));
                Log.w("path of gallery image ", picturePath);
                imageView.setImageBitmap(thumbnail);
            }
        }
    }

    private void copyFile(String inputPath, String inputFile, String outputPath) {

        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist
            File dir = new File(outputPath);
            if (!dir.exists()) {
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

        } catch (FileNotFoundException fnfe1) {
            Log.e("tag", fnfe1.getMessage());
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

    }

    private boolean checkData() {
        boolean retur = false;

        String name, age;
        name = etName.getText().toString();
        age = etAge.getText().toString();

        if (!name.isEmpty() && !age.isEmpty()) {
            retur = true;
            tiAge.setErrorEnabled(false);
            tiName.setErrorEnabled(false);
        } else if (age.isEmpty() && !name.isEmpty()) {
            retur = false;
            tiAge.setErrorEnabled(true);
            tiName.setErrorEnabled(false);
            tiAge.setError("Introduce tu edad");
        } else if (!age.isEmpty() && name.isEmpty()) {
            retur = false;
            tiAge.setErrorEnabled(false);
            tiName.setErrorEnabled(true);
            tiName.setError("Introduce tu nombre");
        } else if (age.isEmpty() && name.isEmpty()) {
            retur = false;
            Snackbar.make(findViewById(R.id.edit_profile_main_content),
                    "Por favor, rellena los datos", Snackbar.LENGTH_SHORT)
                    .show(); // Dont forget to show!
            tiAge.setErrorEnabled(false);
            tiName.setErrorEnabled(false);
        }

        return retur;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            if (checkData()) {
                saveNewData();
                Snackbar.make(findViewById(R.id.edit_profile_main_content),
                        "Datos Guardados", Snackbar.LENGTH_SHORT)
                        .show(); // Dont forget to show!
                finish();
            }
        }
        if (id == R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveNewData() {
        Preferences.setUserName(this, etName.getText().toString());
        Preferences.setUserAge(this, etAge.getText().toString());

        if (path.isEmpty() && !picturePath.isEmpty()) {
            Preferences.setUserPhoto(this, picturePath);
            DataBaseHandler.updateUserPhoto(this, String.valueOf(Preferences.userNumber(this)), etName.getText().toString(),
                    etAge.getText().toString(), picturePath);
        }


        if (picturePath.isEmpty() && !path.isEmpty()) {
            Preferences.setUserPhoto(this, path);
            DataBaseHandler.updateUserPhoto(this, String.valueOf(Preferences.userNumber(this)), etName.getText().toString(),
                    etAge.getText().toString(), path);
        }

        DataBaseHandler.updateUser(this, String.valueOf(Preferences.userNumber(this)), etName.getText().toString(),
                etAge.getText().toString());
    }
}
