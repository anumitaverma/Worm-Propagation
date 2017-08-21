import java.util.concurrent.ThreadLocalRandom;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class WormPropagation {
    static int omega = 100000;
    static int simulTime = 1400;
    static int simuN = 4;
    static int scancount = 2;
    int[][] It = new int[simuN][simulTime];
    int[] I = new int[simulTime];
    int infectedNum = 0;

    public enum status{
        Empty, Susceptible, Infectious
    }
    status [] nodestatus = new status[omega + 1];
    status [] priorstatus = new status[omega + 1];


    public void q1randomWormP(int n) {
        //Initialization for simulation
        int l = n;
        for (int i = 1; i <= omega; i++) {
            nodestatus[i] = status.Empty;
        }//selecting 1000 to be susceptible
        for (int j = 0; j < 100; j++) { //101
            for (int i = 1; i <= 10; i++) { //10
                nodestatus[i + j * 1000] = status.Susceptible;
            }
        }
        //Infect one randomly
        Random randomgen = new Random();
        int clus = randomgen.nextInt(100);
        int IP = randomgen.nextInt(10);
        int randomIP = clus * 1000 + IP;
        nodestatus[clus * 1000 + IP] = status.Infectious;
        infectedNum = 1;
        System.out.println(clus + IP);
        System.out.println(nodestatus[clus * 1000 + IP]);

        int rp1 = 0;
        int prevnum = 0;
        for (int time = 0; time < simulTime; time++) {
            // System.out.println(k + "kl");
            prevnum = infectedNum;
            for (int i = 1; i <= infectedNum; i++) {
                for (int scan = 0; scan < scancount; scan++) {
                    rp1 = randomgen.nextInt(omega);//generate randomIP from 1 to omega
                    if (nodestatus[rp1] == status.Susceptible) {
                        priorstatus[rp1] = status.Susceptible;
                        nodestatus[rp1] = status.Infectious;
                        infectedNum = infectedNum + 1;
                    }
                }
            }
            if (infectedNum > prevnum) {
                It[l][time] = infectedNum;
                System.out.println(l + "," + time + "," + It[l][time]);
            }
        }


    }

    public void print(String args[]) throws UnsupportedEncodingException {

        //Output File Path
        File file = new File("C:/Users/Jhabloo/Desktop/Worm1.txt");
        file.getParentFile().mkdirs();

        int simulTime = 1400;
        int simulN = 3;


        //Write Result to Output File
        try {
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            for (int i = 0; i < simulTime; i++) {
                String out = "";
                for (int j = 0; j < simulN; j++) {
                    out += (out.equals("")) ? It[j][i] : " " + It[j][i];
                }
                System.out.println(out);
                try {
                    writer.println(out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    //MAIN METHOD
    public static void main (String [] args) throws UnsupportedEncodingException{
        WormPropagation worm = new WormPropagation();
        worm.q1randomWormP(1);
        worm.q1randomWormP(2);
        worm.q1randomWormP(3);

    }
}


