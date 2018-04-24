package com.example.a161handlerprogressbar;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
               progressBar.incrementProgressBy(msg.arg1);
               if(msg.what==1) {
                   progressBar.setVisibility(View.GONE);
                   Toast.makeText(MainActivity.this, "下载成功！", Toast.LENGTH_SHORT).show();
               }
            }
        };
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        int progress=0;
                        while(progress<=100) {
                            Message message = new Message();
                            message.arg1 = (int)(Math.random() * 10);
                            message.what=0;
                            progress += message.arg1;
                            message.what=(progress < 100) ? 0:1;
                            handler.sendMessage(message);
                            try {
                                Thread.currentThread().sleep((long) (Math.random() * 500));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });


        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask().execute();
            }
        });
    }
    class DownloadTask extends AsyncTask<Void,Integer,Boolean>{

        private ProgressBar progressBar2;

        @Override
        protected void onPreExecute() {
            progressBar2 = findViewById(R.id.progressBar2);
            progressBar2.setVisibility(View.VISIBLE);
        }

        @Override//子线程操作，这里面封装了一个runnable。返回值Boolean(asynkTask的第三个泛型参数设置的Boolean)
        protected Boolean doInBackground(Void... voids) {
            int progress=0;
            while(progress<100) {
                progress += (int)(Math.random() * 10);
                if(progress>100)
                    progress=100;
                publishProgress(progress);
                try {
                    Thread.currentThread().sleep((long) (Math.random() * 500));
                } catch (InterruptedException e) {
                    return false;
                }
            }
            return true;//代表执行完任务，告诉asyntask调用onPostExecute
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar2.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            progressBar2.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
        }
    }
}

