import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GradeCalculator {

	private static String gradeFile = "src/grades.txt";
	private Scanner fileReader;

	private ArrayList<String> firstName = new ArrayList<String>();
	private ArrayList<String> lastName = new ArrayList<String>();
	private ArrayList<Double> testScores = new ArrayList<Double>();

	private String splitLine[];
	private double average;
	private double classAverage;
	private String[] letterGrade = { "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F" };
	int gradeIndex = 0;
	int gradeScoreIndex = 0;

	public static void main(String[] args) {
		new GradeCalculator();
	}

	public GradeCalculator() {
		openFile();
		readFile();
		fileReader.close();
		printPerStudentAverage();
		printClassAverage();
	}

	public void openFile() {
		try {
			fileReader = new Scanner(new File(gradeFile));
			fileReader.useDelimiter(" ");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void readFile() {

		while (fileReader.hasNextLine()) {

			splitLine = fileReader.nextLine().split(" ");
			firstName.add(splitLine[0]);
			lastName.add(splitLine[1]);

			for (int i = 2; i <= 5; i++) {
				testScores.add(Double.parseDouble(splitLine[i]));
			}
		}
	}

	public void calculateStudentAverage() {

		average = (testScores.get(gradeScoreIndex) + testScores.get(gradeScoreIndex + 1)
				+ testScores.get(gradeScoreIndex + 2) + testScores.get(gradeScoreIndex + 3)) / 4;
		gradeScoreIndex += 4;
	}

	public void determainLetterGrade() {

		if (average <= 100 && average >= 91) {
			gradeIndex = 0;
		} else if (average <= 90.9 && average >= 90) {
			gradeIndex = 1;
		} else if (average <= 89.9 && average >= 89) {
			gradeIndex = 2;
		} else if (average <= 88.9 && average >= 81) {
			gradeIndex = 3;
		} else if (average <= 80.9 && average >= 80) {
			gradeIndex = 4;
		} else if (average <= 79.9 && average >= 79) {
			gradeIndex = 5;
		} else if (average <= 78.9 && average >= 71) {
			gradeIndex = 6;
		} else if (average <= 70.9 && average >= 70) {
			gradeIndex = 7;
		} else if (average <= 69.9 && average >= 69) {
			gradeIndex = 8;
		} else if (average <= 68.9 && average >= 61) {
			gradeIndex = 9;
		} else if (average <= 60.9 && average >= 60) {
			gradeIndex = 10;
		} else if (average < 60) {
			gradeIndex = 11;
		}
	}

	public void printPerStudentAverage() {
		System.out.printf("%-22s%-22s%-22s\n", "Student Name", "Test Score Average", "Letter Grade");

		for (int i = 0; i < firstName.size(); i++) {
			calculateStudentAverage();
			determainLetterGrade();
			System.out.printf("%-22s%-22s%-22s\n", firstName.get(i) + " " + lastName.get(i), average,
					letterGrade[gradeIndex]);
		}

	}

	public void printClassAverage() {
		double firstTestAverage = 0;
		int index = 0;
		for (int i = 0; i < firstName.size(); i++) {
			firstTestAverage += testScores.get(index);
			index += 4;
		}

		index = 1;
		double secondTestAverage = 0;
		for (int i = 0; i < firstName.size(); i++) {
			secondTestAverage += testScores.get(index);
			index += 4;
		}

		index = 2;
		double thirdTestAverage = 0;
		for (int i = 0; i < firstName.size(); i++) {
			thirdTestAverage += testScores.get(index);
			index += 4;
		}

		index = 3;
		double fourthTestAverage = 0;
		for (int i = 0; i < firstName.size(); i++) {
			fourthTestAverage += testScores.get(index);
			index += 4;
		}

		firstTestAverage = (firstTestAverage / firstName.size());
		secondTestAverage = (secondTestAverage / firstName.size());
		thirdTestAverage = (thirdTestAverage / firstName.size());
		fourthTestAverage = (fourthTestAverage / firstName.size());

		System.out.printf("\nClass Averages\n%-22s%-22s%-22s%-22s\n", "Test 1", "Test 2", "Test 3", "Test 4");
		System.out.printf("%-22s%-22s%-22s%-22s\n", firstTestAverage, secondTestAverage, thirdTestAverage, fourthTestAverage);

	}

}
