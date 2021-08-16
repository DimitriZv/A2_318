# Create/update customer contact

## Demonstration: Sample REST Requests

## Show all contacts from Seed Data:
http://localhost:8080/contacts/
```bash
{
  "_embedded": {
    "contactList": [
      {
        "id": 1,
        "firstName": "Bilbo",
        "lastName": "Baggins",
        "email": "bil@bibl.bil",
        "position": "burglar",
        "name": "Bilbo Baggins",
        "_links": {
          "self": {
            "href": "http://localhost:8080/contacts/1"
          },
          "contacts": {
            "href": "http://localhost:8080/contacts"
          }
        }
      },
      {
        "id": 2,
        "firstName": "Frodo",
        "lastName": "Baggins",
        "email": "frod@frod.fr",
        "position": "thief",
        "name": "Frodo Baggins",
        "_links": {
          "self": {
            "href": "http://localhost:8080/contacts/2"
          },
          "contacts": {
            "href": "http://localhost:8080/contacts"
          }
        }
      },
      {
        "id": 4,
        "firstName": "Four",
        "lastName": "Four",
        "email": "Four@Four.Four",
        "position": "New Four",
        "name": "Four Four",
        "_links": {
          "self": {
            "href": "http://localhost:8080/contacts/4"
          },
          "contacts": {
            "href": "http://localhost:8080/contacts"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/contacts"
    }
  }
}
```

## create a new contact:
```bash
curl -v -X POST localhost:8080/contacts -H 'Content-Type:application/json' -d '{"name": "Samwise Gamgee", "email": "sam@sam.sm", "position": "gardener"}'
```
## which returns
```bash
```

## update a contact:
```bash
curl -X PUT localhost:8080/contacts/3 -H 'Content-type:application/json' -d '{"name": "Updated Contact", "email": "new@email.new", "position": "New Potistion"}'
```
## which returns
```bash
```

## delete a contact:
```bash
curl -X DELETE localhost:8080/contacts/3
```
## which returns
```bash
```
