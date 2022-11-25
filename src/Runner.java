import javax.swing.*;

public class Runner {
    Integer num;
    Integer bufferSize;
    String outFile;

    JLabel largestPrime;
    JLabel numbersGen;
    JLabel timeElapsed;

    Runner(Integer num, Integer bufferSize, String outFile, JLabel largestPrime, JLabel numbersGen, JLabel timeElapsed) {
        this.num = num;
        this.bufferSize = bufferSize;
        this.outFile = outFile;
        this.largestPrime = largestPrime;
        this.numbersGen = numbersGen;
        this.timeElapsed = timeElapsed;
    }

    public void run() {
        long begin = System.currentTimeMillis();
        Buffer Queue= new Buffer(this.bufferSize);
        Producer producer= new Producer(Queue, this.num);
        Thread Thread1= new Thread(producer);
        Consumer consumer = new Consumer(Queue, this.outFile);
        Thread Thread2= new Thread(consumer);

        JLabel largestPrime = this.largestPrime;
        JLabel numbersGen = this.numbersGen;
        JLabel timeElapsed = this.timeElapsed;

        producer.onStop(new Callable() {
            @Override
            public void call() {
                long end = System.currentTimeMillis();

                largestPrime.setText(String.valueOf(consumer.getmax()));
        
                numbersGen.setText(String.valueOf(producer.getNumofPrime()));
        
                timeElapsed.setText(String.valueOf(end-begin));
            }
        });

        Thread1.start();
        Thread2.start();
    }
}
