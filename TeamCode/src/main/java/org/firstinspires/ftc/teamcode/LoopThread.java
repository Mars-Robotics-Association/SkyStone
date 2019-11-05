package org.firstinspires.ftc.teamcode;

public class LoopThread implements Runnable {
    private Thread t;
    NavigationManager n;

    LoopThread( NavigationManager setNavigationManager)
    {
        n = setNavigationManager;
    }

    public void run()
    {
        //LOOP
        while(true)
        {
            n.Loop();
            System.out.println("Gogogogogogogo");
        }
    }

    public void start () {
        System.out.println("Starting " +  "LoopThread" );
        if (t == null) {
            t = new Thread (this, "LoopThread");
            t.start ();
        }
    }
}

