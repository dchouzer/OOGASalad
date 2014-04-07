package game_authoring_environment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class GameeditorPanel extends Panel {
	
	private SubPanel mySubPanel;
	private PanelType superType;
	
	public GameeditorPanel() {
		super(PanelType.GAMEEDITOR);
		makeSubPanel();
		construct();
	}

	@Override
	protected void construct() {
		this.setLayout(new BorderLayout());		
		this.add(mySubPanel, BorderLayout.NORTH);
		System.out.println("test");
		this.add(makeTable(), BorderLayout.CENTER);
	

	}

	@Override
	protected void makeSubPanel() {
		mySubPanel = (SubPanel) ViewFactory.buildPanel(PanelType.SUB);
		mySubPanel.setSuperType(getType());
		mySubPanel.addItems(makeSubPanelItems());
		mySubPanel.construct();
	}

	@Override
	protected JComponent makeSubPanelItems() {
		JButton jb = ViewFactory.createJButton("Validate");
		return jb;
	}
	
	protected JComponent makeTable(){
		String[] columnNames = {"Category",
		  "Value",
		  "Type"};
		
		Object[][] data = {
		{"Name", new String(),
		"text"},
		{"Time", new Integer(0),
		"real"},
		{"Display Width", new Integer(480),
		"real"},
		{"Display Height", new Integer(320),
		"real"},
		{"Actor Tags", new String(),
		"text"}
		};
		
		JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		
		//Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		
		//Add the scroll pane to this panel.
		
		return scrollPane;
	}

}
