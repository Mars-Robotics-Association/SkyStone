package org.firstinspires.ftc.teamcode;

public class LoopThread implements Runnable {
    private Thread t;
    NavigationManager nav;
    Attachment [] attachments;
    boolean run = true;

    LoopThread( NavigationManager navigationManagerToLoop, Attachment[] attachmentsToLoop)
    {
        nav = navigationManagerToLoop;
        attachments = attachmentsToLoop;
    }

    public void run()
    {
        //LOOP
        while(run)
        {
            //loop navigation manager
            nav.Loop();

            //loop all the attachments
            for (Attachment a: attachments)
            {
                a.Loop();
            }


            //System.out.println("Gogogogogogogo");
        }
    }

    public void start () {
        System.out.println("Starting " +  "LoopThread" );
        if (t == null) {
            t = new Thread (this, "LoopThread");
            t.start ();
        }
    }

    public void UpdateValues(boolean isRunning)
    {
        run = isRunning;
    }
}

