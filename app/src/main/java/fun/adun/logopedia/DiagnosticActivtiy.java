package fun.adun.logopedia;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.Random;
import android.Manifest;

public class DiagnosticActivtiy extends AppCompatActivity {

    Random rand = new Random();
    View start;
    ImageButton next, startRecord;
    MediaPlayer mPlayer;
    MediaRecorder recorder;
    boolean f = true;
    private static String mFileName = null;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;
    int randomAudio;

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO , WRITE_EXTERNAL_STORAGE };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        randomAudio = rand.nextInt(50);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.diagnostic_activity);
        start = findViewById(R.id.audio_test);
        next = findViewById(R.id.next_arrow);
        startRecord = findViewById(R.id.micro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomAudio = rand.nextInt(50);
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
        view.setBackground(ContextCompat.getDrawable(DiagnosticActivtiy.this, R.drawable.mic_button_inactive)); 
        recorder.stop();
        recorder.reset();
        recorder.release();
        recorder = null;
    }


}