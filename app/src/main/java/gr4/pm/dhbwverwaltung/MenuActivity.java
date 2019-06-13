package gr4.pm.dhbwverwaltung;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;

        import javax.security.auth.login.LoginException;

public class MenuActivity extends MainActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void logoffUser(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent); //wechselt Ansicht zu activity_main
        finish();
        overridePendingTransition(0,0);
    }

    public void openNoteView(View view) {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent); //wechselt Ansicht zu activity_main
        finish();
        overridePendingTransition(0,0);
    }

    public void openProfView(View view) {
        Intent intent = new Intent(this, ProfActivity.class);
        startActivity(intent); //wechselt Ansicht zu activity_main
        finish();
        overridePendingTransition(0,0);
    }

    public void openSecretaryView(View view) {
        Intent intent = new Intent(this, SecretaryActivity.class);
        startActivity(intent); //wechselt Ansicht zu activity_main
        finish();
        overridePendingTransition(0,0);
    }
}
