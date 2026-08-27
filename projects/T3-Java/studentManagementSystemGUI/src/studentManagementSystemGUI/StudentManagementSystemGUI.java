package studentManagementSystemGUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

// Student class with Serializable
class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private int age;

	public Student(String id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }

	public String[] toArray() {
		return new String[]{id, name, String.valueOf(age)};
	}

	@Override
	public String toString() {
		return id + " - " + name;
	}
}

// Course class with Serializable
class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private int credits;

	public Course(String code, String name, int credits) {
		this.code = code;
		this.name = name;
		this.credits = credits;
	}

	public String getCode() { return code; }
	public String getName() { return name; }
	public int getCredits() { return credits; }

	@Override
	public String toString() {
		return code + " - " + name + " (" + credits + " credits)";
	}
}

public class StudentManagementSystemGUI {

	private JFrame frame;
	private JTable studentTable, enrollmentTable, gradeTable;
	private DefaultTableModel studentTableModel, enrollmentTableModel, gradeTableModel;
	private TableRowSorter<DefaultTableModel> studentSorter;

	private List<Student> students = new ArrayList<>();
	private List<Course> courses = new ArrayList<>();
	private Map<String, List<String>> enrollments = new HashMap<>(); // Student ID -> List of Course Codes
	private Map<String, Map<String, String>> grades = new HashMap<>(); // Student ID -> (Course Code -> Grade)
	private Map<String, Map<String, String>> attendance = new HashMap<>(); // Student ID -> (Course Code -> Attendance %)

	private static final String DATA_DIR = "data";
	private static final String STUDENTS_FILE = "students.dat";
	private static final String COURSES_FILE = "courses.dat";
	private static final String ENROLLMENTS_FILE = "enrollments.dat";
	private static final String GRADES_FILE = "grades.dat";
	private static final String ATTENDANCE_FILE = "attendance.dat";

