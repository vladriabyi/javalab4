import java.util.*;

// Клас для літери
class Letter {
    private char value;

    public Letter(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}

// Клас для слова, яке складається з масиву літер
class Word {
    private List<Letter> letters;

    public Word(String word) {
        letters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            letters.add(new Letter(c));
        }
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public String getText() {
        StringBuilder word = new StringBuilder();
        for (Letter letter : letters) {
            word.append(letter.getValue());
        }
        return word.toString();
    }
}

// Клас для розділового знака
class PunctuationMark {
    private char mark;

    public PunctuationMark(char mark) {
        this.mark = mark;
    }

    public char getMark() {
        return mark;
    }
}

// Клас для речення, яке складається з масиву слів та розділових знаків
class Sentence {
    private List<Word> words;
    private List<PunctuationMark> punctuationMarks;

    public Sentence(String sentence) {
        words = new ArrayList<>();
        punctuationMarks = new ArrayList<>();
        String[] parts = sentence.split("\\s+");

        for (String part : parts) {
            if (part.matches("[.,!?;:()\"'-]")) { // Підтримка додаткових розділових знаків
                punctuationMarks.add(new PunctuationMark(part.charAt(0)));
            } else {
                words.add(new Word(part));
            }
        }
    }

    public List<Word> getWords() {
        return words;
    }

    public List<PunctuationMark> getPunctuationMarks() {
        return punctuationMarks;
    }

    public int getWordCount() {
        return words.size();
    }

    public String getText() {
        StringBuilder sentence = new StringBuilder();
        for (Word word : words) {
            sentence.append(word.getText()).append(" ");
        }
        for (PunctuationMark mark : punctuationMarks) {
            sentence.append(mark.getMark()).append(" ");
        }
        return sentence.toString().trim();
    }
}

// Клас для тексту, який складається з масиву речень
class Text {
    private List<Sentence> sentences;

    public Text(String text) {
        sentences = new ArrayList<>();
        String[] sentenceArray = text.split("(?<=[.!?])\\s*"); // Розділення за знаками пунктуації

        for (String sentence : sentenceArray) {
            sentences.add(new Sentence(sentence.trim()));
        }
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void sortSentencesByWordCount() {
        sentences.sort(Comparator.comparingInt(Sentence::getWordCount));
    }

    public void printSentences() {
        for (Sentence sentence : sentences) {
            System.out.println(sentence.getText());
        }
    }
}

