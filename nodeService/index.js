const express = require('express');
const app = express();
const body_parser = require('body-parser');
const eurekaClient = require('./eureka-client');
const axios = require('axios');


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

app.post('/node/users/add', async (req, res) => {
    const { userName, password } = req.body;

    if (userName == null || password == null) {
        return res.status(400).json({ error: 'Veuillez fournir un nom d\'utilisateur et un mot de passe.' });
    }

    const newUser = { userName, password };

    try {
        // Ajoute le nouvel utilisateur localement
        users.push(newUser);

        // Appelle le microservice Spring Boot avec Axios
        const response = await axios.post('http://localhost:8003/spring/authenticate', { userName: newUser.userName, password: newUser.password });
        console.log('Réponse de la méthode authenticate du microservice Spring Boot:', response.data);

        // Envoie une réponse au client
        res.status(201).json({ message: 'Utilisateur ajouté avec succès.', user: newUser, springBootServiceResponse: response.data });
    } catch (error) {
        console.error('Erreur lors de la communication avec le microservice Spring Boot:', error.message);
        res.status(500).send('Erreur lors de la communication avec le microservice Spring Boot');
    }
});


app.get('/node/users/all', (req, res) => {
    res.status(200).json({ users });
});


app.post('/node/spring-data', (req, res) => {
    const dataFromSpring = req.body;

    console.log('Data received from Spring Boot:', dataFromSpring);

    res.status(200).json({ message: 'nodejs service Repond : Data received successfully in Node.js', dataFromSpring });
});