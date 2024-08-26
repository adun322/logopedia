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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainMenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.main_menu_activity);
        RecyclerView recyclerView = findViewById(R.id.saved_sounds);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        SavedSound[] pon = new SavedSound[]{new SavedSound("1", "aeweadsad", false),
                new SavedSound("2", "aeweadsad", true),
                new SavedSound("3", "aeweadsad", true),
                new SavedSound("4", "aeweadsad", true)};
        ///////////////////////////// SAVED SOUNDS
        recyclerView.setAdapter(new SavedSoundsAdapter(pon, this));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button=findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, Level_blocks.class);
                startActivity(intent);
            }
        });
    }






    /////////////////
}