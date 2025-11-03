import java.util.*;

// Question class to store quiz questions
class Question {
    private String question;
    private List<String> options;
    private int correctAnswer;

    public Question(String question, List<String> options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrect(int answer) {
        return answer == correctAnswer;
    }
}

// QuizResult class to store and display results
class QuizResult {
    private String userName;
    private int totalQuestions;
    private int correctAnswers;
    private int wrongAnswers;
    private double percentage;

    public QuizResult(String userName, int totalQuestions, int correctAnswers) {
        this.userName = userName;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = totalQuestions - correctAnswers;
        this.percentage = (correctAnswers * 100.0) / totalQuestions;
    }

    public void displayResult() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("QUIZ RESULTS FOR: " + userName);
        System.out.println("=".repeat(50));
        System.out.println("Total Questions: " + totalQuestions);
        System.out.println("Correct Answers: " + correctAnswers);
        System.out.println("Wrong Answers: " + wrongAnswers);
        System.out.printf("Score: %.2f%%\n", percentage);
        System.out.println("=".repeat(50));

        // Grade based on percentage
        if (percentage >= 90) {
            System.out.println("Grade: A+ (Excellent!)");
        } else if (percentage >= 80) {
            System.out.println("Grade: A (Very Good!)");
        } else if (percentage >= 70) {
            System.out.println("Grade: B (Good!)");
        } else if (percentage >= 60) {
            System.out.println("Grade: C (Average)");
        } else if (percentage >= 50) {
            System.out.println("Grade: D (Below Average)");
        } else {
            System.out.println("Grade: F (Failed - Better luck next time!)");
        }
        System.out.println("=".repeat(50));
    }
}

// Main Quiz Application
public class OnlineQuizApp {
    private static List<Question> questions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Initialize quiz questions
    public static void initializeQuestions() {
        questions.add(new Question(
                "What is the capital of France?",
                Arrays.asList("1. London", "2. Paris", "3. Berlin", "4. Madrid"),
                2
        ));

        questions.add(new Question(
                "Which planet is known as the Red Planet?",
                Arrays.asList("1. Venus", "2. Jupiter", "3. Mars", "4. Saturn"),
                3
        ));

        questions.add(new Question(
                "What is 15 + 27?",
                Arrays.asList("1. 42", "2. 52", "3. 32", "4. 62"),
                1
        ));

        questions.add(new Question(
                "Who painted the Mona Lisa?",
                Arrays.asList("1. Picasso", "2. Van Gogh", "3. Da Vinci", "4. Michelangelo"),
                3
        ));

        questions.add(new Question(
                "What is the largest ocean on Earth?",
                Arrays.asList("1. Atlantic", "2. Indian", "3. Arctic", "4. Pacific"),
                4
        ));

        questions.add(new Question(
                "In which year did World War II end?",
                Arrays.asList("1. 1943", "2. 1944", "3. 1945", "4. 1946"),
                3
        ));

        questions.add(new Question(
                "What is the smallest prime number?",
                Arrays.asList("1. 0", "2. 1", "3. 2", "4. 3"),
                3
        ));

        questions.add(new Question(
                "Which programming language is known as the 'mother of all languages'?",
                Arrays.asList("1. Python", "2. C", "3. Java", "4. Assembly"),
                2
        ));
    }

    // Display welcome message
    public static void displayWelcome() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("     WELCOME TO ONLINE QUIZ APPLICATION");
        System.out.println("=".repeat(50));
    }

    // Conduct the quiz
    public static int conductQuiz() {
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);

            System.out.println("\n" + "-".repeat(50));
            System.out.println("Question " + (i + 1) + " of " + questions.size());
            System.out.println("-".repeat(50));
            System.out.println(q.getQuestion());

            for (String option : q.getOptions()) {
                System.out.println(option);
            }

            int userAnswer = 0;
            boolean validInput = false;

            // Input validation loop
            while (!validInput) {
                System.out.print("\nYour answer (1-4): ");
                try {
                    userAnswer = scanner.nextInt();
                    if (userAnswer >= 1 && userAnswer <= 4) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid input! Please enter a number between 1 and 4.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.next(); // Clear invalid input
                }
            }

            // Check answer
            if (q.isCorrect(userAnswer)) {
                System.out.println("✓ Correct!");
                score++;
            } else {
                System.out.println("✗ Wrong! Correct answer was: " + q.getCorrectAnswer());
            }
        }

        return score;
    }

    // Main method
    public static void main(String[] args) {
        displayWelcome();

        // Get user name
        System.out.print("\nEnter your name: ");
        scanner.nextLine(); // Clear buffer
        String userName = scanner.nextLine();

        System.out.println("\nHello, " + userName + "!");
        System.out.println("The quiz contains " + questions.size() + " questions.");
        System.out.println("Each correct answer = 1 point");

        System.out.print("\nAre you ready to start? (yes/no): ");
        String ready = scanner.nextLine();

        if (ready.equalsIgnoreCase("yes") || ready.equalsIgnoreCase("y")) {
            initializeQuestions();
            int score = conductQuiz();

            // Display results
            QuizResult result = new QuizResult(userName, questions.size(), score);
            result.displayResult();
        } else {
            System.out.println("\nMaybe next time! Goodbye!");
        }

        scanner.close();
    }
}