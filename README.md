# Directory Command Interpreter

## Prerequisites

- Java 21

```bash
brew install openjdk@21
```

```bash
java -version
```

## Build

```bash
./gradlew clean build
```

## Run

```bash
java -jar app/bin/app.jar files\command-input.txt
````
You can pass in any file to parse.

## Run Test Suite

```bash
./gradlew test
```

# Summary

## Usage

The Directory Command Interpreter allows you to manage directories through various commands. Below are the available
commands and examples of how to use them.

## Commands

- **CREATE \<directory\>**: Creates a new directory.
- **DELETE \<directory\>**: Deletes an existing directory.
- **LIST**: Lists all subdirectories.
- **MOVE \<source\> \<target\>**: Moves a directory to a new location.

## Examples

> [!NOTE]
> `root` is a reserved directory name and can only be used as a target for the `MOVE` command.

### Create a Directory
```shell
CREATE fruits
CREATE fruits/apples
```

### Delete a Directory
```shell
CREATE fruits/apples
DELETE fruits/apples
```

### List Subdirectories
```shell
CREATE fruits/apples
DELETE fruits/apples
LIST
fruits
  apples

```

### Move a Directory
```shell
CREATE grains/squash
MOVE grains/squash vegetables
```
