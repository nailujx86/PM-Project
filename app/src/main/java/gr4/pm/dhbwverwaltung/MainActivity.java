package gr4.pm.dhbwverwaltung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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

    @Override
    protected void onResume() {
        super.onResume();
        initUserDatabase();
    }

    private void initUserDatabase() {
        if (!FileIO.fileExists("/dhbwverwaltung/users.json", false)) {
            ArrayList<User> users = new ArrayList<>();
            User user1 = new User(123, "test", "test@dhbw.de", Authentication.hashpw("test"),false);
            User user2 = new User(124, "julian", "email@email.de", Authentication.hashpw("passwort"),false);
            User user3 = new User(124, "moritz", "moritz@dhbw.de", Authentication.hashpw("pw"),false);
            users.add(user1);
            users.add(user2);
            users.add(user3);
            FileIO file = new FileIO("/dhbwverwaltung/users.json", false);
            file.writeToFile(User.toJSON(users));
            Log.i("dhbwverwaltung/userdb", "created");
        } else {//check if there is a user with loggedIn Attribute
            Log.i("dhbwverwaltung/userdb", "exists");
            FileIO db = new FileIO("/dhbwverwaltung/users.json", false);
            String jsondb = db.readFromFile();
            ArrayList<User> users = User.parse(jsondb);
            Data data = Data.getInstance();
            for(User user : users) {
                if(user.getStayLoggedIn().equals(true)) {
                    Log.i("dhbwverwaltung/login", "user " + user.getEmail() + " is already logged in");
                    data.setUser(user); //login User from database
                    break;
                }
            }
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent); //wechselt Ansicht zu activity_menu
            finish();
            overridePendingTransition(0,0);
        }
    }

    public void loginUser(View view){
        CheckBox stayLoggedIn = findViewById(R.id.checkBox_stayLoggedIn);

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
                if(stayLoggedIn.isChecked()){
                    user.setStayLoggedIn(true);
                }else user.setStayLoggedIn(false);
                break;
            }
        }

        if(Data.getInstance().getUser() == null)
            return;

        //db.writeToFile(User.toJSON(users));

        startActivity(intent); //wechselt Ansicht zu activity_menu
        finish();
        overridePendingTransition(0,0);
    }
}
