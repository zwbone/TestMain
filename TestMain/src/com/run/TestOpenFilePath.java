package com.run;

import javax.swing.JFileChooser;

public class TestOpenFilePath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
		chooser.showOpenDialog(null);
		String path = chooser.getSelectedFile().getPath();
		System.out.println(path);
	}

}
