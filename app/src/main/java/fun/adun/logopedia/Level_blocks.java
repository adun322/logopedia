package fun.adun.logopedia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Level_blocks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_level_blocks);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button1 = findViewById(R.id.begin1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Level_blocks.this, StartDiagnosticActivity.class);
                intent.putExtra("block", "1");
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.begin2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Level_blocks.this, StartDiagnosticActivity.class);
                intent.putExtra("block", "2");
                startActivity(intent);
            }
        });

        Button button3 = findViewById(R.id.begin3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Level_blocks.this, StartDiagnosticActivity.class);
                intent.putExtra("block", "3");
                startActivity(intent);
            }
        });

        Button button4 = findViewById(R.id.begin4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Level_blocks.this, StartDiagnosticActivity.class);
                intent.putExtra("block", "4");
                startActivity(intent);
            }
        });
    }
}   