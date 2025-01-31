package com.ashara_mubasharah.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener {

    private Button sendButton, clearButton;
    private EditText nameEditText, messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // Toolbar সেটআপ
        setupToolbar("Feedback",true);

        // UI Components খুঁজে বের করা
        sendButton = findViewById(R.id.sendButtonId);
        clearButton = findViewById(R.id.clearButtonId);
        nameEditText = findViewById(R.id.nameEditTextId);
        messageEditText = findViewById(R.id.messageEditTextId);

        // Click Listener সেট করা
        sendButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try {
            String name = nameEditText.getText().toString().trim();
            String message = messageEditText.getText().toString().trim();

            if (view.getId() == R.id.sendButtonId) {
                if (name.isEmpty() || message.isEmpty()) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822"); // Only email clients will handle this
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"mehediinform@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from App");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Name: " + name + "\nMessage: " + message);

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send Feedback via..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "No email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
            else if (view.getId() == R.id.clearButtonId) {
                nameEditText.setText("");
                messageEditText.setText("");
                Toast.makeText(this, "Feedback cleared", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Back Button Press করলে অ্যাক্টিভিটি বন্ধ হবে
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
