# Survey app backend

## Endpoint documentation

### Authentication

This API is using Basic Authentication

#### User registration
`/register`

Roles available: ROLE_USER or ROLE_ADMIN

```json
{
    "username": "mihai",
    "password": "test",
    "roles": "ROLE_USER"
}
```
### Surveys

#### Get all surveys

Get a list of all available surveys

`GET /surveys`

#### Get survey by ID
`GET /surveys/{id}`


#### Define survey

Add a new survey in system.

`POST /surveys`

POST Request body example
```json
{
  "title": "Test Survey",
  "questions": [
    {
      "question": "Which number is odd?",
      "answers": [
        "10",
        "11",
        "18",
        "22"
      ],
      "required": true
    },
    {
      "question": "Which number is even?",
      "answers": [
        "10",
        "11",
        "18",
        "22"
      ],
      "required": false
    }
  ]
}
```

#### Conduct survey

##### View open surveys
`GET /surveys?open`

##### Open survey
`POST /surveys/{id}/open`

##### Close survey
`POST /surveys/{id}/close`

##### Respond to survey
`POST /surveys/{id}/respond`

```json
{
  "survey": 10,
  "answers": [
    {
      "question": 1,
      "answer": 10
    }
  ]
}
```

#### View Results
