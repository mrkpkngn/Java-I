package com.task.controller;

import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import com.task.service.TaskService;
import com.task.model.TaskItem;
import com.task.model.TaskStatus;

public class TaskItemGUI extends JFrame {
    JButton addButton = new JButton("Add");
    JButton deleteButton = new JButton("Delete");
    JButton updateButton = new JButton("Update");
    JButton reloadButton = new JButton("Reload");
    JButton exitButton = new JButton("Exit");

    JTextField titleField = new JTextField();
    JTextField dateField = new JTextField();
    JComboBox<TaskStatus> statusBox = new JComboBox<>(TaskStatus.values());
    JTextField emailField = new JTextField();
    DefaultTableModel tableModel;
    JTable taskItemTable;

    String[] columnNames = { "Id", "Title", "Due Date", "Status", "User Email" };

    private TaskService _service;

    public TaskItemGUI(TaskService service) {
        this._service = service;

        setTitle("Simple Task Manager");
        setSize(900, 600);

        tableModel = new DefaultTableModel(columnNames, 0);

        for (TaskItem taskItem : _service.getAllTasks()) {
            Object[] row = {
                    taskItem.getId(),
                    taskItem.getTitle(),
                    taskItem.getdueDate(),
                    taskItem.getTaskStatus(),
                    taskItem.getEmail(),
            };
            tableModel.addRow(row);
        }

        taskItemTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(taskItemTable);

        taskItemTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && taskItemTable.getSelectedRow() != -1) {

                    int selectedRow = taskItemTable.getSelectedRow();

                    String title = taskItemTable.getModel().getValueAt(selectedRow, 1).toString();
                    String dueDate = taskItemTable.getModel().getValueAt(selectedRow, 2).toString();
                    TaskStatus status = (TaskStatus) taskItemTable.getModel().getValueAt(selectedRow, 3);
                    String email = taskItemTable.getModel().getValueAt(selectedRow, 4).toString();

                    titleField.setText(title);
                    dateField.setText(dueDate);
                    statusBox.setSelectedItem(status);
                    emailField.setText(email);
                }
            }
        });

        JPanel south = new JPanel(new GridLayout(5, 2, 3, 3));

        // Row 1
        south.add(new JLabel("Title:"));
        south.add(titleField);

        // Row 2
        south.add(new JLabel("Due Date (YYYY-MM-DD)"));
        south.add(dateField);

        // Row 3
        south.add(new JLabel("Status:"));
        south.add(statusBox);

        // Row 4
        south.add(new JLabel("User Email:"));
        south.add(emailField);

        // Row 5
        JPanel buttons = new JPanel();
        buttons.add(addButton);
        buttons.add(deleteButton);
        buttons.add(updateButton);
        buttons.add(reloadButton);
        buttons.add(exitButton);
        south.add(buttons);

        add("Center", scrollPane);
        add("South", south);

        setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = getTitleField();
                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(TaskItemGUI.this,
                            "Title cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String dueDate = getDateField();
                if (dueDate.isEmpty()) {
                    JOptionPane.showMessageDialog(TaskItemGUI.this,
                            "Due Date cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                TaskStatus taskStatus = getStatusBox();

                String email = getEmailField();
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(TaskItemGUI.this,
                            "Due Date cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                TaskItem newTask = _service.createTask(title, dueDate, taskStatus, email);

                Object[] newRow = {
                        newTask.getId(),
                        newTask.getTitle(),
                        newTask.getdueDate(),
                        newTask.getTaskStatus(),
                        newTask.getEmail()
                };
                tableModel.addRow(newRow);
                clearFields();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = taskItemTable.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(TaskItemGUI.this,
                            "Please select a task to update.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id = (int) taskItemTable.getModel().getValueAt(selectedRow, 0);

                String title = getTitleField();
                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(TaskItemGUI.this,
                            "Title cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String dueDate = getDateField();
                if (dueDate.isEmpty()) {
                    JOptionPane.showMessageDialog(TaskItemGUI.this,
                            "Due Date cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                TaskStatus taskStatus = getStatusBox();
                String email = getEmailField();
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(TaskItemGUI.this,
                            "Email cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                TaskItem updatedTask = _service.updateTask(id, title, dueDate, taskStatus, email);

                if (updatedTask != null) {
                    tableModel.setValueAt(updatedTask.getTitle(), selectedRow, 1);
                    tableModel.setValueAt(updatedTask.getdueDate(), selectedRow, 2);
                    tableModel.setValueAt(updatedTask.getTaskStatus(), selectedRow, 3);
                    tableModel.setValueAt(updatedTask.getEmail(), selectedRow, 4);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(TaskItemGUI.this,
                            "Task update failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = taskItemTable.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(TaskItemGUI.this,
                            "Please select a task to update.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id = (int) taskItemTable.getModel().getValueAt(selectedRow, 0);

                boolean success = _service.deleteTask(id);

                if (success) {
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(TaskItemGUI.this,
                            "Task deletion failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _service.reloadTasks();

                tableModel.setRowCount(0);

                for (TaskItem taskItem : _service.getAllTasks()) {
                    Object[] row = {
                            taskItem.getId(),
                            taskItem.getTitle(),
                            taskItem.getdueDate(),
                            taskItem.getTaskStatus(),
                            taskItem.getEmail(),
                    };
                    tableModel.addRow(row);
                }

                clearFields();
            }
        });
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTaskItemTable() {
        return taskItemTable;
    }

    public String getTitleField() {
        return titleField.getText();
    }

    public String getDateField() {
        return dateField.getText();
    }

    public TaskStatus getStatusBox() {
        return (TaskStatus) statusBox.getSelectedItem();
    }

    public String getEmailField() {
        return emailField.getText();
    }

    public void clearFields() {
        titleField.setText("");
        dateField.setText("");
        emailField.setText("");
        statusBox.setSelectedIndex(0);
    }
}
