package com.example.kennck.mirroring;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

public class ViewOnSlave extends AppCompatActivity {
    Button back;
    TextView code;
    VideoView videoView;
    ProgressDialog progressDialog;
    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_on_slave);
        back = (Button) findViewById(R.id.vsBack);
        code = (TextView) findViewById(R.id.vsCode);
        Intent intent = getIntent();
        code.setText(intent.getStringExtra("code"));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent account = new Intent(ViewOnSlave.this, Account.class);
                startActivity(account);
            }
        });
        videoView = (VideoView) findViewById(R.id.videoViewSlave);
        progressDialog = new ProgressDialog(ViewOnSlave.this);
        progressDialog.setTitle("Screen Mirroring");
        progressDialog.setMessage("Bufferring....");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();

        mediaController = new MediaController(ViewOnSlave.this);
        mediaController .setAnchorView(videoView);
        String url = intent.getStringExtra("url");
        Uri uri = Uri.parse(url);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressDialog.dismiss();
                videoView.start();
            }
        });
    }
}
