package View;
import Controller.Rank;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.GameManager;
import Model.Button;
import Model.Label;
import Model.Model;
import Model.MyFont;
import Model.MyIcon;

public class RankPanel extends JPanel {

	private static RankPanel rankPanel;
	private JButton btnGoMain, btnExit;
	private JLabel lblTitle, lblSubTitle;
	
	private JLabel lblName[] = new JLabel[5];
	private JLabel lblScore[] = new JLabel[5];
	private int[] lastScore = new int[2];

	int idx; // 랭킹 Label을 DB로 부터 받아오고 랭킹 재조회 시 기존 랭킹을 지우는 index
	private ImageIcon restart1, restart2, exit1, exit2; // 재시작, 종료 버튼에 이미지를 씌워주기 위한 이미지아이콘 각각 2개씩

	public static RankPanel getInstance() {
		if (rankPanel == null)
			rankPanel = new RankPanel();

		return rankPanel;
	}

	private RankPanel() {
		Game game = GameManager.getInstance().getGame();

		Color backColor = new Color(246, 223, 170);
		setPreferredSize(new Dimension(600, 700));
		setBackground(backColor);
		setLayout(null);

		lblTitle = new Label("RANKING").getRankLabel(MyFont.getRankBIgFont(),50, 50, 500, 90);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle);

		lblSubTitle = new Label("     NAME          SCORE").getRankLabel(MyFont.getRankSmallFont(), 50, 165, 500, 60);
		lblSubTitle.setOpaque(true);
		lblSubTitle.setForeground(Color.red);
		add(lblSubTitle);

		restart1 = new MyIcon("main1.png").getIcon(225, 100);
		restart2 = new MyIcon("main2.png").getIcon(225, 100);

		exit1 = new MyIcon("exit1.png").getIcon(225, 100);
		exit2 = new MyIcon("exit2.png").getIcon(225, 100);

		btnGoMain = new Button("□ MAIN", restart1, restart2).getButton(backColor, 50, 550, 235, 100);
		btnGoMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.mainButton();
			}
		});
		add(btnGoMain);
		

		btnExit = new Button("Exit", exit1, exit2).getButton(backColor, 325, 550, 235, 100);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(btnExit);
	}
	
	public void printRanking(RankPanel rankPanel) {
		idx = 0;

		// 랭킹을 5등까지 출력하는 메소드

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = Rank.getInstance().getCon().createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			rs = stmt.executeQuery("SELECT * FROM Score_Board ORDER BY score DESC");
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		try {
			while (rs.next() && idx < 5) {
				String name = rs.getString(1);
				int score = rs.getInt(2);

				// NAME 랭킹 라벨
				lblName[idx] = new JLabel();
				lblName[idx].setBounds(50, 225 + 60 * (idx), 500, 60);
				lblName[idx].setFont(new Font("Verdana", Font.BOLD, 35));
				lblName[idx].setText(idx + 1 + ".  " + name + "\r\n");
				rankPanel.add(lblName[idx]);

				// SCORE 랭킹 라벨
				lblScore[idx] = new JLabel();
				lblScore[idx].setBounds(340, 225 + 60 * (idx), 500, 60);
				lblScore[idx].setFont(new Font("Verdana", Font.BOLD, 35));
				lblScore[idx].setText(score + "\r\n");
				rankPanel.add(lblScore[idx]);

				lastScore[0] = idx;
				lastScore[1] = score;
				idx += 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void hideBeforeRankLabel() {
		for (int i = 0; i < idx; i++) {
			lblName[i].setVisible(false);
			lblScore[i].setVisible(false);
		}
	}
	
	public void isRankIn() {
		Model barObject = GameManager.getInstance().getModel();
		if (lastScore[0] < 4 || lastScore[1] < barObject.getScore())
			JOptionPane.showMessageDialog(null, "랭킹에 진입했습니다.");
		else
			JOptionPane.showMessageDialog(null, "랭킹에 진입하지 못했습니다.");
	}
}