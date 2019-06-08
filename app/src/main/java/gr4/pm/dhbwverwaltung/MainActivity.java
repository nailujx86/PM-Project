package gr4.pm.dhbwverwaltung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

import gr4.pm.dhbwverwaltung.auth.Authentication;
import gr4.pm.dhbwverwaltung.objects.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
