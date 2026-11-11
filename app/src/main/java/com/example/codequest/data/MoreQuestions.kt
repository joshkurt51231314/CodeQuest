package com.example.codequest.data

private fun q(text: String, correct: String, w1: String, w2: String, w3: String, why: String) =
    Question(text, listOf(correct, w1, w2, w3), 0, why)

object MoreQuestions {
    val all = listOf(
        // Data structures and algorithms
        q("WHICH DATA STRUCTURE USES FIFO ORDER?", "QUEUE", "STACK", "TREE", "HASH TABLE",
            "A queue is First-In, First-Out, like a line of people: the first to arrive is served first."),
        q("WHAT IS AN ARRAY?", "A LIST OF ELEMENTS ACCESSED BY INDEX", "A FUNCTION THAT RETURNS NOTHING", "A TYPE OF LOOP", "A NETWORK PROTOCOL",
            "An array stores elements in order, and each one is reached by its index number."),
        q("WHAT IS THE TIME COMPLEXITY OF READING AN ARRAY ITEM BY INDEX?", "O(1)", "O(N)", "O(LOG N)", "O(N^2)",
            "Reading by index takes the same constant time no matter how big the array is."),
        q("WHICH SORT REPEATEDLY SWAPS NEIGHBORING ITEMS THAT ARE OUT OF ORDER?", "BUBBLE SORT", "QUICKSORT", "MERGE SORT", "HEAP SORT",
            "Bubble sort compares neighbors and swaps them, so large values bubble up to the end."),
        q("WHAT IS RECURSION?", "A FUNCTION THAT CALLS ITSELF", "A LOOP WITH NO CONDITION", "A WAY TO COPY FILES", "A SORTING METHOD",
            "In recursion a function calls itself on a smaller problem until it reaches a base case."),

        // Programming basics
        q("WHICH DATA TYPE STORES TRUE OR FALSE?", "BOOLEAN", "INTEGER", "STRING", "FLOAT",
            "A Boolean can only be true or false."),
        q("WHICH SYMBOL STARTS A SINGLE-LINE COMMENT IN KOTLIN?", "//", "#", "--", "<!--",
            "Kotlin uses // for single-line comments and /* */ for blocks."),
        q("WHAT DOES A FOR LOOP DO?", "REPEATS CODE A SET NUMBER OF TIMES", "STOPS THE PROGRAM", "DECLARES A VARIABLE", "CONNECTS TO A DATABASE",
            "A for loop repeats its body for each value in a range or collection."),
        q("WHAT DOES A COMPILER DO?", "TRANSLATES SOURCE CODE INTO MACHINE CODE", "DRAWS THE USER INTERFACE", "STORES DATA IN A DATABASE", "FIXES BUGS AUTOMATICALLY",
            "A compiler converts the code you write into instructions the computer can run."),
        q("WHAT IS A BUG IN SOFTWARE?", "AN ERROR OR FLAW IN A PROGRAM", "A SPEED BOOST", "A TYPE OF LOOP", "A BACKUP COPY",
            "A bug is a mistake in the code that makes the program behave wrongly."),
        q("WHAT DOES API STAND FOR?", "APPLICATION PROGRAMMING INTERFACE", "ADVANCED PROGRAM INSTALLER", "AUTOMATED PROCESS INPUT", "APPLIED PROTOCOL INDEX",
            "An API lets one piece of software talk to another in a defined way."),
        q("WHAT DOES UI STAND FOR?", "USER INTERFACE", "UNIVERSAL INPUT", "UNIT INDEX", "USER INSTALLATION",
            "The UI is everything the user sees and touches in an app."),

        // Databases
        q("WHICH SQL COMMAND RETRIEVES DATA FROM A TABLE?", "SELECT", "INSERT", "UPDATE", "DROP",
            "SELECT reads rows. INSERT adds them, UPDATE changes them, DROP removes a table."),
        q("WHICH SQL COMMAND DELETES AN ENTIRE TABLE?", "DROP TABLE", "SELECT TABLE", "UPDATE TABLE", "INSERT TABLE",
            "DROP TABLE removes the table and all its data."),
        q("WHAT IS A FOREIGN KEY?", "A FIELD THAT LINKS TO THE PRIMARY KEY OF ANOTHER TABLE", "A KEY THAT ENCRYPTS DATA", "A SECOND PASSWORD", "A KEY USED ONLY FOR SORTING",
            "Foreign keys connect tables, for example an order row pointing to a customer row."),
        q("WHICH ANDROID LIBRARY STORES DATA IN A LOCAL SQLITE DATABASE?", "ROOM", "COIL", "RETROFIT", "LOTTIE",
            "Room is Android's official library for working with a local SQLite database."),

        // Networking
        q("WHICH DEVICE CONNECTS DIFFERENT NETWORKS AND FORWARDS DATA BETWEEN THEM?", "ROUTER", "HUB", "SWITCH", "REPEATER",
            "A router moves data between networks, such as your home network and the internet."),
        q("WHAT DOES IP STAND FOR IN IP ADDRESS?", "INTERNET PROTOCOL", "INTERNAL PORT", "INTERFACE PROFILE", "INFORMATION PACKET",
            "IP is the Internet Protocol, which gives each device an address."),
        q("WHICH PROTOCOL IS USED TO SEND EMAIL?", "SMTP", "FTP", "SSH", "DNS",
            "SMTP (Simple Mail Transfer Protocol) sends email between servers."),
        q("WHICH PROTOCOL TRANSFERS FILES BETWEEN COMPUTERS?", "FTP", "SMTP", "DHCP", "ICMP",
            "FTP is the File Transfer Protocol."),
        q("WHAT DOES DHCP DO?", "AUTOMATICALLY ASSIGNS IP ADDRESSES TO DEVICES", "ENCRYPTS WEB TRAFFIC", "TRANSLATES DOMAIN NAMES", "BLOCKS MALWARE",
            "DHCP hands out IP addresses so you don't have to set them by hand."),
        q("WHAT DOES LAN STAND FOR?", "LOCAL AREA NETWORK", "LONG ACCESS NODE", "LINKED APP NETWORK", "LOCAL ACCESS NUMBER",
            "A LAN connects devices in a small area such as a home, school or office."),
        q("WHAT IS CLOUD COMPUTING?", "USING REMOTE SERVERS ON THE INTERNET TO STORE AND PROCESS DATA", "STORING FILES ONLY ON A USB DRIVE", "A TYPE OF WEATHER SOFTWARE", "A PROGRAMMING LANGUAGE",
            "The cloud means using servers run by someone else over the internet instead of your own machine."),

        // Web
        q("WHAT DOES HTML STAND FOR?", "HYPERTEXT MARKUP LANGUAGE", "HIGH TEXT MACHINE LANGUAGE", "HYPERLINK TEXT MARKING LOGIC", "HOME TOOL MARKUP LANGUAGE",
            "HTML describes the structure of a web page."),
        q("WHICH LANGUAGE IS USED TO STYLE WEB PAGES?", "CSS", "SQL", "PYTHON", "BASH",
            "CSS controls colors, fonts and layout of web pages."),

        // Hardware and operating systems
        q("WHAT DOES RAM STAND FOR?", "RANDOM ACCESS MEMORY", "READ ALWAYS MEMORY", "RAPID ACCESS MODULE", "REMOTE ACCESS MEMORY",
            "RAM is fast, temporary memory that holds data the computer is using right now."),
        q("HOW MANY BITS ARE IN A BYTE?", "8", "4", "16", "10",
            "One byte is made of 8 bits."),
        q("WHAT IS 1010 IN BINARY EQUAL TO IN DECIMAL?", "10", "5", "12", "8",
            "1010 = 8 + 0 + 2 + 0 = 10."),
        q("WHICH COMPONENT IS THE MAIN CIRCUIT BOARD OF A COMPUTER?", "MOTHERBOARD", "POWER SUPPLY", "GPU", "HEATSINK",
            "The motherboard connects the CPU, memory, storage and other parts."),
        q("WHAT DOES GPU STAND FOR?", "GRAPHICS PROCESSING UNIT", "GENERAL PROGRAM UNIT", "GLOBAL POWER UNIT", "GRAPHIC PRINT UTILITY",
            "A GPU handles graphics and other highly parallel work."),
        q("WHICH IS AN EXAMPLE OF AN OPERATING SYSTEM?", "LINUX", "KOTLIN", "HTML", "SQL",
            "Linux is an operating system. Kotlin and SQL are languages, and HTML is a markup language."),
        q("WHICH KERNEL POWERS ANDROID?", "LINUX", "WINDOWS NT", "DARWIN", "FREEBSD",
            "Android is built on the Linux kernel."),

        // Security
        q("WHICH ATTACK USES FAKE EMAILS TO TRICK PEOPLE INTO REVEALING INFORMATION?", "PHISHING", "DDOS", "BRUTE FORCE", "BUFFER OVERFLOW",
            "Phishing messages pretend to be trustworthy to steal passwords or personal data."),
        q("WHAT DOES A FIREWALL DO?", "FILTERS NETWORK TRAFFIC USING RULES", "SPEEDS UP THE INTERNET", "STORES PASSWORDS", "BACKS UP FILES",
            "A firewall allows or blocks network traffic according to security rules."),
        q("WHAT IS TWO-FACTOR AUTHENTICATION?", "USING TWO DIFFERENT PROOFS OF IDENTITY TO LOG IN", "TYPING THE PASSWORD TWICE", "HAVING TWO USERNAMES", "LOGGING IN FROM TWO PHONES",
            "2FA combines something you know, like a password, with something you have, like a code on your phone."),
        q("WHY ARE PASSWORDS HASHED BEFORE STORING?", "SO THE REAL PASSWORD IS NEVER STORED", "SO THEY CAN BE DECRYPTED LATER", "TO MAKE LOGIN FASTER", "TO SAVE DISK SPACE",
            "A hash is a one-way scramble, so even someone who steals the database can't read the original passwords."),

        // Git and Android
        q("WHICH GIT COMMAND DOWNLOADS A COPY OF A REMOTE REPOSITORY?", "GIT CLONE", "GIT COMMIT", "GIT BRANCH", "GIT ADD",
            "git clone copies a whole repository, with its history, to your computer."),
        q("WHAT DOES GIT PUSH DO?", "SENDS YOUR LOCAL COMMITS TO A REMOTE REPOSITORY", "DOWNLOADS NEW FILES", "DELETES A BRANCH", "CREATES A NEW FILE",
            "git push uploads your commits, for example to GitHub."),
        q("WHICH FILE DECLARES AN ANDROID APP'S ACTIVITIES?", "ANDROIDMANIFEST.XML", "BUILD.GRADLE", "STRINGS.XML", "README.MD",
            "The manifest tells Android about your app, including its activities and permissions."),
        q("WHAT IS A VIEWMODEL USED FOR IN ANDROID?", "HOLDING UI STATE AND SURVIVING SCREEN ROTATION", "DRAWING PIXELS ON THE SCREEN", "STORING PASSWORDS", "COMPILING THE APP",
            "A ViewModel keeps screen data and logic separate from the UI and survives rotation.")
    )
}
