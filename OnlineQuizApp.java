import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OnlineQuizApp {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        // Create and add questions to the quiz
        Question question1 = new Question("Who invented the light bulb?",
                new String[]{"A. Thomas Edison", "B. Albert Einstein", "C. Isaac Newton", "D. Alexander Graham Bell"}, 0);
        Question question2 = new Question("What is the capital of Australia?",
                new String[]{"A. Melbourne", "B. Sydney", "C. Canberra", "D. Perth"}, 2);
        Question question3 = new Question("What is the largest mammal in the world?",
                new String[]{"A. Elephant", "B. Blue Whale", "C. Giraffe", "D. Hippopotamus"}, 1);
        Question question4 = new Question("Who wrote the play 'Romeo and Juliet'?",
                new String[]{"A. William Shakespeare", "B. Charles Dickens", "C. Mark Twain", "D. Jane Austen"}, 0);
        Question question5 = new Question("What is the boiling point of water in Celsius?",
                new String[]{"A. 100°C", "B. 0°C", "C. 50°C", "D. 200°C"}, 0);

        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        quiz.addQuestion(question3);
        quiz.addQuestion(question4);
        quiz.addQuestion(question5);

        int score = conductQuiz(quiz);

        // Display user score
        System.out.println("Quiz completed! Your score is: " + score + "/" + quiz.getTotalQuestions());
    }

    public static int conductQuiz(Quiz quiz) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (int i = 0; i < quiz.getTotalQuestions(); i++) {
            Question currentQuestion = quiz.getQuestion(i);
            if (currentQuestion != null) {
                System.out.println("Question " + (i + 1) + ": " + currentQuestion.getQuestionText());
                String[] options = currentQuestion.getOptions();
                for (String option : options) {
                    System.out.println(option);
                }
                System.out.print("Enter your choice (A, B, C, D): ");
                String userAnswer = scanner.nextLine().toUpperCase();

                // Validate user input and calculate score
                if (userAnswer.length() == 1 && userAnswer.matches("[A-D]")) {
                    int userChoice = userAnswer.charAt(0) - 'A';
                    if (userChoice == currentQuestion.getCorrectAnswerIndex()) {
                        score++;
                    }
                } else {
                    System.out.println("Invalid input! Skipping this question.");
                }
            }
        }

        return score;
    }
}

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

class Quiz {
    private List<Question> questions;

    public Quiz() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Question getQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}