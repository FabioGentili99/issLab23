package unibo.http;

import unibo.basicomm23.http.HTTPCommApache;
import unibo.common.IAppl1Core;
import unibo.common.IVrobotMoves;
import unibo.supports.VrobotHLMovesHTTPApache;
import unibo.basicomm23.utils.CommUtils;
public class Appl1Core implements IAppl1Core {
    protected boolean stopped = false;
    protected boolean started = false;
    protected IVrobotMoves vr;

    public Appl1Core() {
        stopped = false;
        configure();
    }

    protected void configure() {
        String URL = "localhost:8090/api/move";
        //URL potrebbe essere letto da un file di configurazione
        HTTPCommApache httpSupport = new HTTPCommApache(URL);
        vr = new VrobotHLMovesHTTPApache(httpSupport);
    }

    @Override
    public void start() throws Exception {
        if( ! started ){
            started = true;
            walkAtBoundary();
        }else CommUtils.outred("Application already started");
    }

    private void walkAtBoundary() throws Exception {
        for( int i=1; i<=4;i++) {
            walkBySteppingWithStop(i);
            vr.turnLeft();
        }
    }

    public void walkBySteppingWithStop(int n) throws Exception {
        boolean stepOk = true;
        while( stepOk  ) {
            stepOk =  vr.step(350);
            CommUtils.delay(300); //to show the steps better
            if( stopped ) {
                waitResume();
            }
        }
        return;
    }

    public synchronized void waitResume(){
        while( stopped ){
            try {
                wait();
            } catch (InterruptedException e) { CommUtils.outred("errore durante l'attesa di una resume"); }
        }
    }

    @Override
    public void stop() {
        stopped = true;
        }

    @Override
    public synchronized void resume() {
        stopped = false;
        notifyAll();  //riattiva waitResume
    }
}
