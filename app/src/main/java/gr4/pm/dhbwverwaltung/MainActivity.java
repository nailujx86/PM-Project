package gr4.pm.dhbwverwaltung;

import android.content.Intent;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import gr4.pm.dhbwverwaltung.auth.Authentication;
import gr4.pm.dhbwverwaltung.data.Data;
import gr4.pm.dhbwverwaltung.io.FileIO;
import gr4.pm.dhbwverwaltung.objects.User;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUserDatabase();

        setContentView(R.layout.activity_login);

        /* /// TESTS ///
        User user1 = new User(123, "terst", "test@test.de", Authentication.hashpw("abc"));
        User user2 = new User(124, "test2", "test2@test.de", Authentication.hashpw("abdef"));
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        Log.i("json", User.toJSON(users));
        ArrayList<User> users2 = User.parse(User.toJSON(users));
        Log.i("json2", User.toJSON(users2));
        Log.i("json3", Authentication.checkpw("abdef", user2.getPasswhash()) ? "true": "false");
        */
    }

    private void initUserDatabase() {
        if (!FileIO.fileExists("/dhbwverwaltung/users.json", false)) {
            ArrayList<User> users = new ArrayList<>();
            User user1 = new User(123, "test", "test@dhbw.de", Authentication.hashpw("test"));
            User user2 = new User(123, "julian", "email@email.de", Authentication.hashpw("passwort"));
            users.add(user1);
            users.add(user2);
            FileIO file = new FileIO("/dhbwverwaltung/users.json", false);
            file.writeToFile(User.toJSON(users));
            Log.i("dhbwverwaltung/userdb", "created");
        } else {
            Log.i("dhbwverwaltung/userdb", "exists");
        }
    }

    public void loginUser(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        EditText editTextUserName = (EditText) findViewById(R.id.editText2);
        String username = editTextUserName.getText().toString(); // speichert username in
        EditText editTextPassword = (EditText) findViewById(R.id.editText);
        String password = editTextPassword.getText().toString();

        FileIO db = new FileIO("/dhbwverwaltung/users.json", false);
        String jsondb = db.readFromFile();
        ArrayList<User> users = User.parse(jsondb);

        for(User user : users) {
            if(user.getEmail().equals(username)) {
                Log.i("dhbwverwaltung/login", "found user " + user.getEmail());
                Authentication.login(user, password);
                break;
            }
        }

        if(Data.getInstance().getUser() == null)
            return;

        startActivity(intent); //wechselt Ansicht zu activity_menu
        finish();
        overridePendingTransition(0,0);
    }
}
