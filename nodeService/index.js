const express = require('express');
const app = express();
const body_parser = require('body-parser');
const eurekaClient = require('./eureka-client');

app.use(body_parser.json());

app.listen(8001, () => {
    console.log("node Service écoute sur le port 8001");
});

/*app.use('/artune', (req, res) => {
    return res.status(200).json({ "msg": "Salut à Artune" });
});

app.get('/node/artune/get', (req, res) => {
    return res.json({ "msg": "Salut depuis le service client" });
});
*/
eurekaClient.registerWithEureka('NODE-SERVICE', 8001);

let users = [];

app.post('/node/users/add', (req, res) => {
    const { userName, password } = req.body;

    if (userName == null || password == null) {
        return res.status(400).json({ error: 'Veuillez fournir un nom d\'utilisateur et un mot de passe.' });
    }
    const newUser = { userName, password }
    users.push(newUser)
    res.status(201).json({ message: 'Utilisateur ajouté avec succès.', user: newUser });

})

app.get('/node/users/all', (req, res) => {
    res.status(200).json({ users });
});