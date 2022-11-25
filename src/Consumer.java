import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Consumer implements Runnable{
    private Buffer queue;

    String outFile;

    private int count =1;
    private long begin;
    private long end;
    private int max;
    private boolean runable =true;

    //constructor
    Consumer(Buffer queue, String outFile){
        this.queue=queue;
        this.outFile = outFile;
    }

    @Override
    public void run() {
        while (runable){
            //to begin a time to calculate the duration time
            if(count==1){
                begin = System.currentTimeMillis();
            }

            //check the buffer is empty or not if is empty consumer must wait or lock
            if(queue.isEmpty()){
                try {
                    queue.WaitEmpty();
                    }
                catch (InterruptedException e){
                    e.printStackTrace();
                    }
                }

            if(!runable){
                break;
            }

            int x = 0;
            if(!queue.isEmpty()){
                x=queue.remove();
            }

            //to stop a consumer and capture the end time to calculate the duration time
            if(getmax()>=x){
                end = System.currentTimeMillis();
                stop();
            }
            max=x;
            FileWriter File=null;
            BufferedWriter buffer=null;
            PrintWriter print=null;
            try {
                File=new FileWriter(this.outFile,true);
                buffer=new BufferedWriter(File);
                print=new PrintWriter(buffer);
                print.println(x);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally {
                try {
                    print.close();
                    buffer.close();
                    File.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            //notify producer
            queue.notifyFull();
        }
    }

    //get a max value
    public int getmax(){
        return max;
    }

    //stop the consumer
    public void stop(){
        runable=false;
        queue.notifyEmpty();
    }
}
