# Random-Words Generator API

A RESTful API for retrieving and managing random words. It integrates the [Datamuse API](https://www.datamuse.com/api/) to populate a PostgreSQL database and provides endpoints for retrieving, adding, updating, and deleting words. The application is fully Dockerized for simplified deployment.

## Table of Contents

- [Install](#install)
    - [Prerequisites](#prerequisites)
    - [Installation Steps](#installation-steps)
- [Usage](#usage)
    - [Example](#example)
    - [Notes](#notes)
- [Contributing](#contributing)
- [License](#license)

## Install

### Prerequisites

This project requires:
- [Git](https://git-scm.com/)
- [Docker](https://www.docker.com/)
 
### Installation Steps

1. Clone this repository:
    ```sh
    git clone https://github.com/anthonyfer0220/random-words-generator-api.git
    ```

2. Navigate to the project directory:
    ```sh
    cd random-words-generator-api
    ```

3. Build and run the project using Docker:
    ```sh
    docker compose up --build
    ```

## Usage

Once the API is running, you can interact with it using `curl`, [Postman](https://www.postman.com/), or a web browser.

### Example

- Populate or replace the database with words from an external API:

```sh
curl -X PUT "http://localhost:8080/api/v1/words"
```

- Get a random word:

```sh
curl "http://localhost:8080/api/v1/words/random"
```

- Retrieve a paginated list of words:

```sh
curl "http://localhost:8080/api/v1/words"
```

- Add a new word:

```sh
curl -X POST "http://localhost:8080/api/v1/words" -H "Content-Type: application/json" -d '{"content": "example"}'
```

### Notes

- The API runs on localhost:8080 by default.
- The database is managed using PostgreSQL within a Docker container.
- To reset the database, stop the containers and remove the volume:

```sh
docker compose down -v
```

- Suggestion: Use `jq` for pretty-printing JSON and the `-s` flag to suppress unnecessary output when interacting with the API via `curl`:

```sh
curl -s "http://localhost:8080/api/v1/words" | jq
```

## Contributing

Contributions are welcome. Feel free to open a pull request or submit issues.

## License

[MIT License](LICENSE) © Anthony Fernandez