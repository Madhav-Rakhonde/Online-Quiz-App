import java.util.*;

class Question {
    private String questionText;
    private String[] options;
    public char correctOption;
    private String explanation;

    public Question(String questionText, String[] options, char correctOption, String explanation) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = Character.toUpperCase(correctOption);
        this.explanation = explanation;
    }

    public void displayQuestion(int qNum) {
        System.out.println("Q" + qNum + ": " + questionText);
        char optionLabel = 'A';
        for (String option : options) {
            System.out.println(optionLabel + ". " + option);
            optionLabel++;
        }
    }

    public boolean isCorrect(char userAnswer) {
        return Character.toUpperCase(userAnswer) == correctOption;
    }

    public String getCorrectAnswer() {
        return correctOption + ". " + options[correctOption - 'A'];
    }

    public String getExplanation() {
        return explanation;
    }
}

public class QuizApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<Question> questions = createQuestions();
            Collections.shuffle(questions); // Shuffle questions for variety

            int score = 0;
            int qNum = 1;
            long startTime = System.currentTimeMillis();

            System.out.println(" Welcome to the Java Quiz!");
            System.out.println("----------------------------");

            for (Question question : questions) {
                question.displayQuestion(qNum);

                char userAnswer = ' ';
                boolean validInput = false;

                while (!validInput) {
                    System.out.print("Enter your answer (A/B/C/D): ");
                    try {
                        String input = scanner.nextLine().trim().toUpperCase();
                        if (input.length() != 1 || input.charAt(0) < 'A' || input.charAt(0) > 'D') {
                            throw new InputMismatchException("Please enter a valid option (A/B/C/D).");
                        }
                        userAnswer = input.charAt(0);
                        validInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println( e.getMessage());
                    } catch (Exception e) {
                        System.out.println(" Unexpected error. Please try again.");
                    }
                }

                if (question.isCorrect(userAnswer)) {
                    System.out.println(" Correct!");
                    score++;
                } else {
                    System.out.println(" Incorrect!");
                    System.out.println("Correct Answer: " + question.getCorrectAnswer());
                }

                System.out.println("Explanation: " + question.getExplanation());
                System.out.println("--------------------------------------------------");
                qNum++;
            }

            long endTime = System.currentTimeMillis();
            long timeTaken = (endTime - startTime) / 1000;

            // Final Results
            System.out.println("\n Quiz Completed!");
            System.out.println("Your Score: " + score + "/" + questions.size());
            System.out.println("Correct Answers: " + score);
            System.out.println("Incorrect Answers: " + (questions.size() - score));
            System.out.println("Total Time Taken: " + timeTaken + " seconds");

            // Retry option
            System.out.print("\nWould you like to try again? (Y/N): ");
            String retry = scanner.nextLine().trim().toUpperCase();
            if (!retry.equals("Y")) {
                System.out.println(" Thank you for playing! Goodbye.");
                break;
            }
        }

        scanner.close();
    }

    public static List<Question> createQuestions() {
        List<Question> list = new ArrayList<>();

        list.add(new Question("Which keyword is used to inherit a class in Java?",
                new String[]{"this", "extends", "implements", "super"},
                'B',
                "The 'extends' keyword is used to inherit a class in Java."));

        list.add(new Question("What is the size of int in Java?",
                new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends on system"},
                'B',
                "Java's int type is always 4 bytes regardless of platform."));

        list.add(new Question("Which method is the entry point for a Java program?",
                new String[]{"start()", "main()", "run()", "init()"},
                'B',
                "The 'main()' method is the starting point for any standalone Java application."));

        list.add(new Question("Which of these is not an OOP concept in Java?",
                new String[]{"Polymorphism", "Inheritance", "Compilation", "Encapsulation"},
                'C',
                "'Compilation' is not an OOP concept; it's a part of the build process."));

        list.add(new Question("Which collection does not allow duplicate elements?",
                new String[]{"List", "ArrayList", "Set", "Map"},
                'C',
                "Set is a collection that does not allow duplicate values."));

        return list;
    }
}
