package Controller;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Model.MyIcon;
import Model.MyFont;

public class TimeThread extends JLabel implements Runnable {

	private Thread myThread;
	private int nSleep, m, s;
	private ImageIcon lblTimeIcon, lblIcon;
	private static TimeThread lblTime;
	private boolean stop = false;

	public static TimeThread getInstance() {
		if (lblTime == null)
			lblTime = new TimeThread();

		return lblTime;
	}

	public TimeThread() {
		nSleep = 1000;
		m = 10;
		s = 0;

		lblTimeIcon = new MyIcon("TimeBoard.png").getIcon(150, 100);
		setIcon(lblTimeIcon);

		setText("" + m + " : 0" + s);
		setFont(MyFont.getPlayPanelFont());
		setForeground(Color.black);
		setHorizontalAlignment(SwingConstants.CENTER);
		setHorizontalTextPosition(SwingConstants.CENTER);

		myThread = new Thread(this);

		setBounds(450, 0, 150, 100);
		setOpaque(true);
		setBackground(Color.white);

		myThread.start();
	}

	public void start() {
		myThread.start();
	}

	public void stop(boolean stop) {
		this.stop = stop;
		System.out.println(String.valueOf(this.stop));
	}

	public void run() {

		while (!stop) {

			try {
				myThread.sleep(nSleep);
			} catch (Exception e) {
			}
			// 남은 시간 경과 표시
			for (m = 9; m >= 0; m--) {
				for (s = 59; s >= 0; s--) {
					if (s < 10) {
						setText("0" + m + " : 0" + s);
					} else {
						setText("0" + m + " : " + s);
					}

					try {
						myThread.sleep(nSleep);
					} catch (Exception e) {
					}
				}

			}

		}

		setFont(MyFont.getPlayPanelFont());
		setText("00 : 00");
		setForeground(Color.red);
	}

	public void setNull() {
		this.lblTime = null;
	}

	public int getMinute() {
		return m;
	}

	public int getSecond() {
		return s;
	}

}