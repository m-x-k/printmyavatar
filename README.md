# PrintMyAvatar.com

https://print-my-avatar.herokuapp.com

## Requirements

* Yarn
* Node
* Java 1.7+

## Development setup

```
yarn install
yarn watch
./gradlew clean build bootRun
```

The application uses a number of external services for storage (AWS S3 bucket), authentication and bot checking.
Therefore to run this locally you will need to add the following environment variables using your own credentials:
```
AWS_ACCESS_KEY_ID=####
AWS_SECRET_ACCESS_KEY=####
S3_BUCKET_NAME=####
RECAPTCHA_SECRET=####
```

In a browser go to `http://localhost:8080/`.

