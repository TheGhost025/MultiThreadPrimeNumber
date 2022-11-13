import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private int size;

    private Object Full =new Object();
    private Object Empty =new Object();
    private final Queue<Integer> queue=new LinkedList<>();
    Buffer(int s){
        size=s;
    }

    public void add(int x){
        synchronized (queue){
            queue.add(x);
        }
    }

    public int remove(){
        synchronized (queue){
            return  queue.poll();
        }
    }

    public boolean isFull(){
        return queue.size()==size;
    }

    public boolean isEmpty(){
        return queue.size()==0;
    }

    public void WaitEmpty() throws InterruptedException {
        synchronized (Empty){
            Empty.wait();
        }
    }

    public void WaitFull() throws InterruptedException {
        synchronized (Full){
            Full.wait();
        }
    }

    public void notifyFull(){
        synchronized (Full){
            Full.notify();
        }
    }

    public void notifyEmpty(){
        synchronized (Empty){
            Empty.notify();
        }
    }

}
