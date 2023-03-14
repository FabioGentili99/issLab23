package unibo.console;
import unibo.basicomm23.utils.CommUtils;
import unibo.http.Appl1Core;

public class CmdConsoleSimulator {
    private Appl1Core appl;

    public CmdConsoleSimulator( Appl1Core appl ){
        this.appl = appl;
    }

    public void activate(   )   {
        try {
            cmdConsoleSimul.start();
            appl.start();
        } catch (Exception e) { CommUtils.outred("errore avvio applicazione da console"); }
    }
    private  Thread cmdConsoleSimul = new Thread("cmdConsole") {
        public void run() {
            for (int i = 1; i <= 5; i++) {
                CommUtils.delay(3000);
                CommUtils.outmagenta("cmdConsoleSimul send STOP " + i);
                appl.stop();
                CommUtils.delay(1500);
                appl.resume();
            }
        }
    };
}
