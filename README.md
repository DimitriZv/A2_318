
http://localhost:8080/contacts/


# Create/update customer contact

## create new:
```bash
curl -v -X POST localhost:8080/contacts -H 'Content-Type:application/json' -d '{"name": "Samwise Gamgee", "email": "sam@sam.sm", "position": "gardener"}'
```
## update:
```bash
## curl -X PUT localhost:8080/contacts/3 -H 'Content-type:application/json' -d '{"name": "Updated Contact", "email": "new@email.new", "position": "New Potistion"}'
```
## delete:
```bash
## curl -X DELETE localhost:8080/contacts/3
```
