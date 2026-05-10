package com.example.a212014_muhammadarif_drnazatul_project1.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Question;

public class LevelContent {
    public static final Map<String, List<Question>> questionsMap = new HashMap<>();

    static {
        // Level 1: Basic Greetings
        questionsMap.put("1", Arrays.asList(
            new Question(1, "What does 'Selamat Pagi' mean?", Arrays.asList("A) Good Morning", "B) Good Night", "C) Good Afternoon"), 0),
            new Question(2, "What does 'Terima Kasih' mean?", Arrays.asList("A) Please", "B) Thank You", "C) Sorry"), 1)
        ));

        // Level 2: Food and Drinks
        questionsMap.put("2", Arrays.asList(
            new Question(1, "What does 'Nasi' mean?", Arrays.asList("A) Bread", "B) Rice", "C) Noodle"), 1),
            new Question(2, "What does 'Air' mean?", Arrays.asList("A) Fire", "B) Earth", "C) Water"), 2)
        ));

        // Level 3: Basic Numbers
        questionsMap.put("3", Arrays.asList(
            new Question(1, "How do you say 'One' in Malay?", Arrays.asList("A) Satu", "B) Dua", "C) Tiga"), 0),
            new Question(2, "How do you say 'Three' in Malay?", Arrays.asList("A) Empat", "B) Tiga", "C) Lima"), 1)
        ));
    }
}
