package unibo.http;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import unibo.basicomm23.utils.CommUtils;
import unibo.common.VrobotMsgs;
import unibo.common.*;
import unibo.basicomm23.http.*;
import unibo.supports.VrobotHLMovesHTTPApache;
import unibo.model.RoomModel;
import java.net.URI;
public class Appl1HTTPSprint1 {
    private IVrobotMoves vr;
    private int[] boundarySteps = {0, 0, 0, 0}; //For testing

    public Appl1HTTPSprint1() {
        configure();
    }

    protected void configure() {
        String URL = "localhost:8090/api/move";
        //URL potrebbe essere letto da un file di configurazione
        HTTPCommApache httpSupport = new HTTPCommApache(URL);
        vr = new VrobotHLMovesHTTPApache(httpSupport);
    }

    public static void main(String[] args) {
        Appl1HTTPSprint1 appl = new Appl1HTTPSprint1();
        try {
            appl.walkAtBoundary();  //ENTRY POINT
        } catch (Exception e) { System.out.println("error");}
    }

    public void walkAtBoundary() throws Exception {
        for (int i = 0; i <= 3; i++) {
            walkAheadUntilCollision(i);
            walkByStepping();
            vr.turnLeft();
        }
    }

    private void walkAheadUntilCollision(int n) throws Exception {
        try {
            vr.forward(3500);
        } catch (CollisionException e) {
            return;
        }
        throw new Exception("no collision");
    }
    public void walkByStepping() throws Exception {
        boolean goon = true;
        while( goon ) {
            goon =  vr.step(350);
            CommUtils.delay(300); //to show the steps better
        }
    }

    public int[] getBoundarySteps(){  //for testig
        return boundarySteps;
    }

    public void walkByStepping(int n) throws Exception {
        boolean goon = true;
        while (goon) {
            goon = vr.step(300);
            if (goon) boundarySteps[n]++;
            CommUtils.delay(300); //to show the steps better
        }
    }

    public boolean checkRobotAtHome() {
        try {
            vr.turnRight();
            boolean res = vr.step(100);
            if (res) return false;
            vr.turnRight();
            res = vr.step(100);
            if (res) return false;
            vr.turnLeft();
            vr.turnLeft();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
