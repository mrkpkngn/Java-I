package com.task.controller;

import java.awt.GridLayout;
import javax.swing.*;
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
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
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
