# Volkwagen Digital Hub Challenge

This project is a Kotlin-based solution for the Volkwagen Digital Hub Challenge.

## Requirements

- Java 22
- Kotlin 2.1.20
- Gradle

## Setup

1. Clone the repository:
   ```sh
   git clone <repo-url>
   cd Volkwagen-Digital-Hub-Challenge
   ```

2. Build the project:
   ```sh
   ./gradlew build
   ```

## Running Tests

To execute the tests, run:
```sh
./gradlew test
```

## Docker

To build and run the project using Docker:

```sh
docker build -t volkwagen-challenge .
docker run --rm volkwagen-challenge
```

To run tests inside Docker:

```sh
docker build -t volkwagen-challenge-test --target test .
docker run --rm volkwagen-challenge-test
```

## Running with Docker and stdin

To provide input to the app running in Docker, use:

```sh
docker run --rm -i volkwagen-challenge
```

Or, to pipe input from a file:

```sh
docker run --rm -i volkwagen-challenge < input.txt
```

## Example
```sh
$ cat input.txt
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
$ docker run --rm -i volkwagen-challenge < input.txt
Hello Volkwagen Digital Hub Reviewers
-------------------------------------
Please enter the input by stdin. A blank line will be considered end of instructions and start the app

Output:
1 3 N
5 1 E

```

## Dependencies

- Kotlin Standard Library
- JUnit 5
- MockK

## Notes

- Ensure you are using Java 22 for compatibility.
- All dependencies are managed via Gradle.