	public StudentManagementSystemGUI() {
		// Create data directory if it doesn't exist
		new File(DATA_DIR).mkdirs();

		// Load data
		loadData();

		// Initialize courses if empty
		if (courses.isEmpty()) {
			initializeDefaultCourses();
		}

		frame = new JFrame("Student Management System");
		frame.setSize(1200, 800);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Add window listener to save data on exit
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				saveData();
			}
		});

		JTabbedPane tabbedPane = new JTabbedPane();

		tabbedPane.add("Students", createStudentPanel());
		tabbedPane.add("Courses", createCoursePanel());
		tabbedPane.add("Enrollment", createEnrollmentPanel());
		tabbedPane.add("Grades", createGradePanel());
		tabbedPane.add("Attendance", createAttendancePanel());
		tabbedPane.add("Reports", createReportsPanel());

		frame.getContentPane().add(tabbedPane);
		frame.setVisible(true);
	}

	private void initializeDefaultCourses() {
		courses.add(new Course("CS101", "Introduction to Programming", 3));
		courses.add(new Course("CS201", "Data Structures", 4));
		courses.add(new Course("MATH101", "Calculus I", 3));
		courses.add(new Course("PHY101", "Physics I", 4));
		courses.add(new Course("ENG101", "English Composition", 3));
	}

	// ---------------- STUDENT PANEL ----------------
	private JPanel createStudentPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		// Create table
		studentTableModel = new DefaultTableModel(new String[]{"ID", "Name", "Age"}, 0) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		studentTable = new JTable(studentTableModel);
		studentSorter = new TableRowSorter<>(studentTableModel);
		studentTable.setRowSorter(studentSorter);
		studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentTable.setRowHeight(25);
		studentTable.setIntercellSpacing(new Dimension(5, 5));

		// Refresh student table
		refreshStudentTable();

		panel.add(new JScrollPane(studentTable), BorderLayout.CENTER);

		// Search panel
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField searchField = new JTextField(20);
		JButton searchBtn = new JButton("Search");
		JButton clearSearchBtn = new JButton("Clear");

		searchPanel.add(new JLabel("Search:"));
		searchPanel.add(searchField);
		searchPanel.add(searchBtn);
		searchPanel.add(clearSearchBtn);

		panel.add(searchPanel, BorderLayout.NORTH);

		// Input panel
		JPanel inputPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		JTextField idField = new JTextField(10);
		JTextField nameField = new JTextField(15);
		JTextField ageField = new JTextField(5);

		gbc.gridx = 0; gbc.gridy = 0;
		inputPanel.add(new JLabel("ID:"), gbc);
		gbc.gridx = 1;
		inputPanel.add(idField, gbc);

		gbc.gridx = 2;
		inputPanel.add(new JLabel("Name:"), gbc);
		gbc.gridx = 3;
		inputPanel.add(nameField, gbc);

		gbc.gridx = 4;
		inputPanel.add(new JLabel("Age:"), gbc);
		gbc.gridx = 5;
		inputPanel.add(ageField, gbc);

		JPanel buttonPanel = new JPanel();
		JButton addBtn = new JButton("Add Student");
		JButton updateBtn = new JButton("Update Student");
		JButton deleteBtn = new JButton("Delete Student");
		JButton clearBtn = new JButton("Clear Fields");

		buttonPanel.add(addBtn);
		buttonPanel.add(updateBtn);
		buttonPanel.add(deleteBtn);
		buttonPanel.add(clearBtn);

		gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 6;
		inputPanel.add(buttonPanel, gbc);

		panel.add(inputPanel, BorderLayout.SOUTH);

		// Search functionality
		searchBtn.addActionListener(e -> {
			String text = searchField.getText();
			if (text.trim().length() == 0) {
				studentSorter.setRowFilter(null);
			} else {
				try {
					studentSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				} catch (PatternSyntaxException ex) {
					JOptionPane.showMessageDialog(frame, "Invalid search pattern!");
				}
			}
		});

		clearSearchBtn.addActionListener(e -> {
			searchField.setText("");
			studentSorter.setRowFilter(null);
		});

		// Table selection listener
		studentTable.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && studentTable.getSelectedRow() >= 0) {
				int modelRow = studentTable.convertRowIndexToModel(studentTable.getSelectedRow());
				idField.setText(studentTableModel.getValueAt(modelRow, 0).toString());
				nameField.setText(studentTableModel.getValueAt(modelRow, 1).toString());
				ageField.setText(studentTableModel.getValueAt(modelRow, 2).toString());
			}
		});

		// Add button action
		addBtn.addActionListener(e -> {
			try {
				String id = idField.getText().trim();
				String name = nameField.getText().trim();
				String ageText = ageField.getText().trim();

				// Validation
				if (id.isEmpty() || name.isEmpty() || ageText.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "All fields are required!");
					return;
				}

				// Check for duplicate ID
				boolean duplicate = students.stream().anyMatch(s -> s.getId().equals(id));
				if (duplicate) {
					JOptionPane.showMessageDialog(frame, "Student ID already exists!");
					return;
				}

				int age = Integer.parseInt(ageText);
				if (age < 0 || age > 150) {
					JOptionPane.showMessageDialog(frame, "Please enter a valid age (0-150)!");
					return;
				}

				Student student = new Student(id, name, age);
				students.add(student);
				studentTableModel.addRow(student.toArray());

				// Clear fields
				idField.setText("");
				nameField.setText("");
				ageField.setText("");

				JOptionPane.showMessageDialog(frame, "Student Added Successfully!");
				saveData();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(frame, "Age must be a number!");
			}
		});

		// Update button action
		updateBtn.addActionListener(e -> {
			int selectedRow = studentTable.getSelectedRow();
			if (selectedRow >= 0) {
				int modelRow = studentTable.convertRowIndexToModel(selectedRow);
				String id = (String) studentTableModel.getValueAt(modelRow, 0);
				String newName = nameField.getText().trim();
				String newAgeText = ageField.getText().trim();

				if (newName.isEmpty() || newAgeText.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Name and Age cannot be empty!");
					return;
				}

				try {
					int newAge = Integer.parseInt(newAgeText);

					// Update table
					studentTableModel.setValueAt(newName, modelRow, 1);
					studentTableModel.setValueAt(newAge, modelRow, 2);

					// Update ArrayList
					for (Student student : students) {
						if (student.getId().equals(id)) {
							student.setName(newName);
							student.setAge(newAge);
							break;
						}
					}

					JOptionPane.showMessageDialog(frame, "Student Updated Successfully!");
					saveData();

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Age must be a number!");
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Please select a student to update!");
			}
		});

		// Delete button action
		deleteBtn.addActionListener(e -> {
			int selectedRow = studentTable.getSelectedRow();
			if (selectedRow >= 0) {
				int confirm = JOptionPane.showConfirmDialog(frame,
						"Are you sure you want to delete this student?\nThis will also delete all enrollments, grades, and attendance records.",
						"Confirm Delete",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);

				if (confirm == JOptionPane.YES_OPTION) {
					int modelRow = studentTable.convertRowIndexToModel(selectedRow);
					String id = (String) studentTableModel.getValueAt(modelRow, 0);

					// Remove from table
					studentTableModel.removeRow(modelRow);

					// Remove from list
					students.removeIf(student -> student.getId().equals(id));

					// Remove enrollments and grades
					enrollments.remove(id);
					grades.remove(id);
					attendance.remove(id);

					JOptionPane.showMessageDialog(frame, "Student Deleted Successfully!");
					saveData();
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Please select a student to delete!");
			}
		});

		// Clear button action
		clearBtn.addActionListener(e -> {
			idField.setText("");
			nameField.setText("");
			ageField.setText("");
			studentTable.clearSelection();
		});

		return panel;
	}

	// ---------------- COURSE PANEL ----------------
	private JPanel createCoursePanel() {
		JPanel panel = new JPanel(new BorderLayout());

		// Create table for courses
		String[] columns = {"Course Code", "Course Name", "Credits"};
		DefaultTableModel courseTableModel = new DefaultTableModel(columns, 0) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable courseTable = new JTable(courseTableModel);
		courseTable.setRowHeight(25);
		courseTable.setIntercellSpacing(new Dimension(5, 5));

		// Load courses
		refreshCourseTable(courseTableModel);

		panel.add(new JScrollPane(courseTable), BorderLayout.CENTER);

		// Input panel
		JPanel inputPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		JTextField codeField = new JTextField(10);
		JTextField nameField = new JTextField(15);
		JTextField creditsField = new JTextField(5);

		gbc.gridx = 0; gbc.gridy = 0;
		inputPanel.add(new JLabel("Code:"), gbc);
		gbc.gridx = 1;
		inputPanel.add(codeField, gbc);

		gbc.gridx = 2;
		inputPanel.add(new JLabel("Name:"), gbc);
		gbc.gridx = 3;
		inputPanel.add(nameField, gbc);

		gbc.gridx = 4;
		inputPanel.add(new JLabel("Credits:"), gbc);
		gbc.gridx = 5;
		inputPanel.add(creditsField, gbc);

		JPanel buttonPanel = new JPanel();
		JButton addBtn = new JButton("Add Course");
		JButton deleteBtn = new JButton("Delete Course");

		buttonPanel.add(addBtn);
		buttonPanel.add(deleteBtn);

		gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 6;
		inputPanel.add(buttonPanel, gbc);

		panel.add(inputPanel, BorderLayout.SOUTH);

		// Add button action
		addBtn.addActionListener(e -> {
			try {
				String code = codeField.getText().trim().toUpperCase();
				String name = nameField.getText().trim();
				String creditsText = creditsField.getText().trim();

				if (code.isEmpty() || name.isEmpty() || creditsText.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "All fields are required!");
					return;
				}

				// Check for duplicate course code
				boolean duplicate = courses.stream().anyMatch(c -> c.getCode().equals(code));
				if (duplicate) {
					JOptionPane.showMessageDialog(frame, "Course code already exists!");
					return;
				}

				int credits = Integer.parseInt(creditsText);
				if (credits <= 0 || credits > 6) {
					JOptionPane.showMessageDialog(frame, "Credits must be between 1 and 6!");
					return;
				}

				Course course = new Course(code, name, credits);
				courses.add(course);
				courseTableModel.addRow(new String[]{code, name, String.valueOf(credits)});

				// Clear fields
				codeField.setText("");
				nameField.setText("");
				creditsField.setText("");

				JOptionPane.showMessageDialog(frame, "Course Added Successfully!");
				saveData();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(frame, "Credits must be a number!");
			}
		});

		// Delete button action
		deleteBtn.addActionListener(e -> {
			int selectedRow = courseTable.getSelectedRow();
			if (selectedRow >= 0) {
				String code = (String) courseTableModel.getValueAt(selectedRow, 0);

				// Check if course is being used
				boolean isUsed = enrollments.values().stream()
						.anyMatch(list -> list.contains(code));

				if (isUsed) {
					int confirm = JOptionPane.showConfirmDialog(frame,
							"This course has enrollments. Deleting it will remove all related data. Continue?",
							"Warning",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);

					if (confirm != JOptionPane.YES_OPTION) {
						return;
					}

					// Remove enrollments for this course
					enrollments.values().forEach(list -> list.remove(code));

					// Remove grades for this course
					grades.values().forEach(map -> map.remove(code));

					// Remove attendance for this course
					attendance.values().forEach(map -> map.remove(code));
				}

				courseTableModel.removeRow(selectedRow);
				courses.removeIf(course -> course.getCode().equals(code));

				JOptionPane.showMessageDialog(frame, "Course Deleted Successfully!");
				saveData();

			} else {
				JOptionPane.showMessageDialog(frame, "Please select a course to delete!");
			}
		});

		return panel;
	}

	// ---------------- ENROLLMENT PANEL ----------------
	private JPanel createEnrollmentPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		// Create table for enrollments
		enrollmentTableModel = new DefaultTableModel(new String[]{"Student ID", "Student Name", "Course Code", "Course Name"}, 0) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		enrollmentTable = new JTable(enrollmentTableModel);
		enrollmentTable.setRowHeight(25);
		enrollmentTable.setIntercellSpacing(new Dimension(5, 5));

		panel.add(new JScrollPane(enrollmentTable), BorderLayout.CENTER);

		// Control panel
		JPanel controlPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		JComboBox<Student> studentBox = new JComboBox<>();
		JComboBox<Course> courseBox = new JComboBox<>();

		// Custom renderers to show meaningful text
		studentBox.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				if (value instanceof Student) {
					value = value.toString();
				}
				return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			}
		});

		courseBox.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				if (value instanceof Course) {
					value = value.toString();
				}
				return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			}
		});

		JButton enrollBtn = new JButton("Enroll");
		JButton withdrawBtn = new JButton("Withdraw");
		JButton refreshBtn = new JButton("Refresh");

		gbc.gridx = 0; gbc.gridy = 0;
		controlPanel.add(new JLabel("Student:"), gbc);
		gbc.gridx = 1;
		controlPanel.add(studentBox, gbc);

		gbc.gridx = 2;
		controlPanel.add(new JLabel("Course:"), gbc);
		gbc.gridx = 3;
		controlPanel.add(courseBox, gbc);

		gbc.gridx = 4;
		controlPanel.add(enrollBtn, gbc);
		gbc.gridx = 5;
		controlPanel.add(withdrawBtn, gbc);
		gbc.gridx = 6;
		controlPanel.add(refreshBtn, gbc);

		panel.add(controlPanel, BorderLayout.NORTH);

		// Refresh combo boxes and table
		refreshEnrollmentData(studentBox, courseBox);

		// Enroll button action
		enrollBtn.addActionListener(e -> {
			Student student = (Student) studentBox.getSelectedItem();
			Course course = (Course) courseBox.getSelectedItem();

			if (student == null || course == null) {
				JOptionPane.showMessageDialog(frame, "Please select both student and course!");
				return;
			}

			String studentId = student.getId();
			String courseCode = course.getCode();

			// Check if already enrolled
			enrollments.putIfAbsent(studentId, new ArrayList<>());
			if (enrollments.get(studentId).contains(courseCode)) {
				JOptionPane.showMessageDialog(frame, "Student is already enrolled in this course!");
				return;
			}

			enrollments.get(studentId).add(courseCode);

			// Add to table
			enrollmentTableModel.addRow(new String[]{
					studentId,
					student.getName(),
					courseCode,
					course.getName()
			});

			JOptionPane.showMessageDialog(frame, "Enrollment Successful!");
			saveData();
		});

		// Withdraw button action
		withdrawBtn.addActionListener(e -> {
			int selectedRow = enrollmentTable.getSelectedRow();
			if (selectedRow >= 0) {
				String studentId = (String) enrollmentTableModel.getValueAt(selectedRow, 0);
				String courseCode = (String) enrollmentTableModel.getValueAt(selectedRow, 2);

				int confirm = JOptionPane.showConfirmDialog(frame,
						"Are you sure you want to withdraw this student from the course?",
						"Confirm Withdrawal",
						JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					// Remove from enrollments
					if (enrollments.containsKey(studentId)) {
						enrollments.get(studentId).remove(courseCode);
					}

					// Remove from table
					enrollmentTableModel.removeRow(selectedRow);

					// Remove grades for this course
					if (grades.containsKey(studentId)) {
						grades.get(studentId).remove(courseCode);
					}

					// Remove attendance for this course
					if (attendance.containsKey(studentId)) {
						attendance.get(studentId).remove(courseCode);
					}

					JOptionPane.showMessageDialog(frame, "Withdrawal Successful!");
					saveData();
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Please select an enrollment to withdraw!");
			}
		});

		// Refresh button action
		refreshBtn.addActionListener(e -> {
			refreshEnrollmentData(studentBox, courseBox);
		});

		return panel;
	}

	// ---------------- GRADES PANEL ----------------
	private JPanel createGradePanel() {
		JPanel panel = new JPanel(new BorderLayout());

		// Create table for grades
		gradeTableModel = new DefaultTableModel(new String[]{"Student ID", "Student Name", "Course Code", "Course Name", "Grade", "GPA Value"}, 0) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		gradeTable = new JTable(gradeTableModel);
		gradeTable.setRowHeight(25);
		gradeTable.setIntercellSpacing(new Dimension(5, 5));

		panel.add(new JScrollPane(gradeTable), BorderLayout.CENTER);

		// Control panel
		JPanel controlPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		JComboBox<Student> studentBox = new JComboBox<>();
		JComboBox<String> courseBox = new JComboBox<>();
		JTextField gradeField = new JTextField(5);
		JComboBox<String> gradeDropdown = new JComboBox<>(new String[]{"A", "B", "C", "D", "F", "90", "80", "70", "60", "50"});

		// Use dropdown for grades
		gradeDropdown.setEditable(true);

		JButton assignBtn = new JButton("Assign/Update Grade");
		JButton refreshBtn = new JButton("Refresh");

		gbc.gridx = 0; gbc.gridy = 0;
		controlPanel.add(new JLabel("Student:"), gbc);
		gbc.gridx = 1;
		controlPanel.add(studentBox, gbc);

		gbc.gridx = 2;
		controlPanel.add(new JLabel("Course:"), gbc);
		gbc.gridx = 3;
		controlPanel.add(courseBox, gbc);

		gbc.gridx = 4;
		controlPanel.add(new JLabel("Grade:"), gbc);
		gbc.gridx = 5;
		controlPanel.add(gradeDropdown, gbc);

		gbc.gridx = 6;
		controlPanel.add(assignBtn, gbc);
		gbc.gridx = 7;
		controlPanel.add(refreshBtn, gbc);

		panel.add(controlPanel, BorderLayout.NORTH);

		// Custom renderer for student box
		studentBox.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				if (value instanceof Student) {
					value = value.toString();
				}
				return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			}
		});

		// Student selection listener
		studentBox.addActionListener(e -> {
			updateCourseBoxForGrades(studentBox, courseBox);
		});

		// Refresh button action
		refreshBtn.addActionListener(e -> {
			refreshGradeData(studentBox, courseBox);
		});

		// Assign grade button action
		assignBtn.addActionListener(e -> {
			Student student = (Student) studentBox.getSelectedItem();
			String courseCode = (String) courseBox.getSelectedItem();
			String grade = (String) gradeDropdown.getSelectedItem();

			if (student == null || courseCode == null || grade == null || grade.trim().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Please select student, course, and enter grade!");
				return;
			}

			String studentId = student.getId();

			// Check if student is enrolled in this course
			if (!enrollments.containsKey(studentId) || !enrollments.get(studentId).contains(courseCode)) {
				JOptionPane.showMessageDialog(frame, "Student is not enrolled in this course!");
				return;
			}

			// Validate grade format
			if (!isValidGrade(grade)) {
				JOptionPane.showMessageDialog(frame, "Please enter a valid grade (A, B, C, D, F or numeric 0-100)!");
				return;
			}

			grades.putIfAbsent(studentId, new HashMap<>());
			grades.get(studentId).put(courseCode, grade);

			refreshGradeTable();

			JOptionPane.showMessageDialog(frame, "Grade Assigned Successfully!");
			saveData();
		});

		// Initial data load
		refreshGradeData(studentBox, courseBox);

		return panel;
	}

	// ---------------- ATTENDANCE PANEL ----------------
	private JPanel createAttendancePanel() {
		JPanel panel = new JPanel(new BorderLayout());

		// Create table for attendance
		String[] columns = {"Student ID", "Student Name", "Course Code", "Course Name", "Attendance %"};
		DefaultTableModel attendanceTableModel = new DefaultTableModel(columns, 0) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable attendanceTable = new JTable(attendanceTableModel);
		attendanceTable.setRowHeight(25);
		attendanceTable.setIntercellSpacing(new Dimension(5, 5));

		panel.add(new JScrollPane(attendanceTable), BorderLayout.CENTER);

		// Control panel
		JPanel controlPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		JComboBox<Student> studentBox = new JComboBox<>();
		JComboBox<String> courseBox = new JComboBox<>();
		JTextField attendanceField = new JTextField(5);
		JSlider attendanceSlider = new JSlider(0, 100, 75);

		attendanceSlider.setMajorTickSpacing(25);
		attendanceSlider.setMinorTickSpacing(5);
		attendanceSlider.setPaintTicks(true);
		attendanceSlider.setPaintLabels(true);

		JButton markBtn = new JButton("Mark Attendance");
		JButton refreshBtn = new JButton("Refresh");

		gbc.gridx = 0; gbc.gridy = 0;
		controlPanel.add(new JLabel("Student:"), gbc);
		gbc.gridx = 1;
		controlPanel.add(studentBox, gbc);

		gbc.gridx = 2;
		controlPanel.add(new JLabel("Course:"), gbc);
		gbc.gridx = 3;
		controlPanel.add(courseBox, gbc);

		gbc.gridx = 4;
		controlPanel.add(new JLabel("Attendance %:"), gbc);
		gbc.gridx = 5;
		controlPanel.add(attendanceSlider, gbc);
		gbc.gridx = 6;
		controlPanel.add(attendanceField, gbc);

		gbc.gridx = 7;
		controlPanel.add(markBtn, gbc);
		gbc.gridx = 8;
		controlPanel.add(refreshBtn, gbc);

		panel.add(controlPanel, BorderLayout.NORTH);

		// Custom renderer for student box
		studentBox.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				if (value instanceof Student) {
					value = value.toString();
				}
				return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			}
		});

		// Link slider and text field
		attendanceSlider.addChangeListener(e -> {
			attendanceField.setText(String.valueOf(attendanceSlider.getValue()));
		});

		attendanceField.addActionListener(e -> {
			try {
				int value = Integer.parseInt(attendanceField.getText());
				if (value >= 0 && value <= 100) {
					attendanceSlider.setValue(value);
				}
			} catch (NumberFormatException ex) {
				// Ignore invalid input
			}
		});

		// Student selection listener
		studentBox.addActionListener(e -> {
			updateCourseBoxForAttendance(studentBox, courseBox);
		});

		// Refresh button
		refreshBtn.addActionListener(e -> {
			refreshAttendanceData(studentBox, courseBox, attendanceTableModel);
		});

		// Mark attendance button
		markBtn.addActionListener(e -> {
			Student student = (Student) studentBox.getSelectedItem();
			String courseCode = (String) courseBox.getSelectedItem();
			String attendanceText = attendanceField.getText();

			if (student == null || courseCode == null || attendanceText.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Please select student, course, and enter attendance!");
				return;
			}

			try {
				int attendancePercent = Integer.parseInt(attendanceText);
				if (attendancePercent < 0 || attendancePercent > 100) {
					JOptionPane.showMessageDialog(frame, "Attendance must be between 0 and 100!");
					return;
				}

				String studentId = student.getId();

				// Check if enrolled
				if (!enrollments.containsKey(studentId) || !enrollments.get(studentId).contains(courseCode)) {
					JOptionPane.showMessageDialog(frame, "Student is not enrolled in this course!");
					return;
				}

				attendance.putIfAbsent(studentId, new HashMap<>());
				attendance.get(studentId).put(courseCode, String.valueOf(attendancePercent));

				refreshAttendanceData(studentBox, courseBox, attendanceTableModel);

				JOptionPane.showMessageDialog(frame, "Attendance Marked Successfully!");
				saveData();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(frame, "Please enter a valid number!");
			}
		});

		// Initial load
		refreshAttendanceData(studentBox, courseBox, attendanceTableModel);

		return panel;
	}

	// ---------------- REPORTS PANEL ----------------
	private JPanel createReportsPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		JTabbedPane reportTabs = new JTabbedPane();

		// Student Report
		reportTabs.add("Student Report", createStudentReportPanel());

		// Course Report
		reportTabs.add("Course Report", createCourseReportPanel());

		// GPA Report
		reportTabs.add("GPA Report", createGPAReportPanel());

		panel.add(reportTabs, BorderLayout.CENTER);

		return panel;
	}

	private JPanel createStudentReportPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		String[] columns = {"Student ID", "Name", "Age", "Courses Enrolled", "Average GPA"};
		DefaultTableModel reportModel = new DefaultTableModel(columns, 0) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable reportTable = new JTable(reportModel);
		reportTable.setRowHeight(25);
		reportTable.setIntercellSpacing(new Dimension(5, 5));

		JButton generateBtn = new JButton("Generate Report");
		JButton exportBtn = new JButton("Export to CSV");

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(generateBtn);
		buttonPanel.add(exportBtn);

		panel.add(buttonPanel, BorderLayout.NORTH);
		panel.add(new JScrollPane(reportTable), BorderLayout.CENTER);

		generateBtn.addActionListener(e -> {
			reportModel.setRowCount(0);
			for (Student student : students) {
				String id = student.getId();
				int enrolledCount = enrollments.getOrDefault(id, new ArrayList<>()).size();
				double gpa = calculateGPA(id);

				reportModel.addRow(new Object[]{
						id,
						student.getName(),
						student.getAge(),
						enrolledCount,
						String.format("%.2f", gpa)
				});
			}
		});

		exportBtn.addActionListener(e -> {
			exportToCSV(reportModel, "student_report.csv");
		});

		return panel;
	}

	private JPanel createCourseReportPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		String[] columns = {"Course Code", "Course Name", "Credits", "Enrolled Students", "Average Grade"};
		DefaultTableModel reportModel = new DefaultTableModel(columns, 0) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable reportTable = new JTable(reportModel);
		reportTable.setRowHeight(25);
		reportTable.setIntercellSpacing(new Dimension(5, 5));

		JButton generateBtn = new JButton("Generate Report");
		JButton exportBtn = new JButton("Export to CSV");

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(generateBtn);
		buttonPanel.add(exportBtn);

		panel.add(buttonPanel, BorderLayout.NORTH);
		panel.add(new JScrollPane(reportTable), BorderLayout.CENTER);

		generateBtn.addActionListener(e -> {
			reportModel.setRowCount(0);
			for (Course course : courses) {
				String code = course.getCode();
				int enrolledCount = 0;
				double totalGradePoints = 0;
				int gradeCount = 0;

				for (String studentId : enrollments.keySet()) {
					if (enrollments.get(studentId).contains(code)) {
						enrolledCount++;
						if (grades.containsKey(studentId) && grades.get(studentId).containsKey(code)) {
							double points = gradeToPoints(grades.get(studentId).get(code));
							if (points >= 0) {
								totalGradePoints += points;
								gradeCount++;
							}
						}
					}
				}

				double avgGrade = gradeCount > 0 ? totalGradePoints / gradeCount : 0;

				reportModel.addRow(new Object[]{
						code,
						course.getName(),
						course.getCredits(),
						enrolledCount,
						String.format("%.2f", avgGrade)
				});
			}
		});

		exportBtn.addActionListener(e -> {
			exportToCSV(reportModel, "course_report.csv");
		});

		return panel;
	}

	private JPanel createGPAReportPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		String[] columns = {"Rank", "Student ID", "Student Name", "GPA", "Academic Standing"};
		DefaultTableModel reportModel = new DefaultTableModel(columns, 0) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JTable reportTable = new JTable(reportModel);
		reportTable.setRowHeight(25);
		reportTable.setIntercellSpacing(new Dimension(5, 5));

		JButton generateBtn = new JButton("Generate GPA Report");
		JButton exportBtn = new JButton("Export to CSV");

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(generateBtn);
		buttonPanel.add(exportBtn);

		panel.add(buttonPanel, BorderLayout.NORTH);
		panel.add(new JScrollPane(reportTable), BorderLayout.CENTER);

		generateBtn.addActionListener(e -> {
			reportModel.setRowCount(0);

			// Create list of students with GPA
			List<Object[]> studentGPAs = new ArrayList<>();
			for (Student student : students) {
				double gpa = calculateGPA(student.getId());
				studentGPAs.add(new Object[]{student, gpa});
			}

			// Sort by GPA descending
			studentGPAs.sort((a, b) -> Double.compare((double) b[1], (double) a[1]));

			// Add to table
			int rank = 1;
			for (Object[] data : studentGPAs) {
				Student student = (Student) data[0];
				double gpa = (double) data[1];

				String standing;
				if (gpa >= 3.5) {
					standing = "Dean's List";
				} else if (gpa >= 3.0) {
					standing = "Good Standing";
				} else if (gpa >= 2.0) {
					standing = "Satisfactory";
				} else {
					standing = "Academic Probation";
				}

				reportModel.addRow(new Object[]{
						rank++,
						student.getId(),
						student.getName(),
						String.format("%.2f", gpa),
						standing
				});
			}
		});

		exportBtn.addActionListener(e -> {
			exportToCSV(reportModel, "gpa_report.csv");
		});

		return panel;
	}

	// ---------------- HELPER METHODS ----------------

	private void refreshStudentTable() {
		studentTableModel.setRowCount(0);
		for (Student student : students) {
			studentTableModel.addRow(student.toArray());
		}
	}

	private void refreshCourseTable(DefaultTableModel model) {
		model.setRowCount(0);
		for (Course course : courses) {
			model.addRow(new String[]{course.getCode(), course.getName(), String.valueOf(course.getCredits())});
		}
	}

	private void refreshEnrollmentData(JComboBox<Student> studentBox, JComboBox<Course> courseBox) {
		// Refresh student combo box
		studentBox.removeAllItems();
		for (Student student : students) {
			studentBox.addItem(student);
		}

		// Refresh course combo box
		courseBox.removeAllItems();
		for (Course course : courses) {
			courseBox.addItem(course);
		}

		// Refresh enrollment table
		enrollmentTableModel.setRowCount(0);
		for (Map.Entry<String, List<String>> entry : enrollments.entrySet()) {
			String studentId = entry.getKey();
			Student student = findStudentById(studentId);
			if (student == null) {
				continue;
			}

			for (String courseCode : entry.getValue()) {
				Course course = findCourseByCode(courseCode);
				if (course == null) {
					continue;
				}

				enrollmentTableModel.addRow(new String[]{
						studentId,
						student.getName(),
						courseCode,
						course.getName()
				});
			}
		}
	}

	private void refreshGradeData(JComboBox<Student> studentBox, JComboBox<String> courseBox) {
		// Refresh student combo box
		studentBox.removeAllItems();
		for (Student student : students) {
			studentBox.addItem(student);
		}

		// Update course box if student selected
		updateCourseBoxForGrades(studentBox, courseBox);

		// Refresh grade table
		refreshGradeTable();
	}

	private void refreshGradeTable() {
		gradeTableModel.setRowCount(0);
		for (Map.Entry<String, Map<String, String>> studentGrades : grades.entrySet()) {
			String studentId = studentGrades.getKey();
			Student student = findStudentById(studentId);
			if (student == null) {
				continue;
			}

			for (Map.Entry<String, String> courseGrade : studentGrades.getValue().entrySet()) {
				String courseCode = courseGrade.getKey();
				String grade = courseGrade.getValue();
				Course course = findCourseByCode(courseCode);

				gradeTableModel.addRow(new String[]{
						studentId,
						student.getName(),
						courseCode,
						course != null ? course.getName() : "Unknown",
								grade,
								String.format("%.2f", gradeToPoints(grade))
				});
			}
		}
	}

	private void refreshAttendanceData(JComboBox<Student> studentBox, JComboBox<String> courseBox, DefaultTableModel tableModel) {
		// Refresh student combo box
		studentBox.removeAllItems();
		for (Student student : students) {
			studentBox.addItem(student);
		}

		// Update course box if student selected
		updateCourseBoxForAttendance(studentBox, courseBox);

		// Refresh attendance table
		tableModel.setRowCount(0);
		for (Map.Entry<String, Map<String, String>> studentAttendance : attendance.entrySet()) {
			String studentId = studentAttendance.getKey();
			Student student = findStudentById(studentId);
			if (student == null) {
				continue;
			}

			for (Map.Entry<String, String> courseAttendance : studentAttendance.getValue().entrySet()) {
				String courseCode = courseAttendance.getKey();
				String attendancePercent = courseAttendance.getValue();
				Course course = findCourseByCode(courseCode);

				tableModel.addRow(new String[]{
						studentId,
						student.getName(),
						courseCode,
						course != null ? course.getName() : "Unknown",
								attendancePercent + "%"
				});
			}
		}
	}

	private void updateCourseBoxForGrades(JComboBox<Student> studentBox, JComboBox<String> courseBox) {
		courseBox.removeAllItems();
		Student student = (Student) studentBox.getSelectedItem();
		if (student != null && enrollments.containsKey(student.getId())) {
			for (String courseCode : enrollments.get(student.getId())) {
				courseBox.addItem(courseCode);
			}
		}
	}

	private void updateCourseBoxForAttendance(JComboBox<Student> studentBox, JComboBox<String> courseBox) {
		courseBox.removeAllItems();
		Student student = (Student) studentBox.getSelectedItem();
		if (student != null && enrollments.containsKey(student.getId())) {
			for (String courseCode : enrollments.get(student.getId())) {
				courseBox.addItem(courseCode);
			}
		}
	}

	private Student findStudentById(String id) {
		for (Student student : students) {
			if (student.getId().equals(id)) {
				return student;
			}
		}
		return null;
	}

	private Course findCourseByCode(String code) {
		for (Course course : courses) {
			if (course.getCode().equals(code)) {
				return course;
			}
		}
		return null;
	}

	private boolean isValidGrade(String grade) {
		if (grade == null) {
			return false;
		}
		if (grade.matches("[ABCDF]")) {
			return true;
		}
		try {
			int numGrade = Integer.parseInt(grade);
			return numGrade >= 0 && numGrade <= 100;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private double gradeToPoints(String grade) {
		if (grade == null) {
			return 0.0;
		}

		// Letter grades
		switch (grade.toUpperCase()) {
		case "A": return 4.0;
		case "B": return 3.0;
		case "C": return 2.0;
		case "D": return 1.0;
		case "F": return 0.0;
		}

		// Numeric grades
		try {
			int numGrade = Integer.parseInt(grade);
			if (numGrade >= 90) {
				return 4.0;
			}
			if (numGrade >= 80) {
				return 3.0;
			}
			if (numGrade >= 70) {
				return 2.0;
			}
			if (numGrade >= 60) {
				return 1.0;
			}
			return 0.0;
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}

	private double calculateGPA(String studentId) {
		if (!grades.containsKey(studentId)) {
			return 0.0;
		}

		Map<String, String> studentGrades = grades.get(studentId);
		if (studentGrades.isEmpty()) {
			return 0.0;
		}

		double totalPoints = 0.0;
		int totalCredits = 0;

		for (Map.Entry<String, String> entry : studentGrades.entrySet()) {
			String courseCode = entry.getKey();
			String grade = entry.getValue();

			Course course = findCourseByCode(courseCode);
			if (course != null) {
				totalPoints += gradeToPoints(grade) * course.getCredits();
				totalCredits += course.getCredits();
			}
		}

		return totalCredits > 0 ? totalPoints / totalCredits : 0.0;
	}

	private void exportToCSV(DefaultTableModel model, String filename) {
		try (PrintWriter writer = new PrintWriter(new File(DATA_DIR, filename))) {
			// Write headers
			for (int i = 0; i < model.getColumnCount(); i++) {
				writer.print(model.getColumnName(i));
				if (i < model.getColumnCount() - 1) {
					writer.print(",");
				}
			}
			writer.println();

			// Write data
			for (int i = 0; i < model.getRowCount(); i++) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					Object value = model.getValueAt(i, j);
					writer.print(value != null ? value.toString() : "");
					if (j < model.getColumnCount() - 1) {
						writer.print(",");
					}
				}
				writer.println();
			}

			JOptionPane.showMessageDialog(frame, "Report exported to " + filename);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Error exporting report: " + e.getMessage());
		}
	}

	// ---------------- DATA PERSISTENCE ----------------

	private void saveData() {
		try {
			// Save students
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_DIR + "/" + STUDENTS_FILE))) {
				oos.writeObject(new ArrayList<>(students));
			}

			// Save courses
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_DIR + "/" + COURSES_FILE))) {
				oos.writeObject(new ArrayList<>(courses));
			}

			// Save enrollments
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_DIR + "/" + ENROLLMENTS_FILE))) {
				oos.writeObject(new HashMap<>(enrollments));
			}

			// Save grades
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_DIR + "/" + GRADES_FILE))) {
				oos.writeObject(new HashMap<>(grades));
			}

			// Save attendance
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_DIR + "/" + ATTENDANCE_FILE))) {
				oos.writeObject(new HashMap<>(attendance));
			}

			System.out.println("Data saved successfully at " + new Date());

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error saving data: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private void loadData() {
		try {
			// Load students
			File file = new File(DATA_DIR + "/" + STUDENTS_FILE);
			if (file.exists()) {
				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
					students = (List<Student>) ois.readObject();
				}
			}

			// Load courses
			file = new File(DATA_DIR + "/" + COURSES_FILE);
			if (file.exists()) {
				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
					courses = (List<Course>) ois.readObject();
				}
			}

			// Load enrollments
			file = new File(DATA_DIR + "/" + ENROLLMENTS_FILE);
			if (file.exists()) {
				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
					enrollments = (Map<String, List<String>>) ois.readObject();
				}
			}

			// Load grades
			file = new File(DATA_DIR + "/" + GRADES_FILE);
			if (file.exists()) {
				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
					grades = (Map<String, Map<String, String>>) ois.readObject();
				}
			}

			// Load attendance
			file = new File(DATA_DIR + "/" + ATTENDANCE_FILE);
			if (file.exists()) {
				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
					attendance = (Map<String, Map<String, String>>) ois.readObject();
				}
			}

			System.out.println("Data loaded successfully from " + new Date());

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error loading data: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		// Set Look and Feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(() -> new StudentManagementSystemGUI());
	}
}
