package swing;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

import static helpers.ConcurrencyHelpers.currentThreadName;
import static helpers.ConcurrencyHelpers.sleep;
import static helpers.Strings.f;
import static javax.swing.SwingWorker.StateValue.DONE;

public class SwingExperimentJFrameWithTest {

    static class SwingExperimentJFrame extends JFrame {
        private final JButton startButton;
        private final JTextArea textArea;

        SwingExperimentJFrame(JButton startButton, JTextArea textArea) {
            this.startButton = startButton;
            this.textArea = textArea;
        }

        private void createUI() {
            textArea.setEditable(false);
            this.setLayout(new FlowLayout());
            this.add(startButton);
            this.add(new JScrollPane(textArea));
            this.pack();
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setVisible(true);
        }

    }

    static class MySwingWorker extends SwingWorker<Void, Integer> {

        private final JTextArea textArea;

        MySwingWorker(JTextArea textArea) {
            this.textArea = textArea;
            // init happens in AWT-EventQueue
            System.out.println(f("init Thread: {}", currentThreadName()));
        }

        @Override
        protected Void doInBackground() throws Exception {
            for (int i = 1; i <= 10; i++) {
                Thread.sleep(500); // Simulate a long-running task
                publish(i); // Update the UI with the progress
                System.out.println(f("doInBackground Thread: {}", currentThreadName()));
            }
            return null;
        }

        @Override
        protected void process(java.util.List<Integer> chunks) {
            for (int number : chunks) {
                textArea.append(f("Number: {}. Thread: {}\n", number, currentThreadName()));
                System.out.println(f("process. Thread: {}", currentThreadName()));
            }
        }

        @Override
        protected void done() {
            textArea.append(f("Task Completed! Thread: {}\n", currentThreadName()));
        }
    }

    record Tuple(SwingExperimentJFrame frame, MySwingWorker worker) {
    }

    static Tuple createSwingExperimentJFrame() {
        JButton jButton = new JButton("Start Long Task");
        JTextArea textArea = new JTextArea(15, 20);

        MySwingWorker worker = new MySwingWorker(textArea);
        jButton.addActionListener(e -> {
            worker.execute();
        });
        return new Tuple(new SwingExperimentJFrame(jButton, textArea), worker);
    }

    @Test
    public void test() {
        Tuple tuple = createSwingExperimentJFrame();
        SwingUtilities.invokeLater(() -> {
            tuple.frame.createUI();
        });
        // wait till UI created
        while (tuple.frame.textArea == null) {
            sleep(10);
        }
        // click button
        tuple.frame.startButton.getActionListeners()[0].actionPerformed(null);
        // waiting finish of Worker
        while (tuple.worker.getState() != DONE) {
            sleep(10);
        }
        tuple.frame.dispatchEvent(new WindowEvent(tuple.frame, Event.WINDOW_DESTROY));
        tuple.frame.dispose();
        tuple.frame.setVisible(false);
//        se.isDisplayable();
        System.out.println();
    }

}


