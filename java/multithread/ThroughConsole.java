package multithread;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.String.format;
import static java.lang.System.out;

public class ThroughConsole {
    //@formatter:off
    static char escCode = 0x1B; static int row = 0, columnRead = 45, columnState=30,columnClock=40,columnBracket=42;
    static final String ANSI_CLS = "\u001b[2J"; static final String ANSI_HOME = "\u001b[H";
    static boolean flag;
    static void cleanScreen(){  out.print(ANSI_CLS + ANSI_HOME); out.flush(); }
    static void clock(){ moveCursor(columnClock); out.print((flag=!flag)?"\\":"/"); moveCursor(columnRead); out.flush(); }
    static void pAt(int column,String str){ out.print(format("%c[%d;%df",escCode,row,column)); out.print(str); out.flush(); }
    static void moveCursor(int column){ out.print(format("%c[%d;%df",escCode,row,column)); out.flush();}
    interface StateFull{void set(String s); String get();}
    static Console console = System.console();
    static final StateFull statefullObject = new StateFull(){
        public void set(String s) { state = s;}
        public String get() {return state;}
        private String state = "";
    };
    static final ReentrantLock lock = new ReentrantLock(true);
    static Callable<StateFull> userReader = () -> {
            while(true) {
                if (console.reader().ready()) {
                    lock.lock();
//                    console.reader().read()
                    String state = console.readLine();
                    if (!state.isEmpty()) statefullObject.set(state);
                    pAt(columnRead,"                          ");
                    lock.unlock();
                }
            }
    };
    static Callable<StateFull> stateWriter = () -> {
        while(true) {
            Thread.sleep(500);
            lock.lock();
//            cleanScreen();
            pAt(0, "Input object state (current:            ):");
            String printableValue = String.format("%-" + 10 + "." + 10 + "s", statefullObject.get());
            pAt(columnState,printableValue);
            moveCursor(columnRead);
            lock.unlock();
        }
    };
    static Callable<StateFull> clockWriter = () -> {
        while(true) {
            Thread.sleep(100);
            lock.lock();
            clock();
            lock.unlock();
        }
    };

    public static void main(String... args) throws Exception{
        cleanScreen();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Collection<Callable<StateFull>> c = new ArrayList<Callable<StateFull>>(){{add(stateWriter);add(userReader);}};
        executorService.invokeAll(c);
    }
    //@formatter:on
}
