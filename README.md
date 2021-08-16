# Create/update customer contact

## Demonstration: Sample REST Requests

## Show all contacts:
http://localhost:8080/contacts/

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
