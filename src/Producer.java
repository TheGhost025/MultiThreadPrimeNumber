public class Producer implements  Runnable{
    private int number;
    private volatile boolean runable=true;
    private  int numofPrime=0;
    private Buffer queue;

    Callable callable;

    //constructor
    Producer(Buffer queue,int num){
        number=num;
        this.queue=queue;
    }

    @Override
    public void run() {
        while (runable){
            //if the buffer is full the producer must wait or lock
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

            //calcute the prime numbers
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
                    //notify the consumer
                    queue.notifyEmpty();
                }

                //to stop producer
                if(i==number){
                    stop();
                }
            }
        }
    }

    //total of prime numbers
    public int getNumofPrime(){
        return numofPrime;
    }

    public void onStop(Callable callable) {
        this.callable = callable;
    }

    //to stop producer
    public void stop(){
        runable=false;
        queue.notifyFull();
        this.callable.call();
    }
}
