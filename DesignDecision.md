# DesignDecisions.md

## Goal of the program

The objective is to:

* **Read words from a file**, one word per line.
* **Group words that are anagrams** together.
* **Print each group on a separate line**.

---

## What I used

### `Map<String, List<String>> anagramGroups`

I used a **HashMap** where the **key** is the sorted version of the word and the **value** is a list of words that share this signature. This allows all anagrams to be grouped together efficiently, since different words that are anagrams will sort to the same key.

---

### `BufferedReader`

I chose `BufferedReader` because it reads the file **line by line**, is **memory efficient**, and is generally **faster** than using `Scanner` for simple line reading, especially on large files.

---

## How it works

* The program opens the file with `BufferedReader` and reads each line (word).
* It creates a "signature" by sorting the letters of the word.
* It uses the signature as the key in the map, appending the word to the corresponding list.
* At the end, it prints all groups of words that are anagrams.

This ensures all anagrams are printed together on the same line.

---

## Error handling

The program checks the command-line arguments to ensure **exactly one argument** (the filename) is provided. If not, it prints usage info and exits.

```java
if (args.length != 1) {
    System.out.println("Usage: java Anagram <file_name>");
    return;
}
```

It also catches `IOException` to handle cases where the file is missing or unreadable, printing an appropriate error message.

```java
catch (IOException e) {
    System.err.println("Error reading file: " + e.getMessage());
}
```
---
## About Scalability
This program can easily handle millions of words, because of the combination of ```HashMap``` and ```BufferedReader``` , which were used for fast grouping and memory-efficient line reading.
If we talk about amounts such as billion of words or more then we can use the Divide and Conquer algorithm to split the input into multiple files and such making the process simpler. We can also use our multi-core CPUs to assign multiple threads for each document. These partial results can then be merged, dramatically reducing total runtime.