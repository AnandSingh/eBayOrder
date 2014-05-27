/*The MIT License (MIT)

Copyright (c) 2014 Anand Singh

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.*/

package com.embvid;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;


/**
 * <p>
 * Title: EmbvidMultiLineTableCellRenderer.Java
 * </p>
 * <p>
 * Description: A Java Application to process orders of ebay.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Anand Singh
 * @version 1.0
 */
public class EmbvidMultiLineTableCellRenderer extends JTextArea implements
		TableCellRenderer, TableCellEditor {

	private static final long serialVersionUID = 3045173042398525063L;
	private List<List<Integer>> rowColHeight = new ArrayList<List<Integer>>();

	public EmbvidMultiLineTableCellRenderer() {
		setLineWrap(true);
		setWrapStyleWord(true);
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}
		setFont(table.getFont());
		if (hasFocus) {
			setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
			if (table.isCellEditable(row, column)) {
				setForeground(UIManager.getColor("Table.focusCellForeground"));
				setBackground(UIManager.getColor("Table.focusCellBackground"));
			}
		} else {
			setBorder(new EmptyBorder(1, 2, 1, 2));
		}
		if (value != null) {
			setText(value.toString());
		} else {
			setText("");
		}
		adjustRowHeight(table, row, column);
		return this;
	}

	/**
	 * Calculate the new preferred height for a given row, and sets the height
	 * on the table.
	 */
	private void adjustRowHeight(JTable table, int row, int column) {
		// The trick to get this to work properly is to set the width of the
		// column to the
		// textarea. The reason for this is that getPreferredSize(), without a
		// width tries
		// to place all the text in one line. By setting the size with the with
		// of the column,
		// getPreferredSize() returnes the proper height which the row should
		// have in
		// order to make room for the text.
		int cWidth = table.getTableHeader().getColumnModel().getColumn(column)
				.getWidth();
		setSize(new Dimension(cWidth, 1000));
		int prefH = getPreferredSize().height;
		while (rowColHeight.size() <= row) {
			rowColHeight.add(new ArrayList<Integer>(column));
		}
		List<Integer> colHeights = rowColHeight.get(row);
		while (colHeights.size() <= column) {
			colHeights.add(0);
		}
		colHeights.set(column, prefH);
		int maxH = prefH;
		for (Integer colHeight : colHeights) {
			if (colHeight > maxH) {
				maxH = colHeight;
			}
		}
		if (table.getRowHeight(row) != maxH) {
			table.setRowHeight(row, maxH);
		}
	}

	@Override
	public void addCellEditorListener(CellEditorListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return getText();
	}

	@Override
	public boolean isCellEditable(EventObject arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeCellEditorListener(CellEditorListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean shouldSelectCell(EventObject arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean stopCellEditing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1,
			boolean arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		return null;
	}
}