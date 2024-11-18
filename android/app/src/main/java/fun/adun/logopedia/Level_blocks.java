package fun.adun.logopedia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Level_blocks extends AppCompatActivity {
    View pon;
    ImageView pon2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_level_blocks);

        pon = findViewById(R.id.level_blocks_h);
        pon2 = findViewById(R.id.return_arrow5);

        pon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Level_blocks.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });

        pon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Level_blocks.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });

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
                Toast.makeText(Level_blocks.this, "Этот раздел ещё в разработке", Toast.LENGTH_SHORT).show();
            }
        });

        Button button3 = findViewById(R.id.begin3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Level_blocks.this, "Этот раздел ещё в разработке", Toast.LENGTH_SHORT).show();
            }
        });

        Button button4 = findViewById(R.id.begin4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Level_blocks.this, "Этот раздел ещё в разработке", Toast.LENGTH_SHORT).show();
            }
        });
    }
}   