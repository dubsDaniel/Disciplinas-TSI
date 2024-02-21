package com.example.p2_daniellcabrera_2096072;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServicePUT extends Service { //Infelizmente não consegui assimilar o conteúdo de Services à tempo
    public ServicePUT() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}