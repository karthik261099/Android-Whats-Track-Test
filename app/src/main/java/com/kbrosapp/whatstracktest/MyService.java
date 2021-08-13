package com.kbrosapp.whatstracktest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    private MediaPlayer player;

    Handler handler;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        
        createNotificationChannel();

        Intent intent1=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent1,0);

        Notification notification=new NotificationCompat.Builder(this,"ChannelId1")
                .setContentTitle("My App tutorial")
                .setContentText("App is running")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).build();

        //Ringtone
//        RingtoneManager mRing = new RingtoneManager(this);
//        int mNumberOfRingtones = mRing.getCursor().getCount();
//        Uri mRingToneUri = mRing.getRingtoneUri((int) (Math.random() * mNumberOfRingtones));
//        player = new MediaPlayer();
//        try {
//            player.setDataSource(getApplication(), mRingToneUri);
//            player.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        player.setLooping(true);
//        player.start();

//        ScheduledExecutorService scheduleTaskExecutor = Executors.newScheduledThreadPool(5);
//        // This schedule a runnable task every 2 minutes
//        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                Toast.makeText(MyService.this, "Hello World!", Toast.LENGTH_SHORT).show();
//            }
//        }, 0, 10, TimeUnit.SECONDS);

//        handler = new Handler();
//        final int delay = 5000; // 1000 milliseconds == 1 second
//
//        final int[] i = {0};
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                Toast.makeText(MyService.this, "hello"+ i[0]++, Toast.LENGTH_SHORT).show();
//                handler.postDelayed(this, delay);
//            }
//        }, delay);


        WebView webView =  new WebView(getApplicationContext());

        webView.loadUrl("https://web.whatsapp.com/send?phone=918655803448");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView webView, String url) {
                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                //super.onPageFinished(webView, url);
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        webView.loadUrl("javascript:function x(){document.querySelector(\"._1WKYb\").textContent=\"sad\";var canvas = document.querySelector(\"._1yHR2\").children[2];\n" +
//                                "var img    = canvas.toDataURL(\"image/png\");\n" +
//                                "document.write('<img src=\"'+img+'\"/><a href=\"'+img+'\" download=\"AwesomeImage.png\">sadsa</a>');}; x();");

                    }
                }, 20000);

            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Win64; x64; rv:46.0) Gecko/20100101 Firefox/68.0");
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setNeedInitialFocus(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //webView.getSettings().setBlockNetworkLoads(true);
        webView.getSettings().setBlockNetworkImage(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setInitialScale(100);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        handler = new Handler();
        final int delay = 2000; // 1000 milliseconds == 1 second

        handler.postDelayed(new Runnable() {
            public void run() {

                webView.evaluateJavascript("javascript:function x(){var isOnline=document.querySelector(\"._3Id9P\").textContent;return isOnline;}; x();", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String isOnline) {
                        if(isOnline!=null && !isOnline.equals("null")){
                            Toast.makeText(getApplicationContext(), isOnline, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                handler.postDelayed(this, delay);
            }
        }, delay);

        startForeground(1,notification);
        return START_STICKY;
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel =new NotificationChannel("ChannelId1","Foreground notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //stopping the player when service is destroyed
        stopForeground(true);
        stopSelf();
        //player.stop();
        handler.removeCallbacksAndMessages(null);
    }

}
