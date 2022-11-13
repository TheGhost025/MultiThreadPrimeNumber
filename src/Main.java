public class Main {
    public static void main(String[] args) {
       Buffer Queue=new Buffer(8);
       Producer producer=new Producer(Queue,100);
       Thread Thread1=new Thread(producer);
       Consumer consumer =new Consumer(Queue);
       Thread Thread2=new Thread(consumer);
        long begin = System.currentTimeMillis();
       Thread1.start();
       Thread2.start();
       try{
           Thread.sleep(10000);
           //producer.stop();
           long end = System.currentTimeMillis();
           System.out.println(producer.getNumofPrime());
           System.out.println(end-begin);
           System.out.println(consumer.getmax());
       }
       catch (InterruptedException e){
           e.printStackTrace();
       }
    }
}