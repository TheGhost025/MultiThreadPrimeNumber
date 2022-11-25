import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private int size;

    private Object Full =new Object();
    private Object Empty =new Object();
    private final Queue<Integer> queue=new LinkedList<>();

    //constructor
    Buffer(int s){
        size=s;
    }

    //add to queue and work synchronize between producer and consumer
    public void add(int x){
        synchronized (queue){
            queue.add(x);
        }
    }

    //remove to queue and work synchronize between producer and consumer
    public int remove(){
        synchronized (queue){
            return  queue.poll();
        }
    }

    //check the queue is full or not
    public boolean isFull(){
        return queue.size()==size;
    }

    //check the queue is empty or not
    public boolean isEmpty(){
        return queue.size()==0;
    }

    //consumer wait
    public void WaitEmpty() throws InterruptedException {
        synchronized (Empty){
            Empty.wait();
        }
    }

    //producer wait
    public void WaitFull() throws InterruptedException {
        synchronized (Full){
            Full.wait();
        }
    }

    //producer notify
    public void notifyFull(){
        synchronized (Full){
            Full.notify();
        }
    }

    //consumer notify
    public void notifyEmpty(){
        synchronized (Empty){
            Empty.notify();
        }
    }

}
