package com.company.cursach;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.company.cursach.Utils.print;

public class Controller {

    @FXML
    public Button SaveButton;

    @FXML
    public Button loadButton;

    @FXML
    public TextArea resultLabel;

    @FXML
    public Button deleteButton;

    @FXML
    public TextArea logger;

    @FXML
    public ComboBox<String> PositionComboBox;

    @FXML
    public TextField textFieldDelete;

    @FXML
    public TextField DepartmentTextField;

    @FXML
    public TextField SurnameTextField;

    @FXML
    public TextField universityTextField;

    private MyListImpl myList;

    private final String filePath = "myListInfo";

    @FXML
    public void initialize() {

        PositionComboBox.setItems(
                FXCollections.observableArrayList(
                        Arrays.stream(Position.values())
                                .map(Position::getName)
                                .collect(Collectors.toList())
                )
        );

        PositionComboBox.getSelectionModel().selectFirst();

        Tooltip tooltip = new Tooltip();
        tooltip.setText("Для удаления введите в текстовое поле id кафедры\n" +
                "если хотите удалить преподавателя ввести id кафедры\nи id преподавателя например -> 0 1");
        tooltip.setAutoHide(true);
        deleteButton.setTooltip(tooltip);
    }

    public void save() {
        logger.setText(Utils.save(filePath, myList));
    }

    public void load() {
        MyListImpl myList = Utils.load(filePath);
        if (myList == null) {
            logger.setText("Something went wrong in loading file");
            resultLabel.setText("Error :(");
        } else {
            this.myList = myList;
            resultLabel.setText(print(myList));
            logger.setText("Load success!");
        }
    }

    /**
     * Метод привязанный к кнопке удаления
     */
    public void delete() {
        final String deleteText = textFieldDelete.getText();
        if (!deleteText.isEmpty() && !deleteText.isBlank()) {
            String[] ids = deleteText.split(" ");
            // удаляем значит препода
            if (ids.length > 1) {
                int departmentId = Integer.parseInt(ids[0]);
                int teacherId = Integer.parseInt(ids[1]);
                try {
                    MyQueueImpl department = myList.get(departmentId);
                    department.removeById(teacherId);
                } catch (IndexOutOfBoundsException e) {
                    logger.setText("Id out of boundary!");
                }
                refresh();
                return;
            }
            // удаляем кафедру
            if (ids.length == 1) {
                int departmentId = Integer.parseInt(ids[0]);
                try {
                    myList.removeByIndex(departmentId);
                } catch (IndexOutOfBoundsException e) {
                    logger.setText("Id out of boundary!");
                }
                refresh();
                return;
            }

            logger.setText("Для удаления введите в текстовое поле id кафедры\n" +
                    "если хотите удалить преподавателя ввести id кафедры\nи id преподавателя например -> 0 1");
        } else {
            logger.setText("Enter id's what you want to delete");
            textFieldDelete.requestFocus();
        }
    }

    /**
     * Метод привязанный к кнопке добавления
     */
    public void add() {
        String department = DepartmentTextField.getText();
        String surname = SurnameTextField.getText();
        Position position = Arrays.stream(
                Position.values())
                .filter(pos ->
                        pos.getName().equals(PositionComboBox.getSelectionModel().getSelectedItem()))
                .findFirst()
                .get();

        if (!department.isBlank()) {
            // если поле surname пустое тогда просто добавляем кафедру
            if (surname.isBlank()) {
                myList.add(new MyQueueImpl(department));
            } else {
                MyQueueImpl queue = myList.getByName(department);
                if (queue != null) {
                    queue.add(new Teacher(surname, position));
                } else {
                    logger.setText("Такой кафедры нет!");
                }
            }
        } else {
            logger.setText("Введите название кафедры!");
            DepartmentTextField.requestFocus();
        }
        refresh();
    }

    /**
     * Метод загружающий тестовые данные
     */
    public void loadTestData(){
        MyListImpl myList = new MyListImpl("MGU");

        MyQueueImpl itDepartment = new MyQueueImpl("IT");
        itDepartment.add(new Teacher("Durov", Position.PROFESSOR));
        itDepartment.add(new Teacher("Gates", Position.ASSISTANT));
        itDepartment.add(new Teacher("Bezos", Position.SENIOR_LECTURER));

        MyQueueImpl lawyerDepartment = new MyQueueImpl("Lawyer");
        lawyerDepartment.add(new Teacher("Makarov", Position.PROFESSOR));
        lawyerDepartment.add(new Teacher("Helidze", Position.SENIOR_ASSISTANT));

        MyQueueImpl medicineDepartment = new MyQueueImpl("Medicine");
        medicineDepartment.add(new Teacher("Urgi", Position.ASSISTANT_PROFESSOR));

        MyQueueImpl aviationDepartment = new MyQueueImpl("Aviation");
        aviationDepartment.add(new Teacher("Gagarin", Position.ASSISTANT_PROFESSOR));
        aviationDepartment.add(new Teacher("Titova", Position.TEACHER));

        myList.add(itDepartment);
        myList.add(lawyerDepartment);
        myList.add(medicineDepartment);
        myList.add(aviationDepartment);

        this.myList = myList;
        refresh();
    }

    public void createUniversity(){
        if (!universityTextField.getText().isBlank()) {
            if (this.myList == null) {
                this.myList = new MyListImpl(universityTextField.getText());
                refresh();
            } else {
                logger.setText("Университет уже существует!");
            }
        } else {
            logger.setText("Введите название университета");
        }
    }

    private void refresh() {
        resultLabel.setText(print(myList));
    }
}