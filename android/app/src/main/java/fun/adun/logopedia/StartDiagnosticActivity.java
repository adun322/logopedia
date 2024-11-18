package fun.adun.logopedia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StartDiagnosticActivity extends AppCompatActivity {
    ImageButton button;
    ImageView back;
    Button back_to_theory;
    View t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.start_diagtostic_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back_to_theory=findViewById(R.id.back_to_theory);
        button=findViewById(R.id.start_diag);
        back=findViewById(R.id.return_arrow);
        t = findViewById(R.id.start_diag_theader);

        back_to_theory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartDiagnosticActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });




        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartDiagnosticActivity.this, Level_blocks.class);
                startActivity(intent);
            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartDiagnosticActivity.this, Level_blocks.class);
                startActivity(intent);
            }
        });
        
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartDiagnosticActivity.this, Level_blocks.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartDiagnosticActivity.this, DiagnosticActivtiy.class);
                startActivity(intent);
            }
        });


    }
}