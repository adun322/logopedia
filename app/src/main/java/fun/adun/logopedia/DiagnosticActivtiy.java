package fun.adun.logopedia;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import android.Manifest;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class DiagnosticActivtiy extends AppCompatActivity {

    Random rand = new Random();
    View start, training_header;
    ImageButton back, next, startRecord;
    ImageView back2;
    MediaPlayer mPlayer;
    MediaRecorder recorder;
    boolean f = true;
    private static String mFileName = null;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO, WRITE_EXTERNAL_STORAGE};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted) finish();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.diagnostic_activity);
        start = findViewById(R.id.audio_test);
        next = findViewById(R.id.next_arrow);
        back = findViewById(R.id.arrow_back);
        back2 = findViewById(R.id.return_arrow4);
        training_header = findViewById(R.id.diag_theader);

        startRecord = findViewById(R.id.micro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ///// ОТДЕЛ ВОСПРОИЗВЕДЕНИЯ ЗВУКОВ! ЗВУКИ ЕЩЁ НЕ ВЫБРАНЫ, НО ГОТОВЫ К ВОСПРОИЗВЕДЕНИЮ

//        Button myButton = findViewById(R.id.audio_test);
//        myButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(DiagnosticActivtiy.this, "Кнопка нажата!", Toast.LENGTH_SHORT).show();
//            }
//        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DiagnosticActivtiy.this, "Этот раздел ещё в разработке", Toast.LENGTH_SHORT).show();
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiagnosticActivtiy.this, StartDiagnosticActivity.class);
                startActivity(intent);
            }
        });

        training_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiagnosticActivtiy.this, StartDiagnosticActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiagnosticActivtiy.this, StartDiagnosticActivity.class);
                startActivity(intent);
            }
        });


        startRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (f) {
                    startRecording(view);
                } else {
                    stopRecording(view);
                }
                f = !f;
            }

        });

    }

    @SuppressLint("RestrictedApi")
    private void startRecording(View view) {
        view.setBackground(ContextCompat.getDrawable(DiagnosticActivtiy.this, R.drawable.mic_button_active));

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mFileName = getExternalCacheDir().getAbsolutePath();
        mFileName += "/audiorecordtest.3gp";
        recorder.setOutputFile(mFileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recorder.start();
    }

    private void stopRecording(View view) {
        recorder.stop();
        recorder.reset();
        recorder.release();
        recorder = null;
        //  sending
        Boolean f = true;

        /// ЗДЕСЬ БУДЕТ РЕАЛИЗОВАНО ОТПРАВЛЕНИЕ ЗАПИСАННОГО ФАЙЛА НА СЕРВЕР - ИНТЕРФЕЙС ДЛ ЭТОГО ГОТОВ


        TextView pon;
        pon = findViewById(R.id.result_text);
        if (f) {
            pon.setText("Отлично!");
            pon.setTextSize(16);
            String color = getString(Integer.parseInt(String.valueOf(R.color.teal_200)));
            pon.setTextColor(Color.parseColor(color));

        } else {
            view.setBackground(ContextCompat.getDrawable(DiagnosticActivtiy.this, R.drawable.mic_button_incorrect));
            pon.setText("Ошибка. Попробуйте произнести ещё раз.");

        }


    }



}