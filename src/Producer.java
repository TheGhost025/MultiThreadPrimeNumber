public class Producer implements  Runnable{
    private int number;
    private volatile boolean runable=true;
    private  int numofPrime=0;
    private Buffer queue;
    Producer(Buffer queue,int num){
        number=num;
        this.queue=queue;
    }

    @Override
    public void run() {
        while (runable){
            if(queue.isFull()){
                try {
                    queue.WaitFull();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                    break;
                }
            }
            if(!runable){
                break;
            }
            for(int i=1;i<=number;i++){
                int count =0;
                for(int j=2;j<=i/2;j++){
                    if(i%j==0){
                        count++;
                    }
                }
                if(count==0){
                    numofPrime++;
                    queue.add(i);
                    queue.notifyEmpty();
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                if(i==number){
                    System.out.println("producer end");
                    queue.notifyEmpty();
                    stop();
                }
            }
        }
    }

    public int getNumofPrime(){
        return numofPrime;
    }

    public void stop(){
        runable=false;
        queue.notifyFull();
    }
}
