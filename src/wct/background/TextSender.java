package wct.background;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import wct.mk.Keyboard;
import wct.mk.Mouse;
import wct.mk.Position;

/**
 *
 * @author workshop
 */
public class TextSender extends SwingWorker<Void, Void> {

    // gui
    private JButton startJButton;
    private JButton stopJButton;
    private JTextField skipTextField;

    // data
    private String text;
    private int noOfGroups;
    private long waitingTime;
    private int skip;
    private Position wcPosition;

    @Override
    protected Void doInBackground() throws Exception {
        startJButton.setEnabled(false);
        stopJButton.setEnabled(true);
        Robot r;
        r = new Robot();
        // click to WeChat app
        Mouse.getInstance().click(r, wcPosition);

        // copy text
        copyText();

        // select group and paste
        for (int i = 0; i < noOfGroups; i++) {
            // down
            Keyboard.getInstance().down(r, skip + i);
            //paste
            Keyboard.getInstance().paste(r);
            Thread.sleep(waitingTime);
            if (isCancelled()) {
                break;
            }
        }
        startJButton.setEnabled(true);
        stopJButton.setEnabled(false);
        skipTextField.setText(Integer.toString(noOfGroups + skip));

        return null;
    }

    private void copyText() {
        StringSelection ss = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, ss);
    }

    public JButton getStartJButton() {
        return startJButton;
    }

    public void setStartJButton(JButton startJButton) {
        this.startJButton = startJButton;
    }

    public JButton getStopJButton() {
        return stopJButton;
    }

    public void setStopJButton(JButton stopJButton) {
        this.stopJButton = stopJButton;
    }

    public JTextField getSkipTextField() {
        return skipTextField;
    }

    public void setSkipTextField(JTextField skipTextField) {
        this.skipTextField = skipTextField;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNoOfGroups() {
        return noOfGroups;
    }

    public void setNoOfGroups(int noOfGroups) {
        this.noOfGroups = noOfGroups;
    }

    public long getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(long waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public Position getWcPosition() {
        return wcPosition;
    }

    public void setWcPosition(Position wcPosition) {
        this.wcPosition = wcPosition;
    }

}
