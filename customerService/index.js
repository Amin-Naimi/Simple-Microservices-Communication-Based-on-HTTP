const express = require('express');
const app = express();
const body_parser = require('body-parser');
const eurekaClient = require('./eureka-client');

app.use(body_parser.json());

app.listen(8001, () => {
    console.log("Customer écoute sur le port 8001");
});

/*app.use('/artune', (req, res) => {
    return res.status(200).json({ "msg": "Salut à Artune" });
});*/

app.get('/node/artune/get', (req, res) => {
    return res.json({ "msg": "Salut depuis le service client" });
});

eurekaClient.registerWithEureka('CUSTOMER-SERVICE', 8001);
