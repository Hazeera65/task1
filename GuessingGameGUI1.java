import java.util.Random;

public class GuessingGameGUI1 extends Application {
    private static final int MAX_NUMBER = 100;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_ATTEMPTS = 5;

    private int secretNumber;
    private int attempts;

    private TextField guessField;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Guessing Game");

        Label promptLabel = new Label("Guess a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ":");
        guessField = new TextField();
        Button guessButton = new Button("Guess");
        resultLabel = new Label();

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(promptLabel, guessField, guessButton, resultLabel);

        Scene scene = new Scene(layout, 300, 150);

        primaryStage.setScene(scene);
        primaryStage.show();

        // Generate a random number
        Random random = new Random();
        secretNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        attempts = 0;

        // Event handler for the guess button
        guessButton.setOnAction(e -> {
            handleGuess();
        });
    }

    private void handleGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            if (guess < MIN_NUMBER || guess > MAX_NUMBER) {
                showAlert("Please enter a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
                return;
            }
            attempts++;

            if (guess < secretNumber) {
                resultLabel.setText("Too low! Try again.");
            } else if (guess > secretNumber) {
                resultLabel.setText("Too high! Try again.");
            } else {
                showAlert("Congratulations! You've guessed the number " + secretNumber +
                        " correctly in " + attempts + " attempts!");
                System.exit(0);
            }

            if (attempts == MAX_ATTEMPTS) {
                showAlert("Sorry, you've run out of attempts. The correct number was " +
                        secretNumber + ".");
                System.exit(0);
            }

            // Clear the text field for the next guess
            guessField.clear();
        } catch (NumberFormatException ex) {
            showAlert("Please enter a valid integer.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guessing Game");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
