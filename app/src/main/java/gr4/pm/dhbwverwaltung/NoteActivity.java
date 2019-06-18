package gr4.pm.dhbwverwaltung;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import java.util.ArrayList;

import gr4.pm.dhbwverwaltung.data.Data;
import gr4.pm.dhbwverwaltung.io.FileIO;
import gr4.pm.dhbwverwaltung.objects.Note;

public class NoteActivity extends MainActivity {

    ArrayList<Note> notes;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        if (FileIO.fileExists("/dhbwverwaltung/notes.json", false)) {
            FileIO db = new FileIO("/dhbwverwaltung/notes.json", false);
            String jsondb = db.readFromFile();
            notes = Note.parse(jsondb);
        } else {
            notes = new ArrayList<>();
            notes.add(new Note(1, Data.getInstance().getUser().getId(), "Keine Notizen gefunden"));
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

    }
}
