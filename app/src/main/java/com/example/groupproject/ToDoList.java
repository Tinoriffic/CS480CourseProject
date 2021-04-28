package com.example.groupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.groupproject.R;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Locale;

public class ToDoList extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private ListView toDoList;
    private EditText edtTxtItem;
    private int itemIndex;
    private String entryItem;
    private ArrayList<String> items = new ArrayList<>();
    private ArrayAdapter<String> itemsAdapter;

    private FileOutputStream outStream;
    private OutputStreamWriter outWriter;
    private Boolean saveExists = false;

    private TextToSpeech speaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        toDoList = findViewById(R.id.toDoList);
        edtTxtItem = findViewById(R.id.edtTxtItem);

        // Check to see if save file "list.txt" exists
        // If list.txt exists, read the contents of the file and add it to the items list
        try {
            FileInputStream saveFile = openFileInput("list.txt");
            saveExists = true;
            Log.d(LOG_TAG, "Save file exists!");
            InputStreamReader inStream = new InputStreamReader(saveFile);
            try (BufferedReader reader = new BufferedReader(inStream)) {
                String line = reader.readLine();
                while (line != null) {
                    Log.d(LOG_TAG, "Input reads: " + line);
                    // Parses the #'s at the beginning of the line, adds to items array
                    line = line.substring(3);
                    items.add(line);
                    line = reader.readLine();
                }

            } catch (IOException e) {
                Log.d(LOG_TAG, "Error on opening file");
            }

        } catch (IOException e) {
            Log.d(LOG_TAG, "Save file doesn't exist");
            saveExists = false;
        }

        if (!saveExists) {
            // Add items to an array list if save file doesn't exist
            items.add("Meal prep");
            items.add("Submit assignment");
            items.add("Prepare for interview");
        }

        // Adds the number sequence to each element of the array list
        for (int i = 0; i < items.size(); i++) {
            int itemPosition = items.indexOf(items.get(i)) + 1;
            String placeholder = itemPosition + ". " + items.get(i);
            items.set(i, placeholder);
        }

        // Array Adapter
        itemsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                items
        );

        toDoList.setAdapter(itemsAdapter);

        // Makes to the to do list clickable
        toDoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String parseString = items.get(position);

                // Tracks the index of the current item selected
                itemIndex = items.indexOf(parseString);
                // Toast.makeText(MainActivity.this, "ItemIndex: " + itemIndex, Toast.LENGTH_SHORT).show();

                // Removes the number of the list at the beginning of the task
                parseString = parseString.substring(3);
                edtTxtItem.setText(parseString);
            }
        });

        // Initialize TTS
        speaker = new TextToSpeech(this, this);

    }

    // This method saves the contents of the list into a text file
    public void saveFile() {
        try {
            String file = "list.txt";
            outStream = openFileOutput(file, MODE_PRIVATE);
            outWriter = new OutputStreamWriter(outStream);

            // Loop that writes each line from the items array (to do list)
            for (int i = 0; i < items.size(); i++) {
                outWriter.write(items.get(i));
                outWriter.write("\n");
            }
            outWriter.close();
            outStream.flush();
            outStream.close();
            Log.d(LOG_TAG, "File saved!");
            saveExists = true;


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void speak(String output) {
        speaker.speak(output, TextToSpeech.QUEUE_FLUSH, null, "Id 0");
    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = speaker.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                Log.d(LOG_TAG, "Language is unavailable.");
            else
                Log.d(LOG_TAG, "TTS successful.");
        }
        else
            Log.d(LOG_TAG, "TTS failed.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addItem:
                entryItem = edtTxtItem.getText().toString();
                String tts_add = entryItem;

                // Updates the order of the sequence
                int itemPosition = items.size() + 1;
                entryItem = itemPosition + ". " + entryItem;

                // Adds the item to array list and notifies adapter
                items.add(entryItem);
                edtTxtItem.setText("");

                // Speaker (TTS) outputs the entry item and says its been added.
                if (speaker.isSpeaking())
                    speaker.stop();
                else
                    speak(tts_add + " has been added.");

                return true;

            case R.id.deleteItem:
                String tts_delete = items.get(itemIndex);
                tts_delete = tts_delete.substring(3);

                items.remove(itemIndex);
                // Updates the order sequence of the numbered list after deleting an entry
                for (int i = itemIndex; i < items.size(); i++) {
                    itemPosition = i + 1;
                    String parse = items.get(i);
                    parse = parse.substring(3);
                    items.set(i, itemPosition + ". " + parse);
                }

                // Notifies the adapter
                itemsAdapter.notifyDataSetChanged();
                edtTxtItem.setText("");

                // Speaker (TTS) outputs the entry item and says its been added.
                if (speaker.isSpeaking())
                    speaker.stop();
                else
                    speak(tts_delete + " has been deleted.");
                return true;

            case R.id.updateItem:
                entryItem = edtTxtItem.getText().toString();

                // Adds the correct number to the beginning of the entry
                itemPosition = itemIndex + 1;
                entryItem = itemPosition + ". " + entryItem;

                // Updates the item in the array and notifies the adapter
                items.set(itemIndex, entryItem);
                itemsAdapter.notifyDataSetChanged();
                edtTxtItem.setText("");
                return true;

            case R.id.saveApp:
                saveFile();
                return true;

            case R.id.exitApp:
                saveFile();
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onDestroy() {

        // shut down TTS engine
        if(speaker != null){
            speaker.stop();
            speaker.shutdown();
        }
        super.onDestroy();
    }

}