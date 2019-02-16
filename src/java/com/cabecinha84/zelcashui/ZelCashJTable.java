package com.cabecinha84.zelcashui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.vaklinov.zcashui.DataTable;
import com.vaklinov.zcashui.Log;

public class ZelCashJTable extends JTable {
	private Color backGroundColor = ZelCashUI.table;
	private Color headerBackGroundColor = ZelCashUI.tableHeader;
	private Color textColor = ZelCashUI.text;
	private boolean keyEventExecuted = false;
	public ZelCashJTable() {
		super();
		this.setBackground(backGroundColor);
		this.getTableHeader().setBackground(headerBackGroundColor);
		this.setForeground(textColor);
		this.getTableHeader().setForeground(textColor);
		addClipBoardMenuOptions();
	}

	public ZelCashJTable(int numRows, int numColumns) {
		super(numRows, numColumns);
		this.setBackground(backGroundColor);
		this.getTableHeader().setBackground(headerBackGroundColor);
		this.setForeground(textColor);
		this.getTableHeader().setForeground(textColor);
		addClipBoardMenuOptions();
	}

	public ZelCashJTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		this.setBackground(backGroundColor);
		this.getTableHeader().setBackground(headerBackGroundColor);
		this.setForeground(textColor);
		this.getTableHeader().setForeground(textColor);
		addClipBoardMenuOptions();
	}

	public ZelCashJTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		super(dm, cm, sm);
		this.setBackground(backGroundColor);
		this.getTableHeader().setBackground(headerBackGroundColor);
		this.setForeground(textColor);
		this.getTableHeader().setForeground(textColor);
		addClipBoardMenuOptions();
	}

	public ZelCashJTable(TableModel dm, TableColumnModel cm) {
		super(dm, cm);
		this.setBackground(backGroundColor);
		this.getTableHeader().setBackground(headerBackGroundColor);
		this.setForeground(textColor);
		this.getTableHeader().setForeground(textColor);
		addClipBoardMenuOptions();
	}

	public ZelCashJTable(TableModel dm) {
		super(dm);
		this.setBackground(backGroundColor);
		this.getTableHeader().setBackground(headerBackGroundColor);
		this.setForeground(textColor);
		this.getTableHeader().setForeground(textColor);
		addClipBoardMenuOptions();
	}

	public ZelCashJTable(Vector<? extends Vector> rowData, Vector<?> columnNames) {
		super(rowData, columnNames);
		this.setBackground(backGroundColor);
		this.getTableHeader().setBackground(headerBackGroundColor);
		this.setForeground(textColor);
		this.getTableHeader().setForeground(textColor);
		addClipBoardMenuOptions();
	}

	private void addClipBoardMenuOptions() {
	    this.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyReleased(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_C  && !keyEventExecuted) {
	            	keyEventExecuted = true;
	            	int lastRow = getSelectedRow();
	            	int lastColumn = getSelectedColumn();
	            	if ((lastRow >= 0) && (lastColumn >= 0))
					{
						String text = getValueAt(lastRow, lastColumn).toString();
					
						Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
						clipboard.setContents(new StringSelection(text), null);
					} else
					{
						// Log perhaps
					}
	            	
                	Runnable r = new Runnable() {
            		public void run() {
           	        	try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							Log.error("Error sleeping thread for processkeyAction: "+e.getMessage());
						}
           	        	 processKeyAction();
	           	         }
	           	    };
	
	           	    new Thread(r).start();
	            }
	        }
	    });
	}
	
	private void processKeyAction() {
		keyEventExecuted = false;
	}
}

