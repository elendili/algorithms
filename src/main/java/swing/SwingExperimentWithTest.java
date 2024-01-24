package swing;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

import static helpers.Helpers.currentThreadName;
import static helpers.Helpers.sleep;
import static helpers.Strings.f;
import static javax.swing.SwingWorker.StateValue.DONE;

public class SwingExperimentWithTest {
    static class SwingExperiment extends JFrame {
        private JButton startButton;
        private JTextArea textArea;
        private SwingWorker<Void, Integer> worker;

        private void createUI() {
            startButton = new JButton("Start Long Task");
            textArea = new JTextArea(15, 20);
            textArea.setEditable(false);

            startButton.addActionListener(e -> worker.execute());

            this.setLayout(new FlowLayout());
            this.add(startButton);
            this.add(new JScrollPane(textArea));
            this.pack();
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setVisible(true);
        }

        class MySwingWorker extends SwingWorker<Void, Integer> {
            {
                // init happens in AWT-EventQueue
                System.out.println(f("init Thread: {}\n", currentThreadName()));
            }

            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(100); // Simulate a long-running task
                    publish(i); // Update the UI with the progress
                    System.out.println(f("doInBackground Thread: {}\n", currentThreadName()));
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                for (int number : chunks) {
                    textArea.append(f("Number: {}. Thread: {}\n", number, currentThreadName()));
                }
            }

            @Override
            protected void done() {
                textArea.append(f("Task Completed! Thread: {}\n", currentThreadName()));
                startButton.setEnabled(true);
            }
        }

        private void setUpWorker() {
            worker = new MySwingWorker();
        }

    }

    @Test
    public void test() {
//        AtomicReference<JFrame> ref = new AtomicReference<>();
        SwingExperiment se = new SwingExperiment();
        SwingUtilities.invokeLater(()->{
            se.createUI();
            se.setUpWorker();
        });
        while(se.worker == null){
            sleep(10);
        }
        se.startButton.getActionListeners()[0].actionPerformed(null);
        while(se.worker.getState()!=DONE){
            sleep(10);
        }
        se.dispatchEvent(new WindowEvent(se, Event.WINDOW_DESTROY));
        se.dispose();
        se.setVisible(false);
//        se.isDisplayable();
        System.out.println();
    }

}


