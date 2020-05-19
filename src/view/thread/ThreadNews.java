package view.thread;

public class ThreadNews extends Thread {

    @Override
    public void run() {
        //Tache que le thread doit effectuer
        //Début avec la méthode start()

        while(true){
            try{
                Thread.sleep(1000);
                //Get le news random

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
