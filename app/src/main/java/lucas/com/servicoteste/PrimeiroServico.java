package lucas.com.servicoteste;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class PrimeiroServico extends Service{

    public String channelID;

    @Override
    public void onCreate() {
        //super.onCreate();

        channelID = getString(R.string.Notificacao);
        createNotificationChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int retval = super.onStartCommand(intent, flags, startId);

        if (createNotificationChannel()==1)
            notificacao();

    return retval;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //Uma Conexao entre servicos e aplicativos
        return null;
    }

    public int createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(channelID, "Foreground Service Channel", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
        return START_STICKY;
    }

    public int notificacao(){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Essa ligação está gravada")
                .setContentText("Você pode ficar tranquilo ;)")
                .setPriority(NotificationCompat.PRIORITY_MAX);

        Notification n = builder.build();

        startForeground(1, n);

        return  START_STICKY;
    }

    public void mostrar(){
        Toast.makeText(this, "ola, seja bem bvindo", Toast.LENGTH_LONG);
    }





}
