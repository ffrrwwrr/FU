const express = require('express');
const app = express(); 
const port = 8089;

app.get('/', function(req, res) {
    res.sendFile('C:/Users/user/Desktop/Web/Lab01/index.html')
});

app.listen(port);
console.log('Server started');