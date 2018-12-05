package GUI;

import java.net.URISyntaxException;

import javax.swing.JFrame;

public class test {
	private static final int EXIT_ON_CLOSE = 0;

	public static void main(String[] args) throws URISyntaxException {
		Map window = new Map();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

	}
}
