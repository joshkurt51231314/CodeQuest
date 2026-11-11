package com.example.codequest.data

data class Question(
    val text: String,
    val options: List<String>,
    val answerIndex: Int,
    val explanation: String
)

fun Question.withShuffledOptions(): Question {
    val correct = options[answerIndex]
    val shuffled = options.shuffled()
    return copy(options = shuffled, answerIndex = shuffled.indexOf(correct))
}

object QuestionBank {
    val all = listOf(
        Question("WHICH DATA STRUCTURE USES LIFO ORDER?",
            listOf("QUEUE", "STACK", "ARRAY", "TREE"), 1,
            "A stack is Last-In, First-Out: the newest item is removed first."),
        Question("WHAT DOES SQL STAND FOR?",
            listOf("STRONG QUERY LOGIC", "STRUCTURED QUERY LANGUAGE", "SIMPLE QUEUE LIST", "SERVER QUERY LINK"), 1,
            "SQL is Structured Query Language, used to talk to relational databases."),
        Question("WHICH LAYER OF THE OSI MODEL HANDLES IP ADDRESSING?",
            listOf("DATA LINK", "TRANSPORT", "NETWORK", "SESSION"), 2,
            "The Network layer (Layer 3) handles logical addressing and routing."),
        Question("WHAT IS THE BIG-O OF BINARY SEARCH?",
            listOf("O(N)", "O(1)", "O(N^2)", "O(LOG N)"), 3,
            "Binary search halves the search space each step, so it is O(log n)."),
        Question("WHICH PROTOCOL SECURES WEB TRAFFIC?",
            listOf("FTP", "HTTP", "HTTPS", "SMTP"), 2,
            "HTTPS is HTTP over TLS, which encrypts the connection."),
        Question("WHICH KEYWORD DECLARES A CONSTANT IN KOTLIN?",
            listOf("VAR", "VAL", "LET", "FINAL"), 1,
            "'val' is read-only; 'var' is mutable."),
        Question("WHAT DOES A PRIMARY KEY DO?",
            listOf("ENCRYPTS A ROW", "UNIQUELY IDENTIFIES A ROW", "SORTS A TABLE", "DELETES DUPLICATES"), 1,
            "A primary key uniquely identifies each record and cannot be NULL."),
        Question("WHICH ATTACK INJECTS MALICIOUS SQL INTO INPUT FIELDS?",
            listOf("PHISHING", "DDOS", "SQL INJECTION", "MITM"), 2,
            "SQL injection abuses unsanitized input. Use prepared statements."),
        Question("WHAT DOES 'GIT COMMIT' DO?",
            listOf("UPLOADS TO REMOTE", "SAVES A SNAPSHOT LOCALLY", "DELETES A BRANCH", "MERGES BRANCHES"), 1,
            "Commit records staged changes to your local repository history."),
        Question("WHICH IS NOT AN OOP PILLAR?",
            listOf("ENCAPSULATION", "POLYMORPHISM", "COMPILATION", "INHERITANCE"), 2,
            "The pillars are encapsulation, abstraction, inheritance and polymorphism."),
        Question("WHAT IS THE DEFAULT PORT OF HTTPS?",
            listOf("80", "21", "443", "22"), 2,
            "HTTPS uses port 443. HTTP uses 80."),
        Question("WHICH SORT IS GENERALLY FASTEST ON AVERAGE?",
            listOf("BUBBLE SORT", "QUICKSORT", "SELECTION SORT", "INSERTION SORT"), 1,
            "Quicksort averages O(n log n); the others are O(n^2)."),
        Question("WHAT DOES CPU STAND FOR?",
            listOf("CENTRAL PROCESSING UNIT", "COMPUTER PERSONAL UNIT", "CENTRAL PROGRAM UTILITY", "CORE PROCESSING USER"), 0,
            "The CPU (Central Processing Unit) is the brain of the computer that executes instructions."),
        Question("WHICH HTML TAG CREATES A HYPERLINK?",
            listOf("<A>", "<LINK>", "<HREF>", "<URL>"), 0,
            "The anchor tag <a> with an href attribute creates a link."),
        Question("WHAT DOES DNS DO?",
            listOf("TRANSLATES DOMAIN NAMES TO IP ADDRESSES", "ENCRYPTS EMAILS", "BLOCKS VIRUSES", "STORES WEB FILES"), 0,
            "DNS works like a phonebook, turning names like google.com into IP addresses."),
        Question("WHICH IS NON-VOLATILE STORAGE?",
            listOf("RAM", "CACHE", "SSD", "REGISTER"), 2,
            "An SSD keeps its data without power. RAM, cache and registers lose data when power is off."),
        Question("WHICH LOOP ALWAYS RUNS AT LEAST ONCE?",
            listOf("FOR", "WHILE", "DO-WHILE", "FOREACH"), 2,
            "A do-while loop checks its condition after the body runs, so it executes at least once.")
    )
}
