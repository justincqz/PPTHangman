import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Word {

    private Set<Character> guessedChars;
    private String originalWord;

    Word(String originalWord) {
        this.originalWord = originalWord;
        guessedChars = new HashSet<>();
    }


    public GuessResult guess(String guess) {
        if (guess.length() == 1) {
            char c = guess.charAt(0);

            if (!Character.isLetter(c)) {
                return GuessResult.UNGUESSABLE_CHAR;
            }

            if (guessedChars.contains(c)) {
                return GuessResult.CHAR_ALREADY_GUESSED;
            }

            guessedChars.add(c);

            if (originalWord.contains(c + "")) {
                if (originalWord.chars().mapToObj(i -> (char) i).filter(Character::isLetter)
                    .collect(Collectors.toSet()).equals(guessedChars)) {
                    return GuessResult.COMPLETED_WORD;
                }
                return GuessResult.CORRECT_GUESS;
            }
        }

        if (originalWord.equals(guess)) {
            return GuessResult.COMPLETED_WORD;
        }

        return GuessResult.INCORRECT_GUESS;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        originalWord.chars().mapToObj(i -> (char) i).map(c -> !Character.isLetter(c)
            || guessedChars.contains(c) ? c: '_').forEach(builder::append);
        return builder.toString();
    }

}
