package theprojectcode;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentLifeManagerApp extends Application {

    private Scene loginScene;
    private Scene registerScene;
    private Scene mainMenuScene;
    private Scene taskManagerScene;
    private Scene settingsScene;
    private Scene examsScene;
    private Scene subjectsScene;

    private TextField txtUsername, txtTaskField, txtFontSize, txtOpacity;
    private PasswordField txtPassword;
    private TextField txtRegUsername;
    private PasswordField txtRegPassword, txtRegConfirmPassword;
    private Label lblStatus, lblRegStatus, lblTaskCount;
    private TableView<Task> taskTableView;
    private TableView<Exam> examsTableView;
    private TableView<Subject> subjectsTableView;

    private CheckBox chkDarkMode;
    private CheckBox chkAutoSave;

    private int loginAttempts = 0;

    private ObservableList<Task> tasksList = FXCollections.observableArrayList();
    private ObservableList<Exam> examsList = FXCollections.observableArrayList();
    private ObservableList<Subject> subjectsList = FXCollections.observableArrayList();

    // DAO Objects
    private TaskDAO taskDAO = new TaskDAO();
    private ExamDAO examDAO = new ExamDAO();
    private SubjectDAO subjectDAO = new SubjectDAO();
    private UserDAO userDAO = new UserDAO();

    public void start(Stage primaryStage) {

        // Load data from database
        tasksList.addAll(taskDAO.getAllTasks());
        examsList.addAll(examDAO.getAllExams());
        subjectsList.addAll(subjectDAO.getAllSubjects());

        // ==================== Login Scene ==================== 
        Label lblLoginTitle = new Label("Student Life Manager");
        lblLoginTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label lblUser = new Label("Username:");
        Label lblPass = new Label("Password:");

        txtUsername = new TextField();
        txtUsername.setPromptText("Enter username");

        txtPassword = new PasswordField();
        txtPassword.setPromptText("Enter password");

        Button btnLogin = new Button("Login");
        btnLogin.setPrefSize(120, 35);
        btnLogin.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        Button btnGoToRegister = new Button("Register");
        btnGoToRegister.setPrefSize(120, 35);
        btnGoToRegister.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        Button btnExit = new Button("Exit");
        btnExit.setPrefSize(120, 35);
        btnExit.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

        lblStatus = new Label("");
        lblStatus.setStyle("-fx-text-fill: red;");

        GridPane loginGrid = new GridPane();
        loginGrid.setAlignment(Pos.CENTER);
        loginGrid.setHgap(15);
        loginGrid.setVgap(15);
        loginGrid.setPadding(new Insets(40));

        loginGrid.add(lblLoginTitle, 0, 0, 2, 1);
        loginGrid.add(lblUser, 0, 1);
        loginGrid.add(txtUsername, 1, 1);
        loginGrid.add(lblPass, 0, 2);
        loginGrid.add(txtPassword, 1, 2);

        HBox buttonBox = new HBox(10, btnLogin, btnGoToRegister, btnExit);
        buttonBox.setAlignment(Pos.CENTER);
        loginGrid.add(buttonBox, 0, 3, 2, 1);
        loginGrid.add(lblStatus, 0, 4, 2, 1);

        loginScene = new Scene(loginGrid, 450, 350);

        // ==================== Register Scene ==================== 
        Label lblRegisterTitle = new Label("Create New Account");
        lblRegisterTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label lblRegUser = new Label("Username:");
        Label lblRegPass = new Label("Password:");
        Label lblRegConfirm = new Label("Confirm Password:");

        txtRegUsername = new TextField();
        txtRegUsername.setPromptText("Choose a username");

        txtRegPassword = new PasswordField();
        txtRegPassword.setPromptText("Choose a password");

        txtRegConfirmPassword = new PasswordField();
        txtRegConfirmPassword.setPromptText("Confirm password");

        Button btnRegister = new Button("Register");
        btnRegister.setPrefSize(120, 35);
        btnRegister.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        Button btnBackToLogin = new Button("Back to Login");
        btnBackToLogin.setPrefSize(120, 35);
        btnBackToLogin.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white;");

        lblRegStatus = new Label("");
        lblRegStatus.setStyle("-fx-text-fill: red;");

        GridPane registerGrid = new GridPane();
        registerGrid.setAlignment(Pos.CENTER);
        registerGrid.setHgap(15);
        registerGrid.setVgap(15);
        registerGrid.setPadding(new Insets(40));

        registerGrid.add(lblRegisterTitle, 0, 0, 2, 1);
        registerGrid.add(lblRegUser, 0, 1);
        registerGrid.add(txtRegUsername, 1, 1);
        registerGrid.add(lblRegPass, 0, 2);
        registerGrid.add(txtRegPassword, 1, 2);
        registerGrid.add(lblRegConfirm, 0, 3);
        registerGrid.add(txtRegConfirmPassword, 1, 3);

        HBox regButtonBox = new HBox(15, btnRegister, btnBackToLogin);
        regButtonBox.setAlignment(Pos.CENTER);
        registerGrid.add(regButtonBox, 0, 4, 2, 1);
        registerGrid.add(lblRegStatus, 0, 5, 2, 1);

        registerScene = new Scene(registerGrid, 450, 400);

        // ==================== Main Menu Scene ==================== 
        Label lblMenuTitle = new Label("Main Menu");
        lblMenuTitle.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        Button btnTaskManager = new Button("Task Manager");
        Button btnExams = new Button("Exams Schedule");
        Button btnSubjects = new Button("Subjects");
        Button btnSettings = new Button("Settings");
        Button btnLogout = new Button("Logout");

        btnTaskManager.setPrefSize(200, 50);
        btnExams.setPrefSize(200, 50);
        btnSubjects.setPrefSize(200, 50);
        btnSettings.setPrefSize(200, 50);
        btnLogout.setPrefSize(200, 50);

        btnTaskManager.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px;");
        btnExams.setStyle("-fx-background-color: #9C27B0; -fx-text-fill: white; -fx-font-size: 14px;");
        btnSubjects.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        btnSettings.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-size: 14px;");
        btnLogout.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white; -fx-font-size: 14px;");

        VBox menuBox = new VBox(20, lblMenuTitle, btnTaskManager, btnExams, btnSubjects, btnSettings, btnLogout);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setPadding(new Insets(50));

        mainMenuScene = new Scene(menuBox, 500, 550);

        // ==================== Task Manager Scene ==================== 
        Label lblTaskTitle = new Label("Task Manager");
        lblTaskTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        lblTaskCount = new Label("Tasks: " + tasksList.size());

        txtTaskField = new TextField();
        txtTaskField.setPromptText("Enter new task...");
        txtTaskField.setPrefWidth(300);

        Button btnAddTask = new Button("Add Task");
        Button btnRemoveTask = new Button("Remove Selected");
        Button btnMarkComplete = new Button("Mark Complete");
        Button btnBackToMenu = new Button("Back to Menu");

        btnAddTask.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnRemoveTask.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        btnMarkComplete.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        btnBackToMenu.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white;");

        taskTableView = new TableView<>();
        taskTableView.setPrefHeight(250);
        taskTableView.setItems(tasksList);

        TableColumn<Task, Integer> taskIdCol = new TableColumn<>("ID");
        taskIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        taskIdCol.setPrefWidth(50);

        TableColumn<Task, String> taskNameCol = new TableColumn<>("Task Name");
        taskNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        taskNameCol.setPrefWidth(350);

        TableColumn<Task, String> taskStatusCol = new TableColumn<>("Status");
        taskStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        taskStatusCol.setPrefWidth(100);

        taskTableView.getColumns().addAll(taskIdCol, taskNameCol, taskStatusCol);

        HBox inputBox = new HBox(10, txtTaskField, btnAddTask);
        inputBox.setAlignment(Pos.CENTER);

        HBox actionBox = new HBox(10, btnRemoveTask, btnMarkComplete, btnBackToMenu);
        actionBox.setAlignment(Pos.CENTER);

        VBox taskBox = new VBox(20, lblTaskTitle, lblTaskCount, inputBox, taskTableView, actionBox);
        taskBox.setAlignment(Pos.CENTER);
        taskBox.setPadding(new Insets(30));

        taskManagerScene = new Scene(taskBox, 600, 500);

        // ==================== Exams Scene ==================== 
        Label titleExams = new Label("Exams Schedule");
        titleExams.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField subjectField = new TextField();
        subjectField.setPromptText("Subject name");
        subjectField.setPrefWidth(300);

        TextField dayField = new TextField();
        dayField.setPromptText("Day");
        dayField.setPrefWidth(80);

        TextField monthField = new TextField();
        monthField.setPromptText("Month");
        monthField.setPrefWidth(80);

        TextField yearField = new TextField();
        yearField.setPromptText("Year");
        yearField.setPrefWidth(100);

        HBox dateBox = new HBox(10, dayField, monthField, yearField);
        dateBox.setAlignment(Pos.CENTER);

        Button addExamBtn = new Button("Add Exam");
        addExamBtn.setPrefSize(140, 35);
        addExamBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        Button deleteExamBtn = new Button("Delete Selected");
        deleteExamBtn.setPrefSize(140, 35);
        deleteExamBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

        examsTableView = new TableView<>();
        examsTableView.setPrefHeight(200);
        examsTableView.setItems(examsList);

        TableColumn<Exam, String> examSubjectCol = new TableColumn<>("Subject");
        examSubjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
        examSubjectCol.setPrefWidth(250);

        TableColumn<Exam, String> examDateCol = new TableColumn<>("Date");
        examDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        examDateCol.setPrefWidth(150);

        examsTableView.getColumns().addAll(examSubjectCol, examDateCol);

        Button btnBackToMenuFromExams = new Button("Back to Menu");
        btnBackToMenuFromExams.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white;");
        btnBackToMenuFromExams.setPrefSize(140, 35);

        HBox examButtonsBox = new HBox(10, addExamBtn, deleteExamBtn);
        examButtonsBox.setAlignment(Pos.CENTER);

        VBox examsRoot = new VBox(20, titleExams, subjectField, dateBox, examButtonsBox, examsTableView, btnBackToMenuFromExams);
        examsRoot.setAlignment(Pos.CENTER);
        examsRoot.setPadding(new Insets(30));

        examsScene = new Scene(examsRoot, 500, 550);

        // ==================== Subjects Scene ==================== 
        Label lblSubjectsTitle = new Label("📚 Subjects");
        lblSubjectsTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField txtSubjectName = new TextField();
        txtSubjectName.setPromptText("Subject Name");
        txtSubjectName.setPrefWidth(250);

        TextField txtSubjectHours = new TextField();
        txtSubjectHours.setPromptText("Hours");
        txtSubjectHours.setPrefWidth(80);

        Button btnAddSubject = new Button("Add");
        btnAddSubject.setPrefSize(100, 35);
        btnAddSubject.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        HBox subjectInputBox = new HBox(10, new Label("Subject Name:"), txtSubjectName, new Label("Hours:"), txtSubjectHours, btnAddSubject);
        subjectInputBox.setAlignment(Pos.CENTER);

        subjectsTableView = new TableView<>();
        subjectsTableView.setPrefHeight(300);
        subjectsTableView.setItems(subjectsList);

        TableColumn<Subject, String> subjectNameCol = new TableColumn<>("Name");
        subjectNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        subjectNameCol.setPrefWidth(300);

        TableColumn<Subject, Integer> subjectHoursCol = new TableColumn<>("Hours");
        subjectHoursCol.setCellValueFactory(new PropertyValueFactory<>("hours"));
        subjectHoursCol.setPrefWidth(100);

        subjectsTableView.getColumns().addAll(subjectNameCol, subjectHoursCol);

        Button btnEditSubject = new Button("✏ Edit");
        btnEditSubject.setPrefSize(100, 35);
        btnEditSubject.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        Button btnDeleteSubject = new Button("❌ Delete");
        btnDeleteSubject.setPrefSize(100, 35);
        btnDeleteSubject.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

        HBox subjectActionsBox = new HBox(10, btnEditSubject, btnDeleteSubject);
        subjectActionsBox.setAlignment(Pos.CENTER);

        Button btnBackToMenuFromSubjects = new Button("Back to Menu");
        btnBackToMenuFromSubjects.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white;");
        btnBackToMenuFromSubjects.setPrefSize(140, 35);

        VBox subjectsRoot = new VBox(20, lblSubjectsTitle, subjectInputBox, subjectsTableView, subjectActionsBox, btnBackToMenuFromSubjects);
        subjectsRoot.setAlignment(Pos.CENTER);
        subjectsRoot.setPadding(new Insets(30));

        subjectsScene = new Scene(subjectsRoot, 600, 550);

        // ==================== Settings Scene ==================== 
        Label lblSettingsTitle = new Label("Settings");
        lblSettingsTitle.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        chkDarkMode = new CheckBox("Dark Mode");

        Label lblFontSize = new Label("Font Size (10-24):");
        txtFontSize = new TextField("14");
        txtFontSize.setPrefWidth(80);
        txtFontSize.setPromptText("10-24");

        Label lblOpacity = new Label("Opacity (0.0-1.0):");
        txtOpacity = new TextField("1.0");
        txtOpacity.setPrefWidth(80);
        txtOpacity.setPromptText("0.0-1.0");

        chkAutoSave = new CheckBox("Auto Save Tasks");
        chkAutoSave.setSelected(true);

        Button btnSave = new Button("Save");
        Button btnCancel = new Button("Cancel");
        Button btnApplyNow = new Button("Apply Now");

        btnSave.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnCancel.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white;");
        btnApplyNow.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        GridPane settingsGrid = new GridPane();
        settingsGrid.setAlignment(Pos.CENTER);
        settingsGrid.setHgap(15);
        settingsGrid.setVgap(20);
        settingsGrid.setPadding(new Insets(40));

        settingsGrid.add(lblSettingsTitle, 0, 0, 2, 1);
        settingsGrid.add(chkDarkMode, 0, 1, 2, 1);
        settingsGrid.add(lblFontSize, 0, 2);
        settingsGrid.add(txtFontSize, 1, 2);
        settingsGrid.add(lblOpacity, 0, 3);
        settingsGrid.add(txtOpacity, 1, 3);
        settingsGrid.add(chkAutoSave, 0, 4, 2, 1);

        HBox settingsButtons = new HBox(15, btnSave, btnApplyNow, btnCancel);
        settingsButtons.setAlignment(Pos.CENTER);
        settingsGrid.add(settingsButtons, 0, 5, 2, 1);

        settingsScene = new Scene(settingsGrid, 450, 400);

        // ==================== Event Handlers ==================== 
        // Login with Database
        btnLogin.setOnAction(e -> {
           
                primaryStage.setScene(mainMenuScene);
                primaryStage.setTitle("Main Menu - Student Life Manager");
                txtUsername.clear();
                txtPassword.clear();
           
        });

        // Go to Register Scene
        btnGoToRegister.setOnAction(e -> {
            primaryStage.setScene(registerScene);
            primaryStage.setTitle("Register - Student Life Manager");
            lblRegStatus.setText("");
            txtRegUsername.clear();
            txtRegPassword.clear();
            txtRegConfirmPassword.clear();
        });

        // Register New User
        btnRegister.setOnAction(e -> {
            String username = txtRegUsername.getText().trim();
            String password = txtRegPassword.getText().trim();
            String confirmPassword = txtRegConfirmPassword.getText().trim();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                lblRegStatus.setText("Please fill all fields!");
                lblRegStatus.setStyle("-fx-text-fill: red;");
                return;
            }

            if (username.length() < 3) {
                lblRegStatus.setText("Username must be at least 3 characters!");
                lblRegStatus.setStyle("-fx-text-fill: red;");
                return;
            }

            if (password.length() < 4) {
                lblRegStatus.setText("Password must be at least 4 characters!");
                lblRegStatus.setStyle("-fx-text-fill: red;");
                return;
            }

            if (!password.equals(confirmPassword)) {
                lblRegStatus.setText("Passwords do not match!");
                lblRegStatus.setStyle("-fx-text-fill: red;");
                return;
            }

            if (userDAO.registerUser(username, password)) {
                lblRegStatus.setText("✓ Registration successful! You can now login.");
                lblRegStatus.setStyle("-fx-text-fill: green;");
                txtRegUsername.clear();
                txtRegPassword.clear();
                txtRegConfirmPassword.clear();

                // Auto redirect to login after 2 seconds
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        javafx.application.Platform.runLater(() -> {
                            primaryStage.setScene(loginScene);
                            primaryStage.setTitle("Login - Student Life Manager");
                        });
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        });

        // Back to Login
        btnBackToLogin.setOnAction(e -> {
            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Login - Student Life Manager");
        });

        btnExit.setOnAction(e -> primaryStage.close());

        btnTaskManager.setOnAction(e -> {
            primaryStage.setScene(taskManagerScene);
            primaryStage.setTitle("Task Manager");
        });

        btnExams.setOnAction(e -> {
            primaryStage.setScene(examsScene);
            primaryStage.setTitle("Exams Schedule");
        });

        btnSubjects.setOnAction(e -> {
            primaryStage.setScene(subjectsScene);
            primaryStage.setTitle("Subjects Management");
        });

        btnSettings.setOnAction(e -> {
            primaryStage.setScene(settingsScene);
            primaryStage.setTitle("Settings");
        });

        btnLogout.setOnAction(e -> {
            loginAttempts = 0;
            btnLogin.setDisable(false);
            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Login - Student Life Manager");
        });

        // ==================== Tasks Events with Database ==================== 
        btnAddTask.setOnAction(e -> {
            String task = txtTaskField.getText().trim();
            if (!task.isEmpty()) {
                if (taskDAO.addTask(task, "Pending")) {
                    tasksList.clear();
                    tasksList.addAll(taskDAO.getAllTasks());
                    lblTaskCount.setText("Tasks: " + tasksList.size());
                    txtTaskField.clear();
                }
            }
        });

        btnRemoveTask.setOnAction(e -> {
            Task selected = taskTableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                if (taskDAO.deleteTask(selected.getId())) {
                    tasksList.remove(selected);
                    lblTaskCount.setText("Tasks: " + tasksList.size());
                }
            }
        });

        btnMarkComplete.setOnAction(e -> {
            Task selected = taskTableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                if (taskDAO.updateTaskStatus(selected.getId(), "✓ Complete")) {
                    selected.setStatus("✓ Complete");
                    taskTableView.refresh();
                }
            }
        });

        btnBackToMenu.setOnAction(e -> {
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle("Main Menu - Student Life Manager");
        });

        // ==================== Exams Events with Database ==================== 
        addExamBtn.setOnAction(e -> {
            String subject = subjectField.getText().trim();
            String sDay = dayField.getText().trim();
            String sMonth = monthField.getText().trim();
            String sYear = yearField.getText().trim();

            if (subject.isEmpty() || sDay.isEmpty() || sMonth.isEmpty() || sYear.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
                return;
            }

            try {
                int d = Integer.parseInt(sDay);
                int m = Integer.parseInt(sMonth);
                int y = Integer.parseInt(sYear);
                String dateStr = d + "/" + m + "/" + y;

                if (examDAO.addExam(subject, dateStr)) {
                    examsList.clear();
                    examsList.addAll(examDAO.getAllExams());
                    subjectField.clear();
                    dayField.clear();
                    monthField.clear();
                    yearField.clear();
                }
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Day, Month, and Year must be numbers").show();
            }
        });

        deleteExamBtn.setOnAction(e -> {
            Exam selected = examsTableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                if (examDAO.deleteExam(selected.getSubject(), selected.getDate())) {
                    examsList.remove(selected);
                }
            }
        });

        btnBackToMenuFromExams.setOnAction(e -> {
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle("Main Menu - Student Life Manager");
        });

        // ==================== Subjects Events with Database ==================== 
        btnAddSubject.setOnAction(e -> {
            String name = txtSubjectName.getText().trim();
            String hoursStr = txtSubjectHours.getText().trim();

            if (name.isEmpty() || hoursStr.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
                return;
            }

            try {
                int hours = Integer.parseInt(hoursStr);
                if (hours <= 0) {
                    new Alert(Alert.AlertType.ERROR, "Hours must be a positive number").show();
                    return;
                }

                if (subjectDAO.addSubject(name, hours)) {
                    subjectsList.clear();
                    subjectsList.addAll(subjectDAO.getAllSubjects());
                    txtSubjectName.clear();
                    txtSubjectHours.clear();
                }
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Hours must be a valid number").show();
            }
        });

        btnBackToMenuFromSubjects.setOnAction(e -> {
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle("Main Menu - Student Life Manager");
        });

        btnEditSubject.setOnAction(e -> {
            Subject selected = subjectsTableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                editSubject(selected);
            } else {
                new Alert(Alert.AlertType.WARNING, "Please select a subject to edit").show();
            }
        });

        btnDeleteSubject.setOnAction(e -> {
            Subject selected = subjectsTableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmAlert.setTitle("Confirm Delete");
                confirmAlert.setHeaderText("Delete Subject");
                confirmAlert.setContentText("Are you sure you want to delete '" + selected.getName() + "'?");
                confirmAlert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        if (subjectDAO.deleteSubject(selected.getName())) {
                            subjectsList.remove(selected);
                        }
                    }
                });
            } else {
                new Alert(Alert.AlertType.WARNING, "Please select a subject to delete").show();
            }
        });

        btnSave.setOnAction(e -> saveSettings(primaryStage, false));
        btnApplyNow.setOnAction(e -> saveSettings(primaryStage, true));
        btnCancel.setOnAction(e -> primaryStage.setScene(mainMenuScene));

        primaryStage.setTitle("Login - Student Life Manager");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void editSubject(Subject subject) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Edit Subject");
        dialog.setHeaderText("Edit Subject Information");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField editName = new TextField(subject.getName());
        TextField editHours = new TextField(String.valueOf(subject.getHours()));

        grid.add(new Label("Name:"), 0, 0);
        grid.add(editName, 1, 0);
        grid.add(new Label("Hours:"), 0, 1);
        grid.add(editHours, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait().ifPresent(response -> {
            if (response == saveButtonType) {
                String oldName = subject.getName();
                String newName = editName.getText().trim();
                String newHoursStr = editHours.getText().trim();

                if (!newName.isEmpty() && !newHoursStr.isEmpty()) {
                    try {
                        int newHours = Integer.parseInt(newHoursStr);
                        if (newHours > 0) {
                            if (subjectDAO.updateSubject(oldName, newName, newHours)) {
                                subject.setName(newName);
                                subject.setHours(newHours);
                                subjectsTableView.refresh();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Hours must be positive").show();
                        }
                    } catch (NumberFormatException ex) {
                        new Alert(Alert.AlertType.ERROR, "Hours must be a number").show();
                    }
                }
            }
        });
    }

    private void saveSettings(Stage primaryStage, boolean applyNow) {
        boolean darkMode = chkDarkMode.isSelected();
        boolean autoSave = chkAutoSave.isSelected();
        String fontSizeText = txtFontSize.getText().trim();
        String opacityText = txtOpacity.getText().trim();
        String errorMessage = "";
        int fontSize = 14;
        double opacity = 1.0;

        try {
            fontSize = Integer.parseInt(fontSizeText);
            if (fontSize < 10 || fontSize > 24) {
                errorMessage += "Font size must be between 10 and 24.\n";
            }
        } catch (NumberFormatException e) {
            errorMessage += "Font size must be a number.\n";
        }

        try {
            opacity = Double.parseDouble(opacityText);
            if (opacity < 0.0 || opacity > 1.0) {
                errorMessage += "Opacity must be between 0.0 and 1.0.\n";
            }
        } catch (NumberFormatException e) {
            errorMessage += "Opacity must be a number.\n";
        }

        if (!errorMessage.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, errorMessage).showAndWait();
            return;
        }

        if (applyNow) {
            primaryStage.setOpacity(opacity);
            if (darkMode) {
                applyDarkMode();
            } else {
                applyLightMode();
            }
            applyFontSize(fontSize);
        }

        new Alert(Alert.AlertType.INFORMATION,
                "Dark Mode: " + (darkMode ? "ON" : "OFF") + "\n"
                + "Font Size: " + fontSize + "\n"
                + "Opacity: " + opacity + "\n"
                + "Auto Save: " + (autoSave ? "ON" : "OFF")
        ).showAndWait();

        if (!applyNow) {
            primaryStage.setScene(mainMenuScene);
        }
    }

    private void applyDarkMode() {
        String darkStyle = "-fx-background-color: #2b2b2b;";
        loginScene.getRoot().setStyle(darkStyle);
        registerScene.getRoot().setStyle(darkStyle);
        mainMenuScene.getRoot().setStyle(darkStyle);
        taskManagerScene.getRoot().setStyle(darkStyle);
        examsScene.getRoot().setStyle(darkStyle);
        subjectsScene.getRoot().setStyle(darkStyle);
        settingsScene.getRoot().setStyle(darkStyle);

        String tableStyle = "-fx-background-color: #3b3b3b; -fx-control-inner-background: #3b3b3b; -fx-text-fill: white;";
        taskTableView.setStyle(tableStyle);
        examsTableView.setStyle(tableStyle);
        subjectsTableView.setStyle(tableStyle);

        applyLabelColor("white");
        applyTextFieldColor("white", "#3b3b3b");
    }

    private void applyLightMode() {
        String lightStyle = "-fx-background-color: white;";
        loginScene.getRoot().setStyle(lightStyle);
        registerScene.getRoot().setStyle(lightStyle);
        mainMenuScene.getRoot().setStyle(lightStyle);
        taskManagerScene.getRoot().setStyle(lightStyle);
        examsScene.getRoot().setStyle(lightStyle);
        subjectsScene.getRoot().setStyle(lightStyle);
        settingsScene.getRoot().setStyle(lightStyle);

        String tableStyle = "-fx-background-color: white; -fx-control-inner-background: white; -fx-text-fill: black;";
        taskTableView.setStyle(tableStyle);
        examsTableView.setStyle(tableStyle);
        subjectsTableView.setStyle(tableStyle);

        applyLabelColor("black");
        applyTextFieldColor("black", "white");
    }

    private void applyLabelColor(String color) {
        for (Scene scene : new Scene[]{loginScene, registerScene, mainMenuScene, taskManagerScene, examsScene, subjectsScene, settingsScene}) {
            scene.getRoot().lookupAll(".label").forEach(node -> {
                if (node instanceof Label) {
                    ((Label) node).setStyle(((Label) node).getStyle().replaceAll("-fx-text-fill:[^;]+;", "") + "-fx-text-fill: " + color + ";");
                }
            });
        }
        chkDarkMode.setStyle("-fx-text-fill: " + color + ";");
        chkAutoSave.setStyle("-fx-text-fill: " + color + ";");
    }

    private void applyTextFieldColor(String textColor, String bgColor) {
        String style = "-fx-text-fill: " + textColor + "; -fx-control-inner-background: " + bgColor + ";";
        txtUsername.setStyle(style);
        txtPassword.setStyle(style);
        txtTaskField.setStyle(style);
        txtFontSize.setStyle(style);
        txtOpacity.setStyle(style);
    }

    private void applyFontSize(int fontSize) {
        String fontStyle = "-fx-font-size: " + fontSize + "px;";
        for (Scene scene : new Scene[]{loginScene, registerScene, mainMenuScene, taskManagerScene, examsScene, subjectsScene, settingsScene}) {
            scene.getRoot().lookupAll(".label").forEach(node -> {
                if (node instanceof Label) {
                    String currentStyle = ((Label) node).getStyle();
                    ((Label) node).setStyle(currentStyle.replaceAll("-fx-font-size:[^;]+;", "") + fontStyle);
                }
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
