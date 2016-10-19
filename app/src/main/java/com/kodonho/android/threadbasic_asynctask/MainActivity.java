package com.kodonho.android.threadbasic_asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progress;
    TextView percent;
    Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = (ProgressBar) findViewById(R.id.progressBar);
        //progress.setMax(100);
        progress.setProgress(0);
        percent = (TextView) findViewById(R.id.textView);
        btnDownload = (Button) findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask().execute(100);
            }
        });
    }
                                        //  1     2     3
    class DownloadTask extends AsyncTask<Integer, Integer, String> {
        // 1. doInBackground 의 parameter type
        // 2. onProgressUpdate 의      "
        // 3. onPostExecute 의         "
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... params) {
            int max = params[0];
            try {
                for (int i = 0; i <= max; i++) {
                    publishProgress(i);
                    Thread.sleep(100);
                }
            }catch(Exception e){
            }
            return "Finish";
        }

        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(aVoid);
            Log.i("DownLoadTask","msg = "+result);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progress.setProgress(values[0]);
            percent.setText(values[0]+" %");
            //super.onProgressUpdate(values);
        }
    }
}
